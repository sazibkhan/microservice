package com.nagalay.authenticationservice.dto.response;

import lombok.Value;


@Value
public class AuthenticationResponse {

  private final String token;
  private final String authority;


//  @CreationTimestamp
//  @Column(updatable = false)
//  private Timestamp timeStamp;
//
//  @Column(updatable = false)
//  @Basic(optional = false)
//  private LocalDateTime expireAt;
//
//  @ManyToOne
//  @JoinColumn(name = “customer_id”, referencedColumnName ="id")
//  private UserEntity user;
//
//  @Transient
//  private boolean isExpired;
//
//  public boolean isExpired() {
//    return getExpireAt().isBefore(LocalDateTime.now()); // this is generic implementation, you can always make it timezone specific
//  }

}
