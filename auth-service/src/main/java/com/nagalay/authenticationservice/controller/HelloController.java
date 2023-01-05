package com.nagalay.authenticationservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("api/v1/hello")
public class HelloController {

  @GetMapping
  @PreAuthorize("hasAnyRole('USER')")
  public String hello() {
    return "Hello";
  }
}
