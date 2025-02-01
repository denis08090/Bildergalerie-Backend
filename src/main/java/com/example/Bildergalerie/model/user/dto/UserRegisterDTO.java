package com.example.Bildergalerie.model.user.dto;

import com.example.Bildergalerie.generic.ExtendedDTO;
import jakarta.validation.constraints.*;

import javax.persistence.Column;
import java.util.UUID;

/**
 * **DTO für die Benutzerregistrierung (`UserRegisterDTO`).**
 *
 * Dieses DTO wird verwendet, wenn sich ein neuer Benutzer registriert.
 * Es enthält Validierungsregeln für die eingegebenen Daten.
 *
 * **Validierung:**
 * - `@NotEmpty`: Stellt sicher, dass das Feld nicht leer ist.
 * - `@Size(min, max)`: Definiert eine Mindest- und Maximallänge.
 * - `@Email`: Prüft, ob eine gültige E-Mail-Adresse eingegeben wurde.
 * - `@Min`: Mindestalter für die Registrierung.
 *
 * @version 1.0
 * @since 2024-07-26
 */
public class UserRegisterDTO extends ExtendedDTO {

  /**
   * **Vorname des Benutzers.**
   *
   * - Darf nicht leer sein (`@NotEmpty`).
   */
  @NotEmpty(message = "First name is required")
  private String firstName;

  /**
   * **Nachname des Benutzers.**
   *
   * - Darf nicht leer sein (`@NotEmpty`).
   */
  @NotEmpty(message = "Last name is required")
  private String lastName;

  /**
   * **Benutzername (muss eindeutig sein).**
   *
   * - Darf nicht leer sein (`@NotEmpty`).
   * - Muss zwischen 1 und 50 Zeichen lang sein (`@Size`).
   * - Ist in der Datenbank **eindeutig** (`@Column(unique = true, nullable = false)`).
   */
  @NotEmpty(message = "Username must not be empty")
  @Size(min = 1, max = 50, message = "Username must be between 1 and 50 characters")
  @Column(unique = true, nullable = false)
  private String userName;

  /**
   * **E-Mail-Adresse des Benutzers.**
   *
   * - Muss eine gültige E-Mail-Adresse sein (`@Email`).
   */
  @Email(message = "Please provide a valid email address")
  private String email;

  /**
   * **Passwort des Benutzers.**
   *
   * - Darf nicht leer sein (`@NotEmpty`).
   * - Muss mindestens 6 Zeichen lang sein (`@Size`).
   */
  @NotEmpty(message = "Password must not be empty")
  @Size(min = 6, message = "Password must be at least 6 characters long")
  private String password;

  /**
   * **Alter des Benutzers.**
   *
   * - Darf nicht null sein (`@NotNull`).
   * - Muss mindestens 1 Jahr alt sein (`@Min(1)`).
   */
  @NotNull(message = "Age is required")
  @Min(value = 1, message = "Age must be greater than 0")
  private Integer age;

  /**
   * **Anzahl der Seeds (optionales Feld, keine Validierung).**
   */
  public int seeds;

  // **Konstruktoren**

  /**
   * **Standard-Konstruktor.**
   */
  public UserRegisterDTO() {
  }

  /**
   * **Konstruktor zur Initialisierung eines `UserRegisterDTO`-Objekts.**
   *
   * @param id        Die UUID des Benutzers.
   * @param firstName Der Vorname.
   * @param lastName  Der Nachname.
   * @param email     Die E-Mail-Adresse.
   * @param password  Das Passwort.
   * @param age       Das Alter.
   * @param userName  Der Benutzername.
   */
  public UserRegisterDTO(UUID id, String firstName, String lastName, String email, String password, Integer age, String userName) {
    super(id);
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.age = age;
    this.userName = userName;
  }

  // **Getter und Setter mit Fluent API**

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

  public String getUserName() {
    return userName;
  }

  public UserRegisterDTO setUserName(String userName) {
    this.userName = userName;
    return this;
  }
}
