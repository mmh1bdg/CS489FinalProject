package com.radix.usermanagement.service.impl;

import com.radix.usermanagement.dto.request.UserProfileUpdateRequestDTO;
import com.radix.usermanagement.dto.response.ProfileResponseDTO;
import com.radix.usermanagement.model.Address;
import com.radix.usermanagement.model.CreditCard;
import com.radix.usermanagement.model.Profile;
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
class ProfileServiceImplTest {

    @Mock
    private ProfileRepository profileRepository;

    @InjectMocks
    private ProfileServiceImpl profileService;

    private Profile profile;

    @BeforeEach
    void setUp() {
        Address address = Address.builder()
                .road("Old Road")
                .city("Old City")
                .state("Old State")
                .zipCode("00000")
                .country("Old Country")
                .build();

        CreditCard creditCard = CreditCard.builder()
                .cardNumber("4111111111111111")
                .expiryDate(java.sql.Date.valueOf("2027-12-01"))
                .cvv(123)
                .provider("VISA")
                .nameOnCard("Test User")
                .build();

        profile = Profile.builder()
                .userId("testuser")
                .firstName("Test")
                .lastName("User")
                .phone("1234567890")
                .photo("photo.jpg")
                .address(address)
                .creditCard(creditCard)
                .build();
    }

    @Test
    void testUpdateProfileSuccess() {
        // Arrange
        when(profileRepository.findByUserId("testuser")).thenReturn(Optional.of(profile));

        UserProfileUpdateRequestDTO requestDTO = UserProfileUpdateRequestDTO.builder()
                .firstName("UpdatedFirstName")
                .lastName("UpdatedLastName")
                .phone("9876543210")
                .photo("updatedphoto.jpg")
                .road("New Road")
                .city("New City")
                .state("New State")
                .zipCode("99999")
                .country("New Country")
                .build();

        // Act
        ProfileResponseDTO updatedProfile = profileService.updateProfile("testuser", requestDTO);

        // Assert
        assertNotNull(updatedProfile);
        assertEquals("UpdatedFirstName", updatedProfile.getFirstName());
        assertEquals("UpdatedLastName", updatedProfile.getLastName());
        assertEquals("9876543210", updatedProfile.getPhone());
        assertEquals("updatedphoto.jpg", updatedProfile.getPhoto());
        assertEquals("New Road", updatedProfile.getRoad());
        assertEquals("New City", updatedProfile.getCity());
        assertEquals("New State", updatedProfile.getState());
        assertEquals("99999", updatedProfile.getZipCode());
        assertEquals("New Country", updatedProfile.getCountry());

        verify(profileRepository, times(1)).save(profile);
    }
}