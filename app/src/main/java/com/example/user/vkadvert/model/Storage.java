package com.example.user.vkadvert.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by user on 07.02.2017.
 */
public class Storage {

    private final static String FIRST_OPENING = "first_opening";

    private static Storage storage;
    private SharedPreferences sharedPreferences;

    public static Storage instance(Context context) {
        if (storage == null) {
            storage = new Storage(context);
        }
        return storage;
    }

    private Storage(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public boolean isFirstOpening() {
        long timeFirstStart = sharedPreferences.getLong(FIRST_OPENING, -1);
        return timeFirstStart<1;
    }

    public void setFirstOpening() {
        sharedPreferences.edit().putLong(FIRST_OPENING, System.currentTimeMillis()).commit();
    }
}
