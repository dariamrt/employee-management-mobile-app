package com.example.proiectandroid.models;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DepartmentDao {
    @Insert
    void insertDepartment(Department department);

    @Query("SELECT * FROM departments")
    List<Department> getAllDepartments();

    @Delete
    void deleteDepartment(Department department);

    @Query("SELECT * FROM departments WHERE id = :id LIMIT 1")
    Department getDepartmentById(int id);

    @Query("SELECT id FROM departments WHERE name = :name LIMIT 1")
    int getDepartmentId(String name);

    @Query("SELECT name FROM departments WHERE id = :departmentId LIMIT 1")
    String getDepartmentNameById(int departmentId);
}
