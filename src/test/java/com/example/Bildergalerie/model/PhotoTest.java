package com.example.Bildergalerie.model;

import org.junit.jupiter.api.Test;

import java.sql.Blob;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class PhotoTest {

    @Test
    void getPhotoId() {
        Photo photo = new Photo();
        photo.setPhotoId(1L);
        assertEquals(1L, photo.getPhotoId());
    }

    @Test
    void setPhotoId() {
        Photo photo = new Photo();
        photo.setPhotoId(1L);
        assertEquals(1L, photo.getPhotoId());
    }

    @Test
    void getPhotoTitle() {
        Photo photo = new Photo();
        photo.setPhotoTitle("test");
        assertEquals("test", photo.getPhotoTitle());
    }

    @Test
    void setPhotoTitle() {
        Photo photo = new Photo();
        photo.setPhotoTitle("test");
        assertEquals("test", photo.getPhotoTitle());
    }

    @Test
    void getPhotoDescription() {
        Photo photo = new Photo();
        photo.setPhotoDescription("test");
        assertEquals("test", photo.getPhotoDescription());
    }

    @Test
    void setPhotoDescription() {
        Photo photo = new Photo();
        photo.setPhotoDescription("test");
        assertEquals("test", photo.getPhotoDescription());
    }

    @Test
    void getPhotoLocation() {
        Photo photo = new Photo();
        photo.setPhotoLocation("test");
        assertEquals("test", photo.getPhotoLocation());
    }

    @Test
    void setPhotoLocation() {
        Photo photo = new Photo();
        photo.setPhotoLocation("test");
        assertEquals("test", photo.getPhotoLocation());
    }

    @Test
    void setPhotoDate() {
        Photo photo = new Photo();
        Date date = new Date(System.currentTimeMillis());
        photo.setPhotoDate(date);
        assertEquals(date,photo.getPhotoDate());
    }

    @Test
    void getPhotoPicture() {
        Photo photo = new Photo();
        Blob blob = null;
        photo.setPhotoPicture(blob);
        assertEquals(blob, photo.getPhotoPicture());
    }

    @Test
    void setPhotoPicture() {
        Photo photo = new Photo();
        Blob blob = null;
        photo.setPhotoPicture(blob);
        assertEquals(blob, photo.getPhotoPicture());
    }

    @Test
    void getAlbum() {
        Photo photo = new Photo();
        Album album = new Album();
        photo.setAlbum(album);
        assertEquals(album, photo.getAlbum());
    }

    @Test
    void setAlbum() {
        Photo photo = new Photo();
        Album album = new Album();
        photo.setAlbum(album);
        assertEquals(album, photo.getAlbum());
    }
}