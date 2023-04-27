package com.example.myapplication.Items;

public class RecipeItem {
    private int recipe_img;
    private String recipe_name;
    private String recipe_rate;
    private String recipe_time;


    public RecipeItem(int recipe_img, String recipe_name, String recipe_rate, String recipe_time) {
        this.recipe_img = recipe_img;
        this.recipe_name = recipe_name;
        this.recipe_rate = recipe_rate;
        this.recipe_time = recipe_time;
    }

    public int getRecipe_img() {
        return recipe_img;
    }

    public void setRecipe_img(int recipe_img) {
        this.recipe_img = recipe_img;
    }

    public String getRecipe_name() {
        return recipe_name;
    }

    public void setRecipe_name(String recipe_name) {
        this.recipe_name = recipe_name;
    }

    public String getRecipe_rate() {
        return recipe_rate;
    }

    public void setRecipe_rate(String recipe_rate) {
        this.recipe_rate = recipe_rate;
    }

    public String getRecipe_time() {
        return recipe_time;
    }

    public void setRecipe_time(String recipe_time) {
        this.recipe_time = recipe_time;
    }
}
