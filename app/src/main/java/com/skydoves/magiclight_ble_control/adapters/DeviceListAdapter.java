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

public class DeviceListAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<DeviceItem> deviceList;

    public DeviceListAdapter(Context mContenxt, ArrayList<DeviceItem> device_list) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_bluetoothdevice, parent, false);
        }
        ImageView imageIcon = (ImageView) convertView.findViewById(R.id.item_icon);
        TextView tvTitle = (TextView) convertView.findViewById(R.id.item_bluetoothdevice_tv_deviceid);
        TextView tvAddress = (TextView) convertView.findViewById(R.id.item_bluetoothdevice_tv_uuid);
        tvTitle.setText(deviceList.get(position).getName());
        tvAddress.setText(deviceList.get(position).getAddress());
        if(deviceList.get(position).getAddress().substring(0,4).equals("chil")) {
            imageIcon.setX(70);
            tvTitle.setX(80);
            tvAddress.setX(80);
        } else {
            imageIcon.setX(10);
            tvTitle.setX(20);
            tvAddress.setX(20);
        }
        return convertView;
    }
}

