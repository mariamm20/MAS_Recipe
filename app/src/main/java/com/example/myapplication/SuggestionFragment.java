package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SuggestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SuggestionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SuggestionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SuggestionFragment.
     */
    // TODO: Rename and change types and number of parameters
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
    Spinner dropdown;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_suggestion, null, false);

        return rootView;
    }
    String[] categories = {"Breakfast", "Lunch", "Dinner","Snacks"};
    AutoCompleteTextView autoComplete_category;
    ArrayAdapter<String> adapter_categories;
    RecyclerView rv;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv = view.findViewById(R.id.suggestion_rv);

        ArrayList<RecipeItem> Recipies = new ArrayList<>();
        Recipies.add(new RecipeItem(R.drawable.burger,"Smashed Burger","4.5","1"));
        Recipies.add(new RecipeItem(R.drawable.burger,"Beef Burger","5.0","2"));
        Recipies.add(new RecipeItem(R.drawable.burger,"Chicken Burger","3.2","4"));
        Recipies.add(new RecipeItem(R.drawable.burger,"Cheese Burger","1.4","6"));
        Recipies.add(new RecipeItem(R.drawable.burger,"KFC Burger","4.0","3"));
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getContext());
        SuggestionRecyclerViewAdapter adapter = new SuggestionRecyclerViewAdapter(getContext(),Recipies);
        rv.setLayoutManager(lm);
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);

        autoComplete_category = view.findViewById(R.id.balance_autocomplete_product_type);
        adapter_categories = new ArrayAdapter<String>(this.getContext(), R.layout.select, categories);
        autoComplete_category.setAdapter(adapter_categories);



    }



}