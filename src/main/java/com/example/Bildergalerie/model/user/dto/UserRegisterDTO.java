package com.example.Bildergalerie.model.user.dto;

import com.example.Bildergalerie.generic.ExtendedDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class UserRegisterDTO extends ExtendedDTO {

  @NotEmpty(message = "{javax.validation.constraints.FirstName.Empty.message}")
  private String firstName;
  @NotEmpty(message = "{javax.validation.constraints.LastName.Empty.message}")
  private String lastName;


  @Email
  private String email;

  @NotEmpty(message = "{javax.validation.constraints.Password.Required.Empty.message}")
  private String password;


  @NotNull(message = "{javax.validation.constraints.Age.Required.Empty.message}")
  @Min(value = 1, message = "{javax.validation.constraints.Age.greater.Empty.message}")
  private Integer age;

  public int seeds;

  public UserRegisterDTO() {
  }

  public UserRegisterDTO(UUID id, String firstName, String lastName, String email, String password,
      String ahvNumber, Integer age, int seeds) {
    super(id);
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.age = age;
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

}
