package com.github.seyyedmojtaba72.android_utils.widget;

/**
 * Created by SeyyedMojtaba on 3/13/2016.
 */

import android.content.Context;
import android.widget.ImageView;

public class SquareImageView extends ImageView {
    private static final String TAG = SquareImageView.class.getSimpleName();

    public SquareImageView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        setMeasuredDimension(width, width);
    }

}