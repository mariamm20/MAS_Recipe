package com.example.mas_recipes.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mas_recipes.API.Listeners.RandomRecipesResponseListener;
import com.example.mas_recipes.API.Listeners.RecipeClickListener;
import com.example.mas_recipes.API.Models.RandomRecipesApiResponse;
import com.example.mas_recipes.API.RequestManager;
import com.example.mas_recipes.Adapter.RandomRecipesAdapter;
import com.example.mas_recipes.R;
import com.example.mas_recipes.RecipeDetails.RecipeDetailsActivity;
import com.example.mas_recipes.RoomDatabase.AppDatabase;
import com.example.mas_recipes.RoomDatabase.WishlistViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    ImageButton back_btn2;
    TextView txt_empty_search_recipes, txt_search_recipes;
    ImageView img_empty_search_recipes;
    RecyclerView rv_search_recipes;
    SearchView searchView;

    ProgressDialog dialog;
    RequestManager manager;
    RandomRecipesAdapter randomRecipesAdapter;
    List<String> tags = new ArrayList<>();

    WishlistViewModel wishlistViewModel;
    int user_id;

    LifecycleOwner lifecycleOwner;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        findView();

        back_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading Recipes...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(searchView.getWindowToken(), 0);

                if (query != null) {
                    txt_empty_search_recipes.setVisibility(View.GONE);
                    img_empty_search_recipes.setVisibility(View.GONE);
                    txt_search_recipes.setVisibility(View.VISIBLE);
                    rv_search_recipes.setVisibility(View.VISIBLE);
                    tags.clear();
                    tags.add(query);
                    manager.getSuggestedRecipes(randomRecipesResponseListener, tags);
                    dialog.show();
                    return true;
                } else {
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

        lifecycleOwner = this;
        wishlistViewModel = new ViewModelProvider(this).get(WishlistViewModel.class);

        SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        user_id = pref.getInt("id", -1);
        Log.d("user_id", String.valueOf(user_id));

        manager = new RequestManager(this);
    }

    private void findView() {
        searchView = findViewById(R.id.search_view);
        back_btn2 = findViewById(R.id.back_btn2);
        rv_search_recipes = findViewById(R.id.rv_search_recipes);
        txt_search_recipes = findViewById(R.id.txt_search_recipes);
        txt_empty_search_recipes = findViewById(R.id.txt_empty_search_recipes);
        img_empty_search_recipes = findViewById(R.id.img_empty_search_recipes);
    }

    private final RandomRecipesResponseListener randomRecipesResponseListener = new RandomRecipesResponseListener() {
        @Override
        public void didFetch(RandomRecipesApiResponse response, String msg) {
            dialog.dismiss();
            rv_search_recipes.setHasFixedSize(true);
            rv_search_recipes.setLayoutManager(new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL, false));
            randomRecipesAdapter = new RandomRecipesAdapter(SearchActivity.this, response.recipes, recipeClickListener, wishlistViewModel, lifecycleOwner, user_id);
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
            Log.d("Recipe ID", id);
        }
    };

}