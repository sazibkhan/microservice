package com.nagalay.authenticationservice.service;

import com.nagalay.authenticationservice.entity.UserEntity;
import com.nagalay.authenticationservice.entity.UserRoleEntity;
import com.nagalay.authenticationservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AppUserDetailService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    UserEntity user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Username not found exception: " + username));

    if(user.getEnabled().equals('N')) {
      throw new IllegalArgumentException(String.format("User not active exception [%s]", username));
    }

    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

    for(UserRoleEntity role: user.getRoles()) {
      grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
    }
    return new org.springframework.security.core.userdetails.User(user.getUsername(),
        user.getPassword(),
        grantedAuthorities);
  }

}
