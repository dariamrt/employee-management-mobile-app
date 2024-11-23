package com.example.proiectandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proiectandroid.R;
import com.example.proiectandroid.database.AppDatabase;
import com.example.proiectandroid.models.User;

public class CreateAccountActivity extends AppCompatActivity {

    private Button btnCreateAccount;
    private EditText etFirstName, etLastName, etEmail, etPassword, etConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_account);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);

        btnCreateAccount = findViewById(R.id.btnCreateAccount);
        btnCreateAccount.setOnClickListener(v -> {
            String firstName = etFirstName.getText().toString();
            String lastName = etLastName.getText().toString();
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            String confirmPassword = etConfirmPassword.getText().toString();

            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(CreateAccountActivity.this, "Please fill all the fields!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(CreateAccountActivity.this, "Passwords don't match!", Toast.LENGTH_SHORT).show();
                return;
            }

            User existingUser = AppDatabase.getInstance(getApplicationContext()).getUserDAO().getUserByEmail(email);
            if (existingUser != null) {
                Toast.makeText(CreateAccountActivity.this, "Email already exists!", Toast.LENGTH_SHORT).show();
                return;
            }

            User user = new User(firstName, lastName, email, password, 1000, 1);
            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            Log.d("CreateAccountActivity", "First Name: " + firstName);
            Log.d("CreateAccountActivity", "Last Name: " + lastName);
            Log.d("CreateAccountActivity", "Email: " + email);

            try {
                db.getUserDAO().insertUser(user);
                Log.d("CreateAccountActivity", "User created successfully with email: " + email);
            } catch (Exception e) {
                Log.e("CreateAccountActivity", "Error creating user: " + e.getMessage());
                Toast.makeText(CreateAccountActivity.this, "Error creating account!", Toast.LENGTH_SHORT).show();
            }



            Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
            finish();
        });
    }
}
