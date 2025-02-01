package com.example.Bildergalerie.model.role;

import com.example.Bildergalerie.authority.Authority;
import com.example.Bildergalerie.generic.ExtendedEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * **Repräsentiert eine Benutzerrolle innerhalb des Systems.**
 *
 * Die `Role`-Entität definiert verschiedene Rollen für Benutzer (`z. B. "ADMIN", "USER"`),
 * die mit bestimmten Berechtigungen (`Authority`) verknüpft sind.
 *
 * - `@Entity`: Markiert diese Klasse als JPA-Entität.
 * - `@Table(name = "role")`: Definiert den Namen der Datenbanktabelle.
 * - `@ManyToMany`: Stellt eine **viele-zu-viele**-Beziehung mit `Authority` her.
 * - `@JoinTable`: Erstellt eine Verknüpfungstabelle zwischen `Role` und `Authority`.
 *
 * @version 1.0
 * @since 2024-07-26
 */
@Entity
@Table(name = "role")
public class Role extends ExtendedEntity {

    /**
     * **Name der Rolle (z. B. "ADMIN", "USER").**
     *
     * - `@Column(name = "name", nullable = false, unique = true)`:
     *   - **Einzigartiger Wert** (`unique = true`).
     *   - **Nicht null** (`nullable = false`).
     */
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    /**
     * **Zugehörige Berechtigungen (`Authority`).**
     *
     * - `@ManyToMany(fetch = FetchType.EAGER)`:
     *   - `EAGER`: Berechtigungen werden direkt mitgeladen.
     *   - Jede Rolle kann mehrere Berechtigungen haben.
     * - `@JoinTable(name = "role_authority")`: Definiert eine Verknüpfungstabelle für die viele-zu-viele-Beziehung.
     *   - `joinColumns = @JoinColumn(name = "role_id", columnDefinition = "BINARY(16)")`: Fremdschlüssel für die Rolle.
     *   - `inverseJoinColumns = @JoinColumn(name = "authority_id", columnDefinition = "BINARY(16)")`: Fremdschlüssel für die Berechtigung.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_authority",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id", columnDefinition = "BINARY(16)"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id", columnDefinition = "BINARY(16)")
    )
    private Set<Authority> authorities = new HashSet<>();

    // **Konstruktoren**

    /**
     * **Standard-Konstruktor für JPA.**
     */
    public Role() {
    }

    /**
     * **Konstruktor zur Initialisierung einer Rolle mit Name und Berechtigungen.**
     *
     * @param id          Die UUID der Rolle.
     * @param name        Der Name der Rolle (z. B. "ADMIN").
     * @param authorities Die zugehörigen Berechtigungen.
     */
    public Role(UUID id, String name, Set<Authority> authorities) {
        super(id);
        this.name = name;
        this.authorities = authorities;
    }

    // **Getter und Setter**

    /**
     * Gibt den Namen der Rolle zurück.
     *
     * @return Der Rollenname.
     */
    public String getName() {
        return name;
    }

    /**
     * Setzt den Namen der Rolle.
     *
     * @param name Der neue Rollenname.
     * @return Die aktuelle `Role`-Instanz (Methodenverkettung).
     */
    public Role setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Gibt die Berechtigungen (`Authorities`) der Rolle zurück.
     *
     * @return Eine `Set` von `Authority`-Objekten.
     */
    public Set<Authority> getAuthorities() {
        return authorities;
    }

    /**
     * Setzt die Berechtigungen (`Authorities`) für diese Rolle.
     *
     * @param authorities Ein `Set` von `Authority`-Objekten.
     * @return Die aktuelle `Role`-Instanz (Methodenverkettung).
     */
    public Role setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
        return this;
    }
}
