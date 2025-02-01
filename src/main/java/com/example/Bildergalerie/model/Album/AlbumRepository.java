package com.example.Bildergalerie.model.Album;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * **Repository-Interface für die Verwaltung von {@link Album}-Entitäten.**
 *
 * Dieses Interface erweitert `JpaRepository` und stellt CRUD-Operationen für Alben bereit.
 * Durch die Verwendung von Spring Data JPA müssen die Methoden nicht manuell implementiert werden.
 *
 * - `JpaRepository<Album, Long>`:
 *   - `Album`: Die verwaltete Entitätsklasse.
 *   - `Long`: Der Typ des Primärschlüssels (`albumId`).
 *
 * Standardmäßig stellt `JpaRepository` Methoden wie bereit:
 * - `findAll()`: Gibt alle Alben zurück.
 * - `findById(Long id)`: Sucht ein Album anhand seiner ID.
 * - `save(Album album)`: Speichert oder aktualisiert ein Album.
 * - `deleteById(Long id)`: Löscht ein Album anhand seiner ID.
 *
 * @version 1.0
 * @since 2024-07-26
 * @author Denis Roos
 */
public interface AlbumRepository extends JpaRepository<Album, Long> {
}
