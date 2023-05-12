package com.example.mas_recipes.API.Listeners;


import com.example.mas_recipes.API.Models.RecipeDetailsResponse;

public interface RecipeDetailsListener {
    void didFetch(RecipeDetailsResponse response, String msg);

    void didError(String msg);
}
