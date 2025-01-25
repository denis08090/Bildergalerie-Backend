package com.example.Bildergalerie.model.user;

import com.example.Bildergalerie.generic.ExtendedService;
import org.mapstruct.control.MappingControl;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService, ExtendedService<User> {

  User register(User user);
}
