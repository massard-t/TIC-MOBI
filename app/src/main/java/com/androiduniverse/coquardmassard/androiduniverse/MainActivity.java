package com.androiduniverse.coquardmassard.androiduniverse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {
    ListView myListView;

    String[] displayHeaders = new String[]{"Tracks", "Albums", "Artists"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myListView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, displayHeaders);
        myListView.setAdapter(adapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
                Intent intent;
                intent = null;
                switch (position) {
                    case 0:
                        intent = new Intent(getApplicationContext(), TracksChartActivity.class);
                        //based on item add info to intent
                        break;
                    case 1:
                        intent = new Intent(getApplicationContext(), AlbumsChartActivity.class);
                        break;
                    case 2:
                        intent = new Intent(getApplicationContext(), ArtistsChartActivity.class);
                        break;
                }
                if (null != intent) {
                    startActivity(intent);
                }
            }
        });
    }
}