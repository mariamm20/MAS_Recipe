package com.example.myapplication.API.Models;

import java.util.ArrayList;

public class RandomRecipesApiResponse {
    public ArrayList<Recipe> recipes;

    public RandomRecipesApiResponse(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }
}
