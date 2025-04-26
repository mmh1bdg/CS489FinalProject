package com.radix.usermanagement.dto.response;

public record UserResponseDTO(
        String userId,
        boolean active,
        String email
) {}