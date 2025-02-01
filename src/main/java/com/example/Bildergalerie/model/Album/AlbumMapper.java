package com.example.Bildergalerie.model.Album;

import com.example.Bildergalerie.model.Photo.PhotoMapper;
import com.example.Bildergalerie.model.user.User;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class AlbumMapper {

    private final PhotoMapper photoMapper;

    public AlbumMapper(PhotoMapper photoMapper) {
        this.photoMapper = photoMapper;
    }

    public AlbumDTO toDTO(Album album) {
        if (album == null) {
            return null;
        }

        AlbumDTO dto = new AlbumDTO();
        dto.setAlbumId(album.getAlbumId());
        dto.setAlbumTitle(album.getAlbumTitle());
        dto.setUserId(album.getUserId());
        dto.setPhotos(album.getPhotos().stream()
                .map(photoMapper::toDTO)
                .collect(Collectors.toList()));

        return dto;
    }

    public Album toEntity(AlbumDTO dto) {
        if (dto == null) {
            return null;
        }

        Album album = new Album();
        album.setAlbumId(dto.getAlbumId());
        album.setAlbumTitle(dto.getAlbumTitle());
        album.setUserId(dto.getUserId());

        return album;
    }
}
