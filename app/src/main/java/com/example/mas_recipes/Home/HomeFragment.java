package com.example.mas_recipes.Home;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.mas_recipes.Registration.EditProfileActivity;
import com.example.mas_recipes.RoomDatabase.AppDatabase;
import com.example.mas_recipes.RoomDatabase.WishlistViewModel;


public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    ProgressDialog dialog;
    RequestManager manager;
    RandomRecipesAdapter randomRecipesAdapter;
    RecyclerView rv_random_recipes;

    CardView searchbar_card, profile_card;
    ImageView reload_btn;
    AppDatabase db;
    TextView tv_id;
    String name;

    WishlistViewModel wishlistViewModel;
    int user_id;

    LifecycleOwner lifecycleOwner;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv_random_recipes = view.findViewById(R.id.rv_random_recipes);
        searchbar_card = view.findViewById(R.id.searchbar_card);
        profile_card = view.findViewById(R.id.profile_card);
        reload_btn = view.findViewById(R.id.reload_btn);
        tv_id = view.findViewById(R.id.tv_id);

        dialog = new ProgressDialog(getContext());
        dialog.setTitle("Loading Recipes...");

        SharedPreferences pref = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        user_id = pref.getInt("id", -1);
        name = pref.getString("name", "Hello, Dear Customer");

        Log.d("user_id", String.valueOf(user_id));
        tv_id.setText("Hello, " +name);

        lifecycleOwner = this;
        wishlistViewModel = new ViewModelProvider(this).get(WishlistViewModel.class);

        manager = new RequestManager(getContext());
        manager.getRandomRecipes(randomRecipesResponseListener);
        dialog.show();

        reload_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setTitle("Reloading Recipes...");
                manager.getRandomRecipes(randomRecipesResponseListener);
                dialog.show();
            }
        });

        searchbar_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });

        profile_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
            }
        });

    }

    private final RandomRecipesResponseListener randomRecipesResponseListener = new RandomRecipesResponseListener() {
        @Override
        public void didFetch(RandomRecipesApiResponse response, String msg) {
            dialog.dismiss();
            rv_random_recipes.setHasFixedSize(true);
            rv_random_recipes.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            randomRecipesAdapter = new RandomRecipesAdapter(getContext(), response.recipes, recipeClickListener, wishlistViewModel,lifecycleOwner, user_id);
            rv_random_recipes.setAdapter(randomRecipesAdapter);
        }

        @Override
        public void didError(String msg) {
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();

        }
    };

    private final RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
            startActivity(new Intent(getContext(), RecipeDetailsActivity.class)
                    .putExtra("id", id));
            Log.d("Recipe ID", id);
        }
    };

}