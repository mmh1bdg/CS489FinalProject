package com.radix.usermanagement.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AddressUpdateRequestDTO(
        @NotBlank String road,
        @NotBlank String city,
        @NotBlank String state,
        @NotBlank String zipCode,
        @NotBlank String country
) {}