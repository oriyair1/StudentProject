package com.example.or.student;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Or on 29/10/2016.
 */
public class CalendarPager extends FragmentPagerAdapter {

    int month, year;
    MonthFragment[] fragments;
    String titles[];

    public CalendarPager(FragmentManager fm, int y, int m) {
        super(fm);
        year = y;
        month = m;
        fragments = new MonthFragment[24];
        for(int i=0; i<24; i++){
            fragments[i] = new MonthFragment(year, month+i-1);
        }

        titles = new String[]{"January", "Fabruary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};


    }

    @Override
    public CharSequence getPageTitle(int position) {

        int i = month-1+position;
        int year1 = year;

        if(i>11){
            year1+=1;
        }
        if(i>23){
            year1+=1;
        }
        i = i%12;

        return titles[i]+", "+year1;

    }

    @Override
    public Fragment getItem(int position) {

        return fragments[position];
    }

    @Override
    public int getCount() {
        return 24;
    }


}
