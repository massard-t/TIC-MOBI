package com.androiduniverse.coquardmassard.androiduniverse;

import com.google.gson.annotations.SerializedName;

/**
 * Created by alexiscoquard on 17/04/2017.
 */

public class Album {
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("artist")
    private Artist artist;

    public Album(int id, String title, Artist artist) {
        this.id = id;
        this.title = title;
        this.artist = artist;
    }

    public String getTitle() {
        return this.title;
    }

    public Artist getArtist() {
        return this.artist;
    }
}
