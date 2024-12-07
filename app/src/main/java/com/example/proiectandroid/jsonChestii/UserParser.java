package com.example.proiectandroid.jsonChestii;

import com.example.proiectandroid.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserParser {
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String SALARY = "salary";
    private static final String POSITION_ID = "positionId";
    private static final String IS_ADMIN = "isAdmin";

    private static User parsareUser(JSONObject jsonObject) throws JSONException {
        String firstName = jsonObject.getString(FIRST_NAME);
        String lastName = jsonObject.getString(LAST_NAME);
        String email = jsonObject.getString(EMAIL);
        String password = jsonObject.getString(PASSWORD);
        double salary = jsonObject.getDouble(SALARY);
        int positionId = jsonObject.getInt(POSITION_ID);
        boolean isAdmin = jsonObject.getBoolean(IS_ADMIN);

        return new User(firstName, lastName, email, password, salary, positionId);
    }

    private static List<User> parsareUsers(JSONArray jsonArray) throws JSONException {
        List<User> usersList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            usersList.add(parsareUser(jsonArray.getJSONObject(i)));
        }
        return usersList;
    }

    public static List<User> parsareJson(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            return parsareUsers(jsonArray);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
