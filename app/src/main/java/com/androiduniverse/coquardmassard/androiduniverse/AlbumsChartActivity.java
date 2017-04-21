package com.androiduniverse.coquardmassard.androiduniverse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AlbumsChartActivity extends AppCompatActivity implements MetaPresenter.AlbumView {

    public ListView albumschartlistview;
    AlbumsChart albumchart = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MetaPresenter presenter = new MetaPresenter(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums_chart);
        albumschartlistview = (ListView) findViewById(R.id.AlbumsListView);
        presenter.askAlbumsChart();

        albumschartlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), AlbumDetailsActivity.class);
                intent.putExtra("id", albumchart.albums.get(position).getId());
                intent.putExtra("title", albumchart.albums.get(position).getTitle());
                intent.putExtra("cover", albumchart.albums.get(position).getCover());
                intent.putExtra("artist", albumchart.albums.get(position).getArtist().getName());
                intent.putExtra("shareLink", albumchart.albums.get(position).getShareLink());
                startActivity(intent);
            }
        });

    }

    @Override
    public void updateList(CustomList list, AlbumsChart albumchart) {
        Log.i("AlbumsChartActivity", "Updating albums");
        this.albumchart = albumchart;
        albumschartlistview.setAdapter(list);
    }

}
