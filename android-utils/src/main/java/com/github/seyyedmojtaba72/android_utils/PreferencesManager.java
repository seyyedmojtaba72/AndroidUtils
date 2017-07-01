package com.github.seyyedmojtaba72.android_utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by SeyyedMojtaba on 6/24/17.
 */

public class PreferencesManager {
    private static final String TAG = PreferencesManager.class.getSimpleName();

    public static boolean isFirstTime(Context context, String element) {
        Boolean bool = true;
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean firstTime = mPreferences.getBoolean(element, true);
        if (firstTime) {
            SharedPreferences.Editor editor = mPreferences.edit();
            editor.putBoolean(element, false);
            editor.commit();
            bool = Boolean.valueOf(true);
        } else {
            bool = Boolean.valueOf(false);
        }
        return bool.booleanValue();
    }

    public static boolean getBoolean(Context context, String element, boolean default_value) {
        boolean bool = default_value;
        try {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

            bool = sharedPreferences.getBoolean(element, default_value);
            return bool;
        } catch (Exception localException1) {
        }
        return bool;
    }

    public static String getString(Context context, String arg0, String default_value) {
        String result = default_value;
        try {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

            result = sharedPreferences.getString(arg0, default_value);
            return result;
        } catch (Exception localException1) {
        }
        return result;
    }

    public static int getInt(Context context, String arg0, int default_value) {
        int result = default_value;
        try {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

            result = sharedPreferences.getInt(arg0, default_value);
            return result;
        } catch (Exception localException1) {
        }
        return result;
    }

    public static float getFloat(Context context, String arg0, float default_value) {
        float result = default_value;
        try {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

            result = sharedPreferences.getFloat(arg0, default_value);
            return result;
        } catch (Exception localException1) {
        }
        return result;
    }

    public static long getLong(Context context, String arg0, long default_value) {
        long result = default_value;
        try {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

            result = sharedPreferences.getLong(arg0, default_value);
            return result;
        } catch (Exception localException1) {
        }
        return result;
    }

    public static void putBoolean(Context context, String arg0, boolean arg1) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(arg0, arg1);
        editor.commit();
    }

    public static void putString(Context context, String arg0, String arg1) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(arg0, arg1);
        editor.commit();
    }

    public static void putInt(Context context, String arg0, int arg1) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(arg0, arg1);
        editor.commit();
    }

    public static void putFloat(Context context, String arg0, float arg1) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(arg0, arg1);
        editor.commit();
    }

    public static void putLong(Context context, String arg0, long arg1) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(arg0, arg1);
        editor.commit();
    }
}
