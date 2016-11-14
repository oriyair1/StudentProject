package com.example.or.student;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

/**
 * Created by Or on 23/07/2016.
 */
public class HoursPagerAdapter extends FragmentPagerAdapter {

    DayFragment[] dayFragments;
    String[] titles;

    public HoursPagerAdapter(FragmentManager fm){
        super(fm);

        titles = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        dayFragments = new DayFragment[7];
        for(int i=0; i<7; i++){
            dayFragments[i] = new DayFragment();
            dayFragments[i].setDay(i);
        }
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return dayFragments[position];
    }

    @Override
    public int getCount() {
        return 7;
    }
}
