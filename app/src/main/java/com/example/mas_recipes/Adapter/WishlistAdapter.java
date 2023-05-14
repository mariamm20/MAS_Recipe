package com.example.mas_recipes.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.example.mas_recipes.API.Listeners.RecipeClickListener;
import com.example.mas_recipes.RecipeDetails.RecipeDetailsActivity;
import com.example.mas_recipes.R;
import com.example.mas_recipes.RoomDatabase.WishlistEntity;
import com.example.mas_recipes.RoomDatabase.WishlistViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder> {

    Context context;
    List<WishlistEntity> wishlistEntities;
    RecipeClickListener listener;

    WishlistViewModel wishlistViewModel;

    int user_id;
    SharedPreferences sharedPreferences_checkbox;
    public static final String CHECKBOX_PREFERENCES = "Checkbox_Pref";
    public static final String WISHLIST_CHECKBOX_STATE_KEY = "wishlist_checkbox_state";

    public WishlistAdapter(Context context, List<WishlistEntity> wishlistEntities, RecipeClickListener listener,WishlistViewModel wishlistViewModel, int user_id) {
        this.wishlistEntities = wishlistEntities;
        this.listener = listener;
        this.context = context;
        this.user_id = user_id;
        this.wishlistViewModel = wishlistViewModel;
        this.sharedPreferences_checkbox = context.getSharedPreferences(CHECKBOX_PREFERENCES, Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public WishlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WishlistAdapter.WishlistViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_wishlist_recipes_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WishlistViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.slide_in_left);

        Picasso.get().load(wishlistEntities.get(position).getImage()).into(holder.wishlist_recipes_img);
        holder.txt_wishlist_recipes_servings.setText(String.valueOf(wishlistEntities.get(position).getServings()) + " serv");
        holder.txt_wishlist_recipes_time.setText(String.valueOf(wishlistEntities.get(position).getReadyInMinutes()) + " min");
        holder.txt_wishlist_recipes_title.setText(wishlistEntities.get(position).getTitle());

        holder.wishlist_recipe_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRecipeClicked(String.valueOf(wishlistEntities.get(holder.getAdapterPosition()).getRecipe_id()));
            }
        });

        // check if the recipe is in the wishlist
        boolean isChecked = sharedPreferences_checkbox.getBoolean(WISHLIST_CHECKBOX_STATE_KEY + "_" + user_id + "_" + wishlistEntities.get(position).getRecipe_id(), false);
        holder.wishlist_checkbox.setChecked(isChecked);

        holder.wishlist_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // remove the recipe from the wishlist
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        WishlistEntity existingItem = wishlistViewModel.getWishlistItem(user_id, wishlistEntities.get(position).getRecipe_id());
                        if (existingItem != null) {
                            wishlistViewModel.delete(existingItem.getId());
                        }
                    }
                }).start();

                Toast.makeText(context, "Recipe removed to wishlist", Toast.LENGTH_SHORT).show();
                Log.d("Add to wishlist", "Recipe removed to wishlist");

                // update the state of the checkbox in shared preferences
                sharedPreferences_checkbox.edit().putBoolean(WISHLIST_CHECKBOX_STATE_KEY + "_" + user_id + "_" + wishlistEntities.get(position).getRecipe_id(), isChecked).apply();

            }
        });


        holder.wishlist_recipes_img.startAnimation(animation);
        holder.txt_wishlist_recipes_title.startAnimation(animation);
        holder.txt_wishlist_recipes_servings.startAnimation(animation);
        holder.txt_wishlist_recipes_time.startAnimation(animation);
        holder.wishlist_checkbox.startAnimation(animation);

    }

    @Override
    public int getItemCount() {
        return wishlistEntities.size();
    }


    // Holder Class for recycler view
    class WishlistViewHolder extends RecyclerView.ViewHolder {
        TextView txt_wishlist_recipes_title, txt_wishlist_recipes_servings, txt_wishlist_recipes_time;
        ImageView wishlist_recipes_img;
        CardView wishlist_recipe_card;
        CheckBox wishlist_checkbox;


        public WishlistViewHolder(@NonNull View itemView) {
            super(itemView);
            wishlist_recipes_img = itemView.findViewById(R.id.wishlist_recipes_img);
            txt_wishlist_recipes_title = itemView.findViewById(R.id.txt_wishlist_recipes_title);
            txt_wishlist_recipes_servings = itemView.findViewById(R.id.txt_wishlist_recipes_servings);
            txt_wishlist_recipes_time = itemView.findViewById(R.id.txt_wishlist_recipes_time);
            wishlist_recipe_card = itemView.findViewById(R.id.wishlist_recipe_card);
            wishlist_checkbox = itemView.findViewById(R.id.wishlist_checkbox);

        }
    }
}
