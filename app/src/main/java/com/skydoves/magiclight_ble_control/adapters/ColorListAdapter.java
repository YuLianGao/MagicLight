package com.skydoves.magiclight_ble_control.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.skydoves.magiclight_ble_control.Lists.ColorBox;
import com.skydoves.magiclight_ble_control.R;

import java.util.ArrayList;

public class ColorListAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<ColorBox> zonelist;

    public ColorListAdapter(Context mContenxt, ArrayList<ColorBox> zone_list) {
        this.mContext = mContenxt;
        this.zonelist = zone_list;
    }

    @Override
    public int getCount() {
        return zonelist.size();
    }

    @Override
    public Object getItem(int position) {
        return zonelist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.mode_list, parent, false);
        }
        TextView tvTitle = (TextView) convertView.findViewById(R.id.template_title);
        TextView tvAddress = (TextView) convertView.findViewById(R.id.template_method);
        TextView mode1, mode2, mode3, mode4, mode5, mode6, mode7, mode8, mode9, mode10, mode11, mode12, mode13, mode14, mode15, mode16;
        mode1 = (TextView) convertView.findViewById(R.id.mode1);
        mode2 = (TextView) convertView.findViewById(R.id.mode2);
        mode3 = (TextView) convertView.findViewById(R.id.mode3);
        mode4 = (TextView) convertView.findViewById(R.id.mode4);
        mode5 = (TextView) convertView.findViewById(R.id.mode5);
        mode6 = (TextView) convertView.findViewById(R.id.mode6);
        mode7 = (TextView) convertView.findViewById(R.id.mode7);
        mode8 = (TextView) convertView.findViewById(R.id.mode8);
        mode9 = (TextView) convertView.findViewById(R.id.mode9);
        mode10 = (TextView) convertView.findViewById(R.id.mode10);
        mode11 = (TextView) convertView.findViewById(R.id.mode11);
        mode12 = (TextView) convertView.findViewById(R.id.mode12);
        mode13 = (TextView) convertView.findViewById(R.id.mode13);
        mode14 = (TextView) convertView.findViewById(R.id.mode14);
        mode15 = (TextView) convertView.findViewById(R.id.mode15);
        mode16 = (TextView) convertView.findViewById(R.id.mode16);

        String[] str = new String[16];
        str = zonelist.get(position).getColors();

        mode1.setBackgroundColor(Color.parseColor("#55000000"));
        mode2.setBackgroundColor(Color.parseColor("#55000000"));
        mode3.setBackgroundColor(Color.parseColor("#55000000"));
        mode4.setBackgroundColor(Color.parseColor("#55000000"));
        mode5.setBackgroundColor(Color.parseColor("#55000000"));
        mode6.setBackgroundColor(Color.parseColor("#55000000"));
        mode7.setBackgroundColor(Color.parseColor("#55000000"));
        mode8.setBackgroundColor(Color.parseColor("#55000000"));
        mode9.setBackgroundColor(Color.parseColor("#55000000"));
        mode10.setBackgroundColor(Color.parseColor("#55000000"));
        mode11.setBackgroundColor(Color.parseColor("#55000000"));
        mode12.setBackgroundColor(Color.parseColor("#55000000"));
        mode13.setBackgroundColor(Color.parseColor("#55000000"));
        mode14.setBackgroundColor(Color.parseColor("#55000000"));
        mode15.setBackgroundColor(Color.parseColor("#55000000"));
        mode16.setBackgroundColor(Color.parseColor("#55000000"));
        int i;
        for (i = 0; i < str.length; i++) {
            if(str[i].equals("empty")) {
                continue;
            }
            if (i == 0 ) {
                mode1.setBackgroundColor(Color.parseColor(str[i]));
            } else if (i == 1) {
                mode2.setBackgroundColor(Color.parseColor(str[i]));
            } else if (i == 2) {
                mode3.setBackgroundColor(Color.parseColor(str[i]));
            } else if (i == 3) {
                mode4.setBackgroundColor(Color.parseColor(str[i]));
            } else if (i == 4) {
                mode5.setBackgroundColor(Color.parseColor(str[i]));
            } else if (i == 5) {
                mode6.setBackgroundColor(Color.parseColor(str[i]));
            } else if (i == 6) {
                mode7.setBackgroundColor(Color.parseColor(str[i]));
            } else if (i == 7) {
                mode8.setBackgroundColor(Color.parseColor(str[i]));
            } else if (i == 8) {
                mode9.setBackgroundColor(Color.parseColor(str[i]));
            } else if (i == 9) {
                mode10.setBackgroundColor(Color.parseColor(str[i]));
            } else if (i == 10) {
                mode11.setBackgroundColor(Color.parseColor(str[i]));
            } else if (i == 11) {
                mode12.setBackgroundColor(Color.parseColor(str[i]));
            } else if (i == 12) {
                mode13.setBackgroundColor(Color.parseColor(str[i]));
            } else if (i == 13) {
                mode14.setBackgroundColor(Color.parseColor(str[i]));
            } else if (i == 14) {
                mode15.setBackgroundColor(Color.parseColor(str[i]));
            } else if (i == 15) {
                mode16.setBackgroundColor(Color.parseColor(str[i]));
            }
        }


        tvTitle.setText(zonelist.get(position).getAddress());
        tvAddress.setText(zonelist.get(position).getAddress());
        return convertView;
    }
}

