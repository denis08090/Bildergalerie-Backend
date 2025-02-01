package com.example.Bildergalerie.generic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

/**
 * **Abstrakte Implementierung eines generischen Service für CRUD-Operationen.**
 *
 * Diese Klasse stellt allgemeine CRUD-Funktionalitäten für Entitäten bereit, die von `ExtendedEntity` erben.
 * Durch die Verwendung von Generics kann sie für verschiedene Entitätstypen wiederverwendet werden.
 *
 * @param <T> Die Entitätsklasse, die von `ExtendedEntity` erbt.
 * @version 1.0
 * @since 2024-07-26
 */
public abstract class ExtendedServiceImpl<T extends ExtendedEntity> implements ExtendedService<T> {

    /**
     * Repository für die Entitätsverwaltung.
     * Dieses wird über Dependency Injection in den konkreten Service-Klassen bereitgestellt.
     */
    protected final ExtendedRepository<T> repository;

    /**
     * Logger für Protokollierung von Service-Aktivitäten.
     */
    protected final Logger logger;

    /**
     * **Konstruktor für den generischen Service.**
     *
     * @param repository Das Repository zur Datenbankverwaltung.
     * @param logger Der Logger zur Fehler- und Ereignisprotokollierung.
     */
    protected ExtendedServiceImpl(ExtendedRepository<T> repository, Logger logger) {
        this.repository = repository;
        this.logger = logger;
    }

    /**
     * **Speichert eine Entität in der Datenbank.**
     *
     * @param entity Die zu speichernde Entität.
     * @return Die gespeicherte Entität.
     */
    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    /**
     * **Löscht eine Entität anhand ihrer ID.**
     *
     * @param id Die UUID der zu löschenden Entität.
     * @return `null`, um den erwarteten Rückgabetyp `Void` zu erfüllen.
     * @throws NoSuchElementException Falls die Entität nicht gefunden wird.
     */
    @Override
    public Void deleteById(UUID id) throws NoSuchElementException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new NoSuchElementException(String.format("Entity with ID '%s' could not be found", id));
        }
        return null;
    }

    /**
     * **Aktualisiert eine vorhandene Entität anhand ihrer ID.**
     *
     * @param id Die UUID der zu aktualisierenden Entität.
     * @param entity Die neuen Werte für die Entität.
     * @return Die aktualisierte Entität.
     * @throws NoSuchElementException Falls die Entität nicht existiert.
     */
    @Override
    public T updateById(UUID id, T entity) throws NoSuchElementException {
        if (repository.existsById(id)) {
            entity.setId(id);
            return repository.save(entity);
        } else {
            throw new NoSuchElementException(String.format("Entity with ID '%s' could not be found", id));
        }
    }

    /**
     * **Gibt eine Liste aller Entitäten zurück.**
     *
     * @return Liste aller gespeicherten Entitäten.
     */
    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    /**
     * **Gibt eine paginierte Liste aller Entitäten zurück.**
     *
     * @param pageable Das Pageable-Objekt für die Paginierung.
     * @return Eine Liste der gefundenen Entitäten, paginiert.
     */
    @Override
    public List<T> findAll(Pageable pageable) {
        Page<T> pagedResult = repository.findAll(pageable);
        return pagedResult.hasContent() ? pagedResult.getContent() : new ArrayList<>();
    }

    /**
     * **Sucht eine Entität anhand ihrer ID.**
     *
     * @param id Die UUID der gesuchten Entität.
     * @return Die gefundene Entität.
     * @throws NoSuchElementException Falls die Entität nicht existiert.
     */
    @Override
    public T findById(UUID id) {
        return findOrThrow(repository.findById(id));
    }

    /**
     * **Überprüft, ob eine Entität mit der angegebenen ID existiert.**
     *
     * @param id Die UUID der zu überprüfenden Entität.
     * @return `true`, falls die Entität existiert, ansonsten `false`.
     */
    @Override
    public boolean existsById(UUID id) {
        return repository.existsById(id);
    }

    /**
     * **Gibt eine Entität zurück oder wirft eine Exception, falls sie nicht existiert.**
     *
     * @param optional Das optionale Objekt mit der Entität.
     * @return Die gefundene Entität.
     * @throws NoSuchElementException Falls die Entität nicht existiert.
     */
    @Override
    public T findOrThrow(Optional<T> optional) throws NoSuchElementException {
        return optional.orElseThrow(() -> new NoSuchElementException("No value present"));
    }
}
