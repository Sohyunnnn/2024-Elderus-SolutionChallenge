package com.elderus.elderusproject.domain.user.dto;

import com.elderus.elderusproject.domain.user.domain.User;
import com.elderus.elderusproject.domain.ward.domain.Ward;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    private String email;
    private String password;

    public User toEntity(){
        return User.builder()
                .email(email)
                .password(password)
                .build();
    }

}