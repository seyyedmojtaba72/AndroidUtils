package com.github.seyyedmojtaba72.android_utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

/**
 * Created by SeyyedMojtaba on 6/24/17.
 */

public class PermissionManager {
    private static final String TAG = PermissionManager.class.getSimpleName();

    public static boolean hasPermission(Activity activity, String permission) {
        int status = ActivityCompat.checkSelfPermission(activity, permission);

        return status == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean hasPermissions(Activity activity, String[] permissions) {
        for(String permission : permissions) {
            int status = ActivityCompat.checkSelfPermission(activity, permission);

            if (status != PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if the app has permission to write to device storage
     * <p>
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyPermissions(Activity activity, String[] permissions, boolean ask_each_time) {
        final int requestCode = 1;

        for (int i = 0; i < permissions.length; i++) {
            int status = ActivityCompat.checkSelfPermission(activity, permissions[i]);

            if (!ask_each_time && status != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        activity,
                        permissions,
                        requestCode
                );
                break;
            } else if (ask_each_time && status == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(
                        activity,
                        permissions,
                        requestCode
                );
                break;
            }
        }
    }
}
