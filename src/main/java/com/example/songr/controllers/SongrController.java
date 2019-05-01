package com.example.songr.controllers;

import com.example.songr.Exceptions.AlbumNotFoundException;
import com.example.songr.database.Album;
import com.example.songr.database.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/albums")
public class SongrController {

    @Autowired
    AlbumRepository repo;

    @GetMapping("/testData")
    public void getDumbyData() {
        Album album1 = new Album();
        album1.title = "Free Spirit";
        album1.artist = "Khalid";
        album1.songCount = 17;
        album1.length = 3437;
        album1.imageUrl = "https://lh3.googleusercontent.com/Yc6cRHzCoih2AYG5EXmqMB25R2AjwN3P_k1Zz3D8MO7_5A7UPzdOO-CujwoFlE3twgvItQ8a=s180-c-e100-rwu-v1";

        this.repo.save(album1);

        Album album2 = new Album();
        album2.title = "The Inevitable End";
        album2.artist = "Royksopp";
        album2.songCount = 17;
        album2.length = 5028;
        album2.imageUrl = "https://lh3.googleusercontent.com/FBvgBhl_Kp-g0euG8WJXhT-MreqRHn7zbdeXsPeei6I_phB24FA382Az4GVUKjinmIUHgXLTzA=s180-c-e100-rwu-v1s";

        this.repo.save(album2);
    }

    @GetMapping("/")
    //Return all albums
    public String getAlbums(Model model) {
        Album album1 = new Album();
        album1.title = "Free Spirit";
        album1.artist = "Khalid";
        album1.songCount = 17;
        album1.length = 3437;
        album1.imageUrl = "https://lh3.googleusercontent.com/Yc6cRHzCoih2AYG5EXmqMB25R2AjwN3P_k1Zz3D8MO7_5A7UPzdOO-CujwoFlE3twgvItQ8a=s180-c-e100-rwu-v1";

        this.repo.save(album1);

        Iterable<Album> albums = this.repo.findAll();
        model.addAttribute("albums", albums);
        return "Albums";
    }

    //Return one album
    @GetMapping("/{id}")
    public Album getAlbum(
            @PathVariable Long id
    ) {
        Optional<Album> album = this.repo.findById(id);
        if (album.isPresent()) {
            return album.get();
        } else {
            throw new AlbumNotFoundException();
        }
    }

    //Create album
    @PostMapping("/")
    public Album createAlbum(
            @RequestBody Album album
    ) {
        album = this.repo.save(album);
        return album;
    }

    //Update one album
    @PostMapping("/{id}")
    public Album updateAlbum(
            @PathVariable Long id,
            @PathVariable Album album
    ) {
        Optional<Album> repoAlbum = this.repo.findById(id);

        if (repoAlbum.isPresent()) {
            Album edit = repoAlbum.get();

            edit.title = album.title;
            edit.artist = album.artist;
            edit.songCount = album.songCount;
            edit.length = album.length;
            edit.imageUrl = album.imageUrl;

            edit = this.repo.save(edit);
            return edit;
        }
        throw new AlbumNotFoundException();
    }

    @DeleteMapping("/{id}")
    public void deleteAlbum(
            @PathVariable Long id
    ) {
        this.repo.deleteById(id);
    }
}
