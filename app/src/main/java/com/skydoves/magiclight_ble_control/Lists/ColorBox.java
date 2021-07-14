package com.skydoves.magiclight_ble_control.Lists;

public class ColorBox {
    private String[] colorname;
    private String address = "111";

    public ColorBox() {
        this.colorname = colorname;
        this.address = address;
    }

    public ColorBox(String[] colorname) {
        this.colorname = colorname;
    }

    public String[] getColors() {
        return colorname;
    }

    public String getAddress() {
        return address;
    }
}
