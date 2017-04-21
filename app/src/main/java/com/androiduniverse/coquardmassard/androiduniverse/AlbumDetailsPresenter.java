package com.androiduniverse.coquardmassard.androiduniverse;

import android.media.MediaPlayer;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alexiscoquard on 20/04/2017.
 */

public class AlbumDetailsPresenter {

    private boolean isPlaying = false;
    private int previewRelativeId = -1;
    MediaPlayer mp = null;

    private AlbumDetailsActivity albumView;
    ApiInterface apiService = null;
    TracksChart tracklist = null;
    int albumId = 0;
    List<String> titles = new ArrayList<>();

    public AlbumDetailsPresenter(AlbumDetailsActivity albumView, int albumId) {
        apiService = ApiClient.getClient().create(ApiInterface.class);
        this.albumView = albumView;
        this.albumId = albumId;
    }

    void askTracklist() {
        callTracks(albumView, apiService, albumId);
    };

    void callTracks(AlbumDetailsActivity v, ApiInterface apiService, int albumId) {
        Call<TracksChart> call =  apiService.getAlbumTracklist(albumId);
        call.enqueue(new Callback<TracksChart>() {
            @Override
            public void onResponse(Call<TracksChart>call, Response<TracksChart> response) {
                tracklist = new TracksChart(response.body().tracks);

                for (int i = 0 ; i < tracklist.tracks.size() ; i++) {
                    titles.add(i + 1 + "  -  " + tracklist.tracks.get(i).getTitle());
                }

                Log.d("onResponseTrackAPI", "Number of elements received: " + tracklist.tracks.size());

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(albumView, android.R.layout.simple_list_item_1, titles);
                albumView.updateAlbumTracklist(adapter);
            }
            @Override
            public void onFailure(Call<TracksChart>call, Throwable t) {
                // Log error here since request failed
                Log.e("albumpresenter", t.toString());
            }
        });
    }

    public void playPreview(int position) {
        if (mp != null) {
            mp.release();
        }
        if (this.previewRelativeId != position || isPlaying == false) {
            try {
                isPlaying = true;
                this.previewRelativeId = position;
                mp = new MediaPlayer();
                mp.setDataSource(tracklist.tracks.get(position).getPreview());
                mp.prepare();
                mp.start();
            } catch (IOException e) {
                Log.e("audio", "prepare() failed");
            }
        }
        else {
            mp.release();
            isPlaying = false;
        }
    }

    interface AlbumView {
        void updateAlbumTracklist(ArrayAdapter<String> adapter);
    }
}
