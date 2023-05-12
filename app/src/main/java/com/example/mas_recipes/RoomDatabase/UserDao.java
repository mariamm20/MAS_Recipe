package com.example.mas_recipes.RoomDatabase;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Insert
    void registerUser(UserEntity userEntity);

    @Query("SELECT * from users where email = (:email) and password = (:password) ")
    UserEntity login(String email, String password);

    // to check is this email is already existed or not
    @Query("SELECT * from users where email = (:email)")
    UserEntity checkEmail(String email);

    @Query("SELECT id FROM users WHERE email = :email")
    int getUserIdByEmail(String email);
}