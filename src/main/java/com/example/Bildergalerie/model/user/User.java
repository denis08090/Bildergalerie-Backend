package com.example.Bildergalerie.model.user;

import com.example.Bildergalerie.generic.ExtendedEntity;
import com.example.Bildergalerie.model.role.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * **Entitätsklasse für Benutzer (`User`).**
 *
 * Diese Klasse repräsentiert Benutzer innerhalb der Anwendung und speichert ihre Daten,
 * einschließlich ihrer Rollen (`Role`).
 *
 * **Validierung:**
 * - `@NotEmpty`: Stellt sicher, dass ein Wert eingegeben wird.
 * - `@Size(min, max)`: Definiert eine Mindest- und Maximallänge für Eingaben.
 * - `@Email`: Stellt sicher, dass eine gültige E-Mail-Adresse eingegeben wird.
 *
 * **Beziehungen:**
 * - `@ManyToMany(fetch = FetchType.EAGER)`: Jeder Benutzer kann mehrere Rollen haben.
 * - `@JoinTable`: Erstellt eine Zwischentabelle `users_role`, um die viele-zu-viele-Beziehung zu verwalten.
 *
 * @version 1.0
 * @since 2024-07-26
 */
@Entity
public class User extends ExtendedEntity {

    /**
     * **Benutzername (muss eindeutig sein).**
     *
     * - Darf nicht leer sein (`@NotEmpty`).
     * - Muss zwischen 1 und 50 Zeichen lang sein (`@Size`).
     * - Ist **eindeutig** in der Datenbank (`@Column(unique = true, nullable = false)`).
     */
    @NotEmpty(message = "Username must not be empty")
    @Size(min = 1, max = 50, message = "Username must be between 1 and 50 characters")
    @Column(unique = true, nullable = false)
    private String userName;

    /**
     * **Vorname des Benutzers.**
     *
     * - Darf nicht leer sein (`@NotEmpty`).
     * - Muss zwischen 1 und 50 Zeichen lang sein (`@Size`).
     */
    @NotEmpty(message = "Firstname must not be empty")
    @Size(min = 1, max = 50, message = "Firstname must be between 1 and 50 characters")
    private String firstName;

    /**
     * **Nachname des Benutzers.**
     *
     * - Darf nicht leer sein (`@NotEmpty`).
     * - Muss zwischen 1 und 50 Zeichen lang sein (`@Size`).
     */
    @NotEmpty(message = "Lastname must not be empty")
    @Size(min = 1, max = 50, message = "Lastname must be between 1 and 50 characters")
    private String lastName;

    /**
     * **E-Mail-Adresse des Benutzers.**
     *
     * - Darf nicht leer sein (`@NotEmpty`).
     * - Muss eine gültige E-Mail-Adresse sein (`@Email`).
     * - Ist **eindeutig** in der Datenbank (`@Column(unique = true, nullable = false)`).
     */
    @NotEmpty(message = "E-Mail must not be empty")
    @Email(message = "Invalid email format")
    @Column(unique = true, nullable = false)
    private String email;

    /**
     * **Passwort des Benutzers.**
     *
     * - Darf nicht leer sein (`@NotEmpty`).
     * - Muss mindestens 6 Zeichen lang sein (`@Size`).
     */
    @NotEmpty(message = "Password must not be empty")
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    private String password;

    /**
     * **Rollen des Benutzers (z. B. "USER", "ADMIN").**
     *
     * - `@ManyToMany(fetch = FetchType.EAGER)`: Benutzer können mehrere Rollen haben.
     * - `@JoinTable(name = "users_role")`: Erstellt eine Zwischentabelle `users_role`.
     * - `joinColumns`: Definiert den Fremdschlüssel für `users_id` in `users_role`.
     * - `inverseJoinColumns`: Definiert den Fremdschlüssel für `role_id` in `users_role`.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_role",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    ) Set<Role> roles = new HashSet<>();

    // **Getter und Setter**

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
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
     */
    public void setEmail(String email) {
        this.email = email;
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
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gibt die Rollen des Benutzers zurück.
     *
     * @return Eine `Set` von `Role`-Objekten.
     */
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     * Setzt die Rollen des Benutzers.
     *
     * @param roles Ein `Set` von `Role`-Objekten.
     * @return Die aktuelle `User`-Instanz (Methodenverkettung).
     */
    public User setRoles(Set<Role> roles) {
        this.roles = roles;
        return this;
    }
}
