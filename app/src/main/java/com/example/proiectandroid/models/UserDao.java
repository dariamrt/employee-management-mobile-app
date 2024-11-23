package com.example.proiectandroid.models;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM users")
    List<User> getAllUsers();

    @Delete
    void deleteUser(User user);

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    User getUserByEmail(String email);

    @Query("SELECT * FROM users WHERE id = :id LIMIT 1")
    User getUserById(int id);

    @Update
    void updateUser(User user);

    @Query("SELECT COUNT(*) FROM users")
    int getUserCount();

    @Query("SELECT id FROM users WHERE email = :email LIMIT 1")
    int getUserIdByEmail(String email);
}
