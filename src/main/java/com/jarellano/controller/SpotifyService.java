package com.jarellano.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jarellano.entities.Artist;
import com.jarellano.model.ArtistResponse;
import com.jarellano.model.SpotifyTrack;
import com.jarellano.model.SpotifyTrackResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

@Service
public class SpotifyService {

    private static final String SPOTIFY_API_URL = "https://api.spotify.com/v1/";
    private RestTemplate restTemplate;

    public SpotifyService() {
        this.restTemplate = new RestTemplate();
    }

    public String getTrack(String trackId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth("2MXqrO1RBfek6RoijghYYp");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                SPOTIFY_API_URL + "tracks/" + trackId,
                HttpMethod.GET,
                entity,
                String.class);

        return response.getBody();
    }

    public List<SpotifyTrack> getTracksFromJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            SpotifyTrackResponse response = objectMapper.readValue(Files.readAllBytes(Paths.get("src/main/java/com/jarellano/service/spoti.json.")), SpotifyTrackResponse.class);
            return response.getItems();
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public String getArtist(String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth("6rqhFgbbKwnb9MLmUQDhG6");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                SPOTIFY_API_URL + "artists/" + id,
                HttpMethod.GET,
                entity,
                String.class);

        return response.getBody();
    }

    public List<Artist> getArtistsFromJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ArtistResponse response = objectMapper.readValue(Files.readAllBytes(Paths.get("src/main/java/com/jarellano/service/spoti.json")), ArtistResponse.class);
            return response.getItems();
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}