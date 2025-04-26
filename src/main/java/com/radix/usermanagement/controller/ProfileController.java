package com.radix.usermanagement.controller;

import com.radix.usermanagement.dto.request.UserProfileUpdateRequestDTO; // 🛑 THIS IMPORT WAS MISSING
import com.radix.usermanagement.dto.response.ProfileResponseDTO;
import com.radix.usermanagement.dto.response.StandardResponse;
import com.radix.usermanagement.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PutMapping("/{userId}")
    public ResponseEntity<StandardResponse<ProfileResponseDTO>> updateProfile(
            @PathVariable String userId,
            @RequestBody @Valid UserProfileUpdateRequestDTO requestDTO
    ) {
        ProfileResponseDTO updatedProfile = profileService.updateProfile(userId, requestDTO);

        StandardResponse<ProfileResponseDTO> response = StandardResponse.<ProfileResponseDTO>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .message("Profile updated successfully")
                .data(updatedProfile)
                .build();

        return ResponseEntity.ok(response);
    }
}