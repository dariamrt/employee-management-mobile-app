package com.example.proiectandroid.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.proiectandroid.models.Department;
import com.example.proiectandroid.models.DepartmentDao;
import com.example.proiectandroid.models.Position;
import com.example.proiectandroid.models.PositionDao;
import com.example.proiectandroid.models.Task;
import com.example.proiectandroid.models.TaskDao;
import com.example.proiectandroid.models.User;
import com.example.proiectandroid.models.UserDao;
@Database(entities = {Department.class, User.class, Position.class, Task.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final String dbName = "proiectAndroid.db";
    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class, dbName)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract UserDao getUserDAO();
    public abstract TaskDao getTaskDao();
    public abstract PositionDao getPositionDao();
    public abstract DepartmentDao getDepartmentDao();
}

