package com.github.seyyedmojtaba72.android_utils;

import android.os.StrictMode;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by SeyyedMojtaba on 6/24/17.
 */

public class UploadManager {

    private static final String TAG = UploadManager.class.getSimpleName();

    public static String uploadFile(String filePath, String urlAddress) throws IllegalAccessException {

        Log.d(TAG, "[uploadFile] uploading " + filePath);
        StrictMode.ThreadPolicy.Builder localboolean1 = new StrictMode.ThreadPolicy.Builder();
        StrictMode.ThreadPolicy policy = localboolean1.build();
        StrictMode.setThreadPolicy(policy);

        File sourceFile = new File(filePath);
        if (!sourceFile.isFile()) {
            Log.e(TAG, "[uploadFile] file not found");
            throw new IllegalAccessException("file not found");
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
                Log.e(TAG, "[uploadFile] state is not OK");
                throw new IllegalStateException("state is not OK");
            }

            Log.d(TAG, "[uploadFile] response = " + response);
            return response;

        } catch (Exception e) {
            Log.e(TAG, "[uploadFile] " + e.getLocalizedMessage());
            e.printStackTrace();
            return "";
        }

    }
}
