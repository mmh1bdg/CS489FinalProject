package com.radix.usermanagement.service.impl;

import com.radix.usermanagement.dto.response.ProfileFullDetailsDTO;
import com.radix.usermanagement.model.Profile;
import com.radix.usermanagement.repository.ProfileRepository;
import com.radix.usermanagement.service.ProfileDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileDetailsServiceImpl implements ProfileDetailsService {

    private final ProfileRepository profileRepository;

    @Override
    public Page<ProfileFullDetailsDTO> getPaginatedProfileDetails(int page, int size) {
        return profileRepository.findAll(PageRequest.of(page, size))
                .map(profile -> {
                    String cardNumber = null;
                    String expiryDate = null;
                    String provider = null;
                    String nameOnCard = null;

                    if (profile.getCreditCard() != null) {
                        cardNumber = profile.getCreditCard().getCardNumber();
                        expiryDate = profile.getCreditCard().getExpiryDate().toString();
                        provider = profile.getCreditCard().getProvider();
                        nameOnCard = profile.getCreditCard().getNameOnCard();
                    }

                    return ProfileFullDetailsDTO.builder()
                            .userId(profile.getUserId())
                            .firstName(profile.getFirstName())
                            .lastName(profile.getLastName())
                            .phone(profile.getPhone())
                            .photo(profile.getPhoto())
                            .road(profile.getAddress() != null ? profile.getAddress().getRoad() : null)
                            .city(profile.getAddress() != null ? profile.getAddress().getCity() : null)
                            .state(profile.getAddress() != null ? profile.getAddress().getState() : null)
                            .zipCode(profile.getAddress() != null ? profile.getAddress().getZipCode() : null)
                            .country(profile.getAddress() != null ? profile.getAddress().getCountry() : null)
                            .cardNumber(cardNumber)
                            .expiryDate(expiryDate)
                            .provider(provider)
                            .nameOnCard(nameOnCard)
                            .build();
                });
    }
}