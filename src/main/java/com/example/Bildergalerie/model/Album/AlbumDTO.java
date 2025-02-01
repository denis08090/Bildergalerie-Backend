package com.example.Bildergalerie.model.Album;

import com.example.Bildergalerie.model.Photo.PhotoDTO;
import java.util.List;
import java.util.UUID;

public class AlbumDTO {

    private Long albumId;
    private String albumTitle;
    private List<PhotoDTO> photos; // Verlinkt nur DTOs
    private UUID userId;

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public List<PhotoDTO> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoDTO> photos) {
        this.photos = photos;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
