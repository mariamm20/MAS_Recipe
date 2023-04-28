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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.myapplication.Adapter.SuggestionRecyclerViewAdapter;
import com.example.myapplication.Items.RecipeItem;
import com.example.myapplication.R;

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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_suggestion, container, false);
    }

    String[] suggestions = {"Breakfast", "Lunch", "Dinner", "Desert","Snacks"};
    AutoCompleteTextView autoComplete_suggestion;
    ArrayAdapter<String> adapter_suggestions;
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

        autoComplete_suggestion = view.findViewById(R.id.autocomplete_suggestions);
        adapter_suggestions = new ArrayAdapter<String>(this.getContext(), R.layout.select, suggestions);
        autoComplete_suggestion.setAdapter(adapter_suggestions);



    }



}