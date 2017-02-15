package com.example.user.vkadvert;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.example.user.vkadvert.utils.LogUtil;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKAccessTokenTracker;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

/**
 * Created by PC on 10-Feb-17.
 */

public class VkInit extends android.app.Application {
    private static final String APP_PREFERENCES_TOKEN = "vklogin";
    SharedPreferences mySharedPreferences;

    VKAccessTokenTracker vkAccessTokenTracker = new VKAccessTokenTracker() {
        @Override
        public void onVKAccessTokenChanged(VKAccessToken oldToken, VKAccessToken newToken) {
            if (newToken == null) {
                // VKAccessToken is invalid
                Intent intent = new Intent(VkInit.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
             else {
                LogUtil.info(this, "VKAccessToken is valid");
                newToken.saveTokenToSharedPreferences(getApplicationContext(),newToken.accessToken);
//                SharedPreferences.Editor editor = mySharedPreferences.edit();
//                editor.putString(APP_PREFERENCES_TOKEN, newToken.accessToken );
//                editor.apply();
            }
        }
    };
    @Override
    public void onCreate() {
        super.onCreate();

        mySharedPreferences = getSharedPreferences(APP_PREFERENCES_TOKEN, Context.MODE_PRIVATE);

//        if(mySharedPreferences.contains(APP_PREFERENCES_TOKEN)) {
//            String oldTok = mySharedPreferences.getString(APP_PREFERENCES_TOKEN, "");
//       }
        vkAccessTokenTracker.startTracking();
        VKSdk.initialize(this);

    }
}
