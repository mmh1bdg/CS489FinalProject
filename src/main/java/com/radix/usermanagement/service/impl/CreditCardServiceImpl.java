package com.radix.usermanagement.service.impl;

import com.radix.usermanagement.dto.request.CreditCardUpdateRequestDTO;
import com.radix.usermanagement.dto.response.CreditCardResponseDTO;
import com.radix.usermanagement.exception.ResourceNotFoundException;
import com.radix.usermanagement.mapper.CreditCardMapper;
import com.radix.usermanagement.model.CreditCard;
import com.radix.usermanagement.model.Profile;
import com.radix.usermanagement.repository.ProfileRepository;
import com.radix.usermanagement.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreditCardServiceImpl implements CreditCardService {

    private final ProfileRepository profileRepository;
    private final CreditCardMapper creditCardMapper;

    @Override
    public CreditCardResponseDTO updateCreditCard(String userId, CreditCardUpdateRequestDTO requestDTO) {
        // Find profile by userId
        Optional<Profile> optionalProfile = profileRepository.findByUserId(userId);
        if (optionalProfile.isEmpty()) {
            throw new ResourceNotFoundException("Profile not found for userId: " + userId);
        }

        Profile profile = optionalProfile.get();

        // Get associated credit card
        CreditCard creditCard = profile.getCreditCard();
        if (creditCard == null) {
            throw new ResourceNotFoundException("No credit card associated with user profile: " + userId);
        }

        // Update credit card fields
        creditCard.setExpiryDate(Date.valueOf(requestDTO.expiryDate())); //
        creditCard.setCvv(requestDTO.cvv());  //
        creditCard.setProvider(requestDTO.provider());  //
        creditCard.setNameOnCard(requestDTO.nameOnCard());  //

        // No need to set CardNumber again here, because it is a Primary Key
        // Card number should not change during update

        // Map updated CreditCard to CreditCardResponseDTO and return
        return creditCardMapper.toCreditCardResponseDTO(creditCard);
    }
}