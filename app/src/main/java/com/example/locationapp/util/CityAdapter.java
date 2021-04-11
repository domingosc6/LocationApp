package com.example.locationapp.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.locationapp.R;
import com.example.locationapp.model.City;
import com.example.locationapp.services.RetrieveCities;
import com.example.locationapp.services.RetrievePictures;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends ArrayAdapter<City> {
    public CityAdapter(Context context, ArrayList<City> pointOfInterests) {
        super(context, 0, pointOfInterests);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        City city = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.city_row, parent, false);
        }
        if (city != null) {
            TextView tvCityName = convertView.findViewById(R.id.city_row_name);
            TextView tvCityCurrTemp = convertView.findViewById(R.id.city_row_current_temp);
            ImageView imageView = convertView.findViewById(R.id.city_row_weather_icon);

            tvCityName.setText(city.getName());
            tvCityCurrTemp.setText(city.getCurrTempCelsius());
            RetrievePictures retrievePictures = new RetrievePictures(imageView);
            String url = city.getUrlIcon();
            retrievePictures.execute(url);
        }

        return convertView;
    }


}