package com.example.myapplication.Ingredients;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.myapplication.Home.Home;
import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Ingredients extends AppCompatActivity {
    ActionBar actionBar;
    BottomNavigationView navigationView;
    ImageButton back_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        ActionBar actionBar = getSupportActionBar();



        back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Ingredients.this, Home.class);
                startActivity(intent);
            }
        });
        navigationView = findViewById(R.id.taps_navigation);
        navigationView.setOnNavigationItemSelectedListener(selectedListener);



        IngredientFragment fragment = new IngredientFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.recipe_method, fragment, "");
        fragmentTransaction.commit();


    }
    private BottomNavigationView.OnNavigationItemSelectedListener selectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        private MenuItem item;

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
                    StepsFragment fragment4 = new StepsFragment();
                    FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction1.replace(R.id.recipe_method, fragment4);
                    fragmentTransaction1.commit();
                    return true;


            }
            return false;
        }
    };

}