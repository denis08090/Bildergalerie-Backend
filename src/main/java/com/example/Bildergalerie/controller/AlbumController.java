package com.example.Bildergalerie.controller;

import com.example.Bildergalerie.model.Album;
import com.example.Bildergalerie.model.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/albums")
public class AlbumController {
    @Autowired
    private AlbumRepository albumRepository;

    @GetMapping
    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    @PostMapping
    public Album addAlbum(@RequestBody Album album) {
        return albumRepository.save(album);
    }

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
