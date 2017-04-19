package com.androiduniverse.coquardmassard.androiduniverse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ArtistsChartActivity extends AppCompatActivity implements MetaPresenter.View {

    private static final String TAG = MainActivity.class.getSimpleName();
    private final static String API_KEY = "frZWMPA11XchelzfulMkqCVZEaBFmE67eFrkgGfPYzgyIvckXts";


    public ListView artistsChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MetaPresenter presenter = new MetaPresenter(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artists_chart);
        artistsChartView = (ListView) findViewById(R.id.ArtistListView);
        presenter.askAPI();
    }

    @Override
    public void updateList(List<String> artists) {
        Log.i("ArtistsChartActivity", "Updating artists");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, artists);
        artistsChartView.setAdapter(adapter);
        //adapter.notifyDataSetChanged();
    }
}
