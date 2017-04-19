package com.androiduniverse.coquardmassard.androiduniverse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by alexiscoquard on 19/04/2017.
 */

public class AlbumsChart {
    @SerializedName("data")
    List<Album> albums;

    public AlbumsChart(List<Album> albums) { this.albums = albums; }
}
