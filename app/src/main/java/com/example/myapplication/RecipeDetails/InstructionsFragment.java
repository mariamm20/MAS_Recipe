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

import com.example.myapplication.API.Listeners.InstructionsListener;
import com.example.myapplication.API.Models.InstructionsResponse;
import com.example.myapplication.API.RequestManager;
import com.example.myapplication.Adapter.InstructionsAdapter;
import com.example.myapplication.R;

import java.util.List;


public class InstructionsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public InstructionsFragment() {
        // Required empty public constructor
    }

    public static InstructionsFragment newInstance(String param1, String param2) {
        InstructionsFragment fragment = new InstructionsFragment();
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
        return inflater.inflate(R.layout.fragment_instructions, container, false);
    }

    int id;
    RecyclerView rv_instructions;
    RequestManager manager;
    InstructionsAdapter instructionsAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv_instructions = view.findViewById(R.id.rv_instructions);

        Intent intent = getActivity().getIntent();
        id = Integer.parseInt(intent.getStringExtra("id"));
        manager = new RequestManager(getContext());

        //recipe details
        manager.getInstructions(instructionsListener, id);
    }


    private final InstructionsListener instructionsListener = new InstructionsListener() {
        @Override
        public void didFetch(List<InstructionsResponse> response, String msg) {
            rv_instructions.setHasFixedSize(true);
            rv_instructions.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            instructionsAdapter = new InstructionsAdapter(getContext(), response);
            rv_instructions.setAdapter(instructionsAdapter);
        }

        @Override
        public void didError(String msg) {
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        }
    };

}