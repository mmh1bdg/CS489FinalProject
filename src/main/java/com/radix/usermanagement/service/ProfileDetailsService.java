package com.radix.usermanagement.service;

import com.radix.usermanagement.dto.response.ProfileFullDetailsDTO;
import org.springframework.data.domain.Page;

public interface ProfileDetailsService {
    Page<ProfileFullDetailsDTO> getPaginatedProfileDetails(int page, int size);
}