package com.github.seyyedmojtaba72.android_utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by SeyyedMojtaba on 6/24/17.
 */

public class Network {
    private static final String TAG = Network.class.getSimpleName();

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
}
