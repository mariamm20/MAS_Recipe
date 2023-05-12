package com.example.myapplication.RoomDatabase;


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
}
