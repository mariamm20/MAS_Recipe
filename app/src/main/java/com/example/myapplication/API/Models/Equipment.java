package com.example.myapplication.API.Models;

public class Equipment {
    public int id;
    public String name;
    public String localizedName;
    public String image;
    public Temperature temperature;

    public Equipment(int id, String name, String localizedName, String image, Temperature temperature) {
        this.id = id;
        this.name = name;
        this.localizedName = localizedName;
        this.image = image;
        this.temperature = temperature;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }
}
