package com.nagalay.authenticationservice.dto.request;

import lombok.Value;

@Value
public class AuthenticationRequest {

  private final String username;
  private final String password;


}
