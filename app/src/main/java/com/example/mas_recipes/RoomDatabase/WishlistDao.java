package com.example.mas_recipes.RoomDatabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WishlistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(WishlistEntity wishlistEntity);

    @Query("SELECT * FROM wishlist WHERE user_id = :user_id")
    List<WishlistEntity> getWishlist(int user_id);

    @Query("DELETE FROM wishlist WHERE id = :id")
    void delete(int id);
}
