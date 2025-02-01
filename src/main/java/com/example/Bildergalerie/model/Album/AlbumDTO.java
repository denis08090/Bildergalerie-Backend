package com.example.Bildergalerie.model.Album;

import com.example.Bildergalerie.model.Photo.PhotoDTO;
import java.util.List;
import java.util.UUID;

/**
 * Datenübertragungsobjekt (DTO) für ein Album.
 * Dieses Objekt wird verwendet, um Album-Daten zwischen Client und Server zu übertragen,
 * ohne direkt mit der Datenbank-Entität zu arbeiten.
 */
public class AlbumDTO {

    private Long albumId; // Eindeutige ID des Albums
    private String albumTitle; // Titel des Albums
    private List<PhotoDTO> photos; // Liste der zugehörigen Fotos als DTOs
    private UUID userId; // ID des Benutzers, dem das Album gehört

    /**
     * Gibt die eindeutige ID des Albums zurück.
     *
     * @return die Album-ID.
     */
    public Long getAlbumId() {
        return albumId;
    }

    /**
     * Setzt die eindeutige ID des Albums.
     *
     * @param albumId Die neue Album-ID.
     */
    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    /**
     * Gibt den Titel des Albums zurück.
     *
     * @return der Album-Titel.
     */
    public String getAlbumTitle() {
        return albumTitle;
    }

    /**
     * Setzt den Titel des Albums.
     *
     * @param albumTitle Der neue Titel des Albums.
     */
    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    /**
     * Gibt die Liste der zugehörigen Fotos (als DTOs) zurück.
     *
     * @return Liste der Foto-DTOs.
     */
    public List<PhotoDTO> getPhotos() {
        return photos;
    }

    /**
     * Setzt die Liste der Fotos für das Album.
     *
     * @param photos Die neue Liste der Foto-DTOs.
     */
    public void setPhotos(List<PhotoDTO> photos) {
        this.photos = photos;
    }

    /**
     * Gibt die Benutzer-ID zurück, die das Album besitzt.
     *
     * @return die Benutzer-ID als UUID.
     */
    public UUID getUserId() {
        return userId;
    }

    /**
     * Setzt die Benutzer-ID für das Album.
     *
     * @param userId Die neue Benutzer-ID als UUID.
     */
    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
