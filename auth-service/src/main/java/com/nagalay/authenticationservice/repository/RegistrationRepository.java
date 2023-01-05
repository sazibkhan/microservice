package com.nagalay.authenticationservice.repository;

import com.nagalay.authenticationservice.entity.RegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<RegistrationEntity,Long> {
}
