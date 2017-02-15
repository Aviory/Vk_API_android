package com.example.user.vkadvert;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.example.user.vkadvert.common.BaseActivity;

import com.example.user.vkadvert.fragments.AdvertFragment;
import com.example.user.vkadvert.fragments.MainFragment;
import com.example.user.vkadvert.utils.LogUtil;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.model.VKScopes;
import com.vk.sdk.util.VKUtil;

import java.util.Arrays;

public class MainActivity extends BaseActivity {

    private String[] scope = new String[]{VKScopes.FRIENDS, VKScopes.ADS, VKScopes.OFFLINE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        VKSdk.login(this, scope);


//        addFragment(getAct(), MainFragment.class, R.id.coordinator_layout,null, false, false, true );
//        addFragment(getAct(), AdvertFragment.class, R.id.coordinator_layout,null, false, false, true );

//        String[] fingerprints = VKUtil.getCertificateFingerprint(this, this.getPackageName());
//        Log.d("key: " , Arrays.asList(fingerprints).toString());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                // Пользователь успешно авторизовался
//                Toast.makeText(getApplicationContext(),"Good",Toast.LENGTH_LONG).show();
                addFragment(getAct(), AdvertFragment.class, R.id.coordinator_layout,null, false, false, true );

            }
            @Override
            public void onError(VKError error) {
                // Произошла ошибка авторизации (например, пользователь запретил авторизацию)
                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public int getLayout() {
        return R.layout.coordinator_layout;
    }
}
