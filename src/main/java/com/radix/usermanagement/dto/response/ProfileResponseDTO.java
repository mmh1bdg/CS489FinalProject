package com.radix.usermanagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponseDTO {
    private String firstName;
    private String lastName;
    private String phone;
    private String photo;
    private String road;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}