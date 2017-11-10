package com.github.seyyedmojtaba72.android_utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

import com.github.seyyedmojtaba72.android_utils.R;


/**
 * Created by SeyyedMojtaba on 8/17/2016.
 */

public class RoundedCornerLayout extends RelativeLayout {
    Path mPath = new Path();
    float mCornerRadius = 50;
    private String TAG = RoundedCornerLayout.class.getSimpleName();

    public RoundedCornerLayout(Context context) {
        super(context);
        loadAttributes(context, null);
    }

    public RoundedCornerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        loadAttributes(context, attrs);
    }

    public RoundedCornerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        loadAttributes(context, attrs);
    }


    private void loadAttributes(Context context,
                                AttributeSet paramAttributeSet) {
        TypedArray localTypedArray = context.obtainStyledAttributes(
                paramAttributeSet, R.styleable.Widgets);
        mCornerRadius = localTypedArray.getDimension(R.styleable.Widgets_sau_radius, 50f);
        localTypedArray.recycle();
        setCornerRadius(mCornerRadius);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.clipPath(mPath);
        super.draw(canvas);
        canvas.restore();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        RectF r = new RectF(0, 0, w, h);
        mPath = new Path();
        mPath.addRoundRect(r, mCornerRadius, mCornerRadius, android.graphics.Path.Direction.CW);
        mPath.close();
    }

    public void setCornerRadius(float radius) {
        Log.e(TAG, "radius: " + radius);
        mCornerRadius = radius;
        invalidate();
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        int save = canvas.save();
        canvas.clipPath(mPath);
        super.dispatchDraw(canvas);
        canvas.restoreToCount(save);
    }
}