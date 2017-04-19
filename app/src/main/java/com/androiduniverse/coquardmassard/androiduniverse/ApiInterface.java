package com.androiduniverse.coquardmassard.androiduniverse;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by alexiscoquard on 17/04/2017.
 */

public interface ApiInterface {
    @GET("0/tracks")
    Call<TracksChart> getTopRatedTracks(@Query("api_key") String apiKey);

    @GET("0/artists")
    Call<ArtistsChart> getTopRatedArtists(@Query("api_key") String apiKey);

    @GET("0/albums")
    Call<AlbumsChart> getTopRatedAlbums(@Query("api_key") String apiKey);
}
