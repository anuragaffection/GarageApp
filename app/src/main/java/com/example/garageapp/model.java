package com.example.garageapp;

public class model {

    int image;
    String carMake;
    String carModel;
    String addCarImage;
    String deleteCar;

    model(int image, String carMake, String carModel, String addCarImage, String deleteCar){
        this.image = image ;
        this.carMake = carMake;
        this.carModel = carModel;
        this.addCarImage = addCarImage;
        this.deleteCar = deleteCar;
    }
}
