package com.github.seyyedmojtaba72.android_utils.calendar;

import android.content.Context;
import android.support.annotation.RawRes;
import android.text.TextUtils;
import android.util.Log;

import com.github.seyyedmojtaba72.android_utils.Functions;
import com.github.seyyedmojtaba72.android_utils.R;
import com.github.seyyedmojtaba72.android_utils.calendar.enitity.EventEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by SeyyedMojtaba on 3/28/2017.
 */

public class CalendarUtils {

    public static String TAG = CalendarUtils.class.getSimpleName();

    private static String[] persianMonths;
    private static String[] islamicMonths;
    private static String[] gregorianMonths;
    private static String[] weekDays;
    private static List<EventEntity> events;

    private static void loadEvents(Context context) {
        List<EventEntity> events = new ArrayList<>();
        try {
            JSONArray days = new JSONObject(Functions.readRawResource(context, R.raw.events)).getJSONArray("events");

            int length = days.length();
            for (int i = 0; i < length; ++i) {
                JSONObject event = days.getJSONObject(i);

                int year = event.getInt("year");
                int month = event.getInt("month");
                int day = event.getInt("day");
                String title = event.getString("title");
                boolean holiday = event.getBoolean("holiday");

                events.add(new EventEntity(new PersianDate(year, month, day), title, holiday));
            }

        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }
        CalendarUtils.events = events;
    }

    public static List<EventEntity> getEvents(Context context, PersianDate day) {
        if (events == null) {
            loadEvents(context);
        }

        List<EventEntity> result = new ArrayList<>();
        for (EventEntity eventEntity : events) {
            if (eventEntity.getDate().equals(day)) {
                result.add(eventEntity);
            }
        }
        return result;
    }

    public static String getEventsTitle(Context context, PersianDate day, boolean holiday) {
        String titles = "";
        boolean first = true;
        List<EventEntity> dayEvents = getEvents(context, day);

        for (EventEntity event : dayEvents) {
            if (event.isHoliday() == holiday) {
                if (first) {
                    first = false;

                } else {
                    titles = titles + "\n";
                }
                titles = titles + event.getTitle();
            }
        }
        return titles;
    }

    public static void loadLanguageResource(Context context) {
        @RawRes int messagesFile = R.raw.messages_fa;

        persianMonths = new String[12];
        islamicMonths = new String[12];
        gregorianMonths = new String[12];
        weekDays = new String[7];

        try {
            JSONObject messages = new JSONObject(Functions.readRawResource(context, messagesFile));

            JSONArray persianMonthsArray = messages.getJSONArray("PersianCalendarMonths");
            for (int i = 0; i < 12; ++i)
                persianMonths[i] = persianMonthsArray.getString(i);

            JSONArray islamicMonthsArray = messages.getJSONArray("IslamicCalendarMonths");
            for (int i = 0; i < 12; ++i)
                islamicMonths[i] = islamicMonthsArray.getString(i);

            JSONArray gregorianMonthsArray = messages.getJSONArray("GregorianCalendarMonths");
            for (int i = 0; i < 12; ++i)
                gregorianMonths[i] = gregorianMonthsArray.getString(i);

            JSONArray weekDaysArray = messages.getJSONArray("WeekDays");
            for (int i = 0; i < 7; ++i)
                weekDays[i] = weekDaysArray.getString(i);

        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public static int getDayOfWeek(AbstractDate date) {
        if (date instanceof IslamicDate)
            date = DateConverter.islamicToCivil((IslamicDate) date);
        else if (date instanceof PersianDate)
            date = DateConverter.persianToCivil((PersianDate) date);

        return date.getDayOfWeek() % 7;
    }

    public static String getWeekDayName(Context context, AbstractDate date) {

        if (weekDays == null)
            loadLanguageResource(context);

        return weekDays[getDayOfWeek(date)];
    }

    public static boolean isHoliday(Context context, PersianDate persianDate) {

        return CalendarUtils.getDayOfWeek(persianDate) == 6 || !TextUtils.isEmpty(getEventsTitle(context, persianDate, true));

    }

    public static boolean isDatesEqual(Date date, Date date2) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int year = cal.get(Calendar.YEAR);
        int day = cal.get(Calendar.DAY_OF_YEAR);

        cal.setTime(date2);

        int year2 = cal.get(Calendar.YEAR);
        int day2 = cal.get(Calendar.DAY_OF_YEAR);

        Log.d(TAG, "[isDatesEqual] year: " + year + ", day: " + day + ", year2: " + year2 + ", day2: " + day2);

        return year == year2 && day == day2;

    }
}
