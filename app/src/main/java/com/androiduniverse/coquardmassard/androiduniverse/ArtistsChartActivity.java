package com.androiduniverse.coquardmassard.androiduniverse;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

public class ArtistsChartActivity extends AppCompatActivity implements MetaPresenter.ArtistView {

    private static final String TAG = MainActivity.class.getSimpleName();
    private final static String API_KEY = "frZWMPA11XchelzfulMkqCVZEaBFmE67eFrkgGfPYzgyIvckXts";


    public ListView artistsChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MetaPresenter presenter = new MetaPresenter(this);
        super.onCreate(savedInstanceState);

        Toolbar toolbar = (Toolbar) findViewById(R.id.artisttoolbar);
        setSupportActionBar(toolbar);

        setContentView(R.layout.activity_artists_chart);
        artistsChartView = (ListView) findViewById(R.id.ArtistListView);
        presenter.askArtistsChart();
    }

    @Override
    public void updateList(CustomList adapter) {
        //CustomList adapter = new CustomList(this, artist, images);
        artistsChartView.setAdapter(adapter);
        Log.d("test", "adapter set");
    }
}
