package com.radix.usermanagement.service;

import com.radix.usermanagement.dto.request.CreditCardUpdateRequestDTO;
import com.radix.usermanagement.dto.response.CreditCardResponseDTO;

public interface CreditCardService {

    CreditCardResponseDTO updateCreditCard(String userId, CreditCardUpdateRequestDTO requestDTO);
}