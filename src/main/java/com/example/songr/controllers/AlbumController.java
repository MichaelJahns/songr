package com.example.songr.controllers;

import com.example.songr.database.Album;
import com.example.songr.database.AlbumRepository;
import com.example.songr.exceptions.AlbumNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/albums")
public class AlbumController {

    @Autowired
    AlbumRepository albumRepo;

    @GetMapping("/")
    //Get All Album
    public String getAlbums(Model model) {
        List<Album> albums = this.albumRepo.findAll();
        model.addAttribute("albums", albums);
        return "albums";
    }

    //Get One Album
    @GetMapping("/{id}")
    public String getAlbum(
            @PathVariable Long id,
            Model model
    ) {
        Optional<Album> foundAlbum = albumRepo.findById(id);

        if (foundAlbum.isPresent()) {
            model.addAttribute("album", foundAlbum.get());
            return "album";
        }
        throw new AlbumNotFoundException();
    }

    //Create album
    @PostMapping("/")
    public RedirectView createAlbum(
            @RequestParam String title,
            @RequestParam String artist,
            @RequestParam int songCount,
            @RequestParam int length,
            @RequestParam String imageUrl
    ) {
        Album album = new Album();
        album.title = title;
        album.artist = artist;
        album.songCount = songCount;
        album.length = length;
        album.imageUrl = imageUrl;
        album = this.albumRepo.save(album);
        return new RedirectView("/albums/");
    }

    @GetMapping("/generateAlbums")
    public RedirectView getDumbyData() {
        Album album1 = new Album();
        album1.title = "Free Spirit";
        album1.artist = "Khalid";
        album1.songCount = 17;
        album1.length = 3437;
        album1.imageUrl = "https://lh3.googleusercontent.com/Yc6cRHzCoih2AYG5EXmqMB25R2AjwN3P_k1Zz3D8MO7_5A7UPzdOO-CujwoFlE3twgvItQ8a=s180-c-e100-rwu-v1";

        album1 = this.albumRepo.save(album1);

        Album album2 = new Album();
        album2.title = "The Inevitable End";
        album2.artist = "Royksopp";
        album2.songCount = 17;
        album2.length = 5028;
        album2.imageUrl = "https://upload.wikimedia.org/wikipedia/en/9/91/R%C3%B6yksopp_-_The_Inevitable_End_-_cover.png";

        album2 = this.albumRepo.save(album2);

        return new RedirectView("/albums/");
    }

    //Update one album
    @PostMapping("/update")
    public RedirectView updateAlbum(
            @RequestParam Long id,
            @RequestParam String title,
            @RequestParam String artist,
            @RequestParam int songCount,
            @RequestParam int length
    ) {
        Optional<Album> repoAlbum = albumRepo.findById(id);

        if (repoAlbum.isPresent()) {
            Album edit = repoAlbum.get();

            edit.title = title;
            edit.artist = artist;
            edit.songCount = songCount;
            edit.length = length;

            edit = albumRepo.save(edit);
        } else {
            throw new AlbumNotFoundException();
        }
        return new RedirectView("/albums/");
    }

    @PostMapping("/delete")
    public RedirectView deleteAlbum(
            @RequestParam Long id
    ) {
        albumRepo.deleteById(id);
        return new RedirectView("/albums/");
    }
}
