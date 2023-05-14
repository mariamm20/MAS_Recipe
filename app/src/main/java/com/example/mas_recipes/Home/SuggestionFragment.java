package com.example.mas_recipes.Home;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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

public class SuggestionFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public SuggestionFragment() {
        // Required empty public constructor
    }

    public static SuggestionFragment newInstance(String param1, String param2) {
        SuggestionFragment fragment = new SuggestionFragment();
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
        return inflater.inflate(R.layout.fragment_suggestion, container, false);
    }

    AutoCompleteTextView autoComplete_suggestion;
    ArrayAdapter<String> adapter_suggestions;
    RecyclerView rv_suggested_recipes;

    ProgressDialog dialog;
    RequestManager manager;
    RandomRecipesAdapter randomRecipesAdapter;

    ImageView img_empty_recipes;
    TextView txt_empty_recipes, txt_suggested_recipes;

    List<String> tags = new ArrayList<>();

    WishlistViewModel wishlistViewModel;
    int user_id;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv_suggested_recipes = view.findViewById(R.id.rv_suggested_recipes);
        img_empty_recipes = view.findViewById(R.id.img_empty_recipes);
        txt_empty_recipes = view.findViewById(R.id.txt_empty_recipes);
        txt_suggested_recipes = view.findViewById(R.id.txt_suggested_recipes);

        dialog = new ProgressDialog(getContext());
        dialog.setTitle("Loading Suggestions...");

        autoComplete_suggestion = view.findViewById(R.id.autocomplete_suggestions);
        adapter_suggestions = new ArrayAdapter<String>(this.getContext(), R.layout.tags_select, getResources().getStringArray(R.array.tags));
        autoComplete_suggestion.setAdapter(adapter_suggestions);
        autoComplete_suggestion.setOnItemClickListener(clickListener);

        wishlistViewModel = new ViewModelProvider(this).get(WishlistViewModel.class);

        SharedPreferences pref = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        user_id = pref.getInt("id", -1);
        Log.d("user_id", String.valueOf(user_id));

        manager = new RequestManager(getContext());


    }

    private final RandomRecipesResponseListener randomRecipesResponseListener = new RandomRecipesResponseListener() {
        @Override
        public void didFetch(RandomRecipesApiResponse response, String msg) {
            dialog.dismiss();
            rv_suggested_recipes.setHasFixedSize(true);
            rv_suggested_recipes.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            randomRecipesAdapter = new RandomRecipesAdapter(getContext(), response.recipes, recipeClickListener, wishlistViewModel, user_id);
            rv_suggested_recipes.setAdapter(randomRecipesAdapter);
        }

        @Override
        public void didError(String msg) {
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();

        }
    };

    private final AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            img_empty_recipes.setVisibility(View.GONE);
            txt_empty_recipes.setVisibility(View.GONE);
            txt_suggested_recipes.setVisibility(View.VISIBLE);
            rv_suggested_recipes.setVisibility(View.VISIBLE);
            tags.clear();
            String itemSelected = adapterView.getItemAtPosition(i).toString();
            tags.add(itemSelected);
            Log.d("spinner", itemSelected);
            manager.getSuggestedRecipes(randomRecipesResponseListener, tags);
            dialog.show();
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