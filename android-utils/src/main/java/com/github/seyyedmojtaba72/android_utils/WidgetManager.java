package com.github.seyyedmojtaba72.android_utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.util.LruCache;
import android.widget.TextView;

/**
 * Created by SeyyedMojtaba on 5/2/2016.
 */
public class WidgetManager {
    private static final String TAG = WidgetManager.class.getSimpleName();

    public static void setTextViewFont(Context context, String fontAddress, TextView view) {
        if(fontAddress.isEmpty()){
            return;
        }
        Typeface customFont = Typeface.createFromAsset(context.getAssets(), fontAddress);
        view.setTypeface(customFont);
    }

    public static void setEditTextFont(Context context, String fontAddress, TextView view) {
        if(fontAddress.isEmpty()){
            return;
        }
        Typeface customFont = Typeface.createFromAsset(context.getAssets(), fontAddress);
        view.setTypeface(customFont);
    }

    public static void JustifyTextView(TextView tv) {
        String html = String.valueOf(Html
                .fromHtml("<![CDATA[<body style=\"text-align:justify; \">"
                        + tv.getText()
                        + "</body>]]>"));

        tv.setText(html);
    }

    public static void JustifyTextView(TextView tv, String text) {
        String html = String.valueOf(Html
                .fromHtml("<![CDATA[<body style=\"text-align:justify; \">"
                        + text
                        + "</body>]]>"));

        tv.setText(html);
    }

    public static SpannableString textWithFont(Context context, String text, String font) {
        SpannableString s = new SpannableString(text);

        if (font == null || font.trim().isEmpty()) {
            return s;
        }

        try {
            s.setSpan(new TypefaceSpan(context, font), 0, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        } catch (Exception e){
            e.printStackTrace();
        }

        return s;
    }

    public static void stripUnderlines(TextView textView) {
        Spannable s = (Spannable) textView.getText();
        URLSpan[] spans = s.getSpans(0, s.length(), URLSpan.class);
        for (URLSpan span : spans) {
            int start = s.getSpanStart(span);
            int end = s.getSpanEnd(span);
            s.removeSpan(span);
            span = new URLSpanNoUnderline(span.getURL());
            s.setSpan(span, start, end, 0);
        }
        textView.setText(s);
    }

    @SuppressLint("ParcelCreator")
    public static class URLSpanNoUnderline extends URLSpan {
        public URLSpanNoUnderline(String url) {
            super(url);
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(false);
        }
    }

    private static class TypefaceSpan extends MetricAffectingSpan {
        /**
         * An <code>LruCache</code> for previously loaded typefaces.
         */
        private LruCache<String, Typeface> sTypefaceCache =
                new LruCache<String, Typeface>(12);

        private Typeface mTypeface;

        /**
         * Load the {@link Typeface} and apply to a {@link Spannable}.
         */
        public TypefaceSpan(Context context, String typefaceName) {
            mTypeface = sTypefaceCache.get(typefaceName);

            if (mTypeface == null) {
                mTypeface = Typeface.createFromAsset(context.getApplicationContext()
                        .getAssets(), String.format("%s", typefaceName));

                // Cache the loaded Typeface
                sTypefaceCache.put(typefaceName, mTypeface);
            }
        }

        @Override
        public void updateMeasureState(TextPaint p) {
            p.setTypeface(mTypeface);

            // Note: This flag is required for proper typeface rendering
            p.setFlags(p.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }

        @Override
        public void updateDrawState(TextPaint tp) {
            tp.setTypeface(mTypeface);

            // Note: This flag is required for proper typeface rendering
            tp.setFlags(tp.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
    }
}
