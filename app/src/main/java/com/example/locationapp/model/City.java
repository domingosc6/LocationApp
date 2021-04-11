package com.example.locationapp.model;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class City implements Serializable {
    @SerializedName("name")
    private String name;
    @SerializedName("timezone")
    private String timezone;
    @SerializedName("main")
    @Expose
    private WeatherTemperature weatherTemperature;
    @SerializedName("sys")
    @Expose
    private CityInfo cityInfo;
    @SerializedName("coord")
    @Expose
    private CityLocation cityLocation;
    @SerializedName("weather")
    @Expose
    private ArrayList<Object> weatherIcon;
    private String urlWeatherIcon;
    private String titleWeather;

    public String getName() {
        return name;
    }

    public String getTimezone() {
        return timezone;
    }

    public String getCurrTempCelsius() {
        return this.getWeatherTemperature().getCurrTempCelsius();
    }

    public String getMaxTempCelsius() {
        return this.getWeatherTemperature().getMaxTempCelsius();
    }

    public String getMinTempCelsius() {
        return this.getWeatherTemperature().getMinTempCelsius();
    }

    public String getAirPressure(){
        return String.format("%shPa", this.getWeatherTemperature().getAirPressure());
    }
    public String getAirHumidity(){
        return String.format("%s%%", this.getWeatherTemperature().getAirHumidity());
    }

    public Date getSunset() {
        long timeStamp = Long.valueOf(this.getCityInfo().getSunset()) + Long.valueOf(this.getTimezone());
        TimeZone currentTimeZone = TimeZone.getDefault();
        if (currentTimeZone.inDaylightTime(new Date())) {
            timeStamp-= 3600;
        }
        Date time = new Date((timeStamp * 1000));
        return time;
    }

    public String getSunsetFormatted() {
        Date sunset = this.getSunset();
        TimeZone currentTimeZone = TimeZone.getDefault();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        sdf.setTimeZone(currentTimeZone);
        return sdf.format(sunset);
    }

    public Date getSunrise() {
        long timeStamp = Long.valueOf(this.getCityInfo().getSunrise()) + Long.valueOf(this.getTimezone());
        TimeZone currentTimeZone = TimeZone.getDefault();
        if (currentTimeZone.inDaylightTime(new Date())) {
            timeStamp-= 3600;
        }
        Date time = new Date((timeStamp * 1000));
        return time;
    }

    public String getSunriseFormatted() {
        Date sunrise = this.getSunrise();
        TimeZone currentTimeZone = TimeZone.getDefault();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        sdf.setTimeZone(currentTimeZone);
        return sdf.format(sunrise);
    }

    public String getUrlIcon() {
        if (this.urlWeatherIcon == null) {
            LinkedTreeMap linkedTreeMap = (LinkedTreeMap) this.getWeatherIcon().get(0);
            String iconId = (String) linkedTreeMap.get("icon");
            String url = String.format("https://openweathermap.org/img/wn/%s@2x.png", iconId);
            this.urlWeatherIcon = url;
        }
        return this.urlWeatherIcon;
    }

    public String getTitleWeather() {
        if (this.titleWeather == null) {
            LinkedTreeMap linkedTreeMap = (LinkedTreeMap) this.getWeatherIcon().get(0);
            String mainWeather = (String) linkedTreeMap.get("main");
            this.titleWeather = mainWeather;
        }
        return this.titleWeather;
    }

    public WeatherTemperature getWeatherTemperature() {
        return weatherTemperature;
    }

    public CityLocation getCityLocation() {
        return cityLocation;
    }

    public ArrayList getWeatherIcon() {
        return weatherIcon;
    }

    public CityInfo getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(CityInfo cityInfo) {
        this.cityInfo = cityInfo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeatherTemperature(WeatherTemperature weatherTemperature) {
        this.weatherTemperature = weatherTemperature;
    }

    public void setCityLocation(CityLocation cityLocation) {
        this.cityLocation = cityLocation;
    }

    public void setWeatherIcon(ArrayList weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    final class WeatherTemperature implements Serializable{
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
            return String.format("%.1fº", currTempCelsius);
        }

        public String getMaxTempCelsius() {
            float maxTempKelvin = Float.parseFloat(maxTemp);
            float maxTempCelsius = (float) (maxTempKelvin - 273.15);
            return String.format("H:%.1fº", maxTempCelsius);
        }

        public String getMinTempCelsius() {
            float minTempKelvin = Float.parseFloat(minTemp);
            float minTempCelsius = (float) (minTempKelvin - 273.15);
            return String.format("L:%.1fº", minTempCelsius);
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

    final class CityLocation implements Serializable {
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
    final class CityInfo implements Serializable {
        @SerializedName("sunrise")
        private String sunrise;
        @SerializedName("sunset")
        private String sunset;
        @SerializedName("country")
        private String country;

        public String getSunrise() {
            return sunrise;
        }

        public String getSunset() {
            return sunset;
        }

        public String getCountry() {
            return country;
        }

        public void setSunrise(String sunrise) {
            this.sunrise = sunrise;
        }

        public void setSunset(String sunset) {
            this.sunset = sunset;
        }

        public void setCountry(String country) {
            this.country = country;
        }
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
