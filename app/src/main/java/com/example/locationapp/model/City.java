package com.example.locationapp.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class City {
    @SerializedName("name")
    private String name;
    @SerializedName("main")
    @Expose
    private WeatherTemperature weatherTemperature;
    final class WeatherTemperature {
        @SerializedName("temp_max")
        private String maxTemp;
        @SerializedName("temp_min")
        private String minTemp;
        @SerializedName("temp")
        private String currTemp;

        public String getCurrTemp() {
            return currTemp;
        }

        public String getMaxTemp() {
            return maxTemp;
        }

        public String getMinTemp() {
            return minTemp;
        }

        public void setCurrTemp(String currTemp) {
            this.currTemp = currTemp;
        }

        public void setMaxTemp(String maxTemp) {
            this.maxTemp = maxTemp;
        }

        public void setMinTemp(String minTemp) {
            this.minTemp = minTemp;
        }
    }

    public WeatherTemperature getWeatherTemperature() {
        return weatherTemperature;
    }

    public void setWeatherTemperature(WeatherTemperature weatherTemperature) {
        this.weatherTemperature = weatherTemperature;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return "Nome: "+ this.getName() + "\nTemperatura Atual: " + this.getWeatherTemperature().getCurrTemp() + "\nTemperatura Máxima: " + this.getWeatherTemperature().getMaxTemp() + "\nTemperatura Mínima: " + this.getWeatherTemperature().getMinTemp();
    }
}
