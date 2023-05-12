package com.example.myapplication.API.Listeners;


import com.example.myapplication.API.Models.RandomRecipesApiResponse;

public interface RandomRecipesResponseListener {
    void didFetch(RandomRecipesApiResponse response, String msg);

    void didError(String msg);
}
