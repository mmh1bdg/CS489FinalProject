package com.radix.usermanagement.mapper;

import com.radix.usermanagement.dto.request.UserProfileUpdateRequestDTO;
import com.radix.usermanagement.dto.response.ProfileResponseDTO;
import com.radix.usermanagement.model.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    Profile toProfile(UserProfileUpdateRequestDTO dto);

    ProfileResponseDTO toProfileResponseDTO(Profile profile);
}