package com.example.or.student;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;

/**
 * Created by Or on 27/08/2016.
 */
public class AddHourDialog extends DialogFragment {

    ImageButton add;
    EditText type;
    TimePicker timePicker;
    HelperDB myDB;
    int day;
    HoursRecyclerAdapter adapter;

    @Override
    public void onResume() {
        super.onResume();

        getDialog().getWindow().setLayout(800, 800);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_hour, container, false);

        setCancelable(true);
        add = (ImageButton)v.findViewById(R.id.vi);
        type = (EditText)v.findViewById(R.id.typeClass);
        timePicker = (TimePicker)v.findViewById(R.id.timePicker);
        myDB = new HelperDB(getContext());


        timePicker.setIs24HourView(true);
        timePicker.setCurrentHour(00);
        timePicker.setCurrentMinute(00);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hour = timePicker.getCurrentHour().toString();
                String minute;
                if(timePicker.getCurrentMinute().toString().length()==1) minute = "0" + timePicker.getCurrentMinute().toString();
                else minute = timePicker.getCurrentMinute().toString();
                String time = hour + ":" + minute;

                myDB.insertHour(day, time, type.getText().toString());
                adapter.syncHours();
                dismiss();
            }
        });


        return v;
    }

    public AddHourDialog(int d, HoursRecyclerAdapter a){
        day = d;
        adapter = a;
    }
}
