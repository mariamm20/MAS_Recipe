package com.example.myapplication.Home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Adapter.WishlistRecyclerViewAdapter;
import com.example.myapplication.Items.RecipeItem;
import com.example.myapplication.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WishlistFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WishlistFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WishlistFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WishlistFragment.
     */
    // TODO: Rename and change types and number of parameters
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wishlist, container, false);
    }
    
    RecyclerView rv_favourite_recipes;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv_favourite_recipes = view.findViewById(R.id.rv_favourite_recipes);

        ArrayList<RecipeItem> Recipies = new ArrayList<>();
        Recipies.add(new RecipeItem(R.drawable.burger,"Smashed Burger","4.5","1"));
        Recipies.add(new RecipeItem(R.drawable.burger,"Beef Burger","5.0","2"));
        Recipies.add(new RecipeItem(R.drawable.burger,"Chicken Burger","3.2","4"));
        Recipies.add(new RecipeItem(R.drawable.burger,"Cheese Burger","1.4","6"));
        Recipies.add(new RecipeItem(R.drawable.burger,"KFC Burger","4.0","3"));
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getContext());
        WishlistRecyclerViewAdapter adapter = new WishlistRecyclerViewAdapter(getContext(),Recipies);
        rv_favourite_recipes.setLayoutManager(lm);
        rv_favourite_recipes.setHasFixedSize(true);
        rv_favourite_recipes.setAdapter(adapter);


    }

}