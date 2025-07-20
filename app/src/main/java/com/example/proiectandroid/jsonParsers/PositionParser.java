package com.example.proiectandroid.jsonParsers;

import com.example.proiectandroid.models.Position;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PositionParser {
    private static final String TITLE = "title";
    private static final String DEPARTMENT_ID = "departmentId";

    private static Position parsarePosition(JSONObject jsonObject) throws JSONException {
        String title = jsonObject.getString(TITLE);
        int departmentId = jsonObject.getInt(DEPARTMENT_ID);

        return new Position(title, departmentId);
    }

    private static List<Position> parsarePositions(JSONArray jsonArray) throws JSONException {
        List<Position> positionsList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            positionsList.add(parsarePosition(jsonArray.getJSONObject(i)));
        }
        return positionsList;
    }

    public static List<Position> parsareJson(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            return parsarePositions(jsonArray);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
