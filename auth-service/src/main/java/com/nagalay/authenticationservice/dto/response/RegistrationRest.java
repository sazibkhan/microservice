package com.nagalay.authenticationservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRest {

    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    private String gender;
    private String phone;
}
