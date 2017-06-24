package com.github.seyyedmojtaba72.android_utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.github.seyyedmojtaba72.android_utils.StringUtils.normalLength;

/**
 * Created by SeyyedMojtaba on 6/24/17.
 */

public class TimeUtils {
    private static final String TAG = TimeUtils.class.getSimpleName();

    public static int getDifferenceOfTimes(String persianDate, String time) {
        try {
            String past_time_text = "";
            String now_date = ShamsiDate.getCurrentShamsidate();

            //Log.d(TAG, "[normalPastTime] today in shamsi is " + now_date);

            int i1 = Integer.parseInt(now_date.split("/")[0]);
            int i2 = Integer.parseInt(now_date.split("/")[1]);
            int i3 = Integer.parseInt(now_date.split("/")[2]);

            String date = persianDate;

            //Log.d(TAG, "[normalPastTime] date is " + date);

            String[] date_splited = date.split("/");

            int i4 = 0, i5 = 0, i6 = 0;
            if (date_splited.length > 0) {
                i4 = Integer.parseInt(date_splited[0]);
            }
            if (date_splited.length > 1) {
                i5 = Integer.parseInt(date_splited[1]);
            }
            if (date_splited.length > 2) {
                i6 = Integer.parseInt(date_splited[2]);
            }

            //Log.d(TAG, "[normalPastTime] THE date is " + i4 + "/" + i5 + "/" + i6);

            int i7 = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            int i8 = Calendar.getInstance().get(Calendar.MINUTE);
            int i9 = Calendar.getInstance().get(Calendar.SECOND);

            //Log.d(TAG, "[normalPastTime] now time is " + i7 + ":" + i8 + ":" + i9);

            String[] time_splited = time.split(":");

            //Log.d(TAG, "[normalPastTime] time is " + time);

            int i10 = 0, i11 = 0, i12 = 0;
            if (time_splited.length > 0) {
                i10 = Integer.parseInt(time_splited[0]);
            }
            if (time_splited.length > 1) {
                i11 = Integer.parseInt(time_splited[1]);
            }
            if (time_splited.length > 2) {
                i12 = Integer.parseInt(time_splited[2]);
            }

            //Log.d(TAG, "[normalPastTime] THE time is " + i10 + ":" + i11 + ":" + i12);

            int i13 = +(31536000 * i1 + 2592000 * i2 + 86400 * i3 + i7 * 3600 + i8 * 60 + i9) - (31536000 * i4 + 2592000 * i5 + 86400 * i6 + i10 * 3600 + i11 * 60 + i12);
            Log.d(TAG, "[getDifferenceOfTimes] difference between time and now is " + i13 + " seconds.");

            return i13;
        } catch (Exception e) {
            Log.d(TAG, "[getDifferenceOfTimes] format of date or time is not correct. it must be yyyy/mm/dd and hh:mm:ss.");
            e.printStackTrace();
        }
        return 0;
    }

    public static String getTimeFormat(long milliSeconds, String dateFormat) {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);


        milliSeconds = Long.parseLong(normalLength(String.valueOf(milliSeconds), 13, "0", false));
        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }
}
