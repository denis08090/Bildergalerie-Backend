package com.example.Bildergalerie.model;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repository interface for {@link Photo} entities.
 *
 * This interface extends JpaRepository and provides CRUD operations
 * for managing Photo entities in the database. It also includes a custom
 * method to find photos by the album's ID.
 *
 * @version 1.0
 * @since 2024-07-26
 * @author Denis Roos
 */
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findByAlbumAlbumId(Long albumId);
}
