package com.skydoves.magiclight_ble_control.model;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.skydoves.magiclight_ble_control.R;
import com.skydoves.magiclight_ble_control.fragment.CustomFragment;
import com.skydoves.magiclight_ble_control.model.DBHelper;
import java.util.ArrayList;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.GetIntegerArray;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.GetStringArray;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.colorbrightnessDec;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.colorstr;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.discreteSeekBar1;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.default_color;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.editModeNumber;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.editable;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.flag_mode;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.mode1;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.mode2;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.mode3;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.mode4;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.mode5;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.mode6;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.mode7;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.mode8;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.mode9;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.mode10;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.mode11;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.mode12;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.mode13;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.mode14;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.mode15;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.mode16;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.modemethod;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.modespeed;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.mycolors;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.mybright;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.mydb;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.mypointx;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.mypointy;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.mytitle;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.rdFade;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.rdJump;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.rdStrobe;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.selectedColor;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.tvmodeTitle;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.txGreenView;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.txRedView;
import static com.skydoves.magiclight_ble_control.ColorModeActivity.txBlueView;
import static com.skydoves.magiclight_ble_control.fragment.CustomFragment.*;

public class ModeClass extends Application {

    public ModeClass() {
        super();
    }

    public void init_ClickEventColorModeActivity(View v) {
        if( v == mode1) {
            flag_mode =1;
            if(editable.equals("create")){
                default_color = 0;
            }
        } else if( v == mode2) {
            flag_mode =2;
            if(editable.equals("create")){
                default_color = 0;
            }
        } else if( v == mode3) {
            flag_mode =3;
            if(editable.equals("create")){
                default_color = 0;
            }
        } else if( v == mode4) {
            flag_mode =4;
            if(editable.equals("create")){
                default_color = 0;
            }
        } else if( v == mode5) {
            flag_mode =5;
        } else if( v == mode6) {
            flag_mode =6;
        } else if( v == mode7) {
            flag_mode =7;
        } else if( v == mode8) {
            flag_mode =8;
        } else if( v == mode9) {
            flag_mode =9;
        } else if( v == mode10) {
            flag_mode =10;
        } else if( v == mode11) {
            flag_mode =11;
        } else if( v == mode12) {
            flag_mode =12;
        } else if( v == mode13) {
            flag_mode =13;
        } else if( v == mode14) {
            flag_mode =14;
        } else if( v == mode15) {
            flag_mode =15;
        } else if( v == mode16) {
            flag_mode = 16;
        }
    }

    public void initUI_colorMode(Activity activity){
        mode1 = activity.findViewById(R.id.mode1);
        mode2 = activity.findViewById(R.id.mode2);
        mode3 = activity.findViewById(R.id.mode3);
        mode4 = activity.findViewById(R.id.mode4);
        mode5 = activity.findViewById(R.id.mode5);
        mode6 = activity.findViewById(R.id.mode6);
        mode7 = activity.findViewById(R.id.mode7);
        mode8 = activity.findViewById(R.id.mode8);
        mode9 = activity.findViewById(R.id.mode9);
        mode10 = activity.findViewById(R.id.mode10);
        mode11 = activity.findViewById(R.id.mode11);
        mode12 = activity.findViewById(R.id.mode12);
        mode13 = activity.findViewById(R.id.mode13);
        mode14 = activity.findViewById(R.id.mode14);
        mode15 = activity.findViewById(R.id.mode15);
        mode16 = activity.findViewById(R.id.mode16);
        mode1.setOnClickListener((View.OnClickListener) activity);
        mode2.setOnClickListener((View.OnClickListener) activity);
        mode3.setOnClickListener((View.OnClickListener) activity);
        mode4.setOnClickListener((View.OnClickListener) activity);
        mode5.setOnClickListener((View.OnClickListener) activity);
        mode6.setOnClickListener((View.OnClickListener) activity);
        mode7.setOnClickListener((View.OnClickListener) activity);
        mode8.setOnClickListener((View.OnClickListener) activity);
        mode9.setOnClickListener((View.OnClickListener) activity);
        mode10.setOnClickListener((View.OnClickListener) activity);
        mode11.setOnClickListener((View.OnClickListener) activity);
        mode12.setOnClickListener((View.OnClickListener) activity);
        mode13.setOnClickListener((View.OnClickListener) activity);
        mode14.setOnClickListener((View.OnClickListener) activity);
        mode15.setOnClickListener((View.OnClickListener) activity);
        mode16.setOnClickListener((View.OnClickListener) activity);
    }

    public void save_modeColor() {
        try {
            if (flag_mode == 1) {
                mode1.setBackgroundColor(Color.parseColor(colorstr));
                mode1.setText(colorbrightnessDec);

            } else if (flag_mode == 2) {
                mode2.setBackgroundColor(Color.parseColor(colorstr));
                mode2.setText(colorbrightnessDec);

            } else if (flag_mode == 3) {
                mode3.setBackgroundColor(Color.parseColor(colorstr));
                mode3.setText(colorbrightnessDec);

            } else if (flag_mode == 4) {
                mode4.setBackgroundColor(Color.parseColor(colorstr));
                mode4.setText(colorbrightnessDec);
            } else if (flag_mode == 5) {
                mode5.setBackgroundColor(Color.parseColor(colorstr));
                mode5.setText(colorbrightnessDec);
            } else if (flag_mode == 6) {
                mode6.setBackgroundColor(Color.parseColor(colorstr));
                mode6.setText(colorbrightnessDec);
            } else if (flag_mode == 7) {
                mode7.setBackgroundColor(Color.parseColor(colorstr));
                mode7.setText(colorbrightnessDec);
            } else if (flag_mode == 8) {
                mode8.setBackgroundColor(Color.parseColor(colorstr));
                mode8.setText(colorbrightnessDec);
            } else if (flag_mode == 9) {
                mode9.setBackgroundColor(Color.parseColor(colorstr));
                mode9.setText(colorbrightnessDec);
            } else if (flag_mode == 10) {
                mode10.setBackgroundColor(Color.parseColor(colorstr));
                mode10.setText(colorbrightnessDec);
            } else if (flag_mode == 11) {
                mode11.setBackgroundColor(Color.parseColor(colorstr));
                mode11.setText(colorbrightnessDec);
            } else if (flag_mode == 12) {
                mode12.setBackgroundColor(Color.parseColor(colorstr));
                mode12.setText(colorbrightnessDec);
            } else if (flag_mode == 13) {
                mode13.setBackgroundColor(Color.parseColor(colorstr));
                mode13.setText(colorbrightnessDec);
            } else if (flag_mode == 14) {
                mode14.setBackgroundColor(Color.parseColor(colorstr));
                mode14.setText(colorbrightnessDec);
            } else if (flag_mode == 15) {
                mode15.setBackgroundColor(Color.parseColor(colorstr));
                mode15.setText(colorbrightnessDec);
            } else if (flag_mode == 16) {
                mode16.setBackgroundColor(Color.parseColor(colorstr));
                mode16.setText(colorbrightnessDec);
            }
        }catch (Exception e)
        {
            int pp = 0;
        }
    }

    public void init_rgbColor(){
        txRedView.setText("0");
        txGreenView.setText("0");
        txBlueView.setText("0");
        if(flag_mode == 1){
            txRedView.setText("255");
            colorstr = "#ff0000";
            selectedColor.setBackgroundColor(Color.parseColor("#ff0000"));
        } else if(flag_mode==2) {
            txGreenView.setText("255");
            colorstr = "#00ff00";
            selectedColor.setBackgroundColor(Color.parseColor("#00ff00"));
        } else if(flag_mode==3) {
            txBlueView.setText("255");
            colorstr = "#0000ff";
            selectedColor.setBackgroundColor(Color.parseColor("#0000ff"));
        } else if(flag_mode==4) {
            txRedView.setText("255");
            txGreenView.setText("255");
            txBlueView.setText("255");
            colorstr = "#ffffff";
            selectedColor.setBackgroundColor(Color.parseColor("#ffffff"));
        }
    }

    public void init_editCreate() {
        ArrayList array_list = mydb.getColorMode1();
        ArrayList array_bright = mydb.getModeBrightness1();
        ArrayList array_pointx = mydb.getModePointX1();
        ArrayList array_pointy = mydb.getModePointY1();
        int speed =0;
        String method = "";
        if(editModeNumber == 1 ){
            array_list = mydb.getColorMode1();
            array_bright = mydb.getModeBrightness1();
            array_pointx = mydb.getModePointX1();
            array_pointy = mydb.getModePointY1();
            speed = mydb.getModeSpeed1();
            method = mydb.getModeMethod(1);
        } else if(editModeNumber ==2) {
            array_list = mydb.getColorMode2();
            array_bright = mydb.getModeBrightness2();
            array_pointx = mydb.getModePointX2();
            array_pointy = mydb.getModePointY2();
            speed = mydb.getModeSpeed2();
            method = mydb.getModeMethod(2);
        } else if(editModeNumber ==3) {
            array_list = mydb.getColorMode3();
            array_bright = mydb.getModeBrightness3();
            array_pointx = mydb.getModePointX3();
            array_pointy = mydb.getModePointY3();
            speed = mydb.getModeSpeed3();
            method = mydb.getModeMethod(3);
        } else if(editModeNumber ==4) {
            array_list = mydb.getColorMode4();
            array_bright = mydb.getModeBrightness4();
            array_pointx = mydb.getModePointX4();
            array_pointy = mydb.getModePointY4();
            speed = mydb.getModeSpeed4();
            method = mydb.getModeMethod(4);
        } else if(editModeNumber ==5) {
            array_list = mydb.getColorMode5();
            array_bright = mydb.getModeBrightness5();
            array_pointx = mydb.getModePointX5();
            array_pointy = mydb.getModePointY5();
            speed = mydb.getModeSpeed5();
            method = mydb.getModeMethod(5);
        } else if(editModeNumber ==6) {
            array_list = mydb.getColorMode6();
            array_bright = mydb.getModeBrightness6();
            array_pointx = mydb.getModePointX6();
            array_pointy = mydb.getModePointY6();
            speed = mydb.getModeSpeed6();
            method = mydb.getModeMethod(6);
        } else if(editModeNumber ==7) {
            array_list = mydb.getColorMode7();
            array_bright = mydb.getModeBrightness7();
            array_pointx = mydb.getModePointX7();
            array_pointy = mydb.getModePointY7();
            speed = mydb.getModeSpeed7();
            method = mydb.getModeMethod(7);
        } else if(editModeNumber ==8) {
            array_list = mydb.getColorMode8();
            array_bright = mydb.getModeBrightness8();
            array_pointx = mydb.getModePointX8();
            array_pointy = mydb.getModePointY8();
            speed = mydb.getModeSpeed8();
            method = mydb.getModeMethod(8);
        } else if(editModeNumber ==9) {
            array_list = mydb.getColorMode9();
            array_bright = mydb.getModeBrightness9();
            array_pointx = mydb.getModePointX9();
            array_pointy = mydb.getModePointY9();
            speed = mydb.getModeSpeed9();
            method = mydb.getModeMethod(9);
        } else if(editModeNumber ==10) {
            array_list = mydb.getColorMode0();
            array_bright = mydb.getModeBrightness0();
            array_pointx = mydb.getModePointX0();
            array_pointy = mydb.getModePointY0();
            speed = mydb.getModeSpeed0();
            method = mydb.getModeMethod(0);
        }
        String[] str = GetStringArray(array_list);
        Integer[] str_bright = GetIntegerArray(array_bright);
        Integer[] str_pointx = GetIntegerArray(array_pointx);
        Integer[] str_pointy = GetIntegerArray(array_pointy);
        int i;
        for (i = 0; i < 16; i++) {
            if(str[i].equals("empty")) {
                mycolors[i]="empty";
                mybright[i] = 100;
                mypointx[i] =305;
                mypointy[i] =413;
                if(i==0) {
                    mypointx[0] = -22;
                    mypointy[0] = 408;
                } else if(i==1){
                    mypointx[1] = 457;
                    mypointy[1] = 128;
                } else if(i==2) {
                    mypointx[2] = 458;
                    mypointy[2] = 698;
                }
                continue;
            }
            mycolors[i]=str[i];
            mybright[i] = str_bright[i];
            mypointx[i] = str_pointx[i];
            mypointy[i] = str_pointy[i];
            if (i == 0) {
                mode1.setBackgroundColor(Color.parseColor(str[i]));
                mode1.setText(String.valueOf(str_bright[i])+"%");
            } else if (i == 1) {
                mode2.setBackgroundColor(Color.parseColor(str[i]));
                mode2.setText(String.valueOf(str_bright[i])+"%");
            } else if (i == 2) {
                mode3.setBackgroundColor(Color.parseColor(str[i]));
                mode3.setText(String.valueOf(str_bright[i])+"%");
            } else if (i == 3) {
                mode4.setBackgroundColor(Color.parseColor(str[i]));
                mode4.setText(String.valueOf(str_bright[i])+"%");
            } else if (i == 4) {
                mode5.setBackgroundColor(Color.parseColor(str[i]));
                mode5.setText(String.valueOf(str_bright[i])+"%");
            } else if (i == 5) {
                mode6.setBackgroundColor(Color.parseColor(str[i]));
                mode6.setText(String.valueOf(str_bright[i])+"%");
            } else if (i == 6) {
                mode7.setBackgroundColor(Color.parseColor(str[i]));
                mode7.setText(String.valueOf(str_bright[i])+"%");
            } else if (i == 7) {
                mode8.setBackgroundColor(Color.parseColor(str[i]));
                mode8.setText(String.valueOf(str_bright[i])+"%");
            } else if (i == 8) {
                mode9.setBackgroundColor(Color.parseColor(str[i]));
                mode9.setText(String.valueOf(str_bright[i])+"%");
            } else if (i == 9) {
                mode10.setBackgroundColor(Color.parseColor(str[i]));
                mode10.setText(String.valueOf(str_bright[i])+"%");
            } else if (i == 10) {
                mode11.setBackgroundColor(Color.parseColor(str[i]));
                mode11.setText(String.valueOf(str_bright[i])+"%");
            } else if (i == 11) {
                mode12.setBackgroundColor(Color.parseColor(str[i]));
                mode12.setText(String.valueOf(str_bright[i])+"%");
            } else if (i == 12) {
                mode13.setBackgroundColor(Color.parseColor(str[i]));
                mode13.setText(String.valueOf(str_bright[i])+"%");
            } else if (i == 13) {
                mode14.setBackgroundColor(Color.parseColor(str[i]));
                mode14.setText(String.valueOf(str_bright[i])+"%");
            } else if (i == 14) {
                mode15.setBackgroundColor(Color.parseColor(str[i]));
                mode15.setText(String.valueOf(str_bright[i])+"%");
            } else if (i == 15) {
                mode16.setBackgroundColor(Color.parseColor(str[i]));
                mode16.setText(String.valueOf(str_bright[i])+"%");
            }
        }
        discreteSeekBar1.setProgress(speed);
        if(method.equals("Fade")){
            rdFade.setChecked(true);
        } else if(method.equals("Jump")) {
            rdJump.setChecked(true);
        } else{
            rdStrobe.setChecked(true);
        }
    }

    public void init_createCreate() {
        for(int i =0; i< 16; i++){
            mycolors[i]="empty";
            mybright[i]=100;
            mypointx[i] =305;
            mypointy[i] =413;
        }
        mypointx[0] = -22;
        mypointy[0] = 408;
        mypointx[1] = 457;
        mypointy[1] = 128;
        mypointx[2] = 458;
        mypointy[2] = 698;
        mycolors[0] = "#ff0000";
        mycolors[1] = "#00ff00";
        mycolors[2] = "#0000ff";
        mycolors[3] = "#ffffff";
        mode1.setBackgroundColor(Color.parseColor(mycolors[0]));
        mode1.setText(String.valueOf(mybright[0])+"%");
        mode2.setBackgroundColor(Color.parseColor(mycolors[1]));
        mode2.setText(String.valueOf(mybright[1])+"%");
        mode3.setBackgroundColor(Color.parseColor(mycolors[2]));
        mode3.setText(String.valueOf(mybright[2])+"%");
        mode4.setBackgroundColor(Color.parseColor(mycolors[3]));
        mode4.setText(String.valueOf(mybright[3])+"%");
        discreteSeekBar1.setProgress(50);
    }

    public void init_saveColors() {

        String modetitle = tvmodeTitle.getText().toString();
        int a=0;
        if(editModeNumber>0) {
            a = editModeNumber;
        } else {
            a = mydb.getCount();
            if(!editable.equals("edit")){
                a++;
                mydb.insertCount(a);
            }
        }
        mydb.onReCreate(editModeNumber);
        int i;
        for(i=0; i<16; i++){
            if(a==1) {
                mydb.insertColorMode1((String) mycolors[i], mybright[i], mypointx[i], mypointy[i], modetitle, modemethod, modespeed);
            } else if(a==2) {
                mydb.insertColorMode2((String) mycolors[i], mybright[i], mypointx[i], mypointy[i], modetitle, modemethod, modespeed);
            } else if(a==3) {
                mydb.insertColorMode3((String) mycolors[i], mybright[i], mypointx[i], mypointy[i], modetitle, modemethod, modespeed);
            } else if(a==4) {
                mydb.insertColorMode4((String) mycolors[i], mybright[i], mypointx[i], mypointy[i], modetitle, modemethod, modespeed);
            } else if(a==5) {
                mydb.insertColorMode5((String) mycolors[i], mybright[i], mypointx[i], mypointy[i], modetitle, modemethod, modespeed);
            } else if(a==6) {
                mydb.insertColorMode6((String) mycolors[i], mybright[i], mypointx[i], mypointy[i], modetitle, modemethod, modespeed);
            } else if(a==7) {
                mydb.insertColorMode7((String) mycolors[i], mybright[i], mypointx[i], mypointy[i], modetitle, modemethod, modespeed);
            } else if(a==8) {
                mydb.insertColorMode8((String) mycolors[i], mybright[i], mypointx[i], mypointy[i], modetitle, modemethod, modespeed);
            } else if(a==9) {
                mydb.insertColorMode9((String) mycolors[i], mybright[i], mypointx[i], mypointy[i], modetitle, modemethod, modespeed);
            } else if(a==10) {
                mydb.insertColorMode0((String) mycolors[i], mybright[i], mypointx[i], mypointy[i], modetitle, modemethod, modespeed);
            }
        }
    }

    public void init_CustomOnResume(Activity activity) {
        ArrayList array_list;
        String[] str;
        mydb = new DBHelper(activity);
        int mode_count = mydb.getCount();
        if(mode_count==0){
            flDescription.setVisibility(View.VISIBLE);
        } else {
            flDescription.setVisibility(View.GONE);
            init_colors(activity);
            int i,j;
            String title, method;
            int speed;
            for (i=1; i<=mode_count;i++) {
                if(i==1) {
                    array_list = mydb.getColorMode1();
                    str = GetStringArray(array_list);
                    for (j = 0; j < str.length; j++) {
                        if(str[j].equals("empty")) {
                            continue;
                        }
                        if (j == 0 ) {
                            mode1_1.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 1) {
                            mode1_2.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 2) {
                            mode1_3.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 3) {
                            mode1_4.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 4) {
                            mode1_5.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 5) {
                            mode1_6.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 6) {
                            mode1_7.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 7) {
                            mode1_8.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 8) {
                            mode1_9.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 9) {
                            mode1_10.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 10) {
                            mode1_11.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 11) {
                            mode1_12.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 12) {
                            mode1_13.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 13) {
                            mode1_14.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 14) {
                            mode1_15.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 15) {
                            mode1_16.setBackgroundColor(Color.parseColor(str[j]));
                        }
                    }
                    title = mydb.getModeTitle(1);
                    method = mydb.getModeMethod(1);
                    speed = mydb.getModeSpeed1();
                    template_title1.setText(title);
                    template_method1.setText(method);
                    template_speed1.setText(String.valueOf(speed));
                } else if (i ==2 ) {
                    array_list = mydb.getColorMode2();
                    str = GetStringArray(array_list);
                    for (j = 0; j < str.length; j++) {
                        if(str[j].equals("empty")) {
                            continue;
                        }
                        if (j == 0 ) {
                            mode2_1.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 1) {
                            mode2_2.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 2) {
                            mode2_3.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 3) {
                            mode2_4.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 4) {
                            mode2_5.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 5) {
                            mode2_6.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 6) {
                            mode2_7.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 7) {
                            mode2_8.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 8) {
                            mode2_9.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 9) {
                            mode2_10.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 10) {
                            mode2_11.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 11) {
                            mode2_12.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 12) {
                            mode2_13.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 13) {
                            mode2_14.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 14) {
                            mode2_15.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 15) {
                            mode2_16.setBackgroundColor(Color.parseColor(str[j]));
                        }
                    }
                    title = mydb.getModeTitle(2);
                    method = mydb.getModeMethod(2);
                    speed = mydb.getModeSpeed2();
                    template_title2.setText(title);
                    template_method2.setText(method);
                    template_speed2.setText(String.valueOf(speed));
                } else if (i ==3 ) {
                    array_list = mydb.getColorMode3();
                    str = GetStringArray(array_list);
                    for (j = 0; j < str.length; j++) {
                        if(str[j].equals("empty")) {
                            continue;
                        }
                        if (j == 0 ) {
                            mode3_1.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 1) {
                            mode3_2.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 2) {
                            mode3_3.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 3) {
                            mode3_4.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 4) {
                            mode3_5.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 5) {
                            mode3_6.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 6) {
                            mode3_7.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 7) {
                            mode3_8.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 8) {
                            mode3_9.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 9) {
                            mode3_10.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 10) {
                            mode3_11.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 11) {
                            mode3_12.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 12) {
                            mode3_13.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 13) {
                            mode3_14.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 14) {
                            mode3_15.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 15) {
                            mode3_16.setBackgroundColor(Color.parseColor(str[j]));
                        }
                    }
                    title = mydb.getModeTitle(3);
                    method = mydb.getModeMethod(3);
                    speed = mydb.getModeSpeed3();
                    template_title3.setText(title);
                    template_method3.setText(method);
                    template_speed3.setText(String.valueOf(speed));
                } else if (i ==4 ) {
                    array_list = mydb.getColorMode4();
                    str = GetStringArray(array_list);
                    for (j = 0; j < str.length; j++) {
                        if(str[j].equals("empty")) {
                            continue;
                        }
                        if (j == 0 ) {
                            mode4_1.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 1) {
                            mode4_2.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 2) {
                            mode4_3.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 3) {
                            mode4_4.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 4) {
                            mode4_5.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 5) {
                            mode4_6.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 6) {
                            mode4_7.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 7) {
                            mode4_8.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 8) {
                            mode4_9.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 9) {
                            mode4_10.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 10) {
                            mode4_11.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 11) {
                            mode4_12.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 12) {
                            mode4_13.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 13) {
                            mode4_14.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 14) {
                            mode4_15.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 15) {
                            mode4_16.setBackgroundColor(Color.parseColor(str[j]));
                        }
                    }
                    title = mydb.getModeTitle(4);
                    method = mydb.getModeMethod(4);
                    speed = mydb.getModeSpeed4();
                    template_title4.setText(title);
                    template_method4.setText(method);
                    template_speed4.setText(String.valueOf(speed));
                } else if (i ==5 ) {
                    array_list = mydb.getColorMode5();
                    str = GetStringArray(array_list);
                    for (j = 0; j < str.length; j++) {
                        if(str[j].equals("empty")) {
                            continue;
                        }
                        if (j == 0 ) {
                            mode5_1.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 1) {
                            mode5_2.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 2) {
                            mode5_3.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 3) {
                            mode5_4.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 4) {
                            mode5_5.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 5) {
                            mode5_6.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 6) {
                            mode5_7.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 7) {
                            mode5_8.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 8) {
                            mode5_9.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 9) {
                            mode5_10.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 10) {
                            mode5_11.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 11) {
                            mode5_12.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 12) {
                            mode5_13.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 13) {
                            mode5_14.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 14) {
                            mode5_15.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 15) {
                            mode5_16.setBackgroundColor(Color.parseColor(str[j]));
                        }
                    }
                    title = mydb.getModeTitle(5);
                    method = mydb.getModeMethod(5);
                    speed = mydb.getModeSpeed5();
                    template_title5.setText(title);
                    template_method5.setText(method);
                    template_speed5.setText(String.valueOf(speed));
                } else if (i ==6 ) {
                    array_list = mydb.getColorMode6();
                    str = GetStringArray(array_list);
                    for (j = 0; j < str.length; j++) {
                        if(str[j].equals("empty")) {
                            continue;
                        }
                        if (j == 0 ) {
                            mode6_1.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 1) {
                            mode6_2.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 2) {
                            mode6_3.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 3) {
                            mode6_4.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 4) {
                            mode6_5.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 5) {
                            mode6_6.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 6) {
                            mode6_7.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 7) {
                            mode6_8.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 8) {
                            mode6_9.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 9) {
                            mode6_10.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 10) {
                            mode6_11.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 11) {
                            mode6_12.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 12) {
                            mode6_13.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 13) {
                            mode6_14.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 14) {
                            mode6_15.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 15) {
                            mode6_16.setBackgroundColor(Color.parseColor(str[j]));
                        }
                    }
                    title = mydb.getModeTitle(6);
                    method = mydb.getModeMethod(6);
                    speed = mydb.getModeSpeed6();
                    template_title6.setText(title);
                    template_method6.setText(method);
                    template_speed6.setText(String.valueOf(speed));
                } else if (i ==7 ) {
                    array_list = mydb.getColorMode7();
                    str = GetStringArray(array_list);
                    for (j = 0; j < str.length; j++) {
                        if(str[j].equals("empty")) {
                            continue;
                        }
                        if (j == 0 ) {
                            mode7_1.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 1) {
                            mode7_2.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 2) {
                            mode7_3.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 3) {
                            mode7_4.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 4) {
                            mode7_5.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 5) {
                            mode7_6.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 6) {
                            mode7_7.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 7) {
                            mode7_8.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 8) {
                            mode7_9.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 9) {
                            mode7_10.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 10) {
                            mode7_11.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 11) {
                            mode7_12.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 12) {
                            mode7_13.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 13) {
                            mode7_14.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 14) {
                            mode7_15.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 15) {
                            mode7_16.setBackgroundColor(Color.parseColor(str[j]));
                        }
                    }
                    title = mydb.getModeTitle(7);
                    method = mydb.getModeMethod(7);
                    speed = mydb.getModeSpeed7();
                    template_title7.setText(title);
                    template_method7.setText(method);
                    template_speed7.setText(String.valueOf(speed));
                } else if (i ==8 ) {
                    array_list = mydb.getColorMode8();
                    str = GetStringArray(array_list);
                    for (j = 0; j < str.length; j++) {
                        if(str[j].equals("empty")) {
                            continue;
                        }
                        if (j == 0 ) {
                            mode8_1.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 1) {
                            mode8_2.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 2) {
                            mode8_3.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 3) {
                            mode8_4.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 4) {
                            mode8_5.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 5) {
                            mode8_6.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 6) {
                            mode8_7.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 7) {
                            mode8_8.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 8) {
                            mode8_9.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 9) {
                            mode8_10.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 10) {
                            mode8_11.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 11) {
                            mode8_12.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 12) {
                            mode8_13.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 13) {
                            mode8_14.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 14) {
                            mode8_15.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 15) {
                            mode8_16.setBackgroundColor(Color.parseColor(str[j]));
                        }
                    }
                    title = mydb.getModeTitle(8);
                    method = mydb.getModeMethod(8);
                    speed = mydb.getModeSpeed8();
                    template_title8.setText(title);
                    template_method8.setText(method);
                    template_speed8.setText(String.valueOf(speed));
                } else if (i ==9 ) {
                    array_list = mydb.getColorMode9();
                    str = GetStringArray(array_list);
                    for (j = 0; j < str.length; j++) {
                        if(str[j].equals("empty")) {
                            continue;
                        }
                        if (j == 0 ) {
                            mode9_1.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 1) {
                            mode9_2.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 2) {
                            mode9_3.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 3) {
                            mode9_4.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 4) {
                            mode9_5.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 5) {
                            mode9_6.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 6) {
                            mode9_7.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 7) {
                            mode9_8.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 8) {
                            mode9_9.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 9) {
                            mode9_10.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 10) {
                            mode9_11.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 11) {
                            mode9_12.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 12) {
                            mode9_13.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 13) {
                            mode9_14.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 14) {
                            mode9_15.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 15) {
                            mode9_16.setBackgroundColor(Color.parseColor(str[j]));
                        }
                    }
                    title = mydb.getModeTitle(9);
                    method = mydb.getModeMethod(9);
                    speed = mydb.getModeSpeed9();
                    template_title9.setText(title);
                    template_method9.setText(method);
                    template_speed9.setText(String.valueOf(speed));
                } else if (i ==10 ) {
                    array_list = mydb.getColorMode0();
                    str = GetStringArray(array_list);
                    for (j = 0; j < str.length; j++) {
                        if(str[j].equals("empty")) {
                            continue;
                        }
                        if (j == 0 ) {
                            mode0_1.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 1) {
                            mode0_2.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 2) {
                            mode0_3.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 3) {
                            mode0_4.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 4) {
                            mode0_5.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 5) {
                            mode0_6.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 6) {
                            mode0_7.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 7) {
                            mode0_8.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 8) {
                            mode0_9.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 9) {
                            mode0_10.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 10) {
                            mode0_11.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 11) {
                            mode0_12.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 12) {
                            mode0_13.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 13) {
                            mode0_14.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 14) {
                            mode0_15.setBackgroundColor(Color.parseColor(str[j]));
                        } else if (j == 15) {
                            mode0_16.setBackgroundColor(Color.parseColor(str[j]));
                        }
                    }
                    title = mydb.getModeTitle(0);
                    method = mydb.getModeMethod(0);
                    speed = mydb.getModeSpeed0();
                    template_title0.setText(title);
                    template_method0.setText(method);
                    template_speed0.setText(String.valueOf(speed));
                }
            }
        }
    }

    public void init_colors(Context context) {
        mode1_1.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode1_2.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode1_3.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode1_4.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode1_5.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode1_6.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode1_7.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode1_8.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode1_9.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode1_10.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode1_11.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode1_12.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode1_13.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode1_14.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode1_15.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode1_16.setBackground(context.getResources().getDrawable(R.drawable.mode_border));

        mode2_1.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode2_2.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode2_3.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode2_4.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode2_5.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode2_6.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode2_7.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode2_8.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode2_9.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode2_10.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode2_11.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode2_12.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode2_13.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode2_14.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode2_15.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode2_16.setBackground(context.getResources().getDrawable(R.drawable.mode_border));

        mode3_1.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode3_2.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode3_3.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode3_4.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode3_5.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode3_6.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode3_7.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode3_8.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode3_9.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode3_10.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode3_11.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode3_12.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode3_13.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode3_14.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode3_15.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode3_16.setBackground(context.getResources().getDrawable(R.drawable.mode_border));

        mode4_1.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode4_2.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode4_3.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode4_4.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode4_5.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode4_6.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode4_7.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode4_8.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode4_9.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode4_10.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode4_11.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode4_12.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode4_13.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode4_14.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode4_15.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode4_16.setBackground(context.getResources().getDrawable(R.drawable.mode_border));

        mode5_1.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode5_2.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode5_3.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode5_4.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode5_5.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode5_6.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode5_7.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode5_8.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode5_9.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode5_10.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode5_11.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode5_12.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode5_13.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode5_14.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode5_15.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode5_16.setBackground(context.getResources().getDrawable(R.drawable.mode_border));

        mode6_1.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode6_2.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode6_3.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode6_4.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode6_5.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode6_6.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode6_7.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode6_8.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode6_9.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode6_10.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode6_11.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode6_12.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode6_13.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode6_14.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode6_15.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode6_16.setBackground(context.getResources().getDrawable(R.drawable.mode_border));

        mode7_1.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode7_2.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode7_3.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode7_4.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode7_5.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode7_6.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode7_7.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode7_8.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode7_9.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode7_10.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode7_11.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode7_12.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode7_13.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode7_14.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode7_15.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode7_16.setBackground(context.getResources().getDrawable(R.drawable.mode_border));

        mode8_1.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode8_2.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode8_3.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode8_4.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode8_5.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode8_6.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode8_7.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode8_8.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode8_9.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode8_10.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode8_11.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode8_12.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode8_13.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode8_14.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode8_15.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode8_16.setBackground(context.getResources().getDrawable(R.drawable.mode_border));

        mode9_1.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode9_2.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode9_3.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode9_4.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode9_5.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode9_6.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode9_7.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode9_8.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode9_9.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode9_10.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode9_11.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode9_12.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode9_13.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode9_14.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode9_15.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode9_16.setBackground(context.getResources().getDrawable(R.drawable.mode_border));

        mode0_1.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode0_2.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode0_3.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode0_4.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode0_5.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode0_6.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode0_7.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode0_8.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode0_9.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode0_10.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode0_11.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode0_12.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode0_13.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode0_14.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode0_15.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
        mode0_16.setBackground(context.getResources().getDrawable(R.drawable.mode_border));
    }

    public void initUI_customFragment(View view, CustomFragment activity) {
        txDescription = view.findViewById(R.id.description_empty);
        flDescription = view.findViewById(R.id.fl_description);
        btnAddMode = view.findViewById(R.id.btn_add_mode);
        mode_pattern1 = view.findViewById(R.id.mode_pattern1);
        mode_pattern2 = view.findViewById(R.id.mode_pattern2);
        mode_pattern3 = view.findViewById(R.id.mode_pattern3);
        mode_pattern4 = view.findViewById(R.id.mode_pattern4);
        mode_pattern5 = view.findViewById(R.id.mode_pattern5);
        mode_pattern6 = view.findViewById(R.id.mode_pattern6);
        mode_pattern7 = view.findViewById(R.id.mode_pattern7);
        mode_pattern8 = view.findViewById(R.id.mode_pattern8);
        mode_pattern9 = view.findViewById(R.id.mode_pattern9);
        mode_pattern0 = view.findViewById(R.id.mode_pattern0);
        template_title1 = view.findViewById(R.id.template_title_1);
        template_method1 = view.findViewById(R.id.template_method_1);
        template_speed1 = view.findViewById(R.id.template_speed_1);
        template_title2 = view.findViewById(R.id.template_title_2);
        template_method2 = view.findViewById(R.id.template_method_2);
        template_speed2 = view.findViewById(R.id.template_speed_2);
        template_title3 = view.findViewById(R.id.template_title_3);
        template_method3 = view.findViewById(R.id.template_method_3);
        template_speed3 = view.findViewById(R.id.template_speed_3);
        template_title4 = view.findViewById(R.id.template_title_4);
        template_method4 = view.findViewById(R.id.template_method_4);
        template_speed4 = view.findViewById(R.id.template_speed_4);
        template_title5 = view.findViewById(R.id.template_title_5);
        template_method5 = view.findViewById(R.id.template_method_5);
        template_speed5 = view.findViewById(R.id.template_speed_5);
        template_title6 = view.findViewById(R.id.template_title_6);
        template_method6 = view.findViewById(R.id.template_method_6);
        template_speed6 = view.findViewById(R.id.template_speed_6);
        template_title7 = view.findViewById(R.id.template_title_7);
        template_method7 = view.findViewById(R.id.template_method_7);
        template_speed7 = view.findViewById(R.id.template_speed_7);
        template_title8 = view.findViewById(R.id.template_title_8);
        template_method8 = view.findViewById(R.id.template_method_8);
        template_speed8 = view.findViewById(R.id.template_speed_8);
        template_title9 = view.findViewById(R.id.template_title_9);
        template_method9 = view.findViewById(R.id.template_method_9);
        template_speed9 = view.findViewById(R.id.template_speed_9);
        template_title0 = view.findViewById(R.id.template_title_0);
        template_method0 = view.findViewById(R.id.template_method_0);
        template_speed0 = view.findViewById(R.id.template_speed_0);

        btn_group1 = view.findViewById(R.id.rl_btn_group1);
        btn_group2 = view.findViewById(R.id.rl_btn_group2);
        btn_group3 = view.findViewById(R.id.rl_btn_group3);
        btn_group4 = view.findViewById(R.id.rl_btn_group4);
        btn_group5 = view.findViewById(R.id.rl_btn_group5);
        btn_group6 = view.findViewById(R.id.rl_btn_group6);
        btn_group7 = view.findViewById(R.id.rl_btn_group7);
        btn_group8 = view.findViewById(R.id.rl_btn_group8);
        btn_group9 = view.findViewById(R.id.rl_btn_group9);
        btn_group0 = view.findViewById(R.id.rl_btn_group0);

        btnDelete1 = view.findViewById(R.id.btn_delete1);
        btnDelete2 = view.findViewById(R.id.btn_delete2);
        btnDelete3 = view.findViewById(R.id.btn_delete3);
        btnDelete4 = view.findViewById(R.id.btn_delete4);
        btnDelete5 = view.findViewById(R.id.btn_delete5);
        btnDelete6 = view.findViewById(R.id.btn_delete6);
        btnDelete7 = view.findViewById(R.id.btn_delete7);
        btnDelete8 = view.findViewById(R.id.btn_delete8);
        btnDelete9 = view.findViewById(R.id.btn_delete9);
        btnDelete0 = view.findViewById(R.id.btn_delete0);
        btnEdit1 = view.findViewById(R.id.btn_edit1);
        btnEdit2 = view.findViewById(R.id.btn_edit2);
        btnEdit3 = view.findViewById(R.id.btn_edit3);
        btnEdit4 = view.findViewById(R.id.btn_edit4);
        btnEdit5 = view.findViewById(R.id.btn_edit5);
        btnEdit6 = view.findViewById(R.id.btn_edit6);
        btnEdit7 = view.findViewById(R.id.btn_edit7);
        btnEdit8 = view.findViewById(R.id.btn_edit8);
        btnEdit9 = view.findViewById(R.id.btn_edit9);
        btnEdit0 = view.findViewById(R.id.btn_edit0);
        mode1_1 = view.findViewById(R.id.mode1_1);
        mode1_2 = view.findViewById(R.id.mode1_2);
        mode1_3 = view.findViewById(R.id.mode1_3);
        mode1_4 = view.findViewById(R.id.mode1_4);
        mode1_5 = view.findViewById(R.id.mode1_5);
        mode1_6 = view.findViewById(R.id.mode1_6);
        mode1_7 = view.findViewById(R.id.mode1_7);
        mode1_8 = view.findViewById(R.id.mode1_8);
        mode1_9 = view.findViewById(R.id.mode1_9);
        mode1_10 = view.findViewById(R.id.mode1_10);
        mode1_11 = view.findViewById(R.id.mode1_11);
        mode1_12 = view.findViewById(R.id.mode1_12);
        mode1_13 = view.findViewById(R.id.mode1_13);
        mode1_14 = view.findViewById(R.id.mode1_14);
        mode1_15 = view.findViewById(R.id.mode1_15);
        mode1_16 = view.findViewById(R.id.mode1_16);

        mode2_1 = view.findViewById(R.id.mode2_1);
        mode2_2 = view.findViewById(R.id.mode2_2);
        mode2_3 = view.findViewById(R.id.mode2_3);
        mode2_4 = view.findViewById(R.id.mode2_4);
        mode2_5 = view.findViewById(R.id.mode2_5);
        mode2_6 = view.findViewById(R.id.mode2_6);
        mode2_7 = view.findViewById(R.id.mode2_7);
        mode2_8 = view.findViewById(R.id.mode2_8);
        mode2_9 = view.findViewById(R.id.mode2_9);
        mode2_10 = view.findViewById(R.id.mode2_10);
        mode2_11 = view.findViewById(R.id.mode2_11);
        mode2_12 = view.findViewById(R.id.mode2_12);
        mode2_13 = view.findViewById(R.id.mode2_13);
        mode2_14 = view.findViewById(R.id.mode2_14);
        mode2_15 = view.findViewById(R.id.mode2_15);
        mode2_16 = view.findViewById(R.id.mode2_16);

        mode3_1 = view.findViewById(R.id.mode3_1);
        mode3_2 = view.findViewById(R.id.mode3_2);
        mode3_3 = view.findViewById(R.id.mode3_3);
        mode3_4 = view.findViewById(R.id.mode3_4);
        mode3_5 = view.findViewById(R.id.mode3_5);
        mode3_6 = view.findViewById(R.id.mode3_6);
        mode3_7 = view.findViewById(R.id.mode3_7);
        mode3_8 = view.findViewById(R.id.mode3_8);
        mode3_9 = view.findViewById(R.id.mode3_9);
        mode3_10 = view.findViewById(R.id.mode3_10);
        mode3_11 = view.findViewById(R.id.mode3_11);
        mode3_12 = view.findViewById(R.id.mode3_12);
        mode3_13 = view.findViewById(R.id.mode3_13);
        mode3_14 = view.findViewById(R.id.mode3_14);
        mode3_15 = view.findViewById(R.id.mode3_15);
        mode3_16 = view.findViewById(R.id.mode3_16);

        mode4_1 = view.findViewById(R.id.mode4_1);
        mode4_2 = view.findViewById(R.id.mode4_2);
        mode4_3 = view.findViewById(R.id.mode4_3);
        mode4_4 = view.findViewById(R.id.mode4_4);
        mode4_5 = view.findViewById(R.id.mode4_5);
        mode4_6 = view.findViewById(R.id.mode4_6);
        mode4_7 = view.findViewById(R.id.mode4_7);
        mode4_8 = view.findViewById(R.id.mode4_8);
        mode4_9 = view.findViewById(R.id.mode4_9);
        mode4_10 = view.findViewById(R.id.mode4_10);
        mode4_11 = view.findViewById(R.id.mode4_11);
        mode4_12 = view.findViewById(R.id.mode4_12);
        mode4_13 = view.findViewById(R.id.mode4_13);
        mode4_14 = view.findViewById(R.id.mode4_14);
        mode4_15 = view.findViewById(R.id.mode4_15);
        mode4_16 = view.findViewById(R.id.mode4_16);

        mode5_1 = view.findViewById(R.id.mode5_1);
        mode5_2 = view.findViewById(R.id.mode5_2);
        mode5_3 = view.findViewById(R.id.mode5_3);
        mode5_4 = view.findViewById(R.id.mode5_4);
        mode5_5 = view.findViewById(R.id.mode5_5);
        mode5_6 = view.findViewById(R.id.mode5_6);
        mode5_7 = view.findViewById(R.id.mode5_7);
        mode5_8 = view.findViewById(R.id.mode5_8);
        mode5_9 = view.findViewById(R.id.mode5_9);
        mode5_10 = view.findViewById(R.id.mode5_10);
        mode5_11 = view.findViewById(R.id.mode5_11);
        mode5_12 = view.findViewById(R.id.mode5_12);
        mode5_13 = view.findViewById(R.id.mode5_13);
        mode5_14 = view.findViewById(R.id.mode5_14);
        mode5_15 = view.findViewById(R.id.mode5_15);
        mode5_16 = view.findViewById(R.id.mode5_16);

        mode6_1 = view.findViewById(R.id.mode6_1);
        mode6_2 = view.findViewById(R.id.mode6_2);
        mode6_3 = view.findViewById(R.id.mode6_3);
        mode6_4 = view.findViewById(R.id.mode6_4);
        mode6_5 = view.findViewById(R.id.mode6_5);
        mode6_6 = view.findViewById(R.id.mode6_6);
        mode6_7 = view.findViewById(R.id.mode6_7);
        mode6_8 = view.findViewById(R.id.mode6_8);
        mode6_9 = view.findViewById(R.id.mode6_9);
        mode6_10 = view.findViewById(R.id.mode6_10);
        mode6_11 = view.findViewById(R.id.mode6_11);
        mode6_12 = view.findViewById(R.id.mode6_12);
        mode6_13 = view.findViewById(R.id.mode6_13);
        mode6_14 = view.findViewById(R.id.mode6_14);
        mode6_15 = view.findViewById(R.id.mode6_15);
        mode6_16 = view.findViewById(R.id.mode6_16);

        mode7_1 = view.findViewById(R.id.mode7_1);
        mode7_2 = view.findViewById(R.id.mode7_2);
        mode7_3 = view.findViewById(R.id.mode7_3);
        mode7_4 = view.findViewById(R.id.mode7_4);
        mode7_5 = view.findViewById(R.id.mode7_5);
        mode7_6 = view.findViewById(R.id.mode7_6);
        mode7_7 = view.findViewById(R.id.mode7_7);
        mode7_8 = view.findViewById(R.id.mode7_8);
        mode7_9 = view.findViewById(R.id.mode7_9);
        mode7_10 = view.findViewById(R.id.mode7_10);
        mode7_11 = view.findViewById(R.id.mode7_11);
        mode7_12 = view.findViewById(R.id.mode7_12);
        mode7_13 = view.findViewById(R.id.mode7_13);
        mode7_14 = view.findViewById(R.id.mode7_14);
        mode7_15 = view.findViewById(R.id.mode7_15);
        mode7_16 = view.findViewById(R.id.mode7_16);

        mode8_1 = view.findViewById(R.id.mode8_1);
        mode8_2 = view.findViewById(R.id.mode8_2);
        mode8_3 = view.findViewById(R.id.mode8_3);
        mode8_4 = view.findViewById(R.id.mode8_4);
        mode8_5 = view.findViewById(R.id.mode8_5);
        mode8_6 = view.findViewById(R.id.mode8_6);
        mode8_7 = view.findViewById(R.id.mode8_7);
        mode8_8 = view.findViewById(R.id.mode8_8);
        mode8_9 = view.findViewById(R.id.mode8_9);
        mode8_10 = view.findViewById(R.id.mode8_10);
        mode8_11 = view.findViewById(R.id.mode8_11);
        mode8_12 = view.findViewById(R.id.mode8_12);
        mode8_13 = view.findViewById(R.id.mode8_13);
        mode8_14 = view.findViewById(R.id.mode8_14);
        mode8_15 = view.findViewById(R.id.mode8_15);
        mode8_16 = view.findViewById(R.id.mode8_16);

        mode9_1 = view.findViewById(R.id.mode9_1);
        mode9_2 = view.findViewById(R.id.mode9_2);
        mode9_3 = view.findViewById(R.id.mode9_3);
        mode9_4 = view.findViewById(R.id.mode9_4);
        mode9_5 = view.findViewById(R.id.mode9_5);
        mode9_6 = view.findViewById(R.id.mode9_6);
        mode9_7 = view.findViewById(R.id.mode9_7);
        mode9_8 = view.findViewById(R.id.mode9_8);
        mode9_9 = view.findViewById(R.id.mode9_9);
        mode9_10 = view.findViewById(R.id.mode9_10);
        mode9_11 = view.findViewById(R.id.mode9_11);
        mode9_12 = view.findViewById(R.id.mode9_12);
        mode9_13 = view.findViewById(R.id.mode9_13);
        mode9_14 = view.findViewById(R.id.mode9_14);
        mode9_15 = view.findViewById(R.id.mode9_15);
        mode9_16 = view.findViewById(R.id.mode9_16);

        mode0_1 = view.findViewById(R.id.mode0_1);
        mode0_2 = view.findViewById(R.id.mode0_2);
        mode0_3 = view.findViewById(R.id.mode0_3);
        mode0_4 = view.findViewById(R.id.mode0_4);
        mode0_5 = view.findViewById(R.id.mode0_5);
        mode0_6 = view.findViewById(R.id.mode0_6);
        mode0_7 = view.findViewById(R.id.mode0_7);
        mode0_8 = view.findViewById(R.id.mode0_8);
        mode0_9 = view.findViewById(R.id.mode0_9);
        mode0_10 = view.findViewById(R.id.mode0_10);
        mode0_11 = view.findViewById(R.id.mode0_11);
        mode0_12 = view.findViewById(R.id.mode0_12);
        mode0_13 = view.findViewById(R.id.mode0_13);
        mode0_14 = view.findViewById(R.id.mode0_14);
        mode0_15 = view.findViewById(R.id.mode0_15);
        mode0_16 = view.findViewById(R.id.mode0_16);
        btnAddMode.setOnClickListener((View.OnClickListener) activity);



        mode_pattern1.setOnClickListener((View.OnClickListener) activity);
        mode_pattern2.setOnClickListener((View.OnClickListener) activity);
        mode_pattern3.setOnClickListener((View.OnClickListener) activity);
        mode_pattern4.setOnClickListener((View.OnClickListener) activity);
        mode_pattern5.setOnClickListener((View.OnClickListener) activity);
        mode_pattern6.setOnClickListener((View.OnClickListener) activity);
        mode_pattern7.setOnClickListener((View.OnClickListener) activity);
        mode_pattern8.setOnClickListener((View.OnClickListener) activity);
        mode_pattern9.setOnClickListener((View.OnClickListener) activity);
        mode_pattern0.setOnClickListener((View.OnClickListener) activity);


        btnDelete1.setOnClickListener((View.OnClickListener) activity);
        btnDelete2.setOnClickListener((View.OnClickListener) activity);
        btnDelete3.setOnClickListener((View.OnClickListener) activity);
        btnDelete4.setOnClickListener((View.OnClickListener) activity);
        btnDelete5.setOnClickListener((View.OnClickListener) activity);
        btnDelete6.setOnClickListener((View.OnClickListener) activity);
        btnDelete7.setOnClickListener((View.OnClickListener) activity);
        btnDelete8.setOnClickListener(activity);
        btnDelete9.setOnClickListener(activity);
        btnDelete0.setOnClickListener(activity);

        btnEdit1.setOnClickListener(activity);
        btnEdit2.setOnClickListener(activity);
        btnEdit3.setOnClickListener(activity);
        btnEdit4.setOnClickListener(activity);
        btnEdit5.setOnClickListener(activity);
        btnEdit6.setOnClickListener(activity);
        btnEdit7.setOnClickListener(activity);
        btnEdit8.setOnClickListener(activity);
        btnEdit9.setOnClickListener(activity);
        btnEdit0.setOnClickListener(activity);


    }

    public void removeMode(int deletedMode) {
        int total = mydb.getCount();
        mydb.insertCount(total-1);
        int i,j;
        ArrayList array_list,array_bright, array_pointx, array_pointy;
        String mode_title, mode_method;
        int mode_speed;
        String[] str;
        Integer[] str_bright;
        Integer[] str_pointx;
        Integer[] str_pointy;
        for (i=deletedMode; i<total; i++) {
            if(i==1) {
                array_list = mydb.getColorMode2();
                array_bright = mydb.getModeBrightness2();
                array_pointx = mydb.getModePointX1();
                array_pointy = mydb.getModePointY1();
                mode_title = mydb.getModeTitle(2);
                mode_method = mydb.getModeMethod(2);
                mode_speed = mydb.getModeSpeed2();
                str = GetStringArray(array_list);
                str_bright = GetIntegerArray(array_bright);
                str_pointx = GetIntegerArray(array_pointx);
                str_pointy = GetIntegerArray(array_pointy);

                mydb.updateColorMode1();
                for(j=0; j<16; j++){
                    mydb.insertColorMode1(str[j], str_bright[j],str_pointx[j], str_pointy[j], mode_title, mode_method, mode_speed);
                }

            } else if (i==2) {
                array_list = mydb.getColorMode3();
                array_bright = mydb.getModeBrightness3();
                array_pointx = mydb.getModePointX3();
                array_pointy = mydb.getModePointY3();
                mode_title = mydb.getModeTitle(3);
                mode_method = mydb.getModeMethod(3);
                mode_speed = mydb.getModeSpeed3();
                str = GetStringArray(array_list);
                str_bright = GetIntegerArray(array_bright);
                str_pointx = GetIntegerArray(array_pointx);
                str_pointy = GetIntegerArray(array_pointy);
                mydb.updateColorMode2();
                for(j=0; j<16; j++){
                    mydb.insertColorMode2(str[j], str_bright[j],str_pointx[j], str_pointy[j], mode_title, mode_method, mode_speed);
                }
            } else if (i==3) {
                array_list = mydb.getColorMode4();
                array_bright = mydb.getModeBrightness4();
                array_pointx = mydb.getModePointX4();
                array_pointy = mydb.getModePointY4();
                mode_title = mydb.getModeTitle(4);
                mode_method = mydb.getModeMethod(4);
                mode_speed = mydb.getModeSpeed4();
                str = GetStringArray(array_list);
                str_bright = GetIntegerArray(array_bright);
                str_pointx = GetIntegerArray(array_pointx);
                str_pointy = GetIntegerArray(array_pointy);
                mydb.updateColorMode3();
                for(j=0; j<16; j++){
                    mydb.insertColorMode3(str[j], str_bright[j],str_pointx[j], str_pointy[j], mode_title, mode_method, mode_speed);
                }
            } else if (i==4) {
                array_list = mydb.getColorMode5();
                array_bright = mydb.getModeBrightness5();
                array_pointx = mydb.getModePointX5();
                array_pointy = mydb.getModePointY5();
                mode_title = mydb.getModeTitle(5);
                mode_method = mydb.getModeMethod(5);
                mode_speed = mydb.getModeSpeed5();
                str = GetStringArray(array_list);
                str_bright = GetIntegerArray(array_bright);
                str_pointx = GetIntegerArray(array_pointx);
                str_pointy = GetIntegerArray(array_pointy);
                mydb.updateColorMode4();
                for(j=0; j<16; j++){
                    mydb.insertColorMode4(str[j], str_bright[j],str_pointx[j], str_pointy[j], mode_title, mode_method, mode_speed);
                }
            } else if (i==5) {

                array_list = mydb.getColorMode6();
                array_bright = mydb.getModeBrightness6();
                array_pointx = mydb.getModePointX6();
                array_pointy = mydb.getModePointY6();
                mode_title = mydb.getModeTitle(6);
                mode_method = mydb.getModeMethod(6);
                mode_speed = mydb.getModeSpeed6();
                str = GetStringArray(array_list);
                str_bright = GetIntegerArray(array_bright);
                str_pointx = GetIntegerArray(array_pointx);
                str_pointy = GetIntegerArray(array_pointy);
                mydb.updateColorMode5();
                for(j=0; j<16; j++){
                    mydb.insertColorMode5(str[j], str_bright[j],str_pointx[j], str_pointy[j], mode_title, mode_method, mode_speed);
                }
            } else if (i==6) {
                array_list = mydb.getColorMode7();
                array_bright = mydb.getModeBrightness7();
                array_pointx = mydb.getModePointX7();
                array_pointy = mydb.getModePointY7();
                mode_title = mydb.getModeTitle(7);
                mode_method = mydb.getModeMethod(7);
                mode_speed = mydb.getModeSpeed7();
                str = GetStringArray(array_list);
                str_bright = GetIntegerArray(array_bright);
                str_pointx = GetIntegerArray(array_pointx);
                str_pointy = GetIntegerArray(array_pointy);
                mydb.updateColorMode6();
                for(j=0; j<16; j++){
                    mydb.insertColorMode6(str[j], str_bright[j],str_pointx[j], str_pointy[j], mode_title, mode_method, mode_speed);
                }
            } else if (i==7) {
                array_list = mydb.getColorMode8();
                array_bright = mydb.getModeBrightness8();
                array_pointx = mydb.getModePointX8();
                array_pointy = mydb.getModePointY8();
                mode_title = mydb.getModeTitle(8);
                mode_method = mydb.getModeMethod(8);
                mode_speed = mydb.getModeSpeed8();
                str = GetStringArray(array_list);
                str_bright = GetIntegerArray(array_bright);
                str_pointx = GetIntegerArray(array_pointx);
                str_pointy = GetIntegerArray(array_pointy);
                mydb.updateColorMode7();
                for(j=0; j<16; j++){
                    mydb.insertColorMode7(str[j], str_bright[j],str_pointx[j], str_pointy[j], mode_title, mode_method, mode_speed);
                }
            } else if (i==8) {
                array_list = mydb.getColorMode9();
                array_bright = mydb.getModeBrightness9();
                array_pointx = mydb.getModePointX9();
                array_pointy = mydb.getModePointY9();
                mode_title = mydb.getModeTitle(9);
                mode_method = mydb.getModeMethod(9);
                mode_speed = mydb.getModeSpeed9();
                str = GetStringArray(array_list);
                str_bright = GetIntegerArray(array_bright);
                str_pointx = GetIntegerArray(array_pointx);
                str_pointy = GetIntegerArray(array_pointy);
                mydb.updateColorMode8();
                for(j=0; j<16; j++){
                    mydb.insertColorMode8(str[j], str_bright[j],str_pointx[j], str_pointy[j], mode_title, mode_method, mode_speed);
                }
            } else if (i==9) {
                array_list = mydb.getColorMode0();
                array_bright = mydb.getModeBrightness0();
                array_pointx = mydb.getModePointX0();
                array_pointy = mydb.getModePointY0();
                mode_title = mydb.getModeTitle(0);
                mode_method = mydb.getModeMethod(0);
                mode_speed = mydb.getModeSpeed0();
                str = GetStringArray(array_list);
                str_bright = GetIntegerArray(array_bright);
                str_pointx = GetIntegerArray(array_pointx);
                str_pointy = GetIntegerArray(array_pointy);
                mydb.updateColorMode9();
                for(j=0; j<16; j++){
                    mydb.insertColorMode9(str[j], str_bright[j],str_pointx[j], str_pointy[j], mode_title, mode_method, mode_speed);
                }
            } else if (i==10) {
                mydb.updateColorMode0();
            }
        }

        for (i=1; i<=10; i++) {
            if(i==1){
                if(i==total) mydb.updateColorMode1();
            } else if(i==2) {
                if(i==total) mydb.updateColorMode2();
            } else if(i==3) {
                if(i==total) mydb.updateColorMode3();
            } else if(i==4) {
                if(i==total) mydb.updateColorMode4();
            } else if(i==5) {
                if(i==total) mydb.updateColorMode5();
            } else if(i==6) {
                if(i==total) mydb.updateColorMode6();
            } else if(i==7) {
                if(i==total) mydb.updateColorMode7();
            } else if(i==8) {
                if(i==total) mydb.updateColorMode8();
            } else if(i==9) {
                if(i==total) mydb.updateColorMode9();
            } else if(i==10) {
                if(i==total) mydb.updateColorMode0();
            }
        }
    }

    public void setUnvisible(int count_mode) {
        for(int i=0;i <10; i++) {
            if(i==0){
                if(i>=count_mode) {
                    mode_pattern1.setVisibility(View.GONE);
                    btn_group1.setVisibility(View.GONE);
                } else {
                    mode_pattern1.setVisibility(View.VISIBLE);
                    btn_group1.setVisibility(View.VISIBLE);
                    template_title1.setText(mytitle);
                    template_method1.setText(modemethod);
                }
            } else if(i==1){
                if(i>=count_mode) {
                    btn_group2.setVisibility(View.GONE);
                    mode_pattern2.setVisibility(View.GONE);
                } else {
                    btn_group2.setVisibility(View.VISIBLE);
                    mode_pattern2.setVisibility(View.VISIBLE);
                    template_title2.setText(mytitle);
                }
            } else if(i==2){
                if(i>=count_mode) {
                    mode_pattern3.setVisibility(View.GONE);
                    btn_group3.setVisibility(View.GONE);
                } else {
                    mode_pattern3.setVisibility(View.VISIBLE);
                    btn_group3.setVisibility(View.VISIBLE);
                    template_title3.setText(mytitle);
                }
            } else if(i==3){
                if(i>=count_mode) {
                    mode_pattern4.setVisibility(View.GONE);
                    btn_group4.setVisibility(View.GONE);
                } else {
                    mode_pattern4.setVisibility(View.VISIBLE);
                    btn_group4.setVisibility(View.VISIBLE);
                    template_title4.setText(mytitle);
                }
            } else if(i==4){
                if(i>=count_mode) {
                    mode_pattern5.setVisibility(View.GONE);
                    btn_group5.setVisibility(View.GONE);
                } else {
                    mode_pattern5.setVisibility(View.VISIBLE);
                    btn_group5.setVisibility(View.VISIBLE);
                    template_title5.setText(mytitle);
                }
            } else if(i==5){
                if(i>=count_mode) {
                    mode_pattern6.setVisibility(View.GONE);
                    btn_group6.setVisibility(View.GONE);
                } else {
                    mode_pattern6.setVisibility(View.VISIBLE);
                    btn_group6.setVisibility(View.VISIBLE);
                    template_title6.setText(mytitle);
                }
            } else if(i==6){
                if(i>=count_mode) {
                    mode_pattern7.setVisibility(View.GONE);
                    btn_group7.setVisibility(View.GONE);
                } else {
                    mode_pattern7.setVisibility(View.VISIBLE);
                    btn_group7.setVisibility(View.VISIBLE);
                }
            } else if(i==7){
                if(i>=count_mode) {
                    mode_pattern8.setVisibility(View.GONE);
                    btn_group8.setVisibility(View.GONE);
                } else {
                    mode_pattern8.setVisibility(View.VISIBLE);
                    btn_group8.setVisibility(View.VISIBLE);
                }
            } else if(i==8){
                if(i>=count_mode) {
                    mode_pattern9.setVisibility(View.GONE);
                    btn_group9.setVisibility(View.GONE);
                } else {
                    mode_pattern9.setVisibility(View.VISIBLE);
                    btn_group9.setVisibility(View.VISIBLE);
                }
            } else if(i==9){
                if(i>=count_mode) {
                    mode_pattern0.setVisibility(View.GONE);
                    btn_group0.setVisibility(View.GONE);
                } else {
                    mode_pattern0.setVisibility(View.VISIBLE);
                    btn_group0.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void addClickForRemoveBtn(View v) {

        if(v == btnDelete1) {
            removeMode(1);
        } else if(v == btnDelete2) {
            removeMode(2);
        } else if(v == btnDelete3) {
            removeMode(3);
        } else if(v == btnDelete4) {
            removeMode(4);
        } else if(v == btnDelete5) {
            removeMode(5);
        } else if(v == btnDelete6) {
            removeMode(6);
        } else if(v == btnDelete7) {
            removeMode(7);
        } else if(v == btnDelete8) {
            removeMode(8);
        } else if(v == btnDelete9) {
            removeMode(9);
        } else if(v == btnDelete0) {
            removeMode(10);
        }
    }

}
