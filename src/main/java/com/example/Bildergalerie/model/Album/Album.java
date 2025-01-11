package com.example.Bildergalerie.model.Album;

import com.example.Bildergalerie.model.Photo.Photo;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
/**
 * Represents a photo album.
 *
 * This class includes the album ID, title, and a list of photos.
 *
 * @version 1.0
 * @since 2024-07-26
 * @author [Denis Roos]
 */
@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long albumId;

    @NotNull(message = "Album title must not be null")
    @Size(min = 1, max = 100, message = "Album title must be between 1 and 100 characters")
    @Column(unique = true, nullable = false)
    private String albumTitle;

    @JsonManagedReference
    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Photo> photos;

    //@ManyToOne(optional = false) // Ein Album muss immer einem User zugeordnet sein
    @JoinColumn(name = "user_id", nullable = false) // Name der Fremdschlüsselspalte in der Datenbank
    private Long userId;

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public @NotNull(message = "Album title must not be null") @Size(min = 1, max = 100, message = "Album title must be between 1 and 100 characters") String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(@NotNull(message = "Album title must not be null") @Size(min = 1, max = 100, message = "Album title must be between 1 and 100 characters") String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public Long getUserId(Long userId) {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
