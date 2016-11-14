package com.example.or.student;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Or on 21/09/2016.
 */
public class CalendarDB extends SQLiteOpenHelper {
    static final int DATABASE_VERSION = 4;
    static final String DATABASE_NAME = "CalendarDB.db";
    static final String EVENTS = "Events";
    static final String EVENT = "Event";
    static final String UID = "_id";
    static final String HOUR = "Hour";
    static final String DAY = "Day";
    static final String MONTH = "Month";
    static final String YEAR = "Year";


    public CalendarDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL("CREATE TABLE "+EVENTS+"("+
               UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
               YEAR+" varchar(225), "+
               MONTH+" varchar(225), "+
               DAY+" varchar(225), "+
               HOUR+" varchar(225), "+
               EVENT+" varchar(225));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+EVENTS);

        onCreate(db);
    }
}
