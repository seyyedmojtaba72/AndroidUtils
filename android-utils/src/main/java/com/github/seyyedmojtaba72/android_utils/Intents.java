package com.github.seyyedmojtaba72.android_utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.telephony.SmsManager;
import android.util.Log;
import android.webkit.MimeTypeMap;

import java.io.File;
import java.util.ArrayList;

import static com.github.seyyedmojtaba72.android_utils.FileUtils.getFileExtention;
import static com.github.seyyedmojtaba72.android_utils.FileUtils.getUriFromFile;

/**
 * Created by SeyyedMojtaba on 6/24/17.
 */

public class Intents {
    private static final String TAG = Intents.class.getSimpleName();

    public static void browseWebViaIntent(Context context, String intent_title, String urlAddress) {
        if ((!urlAddress.startsWith("http://")) && (!urlAddress.startsWith("https://"))) {
            urlAddress = "http://" + urlAddress;
        }
        Intent localIntent = new Intent("android.intent.action.VIEW");
        localIntent.setData(Uri.parse(urlAddress));
        localIntent.addCategory("android.intent.category.BROWSABLE");
        try {
            localIntent = Intent.createChooser(localIntent, intent_title);
            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(localIntent);
            return;
        } catch (ActivityNotFoundException localActivityNotFoundException) {
            localActivityNotFoundException.printStackTrace();
        }
    }

    public static void openUriViaIntent(Context context, String intent_title, Uri uri) {

        Intent newIntent = new Intent(Intent.ACTION_VIEW);
        newIntent.setData(uri);
        newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            Intent intent = Intent.createChooser(newIntent, intent_title);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void openFileViaIntent(Context context, String intent_title, String filePath) {
        Uri uri = getUriFromFile(context, filePath);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        MimeTypeMap myMime = MimeTypeMap.getSingleton();
        String mimeType = myMime.getMimeTypeFromExtension(getFileExtention(filePath));
        intent.setDataAndType(uri, mimeType);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        try {
            if (intent_title.isEmpty()) {
                context.startActivity(intent);
            }

            intent = Intent.createChooser(intent, intent_title);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void sendSMS(Context context, String phoneNumber, String message) {
        try {
            Log.d(TAG, "[sendSMS] sending sms '" + message + "' to " + phoneNumber);

            SmsManager sm = SmsManager.getDefault();
            ArrayList<String> parts = sm.divideMessage(message);

            sm.sendMultipartTextMessage(phoneNumber, null, parts, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void sendSMSViaIntent(Context context, String intent_title, String phoneNumber, String message) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("smsto:" + phoneNumber));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("address", phoneNumber);
        intent.putExtra("sms_body", message);
        try {
            Intent intent2 = Intent.createChooser(intent, intent_title);
            intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent2);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void sendEmailViaIntent(Context context, String intent_title, String[] emails, String subject, String body) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("message/rfc822");
        if (emails.length > 0) intent.putExtra("android.intent.extra.EMAIL", emails);
        if (!subject.isEmpty()) intent.putExtra("android.intent.extra.SUBJECT", subject);
        if (!body.isEmpty()) intent.putExtra("android.intent.extra.TEXT", body);
        try {
            intent = Intent.createChooser(intent, intent_title);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void Call(Context context, String number) {
        if ((number == null) || (number.isEmpty())) {
            return;
        }
        try {
            Intent localIntent = new Intent("android.intent.action.CALL");
            localIntent.setData(Uri.parse("tel:" + Uri.encode(number)));
            localIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(localIntent);
            Log.d(TAG, "[Call] " + Uri.encode(number));
            return;
        } catch (ActivityNotFoundException localActivityNotFoundException) {
            localActivityNotFoundException.printStackTrace();
        }
    }

    public static void callViaIntent(Context context, String intent_title, String number) {
        Intent intent = new Intent("android.intent.action.DIAL");
        intent.setData(Uri.parse("tel:" + number));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            Intent intent2 = Intent.createChooser(intent, intent_title);
            intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent2);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void shareApplicationViaIntent(Context context, String intent_title) {
        try {
            File localFile = new File(context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).publicSourceDir);
            Intent localIntent = new Intent();
            localIntent.setAction("android.intent.action.SEND");

            localIntent.setType("application/vnd.android.package-archive");
            localIntent.putExtra("android.intent.extra.STREAM", Uri.fromFile(localFile));
            localIntent = Intent.createChooser(localIntent, intent_title);
            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(localIntent);
            return;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }

    public static void browseInstagramProfileViaIntent(Activity activity, String intent_title, PackageManager pm, String url) {
        final Intent intent = new Intent(Intent.ACTION_VIEW);
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        final String username = url.substring(url.lastIndexOf("/") + 1);
        try {
            if (pm.getPackageInfo("com.instagram.android", 0) != null) {
                // http://stackoverflow.com/questions/21505941/intent-to-open-instagram-user-profile-on-android
                intent.setData(Uri.parse("http://instagram.com/_u/" + username));
                intent.setPackage("com.instagram.android");
                intent.setData(Uri.parse(url));
            } else {
                browseWebViaIntent(activity, intent_title, "http://instagram.com/_u/" + username);
            }
        } catch (PackageManager.NameNotFoundException ignored) {
            browseWebViaIntent(activity, intent_title, "https://www.instagram.com/" + username);
            ignored.printStackTrace();
        }

        try {
            activity.startActivity(intent);
        } catch (Exception e) {
            browseWebViaIntent(activity, intent_title, "http://instagram.com/_u/" + username);
        }
    }

    public static void shareVideoViaIntent(Context context, String intent_title, String filePath, String subject, String message) {

        Intent intent = new Intent(android.content.Intent.ACTION_SEND);


        MimeTypeMap myMime = MimeTypeMap.getSingleton();
        String mimeType = myMime.getMimeTypeFromExtension(getFileExtention(filePath));
        intent.setDataAndType(Uri.fromFile(new File(filePath)), mimeType);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(filePath)));


        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, intent_title));
    }

    public static void shareTextViaIntent(Context context, String intent_title, String subject, String message) {
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);

        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, intent_title));
    }
}
