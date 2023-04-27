package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

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
    RecyclerView rv;
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
        return inflater.inflate(R.layout.fragment_home, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv = view.findViewById(R.id.rv_main);

        ArrayList<RecipeItem> Recipies = new ArrayList<>();
        Recipies.add(new RecipeItem(R.drawable.burger,"Smashed Burger","4.5","1"));
        Recipies.add(new RecipeItem(R.drawable.burger,"Beef Burger","5.0","2"));
        Recipies.add(new RecipeItem(R.drawable.burger,"Chicken Burger","3.2","4"));
        Recipies.add(new RecipeItem(R.drawable.burger,"Cheese Burger","1.4","6"));
        Recipies.add(new RecipeItem(R.drawable.burger,"KFC Burger","4.0","3"));
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getContext());
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(),Recipies);
        rv.setLayoutManager(lm);
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);


    }


}