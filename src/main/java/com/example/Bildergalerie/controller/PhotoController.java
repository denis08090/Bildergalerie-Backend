package com.example.Bildergalerie.controller;

import com.example.Bildergalerie.model.Photo;
import com.example.Bildergalerie.model.PhotoRepository;
import com.example.Bildergalerie.model.Album;
import com.example.Bildergalerie.model.AlbumRepository;
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

@RestController
@RequestMapping("/api/photos")
public class PhotoController {
    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @GetMapping
    public List<Photo> getPhotos() {
        return photoRepository.findAll();
    }

    @PostMapping
    public Photo addPhoto(
            @RequestParam("photoTitle") String photoTitle,
            @RequestParam("photoDescription") String photoDescription,
            @RequestParam("photoLocation") String photoLocation,
            @RequestParam("photoDate") String photoDate,
            @RequestParam("photoFile") MultipartFile photoFile,
            @RequestParam("albumId") Long albumId) throws IOException, SQLException, ParseException {

        Photo newPhoto = new Photo();
        newPhoto.setPhotoName(photoTitle);
        newPhoto.setPhotoDescription(photoDescription);
        newPhoto.setPhotoLocation(photoLocation);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date sqlDate = new Date(format.parse(photoDate).getTime());
        newPhoto.setPhotoDate(sqlDate);

        SerialBlob blob = new SerialBlob(photoFile.getBytes());
        newPhoto.setPhotoPicture(blob);

        Album album = albumRepository.findById(albumId).orElseThrow(() -> new IllegalArgumentException("Invalid album Id:" + albumId));
        newPhoto.setAlbum(album);

        return photoRepository.save(newPhoto);
    }
}
