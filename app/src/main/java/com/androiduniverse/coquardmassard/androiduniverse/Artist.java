package com.androiduniverse.coquardmassard.androiduniverse;
import com.google.gson.annotations.SerializedName;

/**
 * Created by alexiscoquard on 17/04/2017.
 */

public class Artist {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("link")
    private String link;

    public Artist(int id, String name, String link) {
        this.id = id;
        this.name = name;
        this.link = link;
    }
}
