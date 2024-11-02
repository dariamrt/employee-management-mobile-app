package com.example.proiectandroid.utils;

import android.util.Log;

import com.example.proiectandroid.models.Department;
import com.example.proiectandroid.models.Position;
import com.example.proiectandroid.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User("Dan", "Popa", "dan.popa@gmail.com", "parola123", 20000, new Position("Senior", new Department("Technical", "Acesta e dep Tehnic"))));
        users.add(new User("Ana", "Ionescu", "a.ionescu@hotmail.com", "12345", 7000, new Position("Junior", new Department("Marketing & Sales", "Acest departament face MK si Sales"))));
    }

    public static User authenticateUser(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                UserSessionManager.setCurrentUser(user);
                return user;
            }
        }
        return null;
    }

    // verifica daca exista userul dupa email
    public static boolean doesUserExist(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    // adauga userul in lista
    public static void addUser(User user) {
        Log.d("UserInfo", "Saving User - First Name: " + user.getFirstName() + ", Last Name: " + user.getLastName() + ", Email: " + user.getEmail() +" Salary: " + user.getSalary() + " Position: " + user.getPosition());
        UserSessionManager.setCurrentUser(user);
        users.add(user);
    }

    public static List<User> getUsers() {
        return users;
    }

}
