package com.radix.usermanagement.dto.response;

public record LoginResponseDTO(
        String token,
        String tokenType
) {}