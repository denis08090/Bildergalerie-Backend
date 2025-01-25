package com.example.Bildergalerie.model.user;

import com.example.Bildergalerie.generic.ExtendedEntity;
import com.example.Bildergalerie.model.role.Role;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class User extends ExtendedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID userId;

    @NotEmpty(message = "Username must not be empty")
    @Size(min = 1, max = 50, message = "Username must be between 1 and 50 characters")
    @Column(unique = true, nullable = false)
    private String userName;

    @NotEmpty(message = "Firstname must not be empty")
    @Size(min = 1, max = 50, message = "Firstname must be between 1 and 50 characters")
    private String firstName;

    @NotEmpty(message = "Lastname must not be empty")
    @Size(min = 1, max = 50, message = "Lastname must be between 1 and 50 characters")
    private String lastName;

    @NotEmpty(message = "E-Mail must not be empty")
    @Email(message = "Invalid email format")
    @Column(unique = true, nullable = false)
    private String email;

    @NotEmpty(message = "Password must not be empty")
    @Size(min = 1, max = 50, message = "Password must be between 1 and 50 characters")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_role",
            joinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles = new HashSet<>();

    // Getter and Setter methods
    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public User setRoles(Set<Role> roles) {
        this.roles = roles;
        return this;
    }
}
