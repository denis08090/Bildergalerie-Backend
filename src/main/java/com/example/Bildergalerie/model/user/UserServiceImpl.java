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
  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository repository, Logger logger,
                         BCryptPasswordEncoder bCryptPasswordEncoder,
                         RoleService roleService) {
    super(repository, logger);
    this.userRepository = repository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.roleService = roleService;
  }


  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return userRepository.findByEmail(email)
            .map(UserDetailsImpl::new)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
  }


  @Override
  public User register(User user) {

    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    // Fetch the 'CLIENT' role from  and assign it to the user
    Role clientRole = roleService.findByName("CLIENT");
    // Fetch the silver rank from Rank and assign it to the user


    user.setRoles(Collections.singleton(clientRole)); // Assign the 'CLIENT' role to the user

    // In UserServiceImpl during user registration

    // Save the user with the assigned role
    return save(user);
  }

  public User findByEmail(String email) {
    return userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
  }

}
