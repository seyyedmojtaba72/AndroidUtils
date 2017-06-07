package com.github.seyyedmojtaba72.android_utils;

// Build 10

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.ExifInterface;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.RawRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.telephony.SmsManager;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import android.text.style.URLSpan;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.LruCache;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.seyyedmojtaba72.android_utils.widget.TextViewPlus;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Functions {
    private static String TAG = "Functions";

    public static boolean isFirstTime(Context context, String element) {
        Boolean bool = true;
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean firstTime = mPreferences.getBoolean(element, true);
        if (firstTime) {
            SharedPreferences.Editor editor = mPreferences.edit();
            editor.putBoolean(element, false);
            editor.commit();
            bool = Boolean.valueOf(true);
        } else {
            bool = Boolean.valueOf(false);
        }
        return bool.booleanValue();
    }

    public static boolean getBoolean(Context context, String element, boolean default_value) {
        boolean bool = default_value;
        try {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

            bool = sharedPreferences.getBoolean(element, default_value);
            return bool;
        } catch (Exception localException1) {
        }
        return bool;
    }

    public static String getString(Context context, String arg0, String default_value) {
        String result = default_value;
        try {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

            result = sharedPreferences.getString(arg0, default_value);
            return result;
        } catch (Exception localException1) {
        }
        return result;
    }

    public static int getInt(Context context, String arg0, int default_value) {
        int result = default_value;
        try {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

            result = sharedPreferences.getInt(arg0, default_value);
            return result;
        } catch (Exception localException1) {
        }
        return result;
    }

    public static float getFloat(Context context, String arg0, float default_value) {
        float result = default_value;
        try {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

            result = sharedPreferences.getFloat(arg0, default_value);
            return result;
        } catch (Exception localException1) {
        }
        return result;
    }

    public static long getLong(Context context, String arg0, long default_value) {
        long result = default_value;
        try {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

            result = sharedPreferences.getLong(arg0, default_value);
            return result;
        } catch (Exception localException1) {
        }
        return result;
    }

    public static void putBoolean(Context context, String arg0, boolean arg1) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(arg0, arg1);
        editor.commit();
    }

    public static void putString(Context context, String arg0, String arg1) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(arg0, arg1);
        editor.commit();
    }

    public static void putInt(Context context, String arg0, int arg1) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(arg0, arg1);
        editor.commit();
    }

    public static void putFloat(Context context, String arg0, float arg1) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(arg0, arg1);
        editor.commit();
    }

    public static void putLong(Context context, String arg0, long arg1) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(arg0, arg1);
        editor.commit();
    }

    public static String toPersianDigits(String string) {
        return string.replaceAll("0", "۰").replaceAll("1", "۱").replaceAll("2", "۲").replaceAll("3", "۳").replaceAll("4", "۴").replaceAll("5", "۵").replaceAll("6", "۶").replaceAll("7", "۷").replaceAll("8", "۸").replaceAll("9", "۹");
    }

    public static String toEnglishDigits(String string) {
        return string.replaceAll("۰", "0").replaceAll("۱", "1").replaceAll("۲", "2").replaceAll("۳", "3").replaceAll("۴", "4").replaceAll("۵", "5").replaceAll("۶", "6").replaceAll("۷", "7").replaceAll("۸", "8").replaceAll("۹", "9");
    }

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
            return;
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(context, context.getResources().getString(R.string.no_email_client), Toast.LENGTH_SHORT).show();
        }
    }

    public static void setTitle(Activity activity, CharSequence title) {
        activity.getActionBar().setTitle(title);
    }

    public static String fromUTF(String string) {
        try {
            String str = new String(string.getBytes("UTF-8"), "ISO-8859-1");
            return str;
        } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
            localUnsupportedEncodingException.printStackTrace();
        }
        return "";
    }

    public static String toUTF(String string) {
        try {
            String str = new String(string.getBytes("ISO-8859-1"), "UTF-8");
            return str;
        } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
            localUnsupportedEncodingException.printStackTrace();
        }
        return "";
    }

    public static ArrayList<HashMap<String, String>> sortList(ArrayList<HashMap<String, String>> list) {
        Collections.sort(list, new Comparator<HashMap<String, String>>() {
            public int compare(HashMap<String, String> arg0, HashMap<String, String> arg1) {
                return (arg0.get("title")).compareTo(arg1.get("title"));
            }
        });

        return list;
    }

    public static int getURLSize(String urlAddress, String apiAddress) {
        try {
            if (apiAddress != null) {
                int i = Integer.parseInt(Fetcher.FetchURL(apiAddress));
                return i;
            } else {
                URL url = new URL(urlAddress);
                URLConnection urlConnection = url.openConnection();
                urlConnection.connect();
                int file_size = urlConnection.getContentLength();
                return file_size;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static long getURLLastModified(String urlAddress) {

        HttpURLConnection localHttpURLConnection = null;
        HttpURLConnection.setFollowRedirects(false);
        try {
            localHttpURLConnection = (HttpURLConnection) new URL(urlAddress).openConnection();
            return Long.valueOf(localHttpURLConnection.getLastModified());
        } catch (MalformedURLException localMalformedURLException) {
            localMalformedURLException.printStackTrace();
        } catch (IOException localIOException) {
            localIOException.printStackTrace();
        }

        return 0L;
    }

    public static long getFileSize(String address) {
        File file = new File(address);
        long file_size = 0x0;
        if (file.exists()) {
            file_size = file.length();
        }
        return file_size;
    }


    public static Date getNowDate(TimeZone timezone) {
        Calendar cal = Calendar.getInstance(timezone);
        Date currentLocalTime = cal.getTime();

        return currentLocalTime;
    }

    public static String getNowDateTime(TimeZone timezone, String format) {
        Calendar cal = Calendar.getInstance(timezone);
        Date currentLocalTime = cal.getTime();
        try {
            DateFormat date = new SimpleDateFormat(format);
            date.setTimeZone(timezone);

            String localTime = date.format(currentLocalTime);
            return localTime;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Long getNowTimestamp(TimeZone timezone) {
        Calendar cal = Calendar.getInstance(timezone);
        Date currentLocalTime = cal.getTime();
        return currentLocalTime.getTime();
    }

    public static String getDateTimeFromTimestamp(TimeZone timezone, long timestamp, String format) {
        try {
            Calendar calendar = Calendar.getInstance(timezone);
            timestamp = Long.parseLong(normalLength(String.valueOf(timestamp), 13, "0", false));
            calendar.setTimeInMillis(timestamp);
            Date currentLocalTime = calendar.getTime();
            DateFormat date = new SimpleDateFormat(format);
            date.setTimeZone(timezone);

            String localTime = date.format(currentLocalTime);
            return localTime;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getDayOfWeek(String date) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(date));
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek;
    }

    public static String getDayOfWeekPersianTitle(String date) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(date));
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        String day_of_week = "";
        switch (dayOfWeek) {
            case 1: {
                return "یکشنبه";
            }
            case 2: {
                return "دوشنبه";
            }
            case 3: {
                return "سه شنبه";
            }
            case 4: {
                return "چهارشنبه";
            }
            case 5: {
                return "پنج شنبه";
            }
            case 6: {
                return "جمعه";
            }
            case 7: {
                return "شنبه";
            }
        }
        return day_of_week;
    }

    @SuppressLint("SimpleDateFormat")
    public static String toShamsi(String date) {
        int Y = Integer.parseInt(date.split("/")[0]);
        int M = Integer.parseInt(date.split("/")[1]);
        int D = Integer.parseInt(date.split("/")[2]);
        if (Y == 0) {
            Y = 2000;
        }
        if (Y < 100) {
            Y = Y + 1900;
        }
        if (Y == 0x7d0) {
            if (M > 0x2) {
                SimpleDateFormat temp = new SimpleDateFormat("yyyyMMdd");
                String curentDateandTime = temp.format(new Date());
                String year = curentDateandTime.substring(0x0, 4);
                String month = curentDateandTime.substring(0x4, 6);
                String day = curentDateandTime.substring(0x6, 8);
                Y = Integer.valueOf(year).intValue();
                M = Integer.valueOf(month).intValue();
                D = Integer.valueOf(day).intValue();
            }
        }

        if ((M < 3) || (M == 3) && (D < 21)) {
            Y = Y - 0x26e;
        } else {
            Y = Y - 0x26d;
        }
        switch (M) {
            case 1: {
                if (D < 0x15) {
                    M = 0xa;
                    D = D + 0xa;
                    break;
                } else {
                    M = 0xb;
                    D = D - 0x14;
                    break;
                }
            }
            case 2: {
                if (D < 0x14) {
                    M = 0xb;
                    D = D + 0xb;
                    break;
                } else {
                    M = 0xc;
                    D = D - 0x13;
                    break;
                }
            }
            case 3: {
                if (D < 0x15) {
                    M = 0xc;
                    D = D + 0x9;
                    break;
                } else {
                    M = 0x1;
                    D = D - 0x14;
                    break;
                }
            }
            case 4: {
                if (D < 0x15) {
                    M = 0x1;
                    D = D + 0xb;
                    break;
                } else {
                    M = 0x2;
                    D = D - 0x14;
                    break;
                }
            }
            case 5: {
                if (D < 0x16) {
                    M = M - 0x3;
                    D = D + 0xa;
                    break;
                } else {
                    M = M - 0x2;
                    D = D - 0x15;
                    break;
                }
            }
            case 6: {
                if (D < 0x16) {
                    M = M - 0x3;
                    D = D + 0xa;
                    break;
                } else {
                    M = M - 0x2;
                    D = D - 0x15;
                    break;
                }
            }
            case 7: {
                if (D < 0x17) {
                    M = M - 0x3;
                    D = D + 0x9;
                    break;
                } else {
                    M = M - 0x2;
                    D = D - 0x16;
                    break;
                }
            }
            case 8: {
                if (D < 0x17) {
                    M = M - 0x3;
                    D = D + 0x9;
                    break;
                } else {
                    M = M - 0x2;
                    D = D - 0x16;
                    break;
                }
            }
            case 9: {
                if (D < 0x17) {
                    M = M - 0x3;
                    D = D + 0x9;
                    break;
                } else {
                    M = M - 0x2;
                    D = D - 0x16;
                    break;
                }
            }
            case 10: {
                if (D < 0x17) {
                    M = 0x7;
                    D = D + 0x8;
                    break;
                } else {
                    M = 0x8;
                    D = D - 0x16;
                    break;
                }
            }
            case 11: {
                if (D < 0x16) {
                    M = M - 0x3;
                    D = D + 0x9;
                    break;
                } else {
                    M = M - 0x2;
                    D = D - 0x15;
                    break;
                }
            }
            case 12: {
                if (D < 0x16) {
                    M = M - 0x3;
                    D = D + 0x9;
                    break;
                } else {
                    M = M - 0x2;
                    D = D - 0x15;
                    break;
                }
            }

        }

        String year = null, month, day;
        year = String.valueOf(Y);
        if (M < 10) {
            month = String.valueOf(M);
        } else {
            month = "0" + String.valueOf(M);
        }
        if (D < 0xa) {
            day = String.valueOf(D);
        } else {
            day = "0" + String.valueOf(D);
        }
        return year + "/" + month + "/" + day;
    }

    public static String getShamsiDate(String date) {
        String str1 = toShamsi(date);
        String str2 = str1.split("/")[0];
        String str3 = str1.split("/")[1];
        String str4 = str1.split("/")[2];
        String str5 = "";
        if (str3.equals("01")) {
            str5 = "فروردین";
        } else if (str3.equals("02")) {
            str5 = "اردیبهشت";
        } else if (str3.equals("03")) {
            str5 = "خرداد";
        } else if (str3.equals("04")) {
            str5 = "تیر";
        } else if (str3.equals("05")) {
            str5 = "مرداد";
        } else if (str3.equals("06")) {
            str5 = "شهریور";
        } else if (str3.equals("07")) {
            str5 = "مهر";
        } else if (str3.equals("08")) {
            str5 = "آبان";
        } else if (str3.equals("09")) {
            str5 = "آذر";
        } else if (str3.equals("10")) {
            str5 = "دی";
        } else if (str3.equals("11")) {
            str5 = "بهمن";
        } else if (str3.equals("12")) {
            str5 = "اسفند";
        }

        return getDayOfWeekPersianTitle(date) + " " + str4 + " " + str5 + " " + str2;

    }

    public static String toTimeShort(String time) {
        String hour = time.split(":")[0];
        String min = time.split(":")[1];
        return hour + ":" + min;
    }

    public static int getIndexOfList(String[] list, String find) {

        String result = "";

        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(find)) {
                return i;
            }
        }
        return -1;
    }

    public static String findValueFromVariable(String[] variables, String[] values, String findingVarible) {

        String result = "";

        for (int i = 0; i < variables.length; i++) {
            if (variables[i].equals(findingVarible)) {
                Log.d(TAG, "[findValueFromVariable] " + findingVarible + " = " + values[i]);
                result = values[i];
            }
        }
        return result;
    }

    public static String findValueFromVariable(List<String> variables, List<String> values, String findingVarible) {

        String result = "";

        for (int i = 0; i < variables.size(); i++) {
            if (variables.get(i).equals(findingVarible)) {
                Log.d(TAG, "[findValueFromVariable] " + findingVarible + " = " + values.get(i));
                result = values.get(i);
            }
        }
        return result;
    }

    public static boolean isOnline(Context context) {
        NetworkInfo localNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return (localNetworkInfo != null) && (localNetworkInfo.isConnectedOrConnecting());
    }

    public static boolean isConnected(String url, int port, int timeout) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        try {
            Socket localSocket = new Socket();
            localSocket.connect(new InetSocketAddress(url, port), timeout);
            localSocket.close();
            return true;
        } catch (IOException localIOException) {
        }
        return false;
    }

    public static String getFileDirectory(String address) {
        try {
            return address.substring(0, address.lastIndexOf('/')) + "/";
        } catch (Exception e) {
            return "";
        }
    }

    public static String getFileName(String fileAddress) {
        try {
            return fileAddress.substring(1 + fileAddress.lastIndexOf('/'), fileAddress.length());
        } catch (Exception e) {
            return "";
        }
    }

    public static String getFileSingleName(String fileAddress) {
        try {
            return fileAddress.substring(1 + fileAddress.lastIndexOf('/'), fileAddress.lastIndexOf('.'));
        } catch (Exception e) {
            return "";
        }
    }

    public static String getFileExtention(String fileAddress) {
        return fileAddress.substring(1 + fileAddress.lastIndexOf('.'), fileAddress.length());
    }

    public static boolean copyFile(File srcFile, File dstFile, boolean replace) {
        Log.d(TAG, "copying file from " + srcFile + " to " + dstFile);

        if (!srcFile.exists()) {
            Log.d("TAG", "[copyFile] failed. source file not exists. file path: " + srcFile.getAbsolutePath());

            return false;
        }

        if (dstFile.exists()) {
            if (replace) {
                dstFile.delete();
            } else {
                return false;
            }
        }

        if (!new File(getFileDirectory(dstFile.getAbsolutePath())).exists()) {
            new File(getFileDirectory(dstFile.getAbsolutePath())).mkdirs();
        }

        try {
            InputStream in = new FileInputStream(srcFile.getAbsolutePath());
            OutputStream out = new FileOutputStream(dstFile.getAbsolutePath());

            copyFile(in, out);
            in.close();
            out.close();

        } catch (FileNotFoundException localFileNotFoundException) {
            localFileNotFoundException.printStackTrace();
            return false;
        } catch (IOException localIOException1) {
            localIOException1.printStackTrace();
            return false;
        }

        return true;

    }

    public static boolean copyFile(String srcAddress, String dstAddress, boolean replace) {
        Log.d(TAG, "copying file from " + srcAddress + " to " + dstAddress);

        File srcFile = new File(srcAddress);
        if (!srcFile.exists()) {
            Log.d("TAG", "[copyFile] failed. source file not exists. file path: " + srcFile.getAbsolutePath());

            return false;
        }

        File dstFile = new File(dstAddress);

        if (dstFile.exists()) {
            if (replace) {
                dstFile.delete();
            } else {
                return false;
            }
        }

        if (!new File(getFileDirectory(dstAddress)).exists()) {
            new File(getFileDirectory(dstAddress)).mkdirs();
        }

        try {
            InputStream in = new FileInputStream(srcAddress);
            OutputStream out = new FileOutputStream(dstAddress);

            copyFile(in, out);
            in.close();
            out.close();

        } catch (FileNotFoundException localFileNotFoundException) {
            localFileNotFoundException.printStackTrace();
            return false;
        } catch (IOException localIOException1) {
            localIOException1.printStackTrace();
            return false;
        }

        return true;

    }

    public static boolean copyFileFromAssets(Context context, String srcAddress, String dstAddress, boolean replace) {
        Log.d(TAG, "copying file from " + srcAddress + " to " + dstAddress);
        AssetManager assetManager = context.getAssets();
        File dstFile = new File(dstAddress);

        if (dstFile.exists()) {
            if (replace) {
                dstFile.delete();
            } else {
                return false;
            }
        }

        if (!new File(getFileDirectory(dstAddress)).exists()) {
            new File(getFileDirectory(dstAddress)).mkdirs();
        }

        InputStream in = null;
        OutputStream out = null;
        try {
            in = assetManager.open(srcAddress);
            out = new FileOutputStream(dstAddress);

            copyFile(in, out);
            in.close();
            out.close();

        } catch (FileNotFoundException localFileNotFoundException) {
            localFileNotFoundException.printStackTrace();
            return false;
        } catch (IOException localIOException1) {
            localIOException1.printStackTrace();
            return false;
        }

        return true;

    }

    public static Uri getUriFromFile(Context context, String filePath) {

        File file = new File(filePath);
        Uri uri;
        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(context, context.getPackageName() + ".provider", file);
        } else {
            uri = Uri.fromFile(file);
        }

        return uri;

    }

    public static boolean copyFile(InputStream in, OutputStream out) throws IOException {
        Log.d(TAG, "copying file from input stream to output steam...");
        byte[] buffer = new byte[1024];
        int read = 0;
        while ((read = in.read(buffer)) > 0) {
            out.write(buffer, 0, read);
        }
        return true;
    }

    public static void saveUriToFile(Uri mImageUri, String path) {
        String sourceFilename = mImageUri.getPath();
        String destinationFilename = path;

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            bis = new BufferedInputStream(new FileInputStream(sourceFilename));
            bos = new BufferedOutputStream(new FileOutputStream(destinationFilename, false));
            byte[] buf = new byte[1024];
            bis.read(buf);
            do {
                bos.write(buf);
            } while (bis.read(buf) != -1);
        } catch (IOException e) {

        } finally {
            try {
                if (bis != null) bis.close();
                if (bos != null) bos.close();
            } catch (IOException e) {

            }
        }
    }

    public static void deleteDirectoryTree(File fileOrDirectory) {
        File[] arrayOfFile = null;
        int i = 0;
        if (fileOrDirectory.isDirectory()) {
            arrayOfFile = fileOrDirectory.listFiles();
            i = arrayOfFile.length;
        }
        for (int j = 0; ; j++) {
            if (j >= i) {
                fileOrDirectory.delete();
                return;
            }
            deleteDirectoryTree(arrayOfFile[j]);
        }
    }

    public static void clearCache(Context context) {
        deleteDirectoryTree(context.getCacheDir());
    }

    public static void loadImageFromFile(final Activity activity, final ImageView imageView, String filePath) {
        final File localFile = new File(filePath);
        Picasso.with(activity).load(localFile).noFade().skipMemoryCache().into(imageView);
        /*if (!localFile.exists()) {
            return;
        }

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                imageView.setImageURI(Uri.fromFile(localFile));
            }
        });
*/

    }

    public static void loadCachedImage(final Activity activity, final ImageView imageView, final String urlAddress, final String filePath) {

        //Log.d(TAG, "[loadCachedImage] loading cache image");

        if (isOnline(activity)) {
            final File localFile = new File(filePath);
            if (localFile.exists()) {

                new AsyncTask<Long, Long, Long>() {
                    @Override
                    protected Long doInBackground(Long... longs) {
                        return getURLLastModified(urlAddress);
                    }

                    @Override
                    protected void onPostExecute(Long result) {

                        Log.d(TAG, "[loadCachedImage] urlAddress last modified = " + result + ", file last modified = " + localFile.lastModified());

                        if (result > localFile.lastModified()) {
                            Log.d(TAG, "[loadCachedImage] file on web is newer. downloading from web...");

                            DownloadManager.DownloadTask mDownloadTask = new DownloadManager.DownloadTask();
                            mDownloadTask.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new String[]{urlAddress, getFileDirectory(filePath), getFileName(filePath)});
                            mDownloadTask.setOnEventListener(new DownloadManager.OnEventListener() {
                                @Override
                                public void onProgressUpdate(int progress) {

                                }

                                @Override
                                public void onPostExecute(boolean result) {
                                    Functions.loadImageFromFile(activity, imageView, filePath);
                                }
                            });


                        } else {
                            Log.d(TAG, "[loadCachedImage] file on sdcard is newer. loading from sdcard...");
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    loadImageFromFile(activity, imageView, filePath);
                                }
                            });
                        }

                        super.onPostExecute(result);
                    }
                }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            } else {

                Log.d(TAG, "[loadCachedImage] file not exists. downloading from web...");
                DownloadManager.DownloadTask mDownloadTask = new DownloadManager.DownloadTask();
                mDownloadTask.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new String[]{urlAddress, getFileDirectory(filePath), getFileName(filePath)});
                mDownloadTask.setOnEventListener(new DownloadManager.OnEventListener() {
                    @Override
                    public void onProgressUpdate(int progress) {

                    }

                    @Override
                    public void onPostExecute(boolean result) {
                        if (result) {
                            Functions.loadImageFromFile(activity, imageView, filePath);
                        }
                    }
                });


            }
        } else {
            Log.d(TAG, "[loadCachedImage] phone is offline. loading from sdcard...");
            loadImageFromFile(activity, imageView, filePath);

        }


        //Log.d(TAG, "[loadCachedImage] loading completed.");
    }

    public static Notification createNotification(Context context, int small_icon, int icon, String text, String title, String full_text, Intent intent, boolean autoCancel, long when, boolean hasSound, int sound, boolean hasVibrate, boolean hasLED, int LEDcolor, int LEDOnMS, int LEDOffMS) {
        if (context == null) {
            return null;
        }

        Notification.Builder notificationBuilder = new Notification.Builder(context).setSmallIcon(small_icon).setLargeIcon(BitmapFactory.decodeResource(context.getResources(), icon)).setContentTitle(title).setContentText(text).setTicker(text).setAutoCancel(autoCancel).setWhen(when).setDefaults(0);

        if (intent != null) {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            notificationBuilder.setContentIntent(pendingIntent);
        }


        if (hasLED) {
            Log.d(TAG, "[createNotification] notification has led.");

            notificationBuilder.setLights(LEDcolor, LEDOnMS, LEDOffMS);

        }

        Notification notification;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            notification = notificationBuilder.build();
        } else {
            notification = notificationBuilder.getNotification();
        }


        if (hasSound) {
            if (sound != 0) {
                notification.defaults |= sound;
            } else {
                notification.defaults |= Notification.DEFAULT_SOUND;
            }
        }

        if (hasVibrate) notification.defaults |= Notification.DEFAULT_VIBRATE;

		/*
         * if (hasLED) { Log.d(TAG,
		 * "[createNotification] notification has led.");
		 *
		 * notification.ledARGB = LEDcolor; notification.ledOnMS = LEDOnMS;
		 * notification.ledOffMS = LEDOffMS;
		 *
		 * notification.defaults |= Notification.FLAG_SHOW_LIGHTS;
		 *
		 * }
		 */

        return notification;
    }

    public static void showNotification(Context context, Notification notification, int id) {
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(id, notification);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static String getPathFromURI(Context context, Uri uri) {
        if (uri == null) {
            Log.d(TAG, "[getPathFromURI] uri is null.");
            return "";
        }


        final boolean isKitKatOrAbove = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKatOrAbove && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;






        /*
        String result = "";
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        if (cursor == null) {
            result = uri.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;*/
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static String uploadFile(String filePath, String urlAddress) {

        Log.d(TAG, "[uploadFile] uploading " + filePath);
        StrictMode.ThreadPolicy.Builder localboolean1 = new StrictMode.ThreadPolicy.Builder();
        StrictMode.ThreadPolicy policy = localboolean1.build();
        StrictMode.setThreadPolicy(policy);

        File sourceFile = new File(filePath);
        if (!sourceFile.isFile()) {
            Log.d(TAG, "[uploadFile] file not found");
            return "file not found";
        }

        String fileName = filePath;

        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        int serverResponseCode = 0;

        try {
            FileInputStream fileInputStream = new FileInputStream(sourceFile);
            URL url = new URL(urlAddress);

            // Open a HTTP connection to the URL
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true); // Allow Inputs
            conn.setDoOutput(true); // Allow Outputs
            conn.setUseCaches(false); // Don't use a Cached Copy
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("ENCTYPE", "multipart/form-data");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            conn.setRequestProperty("uploaded_file", fileName);

            dos = new DataOutputStream(conn.getOutputStream());

            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\"" + fileName + "\"" + lineEnd);

            dos.writeBytes(lineEnd);

            // create a buffer of maximum size
            bytesAvailable = fileInputStream.available();

            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            buffer = new byte[bufferSize];

            // read file and write it into form...
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);

            while (bytesRead > 0) {

                dos.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);

            }

            // send multipart form data necesssary after file data...
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

            // Responses from the server (code and message)
            serverResponseCode = conn.getResponseCode();
            // String serverResponseMessage = conn.getResponseMessage();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }

            bufferedReader.close();

            String response = builder.toString();

            Log.d(TAG, "[uploadFile] serverResponseCode = " + String.valueOf(serverResponseCode));

            // close the streams //
            fileInputStream.close();
            dos.flush();
            dos.close();

            if (serverResponseCode != 200) {
                return "fail";
            }

            Log.d(TAG, "[uploadFile] response = " + response);

            return response;

        } catch (Exception e) {
            Log.d(TAG, "[uploadFile] " + e.getLocalizedMessage());
            e.printStackTrace();
            return "fail";
        }

    }

    public static boolean reduceImageFileSize(final String filePath, int max_file_size_bytes, int quality, int ratio) {

        File file = new File(filePath);

        if (!file.exists() || file.length() == 0 || max_file_size_bytes == 0) {
            return false;
        }

        int scale = 1;
        BitmapFactory.Options o = new BitmapFactory.Options();
        //o.inJustDecodeBounds = true;
        //o.inSampleSize = scale;

        Log.d(TAG, "[reduceImageFileSize] to reduce. " + file.length() + " must be smaller than " + max_file_size_bytes);

        if (file.length() > max_file_size_bytes) {
            Log.d(TAG, "[reduceImageFileSize] file size is larger than maximum.");


            Bitmap photo = getBitmapFromFile(filePath);

            float imageWidth = photo.getWidth();
            float imageHeight = photo.getHeight();

            float newImageWidth = 100, newImageHeight = 100;


            try {
                file = new File(filePath);

                while (file.length() > max_file_size_bytes && newImageWidth > 0 && newImageHeight > 0) {
                    Log.d(TAG, "[reduceImageFileSize] resizing photo... (" + file.length() + " > " + max_file_size_bytes + ") ratio: " + ratio + " (" + ((100f - scale * ratio) / 100f) * imageWidth + " x " + ((100f - scale * ratio) / 100f) * imageHeight + ")");

                    newImageWidth = ((100f - scale * ratio) / 100f) * imageWidth;
                    newImageHeight = ((100f - scale * ratio) / 100f) * imageHeight;

                    Bitmap photo2 = Bitmap.createScaledBitmap(photo, Math.round(newImageWidth), Math.round(newImageHeight), false);
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    photo2.compress(Bitmap.CompressFormat.JPEG, quality, bytes);

                    File f = new File(filePath);
                    f.createNewFile();
                    FileOutputStream fo = new FileOutputStream(f);
                    fo.write(bytes.toByteArray());
                    fo.close();

                    scale++;


                    // OLD
                    /*
                    scale++;
                    BitmapFactory.Options o2 = new BitmapFactory.Options();
                    o2.inSampleSize = scale;

                    Bitmap bitmap = BitmapFactory.decodeFile(filePath, o2);
                    FileOutputStream out = null;
                    try {
                        out = new FileOutputStream(filePath);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, out);
                        out.flush();
                        out.close();
                        bitmap.recycle();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    */
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d(TAG, "[reduceImageFileSize] resized (" + file.length() + " <= " + max_file_size_bytes + ")");
        }
        return true;
    }

    public static String URLEncode(String urlAddress) {
        return URLEncoder.encode(urlAddress);
    }

    public static Bitmap getScaledBitmap(String filePath, int maxSize) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        try {
            FileInputStream fis = new FileInputStream(filePath);
            Bitmap imageBitmap = BitmapFactory.decodeStream(fis, null, options);

            int outWidth;
            int outHeight;
            int inWidth = imageBitmap.getWidth();
            int inHeight = imageBitmap.getHeight();
            if (inWidth > inHeight) {
                outWidth = maxSize;
                outHeight = (inHeight * maxSize) / inWidth;
            } else {
                outHeight = maxSize;
                outWidth = (inWidth * maxSize) / inHeight;
            }

            Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, outWidth, outHeight, false);
            return resizedBitmap;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static int getDpFromPx(Context context, int px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }

    public static int getPxFromDp(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    public static String normalPrice(String price, String splitter) {
        String normaled = "";
        int j;
        for (int i = 0; i < price.length(); i++) {
            j = price.length() - i;
            if (j % 3 == 0 && j >= 0 && j < price.length()) normaled += splitter;
            normaled += price.substring(i, i + 1);
        }
        return normaled;
    }

    public static String normalPastTime(String persianDate, String time) {
        try {
            String past_time_text = "";
            String now_date = PersianDate.getCurrentShamsidate();

            //Log.d(TAG, "[normalPastTime] today in shamsi is " + now_date);

            int i1 = Integer.parseInt(now_date.split("/")[0]);
            int i2 = Integer.parseInt(now_date.split("/")[1]);
            int i3 = Integer.parseInt(now_date.split("/")[2]);

            String date = persianDate;

            //Log.d(TAG, "[normalPastTime] date is " + date);

            String[] date_splited = date.split("/");

            int i4 = 0, i5 = 0, i6 = 0;
            if (date_splited.length > 0) {
                i4 = Integer.parseInt(date_splited[0]);
            }
            if (date_splited.length > 1) {
                i5 = Integer.parseInt(date_splited[1]);
            }
            if (date_splited.length > 2) {
                i6 = Integer.parseInt(date_splited[2]);
            }

            //Log.d(TAG, "[normalPastTime] THE date is " + i4 + "/" + i5 + "/" + i6);

            int i7 = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            int i8 = Calendar.getInstance().get(Calendar.MINUTE);
            int i9 = Calendar.getInstance().get(Calendar.SECOND);

            //Log.d(TAG, "[normalPastTime] now time is " + i7 + ":" + i8 + ":" + i9);

            String[] time_splited = time.split(":");

            //Log.d(TAG, "[normalPastTime] time is " + time);

            int i10 = 0, i11 = 0, i12 = 0;
            if (time_splited.length > 0) {
                i10 = Integer.parseInt(time_splited[0]);
            }
            if (time_splited.length > 1) {
                i11 = Integer.parseInt(time_splited[1]);
            }
            if (time_splited.length > 2) {
                i12 = Integer.parseInt(time_splited[2]);
            }

            //Log.d(TAG, "[normalPastTime] THE time is " + i10 + ":" + i11 + ":" + i12);

            int i13 = +(31536000 * i1 + 2592000 * i2 + 86400 * i3 + i7 * 3600 + i8 * 60 + i9) - (31536000 * i4 + 2592000 * i5 + 86400 * i6 + i10 * 3600 + i11 * 60 + i12);
            Log.d(TAG, "[normalPastTime] difference between time and now is " + i13 + " seconds.");

            int i14 = i13 / 31536000;
            int i15 = i13 - 31536000 * i14;
            int i16 = i15 / 2592000;
            int i17 = i15 - 2592000 * i16;
            int i18 = i17 / 86400;
            int i19 = i17 - 86400 * i18;
            int i20 = i19 / 3600;
            int i21 = i19 - i20 * 3600;
            int i22 = i21 / 60;
            int i23 = i21 - i22 * 60;
            if (i23 > 0) {
                past_time_text = (" " + String.valueOf(i23) + " ثانیه");
            }
            if (i22 > 0) {
                past_time_text = (String.valueOf(i22) + " دقیقه و");
            }
            if (i20 > 0) {
                past_time_text = (String.valueOf(i20) + " ساعت و");
            }
            if (i18 > 0) {
                past_time_text = (String.valueOf(i18) + " روز و");
            }
            if (i16 > 0) {
                past_time_text = (String.valueOf(i16) + " ماه و");
            }
            if (i14 > 0) {
                past_time_text = (String.valueOf(i14) + " سال و");
            }
            if (past_time_text.endsWith(" و")) {
                past_time_text = past_time_text.substring(0, past_time_text.lastIndexOf(" و"));
            }

            if (i13 <= 0) {
                past_time_text = "لحظاتی";
            }
            return past_time_text;
        } catch (Exception e) {
            Log.d(TAG, "[normalPastTime] format of date or time is not correct. it must be yyyy/mm/dd and hh:mm:ss.");
            e.printStackTrace();
        }
        return "لحظاتی";
    }

    public static int getDifferenceOfTimes(String persianDate, String time) {
        try {
            String past_time_text = "";
            String now_date = PersianDate.getCurrentShamsidate();

            //Log.d(TAG, "[normalPastTime] today in shamsi is " + now_date);

            int i1 = Integer.parseInt(now_date.split("/")[0]);
            int i2 = Integer.parseInt(now_date.split("/")[1]);
            int i3 = Integer.parseInt(now_date.split("/")[2]);

            String date = persianDate;

            //Log.d(TAG, "[normalPastTime] date is " + date);

            String[] date_splited = date.split("/");

            int i4 = 0, i5 = 0, i6 = 0;
            if (date_splited.length > 0) {
                i4 = Integer.parseInt(date_splited[0]);
            }
            if (date_splited.length > 1) {
                i5 = Integer.parseInt(date_splited[1]);
            }
            if (date_splited.length > 2) {
                i6 = Integer.parseInt(date_splited[2]);
            }

            //Log.d(TAG, "[normalPastTime] THE date is " + i4 + "/" + i5 + "/" + i6);

            int i7 = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            int i8 = Calendar.getInstance().get(Calendar.MINUTE);
            int i9 = Calendar.getInstance().get(Calendar.SECOND);

            //Log.d(TAG, "[normalPastTime] now time is " + i7 + ":" + i8 + ":" + i9);

            String[] time_splited = time.split(":");

            //Log.d(TAG, "[normalPastTime] time is " + time);

            int i10 = 0, i11 = 0, i12 = 0;
            if (time_splited.length > 0) {
                i10 = Integer.parseInt(time_splited[0]);
            }
            if (time_splited.length > 1) {
                i11 = Integer.parseInt(time_splited[1]);
            }
            if (time_splited.length > 2) {
                i12 = Integer.parseInt(time_splited[2]);
            }

            //Log.d(TAG, "[normalPastTime] THE time is " + i10 + ":" + i11 + ":" + i12);

            int i13 = +(31536000 * i1 + 2592000 * i2 + 86400 * i3 + i7 * 3600 + i8 * 60 + i9) - (31536000 * i4 + 2592000 * i5 + 86400 * i6 + i10 * 3600 + i11 * 60 + i12);
            Log.d(TAG, "[getDifferenceOfTimes] difference between time and now is " + i13 + " seconds.");

            return i13;
        } catch (Exception e) {
            Log.d(TAG, "[getDifferenceOfTimes] format of date or time is not correct. it must be yyyy/mm/dd and hh:mm:ss.");
            e.printStackTrace();
        }
        return 0;
    }

    public static String getTimeFormat(long milliSeconds, String dateFormat) {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);


        milliSeconds = Long.parseLong(normalLength(String.valueOf(milliSeconds), 13, "0", false));
        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    public static String normalPhoneNumber(String number, String code) {
        if (number.substring(0, 1).equals("0")) {
            number = number.replaceFirst("0", code);
        }
        return number;
    }

    public static double getVersionValue(String[] version_array, int cells_limit, int cell_size) {
        String value = "0";
        for (int i = 0; i < cells_limit; i++) {
            if (i >= version_array.length) {
                for (int j = 0; j < cell_size; j++) {
                    value += "0";
                }
            } else {
                value += version_array[i];
                for (int j = 0; j < (cell_size - version_array[i].length()); j++) {
                    value += "0";
                }

            }
        }

        Log.d(TAG, "[getVersionValue] version value = " + value);
        try {
            Log.d(TAG, "[getVersionValue] parsed version value = "
                    + NumberFormat.getInstance().parse(value).doubleValue());
            return NumberFormat.getInstance().parse(value).doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static Bitmap getBitmapFromResources(Context context, int resourceId) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId, options);

        return bitmap;
    }

    public static Bitmap getBitmapFromUrl(String urlAddress) {
        try {
            URL url = new URL(urlAddress);
            return BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Bitmap getBitmapFromAssets(Context context, String fileAddress) {
        AssetManager assetManager = context.getAssets();

        InputStream inputStream;
        Bitmap bitmap = null;
        try {
            inputStream = assetManager.open(fileAddress);
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    public static Bitmap getBitmapFromFile(String fileAddress) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeFile(fileAddress, options);
        return bitmap;
    }

    public static void saveBitmapToFile(Bitmap bitmap, String fileAddress) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(fileAddress);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            bitmap.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int degree) {
        try {
            Matrix matrix = new Matrix();
            matrix.postRotate(degree);
            Bitmap mBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            return mBitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void rotateImageIfNeed(String filePath) {
        try {
            // Rotate picture if necessary
            Bitmap fileBitmap = Functions.getBitmapFromFile(filePath);
            Bitmap fileBitmap2 = Functions.rotateBitmap(fileBitmap, Functions.getOrientationOfFile(filePath));
            if (fileBitmap != null) {
                Functions.saveBitmapToFile(fileBitmap, filePath);
            }
            if (fileBitmap != null && !fileBitmap.isRecycled()) {
                fileBitmap.recycle();
            }

            if (fileBitmap2 != null && !fileBitmap2.isRecycled()) {
                fileBitmap2.recycle();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getOrientationOfFile(String fileAddress) {
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(fileAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
        int rotate = 0;
        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_270:
                rotate = 270;
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                rotate = 90;
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                rotate = 180;
                break;
        }
        return rotate;
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

    public static void Vibrate(Context context, int time) {
        ((Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE)).vibrate(time);
    }

    public static void playSound(Context context, int sound, final int length, int streamType) {
        try {
            final MediaPlayer mp = MediaPlayer.create(context, sound);
            AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            float volume = (float) (am.getStreamVolume(streamType) / (double) am.getStreamMaxVolume(streamType));
            mp.setVolume(volume, volume);
            mp.setLooping(false);
            mp.start();
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mp.setLooping(false);
                    //if (mp.isPlaying()) {
                    mp.stop();
                    mp.release();
                    //}
                }
            }, length);

        } catch (Exception e) {
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

    public static String get10(int paramInt) {
        String str = String.valueOf(paramInt);
        if (paramInt < 10) {
            str = "0" + str;
        }
        return str;
    }

    public static int searchList(String[] list, String key) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(key)) {
                return i;
            }
        }
        return -1;
    }

    public static int searchList(int[] list, int key) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == key) {
                return i;
            }
        }
        return -1;
    }

    public static boolean unpackZip(String src_path, String src_file_name, String dst_path, boolean replace) {
        InputStream is;
        ZipInputStream zis;
        try {
            String filename;
            is = new FileInputStream(src_path + src_file_name);
            zis = new ZipInputStream(new BufferedInputStream(is));
            ZipEntry ze;
            byte[] buffer = new byte[1024];
            int count;

            while ((ze = zis.getNextEntry()) != null) {
                // zapis do souboru
                filename = ze.getName();

                if (!replace && new File(src_path + filename).exists()) {
                    continue;
                }

                // Need to create directories if not exists, or
                // it will generate an Exception...
                if (ze.isDirectory()) {
                    File fmd = new File(dst_path + filename);
                    fmd.mkdirs();
                    continue;
                }

                FileOutputStream fout = new FileOutputStream(dst_path + filename);

                // cteni zipu a zapis
                while ((count = zis.read(buffer)) != -1) {
                    fout.write(buffer, 0, count);
                }

                fout.close();
                zis.closeEntry();
            }

            zis.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @SuppressWarnings("unused")
    public static void deleteSMS(Context ctx, String message, String number) {
        Log.d(TAG, "[deleteSMS] number=" + normalPhoneNumber(number, "+98") + ", text=" + message);
        try {
            Uri uriSms = Uri.parse("content://sms");
            Cursor c = ctx.getContentResolver().query(uriSms, new String[]{"_id", "thread_id", "address", "person", "date", "body"}, null, null, null);

            Log.i(TAG, "c count......" + c.getCount());
            if (c != null && c.moveToFirst()) {
                do {
                    long id = c.getLong(0);
                    long threadId = c.getLong(1);
                    String address = c.getString(2);
                    String date = c.getString(3);
                    String body = c.getString(5);

                    Log.e("log>>>", "id>" + c.getString(0) + "  threadId>" + c.getString(1) + "  address>" + c.getString(2) + "  date>" + c.getString(3) + "  4>" + c.getString(4) + "  body>" + c.getString(5));
                    // Log.e("log>>>", "date" + c.getString(0));
                    if (message.equals(body) && normalPhoneNumber(address, "+98").equals(normalPhoneNumber(number, "+98"))) {
                        // mLogger.logInfo("Deleting SMS with id: " + threadId);
                        int rows = ctx.getContentResolver().delete(Uri.parse("content://sms/" + id), "date=?", new String[]{c.getString(4)});
                        Log.i("log>>>", "Delete success......... rows: " + rows);
                        Log.i("log>>>", "Delete success......... body: " + body);
                    }
                } while (c.moveToNext());
            }

        } catch (Exception e) {
            Log.e("log>>>", e.toString());
            Log.e("log>>>", e.getMessage());
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

    public static Date getDateFromTimestamp(TimeZone timezone, long timestamp) {
        try {
            Calendar calendar = Calendar.getInstance(timezone);
            timestamp = Long.parseLong(normalLength(String.valueOf(timestamp), 13, "0", false));
            calendar.setTimeInMillis(timestamp);
            calendar.add(Calendar.MILLISECOND, timezone.getOffset(calendar.getTimeInMillis()));
            return calendar.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static long getTimestampFromDate(TimeZone timezone, Date date) {
        try {
            Calendar calendar = Calendar.getInstance(timezone);
            calendar.setTime(date);
            return calendar.getTimeInMillis();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
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

    public static Uri getVideoContentUri(Context context, File file) {
        String filePath = file.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Video.Media._ID},
                MediaStore.Video.Media.DATA + "=? ",
                new String[]{filePath}, null);
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/video/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (file.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Video.Media.DATA, filePath);
                return context.getContentResolver().insert(
                        MediaStore.Video.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }

    public static void shareTextViaIntent(Context context, String intent_title, String subject, String message) {
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);

        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, intent_title));
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

    public static boolean isServiceRunning(Context context, Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static String normalLength(String text, int length, String character, boolean atFirst) {
        //Log.d(TAG, "[normalLength] old: " + text);

        for (int i = text.length(); i < length; i++) {
            if (atFirst) {
                text = character + text;
            } else {
                text += character;
            }
        }

        //Log.d(TAG, "[normalLength] new: " + text);

        return text;
    }

    public static Uri getUriFromBitmap(Context context, Bitmap image) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), image, "Title", null);
        return Uri.parse(path);
    }

    public static int getAttributeData(Context context, int attr) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attr, typedValue, true);
        return typedValue.data;
    }

    public static int getAttributeResourceId(Context context, int attr) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attr, typedValue, true);
        return typedValue.resourceId;
    }

    public static boolean hasPermission(Activity activity, String permission) {
        int status = ActivityCompat.checkSelfPermission(activity, permission);

        return status == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Checks if the app has permission to write to device storage
     * <p>
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyPermissions(Activity activity, String[] permissions, boolean ask_each_time) {
        final int requestCode = 1;

        for (int i = 0; i < permissions.length; i++) {
            int status = ActivityCompat.checkSelfPermission(activity, permissions[i]);

            if (!ask_each_time && status != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        activity,
                        permissions,
                        requestCode
                );
                break;
            } else if (ask_each_time && status == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(
                        activity,
                        permissions,
                        requestCode
                );
                break;
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void setLocale(Context context, String locale) {
        Locale localLocale = new Locale(locale);
        Resources localResources = context.getResources();
        DisplayMetrics localDisplayMetrics = localResources.getDisplayMetrics();
        Configuration localConfiguration = localResources.getConfiguration();
        localConfiguration.locale = localLocale;
        try {
            localConfiguration.setLayoutDirection(localLocale);
        } catch (Exception e) {
        } catch (NoSuchMethodError e) {
        }
        localResources.updateConfiguration(localConfiguration, localDisplayMetrics);
    }

    public static String decryptStringAESFromStringToString(String text, String key) throws IOException, NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException {

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] keyBytes = new byte[16];
            byte[] b = key.getBytes("UTF-8");
            int len = b.length;
            if (len > keyBytes.length) len = keyBytes.length;
            System.arraycopy(b, 0, keyBytes, 0, len);
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            byte[] results = cipher.doFinal(Base64.decode(text, 0));
            return new String(results, "UTF-8");
        } catch (Exception e) {
            Log.d(TAG, "[decryptStringAES] " + e.getMessage());
            return null;
        }
    }

    public static byte[] decryptStringAESFromStringToBytes(String text, String key) throws IOException, NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException {

        try {
            Cipher cipher = Cipher.getInstance("AES");
            byte[] keyBytes = new byte[16];
            byte[] b = key.getBytes("UTF-8");
            int len = b.length;
            if (len > keyBytes.length) len = keyBytes.length;
            System.arraycopy(b, 0, keyBytes, 0, len);
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            byte[] results = cipher.doFinal(Base64.decode(text, 0));
            return results;
        } catch (Exception e) {
            Log.d(TAG, "[decryptStringAESToBytes] " + e.getMessage());
            return null;
        }
    }

    public static byte[] decryptStringAESFromBytesToBytes(byte[] bytes, String key) throws IOException, NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException {

        try {
            Cipher cipher = Cipher.getInstance("AES");
            byte[] keyBytes = new byte[16];
            byte[] b = key.getBytes("UTF-8");
            int len = b.length;
            if (len > keyBytes.length) len = keyBytes.length;
            System.arraycopy(b, 0, keyBytes, 0, len);
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            byte[] results = cipher.doFinal(bytes);
            return results;
        } catch (Exception e) {
            Log.d(TAG, "[decryptStringAESToBytes] " + e.getMessage());
            return null;
        }
    }

    public static String md5(final String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void customSnackBar(Activity activity, int layout_id, ViewGroup root_layout, String text, int length, Bitmap image, String font, int background_color, int text_color) {


        View view = activity.getLayoutInflater().inflate(layout_id, root_layout, false);
        // Create the Snackbar
        Snackbar snackbar = Snackbar.make(view, text, length);
        // Get the Snackbar's layout view
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        // Hide the text
        TextView textView = (TextView) layout.findViewById(android.support.design.R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);

        // Inflate our custom view
        View snackView = activity.getLayoutInflater().inflate(R.layout.custom_snackbar, null);
        // Configure the view
        ImageView imageView = (ImageView) snackView.findViewById(R.id.image);
        if (image != null) {
            imageView.setImageBitmap(image);
        }

        RelativeLayout backgroundLayout = (RelativeLayout) snackView.findViewById(R.id.layout);
        backgroundLayout.setBackgroundColor(background_color);

        TextViewPlus textViewPlus = (TextViewPlus) snackView.findViewById(R.id.text);
        textViewPlus.setText(text);
        textViewPlus.setFont(activity, font);
        textViewPlus.setTextColor(text_color);

        // Add the view to the Snackbar's layout
        layout.addView(snackView, 0);
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

    public static JSONArray removeFromJSONArray(JSONArray source, int index) throws Exception {
        if (index < 0 || index > source.length() - 1) {
            throw new IndexOutOfBoundsException();
        }

        final JSONArray copy = new JSONArray();
        for (int i = 0, count = source.length(); i < count; i++) {
            if (i != index) copy.put(source.get(i));
        }
        return copy;
    }

    public static SpannableString textWithFont(Context context, String text, String font) {
        SpannableString s = new SpannableString(text);
        s.setSpan(new TypefaceSpan(context, font), 0, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return s;
    }


    public static String readStream(InputStream is) {
        // http://stackoverflow.com/a/5445161
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }


    public static String readRawResource(Context context, @RawRes int res) {
        return readStream(context.getResources().openRawResource(res));
    }

    public static void hideKeyboard(Context context, View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        hideKeyboard(activity.getApplicationContext(), view);
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

    public static class TypefaceSpan extends MetricAffectingSpan {
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
