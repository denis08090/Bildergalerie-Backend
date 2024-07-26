package com.example.Bildergalerie.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.sql.Blob;
import java.sql.Date;

/**
 * Represents a photo in an album.
 *
 * This class includes information about the photo such as the title, description,
 * location, date, and a reference to the album it belongs to.
 *
 * @version 1.0
 * @since 2024-07-26
 * @autor Denis Roos
 */
@Entity
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long photoId;
    @NotEmpty(message = "Photo title must not be empty")
    @Size(min = 1, max = 500, message = "Photo title must be between 1 and 100 characters")
    private String photoTitle;
    @NotEmpty(message = "Photo location must not be empty")
    @Size(min = 1, max = 100, message = "Location must be between 1 and 100 characters")
    private String photoLocation;
    @NotNull(message = "Date must not be null")
    @PastOrPresent(message = "Date cannot be in the future")
    private Date photoDate;

    @JsonIgnore
    private Blob photoPicture;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "albumId")
    private Album album;

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    public String getPhotoTitle() {
        return photoTitle;
    }

    public void setPhotoTitle(String photoTitle) {
        this.photoTitle = photoTitle;
    }

    public String getPhotoLocation() {
        return photoLocation;
    }

    public void setPhotoLocation(String photoLocation) {
        this.photoLocation = photoLocation;
    }

    public Date getPhotoDate() {
        return photoDate;
    }

    public void setPhotoDate(Date photoDate) {
        this.photoDate = photoDate;
    }

    public Blob getPhotoPicture() {
        return photoPicture;
    }

    public void setPhotoPicture(Blob photoPicture) {
        this.photoPicture = photoPicture;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}