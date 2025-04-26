package com.radix.usermanagement.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @NotBlank String userIdOrEmail,
        @NotBlank String password
) {}