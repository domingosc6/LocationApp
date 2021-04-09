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
    @SerializedName("coord")
    @Expose
    private CityLocation cityLocation;

    public String getName() {
        return name;
    }

    public String getCurrTempCelsius() {
        return this.getWeatherTemperature().getCurrTempCelsius();
    }

    public void setName(String name) {
        this.name = name;
    }

    final class WeatherTemperature {
        @SerializedName("temp_max")
        private String maxTemp;
        @SerializedName("temp_min")
        private String minTemp;
        @SerializedName("temp")
        private String currTemp;
        @SerializedName("feels_like")
        private String feelsTemp;
        @SerializedName("pressure")
        private String airPressure;
        @SerializedName("humidity")
        private String airHumidity;

        public String getCurrTemp() {
            return currTemp;
        }

        public String getMaxTemp() {
            return maxTemp;
        }

        public String getMinTemp() {
            return minTemp;
        }

        public String getFeelsTemp() {
            return feelsTemp;
        }

        public String getAirPressure() {
            return airPressure;
        }

        public String getAirHumidity() {
            return airHumidity;
        }

        public void setFeelsTemp(String feelsTemp) {
            this.feelsTemp = feelsTemp;
        }

        public void setAirPressure(String airPressure) {
            this.airPressure = airPressure;
        }

        public void setAirHumidity(String airHumidity) {
            this.airHumidity = airHumidity;
        }

        public String getCurrTempCelsius() {
            float currTempKelvin = Float.parseFloat(currTemp);
            float currTempCelsius = (float) (currTempKelvin - 273.15);
            return String.valueOf(currTempCelsius);
        }

        public String getMaxTempCelsius() {
            float maxTempKelvin = Float.parseFloat(maxTemp);
            float maxTempCelsius = (float) (maxTempKelvin - 273.15);
            return String.valueOf(maxTempCelsius);
        }

        public String getMinTempCelsius() {
            float minTempKelvin = Float.parseFloat(minTemp);
            float minTempCelsius = (float) (minTempKelvin - 273.15);
            return String.valueOf(minTempCelsius);
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

    final class CityLocation {
        @SerializedName("lon")
        private String longitude;
        @SerializedName("lat")
        private String latitude;

        public String getLongitude() {
            return longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }
    }

    public WeatherTemperature getWeatherTemperature() {
        return weatherTemperature;
    }

    public CityLocation getCityLocation() {
        return cityLocation;
    }

    public void setWeatherTemperature(WeatherTemperature weatherTemperature) {
        this.weatherTemperature = weatherTemperature;
    }

    public void setCityLocation(CityLocation cityLocation) {
        this.cityLocation = cityLocation;
    }

    @NonNull
    @Override
    public String toString() {
        return "City Name: "+ this.getName() + "\nCurrent Temperature: " +
                this.getWeatherTemperature().getCurrTempCelsius() + "\nMaximum Temperature: " +
                this.getWeatherTemperature().getMaxTempCelsius() + "\nMinimum Temperature: " +
                this.getWeatherTemperature().getMinTempCelsius() + "\nLongitude: " + this.getCityLocation().getLongitude() + "\n";
    }
}
