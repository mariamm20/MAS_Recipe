package com.example.myapplication.API.Listeners;


import com.example.myapplication.API.Models.RecipeDetailsResponse;

public interface RecipeDetailsListener {
    void didFetch(RecipeDetailsResponse response, String msg);

    void didError(String msg);
}
