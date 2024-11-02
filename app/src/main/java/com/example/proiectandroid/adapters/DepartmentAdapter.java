package com.example.proiectandroid.adapters;

import android.content.Context;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.proiectandroid.R;

public class DepartmentAdapter {
    // am FORMATAT textul pe culori in functie de departamentul de care apartine userul
    public static void setDepartmentColor(TextView departmentTextView, String departmentName, Context context) {
        if (departmentName.equalsIgnoreCase("Technical")) {
            departmentTextView.setTextColor(ContextCompat.getColor(context, R.color.technicalColor));
        } else if (departmentName.equalsIgnoreCase("HR")) {
            departmentTextView.setTextColor(ContextCompat.getColor(context, R.color.hrColor));
        } else if (departmentName.equalsIgnoreCase("Marketing & Sales")) {
            departmentTextView.setTextColor(ContextCompat.getColor(context, R.color.marketingColor));
        } else {
            departmentTextView.setTextColor(ContextCompat.getColor(context, R.color.otherColor));
        }
    }
}
