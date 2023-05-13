package com.example.mas_recipes.RoomDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserEntity.class, WishlistEntity.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "recipe_db";
    private static AppDatabase instance;

    public abstract UserDao userDao();

    public abstract WishlistDao wishlistDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}