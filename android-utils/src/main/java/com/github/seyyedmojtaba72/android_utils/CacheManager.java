package com.github.seyyedmojtaba72.android_utils;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;

import static com.github.seyyedmojtaba72.android_utils.Fetcher.getURLLastModified;
import static com.github.seyyedmojtaba72.android_utils.FileUtils.deleteDirectoryTree;
import static com.github.seyyedmojtaba72.android_utils.FileUtils.getFileDirectory;
import static com.github.seyyedmojtaba72.android_utils.FileUtils.getFileName;
import static com.github.seyyedmojtaba72.android_utils.Network.isOnline;

/**
 * Created by SeyyedMojtaba on 6/24/17.
 */

public class CacheManager {
    private static final String TAG = CacheManager.class.getSimpleName();

   /* public static void loadCachedImage(final Activity activity, final ImageView imageView, final String urlAddress, final String filePath) {

        Log.d(TAG, "[loadCachedImage] loading cache image");

        final File file = new File(filePath);

        if (isOnline(activity)) {
            if (file.exists()) {

                new AsyncTask<Long, Long, Long>() {
                    @Override
                    protected Long doInBackground(Long... longs) {
                        return getURLLastModified(urlAddress);
                    }

                    @Override
                    protected void onPostExecute(Long result) {

                        Log.d(TAG, "[loadCachedImage] urlAddress last modified = " + result + ", file last modified = " + file.lastModified());

                        if (result > file.lastModified()) {
                            Log.d(TAG, "[loadCachedImage] file on web is newer. downloading from web...");

                            DownloadManager.DownloadTask mDownloadTask = new DownloadManager.DownloadTask();
                            mDownloadTask.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new String[]{urlAddress, getFileDirectory(filePath), getFileName(filePath)});
                            mDownloadTask.setOnEventListener(new DownloadManager.OnEventListener() {
                                @Override
                                public void onProgressUpdate(int progress) {

                                }

                                @Override
                                public void onPostExecute(boolean result) {
                                    Picasso.get().load(file).noFade().memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).into(imageView);
                                }
                            });


                        } else {
                            Log.d(TAG, "[loadCachedImage] file on sdcard is newer. loading from sdcard...");
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso.get().load(file).noFade().memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).into(imageView);
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
                            Picasso.get().load(file).noFade().memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).into(imageView);
                        }
                    }
                });


            }
        } else {
            Log.d(TAG, "[loadCachedImage] phone is offline. loading from sdcard...");
            Picasso.get().load(file).noFade().memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).into(imageView);

        }


        Log.d(TAG, "[loadCachedImage] loading completed.");
    }
*/
    public static void clearCache(Context context) {
        deleteDirectoryTree(context.getCacheDir());
    }
}
