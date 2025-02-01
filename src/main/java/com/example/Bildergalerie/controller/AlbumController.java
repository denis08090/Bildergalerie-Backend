package com.example.Bildergalerie.controller;

import com.example.Bildergalerie.model.Album.Album;
import com.example.Bildergalerie.model.Album.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * **REST-Controller zur Verwaltung von Alben.**
 *
 * Diese Klasse stellt verschiedene Endpunkte zur Verfügung, um Alben zu erstellen, abzurufen,
 * zu aktualisieren und zu löschen.
 *
 * @version 1.0
 * @since 2024-07-26
 * @author Denis Roos
 */
@RestController // Markiert diese Klasse als REST-Controller, sodass sie HTTP-Anfragen verarbeiten kann.
@RequestMapping("/albums") // Definiert die Basis-URL für alle Endpunkte in dieser Klasse.
public class AlbumController {

    @Autowired // Automatische Injektion des AlbumRepository zur Datenbankkommunikation.
    private AlbumRepository albumRepository;

    /**
     * **Gibt eine Liste aller vorhandenen Alben zurück.**
     *
     * @return Liste aller Alben als JSON-Array.
     */
    @GetMapping // Definiert einen HTTP-GET-Endpunkt unter "/albums".
    public List<Album> getAllAlbums() {
        return albumRepository.findAll(); // Holt alle Alben aus der Datenbank und gibt sie zurück.
    }

    /**
     * **Fügt ein neues Album zur Datenbank hinzu.**
     *
     * @param album Das Album-Objekt, das im Request-Body gesendet wird.
     * @return Das gespeicherte Album mit generierter ID.
     */
    @PostMapping // Definiert einen HTTP-POST-Endpunkt unter "/albums".
    public Album addAlbum(@RequestBody Album album) {
        return albumRepository.save(album); // Speichert das Album in der Datenbank und gibt es zurück.
    }

    /**
     * **Aktualisiert den Titel eines vorhandenen Albums.**
     *
     * @param albumId Die ID des zu aktualisierenden Albums (aus der URL).
     * @param title Der neue Titel des Albums (als Query-Parameter).
     * @return Ein ResponseEntity mit dem aktualisierten Album oder ein Status 404, falls das Album nicht existiert.
     */
    @PutMapping("/{albumId}") // Definiert einen HTTP-PUT-Endpunkt für die Aktualisierung eines Albums.
    public ResponseEntity<Album> updateAlbum(
            @PathVariable Long albumId, // Holt die Album-ID aus der URL.
            @RequestParam("title") String title // Holt den neuen Titel aus der Anfrage.
    ) {
        // Sucht das Album anhand der ID.
        Optional<Album> existingAlbumOptional = albumRepository.findById(albumId);

        // Falls das Album nicht gefunden wird, gibt es eine 404-Not-Found-Antwort zurück.
        if (!existingAlbumOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // Aktualisiert den Titel des Albums.
        Album existingAlbum = existingAlbumOptional.get();
        existingAlbum.setAlbumTitle(title);

        // Speichert das aktualisierte Album in der Datenbank und gibt es zurück.
        return ResponseEntity.ok(albumRepository.save(existingAlbum));
    }

    /**
     * **Löscht ein Album anhand seiner ID.**
     *
     * @param albumId Die ID des zu löschenden Albums (aus der URL).
     * @return Ein ResponseEntity mit Status 204 (NO CONTENT), wenn das Album erfolgreich gelöscht wurde,
     *         oder ein Status 404, falls das Album nicht existiert.
     */
    @DeleteMapping("/deleteAlbum/{albumId}") // Definiert einen HTTP-DELETE-Endpunkt für das Löschen eines Albums.
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long albumId) { // Holt die Album-ID aus der URL.
        // Überprüft, ob das Album existiert.
        Optional<Album> existingAlbumOptional = albumRepository.findById(albumId);

        // Falls das Album nicht existiert, gibt es eine 404-Not-Found-Antwort zurück.
        if (!existingAlbumOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        try {
            // Löscht das Album aus der Datenbank.
            albumRepository.deleteById(albumId);
            // Gibt eine 204-No-Content-Antwort zurück, was bedeutet, dass die Anfrage erfolgreich war, aber nichts zurückgegeben wird.
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            // Falls beim Löschen ein Fehler auftritt, wird eine Fehlermeldung ausgegeben.
            System.err.println("Fehler beim Löschen des Albums: " + e.getMessage());
            e.printStackTrace();
            // Gibt eine 500-Internal-Server-Error-Antwort zurück, falls ein unerwarteter Fehler auftritt.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
