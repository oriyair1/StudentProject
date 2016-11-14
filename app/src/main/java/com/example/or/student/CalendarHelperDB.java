package com.example.or.student;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Or on 21/09/2016.
 */
public class CalendarHelperDB {

    CalendarDB d;
    SQLiteDatabase db;
    static final String[] days = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    public CalendarHelperDB(Context c){
        d = new CalendarDB(c);

    }

    public void insertEvent(int year, int month, int day, String hour, String event){
        db = d.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(d.YEAR, year);
        values.put(d.MONTH, month);
        values.put(d.DAY, day);
        values.put(d.HOUR, hour);
        values.put(d.EVENT, event);
        db.insert(d.EVENTS, null, values);
    }

    public ArrayList<Hour> getEventsOfDay(int year, int month, int day){
        db = d.getWritableDatabase();
        ArrayList<Hour> hours = new ArrayList<Hour>();
        String[] columns = {d.YEAR, d.MONTH, d.DAY, d.HOUR, d.EVENT};
        Cursor cursor = db.query(d.EVENTS, columns, null, null, null, null, null);
        Hour hour;
        String year1;
        String month1;
        String day1;
        String hour1;
        String event1;
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){

            year1 = cursor.getString(cursor.getColumnIndex(d.YEAR));
            month1 = cursor.getString(cursor.getColumnIndex(d.MONTH));
            day1 = cursor.getString(cursor.getColumnIndex(d.DAY));
            hour1 = cursor.getString(cursor.getColumnIndex(d.HOUR));
            event1 = cursor.getString(cursor.getColumnIndex(d.EVENT));
            if(year1.equals(""+year) && month1.equals(""+month) && day1.equals(""+day)){
                hour = new Hour(hour1, event1);
                hours.add(hour);
            }

        }

        ArrayList<Hour> listedHours = new ArrayList<Hour>();
        int index=0, size = hours.size();
        for(int k=0; k<size; k++) {
            for (int i = 0; i < hours.size(); i++) {
                if (hours.get(index).isLaterThan(hours.get(i))) index = i;
            }
            listedHours.add(hours.get(index));
            hours.remove(index);
            index = 0;
        }

        return listedHours;

    }

    public void deleteHour(int year, int month, int day, String hour){
        db = d.getWritableDatabase();
        db.delete(d.EVENTS, d.YEAR + "='" + year + "' and " + d.MONTH + "='" + month + "' and " + d.DAY + "='" + day + "' and " + d.HOUR + "='" + hour + "'", null);
    }

}
