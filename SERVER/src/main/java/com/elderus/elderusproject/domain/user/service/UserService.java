package com.elderus.elderusproject.domain.user.service;


import com.elderus.elderusproject.domain.guardian.domain.Guardian;
import com.elderus.elderusproject.domain.guardian.domain.GuardianRepository;
import com.elderus.elderusproject.domain.user.domain.User;
import com.elderus.elderusproject.domain.user.domain.UserRepository;
import com.elderus.elderusproject.domain.user.dto.UserResponseDto;
import com.elderus.elderusproject.domain.ward.domain.Ward;
import com.elderus.elderusproject.domain.ward.domain.WardRepository;
import com.elderus.elderusproject.global.exception.AppException;
import com.elderus.elderusproject.global.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final GuardianRepository guardianRepository;
    private final WardRepository wardRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public UserResponseDto join(String email, String password, String givenName,String familyName) {

        // email 중복 체크
        User existUser=userRepository.findByEmail(email);
        if (existUser!=null){
            throw new AppException(ErrorCode.USER_EMAIL_DUPLICATION);
        }

        // 저장
        User user = User.builder()
                .email(email)
                .password(encoder.encode(password))
                .givenName(givenName)
                .familyName(familyName)
                .build();

        userRepository.save(user);

        return UserResponseDto.builder()
                .email(email)
                .givenName(givenName)
                .familyName(familyName)
                .build();
    }


    @Transactional
    public UserResponseDto login(String email, String password){

        // 회원 존재 여부 확인
        Guardian guardian= guardianRepository.findByEmail(email);
        Ward ward = wardRepository.findByEmail(email);

        UserResponseDto userResponseDto = null;

        if (guardian!=null){

            // guardian 비밀번호 일치 여부 확인
            if (!guardian.matchPassword(encoder, password)) {
                throw new AppException(ErrorCode.NOT_EQUAL_INPUT_PASSWORD);
            }

            userResponseDto=UserResponseDto.builder().role("guardian").
                    email(guardian.getEmail())
                    .givenName(guardian.getGivenName())
                    .familyName(guardian.getFamilyName()).build();
        }

        if (ward!=null){

            // ward 비밀번호 일치 여부 확인
            if (!ward.matchPassword(encoder, password)) {
                throw new AppException(ErrorCode.NOT_EQUAL_INPUT_PASSWORD);
            }

            userResponseDto=UserResponseDto.builder().role("ward").
                    email(ward.getEmail())
                    .givenName(ward.getGivenName())
                    .familyName(ward.getFamilyName()).build();
        }

        // user not exist
        if(ward==null && guardian==null) {
            throw new AppException(ErrorCode.NOT_FOUND_USER);
        }

        return userResponseDto;

    }
}