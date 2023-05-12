package com.example.mas_recipes.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mas_recipes.API.Models.InstructionsResponse;
import com.example.mas_recipes.R;

import java.util.List;

public class InstructionsAdapter extends RecyclerView.Adapter<InstructionsAdapter.InstructionsViewHolder> {
    Context context;
    List<InstructionsResponse> instructionsList;

    public InstructionsAdapter(Context context, List<InstructionsResponse> instructionsList) {
        this.context = context;
        this.instructionsList = instructionsList;
    }

    @NonNull
    @Override
    public InstructionsAdapter.InstructionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionsViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_instructions_item, parent, false));

    }


    @Override
    public void onBindViewHolder(@NonNull InstructionsAdapter.InstructionsViewHolder holder, int position) {
        holder.rv_instructions_step.setHasFixedSize(true);
        holder.rv_instructions_step.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        InstructionsStepAdapter instructionsStepAdapter = new InstructionsStepAdapter(context, instructionsList.get(position).steps);
        holder.rv_instructions_step.setAdapter(instructionsStepAdapter);

    }

    @Override
    public int getItemCount() {
        return instructionsList.size();
    }


    // Holder Class for recycler view
    static class InstructionsViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rv_instructions_step;

        public InstructionsViewHolder(@NonNull View itemView) {
            super(itemView);
            rv_instructions_step = itemView.findViewById(R.id.rv_instructions_step);
        }
    }
}

