package com.radix.usermanagement.dto.response;

public record AddressResponseDTO(
        String road,
        String city,
        String state,
        String zipCode,
        String country
) {}