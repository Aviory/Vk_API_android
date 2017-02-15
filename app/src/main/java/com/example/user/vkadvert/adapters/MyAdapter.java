package com.example.user.vkadvert.adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.user.vkadvert.R;
import com.vk.sdk.api.model.VKApiUserFull;
import com.vk.sdk.api.model.VKList;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by PC on 13-Feb-17.
 */
public class MyAdapter extends ArrayAdapter {
    private Context mContext;
    List<VKApiUserFull> list;
    int res;

    public MyAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        list = objects;
        mContext = context;
        res = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VKApiUserFull qq = (VKApiUserFull) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(res, parent, false);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.txtAcc_Id);
        tv.setText(qq.getId());

        TextView tvv = (TextView) convertView.findViewById(R.id.txtAcc_balance);
        tvv.setText(qq.last_name);
        return convertView;
    }
}