package com.radix.usermanagement.service;

import com.radix.usermanagement.dto.request.UserProfileUpdateRequestDTO;
import com.radix.usermanagement.dto.response.ProfileResponseDTO;

public interface ProfileService {

    ProfileResponseDTO updateProfile(String userId, UserProfileUpdateRequestDTO requestDTO);

}