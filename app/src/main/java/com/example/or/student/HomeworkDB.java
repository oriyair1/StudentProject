package com.example.or.student;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Or on 15/10/2016.
 */
public class HomeworkDB extends SQLiteOpenHelper {
    static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "HomeworkDB.db";
    static final String HOMEWORK = "Homework";
    static final String DESCRIPTION = "Description";
    static final String UID = "_id";
    static final String DAY = "Day";
    static final String MONTH = "Month";
    static final String YEAR = "Year";


    public HomeworkDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+HOMEWORK+"("+
                UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                YEAR+" varchar(225), "+
                MONTH+" varchar(225), "+
                DAY+" varchar(225), "+
                DESCRIPTION+" varchar(225));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+HOMEWORK);

        onCreate(db);
    }
}
