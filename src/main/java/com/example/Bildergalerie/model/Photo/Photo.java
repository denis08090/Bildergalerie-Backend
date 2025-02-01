package com.example.Bildergalerie.model.Photo;

import com.example.Bildergalerie.model.Album.Album;
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
 * **Repräsentiert ein Foto in einem Album.**
 *
 * Diese Klasse speichert alle relevanten Informationen über ein Foto, darunter Titel,
 * Beschreibung, Ort, Datum und die Zuordnung zu einem Album.
 *
 * - `@Entity`: Markiert die Klasse als JPA-Entity.
 * - `@Table`: Falls nötig, könnte hier der Tabellenname definiert werden.
 * - `@ManyToOne`: Stellt die Beziehung zwischen `Photo` und `Album` her.
 *
 * @version 1.0
 * @since 2024-07-26
 * @author Denis Roos
 */
@Entity
public class Photo {

    /**
     * **Primärschlüssel für das Foto.**
     *
     * - `@Id`: Markiert dieses Feld als Primärschlüssel.
     * - `@GeneratedValue(strategy = GenerationType.IDENTITY)`: Automatische Generierung der ID durch die Datenbank.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long photoId;

    /**
     * **Titel des Fotos.**
     *
     * - `@NotEmpty`: Der Titel darf nicht leer sein.
     * - `@Size(min = 1, max = 500)`: Mindest- und Maximallänge für den Titel.
     */
    @NotEmpty(message = "Photo title must not be empty")
    @Size(min = 1, max = 500, message = "Photo title must be between 1 and 100 characters")
    private String photoTitle;

    /**
     * **Ort, an dem das Foto aufgenommen wurde.**
     *
     * - `@NotEmpty`: Der Ort darf nicht leer sein.
     * - `@Size(min = 1, max = 100)`: Mindest- und Maximallänge für den Ort.
     */
    @NotEmpty(message = "Photo location must not be empty")
    @Size(min = 1, max = 100, message = "Location must be between 1 and 100 characters")
    private String photoLocation;

    /**
     * **Aufnahmedatum des Fotos.**
     *
     * - `@NotNull`: Das Datum darf nicht null sein.
     * - `@PastOrPresent`: Das Datum darf nicht in der Zukunft liegen.
     */
    @NotNull(message = "Date must not be null")
    @PastOrPresent(message = "Date cannot be in the future")
    private Date photoDate;

    /**
     * **Das Foto selbst als `Blob`.**
     *
     * - `@JsonIgnore`: Verhindert, dass das Bild bei JSON-Responses zurückgegeben wird.
     * - Gespeichert als `Blob` (Binary Large Object), um Bilder direkt in der Datenbank zu speichern.
     */
    @JsonIgnore
    private Blob photoPicture;

    /**
     * **Verknüpfung mit einem Album.**
     *
     * - `@JsonBackReference`: Verhindert rekursive Serialisierung in JSON.
     * - `@ManyToOne`: Viele Fotos gehören zu einem Album.
     * - `@JoinColumn(name = "albumId")`: Definiert den Fremdschlüssel in der Datenbank.
     */
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "albumId", nullable = false)
    private Album album;

    // **Getter und Setter**

    /**
     * Gibt die ID des Fotos zurück.
     *
     * @return Die Foto-ID.
     */
    public Long getPhotoId() {
        return photoId;
    }

    /**
     * Setzt die ID des Fotos.
     *
     * @param photoId Die neue Foto-ID.
     */
    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    /**
     * Gibt den Titel des Fotos zurück.
     *
     * @return Der Foto-Titel.
     */
    public String getPhotoTitle() {
        return photoTitle;
    }

    /**
     * Setzt den Titel des Fotos.
     *
     * @param photoTitle Der neue Foto-Titel.
     */
    public void setPhotoTitle(String photoTitle) {
        this.photoTitle = photoTitle;
    }

    /**
     * Gibt den Ort des Fotos zurück.
     *
     * @return Der Aufnahmeort.
     */
    public String getPhotoLocation() {
        return photoLocation;
    }

    /**
     * Setzt den Aufnahmeort des Fotos.
     *
     * @param photoLocation Der neue Aufnahmeort.
     */
    public void setPhotoLocation(String photoLocation) {
        this.photoLocation = photoLocation;
    }

    /**
     * Gibt das Datum zurück, an dem das Foto aufgenommen wurde.
     *
     * @return Das Aufnahmedatum.
     */
    public Date getPhotoDate() {
        return photoDate;
    }

    /**
     * Setzt das Aufnahmedatum des Fotos.
     *
     * @param photoDate Das neue Datum.
     */
    public void setPhotoDate(Date photoDate) {
        this.photoDate = photoDate;
    }

    /**
     * Gibt das Bild als `Blob` zurück.
     *
     * @return Das Bild als `Blob`.
     */
    public Blob getPhotoPicture() {
        return photoPicture;
    }

    /**
     * Setzt das Bild als `Blob`.
     *
     * @param photoPicture Das neue Bild.
     */
    public void setPhotoPicture(Blob photoPicture) {
        this.photoPicture = photoPicture;
    }

    /**
     * Gibt das zugehörige Album zurück.
     *
     * @return Das Album, zu dem das Foto gehört.
     */
    public Album getAlbum() {
        return album;
    }

    /**
     * Setzt das Album, dem dieses Foto zugeordnet ist.
     *
     * @param album Das neue Album.
     */
    public void setAlbum(Album album) {
        this.album = album;
    }
}
