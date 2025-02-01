package com.example.Bildergalerie.generic;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Pageable;

/**
 * **Generisches Service-Interface für CRUD-Operationen auf Entitäten mit UUID.**
 *
 * Dieses Interface definiert allgemeine Methoden für das Speichern, Aktualisieren,
 * Löschen und Abrufen von Entitäten, die von `ExtendedEntity` erben.
 *
 * @param <T> Die Entitätsklasse, die von `ExtendedEntity` erbt.
 * @version 1.0
 * @since 2024-07-26
 */
public interface ExtendedService<T extends ExtendedEntity> {

    /**
     * **Speichert eine Entität in der Datenbank.**
     *
     * @param entity Die zu speichernde Entität.
     * @return Die gespeicherte Entität mit generierter ID.
     */
    T save(T entity);

    /**
     * **Löscht eine Entität anhand ihrer ID.**
     *
     * @param id Die UUID der zu löschenden Entität.
     * @throws NoSuchElementException Falls die Entität nicht existiert.
     */
    Void deleteById(UUID id) throws NoSuchElementException;

    /**
     * **Aktualisiert eine vorhandene Entität anhand ihrer ID.**
     *
     * @param id Die UUID der zu aktualisierenden Entität.
     * @param entity Die neuen Werte der Entität.
     * @return Die aktualisierte Entität.
     * @throws NoSuchElementException Falls die Entität nicht existiert.
     */
    T updateById(UUID id, T entity) throws NoSuchElementException;

    /**
     * **Gibt eine Liste aller gespeicherten Entitäten zurück.**
     *
     * @return Eine Liste aller Entitäten.
     */
    List<T> findAll();

    /**
     * **Gibt eine paginierte Liste aller gespeicherten Entitäten zurück.**
     *
     * @param pageable Das Pageable-Objekt für Paginierung und Sortierung.
     * @return Eine paginierte Liste der Entitäten.
     */
    List<T> findAll(Pageable pageable);

    /**
     * **Findet eine Entität anhand ihrer ID.**
     *
     * @param id Die UUID der gesuchten Entität.
     * @return Die gefundene Entität.
     * @throws NoSuchElementException Falls die Entität nicht existiert.
     */
    T findById(UUID id);

    /**
     * **Überprüft, ob eine Entität mit einer bestimmten ID existiert.**
     *
     * @param id Die UUID der Entität.
     * @return `true`, wenn die Entität existiert, sonst `false`.
     */
    boolean existsById(UUID id);

    /**
     * **Gibt eine Entität zurück oder wirft eine Ausnahme, falls sie nicht existiert.**
     *
     * @param optional Das `Optional<T>`, das eine Entität enthalten kann.
     * @return Die Entität, falls sie existiert.
     * @throws NoSuchElementException Falls die Entität nicht existiert.
     */
    T findOrThrow(Optional<T> optional) throws NoSuchElementException;
}
