package com.nagalay.authenticationservice.dto.request;

import lombok.Data;

@Data
public class RegistrationDTO {

    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    private String gender;
    private String phone;





}
