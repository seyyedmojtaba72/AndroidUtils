package com.github.seyyedmojtaba72.android_utils.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.github.seyyedmojtaba72.android_utils.R;


public class WrappedWebView extends WebView {
    private static final String TAG = WrappedWebView.class.getSimpleName();

    private int backgroundColor = 0;
    private String html = "";

    public WrappedWebView(Context context, AttributeSet paramAttributeSet) {
        super(context, paramAttributeSet);
        setWebChromeClient(new WebChromeClient() {
        });
        loadAttributes(context, paramAttributeSet);
    }

    private void loadAttributes(Context context, AttributeSet paramAttributeSet) {
        TypedArray localTypedArray = context.obtainStyledAttributes(
                paramAttributeSet, R.styleable.Widgets);
        setHTML(localTypedArray
                .getString(R.styleable.Widgets_sau_html));
        localTypedArray.recycle();
    }

    private void reloadData() {
        if (!isInEditMode()) {
            loadDataWithBaseURL("file:///android_asset/", html, "text/html", "utf-8", "");
            super.setBackgroundColor(this.backgroundColor);
            if (Build.VERSION.SDK_INT >= 11) {
                setLayerType(1, null);
            }
        }
    }

    public void setHTML(String html) {
        this.html = html;
        reloadData();
    }
}
