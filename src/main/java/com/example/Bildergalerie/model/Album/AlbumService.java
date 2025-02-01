package com.example.Bildergalerie.model.Album;

import com.example.Bildergalerie.model.Photo.PhotoMapper;
import com.example.Bildergalerie.model.Photo.PhotoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service-Klasse für die Verwaltung von Alben.
 * Diese Klasse stellt Methoden für CRUD-Operationen (Erstellen, Lesen, Aktualisieren, Löschen) bereit.
 */
@Service
public class AlbumService {

    private final AlbumRepository albumRepository; // Repository für die Album-Datenbankabfragen
    private final PhotoRepository photoRepository; // Repository für Fotos
    private final AlbumMapper albumMapper; // Mapper für Album-Entitäten und DTOs
    private final PhotoMapper photoMapper; // Mapper für Foto-Entitäten und DTOs

    /**
     * Konstruktor für die Dependency Injection.
     *
     * @param albumRepository Repository für die Album-Entität.
     * @param photoRepository Repository für die Foto-Entität.
     * @param albumMapper Mapper zur Umwandlung von Album-Entitäten zu DTOs.
     * @param photoMapper Mapper zur Umwandlung von Foto-Entitäten zu DTOs.
     */
    public AlbumService(AlbumRepository albumRepository, PhotoRepository photoRepository,
                        AlbumMapper albumMapper, PhotoMapper photoMapper) {
        this.albumRepository = albumRepository;
        this.photoRepository = photoRepository;
        this.albumMapper = albumMapper;
        this.photoMapper = photoMapper;
    }

    /**
     * Ruft alle vorhandenen Alben ab und gibt sie als Liste von DTOs zurück.
     *
     * @return Liste aller AlbumDTOs.
     */
    public List<AlbumDTO> getAllAlbums() {
        return albumRepository.findAll().stream()
                .map(albumMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Sucht ein Album anhand seiner ID und gibt es als DTO zurück.
     *
     * @param id Die ID des gesuchten Albums.
     * @return Das AlbumDTO oder null, falls kein Album mit der ID existiert.
     */
    public AlbumDTO getAlbumById(Long id) {
        return albumRepository.findById(id)
                .map(albumMapper::toDTO)
                .orElse(null);
    }

    /**
     * Speichert ein neues Album oder aktualisiert ein bestehendes Album.
     *
     * @param albumDTO Das zu speichernde AlbumDTO.
     * @return Das gespeicherte Album als DTO.
     */
    public AlbumDTO saveAlbum(AlbumDTO albumDTO) {
        Album album = albumMapper.toEntity(albumDTO);
        return albumMapper.toDTO(albumRepository.save(album));
    }

    /**
     * Löscht ein Album anhand seiner ID.
     *
     * @param id Die ID des zu löschenden Albums.
     */
    public void deleteAlbum(Long id) {
        albumRepository.deleteById(id);
    }
}
