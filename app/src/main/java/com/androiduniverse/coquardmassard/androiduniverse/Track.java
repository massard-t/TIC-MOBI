package com.androiduniverse.coquardmassard.androiduniverse;
import com.google.gson.annotations.SerializedName;

/**
 * Created by alexiscoquard on 17/04/2017.
 */

public class Track {
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("link")
    private String link;
    @SerializedName("position")
    private int position;
    @SerializedName("artist")
    private Artist artist;

    public Track(int id, String title, String link, int position, Artist artist) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.position = position;
        this.artist = artist;
    }
}
