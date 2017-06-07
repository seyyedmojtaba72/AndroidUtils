package com.github.seyyedmojtaba72.android_utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by SeyyedMojtaba on 5/2/2016.
 */
public class FontUtils {

    public static void setTextViewCustomFont(Context context, String fontAddress, TextView view) {
        Typeface customFont = Typeface.createFromAsset(context.getAssets(), fontAddress);
        view.setTypeface(customFont);
    }
}
