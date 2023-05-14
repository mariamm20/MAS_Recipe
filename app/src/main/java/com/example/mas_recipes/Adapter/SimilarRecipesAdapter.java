package com.example.mas_recipes.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.example.mas_recipes.API.Models.SimilarRecipesResponse;
import com.example.mas_recipes.R;
import com.example.mas_recipes.RoomDatabase.WishlistEntity;
import com.example.mas_recipes.RoomDatabase.WishlistViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SimilarRecipesAdapter extends RecyclerView.Adapter<SimilarRecipesAdapter.SimilarRecipesHolder> {

    Context context;
    List<SimilarRecipesResponse> similarRecipesList;
    RecipeClickListener listener;
    WishlistViewModel wishlistViewModel;

    int user_id;
    SharedPreferences sharedPreferences_checkbox;
    public static final String CHECKBOX_PREFERENCES = "Checkbox_Pref";
    public static final String WISHLIST_CHECKBOX_STATE_KEY = "wishlist_checkbox_state";

    public SimilarRecipesAdapter(Context context, List<SimilarRecipesResponse> similarRecipesList, RecipeClickListener listener, WishlistViewModel wishlistViewModel, int user_id) {
        this.context = context;
        this.similarRecipesList = similarRecipesList;
        this.listener = listener;
        this.wishlistViewModel= wishlistViewModel;
        this.user_id = user_id;
        this.sharedPreferences_checkbox = context.getSharedPreferences(CHECKBOX_PREFERENCES, Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public SimilarRecipesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SimilarRecipesHolder(LayoutInflater.from(context).inflate(R.layout.rv_similar_recipes_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SimilarRecipesHolder holder, @SuppressLint("RecyclerView") int position) {
        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.slide_in_left);

        holder.txt_similar_recipes_title.setText(similarRecipesList.get(position).title);
        holder.txt_similar_recipes_time.setText(String.valueOf(similarRecipesList.get(position).readyInMinutes) + " min");
        holder.txt_similar_recipes_servings.setText(String.valueOf(similarRecipesList.get(position).servings) + " serv");
        Picasso.get().load("https://spoonacular.com/recipeImages/"
                        + similarRecipesList.get(position).id
                        + "-556x370."
                        + similarRecipesList.get(position).imageType)
                .into(holder.similar_recipe_img);
        holder.similar_recipe_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRecipeClicked(String.valueOf(similarRecipesList.get(holder.getAdapterPosition()).id));
            }
        });

        // check if the recipe is in the wishlist
        boolean isChecked = sharedPreferences_checkbox.getBoolean(WISHLIST_CHECKBOX_STATE_KEY + "_" + user_id + "_" + similarRecipesList.get(position).id, false);
        holder.similar_recipes_checkbox.setChecked(isChecked);

        holder.similar_recipes_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                WishlistEntity wishlistEntity = new WishlistEntity();
                wishlistEntity.setRecipe_id(similarRecipesList.get(position).id);
                wishlistEntity.setServings(similarRecipesList.get(position).servings);
                wishlistEntity.setTitle(similarRecipesList.get(position).title);
                wishlistEntity.setReadyInMinutes(similarRecipesList.get(position).readyInMinutes);

                String imageUrl = "https://spoonacular.com/recipeImages/"
                        + similarRecipesList.get(position).id
                        + "-556x370."
                        + similarRecipesList.get(position).imageType;
                wishlistEntity.setImage(imageUrl);

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
                            WishlistEntity existingItem = wishlistViewModel.getWishlistItem(user_id, similarRecipesList.get(position).id);
                            if (existingItem != null) {
                                wishlistViewModel.delete(existingItem.getId());
                            }
                        }
                    }).start();
                    Toast.makeText(context, "Recipe removed to wishlist", Toast.LENGTH_SHORT).show();
                    Log.d("Add to wishlist", "Recipe removed to wishlist");
                }

                // update the state of the checkbox in shared preferences
                sharedPreferences_checkbox.edit().putBoolean(WISHLIST_CHECKBOX_STATE_KEY + "_" + user_id + "_" + similarRecipesList.get(position).id, isChecked).apply();

            }
        });

        holder.txt_similar_recipes_title.startAnimation(animation);
        holder.txt_similar_recipes_time.startAnimation(animation);
        holder.txt_similar_recipes_servings.startAnimation(animation);
        holder.similar_recipe_img.startAnimation(animation);
        holder.similar_recipes_checkbox.startAnimation(animation);

    }

    @Override
    public int getItemCount() {
        return similarRecipesList.size();
    }

    class SimilarRecipesHolder extends RecyclerView.ViewHolder {
        CardView similar_recipe_card;
        TextView txt_similar_recipes_title, txt_similar_recipes_time, txt_similar_recipes_servings;
        ImageView similar_recipe_img;
        CheckBox similar_recipes_checkbox;

        public SimilarRecipesHolder(@NonNull View itemView) {
            super(itemView);

            similar_recipe_card = itemView.findViewById(R.id.similar_recipe_card);
            similar_recipe_img = itemView.findViewById(R.id.similar_recipes_img);
            txt_similar_recipes_title = itemView.findViewById(R.id.txt_similar_recipes_title);
            txt_similar_recipes_servings = itemView.findViewById(R.id.txt_similar_recipes_servings);
            txt_similar_recipes_time = itemView.findViewById(R.id.txt_similar_recipes_time);
            similar_recipes_checkbox = itemView.findViewById(R.id.similar_recipes_checkbox);
        }
    }
}
