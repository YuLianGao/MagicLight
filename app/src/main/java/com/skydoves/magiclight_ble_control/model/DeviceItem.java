package com.skydoves.magiclight_ble_control.model;

/**
 * Created by skydoves on 2017-07-01.
 */

public class DeviceItem {
    private String deivceName;
    private String deviceAddress;
    private boolean isGroup;

    public String getName(){return deivceName;}
    public String getAddress(){return deviceAddress;}
    public boolean IsGroup(){return isGroup;}

    public DeviceItem(String date, String content, boolean isParent){
        this.deivceName=date;
        this.deviceAddress=content;
        this.isGroup = isParent;
    }
}
