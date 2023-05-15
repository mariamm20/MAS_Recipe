package com.example.mas_recipes.RoomDatabase;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WishlistRepository {
    private WishlistDao wishlistDao;
    private LiveData<List<WishlistEntity>> wishlistEntities;

    public WishlistRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        wishlistDao = database.wishlistDao();
        wishlistEntities = wishlistDao.getAll();
    }

    public LiveData<List<WishlistEntity>> getWishlistEntities() {
        return wishlistEntities;
    }

    public void insert(WishlistEntity wishlistEntity) {
        AppDatabase.executorService.execute(() -> wishlistDao.insert(wishlistEntity));
    }

    public WishlistEntity getWishlistItem(int user_id, int recipe_id) {
        return wishlistDao.getWishlistItem(user_id, recipe_id);
    }

    public LiveData<List<WishlistEntity>> getWishlistItemByUserID(int user_id) {
        return wishlistDao.getWishlistItemByUserID(user_id);
    }

    public LiveData<List<WishlistEntity>> getLiveWishlistItem(int user_id, int recipe_id) {
        return wishlistDao.getLiveWishlistItem(user_id, recipe_id);
    }

    public LiveData<Integer> getCount(int user_id) {
        return wishlistDao.getCount(user_id);
    }

    public void delete(int user_id, int recipe_id) {
        AppDatabase.executorService.execute(() -> wishlistDao.delete(user_id, recipe_id));
    }
}
