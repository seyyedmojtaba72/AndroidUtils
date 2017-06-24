package com.github.seyyedmojtaba72.android_utils;

/**
 * Created by SeyyedMojtaba on 6/24/17.
 */

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;

import java.io.InputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class OtherUtils {
    private static final String TAG = OtherUtils.class.getSimpleName();


    public static String findValueFromVariable(String[] variables, String[] values, String findingVarible) {

        String result = "";

        for (int i = 0; i < variables.length; i++) {
            if (variables[i].equals(findingVarible)) {
                Log.d(TAG, "[findValueFromVariable] " + findingVarible + " = " + values[i]);
                result = values[i];
            }
        }
        return result;
    }

    public static String findValueFromVariable(List<String> variables, List<String> values, String findingVarible) {

        String result = "";

        for (int i = 0; i < variables.size(); i++) {
            if (variables.get(i).equals(findingVarible)) {
                Log.d(TAG, "[findValueFromVariable] " + findingVarible + " = " + values.get(i));
                result = values.get(i);
            }
        }
        return result;
    }


    public static int getDpFromPx(Context context, int px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }

    public static int getPxFromDp(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }


    public static double getVersionValue(String[] version_array, int cells_limit, int cell_size) {
        String value = "0";
        for (int i = 0; i < cells_limit; i++) {
            if (i >= version_array.length) {
                for (int j = 0; j < cell_size; j++) {
                    value += "0";
                }
            } else {
                value += version_array[i];
                for (int j = 0; j < (cell_size - version_array[i].length()); j++) {
                    value += "0";
                }

            }
        }

        Log.d(TAG, "[getVersionValue] version value = " + value);
        try {
            Log.d(TAG, "[getVersionValue] parsed version value = "
                    + NumberFormat.getInstance().parse(value).doubleValue());
            return NumberFormat.getInstance().parse(value).doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void setLocale(Context context, String locale) {
        Locale localLocale = new Locale(locale);
        Resources localResources = context.getResources();
        DisplayMetrics localDisplayMetrics = localResources.getDisplayMetrics();
        Configuration localConfiguration = localResources.getConfiguration();
        localConfiguration.locale = localLocale;
        try {
            localConfiguration.setLayoutDirection(localLocale);
        } catch (Exception e) {
            e.printStackTrace();
        }
        localResources.updateConfiguration(localConfiguration, localDisplayMetrics);
    }


    public static String readStream(InputStream is) {
        // http://stackoverflow.com/a/5445161
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }


}
