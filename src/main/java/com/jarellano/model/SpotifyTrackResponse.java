package com.jarellano.model;

import java.util.List;

public class SpotifyTrackResponse {
    private List<SpotifyTrack> items;

    public List<SpotifyTrack> getItems() {
        return items;
    }

    public void setItems(List<SpotifyTrack> items) {
        this.items = items;
    }
}