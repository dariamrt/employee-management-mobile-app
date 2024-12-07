package com.example.proiectandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proiectandroid.R;
import com.example.proiectandroid.utils.UserSessionManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnLogout, btnTasks, btnChooseDepartment;
    Spinner spnDepartment;
    TextView tvHelloUser;
    ListView lvUserInfo;
    List<String> userInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogout = findViewById(R.id.btnLogout);
        btnTasks = findViewById(R.id.btnTasks);
        btnChooseDepartment = findViewById(R.id.btnChooseDepartment);
        spnDepartment = findViewById(R.id.spnDepartment);
        tvHelloUser = findViewById(R.id.tvHelloUser);
        lvUserInfo = findViewById(R.id.lvUserInfo);

        String[] departments = {"Technical", "HR", "Marketing & Sales", "Other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, departments);
        spnDepartment.setAdapter(adapter);

        userInfoList = new ArrayList<>();
        UserSessionManager sessionManager = new UserSessionManager(this);
        sessionManager.checkLogin(getApplicationContext());

        if (UserSessionManager.getCurrentUser() != null) {
            tvHelloUser.setText("Hello, " + UserSessionManager.getCurrentUser().getFirstName() + " " + UserSessionManager.getCurrentUser().getLastName() + "!");
            userInfoList.add("Email: " + UserSessionManager.getCurrentUser().getEmail());
            lvUserInfo.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userInfoList));
        }

        btnLogout.setOnClickListener(v -> {
            UserSessionManager.logoutUser(this);
            finish();
        });

        btnTasks.setOnClickListener(v -> {
            Intent intentTasks = new Intent(MainActivity.this, TaskManagerActivity.class);
            startActivity(intentTasks);
        });

        btnChooseDepartment.setOnClickListener(v -> Toast.makeText(MainActivity.this, "Department fct not available!", Toast.LENGTH_SHORT).show());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.itViewProfile) {
            Intent intentProfile = new Intent(MainActivity.this, UserDetailsActivity.class);
            startActivity(intentProfile);
            return true;
        } else if (id == R.id.itJson) {
            Intent intentJson = new Intent(MainActivity.this, JsonHomeworkActivity.class);
            startActivity(intentJson);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
