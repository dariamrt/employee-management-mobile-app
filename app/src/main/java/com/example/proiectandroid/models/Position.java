package com.example.proiectandroid.models;

import java.io.Serializable;

public class Position implements Serializable {
    private String title;
    private Department department;

    public Position(String title, Department department) {
        this.title = title;
        this.department = department;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Position{" +
                "title='" + title + '\'' +
                ", department=" + department.getName() +
                '}';
    }
}
