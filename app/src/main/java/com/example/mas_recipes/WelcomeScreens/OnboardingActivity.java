package com.example.mas_recipes.WelcomeScreens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.mas_recipes.Home.HomeActivity;
import com.example.mas_recipes.R;

public class OnboardingActivity extends AppCompatActivity {


    ViewPager viewPager;
    LinearLayout dot_layout;
    ImageButton onboarding_back_btn, onboarding_next_btn;
    Button onboarding_skip_btn;

    ImageView[] dots;
    OnboardingViewPagerAdapter onboardingViewPagerAdapter;
    LinearLayout.LayoutParams params;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        findView();

        onboarding_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getitem(0) > 0) {
                    viewPager.setCurrentItem(getitem(-1), true);
                }
            }
        });

        onboarding_next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPage = viewPager.getCurrentItem();
                int lastPage = viewPager.getAdapter().getCount() - 1;

                if (currentPage < lastPage) {
                    // Not on the last page yet, so go to the next page.
                    viewPager.setCurrentItem(currentPage + 1, true);
                } else {
                    // On the last page, so start the LoginActivity and change the button text.
                    Intent i = new Intent(OnboardingActivity.this, HomeActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });

        onboarding_skip_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OnboardingActivity.this, HomeActivity.class);
                startActivity(i);
                finish();

            }
        });

        onboardingViewPagerAdapter = new OnboardingViewPagerAdapter(this);
        viewPager.setAdapter(onboardingViewPagerAdapter);
        setUpIndicator(0);
        viewPager.addOnPageChangeListener(viewListener);

    }

    private void findView() {
        onboarding_back_btn = findViewById(R.id.onboarding_back_btn);
        onboarding_next_btn = findViewById(R.id.onboarding_next_btn);
        onboarding_skip_btn = findViewById(R.id.onboarding_skip_btn);
        viewPager = (ViewPager) findViewById(R.id.slideViewPager);
        dot_layout = (LinearLayout) findViewById(R.id.indicator_layout);
    }

    public void setUpIndicator(int position) {
        dots = new ImageView[3];
        dot_layout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageResource(R.drawable.circle);
            params = new LinearLayout.LayoutParams(30, 30);
            params.setMargins(20, 0, 20, 0);

            if (i == position) {
                dots[i].setImageResource(R.drawable.circle2);
                params = new LinearLayout.LayoutParams(70, 30);
            }
            dots[i].setLayoutParams(params);
            dot_layout.addView(dots[i]);
        }

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            setUpIndicator(position);

            if (position > 0) {
                onboarding_back_btn.setVisibility(View.VISIBLE);
            } else {
                onboarding_back_btn.setVisibility(View.INVISIBLE);
            }
            if (position == viewPager.getAdapter().getCount() - 1) {
                onboarding_next_btn.setImageResource(R.drawable.ic_baseline_correct_24);
            } else {
                onboarding_next_btn.setImageResource(R.drawable.ic_next);

            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private int getitem(int i) {
        return viewPager.getCurrentItem() + i;
    }


}
