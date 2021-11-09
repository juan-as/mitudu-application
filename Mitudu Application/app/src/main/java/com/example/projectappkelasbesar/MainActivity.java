package com.example.projectappkelasbesar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView onProgressButton, doneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onProgressButton = findViewById(R.id.onProgressButton);
        doneButton = findViewById(R.id.doneButton);

    }

    @Override
    protected void onResume() {
        super.onResume();
        ToDoFragment toDoFragment = ToDoFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragments, toDoFragment);
        transaction.commit();

    }

    public void toProgress(View view) {
        ToDoFragment toDoFragment = ToDoFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragments, toDoFragment);
        transaction.commit();
        doneButton.setTextColor(Color.parseColor("#bfbfbf"));
        onProgressButton.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.navy));
    }

    public void toDone(View view) {
        DoneFragment doneFragment = DoneFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragments, doneFragment);
        transaction.commit();
        onProgressButton.setTextColor(Color.parseColor("#bfbfbf"));
        doneButton.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.navy));
    }

    public void toAdd(View view) {

        Intent intent = new Intent(this, AddToDoPage.class);
        startActivity(intent);

    }
}