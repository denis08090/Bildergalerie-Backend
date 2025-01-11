package com.example.Bildergalerie.DTO;

import com.example.Bildergalerie.generic.ExtendedDTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Set;
import java.util.UUID;

public class UserDTO extends ExtendedDTO {

    private String firstName;

    private String lastName;

    @Email
    private String email;

    @NotEmpty(message = "{javax.validation.constraints.Password.Required.Empty.message}")
    private String password;

    private Set<RoleDTO> roles;


    @Min(value = 1, message = "Age must be greater than 0")
    private Integer age;

    private int seeds;

    public UserDTO() {
    }

    public UserDTO(UUID id, String firstName, String lastName, String email,
                   Set<RoleDTO> roles, String password) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
        this.password = password;

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

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public UserDTO setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
        return this;
    }

    public int getAge() {
        return age;
    }

    public UserDTO setAge(Integer age) {
        this.age = age;
        return this;
    }

    public int getSeeds() { return seeds;}

    public UserDTO setSeeds(int seeds) {
        this.seeds = seeds;
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
