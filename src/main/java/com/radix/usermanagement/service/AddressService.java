package com.radix.usermanagement.service;

import com.radix.usermanagement.dto.request.AddressUpdateRequestDTO;
import com.radix.usermanagement.dto.response.AddressResponseDTO;

public interface AddressService {

    AddressResponseDTO updateAddress(String userId, AddressUpdateRequestDTO requestDTO);
}