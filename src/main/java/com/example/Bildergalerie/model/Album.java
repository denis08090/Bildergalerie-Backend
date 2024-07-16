package com.example.Bildergalerie.model;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int albumId;
    private String albumTitel;


@OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
private List<Photo> photos = new ArrayList<>();

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getAlbumTitel() {
        return albumTitel;
    }

    public void setAlbumTitel(String albumTitel) {
        this.albumTitel = albumTitel;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}
