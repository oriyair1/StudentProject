package com.example.or.student;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class DayFragment extends Fragment {

    int day;
    RecyclerView recyclerView;
    FloatingActionButton fab;
    FragmentManager fm;
    RelativeLayout rl;
    HelperDB myDB;

    public DayFragment() {
        // Required empty public constructor
    }

    public void setDay(int d){
        day = d;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_day, container, false);
        myDB = new HelperDB(getContext());
        fab = (FloatingActionButton)v.findViewById(R.id.fab);
        recyclerView = (RecyclerView)v.findViewById(R.id.hoursList);




        final HoursRecyclerAdapter adapter = new HoursRecyclerAdapter(getContext(), day);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        fm = getChildFragmentManager();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddHourDialog dialog = new AddHourDialog(day, adapter);
                dialog.show(fm, "addHour");

            }
        });




        return v;
    }

}
