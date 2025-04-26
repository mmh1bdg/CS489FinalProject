package com.radix.usermanagement.service.impl;

import com.radix.usermanagement.dto.request.CreditCardUpdateRequestDTO;
import com.radix.usermanagement.dto.response.CreditCardResponseDTO;
import com.radix.usermanagement.model.CreditCard;
import com.radix.usermanagement.repository.CreditCardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreditCardServiceImplTest {

    @Mock
    private CreditCardRepository creditCardRepository;

    @InjectMocks
    private CreditCardServiceImpl creditCardService;

    private CreditCard creditCard;

    @BeforeEach
    void setUp() {
        creditCard = CreditCard.builder()
                .cardNumber("4111111111111111")
                .expiryDate(Date.valueOf("2027-12-01"))
                .cvv(123)
                .provider("VISA")
                .nameOnCard("Test User")
                .build();
    }

    @Test
    void testUpdateCreditCardSuccess() {
        // Arrange
        when(creditCardRepository.findByCardNumber("4111111111111111"))
                .thenReturn(Optional.of(creditCard));

        CreditCardUpdateRequestDTO requestDTO = CreditCardUpdateRequestDTO.builder()
                .expiryDate("2030-12-31")
                .cvv(456)
                .provider("MasterCard")
                .nameOnCard("Updated User")
                .build();

        // Act
        CreditCardResponseDTO responseDTO = creditCardService.updateCreditCard("4111111111111111", requestDTO);

        // Assert
        assertNotNull(responseDTO);
        assertEquals("4111111111111111", responseDTO.cardNumber());
        assertEquals("2030-12-31", responseDTO.expiryDate());
        assertEquals("MasterCard", responseDTO.provider());
        assertEquals("Updated User", responseDTO.nameOnCard());

        verify(creditCardRepository, times(1)).save(creditCard);
    }
}