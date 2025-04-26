package com.radix.usermanagement.dto.response;

public record CreditCardResponseDTO(
        String cardNumber,
        String expiryDate,
        String provider,
        String nameOnCard
) {}