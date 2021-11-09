package com.example.projectappkelasbesar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EditTaskPage extends AppCompatActivity {

    ToDoClass toDoClass;
    TextView tvNamaTask, tvJamTask, tvTanggalTask;
    Button done_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task_page);

        tvNamaTask = findViewById(R.id.tvNamaTask);
        tvJamTask = findViewById(R.id.tvJamTask);
        tvTanggalTask = findViewById(R.id.tvTanggalTask);
        done_btn = findViewById(R.id.done_btn);

        Intent intent = getIntent();
        toDoClass = intent.getParcelableExtra("Task");

        tvNamaTask.setText(toDoClass.getNamaToDo());
        tvJamTask.setText(toDoClass.getJamToDoFinal());
        tvTanggalTask.setText(toDoClass.getTanggalToDoFinal());

    }

    public void doneFunction(View view) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        dbHelper.doneTask(toDoClass);
        finish();
    }

    public void deleteFunction(View view) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        dbHelper.deleteTask(toDoClass);
        finish();
    }

    public void editTask(View view) {
        Intent intent = new Intent(this, EditTask2.class);
        intent.putExtra("Task", toDoClass);
        startActivity(intent);
    }
}