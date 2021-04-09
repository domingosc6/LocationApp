package com.example.locationapp.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.locationapp.R;
import com.example.locationapp.model.City;

import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends ArrayAdapter<City> {
    public CityAdapter(Context context, ArrayList<City> pointOfInterests) {
        super(context, 0, pointOfInterests);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        City city = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.city_row, parent, false);
        }
        // Lookup view for data population
        TextView tvCityName = convertView.findViewById(R.id.city_row_name);
        TextView tvCityCurrTemp = convertView.findViewById(R.id.city_row_current_temp);

        // Populate the data into the template view using the data object
        tvCityName.setText(city.getName());
        tvCityCurrTemp.setText(city.getCurrTempCelsius());
        // Return the completed view to render on screen
        return convertView;
    }

}