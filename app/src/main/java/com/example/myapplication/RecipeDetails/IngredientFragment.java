package com.example.myapplication.RecipeDetails;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.API.Listeners.RecipeDetailsListener;
import com.example.myapplication.API.Models.RecipeDetailsResponse;
import com.example.myapplication.API.RequestManager;
import com.example.myapplication.Adapter.IngredientsAdapter;
import com.example.myapplication.R;

public class IngredientFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public IngredientFragment() {
        // Required empty public constructor
    }


    public static IngredientFragment newInstance(String param1, String param2) {
        IngredientFragment fragment = new IngredientFragment();
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
        return inflater.inflate(R.layout.fragment_ingredient, container, false);
    }

    int id;
    RecyclerView rv_ingredients;
    RequestManager manager;
    IngredientsAdapter ingredientsAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv_ingredients = view.findViewById(R.id.rv_ingredients);

        Intent intent = getActivity().getIntent();
        id = Integer.parseInt(intent.getStringExtra("id"));
        manager = new RequestManager(getContext());

        //recipe details
        manager.getRecipesDetails(recipeDetailsListener, id);

    }

    private final RecipeDetailsListener recipeDetailsListener = new RecipeDetailsListener() {
        @Override
        public void didFetch(RecipeDetailsResponse response, String msg) {
            rv_ingredients.setHasFixedSize(true);
            rv_ingredients.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            ingredientsAdapter = new IngredientsAdapter(getContext(), response.extendedIngredients);
            rv_ingredients.setAdapter(ingredientsAdapter);
        }

        @Override
        public void didError(String msg) {
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();

        }
    };

}