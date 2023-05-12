package com.example.myapplication.Home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.myapplication.API.Listeners.RandomRecipesResponseListener;
import com.example.myapplication.API.Listeners.RecipeClickListener;
import com.example.myapplication.API.Models.RandomRecipesApiResponse;
import com.example.myapplication.API.RequestManager;
import com.example.myapplication.Adapter.RandomRecipesAdapter;
import com.example.myapplication.R;
import com.example.myapplication.RecipeDetails.RecipeDetailsActivity;
import com.example.myapplication.Registration.EditProfileActivity;

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv_random_recipes = view.findViewById(R.id.rv_random_recipes);
        searchbar_card = view.findViewById(R.id.searchbar_card);
        profile_card = view.findViewById(R.id.profile_card);
        reload_btn = view.findViewById(R.id.reload_btn);

        dialog = new ProgressDialog(getContext());
        dialog.setTitle("Loading Recipes...");

        manager = new RequestManager(getContext());
        manager.getRandomRecipes(randomRecipesResponseListener);
        dialog.show();

        reload_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
            randomRecipesAdapter = new RandomRecipesAdapter(getContext(), response.recipes, recipeClickListener);
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

            Toast.makeText(getContext(), id, Toast.LENGTH_SHORT).show();
            Log.d("Recipe ID", id);
        }
    };

}