package com.nagalay.authenticationservice.service;

import com.nagalay.authenticationservice.entity.RegistrationEntity;
import com.nagalay.authenticationservice.repository.RegistrationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class EntityValidationService {

    private final RegistrationRepository registrationRepository ;


    public RegistrationEntity validateRegistration(Long id) {
        Objects.requireNonNull(id);
        return registrationRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException(String
                        .format("Registration not found with id [%s]", id)));
    }


}
