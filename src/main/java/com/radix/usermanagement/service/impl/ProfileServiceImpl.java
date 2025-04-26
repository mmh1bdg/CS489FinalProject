package com.radix.usermanagement.service.impl;

import com.radix.usermanagement.dto.request.UserProfileUpdateRequestDTO;
import com.radix.usermanagement.dto.response.ProfileResponseDTO;
import com.radix.usermanagement.model.Address;
import com.radix.usermanagement.model.Profile;
import com.radix.usermanagement.repository.ProfileRepository;
import com.radix.usermanagement.service.ProfileService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Override
    public ProfileResponseDTO updateProfile(String userId, UserProfileUpdateRequestDTO requestDTO) {
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found for userId: " + userId));

        // Update profile fields
        profile.setFirstName(requestDTO.firstName());
        profile.setLastName(requestDTO.lastName());
        profile.setPhone(requestDTO.phone());
        profile.setPhoto(requestDTO.photo());

        // Update address if present
        if (profile.getAddress() != null) {
            Address address = profile.getAddress();
            address.setRoad(requestDTO.road() != null ? requestDTO.road() : address.getRoad());
            address.setCity(requestDTO.city() != null ? requestDTO.city() : address.getCity());
            address.setState(requestDTO.state() != null ? requestDTO.state() : address.getState());
            address.setZipCode(requestDTO.zipCode() != null ? requestDTO.zipCode() : address.getZipCode());
            address.setCountry(requestDTO.country() != null ? requestDTO.country() : address.getCountry());
        }

        Profile updatedProfile = profileRepository.save(profile);

        return ProfileResponseDTO.builder()
                .firstName(updatedProfile.getFirstName())
                .lastName(updatedProfile.getLastName())
                .phone(updatedProfile.getPhone())
                .photo(updatedProfile.getPhoto())
                .road(updatedProfile.getAddress() != null ? updatedProfile.getAddress().getRoad() : null)
                .city(updatedProfile.getAddress() != null ? updatedProfile.getAddress().getCity() : null)
                .state(updatedProfile.getAddress() != null ? updatedProfile.getAddress().getState() : null)
                .zipCode(updatedProfile.getAddress() != null ? updatedProfile.getAddress().getZipCode() : null)
                .country(updatedProfile.getAddress() != null ? updatedProfile.getAddress().getCountry() : null)
                .build();
    }
}