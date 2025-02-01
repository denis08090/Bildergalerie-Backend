package com.example.Bildergalerie.model.Photo;

import com.example.Bildergalerie.model.Album.Album;
import com.example.Bildergalerie.model.Album.AlbumRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PhotoService {

    private final PhotoRepository photoRepository;
    private final AlbumRepository albumRepository;
    private final PhotoMapper photoMapper;

    public PhotoService(PhotoRepository photoRepository, AlbumRepository albumRepository, PhotoMapper photoMapper) {
        this.photoRepository = photoRepository;
        this.albumRepository = albumRepository;
        this.photoMapper = photoMapper;
    }

    public List<PhotoDTO> getAllPhotos() {
        return photoRepository.findAll().stream()
                .map(photoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PhotoDTO getPhotoById(Long id) {
        return photoRepository.findById(id)
                .map(photoMapper::toDTO)
                .orElse(null);
    }

    public PhotoDTO savePhoto(PhotoDTO photoDTO) {
        Photo photo = photoMapper.toEntity(photoDTO);

        // Album setzen, falls es existiert
        if (photoDTO.getAlbumId() != null) {
            Optional<Album> album = albumRepository.findById(photoDTO.getAlbumId());
            album.ifPresent(photo::setAlbum);
        }

        return photoMapper.toDTO(photoRepository.save(photo));
    }

    public void deletePhoto(Long id) {
        photoRepository.deleteById(id);
    }
}
