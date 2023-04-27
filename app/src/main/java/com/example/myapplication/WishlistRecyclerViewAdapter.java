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

public class WishlistRecyclerViewAdapter extends RecyclerView.Adapter<WishlistRecyclerViewAdapter.RecipiesViewHolder> {
    Context context;
    ArrayList<RecipeItem> Recipies;
    public WishlistRecyclerViewAdapter(Context context, ArrayList<RecipeItem> recipies) {
        this.Recipies = recipies;
        this.context = context;
    }
    @NonNull
    @Override
    public WishlistRecyclerViewAdapter.RecipiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.wishlist_custom_item, null, false);
        WishlistRecyclerViewAdapter.RecipiesViewHolder viewHolder = new WishlistRecyclerViewAdapter.RecipiesViewHolder(v);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull WishlistRecyclerViewAdapter.RecipiesViewHolder holder, int position) {
        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.slide_in_left);

        RecipeItem r = Recipies.get(position);
        holder.iv_image.setImageResource(r.getRecipe_img());
        holder.recipe_rate.setText("("+r.getRecipe_rate()+")");
        holder.recipe_time.setText(r.getRecipe_time());
        holder.recipe_name.setText(r.getRecipe_name());

        holder.iv_image.startAnimation(animation);
        holder.recipe_name.startAnimation(animation);
        holder.recipe_rate.startAnimation(animation);
        holder.recipe_time.startAnimation(animation);
    }
    @Override
    public int getItemCount() {
        return Recipies.size();
    }



    // Holder Class for recycler view
    class RecipiesViewHolder extends RecyclerView.ViewHolder{
        TextView recipe_name,recipe_rate,recipe_time;
        ImageView iv_image;

        public RecipiesViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_image = itemView.findViewById(R.id.recipie_image);
            recipe_name = itemView.findViewById(R.id.recipe_name);
            recipe_rate =  itemView.findViewById(R.id.recipie_rate);
            recipe_time = itemView.findViewById(R.id.recipe_time);
        }
    }
}
