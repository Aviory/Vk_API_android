package com.example.user.vkadvert.utils;

import android.util.Log;

/**
 * Created by user on 07.02.2017.
 */
public class LogUtil {
    public static void info(Object  object, String ms){
        Log.d(object.getClass().getName(),ms);
    }
}
