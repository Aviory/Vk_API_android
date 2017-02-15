package com.example.user.vkadvert.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.user.vkadvert.R;
import com.vk.sdk.api.model.VKApiUserFull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by PC on 15-Feb-17.
 */

public class adsAdapter extends ArrayAdapter {
    private Context mContext;
    int res;


    public adsAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        mContext = context;
        res = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        JSONObject qq = (JSONObject) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(res, parent, false);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.txtAcc_Id);
        try {
            JSONArray ss = qq.getJSONArray("response");
            tv.setText("id: "+ss.getJSONObject(0).getString("account_id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView tvv = (TextView) convertView.findViewById(R.id.txtAcc_balance);
        tvv.setText("Баланс: 384 руб");
        return convertView;
    }
}
