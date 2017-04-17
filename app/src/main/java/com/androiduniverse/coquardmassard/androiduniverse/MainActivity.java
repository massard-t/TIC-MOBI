package com.androiduniverse.coquardmassard.androiduniverse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private final static String API_KEY = "frZWMPA11XchelzfulMkqCVZEaBFmE67eFrkgGfPYzgyIvckXts";

    ListView myListView;

    String[] prenoms = new String[]{"test1", "test2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myListView = (ListView) findViewById(R.id.listView);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<TracksChart> call = apiService.getTopRatedTracks(API_KEY);

        call.enqueue(new Callback<TracksChart>() {
            public void onResponse(Call<TracksChart>call, Response<TracksChart> response) {
                TracksChart trackschart = response.body();
                //Log.d(TAG, "Number of movies received: " + tracks.size());
            }

            public void onFailure(Call<TracksChart>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, prenoms);
        myListView.setAdapter(adapter);
    }
}