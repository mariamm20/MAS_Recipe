package com.example.myapplication.API.Listeners;

import com.example.myapplication.API.Models.InstructionsResponse;

import java.util.List;

public interface InstructionsListener {
    void didFetch(List<InstructionsResponse> response, String msg);

    void didError(String msg);
}
