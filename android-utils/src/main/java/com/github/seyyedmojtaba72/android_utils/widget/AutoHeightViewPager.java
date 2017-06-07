package com.github.seyyedmojtaba72.android_utils.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by SeyyedMojtaba on 6/3/17.
 */

public class AutoHeightViewPager extends ViewPager {
    private int width = 0, height = 0;

    public AutoHeightViewPager(Context context) {
        super(context);
    }

    public AutoHeightViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void refreshHeight() {
        measure(width, height);
        requestLayout();
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        final View tab = getChildAt(getCurrentItem());
        if (isInEditMode() || tab == null) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }

        tab.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        width = tab.getMeasuredWidth();
        height = tab.getMeasuredHeight();

        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);


        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


}
