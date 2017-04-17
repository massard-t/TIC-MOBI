package com.androiduniverse.coquardmassard.androiduniverse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TracksChartActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private final static String API_KEY = "frZWMPA11XchelzfulMkqCVZEaBFmE67eFrkgGfPYzgyIvckXts";

    public ListView tracksChartView;
    List<String> tracks = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracks_chart);

        tracksChartView = (ListView) findViewById(R.id.TracksListView);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<TracksChart> call = apiService.getTopRatedTracks(API_KEY);
        call.enqueue(new Callback<TracksChart>() {
            public void onResponse(Call<TracksChart>call, Response<TracksChart> response) {
                TracksChart trackschart = new TracksChart(response.body().tracks);

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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, tracks);
        tracksChartView.setAdapter(adapter);
    }
}
