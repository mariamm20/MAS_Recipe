package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Items.IngredientItem;
import com.example.myapplication.R;

import java.util.ArrayList;

public class IngredientRecyclerViewAdapter extends RecyclerView.Adapter<IngredientRecyclerViewAdapter.IngredientsViewHolder> {
    Context context;
    ArrayList<IngredientItem> Ingredients;
    public IngredientRecyclerViewAdapter (Context context, ArrayList<IngredientItem> ingredients) {
        this.Ingredients = ingredients;
        this.context = context;
    }
    @NonNull
    @Override
    public IngredientRecyclerViewAdapter.IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.ingredients_custom_item, null, false);
        IngredientRecyclerViewAdapter.IngredientsViewHolder viewHolder = new IngredientRecyclerViewAdapter.IngredientsViewHolder(v);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull IngredientRecyclerViewAdapter.IngredientsViewHolder holder, int position) {
        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.slide_in_left);

        IngredientItem r = Ingredients.get(position);
        holder.ingredient_text.setText(r.getIngredient_text());

        holder.ingredient_text.startAnimation(animation);

    }
    @Override
    public int getItemCount() {
        return Ingredients.size();
    }



    // Holder Class for recycler view
    class IngredientsViewHolder extends RecyclerView.ViewHolder{
        TextView ingredient_text;

        public IngredientsViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredient_text = itemView.findViewById(R.id.step_item);

        }
    }
}

