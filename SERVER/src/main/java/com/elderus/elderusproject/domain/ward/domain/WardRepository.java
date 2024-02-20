package com.elderus.elderusproject.domain.ward.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WardRepository extends JpaRepository<Ward,Long> {
    Ward findByEmail(String email);
}
