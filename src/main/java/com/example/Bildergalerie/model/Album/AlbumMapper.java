package com.example.Bildergalerie.model.Album;

import com.example.Bildergalerie.model.Photo.PhotoMapper;
import com.example.Bildergalerie.model.user.User;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

/**
 * Mapper-Klasse für die Umwandlung zwischen Album-Entitäten und AlbumDTOs.
 * Diese Klasse erleichtert die Konvertierung von Datenmodellen, um sie für
 * die Kommunikation mit Clients oder die Speicherung in der Datenbank anzupassen.
 */
@Component
public class AlbumMapper {

    private final PhotoMapper photoMapper; // Mapper für Fotos

    /**
     * Konstruktor mit Abhängigkeitsspritze für den PhotoMapper.
     *
     * @param photoMapper Mapper zur Konvertierung von Fotos.
     */
    public AlbumMapper(PhotoMapper photoMapper) {
        this.photoMapper = photoMapper;
    }

    /**
     * Konvertiert eine Album-Entität in ein AlbumDTO.
     *
     * @param album Das zu konvertierende Album-Objekt.
     * @return Ein AlbumDTO oder null, falls das Album null ist.
     */
    public AlbumDTO toDTO(Album album) {
        if (album == null) {
            return null;
        }

        AlbumDTO dto = new AlbumDTO();
        dto.setAlbumId(album.getAlbumId()); // Setzt die Album-ID
        dto.setAlbumTitle(album.getAlbumTitle()); // Setzt den Album-Titel
        dto.setUserId(album.getUserId()); // Setzt die Benutzer-ID

        // Konvertiert die Liste der Fotos mithilfe des PhotoMappers in DTOs
        dto.setPhotos(album.getPhotos().stream()
                .map(photoMapper::toDTO)
                .collect(Collectors.toList()));

        return dto;
    }

    /**
     * Konvertiert ein AlbumDTO in eine Album-Entität.
     *
     * @param dto Das zu konvertierende AlbumDTO.
     * @return Ein Album-Objekt oder null, falls das DTO null ist.
     */
    public Album toEntity(AlbumDTO dto) {
        if (dto == null) {
            return null;
        }

        Album album = new Album();
        album.setAlbumId(dto.getAlbumId()); // Setzt die Album-ID
        album.setAlbumTitle(dto.getAlbumTitle()); // Setzt den Album-Titel
        album.setUserId(dto.getUserId()); // Setzt die Benutzer-ID

        // Die Fotos werden nicht gesetzt, da sie aus dem DTO nicht zurückkonvertiert werden

        return album;
    }
}
