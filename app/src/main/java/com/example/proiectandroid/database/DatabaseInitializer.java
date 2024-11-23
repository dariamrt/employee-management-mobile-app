package com.example.proiectandroid.database;

import android.util.Log;

import com.example.proiectandroid.models.Department;
import com.example.proiectandroid.models.Position;
import com.example.proiectandroid.models.Task;
import com.example.proiectandroid.models.User;

public class DatabaseInitializer {

    public static void populateDatabase(AppDatabase database) {
        Log.d("InsertDebug", "test1");

        Department department1 = new Department("IT", "Development and maintenance of software systems");
        Department department2 = new Department("HR", "Human resources and employee management");
        Department department3 = new Department("Sales", "Sales and customer relations");

        database.getDepartmentDao().insertDepartment(department1);
        database.getDepartmentDao().insertDepartment(department2);
        database.getDepartmentDao().insertDepartment(department3);
        Log.d("InsertDebug", "test2");

        int department1Id = database.getDepartmentDao().getDepartmentId("IT");
        int department2Id = database.getDepartmentDao().getDepartmentId("HR");
        int department3Id = database.getDepartmentDao().getDepartmentId("Sales");
        Log.d("InsertDebug", "Department IDs: " + department1Id + ", " + department2Id + ", " + department3Id);

        Position position1 = new Position("Software Developer", department1Id);
        Position position2 = new Position("HR Manager", department2Id);
        Position position3 = new Position("Sales Representative", department3Id);

        database.getPositionDao().insertPosition(position1);
        database.getPositionDao().insertPosition(position2);
        database.getPositionDao().insertPosition(position3);

        int position1Id = database.getPositionDao().getPositionIdByName("Software Developer");
        int position2Id = database.getPositionDao().getPositionIdByName("HR Manager");
        int position3Id = database.getPositionDao().getPositionIdByName("Sales Representative");

        Log.d("InsertDebug", "Position IDs: " + position1Id + ", " + position2Id + ", " + position3Id);

        Log.d("InsertDebug", "test3");

        User user1 = new User("John", "Doe", "john.doe@example.com", "password123", 5000.0, position2Id);
        User user2 = new User("Jane", "Smith", "jane.smith@example.com", "password456", 4000.0, position3Id);
        User user3 = new User("Alice", "Johnson", "alice.johnson@example.com", "password789", 3000.0, position2Id);

        database.getUserDAO().insertUser(user1);
        database.getUserDAO().insertUser(user2);
        database.getUserDAO().insertUser(user3);

        int user1Id = database.getUserDAO().getUserIdByEmail("john.doe@example.com");
        int user2Id = database.getUserDAO().getUserIdByEmail("jane.smith@example.com");
        int user3Id = database.getUserDAO().getUserIdByEmail("alice.johnson@example.com");

        user1.setId(user1Id);
        user2.setId(user2Id);
        user3.setId(user3Id);

        Log.d("InsertDebug", "User IDs: " + user1.getId() + ", " + user2.getId() + ", " + user3.getId());

        Log.d("InsertDebug", "test4");

        Log.d("InsertDebug", "test4");

        Task task1 = new Task("Develop new feature", "Develop a new feature for the app", "2024-12-31", "Pending", user1.getId());
        Task task2 = new Task("Organize team meeting", "Schedule and organize a team meeting", "2024-12-25", "Pending", user2.getId());
        Task task3 = new Task("Close sale with client", "Finalize the sale contract with the client", "2024-12-22", "Pending", user3.getId());

        database.getTaskDao().insertTask(task1);
        database.getTaskDao().insertTask(task2);
        database.getTaskDao().insertTask(task3);

        Log.d("InsertDebug", "test5");

        // logurile sunt puse pentru debugging!!
    }

}
