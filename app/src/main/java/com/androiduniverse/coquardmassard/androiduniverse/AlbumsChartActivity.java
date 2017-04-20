package com.androiduniverse.coquardmassard.androiduniverse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class AlbumsChartActivity extends AppCompatActivity implements MetaPresenter.View {

    public ListView albumschartlistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MetaPresenter presenter = new MetaPresenter(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums_chart);
        albumschartlistview = (ListView) findViewById(R.id.AlbumsListView);
        presenter.askAlbumsChart();
    }

    @Override
    public void updateList(List<String> albums) {
        Log.i("AlbumsChartActivity", "Updating albums");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, albums);
        albumschartlistview.setAdapter(adapter);
    }
}
