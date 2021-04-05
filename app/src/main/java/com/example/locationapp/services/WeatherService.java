package com.example.locationapp.services;

import com.example.locationapp.model.City;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("/cities/{id}")
    Call<City> getCity(String id);
}
