package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StepRecyclerViewAdapter extends RecyclerView.Adapter<StepRecyclerViewAdapter.StepsViewHolder> {
    Context context;
    ArrayList<StepItem> Steps;
    public StepRecyclerViewAdapter (Context context, ArrayList<StepItem> steps) {
        this.Steps = steps;
        this.context = context;
    }
    @NonNull
    @Override
    public StepRecyclerViewAdapter.StepsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.steps_custom_item, null, false);
        StepRecyclerViewAdapter.StepsViewHolder viewHolder = new StepRecyclerViewAdapter.StepsViewHolder(v);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull StepRecyclerViewAdapter.StepsViewHolder holder, int position) {
        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.slide_in_left);

        StepItem r = Steps.get(position);
        holder.step_text.setText((position+1) + " - " +r.getStep_text());

        holder.step_text.startAnimation(animation);

    }
    @Override
    public int getItemCount() {
        return Steps.size();
    }



    // Holder Class for recycler view
    class StepsViewHolder extends RecyclerView.ViewHolder{
        TextView step_text;

        public StepsViewHolder(@NonNull View itemView) {
            super(itemView);
            step_text = itemView.findViewById(R.id.step_item);

        }
    }
}

