package com.skydoves.magiclight_ble_control.main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.skydoves.magiclight_ble_control.CameraColorPickerPreview;
import com.skydoves.magiclight_ble_control.Cameras;
import com.skydoves.magiclight_ble_control.R;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.skydoves.magiclight_ble_control.bleCommunication.BluetoothGattAttributes;
import com.skydoves.magiclight_ble_control.bleCommunication.BluetoothLeService;
import com.skydoves.magiclight_ble_control.data.DeviceInfoManager;
import com.skydoves.magiclight_ble_control.fragment.CameraFragment;
import com.skydoves.magiclight_ble_control.fragment.ColorFragment;
import com.skydoves.magiclight_ble_control.fragment.CustomFragment;
import com.skydoves.magiclight_ble_control.fragment.MusicFragment;
import com.skydoves.magiclight_ble_control.fragment.PatternFragment;
import com.skydoves.magiclight_ble_control.otto.BusProvider;
import com.skydoves.magiclight_ble_control.otto.DeviceChangedEvent;
import com.skydoves.magiclight_ble_control.views.activity.SelectDeviceActivity;
import com.squareup.otto.Subscribe;

import	android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;

import butterknife.ButterKnife;


public class HomePageTabActivity extends AppCompatActivity implements CameraColorPickerPreview.OnColorSelectedListener{

    final Fragment colorFragment = new ColorFragment();
    final Fragment syncFragment = new MusicFragment();
    final Fragment cameraFragment = new CameraFragment();
    final Fragment patternFragment = new PatternFragment();
    final Fragment customFragment = new CustomFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = colorFragment;
    private Switch switchAB;
    public int flag_camera = 0;
    private static final int REQUEST_WRITE_STORAGE = 8000;
    private static final int RESULT_LOAD_IMAGE = 8001;
    private static boolean mConnected = false;
    private Switch actionView;

    private static BluetoothLeService mBluetoothLeService;
    private boolean isChecked = false;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        RelativeLayout rlCamera = findViewById(R.id.rl_cameraPage);
        FrameLayout flMain = findViewById(R.id.main_container);
        CameraAsyncTask mCameraAsyncTask = new CameraAsyncTask();

        switch (item.getItemId()) {
            case R.id.navigation_color:
                if(flag_camera==1) {onPause(); flag_camera = 0;}
                rlCamera.setVisibility(View.GONE);
                flMain.setVisibility(View.VISIBLE);

                fm.beginTransaction().hide(syncFragment).commit();
                fm.beginTransaction().hide(patternFragment).commit();
                fm.beginTransaction().hide(customFragment).commit();
                fm.beginTransaction().hide(cameraFragment).commit();
                fm.beginTransaction().hide(active).show(colorFragment).commit();
                active = colorFragment;
                return true;
            case R.id.navigation_sync:
                if(flag_camera==1) {onPause(); flag_camera = 0;}
                rlCamera.setVisibility(View.GONE);
                flMain.setVisibility(View.VISIBLE);

                fm.beginTransaction().hide(colorFragment).commit();
                fm.beginTransaction().hide(patternFragment).commit();
                fm.beginTransaction().hide(customFragment).commit();
                fm.beginTransaction().hide(cameraFragment).commit();
                fm.beginTransaction().hide(active).show(syncFragment).commit();
                active = syncFragment;
                return true;
            case R.id.navigation_camera:
                if(flag_camera ==0) {
                    flag_camera = 1;
                    flMain.setVisibility(View.GONE);
                    rlCamera.setVisibility(View.VISIBLE);
                    rlCamera.setBackgroundColor(Color.parseColor("#000000"));
                    fm.beginTransaction().hide(colorFragment).commit();
                    fm.beginTransaction().hide(syncFragment).commit();
                    fm.beginTransaction().hide(customFragment).commit();
                    fm.beginTransaction().hide(patternFragment).commit();
                    fm.beginTransaction().hide(active).show(cameraFragment).commit();
                    active = cameraFragment;
                    onResume();
                }
                return true;
            case R.id.navigation_pattern:
                if(flag_camera==1) {onPause(); flag_camera = 0;}
                rlCamera.setVisibility(View.GONE);
                flMain.setVisibility(View.VISIBLE);

                fm.beginTransaction().hide(colorFragment).commit();
                fm.beginTransaction().hide(syncFragment).commit();
                fm.beginTransaction().hide(customFragment).commit();
                fm.beginTransaction().hide(cameraFragment).commit();
                fm.beginTransaction().hide(active).show(patternFragment).commit();
                active = patternFragment;
                return true;
            case R.id.navigation_custom:
                if(flag_camera==1) {onPause(); flag_camera = 0;}
                rlCamera.setVisibility(View.GONE);
                flMain.setVisibility(View.VISIBLE);

                fm.beginTransaction().hide(colorFragment).commit();
                fm.beginTransaction().hide(syncFragment).commit();
                fm.beginTransaction().hide(patternFragment).commit();
                fm.beginTransaction().hide(cameraFragment).commit();
                fm.beginTransaction().hide(active).show(customFragment).commit();
                active = customFragment;
                return true;

        }
        return false;
    };
    private Camera mCamera;
    private int mSelectedColor;
    private CameraAsyncTask mCameraAsyncTask;
    private CameraColorPickerPreview mCameraPreview;
    private FrameLayout mPreviewContainer;
    private View mPointerRing;
    private static final int REQUEST_CAMERA = 100;
    private TextView txRedView, txGreenView, txBlueView;

    private RadioButton rbAuto;

    private RadioButton rbManual;

    private boolean bAuto = true;

    private boolean m_bPowerOn = false;

    private ProgressDialog dialog;

    private ImageView ivGoHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_tab);
        getSupportActionBar().hide(); //<< this

      dialog=new ProgressDialog(this);
        dialog.setMessage("loading...");
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);
       // dialog.show();

        ButterKnife.bind(this);
        BusProvider.getInstance().register(this);
        String strName = getIntent().getStringExtra("device_name");
        TextView tvActivityTitle = findViewById(R.id.tvActivityTitle);
        setTitle(strName);
        tvActivityTitle.setText(strName);
        requestPermission();
        Intent gattServiceIntent = new Intent(this, BluetoothLeService.class);
        bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
        if (mBluetoothLeService != null)
            mBluetoothLeService.connect(DeviceInfoManager.getInstance().getDeviceAddress());
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fm.beginTransaction().add(R.id.main_container, customFragment, "5").hide(customFragment).commit();
        fm.beginTransaction().add(R.id.main_container, patternFragment, "4").hide(patternFragment).commit();
        fm.beginTransaction().add(R.id.main_container, cameraFragment, "3").hide(cameraFragment).commit();
        fm.beginTransaction().add(R.id.main_container, syncFragment, "2").hide(syncFragment).commit();
        fm.beginTransaction().add(R.id.main_container, colorFragment, "1").commit();

        Button btnColorpix = findViewById(R.id.colorpix);
        btnColorpix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Color pix", Toast.LENGTH_SHORT).show();
            }
        });

        RadioButton rdAutomatic, rdManual;
        rdAutomatic = findViewById(R.id.btn_automatic);
        rdManual = findViewById(R.id.btn_manual);
        rdAutomatic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Automatic", Toast.LENGTH_SHORT).show();
            }
        });

        rdManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Manual", Toast.LENGTH_SHORT).show();
            }
        });
        mPreviewContainer = findViewById(R.id.camera_container);
        mPointerRing = findViewById(R.id.pointer_ring);
        txRedView = findViewById(R.id.red_txt);
        txGreenView = findViewById(R.id.green_txt);
        txBlueView = findViewById(R.id.blue_txt);

        rbAuto = findViewById(R.id.btn_automatic);
        rbAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bAuto = true;
            }
        });

        rbManual = findViewById(R.id.btn_manual);
        rbManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bAuto = true;
            }
        });

        Button pixBtn = findViewById(R.id.colorpix);
        pixBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getConnectionState()) {
                    controlLed(getLedBytes(mSelectedColor));
                }
            }
        });

        ImageView ivPowerOn = findViewById(R.id.iv_connect);
        ivPowerOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (m_bPowerOn)
                    ivPowerOn.setBackground(getResources().getDrawable(R.drawable.icon_poweroff));
                else
                    ivPowerOn.setBackground(getResources().getDrawable(R.drawable.icon_power));

                if (m_bPowerOn) m_bPowerOn = false;
                else m_bPowerOn = true;

                setPowerOnBoard(m_bPowerOn);
            }
        });

        ivGoHome = findViewById(R.id.btn_back);
        ivGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        final MenuItem toggleservice = menu.findItem(R.id.toggleservice);
        actionView = (Switch) toggleservice.getActionView();

        actionView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setPowerOnBoard(isChecked);
            }
        });
        return super.onCreateOptionsMenu(menu);
    }*/

    private  boolean getPowerOnBoard() {
        BluetoothGattCharacteristic characteristic = mBluetoothLeService.getGattCharacteristic(BluetoothGattAttributes.LED_READ_CHARACTERISTIC);
        if (characteristic != null) {
            // check connection
            if (!mConnected) {
                Toast.makeText(this, R.string.ble_not_connected, Toast.LENGTH_SHORT).show();
                return false;
            }
            // send characteristic data
            byte[] state = mBluetoothLeService.getDataCharacteristic(characteristic);
            if(state[0] == 0xBB)
                return false;
            else
                return true;
        }
        else
            //Log.e(TAG, "Not founded characteristic");
        return false;
    }

    private  boolean setPowerOnBoard(boolean bPownerOn)
    {
        BluetoothGattCharacteristic characteristic = mBluetoothLeService.getGattCharacteristic(BluetoothGattAttributes.LED_CHARACTERISTIC);
        if (characteristic != null) {
            // check connection
            if (!mConnected) {
                Toast.makeText(this, R.string.ble_not_connected, Toast.LENGTH_SHORT).show();
                return false;
            }
            // send characteristic data
            if(bPownerOn == true) {
                byte[] powerOn = {(byte) 0xCC, (byte) 0x23, (byte)0x33};
                mBluetoothLeService.sendDataCharacteristic(characteristic, powerOn);
            }
            else
            {
                byte[] powerOff = {(byte) 0xCC, (byte) 0x24, (byte)0x33};
                mBluetoothLeService.sendDataCharacteristic(characteristic, powerOff);
            }

        }
       // else
           // Log.e(TAG, "Not founded characteristic");
        return false;
    }

    private void requestPermission() {
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

    private final ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            mBluetoothLeService = ((BluetoothLeService.LocalBinder) service).getService();
            if (!mBluetoothLeService.initialize()) {
                Toast.makeText(getBaseContext(), R.string.ble_not_find, Toast.LENGTH_SHORT).show();
                finish();
            }
            mBluetoothLeService.connect(DeviceInfoManager.getInstance().getDeviceAddress());
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBluetoothLeService = null;
        }
    };
    private final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver()
    {

        @Override
        public void onReceive(Context context, Intent intent) {
            final Intent mIntent = intent;
            final String action = intent.getAction();
            // connected
            if (BluetoothLeService.ACTION_GATT_CONNECTED.equals(action)) {
                //Log.e(TAG, "BroadcastReceiver : Connected!");
                mConnected = true;
               // Toast.makeText(getBaseContext(), R.string.ble_connect_success, Toast.LENGTH_SHORT).show();
                dialog.hide();
                //actionView.setChecked(getPowerOnBoard());
            }
            // disconnected
            else if (BluetoothLeService.ACTION_GATT_DISCONNECTED.equals(action)) {
                //Log.e(TAG, "BroadcastReceiver : Disconnected!");
                mConnected = false;
                Toast.makeText(getBaseContext(), R.string.ble_disconnected, Toast.LENGTH_SHORT).show();
                HomePageTabActivity.super.onBackPressed();
            }
            // found GATT service
            else if (BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED.equals(action)) {
                //Log.e(TAG, "BroadcastReceiver : Found GATT!");
            }
        }
    };

    private static IntentFilter makeGattUpdateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_CONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_DISCONNECTED);
        return intentFilter;
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
           /* if (ContextCompat.checkSelfPermission(HomePageTabActivity.this,
                    Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(HomePageTabActivity.this,
                        new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
            } else {*/
                mCameraAsyncTask = new CameraAsyncTask();
                mCameraAsyncTask.execute();
           // }
        } else {
            mCameraAsyncTask = new CameraAsyncTask();
            mCameraAsyncTask.execute();
        }
        registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mCameraAsyncTask != null) {
            mCameraAsyncTask.cancel(true);
        }
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.setPreviewCallback(null);
            mCamera.release();
            mCamera = null;
        }
        if (mCameraPreview != null) {
            mPreviewContainer.removeView(mCameraPreview);
        }
        unregisterReceiver(mGattUpdateReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BusProvider.getInstance().unregister(this);
        try {
            if (mConnected) {
                unbindService(mServiceConnection);
                mBluetoothLeService = null;
            }
        }
        catch (Exception e){
           // Log.e(TAG, "BLE unbind Error");
        }


    }

    @Subscribe
    public void deviceChanged(DeviceChangedEvent event) {
        if (mBluetoothLeService != null) {
            mBluetoothLeService.disconnect();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mBluetoothLeService.connect(DeviceInfoManager.getInstance().getDeviceAddress());
                }
            }, 500);
        } else
            mBluetoothLeService.connect(DeviceInfoManager.getInstance().getDeviceAddress());
    }

    public static boolean getConnectionState() {return mConnected;}
    public static boolean controlLed(byte[] rgb) {
        // get bluetoothGattCharacteristic
        if(mBluetoothLeService == null)
            return false;
        BluetoothGattCharacteristic characteristic = mBluetoothLeService.getGattCharacteristic(BluetoothGattAttributes.LED_CHARACTERISTIC);
        if (characteristic != null) {
            // check connection
            if (!mConnected) {
               // Toast.makeText(this, R.string.ble_not_connected, Toast.LENGTH_SHORT).show();
                return false;
            }

            // send characteristic data
            mBluetoothLeService.sendDataCharacteristic(characteristic, rgb);
            return true;
        }
        else
           // Log.e(TAG, "Not founded characteristic");
        return false;
    }

    @Override
    public void onColorSelected(int color) {
        int red1 = Color.red(color);
        int green1 = Color.green(color);
        int blue1 = Color.blue(color);
        txRedView.setText(Integer.toString(red1));
        txGreenView.setText(Integer.toString(green1));
        txBlueView.setText(Integer.toString(blue1));
        mSelectedColor = color;
        if(getConnectionState()) {
            if(rbAuto.isChecked())
                controlLed(getLedBytes(color));
        }
        mPointerRing.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
    }

    private byte[] getLedBytes(int newColor) {
        byte[] rgb = new byte[7];
        int color = (int) Long.parseLong(String.format("%06X", (0xFFFFFF & newColor)), 16);
        rgb[0] = (byte) 0x56;
        rgb[1] = (byte) ((color >> 16) & 0xFF);
        rgb[2] = (byte) ((color >> 8) & 0xFF);
        rgb[3] = (byte) ((color >> 0) & 0xFF);
        rgb[4] = 0;
        rgb[5] = (byte) 0xF0;
        rgb[6] = (byte) 0xAA;

        if( (rgb[1] > 150 ) && (rgb[1] < 100) && rgb[2] < 100) {
            //rgb[1] = (byte)0xFF;
            rgb[2] = (byte)0x00; rgb[3] = (byte)0x00;
        }
        if ((rgb[2] > 150 ) && (rgb[2]  < 100  ) && (rgb[2]  < 100  ))
            //rgb[2] = (byte)0xFF;
            rgb[1] = (byte)(rgb[1] /3); rgb[3] = (byte)(rgb[3] /3);

        if ((rgb[3] > 150 ) && (rgb[3]  < 100  ) && (rgb[3] < 100 ))
            // rgb[3] = (byte)0xFF;
            rgb[2] = (byte)(rgb[2] /3); rgb[1] = (byte)(rgb[1] /3);
        return rgb;
    }
    public static class BottomNavigationViewHelper {
        @SuppressLint("RestrictedApi")
        public static void disableShiftMode(BottomNavigationView view) {
            BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
            try {
                Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
                shiftingMode.setAccessible(true);
                shiftingMode.setBoolean(menuView, false);
                shiftingMode.setAccessible(false);
                for (int i = 0; i < menuView.getChildCount(); i++) {
                    BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                    item.setShiftingMode(false);
                    item.setChecked(item.getItemData().isChecked());
                }
            } catch (NoSuchFieldException e) {
                Log.e("BNVHelper", "Unable to get shift mode field", e);
            } catch (IllegalAccessException e) {
                Log.e("BNVHelper", "Unable to change value of shift mode", e);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CAMERA) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {//同意权限
//            } else {
//                HomePageTabActivity.this.finish();
//            }
        }
    }

    private static Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    private class CameraAsyncTask extends AsyncTask<Void, Void, Camera> {

        protected FrameLayout.LayoutParams mPreviewParams;
        @Override
        protected Camera doInBackground(Void... params) {
                Camera camera = getCameraInstance();
                if (camera != null) {
                    Camera.Parameters cameraParameters = camera.getParameters();
                    @SuppressLint("WrongThread") Camera.Size bestSize = Cameras.getBestPreviewSize(
                            cameraParameters.getSupportedPreviewSizes()
                            , mPreviewContainer.getWidth()
                            , mPreviewContainer.getHeight()
                            , true);
                    cameraParameters.setPreviewSize(bestSize.width, bestSize.height);
                    camera.setParameters(cameraParameters);
                    Cameras.setCameraDisplayOrientation(HomePageTabActivity.this, camera);
                    @SuppressLint("WrongThread") int[] adaptedDimension = Cameras.getProportionalDimension(
                            bestSize
                            , mPreviewContainer.getWidth()
                            , mPreviewContainer.getHeight()
                            , true);
                    mPreviewParams = new FrameLayout.LayoutParams(adaptedDimension[0], adaptedDimension[1]);
                    mPreviewParams.gravity = Gravity.CENTER;
                }
                return camera;
        }

        @Override
        protected void onPostExecute(Camera camera) {
            super.onPostExecute(camera);
            if (!isCancelled()) {
                mCamera = camera;
                if (mCamera != null) {
                    mCameraPreview = new CameraColorPickerPreview(HomePageTabActivity.this, mCamera);
                    mCameraPreview.setOnColorSelectedListener(HomePageTabActivity.this);
                    mPreviewContainer.addView(mCameraPreview, 0, mPreviewParams);
                }
            }
        }

        @Override
        protected void onCancelled(Camera camera) {
            super.onCancelled(camera);
            if (camera != null) {
                camera.release();
            }
        }
    }
}
