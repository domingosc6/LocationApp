package com.example.locationapp;

import com.example.locationapp.controllers.CitiesController;

public class Main {
    public static void main(String[] args) {
        CitiesController controller = new CitiesController();
        controller.start();
    }
}
