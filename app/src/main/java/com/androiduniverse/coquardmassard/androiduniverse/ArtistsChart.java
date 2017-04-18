package com.androiduniverse.coquardmassard.androiduniverse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by theo on 18/04/17.
 */

public class ArtistsChart {
    @SerializedName("data")
    List<Artist> artists;

    ArtistsChart(List<Artist> artists) {
        this.artists = artists;
    }
}
