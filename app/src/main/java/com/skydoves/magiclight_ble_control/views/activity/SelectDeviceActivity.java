package com.skydoves.magiclight_ble_control.views.activity;

import android.Manifest;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.skydoves.magiclight_ble_control.Lists.ColorBox;
import com.skydoves.magiclight_ble_control.MyColorPickerView1;
import com.skydoves.magiclight_ble_control.adapters.DeviceGroupAdapter;
import com.skydoves.magiclight_ble_control.adapters.DeviceListAdapter;
import com.skydoves.magiclight_ble_control.adapters.PopUpGroupAdapter;
import com.skydoves.magiclight_ble_control.main.HomePageTabActivity;
import com.skydoves.magiclight_ble_control.model.DBHelper;
import com.skydoves.magiclight_ble_control.model.DeviceItem;
import com.skydoves.magiclight_ble_control.otto.BusProvider;
import com.skydoves.magiclight_ble_control.otto.DeviceChangedEvent;
import com.skydoves.magiclight_ble_control.R;
import com.skydoves.magiclight_ble_control.data.DeviceInfoManager;
import com.skydoves.magiclight_ble_control.views.adapter.LeDeviceListAdapter;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.skydoves.magiclight_ble_control.ColorModeActivity.GetStringArray;

/**
 * Created by skydoves on 2017-07-01.
 */

public class SelectDeviceActivity extends Activity {

    public boolean mScanning;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothLeScanner bluetoothLeScanner;
    private LeDeviceListAdapter mLeDeviceListAdapter;
    private DeviceListAdapter mDeviceListAdapter;
    private DeviceGroupAdapter mGroupListAdapter;
//    private ArrayList<String> deviceList;
    private ArrayList<DeviceItem> deviceList;
    private ArrayList<DeviceItem> groupList;
    private static final long SCAN_PERIOD = 5000;
    private static final int REQUEST_ENABLE_BT = 10000;
    private static final int REQUEST_WRITE_STORAGE = 8000;
    private Handler mHandler;
    private ImageView ivRefresh;
    private Dialog dialog;
    DBHelper mydb;
    String deviceNmae = "";

    @BindView(R.id.bluetoothlist_listview_devices)
    ListView listView_BluetoothList;

    @BindView(R.id.group_listview)
    ListView listView_groupList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bluetoothlist);
        this.setFinishOnTouchOutside(false);
        ButterKnife.bind(this);
        requestPermission();
        mHandler = new Handler();
        // indicate scanning in the title
        TextView status = (TextView) findViewById(R.id.bluetoothlist_status);
        status.setText(R.string.app_name);

        // initialize bluetooth manager & adapter

        final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();

        if(mBluetoothAdapter == null)
        {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("This device not supported for bluetooth!")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, final int id) {
                            startActivity(new Intent(Settings.ACTION_BLUETOOTH_SETTINGS));
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, final int id) {
                            dialog.cancel();
                            finish();
                            System.exit(0);
                        }
                    });
            final AlertDialog alert = builder.create();
            alert.show();
            return;
        }

        else {
            bluetoothLeScanner = mBluetoothAdapter.getBluetoothLeScanner();

            // if bluetooth is not currently enabled,
            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            } else {
                // initialize list view adapter
                deviceList = new ArrayList();
                mLeDeviceListAdapter = new LeDeviceListAdapter(this, R.layout.item_bluetoothdevice);
                listView_BluetoothList.setAdapter(mLeDeviceListAdapter);
                listView_BluetoothList.setOnItemClickListener(new ListViewItemClickListener());
                scanLeDevice(true);
            }


            ivRefresh = (ImageView) findViewById(R.id.bluetoothlist_progressBar);
            ivRefresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    OnRefreshDeviceList();
                }
            });


            mydb = new DBHelper(this);

            Button addGroupBtn = findViewById(R.id.add_group_btn);
            addGroupBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popup_dialog();
                }
            });
            listView_BluetoothList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    DeviceItem listitem = deviceList.get(position);
                    deviceNmae = deviceList.get(position).getName();
                    popup_dialog1(listitem.getName());
                    return true;
                }
            });
            listView_groupList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    DeviceItem listitem = groupList.get(position);
                    deviceNmae = listitem.getName();
                    if (deviceNmae.matches("Diode.*"))
                        popup_dialog_child(groupList.get(position - 1).getName(), listitem.getName());
                    else {
                        if (listitem.getAddress().substring(0, 4).equals("Chil")) {
                            popup_dialog_child(groupList.get(position - 1).getName(), listitem.getName());
                        } else {
                            popup_dialog2(listitem.getName());
                        }
                    }
                    return true;
                }
            });

            //onLoadGroupList();

            Button btHelp = findViewById(R.id.app_help);
            btHelp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent;
                    intent = new Intent(SelectDeviceActivity.this, HelpActivity.class);
                    startActivity(intent);
                }
            });
        }

    }

    private void OnRefreshDeviceList()
    {
        deviceList = null; mHandler = null;
        groupList = null;
        deviceList = new ArrayList();
        groupList = new ArrayList<>();
        mHandler = new Handler();
        mLeDeviceListAdapter.clear();
        listView_BluetoothList.setAdapter(mLeDeviceListAdapter);
        scanLeDevice(false);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scanLeDevice(true);
        mGroupListAdapter = new DeviceGroupAdapter(getApplication(),groupList);
        listView_groupList.setAdapter(mGroupListAdapter);
    }

    private void onLoadGroupList()
    {
        groupList = new ArrayList<>();
        ArrayList groupnames = mydb.getGroupName();
        String[] str = GetStringArray(groupnames);
        for(int i=0; i<str.length; i++) {
            groupList.add(new DeviceItem(str[i], "Address "+String.valueOf(i), true));
        }
        mGroupListAdapter = new DeviceGroupAdapter(this,groupList);
        listView_groupList.setAdapter(mGroupListAdapter);
        listView_groupList.setOnItemClickListener(new ListGroupViewItemClickListener());
        listView_groupList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DeviceItem listitem = groupList.get(position);
                if(listitem.getAddress().substring(0,4).equals("Chil")){
                    popup_dialog_child(groupList.get(position-1).getName(),listitem.getName());
                } else {
                    popup_dialog2(listitem.getName());
                }
                return true;
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        onSearchGroupList();
    }

    private void onSearchGroupList()
    {
        groupList = new ArrayList<>();
        ArrayList groupnames = mydb.getGroupName();
        ArrayList groupParents = mydb.getGroupParentName();
        ArrayList groupChilds = mydb.getGroupChildName();

        String[] str = GetStringArray(groupnames);
        String[] str_parents = GetStringArray(groupParents);
        String[] str_childs = GetStringArray(groupChilds);
        int i,j;
        for(i=0; i<str.length; i++) {
            groupList.add(new DeviceItem(str[i], "Parent "+String.valueOf(i), true));
            for(j=0; j<str_parents.length; j++) {
                if(str[i].equals(str_parents[j])) {
                    groupList.add(new DeviceItem(str_childs[j], "child", false));
                    listView_BluetoothList.setVisibility(View.GONE);
                }
            }
        }

        // mGroupListAdapter = new DeviceListAdapter(this,groupList);
       // mGroupListAdapter = new DeviceGroupAdapter(this,groupList);
       // listView_groupList.setAdapter(mGroupListAdapter);

        listView_groupList.setOnItemClickListener(new ListGroupViewItemClickListener());
    }
    private void requestPermission() {
        statusCheck();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_ADMIN) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, R.string.permission_request, Toast.LENGTH_LONG);
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.BLUETOOTH,
                            Manifest.permission.BLUETOOTH_ADMIN,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.RECORD_AUDIO,
                            Manifest.permission.CAMERA,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_WRITE_STORAGE);
        }
    }

    public void statusCheck() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your nearby location seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("Deny", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                        finish();
                        System.exit(0);
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
    /**
     * scan Le devices
     * @param enable
     */
    //region
    private void scanLeDevice(final boolean enable) {
        if (enable) {
            // stops scanning after a pre-defined scan period.
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mScanning = false;
                    bluetoothLeScanner.stopScan(mScanCallback);
                }
            }, SCAN_PERIOD);

            mScanning = true;
            bluetoothLeScanner.startScan(mScanCallback);
        } else {
            mScanning = false;
            bluetoothLeScanner.stopScan(mScanCallback);
        }
    }

    /**
     * scan result call back
     */
    private ScanCallback mScanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            processResult(result);
        }

        @Override
        public void onBatchScanResults(List<ScanResult> results) {
            for (ScanResult result : results) {
                processResult(result);
            }
        }

        @Override
        public void onScanFailed(int errorCode) {
            Toast.makeText(getApplicationContext(), R.string.ble_not_find, Toast.LENGTH_SHORT).show();
        }

        private void processResult(final ScanResult result) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String deviceName = result.getScanRecord().getDeviceName();
                    if(deviceName != null /*&& deviceName.matches("LEDBlue.*") */&& !deviceList.contains(deviceName)) {
                        TextView tvView = findViewById(R.id.tv_ungrouped);
                        TextView tvdevice = findViewById(R.id.tv_nodevice);
                        tvView.setVisibility(View.VISIBLE);
                        tvdevice.setVisibility(View.GONE);

                        deviceName = deviceName.replace("LEDBlue", "Diode");
                        mLeDeviceListAdapter.addDevice(result.getDevice());
                        deviceList.add(new DeviceItem(deviceName, "child", false));
                        onSearchGroupList();
                        mGroupListAdapter = new DeviceGroupAdapter(getApplication(),groupList);

                        listView_groupList.setAdapter(mGroupListAdapter);
                    }
                }
            });
        }
    };
    //endregion

    /**
     * listViewItem click listener
     */
    private class ListViewItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View clickedView, int pos, long id) {
            BluetoothDevice device = mLeDeviceListAdapter.getDevice(pos);
            if (device == null) return;

            DeviceInfoManager manager = DeviceInfoManager.getInstance();
            manager.setDeviceInfo(device);
            BusProvider.getInstance().post(new DeviceChangedEvent());


           // Toast.makeText(getBaseContext(), R.string.ble_selected, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getBaseContext(), com.skydoves.magiclight_ble_control.main.HomePageTabActivity.class);
            String title = mLeDeviceListAdapter.getDevice(pos).getName().replace("LEDBlue", "Diode");
            intent.putExtra("device_name",title );
            startActivity(intent);

        }
    };
    private class ListGroupViewItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View clickedView, int pos, long id) {
            BluetoothDevice device;
            if(groupList.get(pos).getName().matches("Diode.*"))
                 device = mLeDeviceListAdapter.getDevice(0);
            else
                device = null;

            if (device == null) return;

            DeviceInfoManager manager = DeviceInfoManager.getInstance();
            manager.setDeviceInfo(device);
            BusProvider.getInstance().post(new DeviceChangedEvent());


           // Toast.makeText(getBaseContext(), R.string.ble_selected, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getBaseContext(), com.skydoves.magiclight_ble_control.main.HomePageTabActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            String title = mLeDeviceListAdapter.getDevice(0).getName().replace("LEDBlue", "Diode");
            intent.putExtra("device_name",title );
            startActivity(intent);

               }
    };
    /**
     * closed activity with onBackPressed
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, R.string.ble_canceldfind, Toast.LENGTH_SHORT).show();
    }

    /**
     * onActivityResult, request ble system enable
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // user chose not to enable bluetooth.
        if (requestCode == REQUEST_ENABLE_BT && resultCode == Activity.RESULT_CANCELED) {
            Toast.makeText(this, R.string.ble_canceled, Toast.LENGTH_SHORT).show();
           // finish();
            return;
        }
        // user chose enable bluetooth
        else if(requestCode == REQUEST_ENABLE_BT && resultCode == Activity.RESULT_OK){
            finish();
            Intent intent = new Intent(this, SelectDeviceActivity.class);
            startActivity(intent);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void popup_dialog() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_addgroup);

        TextView cancelbtn, confirmbtn;
        EditText newGroupName;

        newGroupName = dialog.findViewById(R.id.editText_groupName);
        cancelbtn = dialog.findViewById(R.id.btn_cancel);
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        confirmbtn = dialog.findViewById(R.id.btn_confirm);
        confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydb.insertGroup(newGroupName.getText().toString());
                dialog.dismiss();
                onResume();
                OnRefreshDeviceList();
            }
        });
        dialog.show();
    }

    public void popup_dialog1(String renamedDevice) {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_popup);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);

        TextView addToGroupBtn = dialog.findViewById(R.id.addToGroupBtn);
        TextView reNameBtn = dialog.findViewById(R.id.reNameBtn);
        TextView cancelBtn = dialog.findViewById(R.id.cancelDialogBtn);

        addToGroupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                popup_dialog_addToGroup();
            }
        });
        reNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                popup_dialog_rename(renamedDevice);
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }

    public void popup_dialog2(String deletableGroupName) {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_popup2);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);

        TextView deleteGroupBtn = dialog.findViewById(R.id.deleteGroupBtn);
        TextView reNameBtn = dialog.findViewById(R.id.reNameBtn);
        TextView cancelBtn = dialog.findViewById(R.id.cancelDialogBtn);

        deleteGroupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydb.deleteGroup(deletableGroupName);
                onResume();
                dialog.dismiss();
            }
        });
        reNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                popup_dialog_rename1(deletableGroupName);
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }

    public void popup_dialog_child(String parentName, String deletableGroupName) {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_popup_child);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);

        TextView deleteFromGroupBtn = dialog.findViewById(R.id.deleteFromGroupBtn);
        TextView moveToGroup = dialog.findViewById(R.id.moveToGroupBtn);
        TextView reNameBtn = dialog.findViewById(R.id.reNameBtn);
        TextView cancelBtn = dialog.findViewById(R.id.cancelDialogBtn);

        deleteFromGroupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydb.deleteFromGroup(parentName, deletableGroupName);
                onResume();
                dialog.dismiss();
                listView_BluetoothList.setVisibility(View.VISIBLE);
                OnRefreshDeviceList();
            }
        });
        moveToGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                popup_dialog_addToGroup_afterRemove(parentName);
            }
        });
        reNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                popup_dialog_rename_child(deletableGroupName);
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }

    public void popup_dialog_addToGroup() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_addtogroup);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);

        ListView listGroupName = dialog.findViewById(R.id.list_groupName);

        groupList = new ArrayList<>();
        ArrayList groupnames = mydb.getGroupName();
        String[] str = GetStringArray(groupnames);
        for(int i=0; i<str.length; i++) {
            groupList.add(new DeviceItem(str[i], "child "+String.valueOf(i), true));
        }
        PopUpGroupAdapter GroupListAdapter = new PopUpGroupAdapter(this,groupList);
        listGroupName.setAdapter(GroupListAdapter);


        listGroupName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DeviceItem listitem = groupList.get(position);
                mydb.insertChildGroup(listitem.getName(),deviceNmae);
                listView_BluetoothList.setVisibility(View.GONE);
                onResume();
                dialog.dismiss();
                OnRefreshDeviceList();

            }
        });


        TextView cancelBtn = dialog.findViewById(R.id.cancelDialogBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }

    public void popup_dialog_addToGroup_afterRemove(String parentName) {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_addtogroup);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);

        ListView listGroupName = dialog.findViewById(R.id.list_groupName);

        groupList = new ArrayList<>();
        ArrayList groupnames = mydb.getGroupName();
        String[] str = GetStringArray(groupnames);
        for(int i=0; i<str.length; i++) {
            groupList.add(new DeviceItem(str[i], "child "+String.valueOf(i), false));
        }
        PopUpGroupAdapter groupListAdapter = new PopUpGroupAdapter(this,groupList);
        listGroupName.setAdapter(groupListAdapter);


        listGroupName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DeviceItem listitem = groupList.get(position);
                mydb.insertChildGroup(listitem.getName(),deviceList.get(position).getName());
                listView_BluetoothList.setVisibility(View.GONE);
                mydb.deleteFromGroup(parentName, deviceNmae);
                onResume();
                dialog.dismiss();
                OnRefreshDeviceList();

            }
        });


        TextView cancelBtn = dialog.findViewById(R.id.cancelDialogBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }

    public void popup_dialog_rename(String deletedDevice) {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_rename);
        EditText editTextRename = dialog.findViewById(R.id.editText_rename);
        editTextRename.setText(deletedDevice);
        TextView cancelBtn, confirmBtn;
        cancelBtn = dialog.findViewById(R.id.btn_cancel);
        confirmBtn = dialog.findViewById(R.id.btn_confirm);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deviceList = new ArrayList<>();
                deviceList.add(new DeviceItem(editTextRename.getText().toString(), "Changed Name1", false));
                mDeviceListAdapter = new DeviceListAdapter(getApplicationContext(),deviceList);
                listView_BluetoothList.setAdapter(mDeviceListAdapter);
                deviceNmae = editTextRename.getText().toString();
                OnRefreshDeviceList();
                dialog.dismiss();

            }
        });

        dialog.show();
    }

    public void popup_dialog_rename1(String deletableGroupName) {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_rename);
        EditText editTextRename = dialog.findViewById(R.id.editText_rename);
        editTextRename.setText(deletableGroupName);
        TextView cancelBtn, confirmBtn;
        cancelBtn = dialog.findViewById(R.id.btn_cancel);
        confirmBtn = dialog.findViewById(R.id.btn_confirm);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                groupList = new ArrayList<>();
                ArrayList groupnames = mydb.getGroupName();
                String[] str = GetStringArray(groupnames);
                int i,j;
                for(i=0; i<str.length; i++) {
                   if(str[i].equals(deletableGroupName)) {
                       mydb.updateGroup(deletableGroupName,editTextRename.getText().toString());
                   }
                }

                mGroupListAdapter = new DeviceGroupAdapter(getApplicationContext(),groupList);
                listView_groupList.setAdapter(mGroupListAdapter);

                dialog.dismiss();
                onResume();
                OnRefreshDeviceList();
            }
        });

        dialog.show();
    }

    public void popup_dialog_rename_child(String childname) {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_rename);
        EditText editTextRename = dialog.findViewById(R.id.editText_rename);
        editTextRename.setText(childname);
        TextView cancelBtn, confirmBtn;
        cancelBtn = dialog.findViewById(R.id.btn_cancel);
        confirmBtn = dialog.findViewById(R.id.btn_confirm);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deviceList = new ArrayList<>();
                deviceList.add(new DeviceItem(editTextRename.getText().toString(), "Changed Name1", false));
                mDeviceListAdapter = new DeviceListAdapter(getApplicationContext(),deviceList);
                listView_BluetoothList.setAdapter(mDeviceListAdapter);
                deviceNmae = editTextRename.getText().toString();

                mydb.updateChild(childname,editTextRename.getText().toString());


                dialog.dismiss();
                onResume();

                OnRefreshDeviceList();
            }
        });

        dialog.show();
    }

}
