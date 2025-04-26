package com.radix.usermanagement.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UserProfileUpdateRequestDTO(
        @NotBlank(message = "First name is required")
        String firstName,

        @NotBlank(message = "Last name is required")
        String lastName,

        @NotBlank(message = "Phone number is required")
        String phone,

        String photo,

        String road,

        String city,

        String state,

        String zipCode,

        String country
) {
}