package com.example.or.student;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity {


    FragmentManager fm;
    ViewPager viewPager;
    Calendar cal;
    Toolbar toolbar;
    ActionBar actionBar;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        toolbar = (Toolbar)findViewById(R.id.toolbarCalendar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tabLayout = (TabLayout)findViewById(R.id.tabs);

        cal = Calendar.getInstance();
        fm = getSupportFragmentManager();
        viewPager = (ViewPager)findViewById(R.id.calendarPager);
        fm = getSupportFragmentManager();
        CalendarPager calendarPagerAdapter = new CalendarPager(fm, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH));
        viewPager.setAdapter(calendarPagerAdapter);
        viewPager.setCurrentItem(1);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.calendar_menu, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.buttonAddEvent:
                Intent i = new Intent(this, AddEventActivity.class);
                startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
