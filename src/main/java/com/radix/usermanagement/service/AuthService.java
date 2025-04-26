package com.radix.usermanagement.service;

import com.radix.usermanagement.dto.request.LoginRequestDTO;
import com.radix.usermanagement.dto.request.UserRegistrationRequestDTO;
import com.radix.usermanagement.dto.response.LoginResponseDTO;
import com.radix.usermanagement.dto.response.UserWithTokenResponseDTO;

public interface AuthService {

    UserWithTokenResponseDTO register(UserRegistrationRequestDTO requestDTO);

    LoginResponseDTO login(LoginRequestDTO requestDTO);

    void logout(String token);
}