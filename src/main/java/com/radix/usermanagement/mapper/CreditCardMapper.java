package com.radix.usermanagement.mapper;

import com.radix.usermanagement.dto.request.CreditCardUpdateRequestDTO;
import com.radix.usermanagement.dto.response.CreditCardResponseDTO;
import com.radix.usermanagement.model.CreditCard;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.sql.Date;

@Mapper(componentModel = "spring")
public interface CreditCardMapper {

    CreditCardMapper INSTANCE = Mappers.getMapper(CreditCardMapper.class);

    @Mapping(target = "expiryDate", source = "expiryDate", qualifiedByName = "stringToDate")
    CreditCard toCreditCard(CreditCardUpdateRequestDTO dto);

    CreditCardResponseDTO toCreditCardResponseDTO(CreditCard creditCard);

    @Named("stringToDate")
    static Date mapExpiryDate(String expiryDate) {
        return Date.valueOf(expiryDate);
    }
}