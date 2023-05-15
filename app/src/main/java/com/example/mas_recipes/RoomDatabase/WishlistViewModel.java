package com.example.mas_recipes.RoomDatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WishlistViewModel extends AndroidViewModel {
    private WishlistRepository wishlistRepository;
    private LiveData<List<WishlistEntity>> wishlistEntities;

    public WishlistViewModel(@NonNull Application application) {
        super(application);
        wishlistRepository = new WishlistRepository(application);
        wishlistEntities = wishlistRepository.getWishlistEntities();
    }

    public LiveData<List<WishlistEntity>> getWishlistEntities() {
        return wishlistEntities;
    }

    public void insert(WishlistEntity wishlistEntity) {
        wishlistRepository.insert(wishlistEntity);
    }

    public WishlistEntity getWishlistItem(int user_id, int recipe_id) {
        return wishlistRepository.getWishlistItem(user_id, recipe_id);
    }

    public LiveData<List<WishlistEntity>> getWishlistItemByUserID(int user_id) {
        return wishlistRepository.getWishlistItemByUserID(user_id);
    }

    public LiveData<List<WishlistEntity>> getLiveWishlistItem(int user_id, int recipe_id) {
        return wishlistRepository.getLiveWishlistItem(user_id, recipe_id);
    }

    public LiveData<Integer> getCount(int user_id) {
        return wishlistRepository.getCount(user_id);
    }

    public void delete(int user_id, int recipe_id) {
        wishlistRepository.delete(user_id, recipe_id);
    }
}
