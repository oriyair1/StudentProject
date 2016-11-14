package com.example.or.student;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Or on 21/09/2016.
 */
public class CalendarRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {




    View v;
    LayoutInflater inflater;
    Context context;
    Calendar cal;
    int places;
    ArrayList<Hour> events;
    CalendarHelperDB myDB;
    int year;
    int month;
    boolean started  = false;
    final int TYPE_TITLE = 0;
    final int TYPE_ITEM = 1;

    @Override
    public int getItemViewType(int position) {

        if(position<7) return 0;
        else return 1;

    }

    public CalendarRecyclerAdapter(Context con, int y, int m){
        context = con;
        inflater = LayoutInflater.from(context);
        myDB = new CalendarHelperDB(context);
        places = 42;
        year = y;
        month = m;
        cal = Calendar.getInstance();
        cal.set(year, month, 1);
        switch(cal.getActualMaximum(Calendar.DAY_OF_MONTH)){
            case 31:
                if(cal.get(Calendar.DAY_OF_WEEK)>=6)
                {

                    places+=7;
                }
                break;
            case 30:
                if(cal.get(Calendar.DAY_OF_WEEK)==7)
                {
                    places+=7;
                }
                break;
            case 28:
                if(cal.get(Calendar.DAY_OF_WEEK)==1)
                {
                    places-=7;
                }
                break;

        }
        int day = cal.get(Calendar.DAY_OF_WEEK);
        cal.set(year, month, 2-day);



    }

    public void syncHours(){

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType==0){
            v = inflater.inflate(R.layout.days_title, parent, false);
            TitleView titleView = new TitleView(v, 0);
            return titleView;
        }
        else {
            v = inflater.inflate(R.layout.day_layout, parent, false);
            DayView dayView = new DayView(v, cal.get(Calendar.DAY_OF_MONTH), false);
            return dayView;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof TitleView){
            TitleView h = (TitleView) holder;
            h.position = position;
            h.setTextView();
        }
        else {
            DayView h = (DayView) holder;
            h.num = cal.get(Calendar.DAY_OF_MONTH);
            if(started && cal.get(Calendar.DAY_OF_MONTH)==1)
            {

                started = false;
                h.included = false;
            }
            else if(cal.get(Calendar.DAY_OF_MONTH)==1){
                started = true;
            }
            if(started){
                h.included = true;
            }
            h.changeColor();
            cal.add(Calendar.DATE,1);
        }

    }





    @Override
    public int getItemCount() {
        return places;
    }
}

class TitleView extends RecyclerView.ViewHolder{

    TextView textView;
    int position;

    public TitleView(View v, int p) {
        super(v);
        position = p;
        textView = (TextView)v.findViewById(R.id.dayTitle);

    }
    public void setTextView(){
        switch(position){
            case 0:
                textView.setText("Sunday");
                break;
            case 1:
                textView.setText("Monday");
                break;
            case 2:
                textView.setText("Tuesday");
                break;
            case 3:
                textView.setText("Wednesday");
                break;
            case 4:
                textView.setText("thursday");
                break;
            case 5:
                textView.setText("Friday");
                break;
            case 6:
                textView.setText("Saturday");
                break;
        }
    }
}

class DayView extends RecyclerView.ViewHolder{

    TextView numText;
    int num;

    boolean included;
    RelativeLayout layout;

    public DayView(View v, int n, boolean f){
        super(v);
        num = n;
        included = f;

        layout = (RelativeLayout)v.findViewById(R.id.dayLayout);
        numText = (TextView)v.findViewById(R.id.dayNum);
        numText.setText(""+num);

    }

    public void changeColor(){
        if(!included){
            layout.setBackgroundColor(Color.parseColor("#BBDEFB"));
            numText.setTextColor(Color.parseColor("#a3a3a3"));
        }

    }

}