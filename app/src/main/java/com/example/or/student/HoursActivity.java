package com.example.or.student;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class HoursActivity extends AppCompatActivity {

    Toolbar toolbar;
    ActionBar actionBar;
    ViewPager viewPager;
    FragmentManager fm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hours);

        toolbar = (Toolbar) findViewById(R.id.toolbarHours);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();

        viewPager = (ViewPager)findViewById(R.id.pager);
        fm = getSupportFragmentManager();
        HoursPagerAdapter hoursPagerAdapter = new HoursPagerAdapter(fm);
        viewPager.setAdapter(hoursPagerAdapter);





    }
}
