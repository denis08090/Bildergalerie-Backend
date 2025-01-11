package com.example.Bildergalerie.controller;

import com.example.Bildergalerie.model.Album.Album;
import com.example.Bildergalerie.model.Album.AlbumRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AlbumControllerTest {

    @Mock
    private AlbumRepository albumRepository;

    @InjectMocks
    private AlbumController albumController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllAlbums() {
        Album album1 = new Album();
        Album album2 = new Album();
        List<Album> albums = Arrays.asList(album1, album2);

        when(albumRepository.findAll()).thenReturn(albums);

        List<Album> result = albumController.getAllAlbums();
        assertEquals(2, result.size());
        verify(albumRepository, times(1)).findAll();
    }

    @Test
    void addAlbum() {
        Album album = new Album();
        when(albumRepository.save(album)).thenReturn(album);

        Album result = albumController.addAlbum(album);
        assertEquals(album, result);
        verify(albumRepository, times(1)).save(album);
    }

    @Test
    void updateAlbum() {
        Long albumId = 1L;
        String newTitle = "New Title";
        Album album = new Album();
        album.setAlbumTitle("Old Title");

        when(albumRepository.findById(albumId)).thenReturn(Optional.of(album));
        when(albumRepository.save(album)).thenReturn(album);

        Album result = albumController.updateAlbum(albumId, newTitle).getBody();
        assertNotNull(result);
        assertEquals(newTitle, result.getAlbumTitle());
        verify(albumRepository, times(1)).findById(albumId);
        verify(albumRepository, times(1)).save(album);
    }

    @Test
    void deleteAlbum() {
        Long albumId = 1L;
        Album album = new Album();

        when(albumRepository.findById(albumId)).thenReturn(Optional.of(album));
        doNothing().when(albumRepository).deleteById(albumId);

        albumController.deleteAlbum(albumId);
        verify(albumRepository, times(1)).findById(albumId);
        verify(albumRepository, times(1)).deleteById(albumId);
    }
}
