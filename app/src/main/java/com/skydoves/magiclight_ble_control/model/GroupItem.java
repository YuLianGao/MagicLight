package com.skydoves.magiclight_ble_control.model;

import java.util.ArrayList;

/**
 * Created by skydoves on 2017-07-01.
 */

public class GroupItem {
    private String GroupName;
    private String deviceAddress;
    private boolean isGroup;
    private int ndeviceCount;
    private ArrayList<DeviceItem> deviceItems;

    public String getName(){return GroupName;}
    public String getAddress(){return deviceAddress;}
    public boolean IsGroup(){return isGroup;}
    public int getDeviceCount(){return ndeviceCount;}
    public void setDeviceCount(int count)
    {
        ndeviceCount = count;
    }

    public GroupItem(String date, String content, boolean isParent){
        this.GroupName=date;
        this.deviceAddress=content;
        this.isGroup = isParent;
    }
}
