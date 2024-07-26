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

/**
 * REST controller for managing photos in the application.
 *
 * This controller provides endpoints for adding, retrieving, updating,
 * and deleting photos.
 *
 * @version 1.0
 * @since 2024-07-26
 * @author Denis Roos
 */
@RestController
@RequestMapping("/photos")
public class PhotoController {
    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private AlbumRepository albumRepository;

    /**
     * Adds a new photo to an album.
     *
     * @param albumId The ID of the album to which the photo is added
     * @param title The title of the photo
     * @param description The description of the photo
     * @param location The location of the photo
     * @param date The date the photo was taken
     * @param file The file representing the photo
     * @return The added photo
     * @throws IOException if an error occurs while reading the file
     * @throws SQLException if an error occurs with SQL operations
     * @throws ParseException if an error occurs while parsing the date
     */
    @PostMapping("/addPhoto")
    public Photo addPhoto(
            @RequestParam("albumId") Long albumId,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("location") String location,
            @RequestParam("date") String date,
            @RequestParam("file") MultipartFile file) throws IOException, SQLException, ParseException {

        Photo newPhoto = new Photo();
        newPhoto.setPhotoTitle(title);
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

    /**
     * Retrieves a list of all photos in a specific album.
     *
     * @param albumId The ID of the album
     * @return A list of photos in the album
     */
    @GetMapping("/album/{albumId}")
    public List<Photo> getPhotosByAlbum(@PathVariable Long albumId) {
        return photoRepository.findByAlbumAlbumId(albumId);
    }


    /**
     * Updates an existing photo.
     *
     * @param photoId The ID of the photo to be updated
     * @param title The new title of the photo
     * @param description The new description of the photo
     * @param location The new location of the photo
     * @param date The new date of the photo
     * @param file (Optional) The new file representing the photo
     * @return The updated photo
     * @throws IOException if an error occurs while reading the file
     * @throws SQLException if an error occurs with SQL operations
     * @throws ParseException if an error occurs while parsing the date
     */

    @PutMapping("/updatePhoto/{photoId}")
    public Photo updatePhoto(
            @PathVariable Long photoId,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("location") String location,
            @RequestParam("date") String date,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException, SQLException, ParseException {

        Photo existingPhoto = photoRepository.findById(photoId).orElseThrow(() -> new RuntimeException("Photo not found"));

        existingPhoto.setPhotoTitle(title);

        existingPhoto.setPhotoLocation(location);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date sqlDate = new Date(format.parse(date).getTime());
        existingPhoto.setPhotoDate(sqlDate);

        if (file != null) {
            SerialBlob blob = new SerialBlob(file.getBytes());
            existingPhoto.setPhotoPicture(blob);
        }

        return photoRepository.save(existingPhoto);
    }

    /**
     * Deletes a photo.
     *
     * @param photoId The ID of the photo to be deleted
     */
    @DeleteMapping("/deletePhoto/{photoId}")
    public void deletePhoto(@PathVariable Long photoId) {
        photoRepository.deleteById(photoId);
    }
}
