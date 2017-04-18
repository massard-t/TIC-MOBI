package com.androiduniverse.coquardmassard.androiduniverse;

import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alexiscoquard on 17/04/2017.
 */

class TracksChartPresenter {

    private static final String TAG = MainActivity.class.getSimpleName();
    private final static String API_KEY = "frEnz2EKsRbX5tzWQDJJAsmwpXh0HtOjJBFRjOXzORjDJBGst5";

    private List<String> tracks = new ArrayList<String>();
    private TracksChart trackschart;

    View view;

    TracksChartPresenter(View view) {
        this.view = view;
    }

    void askAPI() {
        Log.i(TAG, "Inside askAPI, preparing call to the deezer API");
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<TracksChart> call = apiService.getTopRatedTracks(API_KEY);
        call.enqueue(new Callback<TracksChart>() {
            public void onResponse(Call<TracksChart>call, Response<TracksChart> response) {
                trackschart = new TracksChart(response.body().tracks);

                for (int i = 0 ; i < trackschart.tracks.size() ; i++) {
                    tracks.add(trackschart.tracks.get(i).getTitle());
                }

                Log.d(TAG, "" + trackschart.tracks.size());

                //Log.d(TAG, "Number of movies received: " + tracks.size());
            }

            public void onFailure(Call<TracksChart>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
        view.updateTracklist(tracks);
    }

    interface View {
        void updateTracklist(List<String> tracks);
    }
}
