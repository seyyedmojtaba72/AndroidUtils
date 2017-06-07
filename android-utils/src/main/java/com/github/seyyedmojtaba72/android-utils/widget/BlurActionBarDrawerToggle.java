package com.developars.utils.widget;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.developars.utils.android.R;


/**
 * Created by SeyyedMojtaba on 10/9/2016.
 */

public class BlurActionBarDrawerToggle extends ActionBarDrawerToggle {

    private static final int DOWNSAMPLING = 8;

    private final DrawerLayout drawerLayout;
    private final BlurAndDimView blurrer;
    private final ColorDrawable imageBackgroundDrawable;
    private Bitmap drawerSnapshot;

    public BlurActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, int openDrawerContentDescRes, int closeDrawerContentDescRes, BlurAndDimView blurrer) {
        super(activity, drawerLayout, toolbar, openDrawerContentDescRes, closeDrawerContentDescRes);

        //this should be roughly the same color as your window background
        imageBackgroundDrawable = new ColorDrawable(activity.getResources().getColor(R.color.dark_blue));
        this.drawerLayout = drawerLayout;
        this.blurrer = blurrer;
    }

    @Override
    public void onDrawerSlide(final View drawerView, final float slideOffset) {
        super.onDrawerSlide(drawerView, slideOffset);
        if (slideOffset > 0.0f) {
            setBlurAlpha(slideOffset);
        } else {
            clearBlurImage();
        }
    }

    @Override
    public void onDrawerClosed(View view) {
        clearBlurImage();
    }

    private void setBlurAlpha(float slideOffset) {
        if (!blurrer.hasImage()) {
            setBlurImage();
        }
        blurrer.handleScroll(slideOffset);
    }

    public void setBlurImage() {
        blurrer.setVisibility(View.VISIBLE);
        drawerSnapshot = drawViewToBitmap(drawerSnapshot, drawerLayout, DOWNSAMPLING, imageBackgroundDrawable);
        blurrer.setImage(drawerSnapshot, DOWNSAMPLING);
    }

    public void clearBlurImage() {
        blurrer.clearImage();
        blurrer.setVisibility(View.INVISIBLE);
    }

    private Bitmap drawViewToBitmap(Bitmap dest, View view, int downSampling, Drawable background) {
        float scale = 1f / downSampling;
        int viewWidth = view.getWidth();
        int viewHeight = view.getHeight();
        int bmpWidth = (int) (viewWidth * scale);
        int bmpHeight = (int) (viewHeight * scale);
        if (dest == null || dest.getWidth() != bmpWidth || dest.getHeight() != bmpHeight) {
            dest = Bitmap.createBitmap(bmpWidth, bmpHeight, Bitmap.Config.ARGB_8888);
        }
        Canvas c = new Canvas(dest);
        background.setBounds(new Rect(0, 0, viewWidth, viewHeight));
        background.draw(c);
        if (downSampling > 1) {
            c.scale(scale, scale);
        }

        view.draw(c);
        view.layout(0, 0, viewWidth, viewHeight);
        return dest;
    }
}
