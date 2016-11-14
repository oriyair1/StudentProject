package com.example.or.student;

import android.util.Log;

/**
 * Created by Or on 23/07/2016.
 */


public class Hour{

    String hour;
    String type;

    public Hour(String h, String t){
        hour = h;
        type = t;
    }

    public boolean isLaterThan(Hour h){


        int hour1 = Integer.valueOf(hour.substring(0, hour.indexOf(":")));

        int hour2 = Integer.valueOf(h.hour.substring(0, h.hour.indexOf(":")));

        int minute1 = Integer.valueOf(hour.substring(hour.indexOf(":")+1));

        int minute2 = Integer.valueOf(h.hour.substring(h.hour.indexOf(":")+1));



        if(hour1>hour2) return true;
        else if (hour1<hour2) return false;
        else{
            if(minute1>minute2) return true;
            else return false;
        }
    }
}
