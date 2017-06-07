package com.github.seyyedmojtaba72.android_utils.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.github.seyyedmojtaba72.android_utils.Functions;
import com.github.seyyedmojtaba72.android_utils.R;


public class JustifiedTextViewPlus extends WebView {
    final String TAG = "JustifiedTextViewPlus";


    private String core = "<html><body style='text-align:justify;color:rgba(%s);font-size:%fpx;margin:0; padding:0;'>%s</body></html>";
    private String textColor = "0,0,0,255";
    private String text = "";
    private float textSize = 12f;
    private int backgroundColor = Color.TRANSPARENT;

    public JustifiedTextViewPlus(Context context, AttributeSet paramAttributeSet) {
        super(context, paramAttributeSet);
        setWebChromeClient(new WebChromeClient() {
        });
        if (!getRootView().isInEditMode()) {
            loadAttributes(context, paramAttributeSet);
        }
    }

    private void loadAttributes(Context context, AttributeSet paramAttributeSet) {
        TypedArray localTypedArray = context.obtainStyledAttributes(
                paramAttributeSet, R.styleable.Custom_Widget_Attributes);
        textSize = Functions.getDpFromPx(context, localTypedArray.getDimensionPixelSize(R.styleable.Custom_Widget_Attributes_textSize, 12));
        text = localTypedArray.getString(R.styleable.Custom_Widget_Attributes_text);
        int hex = localTypedArray.getColor(R.styleable.Custom_Widget_Attributes_textColor, Color.BLACK);
        String h = Integer.toHexString(hex);
        int a = Integer.parseInt(h.substring(0, 2), 16);
        int r = Integer.parseInt(h.substring(2, 4), 16);
        int g = Integer.parseInt(h.substring(4, 6), 16);
        int b = Integer.parseInt(h.substring(6, 8), 16);
        textColor = String.format("%d,%d,%d,%d", r, g, b, a);
        reloadData();
        localTypedArray.recycle();
    }

    public void setText(String s) {
        this.text = s;
        // this.setPadding(10, 10, 10, 10);
        reloadData();
    }

    @SuppressLint("NewApi")
    private void reloadData() {

        // loadData(...) has a bug showing utf-8 correctly. That's why we need
        // to set it first.
        this.getSettings().setDefaultTextEncodingName("utf-8");

        this.loadData(String.format(core, textColor, textSize, text),
                "text/html", "utf-8");


        // set WebView's background color *after* data was loaded.
        super.setBackgroundColor(backgroundColor);
        // Hardware rendering breaks background color to work as expected.
        // Need to use software renderer in that case.
        if (android.os.Build.VERSION.SDK_INT >= 11)
            this.setLayerType(WebView.LAYER_TYPE_HARDWARE, null);
    }

    public void setTextColor(int hex) {
        String h = Integer.toHexString(hex);
        int a = Integer.parseInt(h.substring(0, 2), 16);
        int r = Integer.parseInt(h.substring(2, 4), 16);
        int g = Integer.parseInt(h.substring(4, 6), 16);
        int b = Integer.parseInt(h.substring(6, 8), 16);
        textColor = String.format("%d,%d,%d,%d", r, g, b, a);
        reloadData();
    }

    public void setBackgroundColor(int hex) {
        backgroundColor = hex;
        reloadData();
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
        reloadData();
    }

}
