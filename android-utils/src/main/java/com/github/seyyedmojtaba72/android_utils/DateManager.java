package com.github.seyyedmojtaba72.android_utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static com.github.seyyedmojtaba72.android_utils.StringUtils.normalLength;

/**
 * Created by SeyyedMojtaba on 6/24/17.
 */

public class DateManager {
    private static final String TAG = DateManager.class.getSimpleName();

    public static Date getNowDate(TimeZone timezone) {
        Calendar cal = Calendar.getInstance(timezone);
        Date currentLocalTime = cal.getTime();

        return currentLocalTime;
    }

    public static String getNowDateTime(TimeZone timezone, String format) {
        Calendar cal = Calendar.getInstance(timezone);
        Date currentLocalTime = cal.getTime();
        try {
            DateFormat date = new SimpleDateFormat(format);
            date.setTimeZone(timezone);

            String localTime = date.format(currentLocalTime);
            return localTime;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Long getNowTimestamp(TimeZone timezone) {
        Calendar cal = Calendar.getInstance(timezone);
        Date currentLocalTime = cal.getTime();
        return currentLocalTime.getTime();
    }

    public static String getDateTimeFromTimestamp(TimeZone timezone, long timestamp, String format) {
        try {
            Calendar calendar = Calendar.getInstance(timezone);
            timestamp = Long.parseLong(normalLength(String.valueOf(timestamp), 13, "0", false));
            calendar.setTimeInMillis(timestamp);
            Date currentLocalTime = calendar.getTime();
            DateFormat date = new SimpleDateFormat(format);
            date.setTimeZone(timezone);

            String localTime = date.format(currentLocalTime);
            return localTime;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getDayOfWeek(String date) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(date));
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek;
    }

    public static String getWeekDayPersianName(String date) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(date));
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        String day_of_week = "";
        switch (dayOfWeek) {
            case 1: {
                return "یک‌شنبه";
            }
            case 2: {
                return "دوشنبه";
            }
            case 3: {
                return "سه‌شنبه";
            }
            case 4: {
                return "چهارشنبه";
            }
            case 5: {
                return "پنج‌شنبه";
            }
            case 6: {
                return "جمعه";
            }
            case 7: {
                return "شنبه";
            }
        }
        return day_of_week;
    }

    public static Date getDateFromTimestamp(TimeZone timezone, long timestamp) {
        try {
            Calendar calendar = Calendar.getInstance(timezone);
            timestamp = Long.parseLong(normalLength(String.valueOf(timestamp), 13, "0", false));
            calendar.setTimeInMillis(timestamp);
            calendar.add(Calendar.MILLISECOND, timezone.getOffset(calendar.getTimeInMillis()));
            return calendar.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static long getTimestampFromDate(TimeZone timezone, Date date) {
        try {
            Calendar calendar = Calendar.getInstance(timezone);
            calendar.setTime(date);
            return calendar.getTimeInMillis();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
