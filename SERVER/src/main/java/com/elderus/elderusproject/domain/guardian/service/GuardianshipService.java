package com.elderus.elderusproject.domain.guardian.service;

import com.elderus.elderusproject.domain.guardian.domain.Guardian;
import com.elderus.elderusproject.domain.guardian.domain.GuardianRepository;
import com.elderus.elderusproject.domain.guardian.domain.Guardianship;
import com.elderus.elderusproject.domain.guardian.domain.GuardianshipRepository;
import com.elderus.elderusproject.domain.user.domain.User;
import com.elderus.elderusproject.domain.ward.domain.Ward;
import com.elderus.elderusproject.domain.ward.domain.WardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GuardianshipService {

    private final GuardianshipRepository guardianshipRepository;
    private final GuardianRepository guardianRepository;
    private final WardRepository wardRepository;

    public Guardianship createGuardianship(String guardian_email,String ward_email) {

        Guardian guardian=guardianRepository.findByEmail(guardian_email);
        Ward ward = wardRepository.findByEmail(ward_email);

        // 저장
        Guardianship guardianship = Guardianship.builder()
                .guardian(guardian)
                .ward(ward)
                .build();

        return guardianshipRepository.save(guardianship);
    }
}
