package com.example.proiectandroid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.proiectandroid.models.Department;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    // voi implementa, de vazut daca fol SQLite ca altfel se schimba situatia
    private static final String DATABASE_NAME = "companie.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_DEPARTMENTS = "departments";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";

    // crare tabel
    private static final String CREATE_TABLE_DEPARTMENTS = "CREATE TABLE " + TABLE_DEPARTMENTS + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT, "
            + COLUMN_DESCRIPTION + " TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_DEPARTMENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEPARTMENTS);
        onCreate(db);
    }

    // ad departament nou
    public long addDepartment(Department department) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, department.getName());
        values.put(COLUMN_DESCRIPTION, department.getDescription());

        long id = db.insert(TABLE_DEPARTMENTS, null, values);
        db.close();
        return id;
    }

    // obtinere departament
    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_DEPARTMENTS, null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION));

                Department department = new Department(id, name, description);
                departments.add(department);
            } while (cursor.moveToNext());
            cursor.close();
        }

        db.close();
        return departments;
    }

    // sterge dep
    public void deleteDepartment(int departmentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DEPARTMENTS, COLUMN_ID + " = ?", new String[]{String.valueOf(departmentId)});
        db.close();
    }

    // actualizeaza
    public int updateDepartment(Department department) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, department.getName());
        values.put(COLUMN_DESCRIPTION, department.getDescription());

        int rowsUpdated = db.update(TABLE_DEPARTMENTS, values, COLUMN_ID + " = ?", new String[]{String.valueOf(department.getId())});
        db.close();
        return rowsUpdated;
    }
}
