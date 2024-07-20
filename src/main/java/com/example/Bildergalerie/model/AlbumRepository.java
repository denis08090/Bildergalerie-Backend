package com.example.Bildergalerie.model;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository für die Verwaltung von Alben.
 */
public interface AlbumRepository extends JpaRepository<Album, Long> {
}
