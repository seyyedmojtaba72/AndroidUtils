package com.github.seyyedmojtaba72.android_utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.seyyedmojtaba72.android_utils.R;

import java.lang.reflect.Field;

/**
 * Created by SeyyedMojtaba on 3/21/2017.
 */

public class TextInputLayoutPlus extends TextInputLayout {
    private static final String TAG = TextInputLayoutPlus.class.getSimpleName();

    private Context context;
    private String font = "";
    private int gravity = Gravity.END;

    public TextInputLayoutPlus(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        loadAttributes(context, attrs);
    }

    private void loadAttributes(Context context,
                                AttributeSet paramAttributeSet) {
        TypedArray localTypedArray = context.obtainStyledAttributes(
                paramAttributeSet, R.styleable.Widgets);
        setFont(context, localTypedArray.getString(R.styleable.Widgets_sau_font));
        setErrorGravity(context, localTypedArray.getInt(R.styleable.Widgets_sau_errorGravity, Gravity.END));
        localTypedArray.recycle();
    }

    @Override
    public void setError(@Nullable CharSequence error) {
        super.setError(error);
        setFont(context, font);
        setErrorGravity(context, gravity);
    }


    public boolean setFont(Context context, String font) {
        this.font = font;
        if (font == null || font.trim().isEmpty()) {
            return false;
        }

        Typeface typeface;
        try {
            typeface = Typeface.createFromAsset(context.getAssets(), font);
        } catch (Exception localException) {
            Log.e(TAG, "Could not get typeface: " + localException.getMessage());
            return false;
        }

        try {
            Field errorViewField = TextInputLayout.class.getDeclaredField("mErrorView");
            errorViewField.setAccessible(true);
            TextView errorView = (TextView) errorViewField.get(this);
            if (errorView != null) {
                errorView.setTypeface(typeface);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        setTypeface(typeface);
        return true;
    }

    public void setErrorGravity(Context context, int gravity) {
        this.gravity = gravity;
        try {
            Field errorViewField = TextInputLayout.class.getDeclaredField("mErrorView");
            errorViewField.setAccessible(true);
            TextView errorView = (TextView) errorViewField.get(this);
            if (errorView != null) {
                errorView.setGravity(gravity);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.gravity = gravity;
                errorView.setLayoutParams(params);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
