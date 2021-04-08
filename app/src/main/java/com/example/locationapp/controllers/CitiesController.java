package com.example.locationapp.controllers;

import java.util.List;

import com.example.locationapp.model.City;
import com.example.locationapp.services.WeatherService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CitiesController implements Callback<City> {
    static final String BASE_URL = "https://api.openweathermap.org";
    static final String APP_ID = "54ccc7c8ff950ea11fe65d9248d4b4b0";

    public void start() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        WeatherService weatherService = retrofit.create(WeatherService.class);

        //Call<City> call = weatherService.getCityById("2735943", APP_ID);
        Call<City> call = weatherService.getCityByName("London", APP_ID);
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<City> call, Response<City> response) {
        if(response.isSuccessful()) {
            City city = response.body();
            System.out.println(city);
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<City> call, Throwable t) {
        t.printStackTrace();
    }
}
