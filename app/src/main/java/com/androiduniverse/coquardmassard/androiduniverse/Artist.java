package com.androiduniverse.coquardmassard.androiduniverse;
import android.media.Image;

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
    @SerializedName("picture")
    private String picture;

    private Image image;

    public Artist(int id, String name, String link, String picture) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }
    public String getPicture() { return picture; }
}
