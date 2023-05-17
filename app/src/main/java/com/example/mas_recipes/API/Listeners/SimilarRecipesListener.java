package com.example.mas_recipes.API.Listeners;

import com.example.mas_recipes.API.Models.SimilarRecipesResponse;
import java.util.List;

public interface SimilarRecipesListener {
    void didFetch(List<SimilarRecipesResponse> response, String msg);

    void didError(String msg);
}
