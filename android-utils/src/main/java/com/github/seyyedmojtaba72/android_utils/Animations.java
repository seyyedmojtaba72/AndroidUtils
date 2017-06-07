package com.github.seyyedmojtaba72.android_utils;

import android.content.Context;
import android.view.animation.Animation;

public class Animations {
    private Animation FadeIn;
    private Animation FadeOut;
    private Animation SlideInLeft;
    private Animation SlideInRight;
    private Animation SlideOutLeft;
    private Animation SlideOutRight;
    private Animation ZoomIn;
    private Animation ZoomOut;

    public Animations(Context paramContext) {
        this.FadeIn = android.view.animation.AnimationUtils
                .loadAnimation(paramContext, R.anim.fade_in);
        this.FadeOut = android.view.animation.AnimationUtils.loadAnimation(paramContext,
                R.anim.fade_out);
        this.ZoomIn = android.view.animation.AnimationUtils
                .loadAnimation(paramContext, R.anim.zoom_in);
        this.ZoomOut = android.view.animation.AnimationUtils.loadAnimation(paramContext,
                R.anim.zoom_out);
        this.SlideInRight = android.view.animation.AnimationUtils.loadAnimation(paramContext,
                R.anim.slide_in_right);
        this.SlideOutLeft = android.view.animation.AnimationUtils.loadAnimation(paramContext,
                R.anim.slide_out_left);
        this.SlideInLeft = android.view.animation.AnimationUtils.loadAnimation(paramContext,
                R.anim.slide_in_left);
        this.SlideOutRight = android.view.animation.AnimationUtils.loadAnimation(paramContext,
                R.anim.slide_out_right);
    }

    public Animation FadeIn(long paramLong) {
        this.FadeIn.setDuration(paramLong);
        return this.FadeIn;
    }

    public Animation FadeOut(long paramLong) {
        this.FadeOut.setDuration(paramLong);
        return this.FadeOut;
    }

    public Animation SlideInLeft(long paramLong) {
        this.SlideInLeft.setDuration(paramLong);
        return this.SlideInLeft;
    }

    public Animation SlideInRight(long paramLong) {
        this.SlideInRight.setDuration(paramLong);
        return this.SlideInRight;
    }

    public Animation SlideOutLeft(long paramLong) {
        this.SlideOutLeft.setDuration(paramLong);
        return this.SlideOutLeft;
    }

    public Animation SlideOutRight(long paramLong) {
        this.SlideOutRight.setDuration(paramLong);
        return this.SlideOutRight;
    }

    public Animation ZoomIn(long paramLong) {
        this.ZoomIn.setDuration(paramLong);
        return this.ZoomIn;
    }

    public Animation ZoomOut(long paramLong) {
        this.ZoomOut.setDuration(paramLong);
        return this.ZoomOut;
    }

}