package com.example.proiectandroid.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.proiectandroid.activities.LoginPageActivity;
import com.example.proiectandroid.database.AppDatabase;
import com.example.proiectandroid.models.User;

public class UserSessionManager {

    private static final String PREF_NAME = "user_session";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_USER_ID = "userId";

    private SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private Context context;
    private static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public UserSessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void createUserLoginSession(User user, AppDatabase db) {
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.putInt(KEY_USER_ID, user.getId());
        editor.apply();
        currentUser = db.getUserDAO().getUserById(user.getId());
    }

    public boolean isUserLoggedIn() {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public void checkLogin(Context context) {
        if (!isUserLoggedIn()) {
            logoutUser(context);
        } else {
            int userId = sharedPreferences.getInt(KEY_USER_ID, -1);
            currentUser = AppDatabase.getInstance(context).getUserDAO().getUserById(userId);
        }
    }

    public static void logoutUser(Context context) {
        editor.clear();
        editor.apply();
        currentUser = null;
        Intent i = new Intent(context, LoginPageActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
