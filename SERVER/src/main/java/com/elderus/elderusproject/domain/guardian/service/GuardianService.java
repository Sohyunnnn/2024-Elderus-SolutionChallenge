package com.elderus.elderusproject.domain.guardian.service;

import com.elderus.elderusproject.domain.guardian.domain.Guardian;
import com.elderus.elderusproject.domain.guardian.domain.GuardianRepository;
import com.elderus.elderusproject.domain.guardian.dto.GuardianResponseDto;
import com.elderus.elderusproject.domain.user.domain.User;
import com.elderus.elderusproject.domain.user.dto.UserResponseDto;
import com.elderus.elderusproject.global.exception.AppException;
import com.elderus.elderusproject.global.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class GuardianService {

    private final GuardianRepository guardianRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public GuardianResponseDto join(String email, String password, String givenName, String familyName, String phone, String address, Date birth) {

        // email 중복 체크
        Guardian existGuardian=guardianRepository.findByEmail(email);
        if (existGuardian!=null){
            throw new AppException(ErrorCode.USER_EMAIL_DUPLICATION);
        }

        // 저장
        Guardian guardian = Guardian.builder()
                .email(email)
                .password(encoder.encode(password))
                .givenName(givenName)
                .familyName(familyName)
                .phone(phone)
                .address(address)
                .birth(birth)
                .build();

        guardianRepository.save(guardian);

        return GuardianResponseDto.builder()
                .email(email)
                .givenName(givenName)
                .familyName(familyName)
                .phone(phone)
                .birth(birth)
                .address(address)
                .build();
    }
}
