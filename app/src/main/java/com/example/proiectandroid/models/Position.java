package com.example.proiectandroid.models;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "positions",
        foreignKeys = @ForeignKey(
                entity = Department.class,
                parentColumns = "id",
                childColumns = "departmentId",
                onDelete = ForeignKey.CASCADE
        )
)
public class Position implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private int departmentId;

    public Position(String title, int departmentId) {
        this.title = title;
        this.departmentId = departmentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", departmentId=" + departmentId +
                '}';
    }
}
