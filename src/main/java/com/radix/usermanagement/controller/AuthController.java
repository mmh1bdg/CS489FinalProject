package com.radix.usermanagement.controller;

import com.radix.usermanagement.dto.request.LoginRequestDTO;
import com.radix.usermanagement.dto.request.UserRegistrationRequestDTO;
import com.radix.usermanagement.dto.response.LoginResponseDTO;
import com.radix.usermanagement.dto.response.StandardResponse;
import com.radix.usermanagement.dto.response.UserWithTokenResponseDTO;
import com.radix.usermanagement.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<StandardResponse<UserWithTokenResponseDTO>> register(
            @RequestBody @Valid UserRegistrationRequestDTO requestDTO) {
        UserWithTokenResponseDTO userWithToken = authService.register(requestDTO);
        StandardResponse<UserWithTokenResponseDTO> response = StandardResponse.<UserWithTokenResponseDTO>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CREATED.value())
                .message("User registered successfully")
                .data(userWithToken)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<StandardResponse<LoginResponseDTO>> login(
            @RequestBody @Valid LoginRequestDTO requestDTO) {
        LoginResponseDTO loginResponseDTO = authService.login(requestDTO);
        StandardResponse<LoginResponseDTO> response = StandardResponse.<LoginResponseDTO>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .message("Login successful")
                .data(loginResponseDTO)
                .build();
        return ResponseEntity.ok(response);
    }
}