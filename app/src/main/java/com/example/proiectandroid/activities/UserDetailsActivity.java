package com.example.proiectandroid.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proiectandroid.R;
import com.example.proiectandroid.database.AppDatabase;
import com.example.proiectandroid.models.User;
import com.example.proiectandroid.utils.UserSessionManager;

import java.text.NumberFormat;
import java.util.Locale;

public class UserDetailsActivity extends AppCompatActivity {

    private TextView firstNameTextView, lastNameTextView, emailTextView, salaryTextView, positionTextView, departmentTextView;
    private ImageView ivPositionIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        firstNameTextView = findViewById(R.id.firstNameTextView);
        lastNameTextView = findViewById(R.id.lastNameTextView);
        emailTextView = findViewById(R.id.emailTextView);
        salaryTextView = findViewById(R.id.salaryTextView);
        positionTextView = findViewById(R.id.positionTextView);
        departmentTextView = findViewById(R.id.departmentTextView);
        ivPositionIcon = findViewById(R.id.ivPositionIcon);

        User currentUser = UserSessionManager.getCurrentUser();
        if (currentUser != null) {
            firstNameTextView.setText("First Name: " + currentUser.getFirstName());
            lastNameTextView.setText("Last Name: " + currentUser.getLastName());
            emailTextView.setText("Email: " + currentUser.getEmail());
            salaryTextView.setText("Salary: " + NumberFormat.getCurrencyInstance(new Locale("ro", "RO")).format(currentUser.getSalary()));
        } else {
            Toast.makeText(this, "User not found!", Toast.LENGTH_SHORT).show();
        }
    }
}
