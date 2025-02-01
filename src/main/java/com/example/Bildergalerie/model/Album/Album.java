package com.example.Bildergalerie.model.Album;

import com.example.Bildergalerie.model.Photo.Photo;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

/**
 * **Repräsentiert ein Fotoalbum.**
 *
 * Diese Klasse enthält die Album-ID, den Titel des Albums sowie eine Liste von Fotos.
 * Zudem ist das Album einem Benutzer (`userId`) zugeordnet.
 *
 * - `@Entity`: Markiert diese Klasse als JPA-Entity.
 * - `@Table`: Falls notwendig, könnte hier der Tabellenname definiert werden.
 * - `@OneToMany`: Ein Album kann mehrere Fotos enthalten.
 * - `@JoinColumn`: Stellt die Beziehung zum Benutzer her.
 *
 * @version 1.0
 * @since 2024-07-26
 * @author Denis Roos
 */
@Entity
public class Album {

    /**
     * **Primärschlüssel für das Album.**
     *
     * - `@Id`: Markiert das Feld als Primärschlüssel.
     * - `@GeneratedValue(strategy = GenerationType.IDENTITY)`: Automatische Generierung der ID durch die Datenbank.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long albumId;

    /**
     * **Titel des Albums.**
     *
     * - `@NotNull`: Der Titel darf nicht `null` sein.
     * - `@Size(min = 1, max = 100)`: Länge des Titels muss zwischen 1 und 100 Zeichen sein.
     * - `@Column(unique = true, nullable = false)`: Der Titel ist in der Datenbank einzigartig.
     */
    @NotNull(message = "Album title must not be null")
    @Size(min = 1, max = 100, message = "Album title must be between 1 and 100 characters")
    @Column(unique = true, nullable = false)
    private String albumTitle;

    /**
     * **Liste der Fotos, die zu diesem Album gehören.**
     *
     * - `@JsonManagedReference`: Verhindert rekursive Serialisierung bei JSON-Antworten.
     * - `@OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)`:
     *   - `mappedBy = "album"`: Die Zuordnung wird im `Photo`-Entity verwaltet.
     *   - `cascade = CascadeType.ALL`: Änderungen am Album betreffen auch alle verknüpften Fotos.
     *   - `orphanRemoval = true`: Entfernte Fotos werden aus der Datenbank gelöscht.
     */
    @JsonManagedReference
    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Photo> photos;

    /**
     * **ID des Benutzers, dem dieses Album gehört.**
     *
     * - `@JoinColumn(name = "user_id", nullable = false)`: Fremdschlüssel zur Benutzer-ID in der Datenbank.
     * - `UUID`: Universell eindeutige Identifikation des Benutzers.
     */
    @JoinColumn(name = "user_id", nullable = false)
    private UUID userId;

    // **Getter und Setter**

    /**
     * Gibt die ID des Albums zurück.
     *
     * @return Die Album-ID.
     */
    public Long getAlbumId() {
        return albumId;
    }

    /**
     * Setzt die ID des Albums.
     *
     * @param albumId Die neue Album-ID.
     */
    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    /**
     * Gibt den Titel des Albums zurück.
     *
     * @return Der Album-Titel.
     */
    public String getAlbumTitle() {
        return albumTitle;
    }

    /**
     * Setzt den Titel des Albums.
     *
     * @param albumTitle Der neue Album-Titel.
     */
    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    /**
     * Gibt die Liste der Fotos im Album zurück.
     *
     * @return Eine Liste der Fotos.
     */
    public List<Photo> getPhotos() {
        return photos;
    }

    /**
     * Setzt die Fotos für das Album.
     *
     * @param photos Eine Liste der Fotos.
     */
    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    /**
     * Gibt die Benutzer-ID zurück, die diesem Album zugeordnet ist.
     *
     * @return Die UUID des Benutzers.
     */
    public UUID getUserId() {
        return userId;
    }

    /**
     * Setzt die Benutzer-ID für dieses Album.
     *
     * @param userId Die UUID des Benutzers.
     */
    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
