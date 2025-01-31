package com.example.Bildergalerie.model.user;

import com.example.Bildergalerie.generic.ExtendedServiceImpl;
import com.example.Bildergalerie.model.role.Role;
import com.example.Bildergalerie.model.role.RoleService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserServiceImpl extends ExtendedServiceImpl<User> implements UserService {

  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final RoleService roleService;  // Inject RoleService


  @Autowired
  public UserServiceImpl(UserRepository repository, Logger logger,
                         BCryptPasswordEncoder bCryptPasswordEncoder,
                         RoleService roleService) {
    super(repository, logger);
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.roleService = roleService;

  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { //nimmt username
    return ((UserRepository) repository).findByEmail(email)
            .map(UserDetailsImpl::new)
            .orElseThrow(() -> new UsernameNotFoundException(email));
  }

  @Override
  public User register(User user) {
    if (user == null) {
      throw new IllegalStateException("User object is null before saving!");
    }

    // Falls das Passwort null oder leer ist
    if (user.getPassword() == null || user.getPassword().isEmpty()) {
      throw new IllegalArgumentException("Password must not be null or empty");
    }

    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

    // Fetch the 'CLIENT' role from RoleService and assign it to the user
    Role clientRole = roleService.findByName("CLIENT");

    if (clientRole == null) {
      throw new IllegalStateException("Role CLIENT not found!");
    }

    user.setRoles(Collections.singleton(clientRole)); // Assign the 'CLIENT' role to the user

    return save(user);
  }
}
