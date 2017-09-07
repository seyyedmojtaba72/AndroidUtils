package com.github.seyyedmojtaba72.android_utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.os.Vibrator;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.List;

/**
 * Created by SeyyedMojtaba on 6/24/17.
 */

public class DeviceManager {
    private static final String TAG = DeviceManager.class.getSimpleName();


    public static void Vibrate(Context context, int time) {
        ((Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE)).vibrate(time);
    }

    public static void playSound(Context context, int sound, final int length, int streamType) {
        try {
            final MediaPlayer mp = MediaPlayer.create(context, sound);
            AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            float volume = (float) (am.getStreamVolume(streamType) / (double) am.getStreamMaxVolume(streamType));
            mp.setVolume(volume, volume);
            mp.setLooping(false);
            mp.start();
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mp.setLooping(false);
                    //if (mp.isPlaying()) {
                    mp.stop();
                    mp.release();
                    //}
                }
            }, length);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static boolean isServiceRunning(Context context, Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        hideKeyboard(activity.getApplicationContext(), view);
    }

    public static void hideKeyboard(Context context, View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static boolean isAppOnForeground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }
        final String packageName = context.getPackageName();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses)
            if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND && appProcess.processName.equals(packageName)) {
                return true;
            }
        return false;
    }

    public static boolean isPackageInstalled(String packageName, PackageManager packageManager) {
        try {
            packageManager.getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
