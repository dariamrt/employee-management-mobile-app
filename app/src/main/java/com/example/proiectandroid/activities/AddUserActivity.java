package com.example.proiectandroid.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proiectandroid.R;
import com.example.proiectandroid.database.AppDatabase;
import com.example.proiectandroid.models.User;

public class AddUserActivity extends AppCompatActivity {

    private EditText etFirstName, etLastName, etEmail, etPassword, etSalary, etPosition;
    private CheckBox cbIsAdmin;
    private Button btnAddUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etSalary = findViewById(R.id.etSalary);
        etPosition = findViewById(R.id.etPosition);
        cbIsAdmin = findViewById(R.id.cbIsAdmin);
        btnAddUser = findViewById(R.id.btnAddUser);

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = etFirstName.getText().toString();
                String lastName = etLastName.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String salaryText = etSalary.getText().toString();
                String positionIdText = etPosition.getText().toString();
                boolean isAdmin = cbIsAdmin.isChecked();

                if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || salaryText.isEmpty() || positionIdText.isEmpty()) {
                    Toast.makeText(AddUserActivity.this, "Please fill all the fields!!!!", Toast.LENGTH_SHORT).show();
                    return;
                }

                double salary;
                int positionId;
                try {
                    salary = Double.parseDouble(salaryText);
                    positionId = Integer.parseInt(positionIdText);
                } catch (NumberFormatException e) {
                    Toast.makeText(AddUserActivity.this, "Invalid salary or position ID format :(", Toast.LENGTH_SHORT).show();
                    return;
                }

                User user = new User(firstName, lastName, email, password, salary, positionId);
                user.setIsAdmin(isAdmin);

                AppDatabase db = AppDatabase.getInstance(getApplicationContext());
                db.getUserDAO().insertUser(user);

                Toast.makeText(AddUserActivity.this, "User added successfully!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
