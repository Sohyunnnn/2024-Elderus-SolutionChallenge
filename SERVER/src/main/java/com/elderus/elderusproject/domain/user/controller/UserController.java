package com.elderus.elderusproject.domain.user.controller;

import com.elderus.elderusproject.domain.guardian.domain.GuardianRepository;
import com.elderus.elderusproject.domain.user.domain.User;
import com.elderus.elderusproject.domain.user.domain.UserRepository;
import com.elderus.elderusproject.domain.user.dto.LoginDto;
import com.elderus.elderusproject.domain.user.dto.UserJoinRequestDto;
import com.elderus.elderusproject.domain.user.dto.UserResponseDto;
import com.elderus.elderusproject.domain.user.service.UserService;
import com.elderus.elderusproject.global.auth.PrincipalDetails;
import com.elderus.elderusproject.global.exception.AppException;
import com.elderus.elderusproject.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserService userService;

    @GetMapping("/user-info")
    public @ResponseBody User user(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        System.out.println("principalDetails = " + principalDetails.getUser());
        //return "user";
        return principalDetails.getUser();
    }

    // 보안성 ㅠㅠ...
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        try{
            String email = loginDto.getEmail();
            String password = loginDto.getPassword();
            UserResponseDto userResponseDto=userService.login(email,password);
            return ResponseEntity.status(200).body(userResponseDto);
        }
//        JwtToken jwtToken = memberService.login(email, password);
        catch (AppException ex){
            ErrorCode errorCode=ex.getErrorCode();
            return ResponseEntity.status(errorCode.getStatus()).body(errorCode.getMessage());
        }
    }

    @GetMapping("/test/oauth/login")
    public @ResponseBody String testOAuthLogin(Authentication authentication,
                                               @AuthenticationPrincipal OAuth2User oauth) {
        System.out.println("/test/oauth/login ==========================");
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        System.out.println("authentication = " + oAuth2User.getAttributes());

        System.out.println("oAuth2User = " + oauth.getAttributes());

        return "OAuth 세션 정보 확인";
    }

}
