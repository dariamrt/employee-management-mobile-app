package com.example.proiectandroid.jsonParsers;

import com.example.proiectandroid.models.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TaskParser {
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String DEADLINE = "deadline";
    private static final String STATUS = "status";
    private static final String ASSIGNED_USER_ID = "assignedUserId";

    private static Task parsareTask(JSONObject jsonObject) throws JSONException {
        String title = jsonObject.getString(TITLE);
        String description = jsonObject.getString(DESCRIPTION);
        String deadline = jsonObject.getString(DEADLINE);
        String status = jsonObject.getString(STATUS);
        int assignedUserId = jsonObject.getInt(ASSIGNED_USER_ID);

        return new Task(title, description, deadline, status, assignedUserId);
    }

    private static List<Task> parsareTasks(JSONArray jsonArray) throws JSONException {
        List<Task> tasksList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            tasksList.add(parsareTask(jsonArray.getJSONObject(i)));
        }
        return tasksList;
    }

    public static List<Task> parsareJson(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            return parsareTasks(jsonArray);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
