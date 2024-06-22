package com.jarellano.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpotifyTrack {
    private String name;
    private String id;

    @JsonProperty("external_urls")
    private ExternalUrls externalUrls;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ExternalUrls getExternalUrls() {
        return externalUrls;
    }

    public void setExternalUrls(ExternalUrls externalUrls) {
        this.externalUrls = externalUrls;
    }
}