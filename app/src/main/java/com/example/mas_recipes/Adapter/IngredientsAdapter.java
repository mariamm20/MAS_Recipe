package com.example.mas_recipes.Adapter;

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

import com.example.mas_recipes.API.Models.ExtendedIngredient;
import com.example.mas_recipes.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder> {

    Context context;
    List<ExtendedIngredient> ingredientList;

    public IngredientsAdapter(Context context, List<ExtendedIngredient> ingredientList) {
        this.context = context;
        this.ingredientList = ingredientList;
    }

    @NonNull
    @Override
    public IngredientsAdapter.IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngredientsViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_ingredients_item, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull IngredientsAdapter.IngredientsViewHolder holder, int position) {
        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.slide_in_left);

        holder.txt_ingredient_name.setText(ingredientList.get(position).name);
        holder.txt_ingredient_qty.setText(ingredientList.get(position).original);
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/" + ingredientList.get(position).image)
                .into(holder.ingredient_img);

        holder.txt_ingredient_name.startAnimation(animation);
        holder.txt_ingredient_qty.startAnimation(animation);
        holder.ingredient_img.startAnimation(animation);

    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }


    // Holder Class for recycler view
    class IngredientsViewHolder extends RecyclerView.ViewHolder {

        ImageView ingredient_img;
        TextView txt_ingredient_name, txt_ingredient_qty;

        public IngredientsViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_ingredient_name = itemView.findViewById(R.id.txt_ingredient_name);
            txt_ingredient_qty = itemView.findViewById(R.id.txt_ingredient_qty);
            ingredient_img = itemView.findViewById(R.id.ingredient_img);
        }
    }
}

