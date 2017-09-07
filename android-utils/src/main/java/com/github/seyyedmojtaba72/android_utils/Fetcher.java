package com.github.seyyedmojtaba72.android_utils;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class Fetcher {
    private static final String TAG = Fetcher.class.getSimpleName();

    public static String FetchAsset(Context context, String address) {

        BufferedReader reader = null;
        String result = "";
        String mLine = "";
        try {
            reader = new BufferedReader(new InputStreamReader(context
                    .getAssets().open(address)));

            mLine = reader.readLine();
            while (mLine != null) {
                result += mLine;
                mLine = reader.readLine();
            }
        } catch (IOException e) {
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }
        result = result.replaceFirst("\uFEFF", "");
        return result;
    }

    public static String FetchResource(Context context, int resource) {

        InputStream is = context.getResources().openRawResource(resource);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String result = writer.toString();

        result = result.replaceFirst("\uFEFF", "");
        return result;
    }

    public static String FetchFile(String paramString) {
        String result = "";
        Log.d(TAG, "Fetching '" + paramString + "'");
        FileInputStream localFileInputStream1 = null;
        try {
            File localFile = new File(paramString);
            if (!localFile.exists()) {
                Log.d(TAG, "file not found!");
                return "";
            }
            localFileInputStream1 = new FileInputStream(localFile);
            FileChannel localFileChannel = localFileInputStream1.getChannel();
            MappedByteBuffer localMappedByteBuffer = localFileChannel.map(
                    FileChannel.MapMode.READ_ONLY, 0L, localFileChannel.size());
            result = Charset.defaultCharset().decode(localMappedByteBuffer)
                    .toString();
            localFileInputStream1.close();
            result = result.replaceFirst("\uFEFF", "");
            Log.d(TAG, "Fetching '" + paramString + "' complete. result= '"
                    + result + "'");
            try {
                localFileInputStream1.close();
            } catch (IOException localIOException5) {
                localIOException5.printStackTrace();
            }

        } catch (IOException localIOException3) {
            localIOException3.printStackTrace();
        }
        try {
            localFileInputStream1.close();
        } catch (IOException localIOException4) {
            localIOException4.printStackTrace();
        }
        return result;
    }


    public static String FetchURL(String urlAddress) {
        urlAddress = urlAddress.replaceAll(" ", "%20");

        String result = "";
        Log.d(TAG, "Fetching '" + urlAddress + "'");

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());

        BufferedReader in;
        try {
            in = new BufferedReader(new InputStreamReader(new URL(
                    urlAddress).openStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                result += inputLine;
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        result = result.replaceFirst("\uFEFF", "");

        Log.d(TAG, "Fetching '" + urlAddress + "' complete. result= '"
                + result + "'");
        return result;

    }

    public static String httpRequest(String request, String content_type, String urlAddress, JSONArray headers, String data) {

        String result = "";

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());

        URL url;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlAddress);
            connection = (HttpURLConnection) url.openConnection();
            if (request.equals("POST")) {
                connection.setDoOutput(true);
            }
            connection.setRequestMethod(request); // hear you are telling that it is a POST request, which can be changed into "PUT", "GET", "DELETE" etc.
            if (headers != null) {
                for (int i = 0; i < headers.length(); i++) {
                    JSONObject obj = headers.getJSONObject(i);
                    connection.setRequestProperty(obj.getString("key"), obj.getString("value"));
                }
            }
            connection.setRequestProperty("Content-Type", content_type + " ; charset=UTF-8"); // here you are setting the `Content-Type` for the data you are sending which is `application/json`


            if (data != null) {
                //Send request

                OutputStream os = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

                writer.write(data);

                writer.flush();
                writer.close();
                os.close();
            }

            connection.connect();


            BufferedReader in;
            int response = connection.getResponseCode();
            if (response >= 200 && response <= 399) {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            String inputLine;
            while ((inputLine = in.readLine()) != null)
                result += inputLine;
            in.close();


        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        result = result.replaceFirst("\uFEFF", "");

        Log.d(TAG, request + " Request '" + urlAddress + "' complete. result= '" + result + "'");
        return result;
    }


    public static String postRequest(String content_type, String urlAddress, JSONArray headers, String data) {
        return httpRequest("POST", content_type, urlAddress, headers, data);
    }

    public static String postRequest(String urlAddress, JSONArray headers, String data) {
        return postRequest("application/x-www-form-urlencoded", urlAddress, headers, data);
    }

    public static String getRequest(String content_type, String urlAddress, JSONArray headers, String data) {
        return httpRequest("GET", content_type, urlAddress, headers, data);
    }

    public static String getRequest(String urlAddress, JSONArray headers, String data) {
        return getRequest("application/x-www-form-urlencoded", urlAddress, headers, data);
    }

    public static String putRequest(String content_type, String urlAddress, JSONArray headers, String data) {
        return httpRequest("PUT", content_type, urlAddress, headers, data);
    }

    public static String putRequest(String urlAddress, JSONArray headers, String data) {
        return putRequest("application/x-www-form-urlencoded", urlAddress, headers, data);
    }

    public static String deleteRequest(String content_type, String urlAddress, JSONArray headers, String data) {
        return httpRequest("DELETE", content_type, urlAddress, headers, data);
    }

    public static String deleteRequest(String urlAddress, JSONArray headers, String data) {
        return deleteRequest("application/x-www-form-urlencoded", urlAddress, headers, data);
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

        HttpURLConnection localHttpURLConnection;
        HttpURLConnection.setFollowRedirects(false);
        try {
            localHttpURLConnection = (HttpURLConnection) new URL(urlAddress).openConnection();
            return localHttpURLConnection.getLastModified();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static String URLEncode(String urlAddress) {
        return URLEncoder.encode(urlAddress);
    }
}