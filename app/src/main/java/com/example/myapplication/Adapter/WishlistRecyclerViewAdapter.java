package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Items.RecipeItem;
import com.example.myapplication.RecipeDetails.RecipeDetailsActivity;
import com.example.myapplication.R;

import java.util.ArrayList;

public class WishlistRecyclerViewAdapter extends RecyclerView.Adapter<WishlistRecyclerViewAdapter.WishlistViewHolder> {
    Context context;
    ArrayList<RecipeItem> Recipies;
    public WishlistRecyclerViewAdapter(Context context, ArrayList<RecipeItem> recipies) {
        this.Recipies = recipies;
        this.context = context;
    }
    @NonNull
    @Override
    public WishlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.rv_wishlist_recipes_item, null, false);
        WishlistViewHolder viewHolder = new WishlistViewHolder(v);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull WishlistViewHolder holder, int position) {
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
        holder.wishlist_checkbox.startAnimation(animation);

        holder.wishlist_recipe_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RecipeDetailsActivity.class);
                context.startActivity(intent);
            }
        });

        holder.wishlist_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String msg = isChecked ? "Recipe added to wishlist" : "Recipe removed from wishlist";
                holder.wishlist_checkbox.setChecked(isChecked);
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                Log.d("wishlist", msg);
            }
        });
    }
    @Override
    public int getItemCount() {
        return Recipies.size();
    }



    // Holder Class for recycler view
    class WishlistViewHolder extends RecyclerView.ViewHolder{
        TextView recipe_name,recipe_rate,recipe_time;
        ImageView iv_image;
        CardView wishlist_recipe_card;
        CheckBox wishlist_checkbox;


        public WishlistViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_image = itemView.findViewById(R.id.wishlist_recipes_img);
            recipe_name = itemView.findViewById(R.id.txt_wishlist_recipes_title);
            recipe_rate = itemView.findViewById(R.id.txt_wishlist_recipes_servings);
            recipe_time = itemView.findViewById(R.id.txt_wishlist_recipes_time);
            wishlist_recipe_card = itemView.findViewById(R.id.wishlist_recipe_card);
            wishlist_checkbox = itemView.findViewById(R.id.wishlist_checkbox);

        }
    }
}
