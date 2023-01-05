package com.nagalay.authenticationservice.controller;

import com.nagalay.authenticationservice.config.JwtUtil;
import com.nagalay.authenticationservice.dto.request.AuthenticationRequest;
import com.nagalay.authenticationservice.dto.response.AuthenticationResponse;
import com.nagalay.authenticationservice.entity.UserEntity;
import com.nagalay.authenticationservice.repository.UserRepository;
import com.nagalay.authenticationservice.service.AppUserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@AllArgsConstructor
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final AppUserDetailService appUserDetailService;
  private final UserRepository userRepository;
  private final JwtUtil jwtUtil;


  //http://localhost:9788/authenticate
  @RequestMapping(value = "/authenticate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest req) {

    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
    );

    final UserDetails userDetails = appUserDetailService.loadUserByUsername(req.getUsername());
    final String jwt = jwtUtil.generateToken(userDetails);
    Optional<UserEntity> userOpt = userRepository.findByUsername(req.getUsername());
    return ResponseEntity.ok(new AuthenticationResponse(jwt, userOpt.get().getAuthority()));

  }
}
