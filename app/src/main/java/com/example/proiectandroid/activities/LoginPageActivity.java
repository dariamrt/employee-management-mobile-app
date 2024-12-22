package com.example.proiectandroid.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.proiectandroid.R;
import com.example.proiectandroid.database.AppDatabase;
import com.example.proiectandroid.database.DatabaseInitializer;
import com.example.proiectandroid.models.User;
import com.example.proiectandroid.utils.UserSessionManager;

public class LoginPageActivity extends AppCompatActivity {

    private Button btnLogin, btnCreateAccount;
    private EditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_page);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);

        AppDatabase database = AppDatabase.getInstance(this);
        if (database.getUserDAO().getUserCount() == 0) {
            DatabaseInitializer.populateDatabase(database);
        }

        btnLogin.setOnClickListener(v -> handleLogin());
        btnCreateAccount.setOnClickListener(v -> {
            Intent intent = new Intent(LoginPageActivity.this, CreateAccountActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void handleLogin() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        User user = AppDatabase.getInstance(this).getUserDAO().getUserByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            SharedPreferences rolePrefs = getSharedPreferences("UserRolePreferences", MODE_PRIVATE);
            SharedPreferences.Editor editor = rolePrefs.edit();
            editor.putBoolean("isAdmin", user.getIsAdmin());
            editor.putInt("currentUserId", user.getId());
            editor.apply();

            UserSessionManager sessionManager = new UserSessionManager(this);
            sessionManager.createUserLoginSession(user, AppDatabase.getInstance(this));

            Intent intent = user.getIsAdmin()
                    ? new Intent(LoginPageActivity.this, AdminPanel.class)
                    : new Intent(LoginPageActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, user == null ? "Utilizator inexistent!" : "Parola invalida!!", Toast.LENGTH_SHORT).show();
        }
    }
}
