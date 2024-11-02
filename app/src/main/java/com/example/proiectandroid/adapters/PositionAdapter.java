package com.example.proiectandroid.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.proiectandroid.R;

public class PositionAdapter {
    // SETEAZ iconita in functie de pozitie
    static ImageView ivSetPositionIcon;
    public static void setPositionIcon(ImageView imageView, String positionTitle, Context context) {
        Drawable icon;
        switch (positionTitle.toLowerCase()) {
            case "manager":
                icon = ContextCompat.getDrawable(context, R.drawable.ic_manager);
                break;
            case "junior":
                icon = ContextCompat.getDrawable(context, R.drawable.ic_junior);
                break;
            case "senior":
                icon = ContextCompat.getDrawable(context, R.drawable.ic_senior);
                break;
            default:
                icon = ContextCompat.getDrawable(context, R.drawable.ic_default);
                break;
        }

        imageView.setImageDrawable(icon);
    }


    // FORMATEAZA dim font in fct de pozitie
    public static void setPositionFontSize(TextView positionTextView, String positionTitle) {
        switch (positionTitle.toLowerCase()) {
            case "manager":
                positionTextView.setTextSize(20);
                break;
            case "junior":
                positionTextView.setTextSize(16);
                break;
            case "senior":
                positionTextView.setTextSize(18);
                break;
            default:
                positionTextView.setTextSize(14);
                break;
        }
    }

    // FORMATEAZA fundal colorat in functie de pozitie
    public static void setPositionBackgroundColor(TextView positionTextView, String positionTitle, Context context) {
        switch (positionTitle.toLowerCase()) {
            case "manager":
                positionTextView.setBackgroundColor(ContextCompat.getColor(context, R.color.managerBackground));
                break;
            case "junior":
                positionTextView.setBackgroundColor(ContextCompat.getColor(context, R.color.juniorBackground));
                break;
            case "senior":
                positionTextView.setBackgroundColor(ContextCompat.getColor(context, R.color.seniorBackground));
                break;
            default:
                positionTextView.setBackgroundColor(ContextCompat.getColor(context, R.color.defaultBackground));
                break;
        }
    }
}
