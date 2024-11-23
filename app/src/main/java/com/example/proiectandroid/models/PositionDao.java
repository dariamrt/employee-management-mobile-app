package com.example.proiectandroid.models;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PositionDao {
    @Insert
    void insertPosition(Position position);

    @Query("SELECT id FROM POSITIONS WHERE title = :positionName LIMIT 1")
    int getPositionIdByName(String positionName);

    @Query("SELECT * FROM positions")
    List<Position> getAllPositions();

    @Delete
    void deletePosition(Position position);

    @Query("SELECT * FROM positions WHERE id = :id LIMIT 1")
    Position getPositionById(int id);
}
