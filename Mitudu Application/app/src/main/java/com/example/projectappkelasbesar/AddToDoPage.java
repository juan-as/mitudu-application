package com.example.projectappkelasbesar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddToDoPage extends AppCompatActivity {

    EditText et_name, et_date, et_time;
    Button btn_add;

    final Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do_page);

        et_name = findViewById(R.id.et_name);
        et_date = findViewById(R.id.et_date);
        et_time = findViewById(R.id.et_time);
        btn_add = findViewById(R.id.btn_add);

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
                new DatePickerDialog(AddToDoPage.this, date,
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
                new TimePickerDialog(AddToDoPage.this, time,
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE), true).show();
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String taskName = et_name.getText().toString();
                String endDate = et_date.getText().toString();
                String endTime = et_time.getText().toString();
                String startDate = getTodayDate();
                String startTime = getTodayTime();
                ToDoClass toDoClass = new ToDoClass(-1, taskName, startDate, startTime, endDate, endTime, "PROGRESS");

                DatabaseHelper dbHelper = new DatabaseHelper(AddToDoPage.this);

                dbHelper.addTask(toDoClass);

            }
        });

    }

    private void updateLabel() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        et_date.setText(sdf.format(calendar.getTime()));
    }

    private void updateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        et_time.setText(sdf.format(calendar.getTime()));
    }

    public String getTodayDate() {
        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String date = dateFormat.format(todayDate);
        return date;
    }

    public String getTodayTime() {
        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String date = dateFormat.format(todayDate);
        return date;
    }

}