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

class MetaPresenter {

    private static final String TAG = MainActivity.class.getSimpleName();
    private final static String API_KEY = "frEnz2EKsRbX5tzWQDJJAsmwpXh0HtOjJBFRjOXzORjDJBGst5";

    private List<String> elements = new ArrayList<String>();
    private TracksChart trackschart;
    private ArtistsChart artistchart;

    View view;

    MetaPresenter(View view) {
        Log.i("MetaPresenter INIT", view.toString());
        this.view = view;
    }

    void askAPI() {
        Log.i(TAG, "Inside askAPI, preparing call to the deezer API");
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        switch (view.getClass().getSimpleName()) {
            case "TracksChartActivity":
                callTracks(this.view, apiService);
                break;
            case "ArtistsChartActivity":
                callArtists(this.view, apiService);
                break;
        }
    }

    void callArtists(View v, ApiInterface apiService) {
        Call<ArtistsChart> call = apiService.getTopRatedArtists(API_KEY);
        call.enqueue(new Callback<ArtistsChart>() {
            @Override
            public void onResponse(Call<ArtistsChart> call, Response<ArtistsChart> response) {
                artistchart = new ArtistsChart(response.body().artists);

                for (int i = 0; i < artistchart.artists.size(); i++) {
                    elements.add(artistchart.artists.get(i).getName());
                }
                Log.d("onResponseArtistAPI", "Number of elements received: " + artistchart.artists.size());
                view.updateList(elements);
            }

            @Override
            public void onFailure(Call<ArtistsChart> call, Throwable t) {
                Log.e(TAG, t.toString());

            }
        });
    }

    void callTracks(View v, ApiInterface apiService) {
        Call<TracksChart>call =  apiService.getTopRatedTracks(API_KEY);
        call.enqueue(new Callback<TracksChart>() {
            public void onResponse(Call<TracksChart>call, Response<TracksChart> response) {
                trackschart = new TracksChart(response.body().tracks);

                for (int i = 0 ; i < trackschart.tracks.size() ; i++) {
                    elements.add(trackschart.tracks.get(i).getTitle());
                }

                Log.d("onResponseTrackAPI", "Number of elements received: " + trackschart.tracks.size());

                view.updateList(elements);
            }

            public void onFailure(Call<TracksChart>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

    }

    interface View {
        void updateList(List<String> elements);
    }
}
