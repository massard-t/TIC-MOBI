package com.androiduniverse.coquardmassard.androiduniverse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.*;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TracksChartActivity extends AppCompatActivity implements TracksChartPresenter.View {

    private static final String TAG = MainActivity.class.getSimpleName();
    private final static String API_KEY = "frZWMPA11XchelzfulMkqCVZEaBFmE67eFrkgGfPYzgyIvckXts";


    public ListView tracksChartView;
    List<String> tracks = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TracksChartPresenter presenter = new TracksChartPresenter(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracks_chart);
        tracksChartView = (ListView) findViewById(R.id.TracksListView);
        presenter.askAPI();
    }

    public void updateTracklist(List<String> tracks) {
        Log.i("updateTracklist", "updating tracks");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, tracks);
        Log.i("updateTracklist", "after creating adapter");
        tracksChartView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}
