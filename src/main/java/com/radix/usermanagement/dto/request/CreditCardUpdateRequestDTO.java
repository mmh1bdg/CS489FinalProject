package com.radix.usermanagement.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreditCardUpdateRequestDTO(
        @NotBlank String expiryDate,
        @NotNull Integer cvv,
        @NotBlank String provider,
        @NotBlank String nameOnCard
) {}