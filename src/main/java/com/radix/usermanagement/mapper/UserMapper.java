package com.radix.usermanagement.mapper;

import com.radix.usermanagement.dto.request.UserRegistrationRequestDTO;
import com.radix.usermanagement.dto.response.UserResponseDTO;
import com.radix.usermanagement.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    User toUser(UserRegistrationRequestDTO dto);

    UserResponseDTO toUserResponseDTO(User user);
}