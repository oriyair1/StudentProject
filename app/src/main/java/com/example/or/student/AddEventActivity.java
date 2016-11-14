package com.example.or.student;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class AddEventActivity extends AppCompatActivity {

    TextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        time = (TextView)findViewById(R.id.timeTextEvent);

    }

    public void onClickTimeEvent(View v){
        ChooseHourDialog dialog = new ChooseHourDialog(time);
        dialog.show(getSupportFragmentManager(), "chooseTime");
    }

}
