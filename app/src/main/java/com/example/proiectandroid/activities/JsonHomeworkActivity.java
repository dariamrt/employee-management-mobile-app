package com.example.proiectandroid.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.proiectandroid.R;
import com.example.proiectandroid.database.AppDatabase;
import com.example.proiectandroid.jsonChestii.DepartmentParser;
import com.example.proiectandroid.jsonChestii.HttpsManager;
import com.example.proiectandroid.jsonChestii.PositionParser;
import com.example.proiectandroid.jsonChestii.TaskParser;
import com.example.proiectandroid.jsonChestii.UserParser;
import com.example.proiectandroid.models.Department;
import com.example.proiectandroid.models.Position;
import com.example.proiectandroid.models.Task;
import com.example.proiectandroid.models.User;

import java.util.List;

public class JsonHomeworkActivity extends AppCompatActivity {

    private Button btnAdaugaDepartmentDinJson;
    private Button btnAdaugaPositionDinJson;
    private Button btnAdaugaUserDinJson;
    private Button btnAdaugaTaskDinJson;
    private static final String jsonUrlDepartment = "https://www.jsonkeeper.com/b/R7E3";
    private static final String jsonUrlPosition = "https://www.jsonkeeper.com/b/GPI2";
    private static final String jsonUrlUser = "https://www.jsonkeeper.com/b/I65J";
    private static final String jsonUrlTask = "https://www.jsonkeeper.com/b/QOLY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_json_homework);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnAdaugaDepartmentDinJson = findViewById(R.id.btnAdaugaDepartmentDinJson);
        btnAdaugaPositionDinJson = findViewById(R.id.btnAdaugaPositionDinJson);
        btnAdaugaUserDinJson = findViewById(R.id.btnAdaugaUserDinJson);
        btnAdaugaTaskDinJson = findViewById(R.id.btnAdaugaTaskDinJson);

        btnAdaugaDepartmentDinJson.setOnClickListener(v -> incarcareDinJson(jsonUrlDepartment, "department"));
        btnAdaugaPositionDinJson.setOnClickListener(v -> incarcareDinJson(jsonUrlPosition, "position"));
        btnAdaugaUserDinJson.setOnClickListener(v -> incarcareDinJson(jsonUrlUser, "user"));
        btnAdaugaTaskDinJson.setOnClickListener(v -> incarcareDinJson(jsonUrlTask, "task"));
    }

    private void incarcareDinJson(String url, String type) {
        new Thread(() -> {
            HttpsManager manager = new HttpsManager(url);
            String json = manager.procesare();
            new android.os.Handler(getMainLooper()).post(() -> procesareJsonSiAdaugareInBD(json, type));
        }).start();
    }

    private void procesareJsonSiAdaugareInBD(String json, String type) {
        AppDatabase database = AppDatabase.getInstance(this);

        switch (type) {
            case "department":
                List<Department> departments = DepartmentParser.parsareJson(json);
                database.getDepartmentDao().insertDepartments(departments);
                Toast.makeText(this, "Departamente adaugate din json cu succes!", Toast.LENGTH_SHORT).show();
                break;

            case "position":
                List<Position> positions = PositionParser.parsareJson(json);
                database.getPositionDao().insertPositions(positions);
                Toast.makeText(this, "Positions adaugate din json cu succes!", Toast.LENGTH_SHORT).show();
                break;

            case "user":
                List<User> users = UserParser.parsareJson(json);
                database.getUserDAO().insertUsers(users);
                Toast.makeText(this, "Utilizatori adaugati din json cu succes!", Toast.LENGTH_SHORT).show();
                break;

            case "task":
                List<Task> tasks = TaskParser.parsareJson(json);
                database.getTaskDao().insertTasks(tasks);
                Toast.makeText(this, "Taskuri adaugate din json cu succes!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}