package com.nagalay.authenticationservice.config;


import com.nagalay.authenticationservice.entity.UserEntity;
import com.nagalay.authenticationservice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.Optional;

@Configuration
public class UserConfig {

  @Bean
  CommandLineRunner commandLineRunner(UserRepository userRepository,
                                      JdbcTemplate jdbcTemplate) {
    return args -> {

      // Creating new User if not exists by the name 'sazib'
      String username = "sazib";

      Optional<UserEntity> userOpt = userRepository.findByUsername(username);

      if(!userOpt.isPresent()) {
        String fullName = "Sazib";
        String password = new BCryptPasswordEncoder().encode("123456");
        String authority = "admin";
        String enabled = "Y";

        jdbcTemplate.update(
            "INSERT INTO auth_users (username, password,full_name, authority, enabled) VALUES ( ?, ?, ?, ?, ?)",
            username, password, fullName, authority, enabled
        );

        UserEntity user = userRepository.findByUsername(username)
            .orElseThrow(()-> new UsernameNotFoundException(String
                .format("User not found with name [%s]", username)));

        Arrays.asList("ROLE_USER", "ROLE_DEVELOPER", "ROLE_ADMIN").forEach(role-> {
          jdbcTemplate.update(
              "INSERT INTO auth_users_role (user_id, role_name) VALUES (?, ?)",
              user.getId(), role
          );
        });
      }
    };
  }
}
