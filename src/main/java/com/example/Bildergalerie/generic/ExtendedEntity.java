package com.example.Bildergalerie.generic;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

/**
 * **Abstrakte Basisklasse für Entitäten mit einer UUID als Primärschlüssel (BINARY(16)).**
 *
 * Diese Klasse dient als Grundlage für alle Entitäten, die eine UUID als Primärschlüssel haben.
 * Dabei wird die UUID als `BINARY(16)` gespeichert, um Speicherplatz zu sparen und die Performance zu verbessern.
 *
 * - `@MappedSuperclass`: Wird von anderen Entitätsklassen geerbt, aber nicht als eigene Tabelle gespeichert.
 * - `@Id`: Markiert das `id`-Feld als Primärschlüssel.
 * - `@GeneratedValue(generator = "uuid2")`: Automatische Generierung einer UUID.
 * - `@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")`: Hibernate UUID-Generator.
 * - `@Convert(converter = UUIDBinaryConverter.class)`: Nutzt einen `AttributeConverter`, um `UUID` in `BINARY(16)` umzuwandeln.
 * - `@Column(name = "id", updatable = false, nullable = false, columnDefinition = "BINARY(16)")`:
 *   - Speichert die UUID effizient als `BINARY(16)`.
 *   - `updatable = false`: Die ID kann nachträglich nicht geändert werden.
 *   - `nullable = false`: Die ID darf nicht `null` sein.
 */
@MappedSuperclass
public abstract class ExtendedEntity {

    /**
     * **Primärschlüssel der Entität als UUID.**
     *
     * - Automatische Generierung per Hibernate (`uuid2`).
     * - Speicherung als `BINARY(16)` in der Datenbank.
     */
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Convert(converter = UUIDBinaryConverter.class) // Nutzt den Konverter für BINARY(16)
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "BINARY(16)")
    private UUID id;

    /**
     * **Standard-Konstruktor (geschützt, da abstrakte Klasse).**
     *
     * Wird von Unterklassen genutzt, um eine neue Instanz zu erstellen.
     */
    protected ExtendedEntity() {
    }

    /**
     * **Konstruktor mit ID-Parameter.**
     *
     * Wird verwendet, wenn eine spezifische UUID gesetzt werden soll.
     *
     * @param id Die eindeutige UUID der Entität.
     */
    protected ExtendedEntity(UUID id) {
        this.id = id;
    }

    /**
     * **Gibt die UUID der Entität zurück.**
     *
     * @return Die UUID des Objekts.
     */
    public UUID getId() {
        return id;
    }

    /**
     * **Setzt eine neue UUID für die Entität.**
     *
     * Wird für Methodenverkettung (`Fluent API`) implementiert.
     *
     * @param id Die neue UUID der Entität.
     * @return Die aktuelle Instanz von `ExtendedEntity`.
     */
    public ExtendedEntity setId(UUID id) {
        this.id = id;
        return this;
    }
}
