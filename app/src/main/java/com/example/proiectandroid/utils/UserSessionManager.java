package com.example.proiectandroid.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.example.proiectandroid.activities.LoginPageActivity;

public class UserSessionManager {
    // de testat etc dupa ce implementez conexiunea cu BD
    private static final String PREF_NAME = "user_session";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_USER_EMAIL = "userEmail";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public UserSessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    // seteaza sesiunea userului ca activa + salv emailul
    public void createUserLoginSession(String email) {
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.putString(KEY_USER_EMAIL, email);
        editor.apply();
    }

    // verif dacă userul e logat
    public boolean isUserLoggedIn() {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    // redir userul la LOGIN daca nu e logat
    public void checkLogin() {
        if (!isUserLoggedIn()) {
            Intent i = new Intent(context, LoginPageActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }

    // get pt emailul userului autentificat
    public String getUserEmail() {
        return sharedPreferences.getString(KEY_USER_EMAIL, null);
    }

    // sterge sesiunea userului
    public void logoutUser() {
        editor.clear();
        editor.apply();

        Intent i = new Intent(context, LoginPageActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}

