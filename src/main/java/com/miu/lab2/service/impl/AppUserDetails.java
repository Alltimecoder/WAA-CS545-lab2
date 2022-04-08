package com.miu.lab2.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miu.lab2.domain.Role;
import com.miu.lab2.domain.User;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AppUserDetails implements UserDetails {

  private String email;

  @JsonIgnore
  private String password;

  private List<Role> roles;

  public AppUserDetails(User user) {
    this.email = user.getEmail();
    this.password = user.getPassword();
    this.roles = user.getRoles();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles.stream()
        .map(role -> new SimpleGrantedAuthority(role.getRole()))
        .collect(Collectors.toList());
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
