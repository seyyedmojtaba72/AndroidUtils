package com.github.seyyedmojtaba72.android_utils;

import android.os.AsyncTask;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadManager {
    private static final String TAG = "DownloadManager";

    public interface OnEventListener {
        void onProgressUpdate(int progress);

        void onPostExecute(boolean result);
    }

    public static class DownloadTask extends AsyncTask<String[], Integer, Boolean> {
        private OnEventListener onEventListener;

        private String urlAddress;
        private String filePath;
        private boolean isDownloading = false;
        private int progress = 0;


        public DownloadTask() {
        }

        protected Boolean doInBackground(String[]... urls) {
            for (String[] sUrl : urls) {
                Log.d(TAG, "Downloading \"" + sUrl[0] + "\" to \"" + sUrl[1] + "/" + sUrl[2] + "\"");
                urlAddress = sUrl[0];
                String folderPath = sUrl[1];
                filePath = sUrl[1] + "/" + sUrl[2];
                isDownloading = true;

                if (!new File(folderPath).exists()) {
                    new File(folderPath).mkdirs();
                }

                InputStream input = null;
                OutputStream output = null;
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(sUrl[0]);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.connect();

                    // expect HTTP 200 OK, so we don't mistakenly save error
                    // report
                    // instead of the file
                    if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                        isDownloading = false;
                        return false;
                    }

                    // this will be useful to display download percentage
                    // might be -1: server did not report the length
                    int fileLength = connection.getContentLength();

                    // download the file
                    input = connection.getInputStream();
                    output = new FileOutputStream(filePath);

                    byte data[] = new byte[4096];
                    long total = 0;
                    int count;
                    while ((count = input.read(data)) != -1) {
                        // allow canceling with back button
                        if (isCancelled()) {
                            isDownloading = false;
                            input.close();
                            return false;
                        }
                        total += count;
                        // publishing the progress....
                        if (fileLength > 0) // only if total length is known
                            publishProgress((int) (total * 100 / fileLength));
                        output.write(data, 0, count);
                    }
                } catch (Exception e) {
                    return false;
                } finally {
                    try {
                        if (output != null) output.close();
                        if (input != null) input.close();
                    } catch (IOException ignored) {
                        isDownloading = false;
                        return true;
                    }

                    if (connection != null) connection.disconnect();
                }
            }

            isDownloading = false;
            return true;

        }

        public boolean isDownloading() {
            return isDownloading;
        }

        public String getUrlAddress() {
            return urlAddress;
        }

        public int getProgress() {
            return progress;
        }

        public void cancelDownload(boolean deleteFile) {
            if (deleteFile) {
                if (new File(filePath).exists()) {
                    new File(filePath).delete();
                }
            }
            isDownloading = false;
            this.cancel(true);
        }

        public void setOnEventListener(OnEventListener onEventListener) {
            this.onEventListener = onEventListener;
        }


        @Override
        protected void onProgressUpdate(final Integer... values) {
            this.progress = values[0];
            if (onEventListener != null)
                onEventListener.onProgressUpdate(values[0]);

            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (onEventListener != null)
                onEventListener.onPostExecute(result);

            super.onPostExecute(result);
        }
    }
}