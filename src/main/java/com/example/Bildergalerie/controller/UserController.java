package com.example.Bildergalerie.controller;

import com.example.Bildergalerie.model.User.User;
import com.example.Bildergalerie.model.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * REST controller for managing users in the application.
 *
 * This controller provides endpoints for adding, retrieving, updating,
 * and deleting users.
 *
 * @version 1.0
 * @since 2024-07-26
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Adds a new user.
     *
     * @param user The user object to be added
     * @return The register user
     */
    @PostMapping("/register")
    public User addUser(@Valid @RequestBody User user) {

        User newUser = userRepository.save(user);
        return userRepository.save(user);
    }



    /**
     * Retrieves a list of all users.
     *
     * @return A list of users
     */
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param userId The ID of the user to retrieve
     * @return The retrieved user
     */
    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    /**
     * Updates an existing user.
     *
     * @param userId The ID of the user to update
     * @param user The updated user object
     * @return The updated user
     */
    @PutMapping("/updateUser/{userId}")
    public User updateUser(
            @PathVariable Long userId,
            @Valid @RequestBody User user) {

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setUser_name(user.getUser_name());
        existingUser.setFirst_name(user.getFirst_name());
        existingUser.setLast_name(user.getLast_name());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());

        return userRepository.save(existingUser);
    }

    /**
     * Deletes a user.
     *
     * @param userId The ID of the user to delete
     */
    @DeleteMapping("/deleteUser/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userRepository.deleteById(userId);
    }
}
