package com.ooftf.service.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by master on 2017/1/19.
 */

public class NetworkUtil {

    /**
     * 判断网络是否可用
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }

    /**
     * 获取当前网络类型
     */
    public static NetworkType getNetworkType(Context context) {
        NetworkType netType = NetworkType.NETWORK_NONE;
        ConnectivityManager cm = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                netType = NetworkType.NETWORK_WIFI;
            } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                netType = NetworkType.NETWORK_MOBILE;
            }
        }
        return netType;
    }

    public enum NetworkType {
        //wifi网络
        NETWORK_WIFI,
        //移动网络
        NETWORK_MOBILE,
        //没有网络
        NETWORK_NONE
    }
}
