package com.example.Bildergalerie.model.Photo;

import org.springframework.stereotype.Component;

/**
 * Mapper-Klasse für die Umwandlung zwischen Foto-Entitäten und FotoDTOs.
 * Diese Klasse erleichtert die Konvertierung von Datenmodellen, um sie für
 * die Kommunikation mit Clients oder die Speicherung in der Datenbank anzupassen.
 */
@Component
public class PhotoMapper {

    /**
     * Konvertiert eine Foto-Entität in ein PhotoDTO.
     *
     * @param photo Das zu konvertierende Foto-Objekt.
     * @return Ein PhotoDTO oder null, falls das Foto null ist.
     */
    public PhotoDTO toDTO(Photo photo) {
        if (photo == null) {
            return null;
        }

        PhotoDTO dto = new PhotoDTO();
        dto.setPhotoId(photo.getPhotoId()); // Setzt die Foto-ID
        dto.setPhotoTitle(photo.getPhotoTitle()); // Setzt den Foto-Titel
        dto.setPhotoLocation(photo.getPhotoLocation()); // Setzt den Speicherort des Fotos
        dto.setPhotoDate(photo.getPhotoDate()); // Setzt das Aufnahmedatum

        // Falls das Foto zu einem Album gehört, wird die Album-ID gesetzt
        if (photo.getAlbum() != null) {
            dto.setAlbumId(photo.getAlbum().getAlbumId());
        }

        return dto;
    }

    /**
     * Konvertiert ein PhotoDTO in eine Foto-Entität.
     *
     * @param dto Das zu konvertierende PhotoDTO.
     * @return Ein Photo-Objekt oder null, falls das DTO null ist.
     */
    public Photo toEntity(PhotoDTO dto) {
        if (dto == null) {
            return null;
        }

        Photo photo = new Photo();
        photo.setPhotoId(dto.getPhotoId()); // Setzt die Foto-ID
        photo.setPhotoTitle(dto.getPhotoTitle()); // Setzt den Foto-Titel
        photo.setPhotoLocation(dto.getPhotoLocation()); // Setzt den Speicherort des Fotos
        photo.setPhotoDate(dto.getPhotoDate()); // Setzt das Aufnahmedatum

        // Die Album-Zuordnung wird hier nicht gesetzt, da das DTO nur die Album-ID enthält

        return photo;
    }
}
