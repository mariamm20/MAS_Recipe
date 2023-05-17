package com.example.mas_recipes.API.Listeners;

import com.example.mas_recipes.API.Models.RandomRecipesApiResponse;

public interface RandomRecipesResponseListener {
    void didFetch(RandomRecipesApiResponse response, String msg);

    void didError(String msg);
}
