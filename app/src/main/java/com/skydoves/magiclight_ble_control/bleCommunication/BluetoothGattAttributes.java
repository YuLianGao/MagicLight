package com.skydoves.magiclight_ble_control.bleCommunication;

import java.util.HashMap;

/**
 * Created by skydoves on 2017-07-01.
 */

public class BluetoothGattAttributes {
    private static HashMap<String, String> attributes = new HashMap<>();

    // services
    private static final String LED_POWER_SERVICE = "0000fff0-0000-1000-8000-00805f9b34fb";
    private static final String LED_SERVICE = "0000ffe5-0000-1000-8000-00805f9b34fb";

    // services => characteristics
    public static final String LED_CHARACTERISTIC = "0000ffe9-0000-1000-8000-00805f9b34fb";
   // public static final String LED_READ_CHARACTERISTIC = "0000ffea-0000-1000-8000-00805f9b34fb";
  //  public static final String LED_WRITE_CHARACTERISTIC = "0000ffeb-0000-1000-8000-00805f9b34fb";
    public static final String LED_READ_CHARACTERISTIC = "0000fff2-0000-1000-8000-00805f9b34fb";
    public static final String LED_WRITE_CHARACTERISTIC = "0000fff3-0000-1000-8000-00805f9b34fb";

    static {
        // services
        attributes.put(LED_SERVICE, "Led Service");
        attributes.put(LED_POWER_SERVICE, "Led Power Service");

        // characteristics
        attributes.put(LED_CHARACTERISTIC, "Led Characteristic");
        attributes.put(LED_READ_CHARACTERISTIC, "Read Characteristic");
        attributes.put(LED_WRITE_CHARACTERISTIC, "Write Characteristic");
    }

    public static String lookup(String uuid, String defaultName) {
        String name = attributes.get(uuid);
        return name == null ? defaultName : name;
    }
}