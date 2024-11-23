package com.example.proiectandroid.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity(tableName = "tasks",
        foreignKeys = @ForeignKey(
                entity = User.class,
                parentColumns = "id",
                childColumns = "assignedUserId",
                onDelete = ForeignKey.CASCADE
        )
)
public class Task implements Serializable {
    @PrimaryKey (autoGenerate = true)
    private int id;
    private String title;
    private String description;
    private String deadline;
    private String status;
    private int assignedUserId;  // am facut one to many ))

    public Task(String title, String description, String deadline, String status, int assignedUserId) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
        this.assignedUserId = assignedUserId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAssignedUserId() {
        return assignedUserId;
    }

    public void setAssignedUserId(int assignedUserId) {
        this.assignedUserId = assignedUserId;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", status=" + status +
                ", assignedUserId=" + assignedUserId +
                '}';
    }
}
