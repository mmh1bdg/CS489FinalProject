package com.radix.usermanagement.dto.response;

import lombok.Builder;

@Builder
public record ProfileFullDetailsDTO(
        String userId,
        String firstName,
        String lastName,
        String phone,
        String photo,
        // Address
        String road,
        String city,
        String state,
        String zipCode,
        String country,
        // Credit Card
        String cardNumber,
        String expiryDate,
        String provider,
        String nameOnCard
) {}