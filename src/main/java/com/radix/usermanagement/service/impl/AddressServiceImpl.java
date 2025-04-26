package com.radix.usermanagement.service.impl;

import com.radix.usermanagement.dto.request.AddressUpdateRequestDTO;
import com.radix.usermanagement.dto.response.AddressResponseDTO;
import com.radix.usermanagement.mapper.AddressMapper;
import com.radix.usermanagement.model.Address;
import com.radix.usermanagement.model.Profile;
import com.radix.usermanagement.repository.AddressRepository;
import com.radix.usermanagement.repository.ProfileRepository;
import com.radix.usermanagement.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final ProfileRepository profileRepository;
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Override
    @Transactional
    public AddressResponseDTO updateAddress(String userId, AddressUpdateRequestDTO requestDTO) {
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found for userId: " + userId));

        Address address = profile.getAddress();

        if (address == null) {
            address = new Address();
        }

        // Update address fields
        address.setRoad(requestDTO.road());
        address.setCity(requestDTO.city());
        address.setState(requestDTO.state());
        address.setZipCode(requestDTO.zipCode());
        address.setCountry(requestDTO.country());

        Address updatedAddress = addressRepository.save(address);

        // Attach updated address to profile if not already
        if (profile.getAddress() == null) {
            profile.setAddress(updatedAddress);
            profileRepository.save(profile);
        }

        return addressMapper.toAddressResponseDTO(updatedAddress);
    }
}