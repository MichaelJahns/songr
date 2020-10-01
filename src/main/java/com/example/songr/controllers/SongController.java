package com.example.songr.controllers;

import com.example.songr.database.Album;
import com.example.songr.database.AlbumRepository;
import com.example.songr.database.Song;
import com.example.songr.database.SongRepository;
import com.example.songr.exceptions.AlbumNotFoundException;
import com.example.songr.exceptions.SongNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/songs")
public class SongController {
    @Autowired
    SongRepository songRepo;

    @Autowired
    AlbumRepository albumRepo;

    @GetMapping("/")
    // Get All Songs
    public String getSongs(Model model) {
        List<Song> songs = songRepo.findAll();
        List<Album> albums = albumRepo.findAll();

        model.addAttribute("songs", songs);
        model.addAttribute("albums", albums);

        return "songs";
    }

    @GetMapping("/{id}")
    public String getSong(
            @PathVariable Long id,
            Model model
    ) {
        Optional<Song> foundSong = songRepo.findById(id);

        if (foundSong.isPresent()) {
            List<Album> albums = albumRepo.findAll();
            model.addAttribute("song", foundSong.get());
            model.addAttribute("albums", albums);
            return "song";
        }
        throw new SongNotFoundException();
    }

    @PostMapping("/")
    public RedirectView createSong(
            @RequestParam String title,
            @RequestParam int length,
            @RequestParam int track,
            @RequestParam Long album
    ) {
        Optional<Album> foundAlbum = albumRepo.findById(album);

        if (foundAlbum.isPresent()) {

            Song song = new Song();
            Album parentAlbum = foundAlbum.get();
            song.title = title;
            song.length = length;
            song.track = track;
            song.album = parentAlbum;

            song = songRepo.save(song);
        }
        return new RedirectView("/songs/");
    }

    @PostMapping("/update")
    public RedirectView updateSong(
            @RequestParam Long id,
            @RequestParam String title,
            @RequestParam int length,
            @RequestParam int track,
            @RequestParam Long album
    ) {
        Optional<Song> foundSong = songRepo.findById(id);
        Optional<Album> foundAlbum = albumRepo.findById(album);

        if (foundAlbum.isPresent() && foundSong.isPresent()) {
            Song song = foundSong.get();
            Album parentAlbum = foundAlbum.get();

            song.title = title;
            song.length = length;
            song.track = track;
            song.album = parentAlbum;

            song = songRepo.save(song);

        } else if (foundSong.isPresent() != true) {
            throw new SongNotFoundException();
        } else if (foundAlbum.isPresent() != true) {
            throw new AlbumNotFoundException();
        }
        return new

                RedirectView("/songs/");
    }
}
