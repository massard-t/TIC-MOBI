package com.androiduniverse.coquardmassard.androiduniverse;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by alexiscoquard on 17/04/2017.
 */

public interface ApiInterface {
    @GET("chart/0/tracks")
    Call<TracksChart> getTopRatedTracks();
    //Call<TracksChart> getTopRatedTracks(@Query("api_key") String apiKey);

    @GET("chart/0/artists")
    Call<ArtistsChart> getTopRatedArtists();
    //Call<ArtistsChart> getTopRatedArtists(@Query("api_key") String apiKey);

    @GET("chart/0/albums")
    Call<AlbumsChart> getTopRatedAlbums();
    //Call<AlbumsChart> getTopRatedAlbums(@Query("api_key") String apiKey);

    @GET("album/{id}/tracks")
    Call<TracksChart> getAlbumTracklist(@Path("id") int id);
}
