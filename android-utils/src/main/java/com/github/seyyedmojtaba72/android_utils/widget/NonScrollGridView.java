package com.github.seyyedmojtaba72.android_utils.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.GridView;

public class NonScrollGridView extends GridView {
    private static final String TAG = NonScrollGridView.class.getSimpleName();

    public NonScrollGridView(Context context) {
        super(context);
        scrollTo(0, 0);
    }

    public NonScrollGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        scrollTo(0, 0);
    }

    public NonScrollGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        scrollTo(0, 0);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMeasureSpec_custom = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec_custom);
        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = getMeasuredHeight();
    }

}