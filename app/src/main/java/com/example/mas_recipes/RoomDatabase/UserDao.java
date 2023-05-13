package com.example.mas_recipes.RoomDatabase;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDao {
    @Insert
    void registerUser(UserEntity userEntity);

    @Query("SELECT * from users where email = (:email) and password = (:password) ")
    UserEntity login(String email, String password);

    // to check is this email is already existed or not
    @Query("SELECT * from users where email = (:email)")
    UserEntity checkEmail(String email);

    @Query("SELECT * FROM users WHERE id = :profileId")
    UserEntity getProfileById(int profileId);
    @Update
    void updateProfile(UserEntity userEntity);
    @Query("UPDATE users SET is_logged = 0 WHERE id = :userId")
    void logout(int userId);
}