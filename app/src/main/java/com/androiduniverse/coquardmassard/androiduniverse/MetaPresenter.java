package com.androiduniverse.coquardmassard.androiduniverse;

import android.app.Activity;
import android.media.Image;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alexiscoquard on 17/04/2017.
 */

class MetaPresenter {

    private static final String TAG = MainActivity.class.getSimpleName();

    private List<String> elements = new ArrayList<String>();
    private TracksChart trackschart;
    private ArtistsChart artistchart;
    private AlbumsChart  albumschart;

    View view;
    ArtistView artistView = null;
    ApiInterface apiService = null;

    MetaPresenter(View view) {
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Log.i("MetaPresenter INIT", view.toString());
        this.view = view;
    }

    MetaPresenter(ArtistView view) {
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Log.i("MetaPresenter INIT", view.toString());
        this.artistView = view;
    }

    void askTracksChart() {
        callTracks(this.view, apiService);
    }

    void askArtistsChart() {
        callArtists(this.artistView, apiService);
    }

    void askAlbumsChart() {
        callAlbums(this.view, apiService);
    }

    void callArtists(ArtistView v, ApiInterface apiService) {
        Call<ArtistsChart> call = apiService.getTopRatedArtists();
        call.enqueue(new Callback<ArtistsChart>() {
            @Override
            public void onResponse(Call<ArtistsChart> call, Response<ArtistsChart> response) {
                artistchart = new ArtistsChart(response.body().artists);

                List<String> images = new ArrayList<>();

                for (int i = 0; i < artistchart.artists.size(); i++) {
                    elements.add(artistchart.artists.get(i).getName());
                    images.add(artistchart.artists.get(i).getPicture());
                }

                Log.d("onResponseArtistAPI", "Number of elements received: " + artistchart.artists.size());
                CustomList adapter = new CustomList((Activity) artistView, elements, images);
                artistView.updateList(adapter);
            }

            @Override
            public void onFailure(Call<ArtistsChart> call, Throwable t) {
                Log.e(TAG, t.toString());

            }
        });
    }

    void callTracks(View v, ApiInterface apiService) {
        Call<TracksChart>call =  apiService.getTopRatedTracks();
        call.enqueue(new Callback<TracksChart>() {
            @Override
            public void onResponse(Call<TracksChart>call, Response<TracksChart> response) {
                trackschart = new TracksChart(response.body().tracks);

                for (int i = 0 ; i < trackschart.tracks.size() ; i++) {
                    elements.add(trackschart.tracks.get(i).getTitle() + "  -  " + trackschart.tracks.get(i).getArtist().getName());
                }

                Log.d("onResponseTrackAPI", "Number of elements received: " + trackschart.tracks.size());

                view.updateList(elements);
            }
            @Override
            public void onFailure(Call<TracksChart>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }

    void callAlbums(View v,  ApiInterface apiService) {
        Call<AlbumsChart>call = apiService.getTopRatedAlbums();
        call.enqueue(new Callback<AlbumsChart>() {
            @Override
            public void onResponse(Call<AlbumsChart> call, Response<AlbumsChart> response) {
                albumschart = new AlbumsChart(response.body().albums);

                for (int i = 0 ; i < albumschart.albums.size() ; i++) {
                    elements.add(albumschart.albums.get(i).getTitle() + "  -  " + albumschart.albums.get(i).getArtist().getName());
                }

                Log.d("onResponseAlbumAPI", "number of elements received: " + albumschart.albums.size());

                view.updateList(elements);
            }

            @Override
            public void onFailure(Call<AlbumsChart> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    interface View {
        void updateList(List<String> elements);
    }

    interface AlbumView {
        void updateList(List<String> album, List<Image> images);
    }

    interface ArtistView {
        //void updateList(List<String> artist, List<String> images);
        void updateList(CustomList list);
    }
}
