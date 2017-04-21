package com.androiduniverse.coquardmassard.androiduniverse;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    String albumShareLink = null;

    ListView albumTracklistListView;
    TextView albumTitleTextView;
    ImageView albumCoverImageView;

    Button shareButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        albumTracklistListView = (ListView) findViewById(R.id.AlbumTracklistView);
        albumTitleTextView = (TextView) findViewById(R.id.albumTitle);
        albumCoverImageView = (ImageView) findViewById(R.id.albumCover);
        shareButton = (Button) findViewById(R.id.shareButton);

        Bundle extras = getIntent().getExtras();

        this.albumId = extras.getInt("id");
        this.albumTitle = extras.getString("title");
        this.albumCover = extras.getString("cover");
        this.albumArtist = extras.getString("artist");
        this.albumShareLink = extras.getString("shareLink");

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, albumShareLink);
//                intent.putExtra(android.content.Intent.EXTRA_TITLE, "Découvrez la bière "+beer.name);
//                intent.setType("image/png");
//                intent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                startActivity(Intent.createChooser(intent, "Share"));
            }
        });

        albumTitleTextView.setText(this.albumTitle + "  -  " + this.albumArtist);
        loadImage();


        final AlbumDetailsPresenter presenter = new AlbumDetailsPresenter(this, albumId);
        presenter.askTracklist();

        albumTracklistListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.playPreview(position);
            }
        });
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
