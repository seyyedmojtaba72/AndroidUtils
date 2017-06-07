package com.github.seyyedmojtaba72.android_utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

import com.github.seyyedmojtaba72.android_utils.R;


public class EditTextPlus extends EditText {
    final String TAG = "EditTextPlus";

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
                paramAttributeSet, R.styleable.Custom_Widget_Attributes);
        setFont(context, localTypedArray.getString(R.styleable.Custom_Widget_Attributes_font));
        localTypedArray.recycle();
    }

    public boolean setFont(Context context, String font) {
        try {
            Typeface localTypeface = Typeface.createFromAsset(
                    context.getAssets(), font);
            setTypeface(localTypeface);
            return true;
        } catch (Exception localException) {
            Log.e(TAG,
                    "Could not get typeface: " + localException.getMessage());
        }
        return false;
    }
}