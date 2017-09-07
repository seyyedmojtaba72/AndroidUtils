package com.github.seyyedmojtaba72.android_utils.widget;

/**
 * Created by SeyyedMojtaba on 6/24/17.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.github.seyyedmojtaba72.android_utils.R;
import com.github.seyyedmojtaba72.android_utils.widget.util.AutoFitHelper;

import java.util.WeakHashMap;

public class AutoFitLayout extends FrameLayout {
    private static final String TAG = AutoFitLayout.class.getSimpleName();

    private boolean mEnabled;
    private float mMinTextSize;
    private float mPrecision;
    private WeakHashMap<View, AutoFitHelper> mHelpers = new WeakHashMap<View, AutoFitHelper>();

    public AutoFitLayout(Context context) {
        super(context);
        init(context, null, 0);
    }

    public AutoFitLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public AutoFitLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        boolean sizeToFit = true;
        int minTextSize = -1;
        float precision = -1;

        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(
                    attrs,
                    R.styleable.AutoFitTextViewPlus,
                    defStyle,
                    0);
            sizeToFit = ta.getBoolean(R.styleable.AutoFitTextViewPlus_sau_sizeToFit, sizeToFit);
            minTextSize = ta.getDimensionPixelSize(R.styleable.AutoFitTextViewPlus_sau_minTextSize,
                    minTextSize);
            precision = ta.getFloat(R.styleable.AutoFitTextViewPlus_sau_precision, precision);
            ta.recycle();
        }

        mEnabled = sizeToFit;
        mMinTextSize = minTextSize;
        mPrecision = precision;
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        TextView textView = (TextView) child;
        AutoFitHelper helper = AutoFitHelper.create(textView)
                .setEnabled(mEnabled);
        if (mPrecision > 0) {
            helper.setPrecision(mPrecision);
        }
        if (mMinTextSize > 0) {
            helper.setMinTextSize(TypedValue.COMPLEX_UNIT_PX, mMinTextSize);
        }
        mHelpers.put(textView, helper);
    }

    /**
     * Returns the {@link AutoFitHelper} for this child View.
     */
    public AutoFitHelper getAutoFitHelper(TextView textView) {
        return mHelpers.get(textView);
    }

    /**
     * Returns the {@link AutoFitHelper} for this child View.
     */
    public AutoFitHelper getAutoFitHelper(int index) {
        return mHelpers.get(getChildAt(index));
    }
}
