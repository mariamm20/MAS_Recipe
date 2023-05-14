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
import com.example.mas_recipes.API.Models.Recipe;
import com.example.mas_recipes.R;
import com.example.mas_recipes.RoomDatabase.WishlistEntity;
import com.example.mas_recipes.RoomDatabase.WishlistViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RandomRecipesAdapter extends RecyclerView.Adapter<RandomRecipesAdapter.RandomRecipesHolder> {
    Context context;
    List<Recipe> recipeList;
    RecipeClickListener listener;

    WishlistViewModel wishlistViewModel;

    int user_id;
    SharedPreferences sharedPreferences_checkbox;
    public static final String CHECKBOX_PREFERENCES = "Checkbox_Pref";
    public static final String WISHLIST_CHECKBOX_STATE_KEY = "wishlist_checkbox_state";

    public RandomRecipesAdapter(Context context, List<Recipe> recipeList, RecipeClickListener listener, WishlistViewModel wishlistViewModel, int user_id) {
        this.context = context;
        this.recipeList = recipeList;
        this.listener = listener;
        this.wishlistViewModel = wishlistViewModel;
        this.user_id = user_id;
        this.sharedPreferences_checkbox = context.getSharedPreferences(CHECKBOX_PREFERENCES, Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public RandomRecipesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomRecipesHolder(LayoutInflater.from(context).inflate(R.layout.rv_reandom_recipes_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipesHolder holder, @SuppressLint("RecyclerView") int position) {
        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.slide_in_left);

        holder.txt_random_recipes_title.setText(recipeList.get(position).title);
        holder.txt_random_recipes_rating.setText(String.valueOf(recipeList.get(position).aggregateLikes));
        holder.txt_random_recipes_time.setText(String.valueOf(recipeList.get(position).readyInMinutes) + " Minutes");
        holder.txt_random_recipes_serving.setText(String.valueOf(recipeList.get(position).servings) + " Servings");
        Picasso.get().load(recipeList.get(position).image).into(holder.random_recipes_img);

        holder.random_recipes_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRecipeClicked(String.valueOf(recipeList.get(holder.getAdapterPosition()).id));
            }
        });

        holder.share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sourceUrl = recipeList.get(position).sourceUrl;

                // Create an intent to share the source URL
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Check out this recipe!");
                shareIntent.putExtra(Intent.EXTRA_TEXT, sourceUrl);

                try {
                    // Start the share activity
                    context.startActivity(Intent.createChooser(shareIntent, "Share via"));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(context, "There are no source url to share...", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // check if the recipe is in the wishlist
        boolean isChecked = sharedPreferences_checkbox.getBoolean(WISHLIST_CHECKBOX_STATE_KEY + "_" + user_id + "_" + recipeList.get(position).id, false);
        holder.random_recipes_checkbox.setChecked(isChecked);

        holder.random_recipes_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                WishlistEntity wishlistEntity = new WishlistEntity();

                wishlistEntity.setRecipe_id(recipeList.get(position).id);
                wishlistEntity.setImage(recipeList.get(position).image);
                wishlistEntity.setServings(recipeList.get(position).servings);
                wishlistEntity.setTitle(recipeList.get(position).title);
                wishlistEntity.setReadyInMinutes(recipeList.get(position).readyInMinutes);

                wishlistEntity.setUser_id(user_id);

                if (isChecked) {
                    // add the recipe to the wishlist
                    wishlistViewModel.insert(wishlistEntity);
                    Toast.makeText(context, "Recipe added to wishlist", Toast.LENGTH_SHORT).show();
                    Log.d("Add to wishlist", "Recipe added to wishlist");

                } else {
                    // remove the recipe from the wishlist
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            WishlistEntity existingItem = wishlistViewModel.getWishlistItem(user_id, recipeList.get(position).id);
                            if (existingItem != null) {
                                wishlistViewModel.delete(existingItem.getId());
                                }
                        }
                    }).start();
                    Toast.makeText(context, "Recipe removed to wishlist", Toast.LENGTH_SHORT).show();
                    Log.d("Add to wishlist", "Recipe removed to wishlist");
                }

                // update the state of the checkbox in shared preferences
                sharedPreferences_checkbox.edit().putBoolean(WISHLIST_CHECKBOX_STATE_KEY + "_" + user_id + "_" + recipeList.get(position).id, isChecked).apply();

            }
        });

        holder.random_recipes_img.startAnimation(animation);
        holder.txt_random_recipes_title.startAnimation(animation);
        holder.txt_random_recipes_rating.startAnimation(animation);
        holder.txt_random_recipes_time.startAnimation(animation);
        holder.random_recipes_checkbox.startAnimation(animation);

    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }


    // Holder Class for recycler view
    class RandomRecipesHolder extends RecyclerView.ViewHolder {
        TextView txt_random_recipes_title, txt_random_recipes_rating, txt_random_recipes_time, txt_random_recipes_serving;
        ImageView random_recipes_img;
        CardView random_recipes_card;
        ImageView share_btn;
        CheckBox random_recipes_checkbox;

        public RandomRecipesHolder(@NonNull View itemView) {
            super(itemView);
            random_recipes_img = itemView.findViewById(R.id.random_recipes_img);
            txt_random_recipes_title = itemView.findViewById(R.id.txt_random_recipes_title);
            txt_random_recipes_rating = itemView.findViewById(R.id.txt_random_recipes_rating);
            txt_random_recipes_time = itemView.findViewById(R.id.txt_random_recipes_time);
            txt_random_recipes_serving = itemView.findViewById(R.id.txt_random_recipes_serving);
            random_recipes_card = itemView.findViewById(R.id.random_recipes_card);
            share_btn = itemView.findViewById(R.id.share_btn);
            random_recipes_checkbox = itemView.findViewById(R.id.random_recipes_checkbox);
        }
    }


}
