package com.example.or.student;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class HoursRecyclerAdapter extends RecyclerView.Adapter<HourView> {

    View v;
    LayoutInflater inflater;
    Context context;
    ArrayList<Hour> hours;
    HelperDB myDB;
    int day;

    public HoursRecyclerAdapter(Context con, int d){
        context = con;
        inflater = LayoutInflater.from(context);
        myDB = new HelperDB(context);
        day = d;
        hours = myDB.getHoursOfDay(day);

    }

    public void syncHours(){
        hours = myDB.getHoursOfDay(day);
        notifyDataSetChanged();
    }

    @Override
    public HourView onCreateViewHolder(ViewGroup parent, int viewType) {

        v = inflater.inflate(R.layout.hour_item_layout, parent, false);
        HourView hourView = new HourView(v, context, myDB, day, this);
        return hourView;
    }

    @Override
    public void onBindViewHolder(HourView holder, int position) {
        Hour hour = hours.get(position);
        holder.hour.setText(hour.hour);
        holder.type.setText(hour.type);
    }



    @Override
    public int getItemCount() {
        return hours.size();
    }
}


class HourView extends RecyclerView.ViewHolder{

    TextView hour;
    TextView type;
    HoursRecyclerAdapter adapter;
    Context context;
    int day;
    HelperDB myDB;

    public HourView(View v, Context c, HelperDB m, int d, HoursRecyclerAdapter a){
        super(v);
        context = c;
        adapter = a;
        day = d;
        myDB = m;
        hour = (TextView)v.findViewById(R.id.time);
        type = (TextView)v.findViewById(R.id.type);
        v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete Hour").setMessage("Are you sure you want to remove this hour?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                myDB.deleteHour(day, hour.getText().toString(), type.getText().toString());
                                adapter.syncHours();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                Dialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });


    }

}