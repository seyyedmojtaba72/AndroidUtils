package com.github.seyyedmojtaba72.android_utils.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.seyyedmojtaba72.android_utils.WidgetManager;

import java.lang.reflect.Field;


public class TextInputEditTextPlus extends TextInputEditText {
    private static final String TAG = TextInputLayoutPlus.class.getSimpleName();

    private Context context;
    private String font = "";
    private int gravity = Gravity.END;

    public TextInputEditTextPlus(Context context, AttributeSet attrs) {
        super(context, attrs);
        loadAttributes(context, attrs);
    }

    public TextInputEditTextPlus(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        loadAttributes(context, attrs);
    }

    private void loadAttributes(Context context,
                                AttributeSet paramAttributeSet) {
        TypedArray localTypedArray = context.obtainStyledAttributes(
                paramAttributeSet, com.github.seyyedmojtaba72.android_utils.R.styleable.Widgets);
        setFont(context, localTypedArray.getString(com.github.seyyedmojtaba72.android_utils.R.styleable.Widgets_sau_font));
        setErrorGravity(context, localTypedArray.getInt(com.github.seyyedmojtaba72.android_utils.R.styleable.Widgets_sau_errorGravity, Gravity.END));
        localTypedArray.recycle();
    }

    public boolean setFont(Context context, String font) {
        if (font == null || font.trim().isEmpty()) {
            return false;
        }

        this.font = font;

        Typeface typeface;
        try {
            typeface = Typeface.createFromAsset(context.getAssets(), font);
        } catch (Exception localException) {
            Log.e(TAG, "Could not get typeface: " + localException.getMessage());
            return false;
        }

        try {
            Field errorViewField = TextInputEditText.class.getDeclaredField("mErrorView");
            errorViewField.setAccessible(true);
            TextView errorView = (TextView) errorViewField.get(this);
            if (errorView != null) {
                errorView.setTypeface(typeface);
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }


        setTypeface(typeface);
        return true;
    }

    public void setErrorGravity(Context context, int gravity) {
        this.gravity = gravity;
        try {
            Field errorViewField = TextInputEditText.class.getDeclaredField("mErrorView");
            errorViewField.setAccessible(true);
            TextView errorView = (TextView) errorViewField.get(this);
            if (errorView != null) {
                errorView.setGravity(gravity);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.gravity = gravity;
                errorView.setLayoutParams(params);
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    @Override
    public void setError(@Nullable CharSequence error) {
        setFont(context, font);
        setErrorGravity(context, gravity);

        error = WidgetManager.textWithFont(context, error.toString(), font);
        super.setError(error);
    }
}
