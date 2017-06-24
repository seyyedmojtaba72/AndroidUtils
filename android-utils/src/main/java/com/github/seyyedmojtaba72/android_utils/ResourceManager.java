package com.github.seyyedmojtaba72.android_utils;

import android.content.Context;
import android.support.annotation.RawRes;
import android.util.TypedValue;

import static com.github.seyyedmojtaba72.android_utils.OtherUtils.readStream;

/**
 * Created by SeyyedMojtaba on 6/24/17.
 */

public class ResourceManager {
    private static final String TAG = ResourceManager.class.getSimpleName();

    public static int getAttributeData(Context context, int attr) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attr, typedValue, true);
        return typedValue.data;
    }

    public static int getAttributeResourceId(Context context, int attr) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attr, typedValue, true);
        return typedValue.resourceId;
    }

    public static String readRawResource(Context context, @RawRes int res) {
        return readStream(context.getResources().openRawResource(res));
    }

}
