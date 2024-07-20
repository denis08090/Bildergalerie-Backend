package com.example.Bildergalerie.model;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repository für die Verwaltung von Fotos.
 */
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findByAlbumAlbumId(Long albumId);
}
