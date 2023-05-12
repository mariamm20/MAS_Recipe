package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.API.Models.Step;
import com.example.myapplication.R;

import java.util.List;

public class InstructionsStepAdapter extends RecyclerView.Adapter<InstructionsStepAdapter.InstructionsStepViewHolder> {

    Context context;
    List<Step> stepList;

    public InstructionsStepAdapter(Context context, List<Step> stepList) {
        this.context = context;
        this.stepList = stepList;
    }

    @NonNull
    @Override
    public InstructionsStepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionsStepViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_instructions_step_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull InstructionsStepViewHolder holder, int position) {
        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.slide_in_left);

        holder.txt_instruction_step_number.setText(String.valueOf(stepList.get(position).number));
        holder.txt_instruction_step_title.setText(stepList.get(position).step);

        holder.rv_instructions_equipment.setHasFixedSize(true);
        holder.rv_instructions_equipment.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        EquipmentAdapter equipmentAdapter = new EquipmentAdapter(context, stepList.get(position).equipment);
        holder.rv_instructions_equipment.setAdapter(equipmentAdapter);


        holder.txt_instruction_step_number.startAnimation(animation);
        holder.txt_instruction_step_title.startAnimation(animation);
        holder.rv_instructions_equipment.startAnimation(animation);

    }

    @Override
    public int getItemCount() {
        return stepList.size();
    }


    class InstructionsStepViewHolder extends RecyclerView.ViewHolder {
        TextView txt_instruction_step_number, txt_instruction_step_title;
        RecyclerView rv_instructions_equipment;

        public InstructionsStepViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_instruction_step_number = itemView.findViewById(R.id.txt_instruction_number);
            txt_instruction_step_title = itemView.findViewById(R.id.txt_instruction_title);
            rv_instructions_equipment = itemView.findViewById(R.id.rv_equipment);
        }
    }
}