package com.example.or.student;

import android.app.Fragment;
import android.app.TimePickerDialog;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.List;

public class AddEventActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    TextView time;
    TimePickerDialog timePickerDialog;
    CheckBox checkBox;
    ImageView plus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        plus = (ImageView)findViewById(R.id.addReminder);
        time = (TextView)findViewById(R.id.timeTextEvent);
        timePickerDialog = new TimePickerDialog(this, this, 12, 0, true);
        checkBox = (CheckBox)findViewById(R.id.checkBoxReminder);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    getSupportFragmentManager().beginTransaction().add(R.id.reminder_layout, new ReminderFragment(), "reminder").commit();
                    plus.setVisibility(View.VISIBLE);
                }
                else{
                    List<android.support.v4.app.Fragment> fragments = getSupportFragmentManager().getFragments();
                    for(int i=0; i<fragments.size(); i++){
                        getSupportFragmentManager().beginTransaction().remove(fragments.get(i)).commit();
                    }
                    plus.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    public void onClickTimeEvent(View v){
       timePickerDialog.show();
    }
    public void onClickPlus(View v){
        getSupportFragmentManager().beginTransaction().add(R.id.reminder_layout, new ReminderFragment()).commit();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String h = ""+hourOfDay;
        String m = ""+minute;
        if(hourOfDay < 10)
            h = "0" + h;
        if(minute < 10)
            m = "0" + m;
        time.setText(h + ":" + m);
    }
}
