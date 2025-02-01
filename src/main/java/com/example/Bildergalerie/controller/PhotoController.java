package com.example.Bildergalerie.controller;

import com.example.Bildergalerie.model.Album.Album;
import com.example.Bildergalerie.model.Album.AlbumRepository;
import com.example.Bildergalerie.model.Photo.Photo;
import com.example.Bildergalerie.model.Photo.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * **REST-Controller zur Verwaltung von Fotos in der Anwendung.**
 *
 * Dieser Controller stellt Endpunkte bereit, um Fotos hinzuzufügen, abzurufen, zu aktualisieren und zu löschen.
 *
 * @version 1.0
 * @since 2024-07-26
 * @author Denis Roos
 */
@RestController // Markiert die Klasse als REST-Controller für HTTP-Anfragen.
@RequestMapping("/photos") // Definiert die Basis-URL für alle Endpunkte dieser Klasse.
public class PhotoController {

    @Autowired // Automatische Injektion des PhotoRepository für Datenbankoperationen.
    private PhotoRepository photoRepository;

    @Autowired // Automatische Injektion des AlbumRepository für Datenbankoperationen.
    private AlbumRepository albumRepository;

    /**
     * **Fügt ein neues Foto zu einem Album hinzu.**
     *
     * @param albumId Die ID des Albums, dem das Foto hinzugefügt wird.
     * @param title Der Titel des Fotos.
     * @param description Die Beschreibung des Fotos.
     * @param location Der Aufnahmeort des Fotos.
     * @param date Das Datum, an dem das Foto aufgenommen wurde.
     * @param file Die Datei, die das Foto repräsentiert.
     * @return Das gespeicherte Foto-Objekt mit generierter ID.
     * @throws IOException Falls beim Lesen der Datei ein Fehler auftritt.
     * @throws SQLException Falls es zu einem Fehler bei SQL-Operationen kommt.
     * @throws ParseException Falls das Datum nicht korrekt formatiert ist.
     */
    @PostMapping("/addPhoto") // Definiert einen HTTP-POST-Endpunkt zum Hinzufügen eines Fotos.
    public Photo addPhoto(
            @RequestParam("albumId") Long albumId,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("location") String location,
            @RequestParam("date") String date,
            @RequestParam("file") MultipartFile file) throws IOException, SQLException, ParseException {

        // Neues Foto-Objekt erstellen
        Photo newPhoto = new Photo();
        newPhoto.setPhotoTitle(title);
        newPhoto.setPhotoLocation(location);

        // Datum konvertieren und setzen
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date sqlDate = new Date(format.parse(date).getTime());
        newPhoto.setPhotoDate(sqlDate);

        // Bild in BLOB umwandeln und setzen
        SerialBlob blob = new SerialBlob(file.getBytes());
        newPhoto.setPhotoPicture(blob);

        // Album aus der Datenbank abrufen oder Fehler werfen, falls es nicht existiert
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new RuntimeException("Album not found"));
        newPhoto.setAlbum(album);

        // Foto in der Datenbank speichern und zurückgeben
        return photoRepository.save(newPhoto);
    }

    /**
     * **Gibt eine Liste aller Fotos in einem bestimmten Album zurück.**
     *
     * @param albumId Die ID des Albums, dessen Fotos abgerufen werden sollen.
     * @return Eine Liste aller Fotos im angegebenen Album.
     */
    @GetMapping("/album/{albumId}") // Definiert einen HTTP-GET-Endpunkt zum Abrufen aller Fotos eines Albums.
    public List<Photo> getPhotosByAlbum(@PathVariable Long albumId) {
        return photoRepository.findByAlbumAlbumId(albumId);
    }

    /**
     * **Aktualisiert ein vorhandenes Foto.**
     *
     * @param photoId Die ID des Fotos, das aktualisiert werden soll.
     * @param title Der neue Titel des Fotos.
     * @param description Die neue Beschreibung des Fotos.
     * @param location Der neue Aufnahmeort des Fotos.
     * @param date Das neue Datum des Fotos.
     * @param file (Optional) Die neue Datei, falls ein neues Bild hochgeladen wird.
     * @return Das aktualisierte Foto.
     * @throws IOException Falls beim Lesen der Datei ein Fehler auftritt.
     * @throws SQLException Falls es zu einem Fehler bei SQL-Operationen kommt.
     * @throws ParseException Falls das Datum nicht korrekt formatiert ist.
     */
    @PutMapping("/updatePhoto/{photoId}") // Definiert einen HTTP-PUT-Endpunkt zum Aktualisieren eines Fotos.
    public Photo updatePhoto(
            @PathVariable Long photoId,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("location") String location,
            @RequestParam("date") String date,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException, SQLException, ParseException {

        // Foto aus der Datenbank abrufen oder Fehler werfen, falls es nicht existiert
        Photo existingPhoto = photoRepository.findById(photoId)
                .orElseThrow(() -> new RuntimeException("Photo not found"));

        // Aktualisierung der Foto-Daten
        existingPhoto.setPhotoTitle(title);
        existingPhoto.setPhotoLocation(location);

        // Datum konvertieren und setzen
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date sqlDate = new Date(format.parse(date).getTime());
        existingPhoto.setPhotoDate(sqlDate);

        // Falls eine neue Datei hochgeladen wurde, diese in ein BLOB umwandeln und setzen
        if (file != null) {
            SerialBlob blob = new SerialBlob(file.getBytes());
            existingPhoto.setPhotoPicture(blob);
        }

        // Aktualisiertes Foto in der Datenbank speichern und zurückgeben
        return photoRepository.save(existingPhoto);
    }

    /**
     * **Löscht ein Foto anhand seiner ID.**
     *
     * @param photoId Die ID des zu löschenden Fotos.
     */
    @DeleteMapping("/deletePhoto/{photoId}") // Definiert einen HTTP-DELETE-Endpunkt zum Löschen eines Fotos.
    public void deletePhoto(@PathVariable Long photoId) {
        photoRepository.deleteById(photoId);
    }
}
