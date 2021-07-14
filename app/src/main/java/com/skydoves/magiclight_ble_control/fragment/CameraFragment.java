package com.skydoves.magiclight_ble_control.fragment;


import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.camerakit.CameraKitView;
import com.skydoves.magiclight_ble_control.R;
import com.skydoves.magiclight_ble_control.main.HomePageTabActivity;

public class CameraFragment extends Fragment {

    private CameraKitView cameraKitView;
    private Button  btnColorPix, btnAutomatic, btnManual;
    private ImageView ivCursor;
    private TextView txRedView;
    private TextView txGreenView;
    private TextView txBlueView;
    byte[]  rgbLED;
    public CameraFragment() {
         rgbLED = new byte[7];
    }
    @Override
    public void onHiddenChanged(boolean hidden) {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_camera, container, false);
//
//        cameraKitView = view.findViewById(R.id.camera);
//        btnColorPix = view.findViewById(R.id.colorpix);
//        ivCursor = view.findViewById(R.id.cursor);
//        txBlueView = view.findViewById(R.id.blue_txt);
//        txGreenView = view.findViewById(R.id.green_txt);
//        txRedView = view.findViewById(R.id.red_txt);
//
//        btnColorPix.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(((HomePageTabActivity)getActivity()).getConnectionState()) {
//                    ((HomePageTabActivity)getActivity()).controlLed(rgbLED);
//                }
//            }
//        });
//
//        cameraKitView.setOnTouchListener(new View.OnTouchListener() {
//            int x_cord = 0;  int y_cord = 0;
//            public boolean onTouch(View v, MotionEvent event) {
//                android.widget.RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)ivCursor.getLayoutParams();
//
//
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        x_cord = (int) event.getX();
//                        y_cord = (int) event.getY();
//                        params.leftMargin =x_cord;
//                        params.topMargin = y_cord;
//                        ivCursor.setLayoutParams(params);
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        cameraKitView.captureImage(new  CameraKitView.ImageCallback() {
//                            @Override
//                            public void onImage(CameraKitView cameraKitView, final byte[] capturedImage) {
//                                Bitmap bitmap = BitmapFactory.decodeByteArray(capturedImage,0,capturedImage.length);
//                                rgbLED = getLedBytes(bitmap.getPixel(x_cord, y_cord *  1920 / 1650));
//                                txRedView.setText(Integer.toString(rgbLED[1] & 0xFF));
//                                txGreenView.setText(Integer.toString(rgbLED[2] & 0xFF));
//                                txBlueView.setText(Integer.toString(rgbLED[3] & 0xFF));
//
//                                if(((HomePageTabActivity)getActivity()).getConnectionState()) {
//                                    ((HomePageTabActivity)getActivity()).controlLed(rgbLED);
//                                }
//
//                            }
//                        });
//                }
//
//                return true;
//            }
//        });
//        cameraKitView.setPreviewListener(new CameraKitView.PreviewListener() {
//            @Override
//            public void onStart() {
//            }
//            @Override
//            public void onStop() {
//            }
//        });
        return  view;
    }
//
//    private static String toHexadecimal(byte[] digest){
//        String hash = "";
//        for(byte aux : digest) {
//            int b = aux & 0xff;
//            if (Integer.toHexString(b).length() == 1) hash += "0";
//            hash += Integer.toHexString(b);
//        }
//        return hash;
//    }
//
//    public static int pxFromDp( final float dp) {
//        return (int)(dp * Resources.getSystem().getDisplayMetrics().density);
//    }
//
//    public static int pxToDp(int px) {
//        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
//    }
//    @Override
//    public void onStart() {
//        super.onStart();
//        cameraKitView.onStart();
//
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        cameraKitView.onResume();
//    }
//
//    @Override
//    public void onPause() {
//        cameraKitView.onPause();
//        super.onPause();
//
//    }
//
//    @Override
//    public void onStop() {
//        cameraKitView.onStop();
//        super.onStop();
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        cameraKitView.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//    }
//
//    private byte[] getLedBytes(int newColor) {
//        byte[] rgb = new byte[7];
//        int color = (int)Long.parseLong(String.format("%06X", (0xFFFFFF & newColor)), 16);
//        rgb[0] = (byte)0x56;
//        rgb[1] = (byte)((color >> 16) & 0xFF);
//        rgb[2]= (byte)((color >> 8) & 0xFF);
//        rgb[3] = (byte)((color >> 0) & 0xFF);
//        rgb[4] = 0;
//        rgb[5] = (byte)0xF0;
//        rgb[6] = (byte)0xAA;
//
//        if( (rgb[1] > 150 ) && (rgb[1] < 100) && rgb[2] < 100) {
//            rgb[1] = (byte)0xFF; rgb[2] = (byte)0x00; rgb[3] = (byte)0x00;
//        }
//        if ((rgb[2] > 150 ) && (rgb[2]  < 100  ) && (rgb[2]  < 100  ))
//            rgb[2] = (byte)0xFF;rgb[1] = (byte)(rgb[1] /3); rgb[3] = (byte)(rgb[3] /3);
//
//        if ((rgb[3] > 150 ) && (rgb[3]  < 100  ) && (rgb[3] < 100 ))
//            rgb[3] = (byte)0xFF; rgb[2] = (byte)(rgb[2] /3); rgb[1] = (byte)(rgb[1] /3);
//
//        return rgb;
//    }

}
