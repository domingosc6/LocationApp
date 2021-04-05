package com.example.locationapp.model;

public class City {
    private String name;
    private String maxTemp;
    private String minTemp;
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

    public String getName() {
        return name;
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

    public void setName(String name) {
        this.name = name;
    }
}
