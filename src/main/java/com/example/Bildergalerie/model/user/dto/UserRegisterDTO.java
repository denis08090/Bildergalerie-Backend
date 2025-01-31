package com.example.Bildergalerie.model.user.dto;

import com.example.Bildergalerie.generic.ExtendedDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import javax.persistence.Column;
import java.util.UUID;

public class UserRegisterDTO extends ExtendedDTO {

  @NotEmpty(message = "First name is required")
  private String firstName;
  @NotEmpty(message = "{javax.validation.constraints.LastName.Empty.message}")
  private String lastName;

  @NotEmpty(message = "Username must not be empty")
  @Size(min = 1, max = 50, message = "Username must be between 1 and 50 characters")
  @Column(unique = true, nullable = false)
  private String userName;


  @Email
  private String email;

  @NotEmpty(message = "{javax.validation.constraints.Password.Required.Empty.message}")
  @Size(min = 6, message = "Password must be at least 6 characters long")
  private String password;



  @NotNull(message = "{javax.validation.constraints.Age.Required.Empty.message}")
  @Min(value = 1, message = "{javax.validation.constraints.Age.greater.Empty.message}")
  private Integer age;

  public int seeds;

  public UserRegisterDTO() {
  }

  public UserRegisterDTO(UUID id, String firstName, String lastName, String email, String password, Integer age, String userName) {
    super(id);
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.age = age;
    this.userName = userName;
  }

  public String getFirstName() {
    return firstName;
  }

  public UserRegisterDTO setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public UserRegisterDTO setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public UserRegisterDTO setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public UserRegisterDTO setPassword(String password) {
    this.password = password;
    return this;
  }

  public Integer getAge() {
    return age;
  }

  public UserRegisterDTO setAge(Integer age) {
    this.age = age;
    return this;
  }

  public String getUserName() { return userName; }

  public UserRegisterDTO setUserName(String userName){
    this.userName = userName;
    return this;
  }



}
