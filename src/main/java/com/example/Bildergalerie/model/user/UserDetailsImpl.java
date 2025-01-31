package com.example.Bildergalerie.model.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public record UserDetailsImpl(User user) implements UserDetails {

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (user.getRoles() == null) {
      throw new IllegalStateException("User has no roles assigned!");
    }
    return user.getRoles().stream()
            .flatMap(r -> {
              if (r.getAuthorities() == null) {
                throw new IllegalStateException("Role has no authorities!");
              }
              return r.getAuthorities().stream();
            })
            .map(a -> new SimpleGrantedAuthority(a.getName()))
            .collect(Collectors.toList());
  }

  @Override
  public String getPassword() {
    if (user.getPassword() == null) {
      throw new IllegalStateException("User password is null!");
    }
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    if (user.getEmail() == null) {
      throw new IllegalStateException("User email is null!");
    }
    return user.getEmail();
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
