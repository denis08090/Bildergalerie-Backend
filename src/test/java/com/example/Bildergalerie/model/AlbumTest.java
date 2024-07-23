package com.example.Bildergalerie.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlbumTest {

    @Test
    void getAlbumId() {
        Album album = new Album();
        album.setAlbumId(1L);
        assertEquals(1L, album.getAlbumId());
    }

    @Test
    void setAlbumId() {
        Album album = new Album();
        album.setAlbumId(1L);
        assertEquals(1L, album.getAlbumId());
    }

    @Test
    void getAlbumTitle() {
        Album album = new Album();
        album.setAlbumTitle("Switzerland");
        assertEquals("Switzerland", album.getAlbumTitle());
    }

    @Test
    void setAlbumTitle() {
        Album album = new Album();
        album.setAlbumTitle("Switzerland");
        assertEquals("Switzerland", album.getAlbumTitle());
    }
}
