package com.github.seyyedmojtaba72.android_utils.widget;

/**
 * Created by SeyyedMojtaba on 6/24/17.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;

import com.github.seyyedmojtaba72.android_utils.R;
import com.github.seyyedmojtaba72.android_utils.widget.util.AutoFitHelper;

public class AutoFitTextViewPlus extends TextView implements AutoFitHelper.OnTextSizeChangeListener {
    private static final String TAG = AutoFitTextViewPlus.class.getSimpleName();

    private AutoFitHelper mHelper;

    public AutoFitTextViewPlus(Context context) {
        super(context);
        init(context, null, 0);
    }

    public AutoFitTextViewPlus(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public AutoFitTextViewPlus(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        mHelper = AutoFitHelper.create(this, attrs, defStyle)
                .addOnTextSizeChangeListener(this);
        TypedArray localTypedArray = context.obtainStyledAttributes(attrs, R.styleable.Widgets);
        setFont(context, localTypedArray.getString(R.styleable.Widgets_sau_font));
        localTypedArray.recycle();
    }

    // Getters and Setters

    public boolean setFont(Context context, String font) {
        try {
            Typeface localTypeface = Typeface.createFromAsset(
                    context.getAssets(), font);
            setTypeface(localTypeface);
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Could not get typeface: " + e.getMessage());
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTextSize(int unit, float size) {
        super.setTextSize(unit, size);
        if (mHelper != null) {
            mHelper.setTextSize(unit, size);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLines(int lines) {
        super.setLines(lines);
        if (mHelper != null) {
            mHelper.setMaxLines(lines);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMaxLines(int maxLines) {
        super.setMaxLines(maxLines);
        if (mHelper != null) {
            mHelper.setMaxLines(maxLines);
        }
    }

    /**
     * Returns the {@link AutoFitHelper} for this View.
     */
    public AutoFitHelper getAutoFitHelper() {
        return mHelper;
    }

    /**
     * Returns whether or not the text will be automatically re-sized to fit its constraints.
     */
    public boolean isSizeToFit() {
        return mHelper.isEnabled();
    }

    /**
     * If true, the text will automatically be re-sized to fit its constraints; if false, it will
     * act like a normal TextView.
     *
     * @param sizeToFit
     */
    public void setSizeToFit(boolean sizeToFit) {
        mHelper.setEnabled(sizeToFit);
    }

    /**
     * Sets the property of this field (sizeToFit), to automatically resize the text to fit its
     * constraints.
     */
    public void setSizeToFit() {
        setSizeToFit(true);
    }

    /**
     * Returns the maximum size (in pixels) of the text in this View.
     */
    public float getMaxTextSize() {
        return mHelper.getMaxTextSize();
    }

    /**
     * Set the maximum text size to the given value, interpreted as "scaled pixel" units. This size
     * is adjusted based on the current density and user font size preference.
     *
     * @param size The scaled pixel size.
     */
    public void setMaxTextSize(float size) {
        mHelper.setMaxTextSize(size);
    }

    /**
     * Set the maximum text size to a given unit and value. See TypedValue for the possible
     * dimension units.
     *
     * @param unit The desired dimension unit.
     * @param size The desired size in the given units.
     */
    public void setMaxTextSize(int unit, float size) {
        mHelper.setMaxTextSize(unit, size);
    }

    /**
     * Returns the minimum size (in pixels) of the text in this View.
     */
    public float getMinTextSize() {
        return mHelper.getMinTextSize();
    }

    /**
     * Set the minimum text size to the given value, interpreted as "scaled pixel" units. This size
     * is adjusted based on the current density and user font size preference.
     *
     * @param minSize The scaled pixel size.
     */
    public void setMinTextSize(int minSize) {
        mHelper.setMinTextSize(TypedValue.COMPLEX_UNIT_SP, minSize);
    }

    /**
     * Set the minimum text size to a given unit and value. See TypedValue for the possible
     * dimension units.
     *
     * @param unit    The desired dimension unit.
     * @param minSize The desired size in the given units.
     */
    public void setMinTextSize(int unit, float minSize) {
        mHelper.setMinTextSize(unit, minSize);
    }

    /**
     * Returns the amount of precision used to calculate the correct text size to fit within its
     * bounds.
     */
    public float getPrecision() {
        return mHelper.getPrecision();
    }

    /**
     * Set the amount of precision used to calculate the correct text size to fit within its
     * bounds. Lower precision is more precise and takes more time.
     *
     * @param precision The amount of precision.
     */
    public void setPrecision(float precision) {
        mHelper.setPrecision(precision);
    }

    @Override
    public void onTextSizeChange(float textSize, float oldTextSize) {
        // do nothing
    }
}