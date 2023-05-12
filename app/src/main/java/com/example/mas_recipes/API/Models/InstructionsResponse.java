package com.example.mas_recipes.API.Models;

import java.util.ArrayList;

public class InstructionsResponse {
    public String name;
    public ArrayList<Step> steps;

    public InstructionsResponse(String name, ArrayList<Step> steps) {
        this.name = name;
        this.steps = steps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }
}
