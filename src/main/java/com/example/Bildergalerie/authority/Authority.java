package com.example.Bildergalerie.authority;

import com.example.Bildergalerie.generic.ExtendedEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Die Klasse `Authority` repräsentiert eine Berechtigung (z. B. eine Rolle) innerhalb der Anwendung.
 * Sie ist eine JPA-Entity und wird in der Datenbank in der Tabelle "authority" gespeichert.
 */
@Entity
@Table(name = "authority") // Definiert den Tabellennamen in der Datenbank
public class Authority extends ExtendedEntity {

    /**
     * Der Name der Autorität (z. B. "ROLE_ADMIN", "ROLE_USER").
     * - @Column(name = "name"): Weist das Feld der Spalte "name" in der Datenbank zu.
     * - nullable = false: Der Name darf nicht null sein.
     * - unique = true: Der Name muss eindeutig sein (darf sich in der Datenbank nicht wiederholen).
     */
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    /**
     * Standard-Konstruktor (erforderlich für JPA).
     */
    public Authority() {
    }

    /**
     * Konstruktor zur Erstellung einer neuen Autorität mit einer eindeutigen ID und einem Namen.
     *
     * @param id   Die eindeutige ID der Autorität (UUID).
     * @param name Der Name der Autorität.
     */
    public Authority(UUID id, String name) {
        super(id); // Ruft den Konstruktor der Elternklasse (ExtendedEntity) auf
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
     * @return Das aktualisierte `Authority`-Objekt.
     */
    public Authority setName(String name) {
        this.name = name;
        return this;
    }
}
