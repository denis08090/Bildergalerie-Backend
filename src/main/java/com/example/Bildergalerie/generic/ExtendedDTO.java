package com.example.Bildergalerie.generic;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * **Abstrakte Klasse für erweiterte Data Transfer Objects (DTOs).**
 *
 * Diese Klasse dient als Basis für DTOs und stellt eine UUID als eindeutige Identifikation bereit.
 * DTOs (Data Transfer Objects) werden verwendet, um Daten zwischen Schichten der Anwendung zu übertragen,
 * insbesondere zwischen dem Backend und dem Frontend.
 *
 * @version 1.0
 * @since 2024-07-26
 */
public abstract class ExtendedDTO {

    /**
     * **Eindeutige ID des DTOs.**
     *
     * - Die ID wird als `UUID` (Universally Unique Identifier) gespeichert.
     * - Sie kann für verschiedene Entitäten genutzt werden, um eine sichere Identifikation zu gewährleisten.
     */
    private UUID id;

    /**
     * **Standard-Konstruktor (geschützt, da abstrakte Klasse).**
     *
     * Dieser Konstruktor wird von abgeleiteten DTO-Klassen genutzt.
     */
    protected ExtendedDTO() {
    }

    /**
     * **Konstruktor mit ID-Parameter.**
     *
     * @param id Die eindeutige UUID des DTOs.
     */
    protected ExtendedDTO(UUID id) {
        this.id = id;
    }

    /**
     * **Getter für die ID.**
     *
     * @return Die UUID des DTOs.
     */
    public UUID getId() {
        return id;
    }

    /**
     * **Setter für die ID.**
     *
     * - Wird für die fluide API implementiert, damit Methodenverkettung möglich ist.
     *
     * @param id Die neue UUID des DTOs.
     * @return Die aktuelle Instanz von `ExtendedDTO`.
     */
    public ExtendedDTO setId(UUID id) {
        this.id = id;
        return this;
    }
}
