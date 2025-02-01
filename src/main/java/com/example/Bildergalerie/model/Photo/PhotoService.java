package com.example.Bildergalerie.model.Photo;

import com.example.Bildergalerie.model.Album.Album;
import com.example.Bildergalerie.model.Album.AlbumRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service-Klasse für die Verwaltung von Fotos.
 * Diese Klasse stellt Methoden für CRUD-Operationen (Erstellen, Lesen, Aktualisieren, Löschen) bereit.
 */
@Service
public class PhotoService {

    private final PhotoRepository photoRepository; // Repository für Foto-Datenbankabfragen
    private final AlbumRepository albumRepository; // Repository für Alben
    private final PhotoMapper photoMapper; // Mapper für die Umwandlung von Foto-Entitäten in DTOs

    /**
     * Konstruktor für die Dependency Injection.
     *
     * @param photoRepository Repository für die Foto-Entität.
     * @param albumRepository Repository für die Album-Entität.
     * @param photoMapper Mapper zur Umwandlung von Foto-Entitäten zu DTOs.
     */
    public PhotoService(PhotoRepository photoRepository, AlbumRepository albumRepository, PhotoMapper photoMapper) {
        this.photoRepository = photoRepository;
        this.albumRepository = albumRepository;
        this.photoMapper = photoMapper;
    }

    /**
     * Ruft alle vorhandenen Fotos ab und gibt sie als Liste von DTOs zurück.
     *
     * @return Liste aller PhotoDTOs.
     */
    public List<PhotoDTO> getAllPhotos() {
        return photoRepository.findAll().stream()
                .map(photoMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Sucht ein Foto anhand seiner ID und gibt es als DTO zurück.
     *
     * @param id Die ID des gesuchten Fotos.
     * @return Das PhotoDTO oder null, falls kein Foto mit der ID existiert.
     */
    public PhotoDTO getPhotoById(Long id) {
        return photoRepository.findById(id)
                .map(photoMapper::toDTO)
                .orElse(null);
    }

    /**
     * Speichert ein neues Foto oder aktualisiert ein bestehendes Foto.
     * Falls das Foto einem Album zugeordnet ist, wird die Beziehung gesetzt.
     *
     * @param photoDTO Das zu speichernde PhotoDTO.
     * @return Das gespeicherte Foto als DTO.
     */
    public PhotoDTO savePhoto(PhotoDTO photoDTO) {
        Photo photo = photoMapper.toEntity(photoDTO);

        // Falls das Foto einem Album zugeordnet ist, wird die Album-Zuordnung gesetzt
        if (photoDTO.getAlbumId() != null) {
            Optional<Album> album = albumRepository.findById(photoDTO.getAlbumId());
            album.ifPresent(photo::setAlbum);
        }

        return photoMapper.toDTO(photoRepository.save(photo));
    }

    /**
     * Löscht ein Foto anhand seiner ID.
     *
     * @param id Die ID des zu löschenden Fotos.
     */
    public void deletePhoto(Long id) {
        photoRepository.deleteById(id);
    }
}
