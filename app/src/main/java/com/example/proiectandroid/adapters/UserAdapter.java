package com.example.proiectandroid.adapters;

import static com.example.proiectandroid.adapters.PositionAdapter.setPositionIcon;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proiectandroid.R;
import com.example.proiectandroid.activities.UserDetailsActivity;
import com.example.proiectandroid.models.User;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private Context context;
    private List<User> users;

    public UserAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = users.get(position);

        holder.initialsTextView.setText(user.getFirstName().substring(0, 1) + user.getLastName().substring(0, 1));

        holder.salaryTextView.setText(formatSalary(user.getSalary()));
        Log.d("UserAdapter", "womp womp" );
        if (user.getPosition() != null) {
            String positionTitle = user.getPosition().getTitle();
            holder.positionTextView.setText(formatBold("Position: ", positionTitle));

            PositionAdapter.setPositionFontSize(holder.positionTextView, positionTitle);
            PositionAdapter.setPositionBackgroundColor(holder.positionTextView, positionTitle, context);

            // pun iconita dupa text
            setPositionIcon(holder.ivPositionIcon, positionTitle, context);

            if (user.getPosition().getDepartment() != null) {
                holder.departmentTextView.setText(formatBold("Department: ", user.getPosition().getDepartment().getName()));
                DepartmentAdapter.setDepartmentColor(holder.departmentTextView, user.getPosition().getDepartment().getName(), context);
            } else {
                holder.departmentTextView.setText("Department: N/A");
            }
        } else {
            holder.positionTextView.setText("Position: N/A");
            holder.departmentTextView.setText("Department: N/A");
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, UserDetailsActivity.class);
            intent.putExtra("user", user);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    private String formatSalary(double salary) {
        return NumberFormat.getCurrencyInstance(new Locale("ro", "RO")).format(salary);
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView initialsTextView, salaryTextView, positionTextView, departmentTextView;
        ImageView ivPositionIcon;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            initialsTextView = itemView.findViewById(R.id.initialsTextView);
            salaryTextView = itemView.findViewById(R.id.salaryTextView);
            positionTextView = itemView.findViewById(R.id.positionTextView);
            departmentTextView = itemView.findViewById(R.id.departmentTextView);
            ivPositionIcon = itemView.findViewById(R.id.ivPositionIcon); // Initialize ImageView
        }
    }

    public static SpannableString formatBold(String label, String value) {
        String fullText = label + value;
        SpannableString spannableString = new SpannableString(fullText);
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), label.length(), fullText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }
}

