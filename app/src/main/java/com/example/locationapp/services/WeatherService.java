package com.example.locationapp.services;

import com.example.locationapp.model.City;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("/data/2.5/weather")
    Call<City> getCityById(@Query("id") String id, @Query("appid") String appid);
    @GET("/data/2.5/weather")
    Call<City> getCityByName(@Query("q") String name, @Query("appid") String appid);
}
