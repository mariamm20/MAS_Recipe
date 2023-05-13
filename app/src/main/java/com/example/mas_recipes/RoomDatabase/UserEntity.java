package com.example.mas_recipes.RoomDatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class UserEntity
{
    @PrimaryKey(autoGenerate = true)
    Integer id;

    @ColumnInfo(name = "userName")
    String userName;
    @ColumnInfo(name = "email")
    String email;
    @ColumnInfo(name = "password")
    String password;
    @ColumnInfo(name = "confirmPassword")
    String confirmPassword;
    @ColumnInfo(name = "is_logged")
    Boolean is_logged;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Boolean getIs_logged() {
        return is_logged;
    }

    public void setIs_logged(Boolean is_logged) {
        this.is_logged = is_logged;
    }
}