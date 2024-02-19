package com.elderus.elderusproject.domain.guardian.dto;

import com.elderus.elderusproject.domain.guardian.domain.Guardian;
import com.elderus.elderusproject.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GuardianJoinDto {

    private String email;
    private String password;
    private String familyName;
    private String givenName;
    private String phone;
    private String address;
    private Date birth;

    public Guardian toEntity(){
        return Guardian.builder()
                .email(email)
                .password(password)
                .givenName(givenName)
                .familyName(familyName)
                .address(address)
                .phone(phone)
                .birth(birth)
                .build();
    }
}
