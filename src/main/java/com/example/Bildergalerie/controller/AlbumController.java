package com.example.Bildergalerie.controller;

import com.example.Bildergalerie.model.Album;
import com.example.Bildergalerie.model.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/{id}") //path vlt ändern
    public Album updateAlbum(
            @PathVariable Long albumId,
            @RequestParam("title") String title) {
        Album existingAlbum = albumRepository.findById(albumId).orElseThrow(() -> new RuntimeException("Album not found"));
        existingAlbum.setAlbumTitle(title);
        return albumRepository.save(existingAlbum);
    }

    @DeleteMapping("/deleteAlbum/{albumId}")
    public void deleteAlbum(@PathVariable Long albumId) {
        albumRepository.deleteById(albumId);
    }
}
