package com.example.projectappkelasbesar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Database Info
    private static final String DB_NAME = "ToDoDatabase.db";
    private static final int DB_VERSION = 1;

    //Table Info
    private static final String TABLE_NAME = "To_Do";
    private static final String TABLE_COLUMN_ID = "To_Do_ID";
    private static final String TABLE_COLUMN_NAME = "To_Do_Name";
    private static final String TABLE_COLUMN_DATE_START  = "To_Do_Date_Start";
    private static final String TABLE_COLUMN_HOUR_START  = "To_Do_Hour_Start";
    private static final String TABLE_COLUMN_DATE_FINAL  = "To_Do_Date_Final";
    private static final String TABLE_COLUMN_HOUR_FINAL  = "To_Do_Hour_Final";
    private static final String STATUS = "To_Do_Status";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                TABLE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TABLE_COLUMN_NAME + " TEXT NOT NULL, " +
                TABLE_COLUMN_DATE_START + " TEXT NOT NULL, " +
                TABLE_COLUMN_HOUR_START + " TEXT NOT NULL, " +
                TABLE_COLUMN_DATE_FINAL + " TEXT NOT NULL, " +
                TABLE_COLUMN_HOUR_FINAL + " TEXT NOT NULL, " +
                STATUS + " TEXT NOT NULL)");

    }

    public ArrayList getProgressList(String ToDoStatus){

        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + STATUS + " =?", new String[]{ToDoStatus});
        ArrayList<ToDoClass> toDoClasses = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{

                Integer id = cursor.getInt(0);
                String name = cursor.getString(1);
                String date_start = cursor.getString(2);
                String hour_start = cursor.getString(3);
                String date_final = cursor.getString(4);
                String hour_final = cursor.getString(5);
                String status = cursor.getString(6);

                toDoClasses.add(new ToDoClass(id, name, date_start, hour_start, date_final, hour_final, status));

            }while (cursor.moveToNext());
        }

        cursor.close();

        return toDoClasses;
    }

    public void addTask(ToDoClass toDoClass){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues taskContent = new ContentValues();

        taskContent.put(TABLE_COLUMN_NAME, toDoClass.getNamaToDo());
        taskContent.put(TABLE_COLUMN_DATE_FINAL, toDoClass.getTanggalToDoFinal());
        taskContent.put(TABLE_COLUMN_HOUR_FINAL, toDoClass.getJamToDoFinal());
        taskContent.put(TABLE_COLUMN_DATE_START, toDoClass.getTanggalToDoStart());
        taskContent.put(TABLE_COLUMN_HOUR_START, toDoClass.getJamToDoStart());
        taskContent.put(STATUS, "PROGRESS");

        database.insert(TABLE_NAME, null, taskContent);
    }

    public void editTask(){

    }

    public void doneTask(ToDoClass toDoClass){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL("UPDATE " + TABLE_NAME + " SET " + STATUS + " = 'DONE'" + " WHERE " + TABLE_COLUMN_ID + " = " + toDoClass.getIdToDo());
    }

    public void deleteTask(ToDoClass toDoClass){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE "  + TABLE_COLUMN_ID + "  = '"+ toDoClass.getIdToDo() + "'");
    }

    public void updateTask(ToDoClass toDoClass) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL("UPDATE " + TABLE_NAME + " SET " + TABLE_COLUMN_DATE_FINAL + " = '" + toDoClass.getTanggalToDoFinal() + "', " + TABLE_COLUMN_HOUR_FINAL + " = '" + toDoClass.getJamToDoFinal() + "', " + TABLE_COLUMN_NAME + " = '" + toDoClass.getNamaToDo() + "' WHERE " + TABLE_COLUMN_ID + " = '" + toDoClass.getIdToDo() + "'");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
