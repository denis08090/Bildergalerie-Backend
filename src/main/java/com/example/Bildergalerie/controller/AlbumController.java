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
 * REST controller for managing albums.
 *
 * This controller provides endpoints for retrieving, adding, updating,
 * and deleting albums.
 *
 * @version 1.0
 * @since 2024-07-26
 * @author Denis Roos
 */
@RestController
@RequestMapping("/albums")
public class AlbumController {
    @Autowired
    private AlbumRepository albumRepository;

    /**
     * Retrieves a list of all albums.
     *
     * @return List of albums
     */
    @GetMapping
    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }


    /**
     * Adds a new album.
     *
     * @param album The album to be added
     * @return The added album
     */
    @PostMapping
    public Album addAlbum(@RequestBody Album album) {
        return albumRepository.save(album);
    }

    /**
            * Updates an existing album.
            *
            * @param albumId The ID of the album to update
     * @param title The new title of the album
     * @return ResponseEntity with the updated album
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
     * Deletes an album.
     *
     * @param albumId The ID of the album to delete
     * @return ResponseEntity with no content
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
