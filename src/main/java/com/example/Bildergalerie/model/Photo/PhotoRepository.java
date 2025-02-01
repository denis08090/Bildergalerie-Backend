package com.example.Bildergalerie.model.Photo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * **Repository-Interface für die Verwaltung von {@link Photo}-Entitäten.**
 *
 * Dieses Interface erweitert `JpaRepository` und stellt CRUD-Operationen für `Photo`-Entitäten bereit.
 * Zusätzlich enthält es eine benutzerdefinierte Methode zur Suche von Fotos anhand der Album-ID.
 *
 * - `JpaRepository<Photo, Long>`:
 *   - `Photo`: Die verwaltete Entitätsklasse.
 *   - `Long`: Der Typ des Primärschlüssels (`photoId`).
 *
 * Standardmäßig stellt `JpaRepository` Methoden bereit wie:
 * - `findAll()`: Gibt alle Fotos zurück.
 * - `findById(Long id)`: Sucht ein Foto anhand seiner ID.
 * - `save(Photo photo)`: Speichert oder aktualisiert ein Foto.
 * - `deleteById(Long id)`: Löscht ein Foto anhand seiner ID.
 *
 * @version 1.0
 * @since 2024-07-26
 * @author Denis Roos
 */
public interface PhotoRepository extends JpaRepository<Photo, Long> {

    /**
     * **Sucht alle Fotos eines bestimmten Albums.**
     *
     * - Diese Methode wird von Spring Data JPA automatisch basierend auf dem Methodennamen generiert.
     * - Spring Data JPA erkennt `AlbumAlbumId` als Referenz auf das Attribut `albumId` in `Album`.
     *
     * @param albumId Die ID des Albums.
     * @return Eine Liste von `Photo`-Entitäten, die zu diesem Album gehören.
     */
    List<Photo> findByAlbumAlbumId(Long albumId);
}
