package com.example.proiectandroid.utils;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.proiectandroid.models.Department;
import com.example.proiectandroid.models.Position;
import com.example.proiectandroid.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User("Dan", "Popa", "dp", "123", 20000, new Position("Senior", new Department("Technical", "Acesta e dep Tehnic"))));
        users.add(new User("Ana", "Ionescu", "a.ionescu@hotmail.com", "12345", 7000, new Position("Junior", new Department("Marketing & Sales", "Acest departament face MK si Sales"))));
        users.add(new User("Andrei", "Patronu", "ap@admin.com", "1234", true));
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
        Log.d("UserInfo", "Saving User - First Name: " + user.getFirstName() + ", Last Name: " + user.getLastName() + ", Email: " + user.getEmail() + " Salary: " + user.getSalary() + " Position: " + user.getPosition());
        UserSessionManager.setCurrentUser(user);
        users.add(user);
    }

    public static List<User> getUsers() {
        return users;
    }


    public static User getUserById(String userId) {
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    public static User getUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }
    public static void updateUser(User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(updatedUser.getId())) {
                users.set(i, updatedUser);
                break;
            }
        }
    }

}

