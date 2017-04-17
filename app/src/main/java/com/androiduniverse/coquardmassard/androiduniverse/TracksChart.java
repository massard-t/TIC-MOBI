package com.androiduniverse.coquardmassard.androiduniverse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by alexiscoquard on 17/04/2017.
 */

class TracksChart {
    @SerializedName("data")
    List<Track> tracks;

    TracksChart(List<Track> tracks) {
        this.tracks = tracks;
    }
}
