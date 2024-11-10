package com.example.proiectandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proiectandroid.R;
import com.example.proiectandroid.models.User;
import com.example.proiectandroid.utils.UserManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AdminPanel extends AppCompatActivity {

    private ListView userListView;
    private FloatingActionButton floatingActionButton;
    private List<User> userList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        userListView = findViewById(R.id.userListView);
        floatingActionButton = findViewById(R.id.floatingActionButton);

        userList = UserManager.getUsers();
//        for(User user : userList){
//            Log.e("AdminPanel", user.getFirstName());
//            Toast.makeText(this, user.getLastName(), Toast.LENGTH_SHORT).show();
//        }

        if (!userList.isEmpty()) {
            ArrayAdapter<User> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userList);
            userListView.setAdapter(adapter);
        }

        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User selectedUser = userList.get(position);
                Intent intent = new Intent(AdminPanel.this, EditUserActivity.class);
                intent.putExtra("userId", selectedUser.getId());
                startActivity(intent);
            }
        });

        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User selectedUser = userList.get(position);
                Intent intent = new Intent(AdminPanel.this, EditUserActivity.class);
                intent.putExtra("user", selectedUser);
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onResume() { // pt cand se intoarce in pagina din alte activitati
        super.onResume();
        List<User> updatedUsers = UserManager.getUsers();

        for (User updatedUser : updatedUsers) {
            boolean userExists = false;

            for (User existingUser : userList) {
                if (existingUser.getId().equals(updatedUser.getId())) {
                    userExists = true;
                    break;
                }
            }

            if (!userExists) {
                userList.add(updatedUser);
            }
        }
        ((ArrayAdapter) userListView.getAdapter()).notifyDataSetChanged();
    }

}