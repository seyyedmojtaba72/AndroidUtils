package com.github.seyyedmojtaba72.android_utils;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

/**
 * Created by SeyyedMojtaba on 6/24/17.
 */

public class StringUtils {
    private static final String TAG = StringUtils.class.getSimpleName();

    public static String toPersianDigits(String string) {
        return string.replaceAll("0", "۰").replaceAll("1", "۱").replaceAll("2", "۲").replaceAll("3", "۳").replaceAll("4", "۴").replaceAll("5", "۵").replaceAll("6", "۶").replaceAll("7", "۷").replaceAll("8", "۸").replaceAll("9", "۹");
    }

    public static String toEnglishDigits(String string) {
        return string.replaceAll("۰", "0").replaceAll("۱", "1").replaceAll("۲", "2").replaceAll("۳", "3").replaceAll("۴", "4").replaceAll("۵", "5").replaceAll("۶", "6").replaceAll("۷", "7").replaceAll("۸", "8").replaceAll("۹", "9");
    }


    public static String fromUTF(String string) {
        try {
            String str = new String(string.getBytes("UTF-8"), "ISO-8859-1");
            return str;
        } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
            localUnsupportedEncodingException.printStackTrace();
        }
        return "";
    }

    public static String toUTF(String string) {
        try {
            String str = new String(string.getBytes("ISO-8859-1"), "UTF-8");
            return str;
        } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
            localUnsupportedEncodingException.printStackTrace();
        }
        return "";
    }


    public static String formatNumber(String price, String splitter) {
        String normaled = "";
        if (price.substring(0, 1).equals("-")) {
            normaled = "-";
            price = price.replace("-", "");
        }
        int j;
        for (int i = 0; i < price.length(); i++) {
            j = price.length() - i;
            if (j % 3 == 0 && j >= 0 && j < price.length()) normaled += splitter;
            normaled += price.substring(i, i + 1);
        }
        return normaled;
    }

    public static String normalPastTime(String persianDate, String time) {
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
            Log.d(TAG, "[normalPastTime] difference between time and now is " + i13 + " seconds.");

            int i14 = i13 / 31536000;
            int i15 = i13 - 31536000 * i14;
            int i16 = i15 / 2592000;
            int i17 = i15 - 2592000 * i16;
            int i18 = i17 / 86400;
            int i19 = i17 - 86400 * i18;
            int i20 = i19 / 3600;
            int i21 = i19 - i20 * 3600;
            int i22 = i21 / 60;
            int i23 = i21 - i22 * 60;
            if (i23 > 0) {
                past_time_text = (" " + String.valueOf(i23) + " ثانیه");
            }
            if (i22 > 0) {
                past_time_text = (String.valueOf(i22) + " دقیقه و");
            }
            if (i20 > 0) {
                past_time_text = (String.valueOf(i20) + " ساعت و");
            }
            if (i18 > 0) {
                past_time_text = (String.valueOf(i18) + " روز و");
            }
            if (i16 > 0) {
                past_time_text = (String.valueOf(i16) + " ماه و");
            }
            if (i14 > 0) {
                past_time_text = (String.valueOf(i14) + " سال و");
            }
            if (past_time_text.endsWith(" و")) {
                past_time_text = past_time_text.substring(0, past_time_text.lastIndexOf(" و"));
            }

            if (i13 <= 0) {
                past_time_text = "لحظاتی";
            }
            return past_time_text;
        } catch (Exception e) {
            Log.d(TAG, "[normalPastTime] format of date or time is not correct. it must be yyyy/mm/dd and hh:mm:ss.");
            e.printStackTrace();
        }
        return "لحظاتی";
    }

    public static String normalPhoneNumber(String number, String code) {
        if (number.substring(0, 1).equals("0")) {
            number = number.replaceFirst("0", code);
        }
        return number;
    }

    public static String normalLength(String text, int length, String character, boolean atFirst) {
        //Log.d(TAG, "[normalLength] old: " + text);

        for (int i = text.length(); i < length; i++) {
            if (atFirst) {
                text = character + text;
            } else {
                text += character;
            }
        }

        //Log.d(TAG, "[normalLength] new: " + text);

        return text;
    }

}
