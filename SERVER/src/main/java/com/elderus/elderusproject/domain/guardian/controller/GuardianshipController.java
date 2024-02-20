package com.elderus.elderusproject.domain.guardian.controller;

import com.elderus.elderusproject.domain.guardian.domain.Guardian;
import com.elderus.elderusproject.domain.guardian.domain.Guardianship;
import com.elderus.elderusproject.domain.guardian.dto.GuardianshipDto;
import com.elderus.elderusproject.domain.guardian.service.GuardianService;
import com.elderus.elderusproject.domain.guardian.service.GuardianshipService;
import com.elderus.elderusproject.domain.ward.domain.Ward;
import com.elderus.elderusproject.domain.ward.service.WardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/guardianship")
public class GuardianshipController {

    private final GuardianService guardianService;
    private final GuardianshipService guardianshipService;
    private final WardService wardService;

    @PostMapping("/create")
    public Guardianship createGuardianship(@RequestBody GuardianshipDto dto) {
//        Guardian guardian = guardianService.getGuardianById(guardianId);
//        Ward ward = wardService.getWardById(wardId);

        String guardianEmail= dto.getGuardian_email();
        String wardEmail=dto.getWard_email();

        return guardianshipService.createGuardianship(guardianEmail, wardEmail);
    }
}
