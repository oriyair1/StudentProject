package com.example.or.student;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Or on 23/07/2016.
 */
public class HelperDB {

    DB d;
    SQLiteDatabase db;
    static final String[] days = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    public HelperDB(Context c){
        d = new DB(c);

    }

    public void insertHour(int day, String hour, String type){
        db = d.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(d.HOUR, hour);
        values.put(d.CLASS, type);
        db.insert(days[day], null, values);
    }

    public ArrayList<Hour> getHoursOfDay(int day){
        db = d.getWritableDatabase();
        ArrayList<Hour> hours = new ArrayList<Hour>();
        String[] columns = {d.HOUR, d.CLASS};
        Cursor cursor = db.query(days[day], columns, null, null, null, null, null);
        Hour hour;
        String h;
        String t;
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){

            h = cursor.getString(cursor.getColumnIndex(d.HOUR));
            t = cursor.getString(cursor.getColumnIndex(d.CLASS));
            Log.i("hiiiiiiiiiii", "hour: " + h);
            Log.i("hiiiiiiiiiii", "class: " + t);
            hour = new Hour(h, t);
            hours.add(hour);
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

    public void deleteHour(int day, String hour, String type){
        db = d.getWritableDatabase();
        db.delete(days[day], d.HOUR + "='" + hour + "' and " + d.CLASS + "='" + type + "'", null);
    }



}
