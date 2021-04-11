package com.example.locationapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.locationapp.model.City;
import com.example.locationapp.services.RetrievePictures;

public class CityActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_activity);
        City city = (City) getIntent().getSerializableExtra("City");
        TextView cityNameTextView = (TextView) findViewById(R.id.city_name);
        TextView cityCurrTempTextView = (TextView) findViewById(R.id.city_current_temp);
        TextView cityMaxTempTextView = (TextView) findViewById(R.id.city_max_temp);
        TextView cityMinTempTextView = (TextView) findViewById(R.id.city_min_temp);
        ImageView cityWeatherIconView = (ImageView) findViewById(R.id.city_weather_icon);
        TextView cityAirPressureTextView = (TextView) findViewById(R.id.city_air_pressure);
        TextView cityAirHumidityTextView = (TextView) findViewById(R.id.city_air_humidity);
        TextView citySunriseTextView = (TextView) findViewById(R.id.city_sunrise);
        TextView citySunsetTextView = (TextView) findViewById(R.id.city_sunset);

        cityNameTextView.setText(city.getName());
        cityCurrTempTextView.setText(city.getCurrTempCelsius());
        cityMaxTempTextView.setText(city.getMaxTempCelsius());
        cityMinTempTextView.setText(city.getMinTempCelsius());
        cityAirPressureTextView.setText(city.getAirPressure());
        cityAirHumidityTextView.setText(city.getAirHumidity());
        citySunriseTextView.setText(city.getSunriseFormatted());
        citySunsetTextView.setText(city.getSunsetFormatted());

        RetrievePictures retrievePictures = new RetrievePictures(cityWeatherIconView);
        String url = city.getUrlIcon();
        retrievePictures.execute(url);
    }
}
