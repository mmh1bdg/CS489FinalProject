package com.radix.usermanagement.service.impl;

import com.radix.usermanagement.dto.request.AddressUpdateRequestDTO;
import com.radix.usermanagement.dto.response.AddressResponseDTO;
import com.radix.usermanagement.model.Address;
import com.radix.usermanagement.model.Profile;
import com.radix.usermanagement.repository.AddressRepository;
import com.radix.usermanagement.repository.ProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceImplTest {

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressServiceImpl addressService;

    private Profile profile;
    private Address address;

    @BeforeEach
    void setUp() {
        address = new Address();
        address.setId(1);

        profile = new Profile();
        profile.setUserId("testuser");
        profile.setAddress(address);
    }

    @Test
    void testUpdateAddressSuccess() {
        // Arrange
        when(profileRepository.findByUserId("testuser")).thenReturn(Optional.of(profile));

        AddressUpdateRequestDTO requestDTO = AddressUpdateRequestDTO.builder()
                .road("123 Main Street")
                .city("New York")
                .state("NY")
                .zipCode("10001")
                .country("USA")
                .build();

        // Act
        AddressResponseDTO responseDTO = addressService.updateAddress("testuser", requestDTO);

        // Assert
        assertNotNull(responseDTO);
        assertEquals("123 Main Street", responseDTO.road());
        assertEquals("New York", responseDTO.city());
        assertEquals("NY", responseDTO.state());
        assertEquals("10001", responseDTO.zipCode());
        assertEquals("USA", responseDTO.country());
    }
}