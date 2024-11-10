package com.example.proiectandroid.activities;

import static com.example.proiectandroid.utils.UserManager.getUserByEmail;
import static com.example.proiectandroid.utils.UserManager.getUserById;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proiectandroid.R;
import com.example.proiectandroid.models.Status;
import com.example.proiectandroid.models.Task;
import com.example.proiectandroid.models.User;
import com.example.proiectandroid.utils.UserManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskManagerActivity extends AppCompatActivity {

    private RecyclerView taskRecyclerView;
    private TaskAdapter taskAdapter;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_manager);

        currentUser = getCurrentUser();

        taskRecyclerView = findViewById(R.id.taskListView);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Task> userTasks = getUserTasks(currentUser);

        taskAdapter = new TaskAdapter(userTasks);
        taskRecyclerView.setAdapter(taskAdapter);

        findViewById(R.id.floatingActionButton).setOnClickListener(v -> {
            Intent intent = new Intent(TaskManagerActivity.this, AddTaskActivity.class);
            startActivity(intent);
        });
    }

    private User getCurrentUser() {
        SharedPreferences loginPrefs = getSharedPreferences("UserLoginPreferences", MODE_PRIVATE);
        String crtUserId = loginPrefs.getString("currentUserId", "0");

        return UserManager.getUserById(crtUserId);
    }

    private List<Task> getUserTasks(User currentUser) {
        List<Task> allTasks = getAllTasks();
        List<Task> userTasks = new ArrayList<>();

        for (Task task : allTasks) {
            if (task.getAssignedUser() != null && task.getAssignedUser().equals(currentUser)) {
                userTasks.add(task);
            }
        }

        return userTasks;
    }

    private List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        User dan = getUserByEmail("dp@gmail.com");
        User ana = getUserByEmail("a.ionescu@hotmail.com");

        tasks.add(new Task("Task 1", "Description 1", new Date(), Status.COMPLETED, dan));
        tasks.add(new Task("Task 3", "Descriere 3", new Date(), Status.COMPLETED, dan));
        tasks.add(new Task("Task 2", "Descriere 2", new Date(), Status.IN_PROGRESS, ana));

        return tasks;
    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

        private List<Task> taskList;

        public TaskAdapter(List<Task> taskList) {
            this.taskList = taskList;
        }

        @Override
        public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_task, parent, false);
            return new TaskViewHolder(view);
        }

        @Override
        public void onBindViewHolder(TaskViewHolder holder, int position) {
            Task task = taskList.get(position);
            holder.bind(task);
        }

        @Override
        public int getItemCount() {
            return taskList.size();
        }

        public class TaskViewHolder extends RecyclerView.ViewHolder {

            private Spinner statusSpinner;

            public TaskViewHolder(View itemView) {
                super(itemView);
                statusSpinner = itemView.findViewById(R.id.statusSpinner);
            }

            public void bind(Task task) {
                ArrayAdapter<Status> statusAdapter = new ArrayAdapter<>(TaskManagerActivity.this, android.R.layout.simple_spinner_item, Status.values());
                statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                statusSpinner.setAdapter(statusAdapter);

                statusSpinner.setSelection(getStatusPosition(task));

                statusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        task.setStatus(Status.valueOf(Status.values()[position].toString()));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });
            }

            private int getStatusPosition(Task task) {
                for (int i = 0; i < Status.values().length; i++) {
                    if (Status.values()[i].toString().equals(task.getStatus())) {
                        return i;
                    }
                }
                return 0;
            }
        }
    }
}
