package com.example.mas_recipes.Home;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mas_recipes.API.Listeners.RandomRecipesResponseListener;
import com.example.mas_recipes.API.Listeners.RecipeClickListener;
import com.example.mas_recipes.API.Models.RandomRecipesApiResponse;
import com.example.mas_recipes.API.RequestManager;
import com.example.mas_recipes.Adapter.RandomRecipesAdapter;
import com.example.mas_recipes.Adapter.WishlistAdapter;
import com.example.mas_recipes.R;
import com.example.mas_recipes.RecipeDetails.RecipeDetailsActivity;
import com.example.mas_recipes.RoomDatabase.AppDatabase;
import com.example.mas_recipes.RoomDatabase.WishlistEntity;
import com.example.mas_recipes.RoomDatabase.WishlistViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class WishlistFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public WishlistFragment() {
        // Required empty public constructor
    }

    public static WishlistFragment newInstance(String param1, String param2) {
        WishlistFragment fragment = new WishlistFragment();
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
        return inflater.inflate(R.layout.fragment_wishlist, container, false);
    }

    TextView txt_wishlist_count;
    WishlistAdapter wishlistAdapter;
    RecyclerView rv_wishlist_recipes;
    List<WishlistEntity> wishlistEntities;
    WishlistViewModel wishlistViewModel;
    int user_id;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv_wishlist_recipes = view.findViewById(R.id.rv_wishlist_recipes);
        txt_wishlist_count = view.findViewById(R.id.txt_wishlist_count);

        //user_id
        SharedPreferences pref = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        user_id = pref.getInt("id", -1);
        Log.d("user_id", String.valueOf(user_id));

        wishlistViewModel = new ViewModelProvider(this).get(WishlistViewModel.class);

        wishlistViewModel.getCount(user_id).observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer count) {
                // Update the UI with the new count
                txt_wishlist_count.setText("Favourite Recipes (" + count + ")");
                Log.d("wishlist_count", String.valueOf(count));
            }
        });

        loadWishlistData();
    }

    private void loadWishlistData() {
        rv_wishlist_recipes.setHasFixedSize(true);
        rv_wishlist_recipes.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        wishlistEntities = new ArrayList<>();
        wishlistAdapter = new WishlistAdapter(getContext(), wishlistEntities, recipeClickListener, wishlistViewModel, user_id);
        rv_wishlist_recipes.setAdapter(wishlistAdapter);

        WishlistViewModel viewModel = new ViewModelProvider(this).get(WishlistViewModel.class);
        viewModel.getWishlistItemByUserID(user_id).observe(getViewLifecycleOwner(), new Observer<List<WishlistEntity>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(List<WishlistEntity> entities) {
                wishlistEntities.clear();
                wishlistEntities.addAll(entities);
                wishlistAdapter.notifyDataSetChanged();
            }
        });
    }


    private final RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
            startActivity(new Intent(getContext(), RecipeDetailsActivity.class)
                    .putExtra("id", id));

//            Toast.makeText(getContext(), id, Toast.LENGTH_SHORT).show();
            Log.d("Recipe ID", id);
        }
    };


}