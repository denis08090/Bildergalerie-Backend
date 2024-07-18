package com.example.Bildergalerie.controller;

import com.example.Bildergalerie.model.Album;
import com.example.Bildergalerie.model.AlbumRepository;
import com.example.Bildergalerie.model.Photo;
import com.example.Bildergalerie.model.PhotoRepository;
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
import java.util.Optional;

@RestController
@RequestMapping("/photos")
public class PhotoController {
    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @PostMapping("/addPhoto")
    public Photo addPhoto(
            @RequestParam("albumId") Long albumId,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("location") String location,
            @RequestParam("date") String date,
            @RequestParam("file") MultipartFile file) throws IOException, SQLException, ParseException {

        Photo newPhoto = new Photo();
        newPhoto.setPhotoName(title);
        newPhoto.setPhotoDescription(description);
        newPhoto.setPhotoLocation(location);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date sqlDate = new Date(format.parse(date).getTime());
        newPhoto.setPhotoDate(sqlDate);

        SerialBlob blob = new SerialBlob(file.getBytes());
        newPhoto.setPhotoPicture(blob);

        Album album = albumRepository.findById(albumId).orElseThrow(() -> new RuntimeException("Album not found"));
        newPhoto.setAlbum(album);

        return photoRepository.save(newPhoto);
    }

    @GetMapping("/{id}")
    public Photo getPhotoById(@PathVariable Long id) {
        return photoRepository.findById(id).orElseThrow(() -> new RuntimeException("Photo not found"));
    }

    @GetMapping
    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }
}
