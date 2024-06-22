package com.jarellano.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jarellano.entities.Artist;

import java.util.List;

public class ArtistResponse {
    @JsonProperty("items")
    private List<Artist> items;

    public List<Artist> getItems() {
        return items;
    }

    public void setItems(List<Artist> items) {
        this.items = items;
    }
}