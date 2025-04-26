package com.radix.usermanagement.controller;

import com.radix.usermanagement.dto.response.ProfileFullDetailsDTO;
import com.radix.usermanagement.dto.response.StandardResponse;
import com.radix.usermanagement.service.ProfileDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/profile-details")
@RequiredArgsConstructor
public class ProfileDetailsController {

    private final ProfileDetailsService profileDetailsService;

    @GetMapping
    public ResponseEntity<StandardResponse<Page<ProfileFullDetailsDTO>>> getPaginatedProfileDetails(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<ProfileFullDetailsDTO> profileDetailsPage = profileDetailsService.getPaginatedProfileDetails(page, size);

        StandardResponse<Page<ProfileFullDetailsDTO>> response = StandardResponse.<Page<ProfileFullDetailsDTO>>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .message("Profile full details retrieved successfully")
                .data(profileDetailsPage)
                .build();

        return ResponseEntity.ok(response);
    }
}