package com.github.seyyedmojtaba72.android_utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by SeyyedMojtaba on 6/24/17.
 */

public class NotificationUtils {

    private static final String TAG = NotificationUtils.class.getSimpleName();


    public static Notification createNotification(Context context, int small_icon, String title, String text) {
        return createNotification(context, small_icon, title, text, null);

    }

    public static Notification createNotification(Context context, int small_icon, String title, String text, Intent intent) {
        return createNotification(context, small_icon, -1, title, text, intent, true, -1, true, -1, true, false, -1, -1, -1);
    }


    public static Notification createNotification(Context context, int small_icon, int icon, String title, String text, Intent intent, boolean autoCancel, long when, boolean hasSound, int sound, boolean hasVibrate, boolean hasLED, int LEDColor, int LEDOnMS, int LEDOffMS) {
        if (context == null) {
            return null;
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(small_icon);

        if (icon != -1) {
            notificationBuilder = notificationBuilder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), icon));
        }
        notificationBuilder = notificationBuilder
                .setContentTitle(title)
                .setContentText(text)
                .setTicker(text)
                .setAutoCancel(autoCancel);

        if (when != -1) {
            notificationBuilder = notificationBuilder.setWhen(when);
        }

        if (intent != null) {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            notificationBuilder.setContentIntent(pendingIntent);
        }


        if (hasLED) {
            Log.d(TAG, "[createNotification] notification has led.");

            notificationBuilder.setLights(LEDColor, LEDOnMS, LEDOffMS);

        }

        Notification notification;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            notification = notificationBuilder.build();
        } else {
            notification = notificationBuilder.getNotification();
        }


        if (hasSound) {
            if (sound != -1) {
                notification.defaults |= sound;
            } else {
                notification.defaults |= Notification.DEFAULT_SOUND;
            }
        }

        if (hasVibrate) notification.defaults |= Notification.DEFAULT_VIBRATE;

        return notification;
    }

    public static void showNotification(Context context, Notification notification, int id) {
        String channelId = "channel-01";
        String channelName = "Channel Name";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            mNotificationManager.createNotificationChannel(mChannel);
        }

        mNotificationManager.notify(id, notification);
    }
}
