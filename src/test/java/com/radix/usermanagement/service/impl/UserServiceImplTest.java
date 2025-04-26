package com.radix.usermanagement.service.impl;

import com.radix.usermanagement.dto.request.PasswordResetRequestDTO;
import com.radix.usermanagement.dto.response.UserResponseDTO;
import com.radix.usermanagement.model.User;
import com.radix.usermanagement.repository.UserRepository;
import com.radix.usermanagement.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUserId("testuser");
        user.setEmail("test@example.com");
        user.setPassword("oldpassword");
        user.setActive(true);
    }

    @Test
    void testResetPasswordSuccess() {
        // Arrange
        when(userRepository.findByUserId("testuser")).thenReturn(Optional.of(user));
        when(passwordEncoder.encode("newpassword")).thenReturn("encodedNewPassword");

        PasswordResetRequestDTO requestDTO = PasswordResetRequestDTO.builder()
                .newPassword("newpassword")
                .build();

        // Act
        UserResponseDTO responseDTO = userService.resetPassword("testuser", requestDTO);

        // Assert
        assertNotNull(responseDTO);
        assertEquals("testuser", responseDTO.userId());
        assertEquals("test@example.com", responseDTO.email());
        assertTrue(responseDTO.active());

        verify(userRepository, times(1)).save(user);
        assertEquals("encodedNewPassword", user.getPassword());
    }
}