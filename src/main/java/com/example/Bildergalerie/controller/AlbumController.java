package com.example.Bildergalerie.controller;

import com.example.Bildergalerie.model.Album;
import com.example.Bildergalerie.model.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller für die Verwaltung von Alben.
 */
@RestController
@RequestMapping("/albums")
public class AlbumController {
    @Autowired
    private AlbumRepository albumRepository;

    /**
     * Gibt eine Liste aller Alben zurück.
     *
     * @return Liste der Alben
     */
    @GetMapping
    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    /**
     * Fügt ein neues Album hinzu.
     *
     * @param album Das hinzuzufügende Album
     * @return Das hinzugefügte Album
     */
    @PostMapping
    public Album addAlbum(@RequestBody Album album) {
        return albumRepository.save(album);
    }

    /**
     * Aktualisiert ein bestehendes Album.
     *
     * @param albumId Die ID des zu aktualisierenden Albums
     * @param title Der neue Titel des Albums
     * @return ResponseEntity mit dem aktualisierten Album
     */
    @PutMapping("/{albumId}")
    public ResponseEntity<Album> updateAlbum(
            @PathVariable Long albumId,
            @RequestParam("title") String title) {

        Optional<Album> existingAlbumOptional = albumRepository.findById(albumId);
        if (!existingAlbumOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Album existingAlbum = existingAlbumOptional.get();
        existingAlbum.setAlbumTitle(title);
        return ResponseEntity.ok(albumRepository.save(existingAlbum));
    }

    /**
     * Löscht ein Album.
     *
     * @param albumId Die ID des zu löschenden Albums
     * @return ResponseEntity ohne Inhalt
     */
    @DeleteMapping("/deleteAlbum/{albumId}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long albumId) {
        Optional<Album> existingAlbumOptional = albumRepository.findById(albumId);
        if (!existingAlbumOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        try {
            albumRepository.deleteById(albumId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            System.err.println("Error deleting album: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
