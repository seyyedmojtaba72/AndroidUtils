package com.github.seyyedmojtaba72.android_utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;

/**
 * Created by SeyyedMojtaba on 6/24/17.
 */

public class NotificationUtils {

    private static final String TAG = NotificationUtils.class.getSimpleName();

    public static Notification createNotification(Context context, int small_icon, int icon, String text, String title, String full_text, Intent intent, boolean autoCancel, long when, boolean hasSound, int sound, boolean hasVibrate, boolean hasLED, int LEDcolor, int LEDOnMS, int LEDOffMS) {
        if (context == null) {
            return null;
        }

        Notification.Builder notificationBuilder = new Notification.Builder(context).setSmallIcon(small_icon).setLargeIcon(BitmapFactory.decodeResource(context.getResources(), icon)).setContentTitle(title).setContentText(text).setTicker(text).setAutoCancel(autoCancel).setWhen(when).setDefaults(0);

        if (intent != null) {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            notificationBuilder.setContentIntent(pendingIntent);
        }


        if (hasLED) {
            Log.d(TAG, "[createNotification] notification has led.");

            notificationBuilder.setLights(LEDcolor, LEDOnMS, LEDOffMS);

        }

        Notification notification;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            notification = notificationBuilder.build();
        } else {
            notification = notificationBuilder.getNotification();
        }


        if (hasSound) {
            if (sound != 0) {
                notification.defaults |= sound;
            } else {
                notification.defaults |= Notification.DEFAULT_SOUND;
            }
        }

        if (hasVibrate) notification.defaults |= Notification.DEFAULT_VIBRATE;

		/*
         * if (hasLED) { Log.d(TAG,
		 * "[createNotification] notification has led.");
		 *
		 * notification.ledARGB = LEDcolor; notification.ledOnMS = LEDOnMS;
		 * notification.ledOffMS = LEDOffMS;
		 *
		 * notification.defaults |= Notification.FLAG_SHOW_LIGHTS;
		 *
		 * }
		 */

        return notification;
    }

    public static void showNotification(Context context, Notification notification, int id) {
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(id, notification);
    }
}
