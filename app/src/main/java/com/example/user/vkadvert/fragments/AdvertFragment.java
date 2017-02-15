package com.example.user.vkadvert.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.user.vkadvert.MainActivity;
import com.example.user.vkadvert.R;
import com.example.user.vkadvert.adapters.MyAdapter;
import com.example.user.vkadvert.adapters.adsAdapter;
import com.example.user.vkadvert.common.BaseFragment;
import com.example.user.vkadvert.utils.LogUtil;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by user on 08.02.2017.
 */

public class AdvertFragment extends BaseFragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        initListView();
        initAds();
        initToolbar();
    }

    private void initAds() {
        ListView listView = (ListView) getAct().findViewById(R.id.advertList);
        VKRequest request = new VKRequest("ads.getAccounts");

        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                JSONObject list = response.json;
                ArrayList<JSONObject> lists = new ArrayList<JSONObject>();
                lists.add(list);
                ListAdapter adapter = new adsAdapter(getContext(),R.layout.adver_item,lists);

                listView.setAdapter(adapter);
            }
        });
    }
    private void initListView() {
        ListView listView = (ListView) getAct().findViewById(R.id.advertList);

        VKRequest request = VKApi.friends().get(VKParameters.from(VKApiConst.FIELDS,"first_name,last_name"));
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                VKList list = (VKList) response.parsedModel;

                ListAdapter adapter = new MyAdapter(getContext(),R.layout.adver_item,list);

                // присваиваем адаптер списку
                listView.setAdapter(adapter);

            }
        });
    }

    private void initToolbar(){
        Toolbar toolbar = (Toolbar) getAct().findViewById(R.id.toolbar);
        getAct().setSupportActionBar(toolbar);
        getAct().getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        new Drawer()
                .withActivity(getAct())
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.txt_drawer_rooms),
                        new PrimaryDrawerItem().withName(R.string.txt_drawer_condition),
                        new PrimaryDrawerItem().withName(R.string.txt_drawer_aboutApp),
                        new PrimaryDrawerItem().withName(""),
                        new PrimaryDrawerItem().withName(""),
                        new PrimaryDrawerItem().withName(""),
                        new PrimaryDrawerItem().withName(R.string.txt_drawer_contacts),
                        new PrimaryDrawerItem().withName(R.string.txt_drawer_exit)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    // Обработка клика
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        if (drawerItem instanceof Nameable) {
                            if(position==0){
                                LogUtil.info("0","кабинеты");
                            }
                            if(position==1){
                                LogUtil.info("1","условия");
                            }
                            if(position==2){
                                LogUtil.info("2","О приложении");
                            }
                            if(position==6){
                                LogUtil.info("6","контакты");
                            }
                            if(position==7){
                                LogUtil.info("7","выход");
                            }
                        }
                    }
                })

                .build();
    }

    @Override
    public int getLayout() {

        return R.layout.advert_layout;
    }

    @Override
    public MainActivity getAct() {
        return (MainActivity) getActivity();
    }
}
