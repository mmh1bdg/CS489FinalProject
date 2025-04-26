package com.radix.usermanagement.service.impl;

import com.radix.usermanagement.dto.request.LoginRequestDTO;
import com.radix.usermanagement.dto.request.UserRegistrationRequestDTO;
import com.radix.usermanagement.dto.response.LoginResponseDTO;
import com.radix.usermanagement.dto.response.UserWithTokenResponseDTO;
import com.radix.usermanagement.mapper.UserMapper;
import com.radix.usermanagement.model.*;
import com.radix.usermanagement.repository.*;
import com.radix.usermanagement.security.JwtUtils;
import com.radix.usermanagement.service.AuthService;
import com.radix.usermanagement.service.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AddressRepository addressRepository;
    private final CreditCardRepository creditCardRepository;
    private final ProfileRepository profileRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Override
    public UserWithTokenResponseDTO register(UserRegistrationRequestDTO requestDTO) {
        // Check if userId or email already exists
        if (userRepository.findByUserId(requestDTO.userId()).isPresent() ||
                userRepository.findByEmail(requestDTO.email()).isPresent()) {
            throw new RuntimeException("User already exists with same userId or email");
        }

        // Create and save User
        User user = userMapper.toUser(requestDTO);
        user.setPassword(passwordEncoder.encode(requestDTO.password()));
        user.setActive(true); // Set active true during registration
        User savedUser = userRepository.save(user);

        // Create and save Address
        Address address = Address.builder()
                .road(requestDTO.road())
                .city(requestDTO.city())
                .state(requestDTO.state())
                .zipCode(requestDTO.zipCode())
                .country(requestDTO.country())
                .build();
        Address savedAddress = addressRepository.save(address);

        // Create and save CreditCard
        CreditCard creditCard = CreditCard.builder()
                .cardNumber(requestDTO.cardNumber())
                .expiryDate(Date.valueOf(requestDTO.expiryDate()))
                .cvv(requestDTO.cvv())
                .provider(requestDTO.provider())
                .nameOnCard(requestDTO.nameOnCard())
                .build();
        CreditCard savedCreditCard = creditCardRepository.save(creditCard);

        // Create and save Profile
        Profile profile = Profile.builder()
                .userId(savedUser.getUserId())
                .firstName(requestDTO.firstName())
                .lastName(requestDTO.lastName())
                .address(savedAddress)
                .phone(requestDTO.phone())
                .creditCard(savedCreditCard)
                .photo(requestDTO.photo())
                .build();
        profileRepository.save(profile);

        // Assign Role
        Role role = roleService.findOrCreateRole(requestDTO.roleName());
        UserRole userRole = UserRole.builder()
                .id(new UserRoleId(savedUser.getId(), role.getId()))
                .user(savedUser)
                .role(role)
                .build();
        userRoleRepository.save(userRole);

        // After successful registration, generate JWT Token
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDTO.userId(), requestDTO.password()
                )
        );
        String token = jwtUtils.generateToken(authentication);

        // Prepare and return UserWithTokenResponseDTO
        return new UserWithTokenResponseDTO(
                savedUser.getUserId(),
                savedUser.getEmail(),
                token
        );
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO requestDTO) {
        // Authenticate user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDTO.userIdOrEmail(),
                        requestDTO.password()
                )
        );

        // Generate JWT token
        String token = jwtUtils.generateToken(authentication);

        return new LoginResponseDTO(token, "Bearer");
    }

    @Override
    public void logout(String token) {
        // Logout - JWT tokens are stateless, no action needed
    }
}