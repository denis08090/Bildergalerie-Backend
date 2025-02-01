package com.example.Bildergalerie.model.Photo;

import org.springframework.stereotype.Component;

@Component
public class PhotoMapper {

    public PhotoDTO toDTO(Photo photo) {
        if (photo == null) {
            return null;
        }

        PhotoDTO dto = new PhotoDTO();
        dto.setPhotoId(photo.getPhotoId());
        dto.setPhotoTitle(photo.getPhotoTitle());
        dto.setPhotoLocation(photo.getPhotoLocation());
        dto.setPhotoDate(photo.getPhotoDate());

        if (photo.getAlbum() != null) {
            dto.setAlbumId(photo.getAlbum().getAlbumId());
        }

        return dto;
    }

    public Photo toEntity(PhotoDTO dto) {
        if (dto == null) {
            return null;
        }

        Photo photo = new Photo();
        photo.setPhotoId(dto.getPhotoId());
        photo.setPhotoTitle(dto.getPhotoTitle());
        photo.setPhotoLocation(dto.getPhotoLocation());
        photo.setPhotoDate(dto.getPhotoDate());

        return photo;
    }
}
