package com.example.user.vkadvert.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.vkadvert.MainActivity;
import com.example.user.vkadvert.R;
import com.example.user.vkadvert.common.BaseActivity;
import com.example.user.vkadvert.common.BaseFragment;
import com.example.user.vkadvert.utils.LogUtil;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKAccessTokenTracker;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.model.VKScopes;

/**
 * Created by user on 08.02.2017.
 */

public class MainFragment extends BaseFragment {

    @Override
    public int getLayout() {
        return R.layout.login_layout;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public MainActivity getAct() {
        return (MainActivity) getActivity();
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if(view.getId() == R.id.btn_login){
            VKSdk.login(getAct(), "login");
        }
    }
}