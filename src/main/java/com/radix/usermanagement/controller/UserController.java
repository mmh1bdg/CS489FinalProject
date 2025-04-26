package com.radix.usermanagement.controller;

import com.radix.usermanagement.dto.request.AddressUpdateRequestDTO;
import com.radix.usermanagement.dto.request.CreditCardUpdateRequestDTO;
import com.radix.usermanagement.dto.request.PasswordResetRequestDTO;
import com.radix.usermanagement.dto.request.UserProfileUpdateRequestDTO;
import com.radix.usermanagement.dto.response.AddressResponseDTO;
import com.radix.usermanagement.dto.response.CreditCardResponseDTO;
import com.radix.usermanagement.dto.response.ProfileResponseDTO;
import com.radix.usermanagement.dto.response.StandardResponse;
import com.radix.usermanagement.dto.response.UserResponseDTO;
import com.radix.usermanagement.service.AddressService;
import com.radix.usermanagement.service.CreditCardService;
import com.radix.usermanagement.service.ProfileService;
import com.radix.usermanagement.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ProfileService profileService;
    private final AddressService addressService;
    private final CreditCardService creditCardService;

    @PutMapping("/{userId}/reset-password")
    public ResponseEntity<StandardResponse<UserResponseDTO>> resetPassword(
            @PathVariable String userId,
            @RequestBody @Valid PasswordResetRequestDTO requestDTO) {

        UserResponseDTO updatedUser = userService.resetPassword(userId, requestDTO);

        StandardResponse<UserResponseDTO> response = StandardResponse.<UserResponseDTO>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .message("Password reset successfully")
                .data(updatedUser)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{userId}/profile")
    public ResponseEntity<StandardResponse<ProfileResponseDTO>> updateProfile(
            @PathVariable String userId,
            @RequestBody @Valid UserProfileUpdateRequestDTO requestDTO) {

        ProfileResponseDTO updatedProfile = profileService.updateProfile(userId, requestDTO);

        StandardResponse<ProfileResponseDTO> response = StandardResponse.<ProfileResponseDTO>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .message("Profile updated successfully")
                .data(updatedProfile)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{userId}/address")
    public ResponseEntity<StandardResponse<AddressResponseDTO>> updateAddress(
            @PathVariable String userId,
            @RequestBody @Valid AddressUpdateRequestDTO requestDTO) {

        AddressResponseDTO updatedAddress = addressService.updateAddress(userId, requestDTO);

        StandardResponse<AddressResponseDTO> response = StandardResponse.<AddressResponseDTO>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .message("Address updated successfully")
                .data(updatedAddress)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{userId}/creditcard")
    public ResponseEntity<StandardResponse<CreditCardResponseDTO>> updateCreditCard(
            @PathVariable String userId,
            @RequestBody @Valid CreditCardUpdateRequestDTO requestDTO) {

        CreditCardResponseDTO updatedCard = creditCardService.updateCreditCard(userId, requestDTO);

        StandardResponse<CreditCardResponseDTO> response = StandardResponse.<CreditCardResponseDTO>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .message("Credit card updated successfully")
                .data(updatedCard)
                .build();

        return ResponseEntity.ok(response);
    }
}