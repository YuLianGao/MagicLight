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

public class DeviceGroupAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<DeviceItem> deviceList;

    public DeviceGroupAdapter(Context mContenxt, ArrayList<DeviceItem> device_list) {
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
       // if (convertView == null) {
            if(deviceList.get(position).IsGroup() == true)
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_bluetoothgroup, parent, false);
            else
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_bluetoothdevice, parent, false);
       // }

        if(deviceList.get(position).IsGroup() == true)  {
           TextView tvGroupName = (TextView) convertView.findViewById(R.id.item_group_id);
           tvGroupName.setText(deviceList.get(position).getName());
           TextView tvAddress = (TextView) convertView.findViewById(R.id.item_group_count);
           tvAddress.setText("0  devices(s)");
       }
       else
       {
           ImageView tvicon = (ImageView) convertView.findViewById(R.id.item_icon);
           TextView tvdeviceName = (TextView) convertView.findViewById(R.id.item_bluetoothdevice_tv_deviceid);
           tvdeviceName.setText(deviceList.get(position).getName());
           TextView tvAddress = (TextView) convertView.findViewById(R.id.item_bluetoothdevice_tv_uuid);
           tvAddress.setText("DiodeBT V6");
           tvicon.setX(70);
           tvdeviceName.setX(80);
           tvAddress.setX(80);
       }


        return convertView;
    }
}

