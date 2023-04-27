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

import com.example.myapplication.Adapter.StepRecyclerViewAdapter;
import com.example.myapplication.Items.StepItem;
import com.example.myapplication.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StepsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StepsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StepsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment steps.
     */
    // TODO: Rename and change types and number of parameters
    public static StepsFragment newInstance(String param1, String param2) {
        StepsFragment fragment = new StepsFragment();
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
        return inflater.inflate(R.layout.fragment_steps, container, false);
    }
    RecyclerView rv;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv = view.findViewById(R.id.steps_rv);

        ArrayList<StepItem> Steps = new ArrayList<>();
        Steps.add(new StepItem("Prepare the seasoning mix. In a small bowl, mix together the salt, pepper, and garlic powder. Set aside."));
        Steps.add(new StepItem("In another small bowl, mix the mayonnaise, mustard, and ketchup together until smooth. Set aside."));
        Steps.add(new StepItem("Divide the ground beef into three 1/3-pound patties. Shape into discs about 1/2 inch thick."));
        Steps.add(new StepItem("Season the patties with 1/2 teaspoon of seasoning mix per side of the patty"));
        Steps.add(new StepItem("Spray your skillet with a little non-stick spray and place burgers in the skillet"));
        Steps.add(new StepItem("Cook the burgers over medium heat for 4 minutes. Don’t touch them too much so you get a nice crust on the first side."));
        Steps.add(new StepItem("Use a sturdy metal spatula to flip the burgers."));
        Steps.add(new StepItem("After you flip the burgers, add the cheese, if using, and continue to cook for 3 minutes until the burger is cooked"));
        Steps.add(new StepItem("Build your burgers by spreading some burger sauce on each bun. "));
        Steps.add(new StepItem("Put the burgers on the bottom bun and top with the lettuce, tomatoes, and the top bun."));
        Steps.add(new StepItem("Serve immediately with a cold drink."));

        RecyclerView.LayoutManager lm = new LinearLayoutManager(getContext());
        StepRecyclerViewAdapter adapter = new StepRecyclerViewAdapter(getContext(),Steps);
        rv.setLayoutManager(lm);
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);


    }
}