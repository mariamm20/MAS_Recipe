package com.example.mas_recipes.API.Listeners;

import com.example.mas_recipes.API.Models.InstructionsResponse;

import java.util.List;

public interface InstructionsListener {
    void didFetch(List<InstructionsResponse> response, String msg);

    void didError(String msg);
}
