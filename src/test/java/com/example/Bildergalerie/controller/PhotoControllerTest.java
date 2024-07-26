package com.example.Bildergalerie.controller;

import com.example.Bildergalerie.model.Album;
import com.example.Bildergalerie.model.AlbumRepository;
import com.example.Bildergalerie.model.Photo;
import com.example.Bildergalerie.model.PhotoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PhotoControllerTest {

    @Mock
    private PhotoRepository photoRepository;

    @Mock
    private AlbumRepository albumRepository;

    @InjectMocks
    private PhotoController photoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addPhoto() throws Exception {
        Album album = new Album();
        album.setAlbumId(1L);

        Photo photo = new Photo();
        photo.setPhotoTitle("Test Photo");
        photo.setPhotoLocation("Test Location");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date sqlDate = new Date(format.parse("2023-01-01").getTime());
        photo.setPhotoDate(sqlDate);

        MockMultipartFile file = new MockMultipartFile("file", "test.jpg", "image/jpeg", "test data".getBytes());

        when(albumRepository.findById(1L)).thenReturn(Optional.of(album));
        when(photoRepository.save(any(Photo.class))).thenReturn(photo);

        Photo result = photoController.addPhoto(1L, "Test Photo", "Test Description", "Test Location", "2023-01-01", file);

        assertNotNull(result);
        assertEquals("Test Photo", result.getPhotoTitle());
        verify(photoRepository, times(1)).save(any(Photo.class));
    }

    @Test
    void getPhotosByAlbum() {
        Album album = new Album();
        album.setAlbumId(1L);
        Photo photo1 = new Photo();
        photo1.setAlbum(album);
        Photo photo2 = new Photo();
        photo2.setAlbum(album);
        List<Photo> photos = Arrays.asList(photo1, photo2);

        when(photoRepository.findByAlbumAlbumId(1L)).thenReturn(photos);

        List<Photo> result = photoController.getPhotosByAlbum(1L);
        assertEquals(2, result.size());
        verify(photoRepository, times(1)).findByAlbumAlbumId(1L);
    }

    @Test
    void updatePhoto() throws Exception {
        Photo photo = new Photo();
        photo.setPhotoId(1L);
        photo.setPhotoTitle("Old Title");
        photo.setPhotoLocation("Old Location");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date sqlDate = new Date(format.parse("2022-01-01").getTime());
        photo.setPhotoDate(sqlDate);

        when(photoRepository.findById(1L)).thenReturn(Optional.of(photo));
        when(photoRepository.save(any(Photo.class))).thenReturn(photo);

        Photo result = photoController.updatePhoto(1L, "New Title", "New Description", "New Location", "2023-01-01", null);

        assertNotNull(result);
        assertEquals("New Title", result.getPhotoTitle());
        assertEquals("New Location", result.getPhotoLocation());
        verify(photoRepository, times(1)).findById(1L);
        verify(photoRepository, times(1)).save(any(Photo.class));
    }

    @Test
    void deletePhoto() {
        Photo photo = new Photo();
        photo.setPhotoId(1L);

        when(photoRepository.findById(1L)).thenReturn(Optional.of(photo));
        doNothing().when(photoRepository).deleteById(1L);

        photoController.deletePhoto(1L);

        verify(photoRepository, times(1)).deleteById(1L);
    }
}
