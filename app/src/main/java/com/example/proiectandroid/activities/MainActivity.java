package com.example.proiectandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.proiectandroid.R;
import com.example.proiectandroid.models.User;
import com.example.proiectandroid.utils.UserManager;
import com.example.proiectandroid.utils.UserSessionManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnLogout;
    Button btnSettings;
    Button btnChooseDepartment;
    Spinner spnDepartment;
    TextView tvHelloUser;
    ListView lvUserInfo; // sa afisam info pt user creat SAU autentificat, in fct de activitatea de unde vii
    List<String> userInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // responsiveness etc
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // leg var de comp vizuale
        btnLogout = findViewById(R.id.btnLogout);
        btnSettings = findViewById(R.id.btnSettings);
        btnChooseDepartment = findViewById(R.id.btnChooseDepartment);
        spnDepartment = findViewById(R.id.spnDepartment);
        tvHelloUser = findViewById(R.id.tvHelloUser);
        lvUserInfo = findViewById(R.id.lvUserInfo);

        // populam spinnerul(aka drop down-ul)
        String[] departments = {"Technical", "HR", "Marketing & Sales", "Other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, departments);
        spnDepartment.setAdapter(adapter);

        userInfoList = new ArrayList<>();

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("user")) {
            User user = (User) intent.getSerializableExtra("user");
            if (user != null) {
                tvHelloUser.setText("Hello, " + user.getFirstName() + " " + user.getLastName() + "!");

                userInfoList.add("First Name: " + user.getFirstName());
                userInfoList.add("Last Name: " + user.getLastName());
                userInfoList.add("Email: " + user.getEmail());

                ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userInfoList);
                lvUserInfo.setAdapter(listAdapter);
            } else {
                tvHelloUser.setText("User null frate"); // teoretic aici nu ajunge cred dat fiind ca nu s-ar efectua autentificarea
            }
        }

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginPageActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "The settings functionality is not done yet! :(", Toast.LENGTH_SHORT).show();
            }
        });

        btnChooseDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "The department can't be contacted yet! :(", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    // meniul care duce la optiunile de setari, schimbat parola, editat profil!!
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.itChangePassword) {
            Intent historyIntent = new Intent(MainActivity.this, ChangePasswordActivity.class);
            startActivity(historyIntent);
            return true;
        } else if (id == R.id.itSettings) {
            Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        } else if (id == R.id.itEditProfile) {
            Intent editProfileIntent = new Intent(MainActivity.this, EditProfileActivity.class);
            startActivity(editProfileIntent);
            return true;
        } else if (id == R.id.itViewProfile) {
            User currentUser = UserSessionManager.getCurrentUser();
            Intent profileIntent = new Intent(MainActivity.this, UserDetailsActivity.class);
            profileIntent.putExtra("user", currentUser);
            startActivity(profileIntent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}