package com.example.or.student;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class MonthFragment extends Fragment {

    RecyclerView calendar;

    int month, year;

    public MonthFragment() {
        // Required empty public constructor
    }
    public MonthFragment(int y, int m){
        month = m;
        year=y;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_month, container, false);

        calendar = (RecyclerView)v.findViewById(R.id.calendar);

        CalendarRecyclerAdapter adapter = new CalendarRecyclerAdapter(getContext(), year, month);
        calendar.setAdapter(adapter);
        calendar.setLayoutManager(new GridLayoutManager(getContext(), 7));


        return v;
    }

}
