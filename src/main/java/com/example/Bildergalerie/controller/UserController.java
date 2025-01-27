package com.example.Bildergalerie.controller;

import com.example.Bildergalerie.model.user.User;
import com.example.Bildergalerie.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

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
     * @return The registered user
     */
    @PostMapping("/register")
    public User addUser(@Valid @RequestBody User user) {
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
    public User getUserById(@PathVariable UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
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
            @PathVariable UUID userId,
            @Valid @RequestBody User user) {

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        existingUser.setUserName(user.getUserName());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
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
    public void deleteUser(@PathVariable UUID userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found with ID: " + userId);
        }
        userRepository.deleteById(userId);
    }
}
