package com.example.mas_recipes.WelcomeScreens;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.mas_recipes.R;

public class OnboardingViewPagerAdapter extends PagerAdapter {
    Context context;

    int images[] = {
            R.drawable.onboarding_bac_1,
            R.drawable.onboarding_bac_2,
            R.drawable.onboarding_bac_3
    };

    int titles[] = {
            R.string.title_1,
            R.string.title_2,
            R.string.title_3
    };

    int description[] = {
            R.string.description_1,
            R.string.description_2,
            R.string.description_3
    };

    public OnboardingViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @SuppressLint("MissingInflatedId")
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.onboarding_slider_layout, container, false);

         ImageView slide_img = (ImageView) view.findViewById(R.id.slide_img);
        TextView slide_title = (TextView) view.findViewById(R.id.slide_title);
        TextView slide_description = (TextView) view.findViewById(R.id.slide_description);

        slide_img.setImageResource(images[position]);
        slide_title.setText(titles[position]);
        slide_description.setText(description[position]);

        container.addView(view);
        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }



}
