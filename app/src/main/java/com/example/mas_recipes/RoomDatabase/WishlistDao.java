package com.example.mas_recipes.RoomDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WishlistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(WishlistEntity wishlistEntity);

    @Query("SELECT * FROM wishlist")
    LiveData<List<WishlistEntity>> getAll();

    @Query("SELECT * FROM wishlist WHERE user_id = :user_id AND recipe_id = :recipe_id")
    WishlistEntity getWishlistItem(int user_id, int recipe_id);

    @Query("SELECT * FROM wishlist WHERE user_id = :user_id")
    LiveData<List<WishlistEntity>> getWishlistItemByUserID(int user_id);

    @Query("DELETE FROM wishlist WHERE id = :id")
    void delete(int id);
}
