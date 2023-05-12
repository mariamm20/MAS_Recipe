package com.example.mas_recipes.RecipeDetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mas_recipes.API.Listeners.RecipeDetailsListener;
import com.example.mas_recipes.API.Models.RecipeDetailsResponse;
import com.example.mas_recipes.API.RequestManager;
import com.example.mas_recipes.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

public class RecipeDetailsActivity extends AppCompatActivity {

    BottomNavigationView navigationView;
    ImageButton back_btn;

    ImageView details_recipe_img, share_btn2;
    CheckBox details_recipe_checkbox;
    TextView txt_details_recipe_title, txt_details_recipe_rating, txt_details_recipe_time, txt_details_recipe_serving;

    int id;
    ProgressDialog dialog;
    RequestManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        findView();

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        navigationView.setOnNavigationItemSelectedListener(selectedListener);

        IngredientFragment fragment = new IngredientFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.recipe_method, fragment, "");
        fragmentTransaction.commit();

        id = Integer.parseInt(getIntent().getStringExtra("id"));
        manager = new RequestManager(this);

        //recipe details
        manager.getRecipesDetails(recipeDetailsListener, id);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading Details...");
        dialog.show();


    }

    private void findView() {
        back_btn = findViewById(R.id.back_btn);
        share_btn2 = findViewById(R.id.share_btn2);
        navigationView = findViewById(R.id.taps_navigation);
        details_recipe_checkbox = findViewById(R.id.details_recipe_checkbox);
        txt_details_recipe_title = findViewById(R.id.txt_details_recipe_title);
        txt_details_recipe_rating = findViewById(R.id.txt_details_recipe_rating);
        txt_details_recipe_serving = findViewById(R.id.txt_details_recipe_serving);
        txt_details_recipe_time = findViewById(R.id.txt_details_recipe_time);
        details_recipe_img = findViewById(R.id.details_recipe_img);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener selectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {

                case R.id.ingredient:

                    IngredientFragment fragment3 = new IngredientFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.recipe_method, fragment3, "");
                    fragmentTransaction.commit();
                    return true;

                case R.id.steps:
                    InstructionsFragment fragment4 = new InstructionsFragment();
                    FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction1.replace(R.id.recipe_method, fragment4);
                    fragmentTransaction1.commit();
                    return true;

                    case R.id.similar_recipes:
                    SimilarRecipesFragment fragment5 = new SimilarRecipesFragment();
                    FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction2.replace(R.id.recipe_method, fragment5);
                    fragmentTransaction2.commit();
                    return true;
            }
            return false;
        }
    };

    private final RecipeDetailsListener recipeDetailsListener = new RecipeDetailsListener() {
        @Override
        public void didFetch(RecipeDetailsResponse response, String msg) {
            dialog.dismiss();
            txt_details_recipe_title.setText(response.title);
            txt_details_recipe_time.setText(String.valueOf(response.readyInMinutes) + " Minutes");
            txt_details_recipe_serving.setText(String.valueOf(response.servings) + " Servings");
            txt_details_recipe_rating.setText("(" + String.valueOf(response.aggregateLikes) + " Likes)");
            Picasso.get().load(response.image).into(details_recipe_img);

            details_recipe_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    String msg = isChecked ? "Recipe added to wishlist" : "Recipe removed from wishlist";
                    details_recipe_checkbox.setChecked(isChecked);
                    Toast.makeText(RecipeDetailsActivity.this, msg, Toast.LENGTH_SHORT).show();
                    Log.d("wishlist", msg);
                }
            });
            
            share_btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String sourceUrl = response.sourceUrl;

                    // Create an intent to share the source URL
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Check out this recipe!");
                    shareIntent.putExtra(Intent.EXTRA_TEXT, sourceUrl);

                    try {
                        // Start the share activity
                        startActivity(Intent.createChooser(shareIntent, "Share via"));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(RecipeDetailsActivity.this, "There are no source url to share...", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

        @Override
        public void didError(String msg) {
            Toast.makeText(RecipeDetailsActivity.this, msg, Toast.LENGTH_SHORT).show();

        }
    };
}