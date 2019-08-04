package com.bai.psychedelic.cloudmusic.util;

import android.util.Log;

import com.bai.psychedelic.cloudmusic.BuildConfig;

public class MyLog {
    private static boolean DEBUG = BuildConfig.DEBUG;
    public static void d(String tag,String content){
        Log.d(tag,content);
    }
    public static void e(String tag,String content){
        Log.e(tag,content);
    }
}
