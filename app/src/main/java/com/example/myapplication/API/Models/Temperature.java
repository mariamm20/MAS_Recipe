package com.example.myapplication.API.Models;

public class Temperature {
    public double number;
    public String unit;

    public Temperature(double number, String unit) {
        this.number = number;
        this.unit = unit;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
