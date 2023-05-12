package com.example.myapplication.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.API.Listeners.RandomRecipesResponseListener;
import com.example.myapplication.API.Listeners.RecipeClickListener;
import com.example.myapplication.API.Models.RandomRecipesApiResponse;
import com.example.myapplication.API.RequestManager;
import com.example.myapplication.Adapter.RandomRecipesAdapter;
import com.example.myapplication.R;
import com.example.myapplication.RecipeDetails.RecipeDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    
    ImageButton back_btn2;
    ProgressDialog dialog;
    RequestManager manager;
    RandomRecipesAdapter randomRecipesAdapter;
    RecyclerView rv_search_recipes;
    SearchView searchView;
    List<String> tags = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        back_btn2 = findViewById(R.id.back_btn2);
        back_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading Recipes...");

        searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(searchView.getWindowToken(), 0);

                if(query != null){
                    tags.clear();
                    tags.add(query);
                    manager.getSuggestedRecipes(randomRecipesResponseListener, tags);
                    dialog.show();
                    return true;
                }else{
                    tags.clear();
                    dialog.dismiss();
                    return false;
                }

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        manager = new RequestManager(this);
    }

    private final RandomRecipesResponseListener randomRecipesResponseListener = new RandomRecipesResponseListener() {
        @Override
        public void didFetch(RandomRecipesApiResponse response, String msg) {
            dialog.dismiss();
            rv_search_recipes = findViewById(R.id.rv_search_recipes);
            rv_search_recipes.setHasFixedSize(true);
            rv_search_recipes.setLayoutManager(new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL, false));
            randomRecipesAdapter = new RandomRecipesAdapter(SearchActivity.this, response.recipes, recipeClickListener);
            rv_search_recipes.setAdapter(randomRecipesAdapter);
        }

        @Override
        public void didError(String msg) {
            Toast.makeText(SearchActivity.this, msg, Toast.LENGTH_SHORT).show();

        }
    };


    private final RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
            startActivity(new Intent(SearchActivity.this, RecipeDetailsActivity.class)
                    .putExtra("id", id));

            Toast.makeText(SearchActivity.this, id, Toast.LENGTH_SHORT).show();
            Log.d("Recipe ID", id);
        }
    };

}