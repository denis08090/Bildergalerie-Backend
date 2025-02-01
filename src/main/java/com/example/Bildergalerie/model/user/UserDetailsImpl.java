package com.example.Bildergalerie.model.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record UserDetailsImpl(User user) implements UserDetails {

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (user.getRoles() == null || user.getRoles().isEmpty()) {
      System.out.println("⚠️ WARNUNG: User hat keine Rollen! (" + user.getEmail() + ")");
      return List.of(new SimpleGrantedAuthority("ROLE_USER")); // ✅ Fallback, falls keine Rolle zugewiesen ist
    }

    return user.getRoles().stream()
            .flatMap(r -> {
              if (r.getAuthorities() == null || r.getAuthorities().isEmpty()) {
                System.out.println("⚠️ WARNUNG: Rolle " + r.getName() + " hat keine Authorities!");
                return Stream.of(new SimpleGrantedAuthority(r.getName())); // ✅ Direkt die Rolle als Authority setzen
              }
              return r.getAuthorities().stream()
                      .map(a -> new SimpleGrantedAuthority(a.getName()));
            })
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
