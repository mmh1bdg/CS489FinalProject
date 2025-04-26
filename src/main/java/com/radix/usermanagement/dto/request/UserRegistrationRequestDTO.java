package com.radix.usermanagement.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRegistrationRequestDTO(
        @NotBlank String userId,
        @NotBlank @Size(min = 6) String password,
        @NotBlank String roleName,

        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String phone,
        String photo,

        @NotBlank String road,
        @NotBlank String city,
        @NotBlank String state,
        @NotBlank String zipCode,
        @NotBlank String country,

        @NotBlank String cardNumber,
        @NotNull Integer cvv,
        @NotNull String expiryDate,
        @NotBlank String provider,
        @NotBlank String nameOnCard,

        @NotBlank @Email String email
) {}