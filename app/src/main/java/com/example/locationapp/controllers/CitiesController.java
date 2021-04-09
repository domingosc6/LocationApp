package com.example.locationapp.controllers;

import android.os.AsyncTask;

import androidx.arch.core.util.Function;
import androidx.core.util.Consumer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.example.locationapp.model.City;
import com.example.locationapp.services.WeatherService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CitiesController {
    static final String BASE_URL = "https://api.openweathermap.org";
    static final String APP_ID = "54ccc7c8ff950ea11fe65d9248d4b4b0";
    static final ArrayList<String> citiesNames = new ArrayList<>(Arrays.asList("Lisbon", "Madrid", "Paris", "Berlin", "Copenhagen", "Rome", "London", "Dublin", "Prague", "Vienna"));
    ArrayList<City> listOfCities;

    public ArrayList<City> getCitiesForMainActivity() throws IOException {
        //inicializar API e lista
        listOfCities = new ArrayList<>();


        for (int i = 0; i < citiesNames.size(); i++) {
            RetrieveCities retrieveCities = new RetrieveCities();
            City city = new City();
            try {
                city = retrieveCities.execute(citiesNames.get(i)).get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (null != city) {
                listOfCities.add(city);
            }
        }
        return listOfCities;
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

    public String getCityFromCurrentLocation(Retrofit retrofit) {
        //completar depois
        return "";
    }

    public static class RetrieveCities extends AsyncTask<String, Void, City> {

        private Exception exception;
        @Override
        protected City doInBackground(String... cityName) {
            City city;
            try {
                WeatherService weatherService = prepareCalls();
                Call<City> call = null;
                call = weatherService.getCityByName(cityName[0], APP_ID);
                Response<City> response = call.execute();
                if(response.isSuccessful()) {
                    city = response.body();
                } else {
                    city = null;
                    System.out.println(response.errorBody());
                }
            } catch (Exception e) {
                this.exception = e;
                city = null;
                return null;
            }
            return city;
        }
    }
}
