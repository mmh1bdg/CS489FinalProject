package com.radix.usermanagement.service.impl;

import com.radix.usermanagement.dto.request.PasswordResetRequestDTO;
import com.radix.usermanagement.dto.response.UserResponseDTO;
import com.radix.usermanagement.exception.ResourceNotFoundException;
import com.radix.usermanagement.mapper.UserMapper;
import com.radix.usermanagement.model.User;
import com.radix.usermanagement.repository.UserRepository;
import com.radix.usermanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserResponseDTO resetPassword(String userId, PasswordResetRequestDTO requestDTO) {
        // Find user by userId
        Optional<User> optionalUser = userRepository.findByUserId(userId);
        if (optionalUser.isEmpty()) {
            throw new ResourceNotFoundException("User not found with userId: " + userId);
        }

        // Update password
        User user = optionalUser.get();
        user.setPassword(passwordEncoder.encode(requestDTO.newPassword())); // RECORD style

        User updatedUser = userRepository.save(user);

        // Map updated User to UserResponseDTO and return
        return userMapper.toUserResponseDTO(updatedUser);
    }
}