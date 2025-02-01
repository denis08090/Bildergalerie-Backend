package com.example.Bildergalerie.authority.authorityDTO;

import com.example.Bildergalerie.generic.ExtendedDTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * Die Klasse AuthorityDTO dient als Data Transfer Object (DTO) für Autoritätsdaten (z. B. Rollen oder Berechtigungen).
 * Sie erweitert die `ExtendedDTO`-Klasse und beinhaltet grundlegende Eigenschaften für eine Autorität.
 */
public class AuthorityDTO extends ExtendedDTO {

    /**
     * Der Name der Autorität (z. B. "ROLE_ADMIN", "ROLE_USER").
     *
     * - @NotNull stellt sicher, dass das Feld nicht null ist.
     * - @Size(min = 1, max = 255) stellt sicher, dass der Name mindestens 1 und höchstens 255 Zeichen lang ist.
     */
    @NotNull
    @Size(min = 1, max = 255)
    private String name;

    /**
     * Standard-Konstruktor (notwendig für Frameworks wie Spring oder Hibernate).
     */
    public AuthorityDTO() {
    }

    /**
     * Konstruktor mit Parametern zum Erstellen eines AuthorityDTO-Objekts mit einer ID und einem Namen.
     *
     * @param id   Die eindeutige ID der Autorität (UUID).
     * @param name Der Name der Autorität.
     */
    public AuthorityDTO(UUID id, String name) {
        super(id); // Ruft den Konstruktor der Elternklasse (ExtendedDTO) auf
        this.name = name;
    }

    /**
     * Gibt den Namen der Autorität zurück.
     *
     * @return Der Name der Autorität.
     */
    public String getName() {
        return name;
    }

    /**
     * Setzt den Namen der Autorität und gibt das aktualisierte Objekt zurück.
     * Dadurch wird eine fluide API ermöglicht.
     *
     * @param name Der neue Name der Autorität.
     * @return Das aktualisierte `AuthorityDTO`-Objekt.
     */
    public AuthorityDTO setName(String name) {
        this.name = name;
        return this;
    }
}
