package com.example.Bildergalerie.model.Album;

import com.example.Bildergalerie.model.Photo.PhotoMapper;
import com.example.Bildergalerie.model.Photo.PhotoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final PhotoRepository photoRepository;
    private final AlbumMapper albumMapper;
    private final PhotoMapper photoMapper;

    public AlbumService(AlbumRepository albumRepository, PhotoRepository photoRepository,
                        AlbumMapper albumMapper, PhotoMapper photoMapper) {
        this.albumRepository = albumRepository;
        this.photoRepository = photoRepository;
        this.albumMapper = albumMapper;
        this.photoMapper = photoMapper;
    }

    public List<AlbumDTO> getAllAlbums() {
        return albumRepository.findAll().stream()
                .map(albumMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AlbumDTO getAlbumById(Long id) {
        return albumRepository.findById(id)
                .map(albumMapper::toDTO)
                .orElse(null);
    }

    public AlbumDTO saveAlbum(AlbumDTO albumDTO) {
        Album album = albumMapper.toEntity(albumDTO);
        return albumMapper.toDTO(albumRepository.save(album));
    }

    public void deleteAlbum(Long id) {
        albumRepository.deleteById(id);
    }
}
