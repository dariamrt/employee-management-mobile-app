package com.example.proiectandroid.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingsManager {

    private static final String PREFS_NAME = "user_settings";
    private static final String KEY_THEME = "theme";

    private SharedPreferences sharedPreferences;

    public SettingsManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void setTheme(String theme) {
        sharedPreferences.edit().putString(KEY_THEME, theme).apply();
    }

    public String getTheme() {
        return sharedPreferences.getString(KEY_THEME, "default");
    }
}
