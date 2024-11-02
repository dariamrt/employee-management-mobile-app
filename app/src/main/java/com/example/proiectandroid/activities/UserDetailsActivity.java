package com.example.proiectandroid.activities;

import static com.example.proiectandroid.adapters.UserAdapter.formatBold;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proiectandroid.R;
import com.example.proiectandroid.adapters.DepartmentAdapter;
import com.example.proiectandroid.adapters.PositionAdapter;
import com.example.proiectandroid.models.User;

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

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");

        if (user != null) {
            // text FORMATAT cu bold pe continut pt campurile prenume, nume, email
            firstNameTextView.setText(formatBold("First Name: ", user.getFirstName()));
            lastNameTextView.setText(formatBold("Last Name: ", user.getLastName()));
            emailTextView.setText(formatBold("Email: ", user.getEmail()));

            // am FORMATAT salariul numar sa fie afisat in RON si bold pe continut
            String salaryFormatted = NumberFormat.getCurrencyInstance(new Locale("ro", "RO")).format(user.getSalary());
            salaryTextView.setText(formatBold("Salary: ", salaryFormatted));

            // am formatat pozitia sa aiba fundalul colorat, continutul boldat, FONT diferit ca dimensiune si ICONITA SPECIFICA in funtie de pozitie
            if (user.getPosition() != null) {
                String positionTitle = user.getPosition().getTitle();

                positionTextView.setText(formatBold("Position: ", positionTitle));
                PositionAdapter.setPositionIcon(ivPositionIcon, positionTitle, this);
                PositionAdapter.setPositionFontSize(positionTextView, positionTitle);
                PositionAdapter.setPositionBackgroundColor(positionTextView, positionTitle, this);

                if (user.getPosition().getDepartment() != null) {
                    String departmentName = user.getPosition().getDepartment().getName();
                    departmentTextView.setText(formatBold("Department: ", departmentName));
                    DepartmentAdapter.setDepartmentColor(departmentTextView, departmentName, this);
                } else {
                    departmentTextView.setText("Department: N/A");
                }
            } else {
                positionTextView.setText("Position: N/A");
                departmentTextView.setText("Department: N/A");
            }
        }
    }
}
