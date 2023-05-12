package com.example.myapplication.API.Listeners;

import com.example.myapplication.API.Models.SimilarRecipesResponse;

import java.util.List;

public interface SimilarRecipesListener {
    void didFetch(List<SimilarRecipesResponse> response, String msg);

    void didError(String msg);
}
