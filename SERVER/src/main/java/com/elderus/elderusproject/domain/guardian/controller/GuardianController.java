package com.elderus.elderusproject.domain.guardian.controller;

import com.elderus.elderusproject.domain.guardian.dto.GuardianJoinDto;
import com.elderus.elderusproject.domain.guardian.dto.GuardianResponseDto;
import com.elderus.elderusproject.domain.guardian.service.GuardianService;
import com.elderus.elderusproject.domain.user.dto.UserJoinRequestDto;
import com.elderus.elderusproject.domain.user.dto.UserResponseDto;
import com.elderus.elderusproject.global.exception.AppException;
import com.elderus.elderusproject.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/guardian")
public class GuardianController {

    private final GuardianService guardianService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody GuardianJoinDto dto) throws Exception{
        try{
            GuardianResponseDto result=guardianService.join(dto.getEmail(),dto.getPassword(), dto.getFamilyName(),dto.getGivenName(),dto.getPhone(),dto.getAddress(),dto.getBirth());
            return ResponseEntity.status(200).body(result);
        }
        catch (AppException ex){
            ErrorCode errorCode = ex.getErrorCode();
            System.out.println(errorCode.getMessage());
            return ResponseEntity.status(errorCode.getStatus()).body(errorCode.getMessage());
        }
    }




}
