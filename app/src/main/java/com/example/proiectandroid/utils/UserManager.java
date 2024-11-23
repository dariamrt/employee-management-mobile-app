package com.example.proiectandroid.utils;

import android.content.Context;

import com.example.proiectandroid.database.AppDatabase;
import com.example.proiectandroid.models.User;
import com.example.proiectandroid.models.UserDao;

import java.util.List;

public class UserManager {

    private static UserDao userDao;

    public static void initialize(Context context) {
        AppDatabase database = AppDatabase.getInstance(context);
        userDao = database.getUserDAO();
    }

    public static User authenticateUser(String email, String password) {
        User user = userDao.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            UserSessionManager.setCurrentUser(user);
            return user;
        }
        return null;
    }

    public static boolean doesUserExist(String email) {
        return userDao.getUserByEmail(email) != null;
    }

    public static void addUser(User user) {
        userDao.insertUser(user);
        UserSessionManager.setCurrentUser(user);
    }

    public static List<User> getUsers() {
        return userDao.getAllUsers();
    }

    public static User getUserById(int userId) {
        return userDao.getUserById(userId);
    }

    public static User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    public static void updateUser(User updatedUser) {
        userDao.updateUser(updatedUser);
    }
}
