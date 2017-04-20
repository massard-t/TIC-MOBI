package com.androiduniverse.coquardmassard.androiduniverse;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by alexiscoquard on 19/04/2017.
 */

public class CustomList extends ArrayAdapter<String> {
    private final Activity context;
    private final List<String> artists;
    private final List<String> pictures;

    public CustomList(Activity context, List<String> artists, List<String> pictures) {
        super(context, R.layout.picture_list, artists);
        this.context = context;
        this.artists = artists;
        this.pictures = pictures;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.picture_list, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);

        final ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        //txtTitle.setText(web[position]);
        txtTitle.setText(artists.get(position));

        URL url = null;
        try {
            url = new URL(pictures.get(position));
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
                        //bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                        imageView.setImageBitmap(bmp[0]);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        return rowView;
    }
}