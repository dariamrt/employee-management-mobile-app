package com.example.proiectandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.proiectandroid.R;
import com.example.proiectandroid.models.User;
import com.example.proiectandroid.utils.UserManager;

public class LoginPageActivity extends AppCompatActivity {

    Button btnLogin;
    Button btnCreateAccount;

    EditText etEmail;
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_page);

        // pt responsiveness asta
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // leg variabilele de componentele vizuale
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);

        // butonul de login functionalitati, etc.
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                // verific daca userul exista in lista
                if (UserManager.doesUserExist(email)) {
                    // daca exista => autentficiare, altfel toast
                    User user = UserManager.authenticateUser(email, password);
                    if (user != null) {
                        Intent intent = new Intent(LoginPageActivity.this, MainActivity.class);
                        intent.putExtra("user", user);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginPageActivity.this, "Parola invalida!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginPageActivity.this, "Nu exista userul!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // daca n-ai cont, dai pe acest buton => te duce la activitatea de facere cont
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPageActivity.this, CreateAccountActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
