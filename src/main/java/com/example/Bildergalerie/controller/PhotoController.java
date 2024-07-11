package com.example.Bildergalerie.controller;

import com.example.Bildergalerie.model.Photo;
import com.example.Bildergalerie.model.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;



@Controller
public class PhotoController {
    @Autowired
    private PhotoRepository photoRepository;

    @GetMapping("/")
    public String home() {
        return "index";
    }
    @PostMapping("/addPhoto")
    public String addPhoto(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("location") String location,
            @RequestParam("date") String date,
            @RequestParam("file") MultipartFile file,
            Model model) throws IOException, SQLException, ParseException {

        Photo newPhoto = new Photo();
        newPhoto.setName(title);
        newPhoto.setDescription(description);
        newPhoto.setLocation(location);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date sqlDate = new Date(format.parse(date).getTime());
        newPhoto.setDate(sqlDate);

        SerialBlob blob = new SerialBlob(file.getBytes());
        newPhoto.setPicture(blob);

        photoRepository.save(newPhoto);

        model.addAttribute("message", "Bravo du hast das Bilde erfolgreich Hochgeladen " + file.getOriginalFilename() + " !");
        return "redirect:/";
    }

    @GetMapping("/photos")
    public String getPhotos(Model model) {
        List<Photo> photos = photoRepository.findAll();
        model.addAttribute("photos", photos);
        return "photos";
    }
}
