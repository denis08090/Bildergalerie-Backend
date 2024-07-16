package com.example.Bildergalerie.model;

import javax.persistence.*;
import java.sql.Blob;
import java.sql.Date;

@Entity
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long photoId;
    private String photoName;
    private String photoDescription;
    private String photoLocation;
    private Date photoDate;
    private Blob photoPicture;

    @ManyToOne
    @JoinColumn(name = "albumId")
    private Album album;

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getPhotoDescription() {
        return photoDescription;
    }

    public void setPhotoDescription(String photoDescription) {
        this.photoDescription = photoDescription;
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
