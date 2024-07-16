package com.example.Bildergalerie.model;

import com.example.Bildergalerie.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
