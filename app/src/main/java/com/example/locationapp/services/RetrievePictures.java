package com.example.locationapp.services;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

public class RetrievePictures extends AsyncTask<String, Void, Bitmap> {
    ImageView cityWeatherIcon;

    public RetrievePictures(ImageView cityWeatherIcon) {
        this.cityWeatherIcon = cityWeatherIcon;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        cityWeatherIcon.setImageBitmap(result);
    }
}