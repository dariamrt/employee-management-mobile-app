package com.example.proiectandroid.utils;

import com.example.proiectandroid.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User("Dan", "Popa", "dan.popa@gmail.com", "parola123"));
        users.add(new User("Ana", "Ionescu", "a.ionescu@hotmail.com", "parolaMaiSmechera!123"));
    }

    public static User authenticateUser(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
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
        users.add(user);
    }

    public static List<User> getUsers() {
        return users;
    }
}
