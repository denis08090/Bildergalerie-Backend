package com.example.Bildergalerie.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long albumId;
    private String albumTitle;

    @OneToMany(mappedBy = "album")
    private List<Photo> photos;

    // Getters and Setters
    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitel) {
        this.albumTitle = albumTitel;
    }
}
