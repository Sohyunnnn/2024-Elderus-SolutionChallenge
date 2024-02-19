package com.elderus.elderusproject.domain.user.dto;

import com.elderus.elderusproject.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserJoinRequestDto {

    private String email;
    private String password;
    private String familyName;
    private String givenName;

    @Builder
    public UserJoinRequestDto(String email, String password, String givenName, String familyName){
        this.email=email;
        this.password=password;
        this.familyName=familyName;
        this.givenName=givenName;
    }

    public User toEntity(){
        return User.builder()
                .email(email)
                .password(password)
                .givenName(givenName)
                .familyName(familyName)
                .build();
    }
}
