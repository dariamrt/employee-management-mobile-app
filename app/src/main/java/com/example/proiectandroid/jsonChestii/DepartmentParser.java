package com.example.proiectandroid.jsonChestii;

import com.example.proiectandroid.models.Department;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DepartmentParser {
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";

    private static Department parsareDepartment(JSONObject jsonObject) throws JSONException {
        String name = jsonObject.getString(NAME);
        String description = jsonObject.getString(DESCRIPTION);

        return new Department(name, description);
    }

    // array JSON => object Array
    private static List<Department> parsareDepartments(JSONArray jsonArray) throws JSONException {
        List<Department> listaDepartamente = new ArrayList<>();
        for(int i=0;i<jsonArray.length();i++){
            listaDepartamente.add(parsareDepartment(jsonArray.getJSONObject(i)));
        }
        return listaDepartamente;
    }

    // string JSON => object Array
    public static List<Department> parsareJson(String json){
        try {
            JSONArray jsonArray = new JSONArray(json);
            return parsareDepartments(jsonArray);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}