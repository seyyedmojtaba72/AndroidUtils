package com.github.seyyedmojtaba72.android_utils.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

import com.github.seyyedmojtaba72.android_utils.R;


public class EditTextPlus extends EditText {
    private static final String TAG = EditTextPlus.class.getSimpleName();

    public EditTextPlus(Context paramContext) {
        super(paramContext);
    }

    public EditTextPlus(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        loadAttributes(paramContext, paramAttributeSet);
    }

    public EditTextPlus(Context paramContext, AttributeSet paramAttributeSet,
                        int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        loadAttributes(paramContext, paramAttributeSet);
    }

    private void loadAttributes(Context context,
                                AttributeSet paramAttributeSet) {
        TypedArray localTypedArray = context.obtainStyledAttributes(
                paramAttributeSet, R.styleable.Widgets);
        setFont(context, localTypedArray.getString(R.styleable.Widgets_sau_font));
        localTypedArray.recycle();
    }

    public boolean setFont(Context context, String font) {
        if (font == null || font.trim().isEmpty()) {
            return false;
        }
        try {
            Typeface localTypeface = Typeface.createFromAsset(
                    context.getAssets(), font);
            setTypeface(localTypeface);
            return true;
        } catch (Exception localException) {
            Log.e(TAG, "Could not get typeface: " + localException.getMessage());
        }
        return false;
    }
}