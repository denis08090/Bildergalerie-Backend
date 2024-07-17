package com.example.Bildergalerie.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long albumId;
    private String albumTitel;

    // Getters and Setters
    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public String getAlbumTitel() {
        return albumTitel;
    }

    public void setAlbumTitel(String albumTitel) {
        this.albumTitel = albumTitel;
    }
}
