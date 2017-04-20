package com.androiduniverse.coquardmassard.androiduniverse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TracksChartActivity extends AppCompatActivity implements MetaPresenter.View {

    private static final String TAG = MainActivity.class.getSimpleName();
    private final static String API_KEY = "frZWMPA11XchelzfulMkqCVZEaBFmE67eFrkgGfPYzgyIvckXts";

    private List<Track> tracks;

    public ListView tracksChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MetaPresenter presenter = new MetaPresenter(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracks_chart);
        tracksChartView = (ListView) findViewById(R.id.TracksListView);
        presenter.askTracksChart();
    }

    public void updateList(List<String> tracks) {
        Log.i("updateTracklist", "updating tracks");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, tracks);
        Log.i("updateTracklist", "after creating adapter");
        tracksChartView.setAdapter(adapter);
    }
}

