package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Ingredients extends AppCompatActivity {
    ActionBar actionBar;
    BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        ActionBar actionBar = getSupportActionBar();



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