package com.example.locationapp.controllers;

import android.location.LocationListener;
import android.location.LocationManager;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import com.example.locationapp.model.City;
import com.example.locationapp.services.RetrieveCities;


public class CitiesController extends android.app.Application {
    private static final ArrayList<String> citiesNames = new ArrayList<>(Arrays.asList("Lisbon", "Madrid", "Paris", "Berlin", "Copenhagen", "Rome", "London", "Dublin", "Prague", "Vienna"));
    ArrayList<City> listOfCities;

    public ArrayList<City> getCitiesForMainActivity() throws IOException {
        //inicializar API e lista
        listOfCities = new ArrayList<>();

        for (int i = 0; i < citiesNames.size(); i++) {
            RetrieveCities retrieveCities = new RetrieveCities();
            City city = new City();
            try {
                city = retrieveCities.execute("byName", citiesNames.get(i)).get();
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



    public City getCityFromCurrentLocation(String lat, String lon) {

        RetrieveCities retrieveCities = new RetrieveCities();
        City city = new City();
        try {
            city = retrieveCities.execute("byLocation", lat, lon).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return city;
    }

}
