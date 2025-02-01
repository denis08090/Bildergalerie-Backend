package com.example.Bildergalerie.generic;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

/**
 * **Abstrakte Basisklasse für Entitäten mit einer UUID als Primärschlüssel.**
 *
 * Diese Klasse bietet eine standardisierte ID-Funktionalität für alle Entitäten,
 * indem sie `UUID` als Primärschlüssel verwendet.
 *
 * - `@MappedSuperclass`: Diese Klasse wird nicht als eigene Tabelle gespeichert, sondern
 *   von anderen Entitätsklassen geerbt.
 * - `@Id`: Definiert die ID als Primärschlüssel.
 * - `@GeneratedValue(generator = "uuid2")`: Automatische Generierung einer UUID.
 * - `@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")`:
 *   Verwendet Hibernate zur Generierung von UUIDs.
 * - `@Column(name = "id", updatable = false, nullable = false, columnDefinition = "BINARY(16)")`:
 *   Speichert die UUID effizient als `BINARY(16)`, um Speicherplatz zu sparen.
 *
 * @version 1.0
 * @since 2024-07-26
 */
@MappedSuperclass // Markiert diese Klasse als Basisklasse für andere Entitäten.
public abstract class ExtendedEntity {

    /**
     * **Eindeutige ID der Entität.**
     *
     * - `@Id`: Markiert dieses Feld als Primärschlüssel.
     * - `@GeneratedValue(generator = "uuid2")`: Automatische Generierung einer UUID.
     * - `@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")`:
     *   Verwendet Hibernate, um eine UUID zu generieren.
     * - `@Column(name = "id", updatable = false, nullable = false, columnDefinition = "BINARY(16)")`:
     *   - Die ID ist nicht änderbar (`updatable = false`).
     *   - Die ID darf nicht `null` sein (`nullable = false`).
     *   - Speicherung als `BINARY(16)`, um Speicherplatz in der Datenbank zu sparen.
     */
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "BINARY(16)")
    private UUID id;

    /**
     * **Standard-Konstruktor (geschützt, da abstrakte Klasse).**
     *
     * Wird von Unterklassen genutzt.
     */
    protected ExtendedEntity() {
    }

    /**
     * **Konstruktor mit ID-Parameter.**
     *
     * @param id Die eindeutige UUID der Entität.
     */
    protected ExtendedEntity(UUID id) {
        this.id = id;
    }

    /**
     * **Getter für die ID.**
     *
     * @return Die UUID der Entität.
     */
    public UUID getId() {
        return id;
    }

    /**
     * **Setter für die ID.**
     *
     * - Wird für die fluide API implementiert, um Methodenverkettung zu ermöglichen.
     *
     * @param id Die neue UUID der Entität.
     * @return Die aktuelle Instanz von `ExtendedEntity`.
     */
    public ExtendedEntity setId(UUID id) {
        this.id = id;
        return this;
    }
}
