package com.elderus.elderusproject.domain.ward.dto;

import com.elderus.elderusproject.domain.guardian.domain.Guardian;
import com.elderus.elderusproject.domain.ward.domain.Ward;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WardResponseDto {

    private String email;
    private String familyName;
    private String givenName;
    private String phone;
    private String address;
    private Date birth;

    public Ward toEntity(){
        return Ward.builder()
                .email(email)
                .givenName(givenName)
                .familyName(familyName)
                .address(address)
                .phone(phone)
                .birth(birth)
                .build();
    }
}
