package com.example.locationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.locationapp.controllers.CitiesController;
import com.example.locationapp.model.City;
import com.example.locationapp.util.CityAdapter;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView citiesListLv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CitiesController controller = new CitiesController();

        ArrayList<City> listOfCities = null;
        try {
            listOfCities = controller.getCitiesForMainActivity();
        } catch (IOException e) {
            listOfCities = null;
            e.printStackTrace();
        }

        CityAdapter cityAdapter;
        citiesListLv = (ListView) findViewById(R.id.cities_list);
        cityAdapter = new CityAdapter(MainActivity.this, listOfCities);
        citiesListLv.setAdapter(cityAdapter);

    }
}