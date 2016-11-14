package com.example.or.student;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Or on 23/07/2016.
 */
public class DB extends SQLiteOpenHelper {
    static final int DATABASE_VERSION = 2;
    static final String DATABASE_NAME = "MyDB.db";
    static final String UID = "_id";
    static final String HOUR = "Hour";
    static final String CLASS = "Class";
    static final String[] titles = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};





    public DB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private String createTable(String day){
        return "CREATE TABLE "+day+"("+
                UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                HOUR+" varchar(225), "+
                CLASS+" varchar(225));";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for(int i=0; i<titles.length; i++){
            db.execSQL(createTable(titles[i]));
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for(int i=0; i<titles.length; i++){
            db.execSQL("DROP TABLE IF EXISTS "+titles[i]);
        }
        onCreate(db);
    }
}