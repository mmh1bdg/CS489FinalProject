package com.radix.usermanagement.service;

import com.radix.usermanagement.dto.request.PasswordResetRequestDTO;
import com.radix.usermanagement.dto.response.UserResponseDTO;

public interface UserService {

    UserResponseDTO resetPassword(String userId, PasswordResetRequestDTO requestDTO);
}