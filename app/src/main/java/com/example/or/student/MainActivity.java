package com.example.or.student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClickHours(View v){
        startActivity(new Intent(this, HoursActivity.class));
    }
    public void onClickCalendar(View v){
        startActivity(new Intent(this, CalendarActivity.class));
    }
    public void onClickHomework(View v){
        startActivity(new Intent(this, HomeworkActivity.class));
    }
}
