package com.elderus.elderusproject.domain.ward.service;

import com.elderus.elderusproject.domain.guardian.domain.Guardian;
import com.elderus.elderusproject.domain.guardian.dto.GuardianResponseDto;
import com.elderus.elderusproject.domain.ward.domain.Ward;
import com.elderus.elderusproject.domain.ward.domain.WardRepository;
import com.elderus.elderusproject.domain.ward.dto.WardResponseDto;
import com.elderus.elderusproject.global.exception.AppException;
import com.elderus.elderusproject.global.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class WardService {

    private final WardRepository wardRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public WardResponseDto join(String email, String password, String givenName, String familyName, String phone, String address, Date birth) {

        // email 중복 체크
        Ward existWard= wardRepository.findByEmail(email);
        if (existWard!=null){
            throw new AppException(ErrorCode.USER_EMAIL_DUPLICATION);
        }

        // 저장
        Ward ward = Ward.builder()
                .email(email)
                .password(encoder.encode(password))
                .givenName(givenName)
                .familyName(familyName)
                .phone(phone)
                .address(address)
                .birth(birth)
                .build();

        wardRepository.save(ward);

        return WardResponseDto.builder()
                .email(email)
                .givenName(givenName)
                .familyName(familyName)
                .phone(phone)
                .birth(birth)
                .address(address)
                .build();
    }
}
