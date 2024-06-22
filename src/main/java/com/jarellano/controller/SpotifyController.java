package com.jarellano.controller;

import com.jarellano.entities.Artist;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/apis/ApiSpotify")
public class SpotifyController {

    private SpotifyService spotifyService;

    public SpotifyController(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }

    @GetMapping("/artist/{id}")
    @ResponseBody
    public String getArtist(@PathVariable String id) {
        return spotifyService.getArtist(id);
    }

    @GetMapping
    public String apiSpotify() {
        return "apis/ApiSpotify";
    }

    @GetMapping("/artists")
    public String getArtists(Model model) {
        List<Artist> artists = spotifyService.getArtistsFromJson();
        model.addAttribute("artists", artists);
        return "apis/ApiSpotify";
    }
}