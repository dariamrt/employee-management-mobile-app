package com.example.proiectandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proiectandroid.R;
import com.example.proiectandroid.models.User;
import com.example.proiectandroid.utils.UserManager;

public class CreateAccountActivity extends AppCompatActivity {

    Button btnCreateAccount;
    EditText etFirstName, etLastName, etEmail, etPassword, etConfirmPassword;

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
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = etFirstName.getText().toString();
                String lastName = etLastName.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String confirmPassword = etConfirmPassword.getText().toString();

                // verific daca a completat campurile
                if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(CreateAccountActivity.this,
                            "Please fill all the fields!!!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // verific daca se matchuiesc parolele
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(CreateAccountActivity.this,
                            "Passwords don't match!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // creez un obiect user si il bag in lista folosind metoda din UserManager
                User user = new User(firstName, lastName, email, password);
                UserManager.addUser(user);

//                // toast cu datele userului create -> pt testare l-am fct
//                Toast.makeText(CreateAccountActivity.this,
//                        "Account created: \n" + user.getFirstName() + "\n" + user.getLastName() + "\n" + user.getEmail(), Toast.LENGTH_LONG).show();

                // acum trimit userul in main act sa-l afisez
                Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });
    }
}
