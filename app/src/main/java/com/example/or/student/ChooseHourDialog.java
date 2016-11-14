package com.example.or.student;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Or on 21/09/2016.
 */
public class ChooseHourDialog extends DialogFragment {

    TimePicker timePicker;
    TextView timeText;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
         return new AlertDialog.Builder(getActivity())
                .setTitle("title")
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                String hour = timePicker.getCurrentHour().toString();
                                String minute;
                                if(timePicker.getCurrentMinute().toString().length()==1) minute = "0" + timePicker.getCurrentMinute().toString();
                                else minute = timePicker.getCurrentMinute().toString();
                                String time = hour + ":" + minute;
                                timeText.setText(time);
                                dialog.dismiss();
                            }
                        }
                )
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        }
                )
                .create();
    }

    @Override
    public void onResume() {
        super.onResume();

        getDialog().getWindow().setLayout(800,1500);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_choose_hour, container, false);

        setCancelable(true);

        //db = new CalendarHelperDB(getContext());


        //event = (EditText)v.findViewById(R.id.typeEvent);
        timePicker = (TimePicker)v.findViewById(R.id.timePickerEvent);


        timePicker.setIs24HourView(true);
        timePicker.setCurrentHour(00);
        timePicker.setCurrentMinute(00);




        return v;
    }

    public ChooseHourDialog(TextView t){
        timeText = t;
    }
}