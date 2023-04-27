package com.example.myapplication.Adapter;
import static android.app.PendingIntent.getActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Ingredients.Ingredients;
import com.example.myapplication.R;
import com.example.myapplication.Items.RecipeItem;

import java.util.ArrayList;
public class RecipeRecyclerViewAdapter extends RecyclerView.Adapter<RecipeRecyclerViewAdapter.RecipiesViewHolder> {
    Context context;
    ArrayList<RecipeItem>  Recipies;
    public RecipeRecyclerViewAdapter(Context context, ArrayList<RecipeItem> recipies) {
           this.Recipies = recipies;
           this.context = context;
    }
    @NonNull
    @Override
    public RecipiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.recipe_custom_item, parent, false);
        RecipiesViewHolder viewHolder = new RecipiesViewHolder(v);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull RecipiesViewHolder holder, int position) {
        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.slide_in_left);

        RecipeItem r = Recipies.get(position);
        holder.iv_image.setImageResource(r.getRecipe_img());
        holder.recipe_rate.setText(r.getRecipe_rate());
        holder.recipe_time.setText(r.getRecipe_time());
        holder.recipe_name.setText(r.getRecipe_name());

        holder.iv_image.startAnimation(animation);
        holder.recipe_name.startAnimation(animation);
        holder.recipe_rate.startAnimation(animation);
        holder.recipe_time.startAnimation(animation);

        holder.recipe_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Ingredients.class);
                context.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return Recipies.size();
    }



    // Holder Class for recycler view
    class RecipiesViewHolder extends RecyclerView.ViewHolder{
        TextView recipe_name,recipe_rate,recipe_time;
        ImageView iv_image;
        CardView recipe_card;

        public RecipiesViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_image = itemView.findViewById(R.id.recipie_image);
            recipe_name = itemView.findViewById(R.id.recipe_name);
            recipe_rate =  itemView.findViewById(R.id.recipie_rate);
            recipe_time = itemView.findViewById(R.id.recipe_time);
            recipe_card = itemView.findViewById(R.id.recipe_card);
        }
    }
}
