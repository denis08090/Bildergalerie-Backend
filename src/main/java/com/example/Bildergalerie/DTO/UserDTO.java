package com.example.Bildergalerie.DTO;

import com.example.Bildergalerie.generic.ExtendedDTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) for User entity.
 *
 * Provides a simplified representation of the User entity for data transfer between
 * different layers of the application.
 *
 * @version 1.0
 * @since 2024-07-26
 */
public class UserDTO extends ExtendedDTO {

    @NotEmpty(message = "Username must not be empty")
    @Size(min = 1, max = 50, message = "Username must be between 1 and 50 characters")
    private String userName;

    @NotEmpty(message = "Firstname must not be empty")
    @Size(min = 1, max = 50, message = "Firstname must be between 1 and 50 characters")
    private String firstName;

    @NotEmpty(message = "Lastname must not be empty")
    @Size(min = 1, max = 50, message = "Lastname must be between 1 and 50 characters")
    private String lastName;

    @NotEmpty(message = "E-Mail must not be empty")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotEmpty(message = "Password must not be empty")
    @Size(min = 1, max = 50, message = "Password must be between 1 and 50 characters")
    private String password;

    /**
     * Default constructor.
     */
    public UserDTO() {
    }

    /**
     * Constructor with parameters.
     *
     * @param id        The UUID of the user
     * @param userName  The username of the user
     * @param firstName The first name of the user
     * @param lastName  The last name of the user
     * @param email     The email address of the user
     * @param password  The password of the user
     */
    public UserDTO(UUID id, String userName, String firstName, String lastName, String email, String password) {
        super(id);
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters with fluent API for chaining

    public String getUserName() {
        return userName;
    }

    public UserDTO setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDTO setPassword(String password) {
        this.password = password;
        return this;
    }
}
