package com.example.locationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.locationapp.controllers.CitiesController;
import com.example.locationapp.model.City;
import com.example.locationapp.util.CityAdapter;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LocationListener {
    private ListView citiesListLv;
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected int TAG_CODE_PERMISSION_LOCATION = 0;
    protected double currentLatitude = 0;
    protected double currentLongitude = 0;
    protected CitiesController controller;
    ArrayList<City> listOfCities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new CitiesController();


        try {
            listOfCities = controller.getCitiesForMainActivity();
        } catch (IOException e) {
            listOfCities = null;
            e.printStackTrace();
        }

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION },
                    TAG_CODE_PERMISSION_LOCATION);
            return;
        }else {
            getLocation();
        }

        CityAdapter cityAdapter;
        citiesListLv = (ListView) findViewById(R.id.cities_list);
        cityAdapter = new CityAdapter(MainActivity.this, listOfCities);
        citiesListLv.setAdapter(cityAdapter);

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        currentLatitude = location.getLatitude();
        currentLongitude = location.getLongitude();
        City locationCity = controller.getCityFromCurrentLocation(String.valueOf(currentLatitude), String.valueOf(currentLongitude));
        System.out.println("Obtive cidade por localizacao");
        System.out.println(currentLatitude);
        System.out.println(currentLongitude);
        listOfCities.add(locationCity);
        locationManager.removeUpdates(this);
        CityAdapter cityAdapter;
        citiesListLv = (ListView) findViewById(R.id.cities_list);
        cityAdapter = new CityAdapter(MainActivity.this, listOfCities);
        citiesListLv.setAdapter(cityAdapter);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        Log.d("Latitude","disable");
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 0, this);
        } else {
            finish();
        }
        return;
    }

    @SuppressLint("MissingPermission")
    private void getLocation() {
        locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, this, null);
    }
}