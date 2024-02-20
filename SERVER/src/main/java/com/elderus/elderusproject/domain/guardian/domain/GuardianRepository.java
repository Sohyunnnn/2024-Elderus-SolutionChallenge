package com.elderus.elderusproject.domain.guardian.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GuardianRepository extends JpaRepository<Guardian,Long> {
    Guardian findByEmail(String email);
//    Boolean existsByUsername(String username);

    
}
