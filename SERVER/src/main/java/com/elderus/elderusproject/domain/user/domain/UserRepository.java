package com.elderus.elderusproject.domain.user.domain;

import com.elderus.elderusproject.domain.guardian.domain.Guardian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    //Optional<User> findByEmail(String email);
    User findByEmail(String email);
//    Boolean existsByUsername(String username);

    
}
