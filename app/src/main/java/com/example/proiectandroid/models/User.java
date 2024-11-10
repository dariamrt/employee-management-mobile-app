package com.example.proiectandroid.models;

import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private double salary;
    private Position position;
    private Boolean isAdmin;

    public User(String firstName, String lastName, String email, String password, double salary, Position position) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.salary = salary;
        this.position = position;
        this.isAdmin = false;
    }

    public User(String firstName, String lastName, String email, String password) {
        this(firstName, lastName, email, password, 0, new Position("No Position", new Department("Unassigned", "General Department")));
    }

    public User(String firstName, String lastName, String email, String password, Boolean isAdmin) {
        this(firstName, lastName, email, password, 21000, new Position("Admin", new Department("Tech Dep", "Admin")));
        this.isAdmin = isAdmin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "User " + id +
                ": " + lastName + ", " + firstName + ", " + email;
    }

}
