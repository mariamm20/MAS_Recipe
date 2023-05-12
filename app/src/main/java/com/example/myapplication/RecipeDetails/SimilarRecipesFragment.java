package com.example.myapplication.RecipeDetails;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.API.Listeners.RecipeClickListener;
import com.example.myapplication.API.Listeners.RecipeDetailsListener;
import com.example.myapplication.API.Listeners.SimilarRecipesListener;
import com.example.myapplication.API.Models.RecipeDetailsResponse;
import com.example.myapplication.API.Models.SimilarRecipesResponse;
import com.example.myapplication.API.RequestManager;
import com.example.myapplication.Adapter.IngredientsAdapter;
import com.example.myapplication.Adapter.SimilarRecipesAdapter;
import com.example.myapplication.R;

import java.util.List;

public class SimilarRecipesFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public SimilarRecipesFragment() {
        // Required empty public constructor
    }


    public static SimilarRecipesFragment newInstance(String param1, String param2) {
        SimilarRecipesFragment fragment = new SimilarRecipesFragment();
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
        return inflater.inflate(R.layout.fragment_similar_recipes, container, false);
    }

    int id;
    RecyclerView rv_similar_recipes;
    RequestManager manager;
    SimilarRecipesAdapter similarRecipesAdapter;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv_similar_recipes = view.findViewById(R.id.rv_rv_similar_recipes);

        Intent intent = getActivity().getIntent();
        id = Integer.parseInt(intent.getStringExtra("id"));
        manager = new RequestManager(getContext());

        //similar recipes
        manager.getSimilarRecipes(similarRecipesListener, id);

    }

    private final SimilarRecipesListener similarRecipesListener = new SimilarRecipesListener() {
        @Override
        public void didFetch(List<SimilarRecipesResponse> response, String msg) {
            rv_similar_recipes.setHasFixedSize(true);
            rv_similar_recipes.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            similarRecipesAdapter = new SimilarRecipesAdapter(getContext(), response, recipeClickListener);
            rv_similar_recipes.setAdapter(similarRecipesAdapter);
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