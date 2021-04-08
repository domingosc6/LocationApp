package com.example.locationapp.repositories;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import com.example.locationapp.model.City;
import com.example.locationapp.services.WeatherService;

public class CityRepository {
    private WeatherService weatherService;

    public CityRepository(
            WeatherService weatherService
    ) {
        this.weatherService = weatherService;
    }

    public Call<City> getUserById(String id) {
        return weatherService.getCityById(id,"");
    }
}
