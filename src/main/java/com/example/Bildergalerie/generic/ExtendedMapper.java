package com.example.Bildergalerie.generic;

import java.util.List;
import java.util.Set;

/**
 * **Generisches Mapper-Interface für die Umwandlung zwischen Entitäten und DTOs.**
 *
 * Dieses Interface definiert allgemeine Methoden für das Mapping zwischen
 * Geschäftsobjekten (Business Objects, BOs) und Data Transfer Objects (DTOs).
 *
 * - **BO (Business Object)**: Eine Entität, die in der Datenbank gespeichert wird.
 * - **DTO (Data Transfer Object)**: Ein Objekt, das zur Übertragung von Daten dient,
 *   meist zwischen Backend und Frontend.
 *
 * @param <BO>  Die Entitätsklasse, die `ExtendedEntity` erweitert.
 * @param <DTO> Die DTO-Klasse, die `ExtendedDTO` erweitert.
 * @version 1.0
 * @since 2024-07-26
 */
public interface ExtendedMapper<BO extends ExtendedEntity, DTO extends ExtendedDTO> {

    /**
     * **Konvertiert ein DTO in eine Entität.**
     *
     * @param dto Das DTO, das in eine Entität umgewandelt wird.
     * @return Das entsprechende Business Object.
     */
    BO fromDTO(DTO dto);

    /**
     * **Konvertiert eine Liste von DTOs in eine Liste von Entitäten.**
     *
     * @param dtos Die Liste der DTOs.
     * @return Die entsprechende Liste von Business Objects.
     */
    List<BO> fromDTOs(List<DTO> dtos);

    /**
     * **Konvertiert ein Set von DTOs in ein Set von Entitäten.**
     *
     * @param dtos Das Set der DTOs.
     * @return Das entsprechende Set von Business Objects.
     */
    Set<BO> fromDTOs(Set<DTO> dtos);

    /**
     * **Konvertiert eine Entität in ein DTO.**
     *
     * @param BO Das Business Object, das in ein DTO umgewandelt wird.
     * @return Das entsprechende DTO.
     */
    DTO toDTO(BO BO);

    /**
     * **Konvertiert eine Liste von Entitäten in eine Liste von DTOs.**
     *
     * @param BOs Die Liste der Business Objects.
     * @return Die entsprechende Liste von DTOs.
     */
    List<DTO> toDTOs(List<BO> BOs);

    /**
     * **Konvertiert ein Set von Entitäten in ein Set von DTOs.**
     *
     * @param BOs Das Set der Business Objects.
     * @return Das entsprechende Set von DTOs.
     */
    Set<DTO> toDTOs(Set<BO> BOs);
}
