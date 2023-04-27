package com.example.myapplication.Ingredients;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Adapter.IngredientRecyclerViewAdapter;
import com.example.myapplication.Items.IngredientItem;
import com.example.myapplication.R;

import java.util.ArrayList;

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


    // TODO: Rename and change types and number of parameters
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ingredient, container, false);
    }
    RecyclerView rv;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv = view.findViewById(R.id.ingredient_rv);

        ArrayList<IngredientItem> Ingredients = new ArrayList<>();
        Ingredients.add(new IngredientItem("1 tablespoon kosher salt"));
        Ingredients.add(new IngredientItem("1 tablespoon black pepper"));
        Ingredients.add(new IngredientItem("1 teaspoon garlic powder"));
        Ingredients.add(new IngredientItem("1/4 cup mayonnaise"));
        Ingredients.add(new IngredientItem("1 tablespoon Dijon mustard"));
        Ingredients.add(new IngredientItem("1 tablespoon ketchup"));
        Ingredients.add(new IngredientItem("1 pound ground chuck (90:10)"));
        Ingredients.add(new IngredientItem("6 slices American cheese"));
        Ingredients.add(new IngredientItem("Green lettuce leaves"));
        Ingredients.add(new IngredientItem("Thinly sliced fresh tomato"));
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getContext());
        IngredientRecyclerViewAdapter adapter = new IngredientRecyclerViewAdapter(getContext(),Ingredients);
        rv.setLayoutManager(lm);
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);


    }

}