package com.example.Bildergalerie.model.User;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long userId;

    @NotEmpty(message = "Username must not be empty")
    @Size(min = 1, max = 50, message = "Username must be between 1 and 50 characters")
    @UniqueElements(message = "Username is already used")
    @Column(unique = true)
    private String user_name;

    @NotEmpty(message = "Firstname must not be empty")
    @Size(min = 1, max = 50, message = "Firstname must be between 1 and 50 characters")
    private String first_name;

    @NotEmpty(message = "Lastname must not be empty")
    @Size(min = 1, max = 50, message = "Firstname must be between 1 and 50 characters")
    private String last_name;

    @NotEmpty(message = "E-Mail must not be empty")
    @Email
    @Column(unique = true)
    private String email;

    @NotEmpty(message = "Password must not be empty")
    @Size(min = 1, max = 50, message = "Password must be between 1 and 50 characters")
    private String password;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public @NotEmpty(message = "Username must not be empty") @Size(min = 1, max = 50, message = "Username must be between 1 and 50 characters") @UniqueElements(message = "Username is already used") String getUser_name() {
        return user_name;
    }

    public void setUser_name(@NotEmpty(message = "Username must not be empty") @Size(min = 1, max = 50, message = "Username must be between 1 and 50 characters") @UniqueElements(message = "Username is already used") String user_name) {
        this.user_name = user_name;
    }

    public @NotEmpty(message = "Firstname must not be empty") @Size(min = 1, max = 50, message = "Firstname must be between 1 and 50 characters") String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(@NotEmpty(message = "Firstname must not be empty") @Size(min = 1, max = 50, message = "Firstname must be between 1 and 50 characters") String first_name) {
        this.first_name = first_name;
    }

    public @NotEmpty(message = "Lastname must not be empty") @Size(min = 1, max = 50, message = "Firstname must be between 1 and 50 characters") String getLast_name() {
        return last_name;
    }

    public void setLast_name(@NotEmpty(message = "Lastname must not be empty") @Size(min = 1, max = 50, message = "Firstname must be between 1 and 50 characters") String last_name) {
        this.last_name = last_name;
    }

    public @NotEmpty(message = "E-Mail must not be empty") @Email @UniqueElements(message = "E-Mail is already used") String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty(message = "E-Mail must not be empty") @Email @UniqueElements(message = "E-Mail is already used") String email) {
        this.email = email;
    }

    public @NotEmpty(message = "Password must not be empty") @Size(min = 1, max = 50, message = "Password must be between 1 and 50 characters") String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty(message = "Password must not be empty") @Size(min = 1, max = 50, message = "Password must be between 1 and 50 characters") String password) {
        this.password = password;
    }
}
