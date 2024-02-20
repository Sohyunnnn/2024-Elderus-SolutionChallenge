package com.elderus.elderusproject.domain.user.dto;

import com.elderus.elderusproject.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private String role;
    private String email;
    private String givenName;
    private String familyName;

    @Builder
    public UserResponseDto(String role,String email,String givenName,String familyName){
        this.role=role;
        this.email=email;
        this.givenName=givenName;
        this.familyName=familyName;
    }
//    public User toEntity(){
//        return User.builder()
//                .email(email)
//                .givenName(givenName)
//                .familyName(familyName)
//                .build();
//    }
}
