package com.example.proiectandroid.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proiectandroid.R;
import com.example.proiectandroid.models.User;
import com.example.proiectandroid.utils.UserManager;

public class EditUserActivity extends AppCompatActivity {

    private EditText etFirstName, etLastName, etEmail, etPassword, etSalary;
    private Button btnSave;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etSalary = findViewById(R.id.etSalary);
        btnSave = findViewById(R.id.btnSave);

        user = (User) getIntent().getSerializableExtra("user");

        if (user != null) {
            etFirstName.setText(user.getFirstName());
            etLastName.setText(user.getLastName());
            etEmail.setText(user.getEmail());
            etPassword.setText(user.getPassword());
            etSalary.setText(String.valueOf(user.getSalary()));
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = etFirstName.getText().toString();
                String lastName = etLastName.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String salaryString = etSalary.getText().toString();

                if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || salaryString.isEmpty()) {
                    Toast.makeText(EditUserActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                double salary;
                try {
                    salary = Double.parseDouble(salaryString);
                } catch (NumberFormatException e) {
                    Toast.makeText(EditUserActivity.this, "Invalid salary", Toast.LENGTH_SHORT).show();
                    return;
                }

                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setPassword(password);
                user.setSalary(salary);

                UserManager.updateUser(user);

                Toast.makeText(EditUserActivity.this, "User updated", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
