package com.github.seyyedmojtaba72.android_utils;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.ViewAnimationUtils;

/**
 * Created by SeyyedMojtaba on 10/4/2016.
 */

public class Transitions {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void CircularReveal(View targetView, int centerX, int centerY, float radius) {
        Animator mCircularReveal = ViewAnimationUtils.createCircularReveal(
                targetView, centerX, centerY, 0, radius);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void loadTransition(Activity activity, int transition) {
        Transition explode = TransitionInflater.from(activity).inflateTransition(transition);
        activity.getWindow().setEnterTransition(explode);
    }
}
