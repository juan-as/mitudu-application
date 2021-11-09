package com.example.projectappkelasbesar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EditTask2 extends AppCompatActivity {

    EditText et_name, et_date, et_time;
    Button btn_add;
    ToDoClass toDoClass;

    final Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task2);

        et_name = findViewById(R.id.et_name);
        et_date = findViewById(R.id.et_date);
        et_time = findViewById(R.id.et_time);
        btn_add = findViewById(R.id.btn_add);

        Intent intent = getIntent();

        toDoClass = intent.getParcelableExtra("Task");

        et_name.setText(toDoClass.getNamaToDo());
        et_date.setText(toDoClass.getTanggalToDoFinal());
        et_time.setText(toDoClass.getJamToDoFinal());

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        et_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(EditTask2.this, date,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
                updateTime();
            }
        };

        et_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(EditTask2.this, time,
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE), true).show();
            }
        });

    }

    public void updateTask(View view) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        Integer ID = toDoClass.getIdToDo();
        String Name = et_name.getText().toString();
        String Date = et_date.getText().toString();
        String Hour = et_time.getText().toString();
        toDoClass = new ToDoClass(ID, Name, toDoClass.getTanggalToDoStart(), toDoClass.getJamToDoStart(), Date, Hour, toDoClass.getStatus());
        dbHelper.updateTask(toDoClass);
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void updateLabel() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        et_date.setText(sdf.format(calendar.getTime()));
    }

    private void updateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        et_time.setText(sdf.format(calendar.getTime()));
    }
}