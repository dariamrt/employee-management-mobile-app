package com.example.proiectandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proiectandroid.R;
import com.example.proiectandroid.database.AppDatabase;
import com.example.proiectandroid.models.Status;
import com.example.proiectandroid.models.Task;
import com.example.proiectandroid.utils.UserSessionManager;

import java.util.List;

public class TaskManagerActivity extends AppCompatActivity {

    private RecyclerView taskRecyclerView;
    private TaskAdapter taskAdapter;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_manager);

        database = AppDatabase.getInstance(getApplicationContext());

        taskRecyclerView = findViewById(R.id.taskListView);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        UserSessionManager sessionManager = new UserSessionManager(this);
        sessionManager.checkLogin(getApplicationContext());

        if (UserSessionManager.getCurrentUser() != null) {
            int currentUserId = UserSessionManager.getCurrentUser().getId();
            List<Task> userTasks = database.getTaskDao().getTasksByAssignedUserId(currentUserId);

            taskAdapter = new TaskAdapter(userTasks);
            taskRecyclerView.setAdapter(taskAdapter);
        }

        findViewById(R.id.floatingActionButton).setOnClickListener(v -> {
            Intent intent = new Intent(TaskManagerActivity.this, AddTaskActivity.class);
            startActivity(intent);
        });
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
                        task.setStatus(Status.values()[position].toString());
                        database.getTaskDao().updateTask(task);
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
