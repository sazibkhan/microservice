package com.nagalay.profileservice.service;
import com.nagalay.profileservice.entity.ProfileEntity;
import com.nagalay.profileservice.entity.UserEntity;
import com.nagalay.profileservice.repository.ProfileRepository;
import com.nagalay.profileservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class EntityValidationService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    public ProfileEntity validateProfile(Long id) {
        Objects.requireNonNull(id);
        return profileRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException(
                        String.format("Profile  not found with id [%s]", id)));
    }

    public UserEntity validateUser(Long id) {
        Objects.requireNonNull(id);
        return userRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException(
                        String.format("User  not found with id [%s]", id)));
    }

}
