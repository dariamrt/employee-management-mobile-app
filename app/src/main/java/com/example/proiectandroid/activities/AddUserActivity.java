package com.example.proiectandroid.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proiectandroid.R;
import com.example.proiectandroid.models.Department;
import com.example.proiectandroid.models.Position;
import com.example.proiectandroid.models.User;
import com.example.proiectandroid.utils.UserManager;

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
                String positionName = etPosition.getText().toString();
                boolean isAdmin = cbIsAdmin.isChecked();

                if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || salaryText.isEmpty() || positionName.isEmpty()) {
                    Toast.makeText(AddUserActivity.this, "Please fill all the fields!", Toast.LENGTH_SHORT).show();
                    return;
                }

                double salary;
                try {
                    salary = Double.parseDouble(salaryText);
                } catch (NumberFormatException e) {
                    Toast.makeText(AddUserActivity.this, "Invalid salary format", Toast.LENGTH_SHORT).show();
                    return;
                }

                Position position = new Position(positionName, new Department("General", "Generic department description"));
                User user = new User(firstName, lastName, email, password, salary, position);
                user.setIsAdmin(isAdmin);
                UserManager.addUser(user);

                Toast.makeText(AddUserActivity.this, "User added successfully!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
