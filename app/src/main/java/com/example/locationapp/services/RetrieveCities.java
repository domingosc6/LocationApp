package com.example.locationapp.services;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.example.locationapp.model.City;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrieveCities extends AsyncTask<String, Void, City> {

    private Exception exception;
    static final String BASE_URL = "https://api.openweathermap.org";
    static final String APP_ID = "54ccc7c8ff950ea11fe65d9248d4b4b0";
    @Override
    protected City doInBackground(String... cityName) {
        City city;
        try {
            WeatherService weatherService = prepareCalls();
            Call<City> call = null;
            if (cityName[0] == "byName") {
                call = weatherService.getCityByName(cityName[1], APP_ID);
            } else if (cityName[0] == "byLocation") {
                call = weatherService.getCityByCoord(cityName[1], cityName[2], APP_ID);
            }
            Response<City> response = call.execute();
            if(response.isSuccessful()) {
                city = response.body();
                System.out.println(city.getName());
                System.out.println(city.getSunriseFormatted());
                System.out.println(city.getSunsetFormatted());
            } else {
                city = null;
            }
        } catch (Exception e) {
            this.exception = e;
            return null;
        }
        return city;
    }

    public static WeatherService prepareCalls(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        WeatherService weatherService = retrofit.create(WeatherService.class);

        return weatherService;
    }
}
