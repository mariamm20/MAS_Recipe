package com.example.mas_recipes.API;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.room.Room;

import com.example.mas_recipes.API.Listeners.InstructionsListener;
import com.example.mas_recipes.API.Listeners.RandomRecipesResponseListener;
import com.example.mas_recipes.API.Listeners.RecipeDetailsListener;
import com.example.mas_recipes.API.Listeners.SimilarRecipesListener;
import com.example.mas_recipes.API.Models.InstructionsResponse;
import com.example.mas_recipes.API.Models.RandomRecipesApiResponse;
import com.example.mas_recipes.API.Models.Recipe;
import com.example.mas_recipes.API.Models.RecipeDetailsResponse;
import com.example.mas_recipes.API.Models.SimilarRecipesResponse;
import com.example.mas_recipes.Home.WishlistFragment;
import com.example.mas_recipes.R;
import com.example.mas_recipes.RoomDatabase.AppDatabase;
import com.example.mas_recipes.RoomDatabase.WishlistEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    AppDatabase db;

    public RequestManager(Context context) {
        this.context = context;
        db = Room.databaseBuilder(context, AppDatabase.class, "recipe_db")
                .allowMainThreadQueries().build();
    }

    public void getRandomRecipes(RandomRecipesResponseListener listener) {
        CallRandomRecipes callRandomRecipes = retrofit.create(CallRandomRecipes.class);
        Call<RandomRecipesApiResponse> call = callRandomRecipes.callRandomRecipes(context.getString(R.string.api_key), "25");
        call.enqueue(new Callback<RandomRecipesApiResponse>() {
            @Override
            public void onResponse(Call<RandomRecipesApiResponse> call, Response<RandomRecipesApiResponse> response) {
                if (!response.isSuccessful()) {
                    listener.didError(response.message());
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RandomRecipesApiResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    public void getRecipesDetails(RecipeDetailsListener listener, int id) {
        CallRecipeDetails callRecipeDetails = retrofit.create(CallRecipeDetails.class);
        Call<RecipeDetailsResponse> call = callRecipeDetails.callRecipeDetails(id, context.getString(R.string.api_key));
        call.enqueue(new Callback<RecipeDetailsResponse>() {
            @Override
            public void onResponse(Call<RecipeDetailsResponse> call, Response<RecipeDetailsResponse> response) {
                if (!response.isSuccessful()) {
                    listener.didError(response.message());
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RecipeDetailsResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });


    }

    public void getInstructions(InstructionsListener listener, int id) {
        CallInstructions callInstructions = retrofit.create(CallInstructions.class);
        Call<List<InstructionsResponse>> call = callInstructions.callInstructions(id, context.getString(R.string.api_key));
        call.enqueue(new Callback<List<InstructionsResponse>>() {
            @Override
            public void onResponse(Call<List<InstructionsResponse>> call, Response<List<InstructionsResponse>> response) {
                if (!response.isSuccessful()) {
                    listener.didError(response.message());
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<List<InstructionsResponse>> call, Throwable t) {
                listener.didError(t.getMessage());

            }
        });
    }

    public void getSimilarRecipes(SimilarRecipesListener listener, int id) {
        CallSimilarRecipes callSimilarRecipes = retrofit.create(CallSimilarRecipes.class);
        Call<List<SimilarRecipesResponse>> call = callSimilarRecipes.callSimilarRecipes(id, context.getString(R.string.api_key), "15");
        call.enqueue(new Callback<List<SimilarRecipesResponse>>() {
            @Override
            public void onResponse(Call<List<SimilarRecipesResponse>> call, Response<List<SimilarRecipesResponse>> response) {
                if (!response.isSuccessful()) {
                    listener.didError(response.message());
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<List<SimilarRecipesResponse>> call, Throwable t) {
                listener.didError(t.getMessage());

            }
        });
    }

    public void getSuggestedRecipes(RandomRecipesResponseListener listener, List<String> tags) {
        CallSuggestedRecipes callSuggestedRecipes = retrofit.create(CallSuggestedRecipes.class);
        Call<RandomRecipesApiResponse> call = callSuggestedRecipes.callSuggestedRecipes(context.getString(R.string.api_key), "25", tags);
        call.enqueue(new Callback<RandomRecipesApiResponse>() {
            @Override
            public void onResponse(Call<RandomRecipesApiResponse> call, Response<RandomRecipesApiResponse> response) {
                if (!response.isSuccessful()) {
                    listener.didError(response.message());
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RandomRecipesApiResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    //----interfaces----------
    private interface CallRandomRecipes {
        @GET("recipes/random")
        Call<RandomRecipesApiResponse> callRandomRecipes(
                @Query("apiKey") String apiKey,
                @Query("number") String number
        );
    }

    private interface CallRecipeDetails {
        @GET("recipes/{id}/information")
        Call<RecipeDetailsResponse> callRecipeDetails(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }

    private interface CallInstructions {
        @GET("recipes/{id}/analyzedInstructions")
        Call<List<InstructionsResponse>> callInstructions(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }

    private interface CallSimilarRecipes {
        @GET("recipes/{id}/similar")
        Call<List<SimilarRecipesResponse>> callSimilarRecipes(
                @Path("id") int id,
                @Query("apiKey") String apiKey,
                @Query("number") String number
        );
    }

    private interface CallSuggestedRecipes {
        @GET("recipes/random")
        Call<RandomRecipesApiResponse> callSuggestedRecipes(
                @Query("apiKey") String apiKey,
                @Query("number") String number,
                @Query("tags") List<String> tags
        );
    }

}
