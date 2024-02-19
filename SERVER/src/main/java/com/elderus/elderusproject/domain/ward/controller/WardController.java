package com.elderus.elderusproject.domain.ward.controller;


import com.elderus.elderusproject.domain.guardian.dto.GuardianJoinDto;
import com.elderus.elderusproject.domain.guardian.dto.GuardianResponseDto;
import com.elderus.elderusproject.domain.ward.dto.WardJoinDto;
import com.elderus.elderusproject.domain.ward.dto.WardResponseDto;
import com.elderus.elderusproject.domain.ward.service.WardService;
import com.elderus.elderusproject.global.exception.AppException;
import com.elderus.elderusproject.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/ward")
public class WardController {


    private final WardService wardService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody WardJoinDto dto) throws Exception{
        try{
            WardResponseDto result= wardService.join(dto.getEmail(),dto.getPassword(), dto.getFamilyName(),dto.getGivenName(),dto.getPhone(),dto.getAddress(),dto.getBirth());
            return ResponseEntity.status(200).body(result);
        }
        catch (AppException ex){
            ErrorCode errorCode = ex.getErrorCode();
            return ResponseEntity.status(errorCode.getStatus()).body(ex.getMessage());
        }
    }

}
