package com.example.user.vkadvert.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import com.example.user.vkadvert.MainActivity;
import com.example.user.vkadvert.R;
import com.example.user.vkadvert.common.BaseActivity;
import com.example.user.vkadvert.common.BaseFragment;
import com.example.user.vkadvert.model.AdvertItem;
import com.example.user.vkadvert.utils.LogUtil;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Badgeable;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKList;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by user on 08.02.2017.
 */

public class AdvertFragment extends BaseFragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initListView();
        initToolbar();
    }

    private void initListView() {
        ListView listView = (ListView) getAct().findViewById(R.id.advertList);
        VKRequest request = new VKApi.friends().get(VKParameters.from(VKApiConst.FIELDS,"first_name,last_name"));
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                VKList list = (VKList) response.parsedModel;
            }
        });

        ArrayList<HashMap<String, String>> myArrList = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map;

        map = new HashMap<String, String>();
        map.put("Name", "id: 1234");
        map.put("Tel", "Balance: 545р");
        myArrList.add(map);

        map = new HashMap<String, String>();
        map.put("Name", "id: 1234");
        map.put("Tel", "Balance: 845р");
        myArrList.add(map);

        map = new HashMap<String, String>();
        map.put("Name", "id: 1234");
        map.put("Tel", "Balance: 468р");
        myArrList.add(map);


        SimpleAdapter adapter = new SimpleAdapter(getContext(), myArrList, R.layout.adver_item,
                new String[] {"Name", "Tel"},
                new int[] {R.id.txtAcc_Id, R.id.txtAcc_balance});

        // присваиваем адаптер списку
        listView.setAdapter(adapter);
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
