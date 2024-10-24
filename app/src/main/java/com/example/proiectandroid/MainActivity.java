package com.example.proiectandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnLogout;
    Button btnSettings;
    Button btnChooseDepartment;
    Spinner spnDepartment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // pt resposiveness
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnLogout = findViewById(R.id.btnLogout);
        btnSettings = findViewById(R.id.btnSettings);
        btnChooseDepartment = findViewById(R.id.btnChooseDepartment);
        spnDepartment = findViewById(R.id.spnDepartment);

        // dropdown-ul facut folosind spinner si elementele din el
        String[] departments = {"Technical", "HR", "Marketing & Sales", "Other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, departments);
        spnDepartment.setAdapter(adapter);

        // butonul de logout ma duce inapoi la pagina de Login
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginPageActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // am creat un toast la butonul de setari si pt butonul de alegere departament din dropdown
        // pt ca nu am implementat inca o pagina de setari
        // sau o functionalitate care face ceva cu elem din drop down
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "The settings functionality is not done! :(", Toast.LENGTH_SHORT).show();
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
            Intent historyIntent = new Intent(MainActivity.this, EditProfileActivity.class);
            startActivity(historyIntent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
