package com.example.Bildergalerie.model.Photo;

import java.sql.Date;

/**
 * Datenübertragungsobjekt (DTO) für Fotos.
 * Dieses Objekt dient der Übertragung von Foto-Daten zwischen Client und Server,
 * ohne direkt mit der Datenbank-Entität zu arbeiten.
 */
public class PhotoDTO {

    private Long photoId; // Eindeutige ID des Fotos
    private String photoTitle; // Titel des Fotos
    private String photoLocation; // Speicherort oder URL des Fotos
    private Date photoDate; // Aufnahmedatum des Fotos
    private Long albumId; // Referenz auf das zugehörige Album (anstelle des gesamten Album-Objekts)

    /**
     * Gibt die eindeutige ID des Fotos zurück.
     *
     * @return die Foto-ID.
     */
    public Long getPhotoId() {
        return photoId;
    }

    /**
     * Setzt die eindeutige ID des Fotos.
     *
     * @param photoId Die neue Foto-ID.
     */
    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    /**
     * Gibt den Titel des Fotos zurück.
     *
     * @return der Foto-Titel.
     */
    public String getPhotoTitle() {
        return photoTitle;
    }

    /**
     * Setzt den Titel des Fotos.
     *
     * @param photoTitle Der neue Titel des Fotos.
     */
    public void setPhotoTitle(String photoTitle) {
        this.photoTitle = photoTitle;
    }

    /**
     * Gibt den Speicherort oder die URL des Fotos zurück.
     *
     * @return der Speicherort des Fotos.
     */
    public String getPhotoLocation() {
        return photoLocation;
    }

    /**
     * Setzt den Speicherort oder die URL des Fotos.
     *
     * @param photoLocation Der neue Speicherort des Fotos.
     */
    public void setPhotoLocation(String photoLocation) {
        this.photoLocation = photoLocation;
    }

    /**
     * Gibt das Aufnahmedatum des Fotos zurück.
     *
     * @return das Aufnahmedatum.
     */
    public Date getPhotoDate() {
        return photoDate;
    }

    /**
     * Setzt das Aufnahmedatum des Fotos.
     *
     * @param photoDate Das neue Aufnahmedatum.
     */
    public void setPhotoDate(Date photoDate) {
        this.photoDate = photoDate;
    }

    /**
     * Gibt die ID des Albums zurück, zu dem das Foto gehört.
     *
     * @return die Album-ID.
     */
    public Long getAlbumId() {
        return albumId;
    }

    /**
     * Setzt die ID des Albums, zu dem das Foto gehört.
     *
     * @param albumId Die neue Album-ID.
     */
    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }
}
