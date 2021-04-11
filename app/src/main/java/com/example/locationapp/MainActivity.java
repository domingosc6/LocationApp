package com.example.locationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.locationapp.controllers.CitiesController;
import com.example.locationapp.model.City;
import com.example.locationapp.util.CityAdapter;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LocationListener {
    private ListView citiesListLv;
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected int TAG_CODE_PERMISSION_LOCATION = 0;
    protected CitiesController controller;
    ArrayList<City> listOfCities = new ArrayList<>();
    private CityAdapter cityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller = new CitiesController();

        try {
            listOfCities = controller.getCitiesForMainActivity();
        } catch (IOException e) {
            listOfCities = null;
        }

        CityAdapter cityAdapter;
        citiesListLv = (ListView) findViewById(R.id.cities_list);
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup)inflater.inflate(R.layout.list_header, citiesListLv, false);
        citiesListLv.addHeaderView(header, null, false);
        cityAdapter = new CityAdapter(MainActivity.this, listOfCities);
        citiesListLv.setAdapter(cityAdapter);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION },
                    TAG_CODE_PERMISSION_LOCATION);
            setListAndAdapters();
            return;
        }else {
            getLocation();
        }

    }

    private void setListAndAdapters(){
        cityAdapter = new CityAdapter(MainActivity.this, listOfCities);
        citiesListLv.setAdapter(cityAdapter);

        citiesListLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent1 = new Intent(MainActivity.this, CityActivity.class);
                intent1.putExtra("City", (Serializable) listOfCities.get(position-1));
                startActivity(intent1);
            }
        });
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        double currentLatitude = location.getLatitude();
        double currentLongitude = location.getLongitude();

        City locationCity = controller.getCityFromCurrentLocation(String.valueOf(currentLatitude), String.valueOf(currentLongitude));
        listOfCities.add(locationCity);
        setListAndAdapters();

        locationManager.removeUpdates(this);

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
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
            setListAndAdapters();
        }
        return;
    }

    @SuppressLint("MissingPermission")
    private void getLocation() {
        locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, this, null);
    }

}