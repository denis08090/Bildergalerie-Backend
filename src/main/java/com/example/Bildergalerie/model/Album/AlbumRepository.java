package com.example.Bildergalerie.model.Album;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Album} entities.
 *
 * This interface extends JpaRepository and provides CRUD operations
 * for managing Album entities in the database.
 *
 * @version 1.0
 * @since 2024-07-26
 * @author Denis Roos
 */
public interface AlbumRepository extends JpaRepository<Album, Long> {
}
