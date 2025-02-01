package com.example.Bildergalerie.model.user.dto;

import com.example.Bildergalerie.generic.ExtendedDTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * **Data Transfer Object (DTO) für die Benutzerentität (`User`).**
 *
 * Dieses DTO dient als vereinfachte Darstellung der `User`-Entität für die Übertragung
 * von Daten zwischen verschiedenen Schichten der Anwendung.
 * Es enthält keine geschäftslogischen Methoden oder Datenbank-Anmerkungen.
 *
 * **Validierung:**
 * - `@NotEmpty`: Stellt sicher, dass das Feld nicht leer ist.
 * - `@Size(min, max)`: Definiert eine minimale und maximale Zeichenlänge.
 * - `@Email`: Prüft, ob eine gültige E-Mail-Adresse eingegeben wurde.
 *
 * @version 1.0
 * @since 2024-07-26
 */
public class UserDTO extends ExtendedDTO {

    /**
     * **Benutzername.**
     *
     * - Muss zwischen 1 und 50 Zeichen lang sein.
     * - Darf nicht leer sein.
     */
    @NotEmpty(message = "Username must not be empty")
    @Size(min = 1, max = 50, message = "Username must be between 1 and 50 characters")
    private String userName;

    /**
     * **Vorname des Benutzers.**
     *
     * - Muss zwischen 1 und 50 Zeichen lang sein.
     * - Darf nicht leer sein.
     */
    @NotEmpty(message = "Firstname must not be empty")
    @Size(min = 1, max = 50, message = "Firstname must be between 1 and 50 characters")
    private String firstName;

    /**
     * **Nachname des Benutzers.**
     *
     * - Muss zwischen 1 und 50 Zeichen lang sein.
     * - Darf nicht leer sein.
     */
    @NotEmpty(message = "Lastname must not be empty")
    @Size(min = 1, max = 50, message = "Lastname must be between 1 and 50 characters")
    private String lastName;

    /**
     * **E-Mail-Adresse des Benutzers.**
     *
     * - Muss eine gültige E-Mail-Adresse sein.
     * - Darf nicht leer sein.
     */
    @NotEmpty(message = "E-Mail must not be empty")
    @Email(message = "Please provide a valid email address")
    private String email;

    /**
     * **Passwort des Benutzers.**
     *
     * - Muss zwischen 1 und 50 Zeichen lang sein.
     * - Darf nicht leer sein.
     */
    @NotEmpty(message = "Password must not be empty")
    @Size(min = 1, max = 50, message = "Password must be between 1 and 50 characters")
    private String password;

    // **Konstruktoren**

    /**
     * **Standard-Konstruktor.**
     */
    public UserDTO() {
    }

    /**
     * **Konstruktor zur Initialisierung eines `UserDTO`-Objekts.**
     *
     * @param id        Die UUID des Benutzers.
     * @param userName  Der Benutzername.
     * @param firstName Der Vorname des Benutzers.
     * @param lastName  Der Nachname des Benutzers.
     * @param email     Die E-Mail-Adresse des Benutzers.
     * @param password  Das Passwort des Benutzers.
     */
    public UserDTO(UUID id, String userName, String firstName, String lastName, String email, String password) {
        super(id); // Setzt die ID aus `ExtendedDTO`
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    // **Getter und Setter mit fluent API für Methodenverkettung**

    /**
     * Gibt den Benutzernamen zurück.
     *
     * @return Der Benutzername.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setzt den Benutzernamen.
     *
     * @param userName Der neue Benutzername.
     * @return Die aktuelle `UserDTO`-Instanz (Methodenverkettung).
     */
    public UserDTO setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    /**
     * Gibt den Vornamen des Benutzers zurück.
     *
     * @return Der Vorname.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setzt den Vornamen des Benutzers.
     *
     * @param firstName Der neue Vorname.
     * @return Die aktuelle `UserDTO`-Instanz (Methodenverkettung).
     */
    public UserDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * Gibt den Nachnamen des Benutzers zurück.
     *
     * @return Der Nachname.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setzt den Nachnamen des Benutzers.
     *
     * @param lastName Der neue Nachname.
     * @return Die aktuelle `UserDTO`-Instanz (Methodenverkettung).
     */
    public UserDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * Gibt die E-Mail-Adresse des Benutzers zurück.
     *
     * @return Die E-Mail-Adresse.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setzt die E-Mail-Adresse des Benutzers.
     *
     * @param email Die neue E-Mail-Adresse.
     * @return Die aktuelle `UserDTO`-Instanz (Methodenverkettung).
     */
    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * Gibt das Passwort des Benutzers zurück.
     *
     * @return Das Passwort.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setzt das Passwort des Benutzers.
     *
     * @param password Das neue Passwort.
     * @return Die aktuelle `UserDTO`-Instanz (Methodenverkettung).
     */
    public UserDTO setPassword(String password) {
        this.password = password;
        return this;
    }
}
