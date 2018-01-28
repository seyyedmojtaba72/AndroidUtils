package com.github.seyyedmojtaba72.android_utils.widget.materialshowcaseview.shape;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.telecooleh.artistick.android.util.widget.materialshowcaseview.shape.*;
import com.telecooleh.artistick.android.util.widget.materialshowcaseview.target.Target;

/**
 * A Shape implementation that draws nothing.
 */
public class NoShape implements com.telecooleh.artistick.android.util.widget.materialshowcaseview.shape.Shape {

    @Override
    public void updateTarget(Target target) {
        // do nothing
    }

    @Override
    public void draw(Canvas canvas, Paint paint, int x, int y, int padding) {
        // do nothing
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }
}
