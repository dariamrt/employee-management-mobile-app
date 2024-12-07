package com.example.proiectandroid.models;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert
    void insertTask(Task task);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTasks(List<Task> tasks);

    @Query("SELECT * FROM tasks")
    List<Task> getAllTasks();

    @Delete
    void deleteTask(Task task);

    @Query("SELECT * FROM tasks WHERE id = :id LIMIT 1")
    Task getTaskById(int id);

    @Update
    void updateTask(Task task);

    @Query("SELECT * FROM tasks WHERE assignedUserId = :userId")
    List<Task> getTasksByUserId(int userId);

    @Query("SELECT * FROM tasks WHERE assignedUserId = :userId")
    List<Task> getTasksByAssignedUserId(int userId);
}
