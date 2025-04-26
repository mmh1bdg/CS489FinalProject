package com.radix.usermanagement.mapper;

import com.radix.usermanagement.dto.request.AddressUpdateRequestDTO;
import com.radix.usermanagement.dto.response.AddressResponseDTO;
import com.radix.usermanagement.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    @Mapping(target = "id", ignore = true)
    Address toAddress(AddressUpdateRequestDTO dto);

    AddressResponseDTO toAddressResponseDTO(Address address);
}