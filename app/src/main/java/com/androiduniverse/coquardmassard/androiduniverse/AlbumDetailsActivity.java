package com.androiduniverse.coquardmassard.androiduniverse;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class AlbumDetailsActivity extends AppCompatActivity implements AlbumDetailsPresenter.AlbumView {

    int albumId = 0;
    String albumTitle = null;
    String albumCover = null;
    String albumArtist = null;

    ListView albumTracklistListView;
    TextView albumTitleTextView;
    ImageView albumCoverImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_details);
        albumTracklistListView = (ListView) findViewById(R.id.AlbumTracklistView);
        albumTitleTextView = (TextView) findViewById(R.id.albumTitle);
        albumCoverImageView = (ImageView) findViewById(R.id.albumCover);

        Bundle extras = getIntent().getExtras();

        this.albumId = extras.getInt("id");
        this.albumTitle = extras.getString("title");
        this.albumCover = extras.getString("cover");
        this.albumArtist = extras.getString("artist");
        albumTitleTextView.setText(this.albumTitle + "  -  " + this.albumArtist);
        loadImage();


        AlbumDetailsPresenter presenter = new AlbumDetailsPresenter(this, albumId);
        presenter.askTracklist();
    }

    @Override
    public void updateAlbumTracklist(ArrayAdapter<String> adapter) {
        albumTracklistListView.setAdapter(adapter);
    }

    private void loadImage() {
        URL url = null;
        try {
            url = new URL(this.albumCover);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        final Bitmap[] bmp = {null};


        final URL finalUrl = url;
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    try {
                        bmp[0] = BitmapFactory.decodeStream(finalUrl.openConnection().getInputStream());
                        albumCoverImageView.setImageBitmap(bmp[0]);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }
}
