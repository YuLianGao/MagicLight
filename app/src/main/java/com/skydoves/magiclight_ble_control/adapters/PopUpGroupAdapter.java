package com.skydoves.magiclight_ble_control.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.skydoves.magiclight_ble_control.Lists.ColorBox;
import com.skydoves.magiclight_ble_control.R;
import com.skydoves.magiclight_ble_control.model.DeviceItem;

import java.util.ArrayList;

public class PopUpGroupAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<DeviceItem> deviceList;

    public PopUpGroupAdapter(Context mContenxt, ArrayList<DeviceItem> device_list) {
        this.mContext = mContenxt;
        this.deviceList = device_list;
    }

    @Override
    public int getCount() {
        return deviceList.size();
    }

    @Override
    public Object getItem(int position) {
        return deviceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_popup_group, parent, false);

            TextView tvGroupName = convertView.findViewById(R.id.item_group_id);
            tvGroupName.setText(deviceList.get(position).getName());
            return convertView;
        }
        return  convertView;
    }
}

