package com.example.Bildergalerie.model.role.DTO;

import com.example.Bildergalerie.authority.authorityDTO.AuthorityDTO;
import com.example.Bildergalerie.generic.ExtendedDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

/**
 * **Data Transfer Object (DTO) für Rollen.**
 *
 * Dieses DTO wird verwendet, um Rollen und deren Berechtigungen (`AuthorityDTO`) zu übertragen.
 * Es erweitert `ExtendedDTO`, das eine universell eindeutige ID (`UUID`) enthält.
 *
 * - `@NotNull`: Der Name der Rolle darf nicht `null` sein.
 * - `@Size(min = 1, max = 255)`: Der Rollenname muss zwischen 1 und 255 Zeichen lang sein.
 * - `@Valid`: Die enthaltenen `AuthorityDTO`-Objekte werden ebenfalls validiert.
 *
 * @version 1.0
 * @since 2024-07-26
 */
public class RoleDTO extends ExtendedDTO {

    /**
     * **Name der Rolle (z. B. "ADMIN", "USER").**
     *
     * - Muss zwischen 1 und 255 Zeichen lang sein.
     * - Muss gesetzt sein (`@NotNull`).
     */
    @NotNull
    @Size(min = 1, max = 255)
    private String name;

    /**
     * **Set von Berechtigungen, die dieser Rolle zugeordnet sind.**
     *
     * - Enthält eine Menge von `AuthorityDTO`-Objekten.
     * - `@Valid`: Stellt sicher, dass die enthaltenen `AuthorityDTO`-Objekte ebenfalls validiert werden.
     */
    @Valid
    private Set<AuthorityDTO> authorities;

    /**
     * **Standard-Konstruktor für Serialisierung und Deserialisierung.**
     */
    public RoleDTO() {
    }

    /**
     * **Konstruktor zur Initialisierung einer Rolle mit Name und Berechtigungen.**
     *
     * @param id          Die eindeutige ID der Rolle (UUID).
     * @param name        Der Name der Rolle.
     * @param authorities Die Menge der zugehörigen Berechtigungen.
     */
    public RoleDTO(UUID id, String name, Set<AuthorityDTO> authorities) {
        super(id); // Setzt die ID in der übergeordneten Klasse `ExtendedDTO`
        this.name = name;
        this.authorities = authorities;
    }

    /**
     * **Gibt den Namen der Rolle zurück.**
     *
     * @return Der Rollenname.
     */
    public String getName() {
        return name;
    }

    /**
     * **Setzt den Namen der Rolle.**
     *
     * - Ermöglicht Methodenverkettung durch Rückgabe von `this`.
     *
     * @param name Der neue Rollenname.
     * @return Die aktuelle `RoleDTO`-Instanz.
     */
    public RoleDTO setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * **Gibt die zugehörigen Berechtigungen der Rolle zurück.**
     *
     * @return Ein `Set` von `AuthorityDTO`-Objekten.
     */
    public Set<AuthorityDTO> getAuthorities() {
        return authorities;
    }

    /**
     * **Setzt die zugehörigen Berechtigungen der Rolle.**
     *
     * - Ermöglicht Methodenverkettung durch Rückgabe von `this`.
     *
     * @param authorities Ein `Set` von `AuthorityDTO`-Objekten.
     * @return Die aktuelle `RoleDTO`-Instanz.
     */
    public RoleDTO setAuthorities(Set<AuthorityDTO> authorities) {
        this.authorities = authorities;
        return this;
    }
}
