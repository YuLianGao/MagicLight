package com.skydoves.magiclight_ble_control;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.skydoves.colorpickerview.ColorPickerView;
import com.skydoves.magiclight_ble_control.main.HomePageTabActivity;
import com.skydoves.magiclight_ble_control.model.DBHelper;
import com.skydoves.magiclight_ble_control.model.ModeClass;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static com.skydoves.magiclight_ble_control.MyColorPickerView1.center_x;
import static com.skydoves.magiclight_ble_control.MyColorPickerView1.center_y;
import static com.skydoves.magiclight_ble_control.MyColorPickerView1.selected_x;
import static com.skydoves.magiclight_ble_control.MyColorPickerView1.selected_y;

public class ColorModeActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvActivityTitle;

    public static TextView mode1, mode2, mode3, mode4, mode5, mode6, mode7, mode8, mode9, mode10, mode11, mode12, mode13, mode14, mode15, mode16;
    Dialog dialog;
    ImageView btnback;
    public static TextView tvmodeTitle, tvmodeSpeed, tvbrightness;
    public static int modespeed = 200, brightness = 200;

    public static TextView txRedView, txGreenView, txBlueView;
    public static View selectedColor;

    Button btnsave;

    public static String[] mycolors;
    public static Integer[] mybright, mypointx, mypointy;
    int color = 0;
    public static int flag_mode=0;
    public static String colorstr, colorbrightness="64", colorbrightnessDec = "100%", mytitle = "", modemethod = "";
    public static RadioButton rdFade, rdJump, rdStrobe;
    public static DiscreteSeekBar discreteSeekBar1;
    public static DBHelper mydb;
    public static String editable;
    public static int editModeNumber =0;
    public static int default_color = 0;
    ModeClass modeClass;
    MyColorPickerView1 colorPickerView;

    CustomThread customThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_mode);
        getSupportActionBar().hide(); //<< this
        mydb = new DBHelper(this);
        modeClass = new ModeClass();
        mycolors = new String[16];
        mybright = new Integer[16];
        mypointx = new Integer[16];
        mypointy = new Integer[16];
        Bundle bundle = getIntent().getExtras();
        editable = bundle.getString("editable");
        editModeNumber = bundle.getInt("edit_mode_number");

        initUI();
        if(editable.equals("edit")){
            tvActivityTitle.setVisibility(View.GONE);
            modeClass.init_editCreate();
        }
        else {
            tvActivityTitle.setVisibility(View.VISIBLE);
            modeClass.init_createCreate();

        }

        customThread = new CustomThread();
        customThread.start();

    }

    public static String[] GetStringArray(ArrayList<String> arr) {
        String str[] = new String[arr.size()];
        Object[] objArr = arr.toArray();
        int i = 0;
        for (Object obj : objArr) {
            str[i++] = (String)obj;
        }
        return str;
    }

    public static Integer[] GetIntegerArray(ArrayList<Integer> arr) {
        Integer str[] = new Integer[arr.size()];
        Object[] objArr = arr.toArray();
        int i = 0;
        for (Object obj : objArr) {
            str[i++] = (Integer)obj;
        }
        return str;
    }

    private void initUI(){
        tvActivityTitle = findViewById(R.id.tvActivityTitle);
        btnsave = findViewById(R.id.btn_save);
        btnback = findViewById(R.id.btn_back);
        tvmodeTitle = findViewById(R.id.mode_title);
        tvmodeSpeed = this.findViewById(R.id.speed_txt);
        rdFade = findViewById(R.id.rd_fade);
        rdJump = findViewById(R.id.rd_jump);
        rdStrobe = findViewById(R.id.rd_strobe);
        rdFade.setOnClickListener(this);
        rdJump.setOnClickListener(this);
        rdStrobe.setOnClickListener(this);

        discreteSeekBar1= (DiscreteSeekBar) findViewById(R.id.seekBar_mode);
        discreteSeekBar1.setOnProgressChangeListener(progressChangeListener);
        discreteSeekBar1.setMax(100);
        discreteSeekBar1.setMin(0);
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm", Locale.getDefault());

        if(!editable.equals("edit")) {
            String currentDateandTime = sdf.format(new Date());
            tvmodeTitle.setText("Custom mode" + currentDateandTime);
            mytitle = tvmodeTitle.getText().toString();
            rdFade.setChecked(true);
            modemethod = "Fade";
        }
        else {
            mytitle = mydb.getModeTitle(editModeNumber);
            tvmodeTitle.setText(mytitle);
            modemethod = mydb.getModeMethod(editModeNumber);
            if(modemethod == "Fade") rdFade.setChecked(true);
            else if(modemethod == "Jump") rdJump.setChecked(true);
            else if(modemethod == "Strobe") rdStrobe.setChecked(true);

        }

        for(int j = 0; j < mydb.getModePointX1().size(); j++)
        {
            mypointx[j] = mydb.getModePointX1().get(j);
            mypointy[j] = mydb.getModePointY1().get(j);
        }
        btnsave.setOnClickListener(this);
        btnback.setOnClickListener(this);
        modeClass.initUI_colorMode(this);

    }

    @Override
    public void onClick(View v) {
        if(v == btnsave) {
            saveColors();
            onBackPressed();
        } else if(v == btnback) {
            onBackPressed();
        } else if(v == rdFade) {
            modemethod = "Fade";
            customThread.exit(true);
            customThread.exit(false);
        } else if(v == rdJump) {
                modemethod = "Jump";
            customThread.exit(true);
            customThread.exit(false);
        } else if(v == rdStrobe) {
            modemethod = "Strobe";
            customThread.exit(true);
            customThread.exit(false);
        } else {
            modeClass.init_ClickEventColorModeActivity(v);
            popup_dialog();
        }

    }

    private void saveColors(){
        modeClass.init_saveColors();
    }

    public void popup_dialog() {
        color =0;
        default_color = 0;
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_custom);
        txRedView = dialog.findViewById(R.id.red_txt);
        txGreenView = dialog.findViewById(R.id.green_txt);
        txBlueView = dialog.findViewById(R.id.blue_txt);
        selectedColor = dialog.findViewById(R.id.selected_color);

        int nSpeed = 100;

        DiscreteSeekBar discreteSeekBar;
        Button cancelbtn, confirmbtn;
        colorPickerView= (MyColorPickerView1) dialog.findViewById(R.id.colorPickerView);
       // colorPickerView.initSelector(mypointx[flag_mode - 1], mypointy[flag_mode - 1]);
        colorPickerView.setColorListener(colorListener);

        tvbrightness= dialog.findViewById(R.id.speed_txt);
        tvbrightness.setText("Brightness: " + Integer.toString(nSpeed));
        discreteSeekBar= (DiscreteSeekBar) dialog.findViewById(R.id.seekBar);
        discreteSeekBar.setOnProgressChangeListener(progressChangeListener1);
        discreteSeekBar.setMax(100);
        discreteSeekBar.setMin(1);

        if(!mycolors[flag_mode-1].equals("empty")) {
            String str2;
            if(mycolors[flag_mode-1].length()>8) str2 = mycolors[flag_mode-1].substring(3,9);
            else str2 = mycolors[flag_mode-1].substring(1,7);
            try {
                selectedColor.setBackgroundColor(Color.parseColor("#" + str2));
            }
            catch (Exception e){
            }
            txRedView.setText(String.valueOf(Integer.decode("0x"+str2.substring(0,2))));
            txGreenView.setText(String.valueOf(Integer.decode("0x"+str2.substring(2,4))));
            txBlueView.setText(String.valueOf(Integer.decode("0x"+str2.substring(4,6))));
            Point clrPoint = new Point( mypointx[flag_mode - 1], mypointy[flag_mode - 1]);
            colorPickerView.setSelectedPoint(clrPoint);
        }
        else
        {
            Point ptInit = new Point(305, 413);
            colorPickerView.setSelectedPoint(ptInit);
            txRedView.setText("255");
            txGreenView.setText("255");
            txBlueView.setText("255");

        }

        if(!editable.equals("edit")) {
            brightness =100;
            colorstr = mycolors[flag_mode-1];
            discreteSeekBar.setProgress(100);
            modeClass.init_rgbColor();

        } else {
            brightness =mybright[flag_mode-1];
            colorstr = mycolors[flag_mode-1];
            discreteSeekBar.setProgress(brightness);
        }

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
                Point selPos =  colorPickerView.getSelectorPos();
                mypointx[flag_mode - 1] = selPos.x;
                mypointy[flag_mode - 1] = selPos.y;
                selected_x = selPos.x;
                selected_y = selPos.y;
                String redStr = txRedView.getText().toString();
                String greenStr = txGreenView.getText().toString();
                String blueStr = txBlueView.getText().toString();
                if (Integer.parseInt(redStr) <= 0xF)
                    redStr = "0" + Integer.toHexString(Integer.parseInt(redStr));
                else
                    redStr =  Integer.toHexString(Integer.parseInt(redStr));
                if (Integer.parseInt(greenStr) <= 0xF)
                    greenStr = "0" + Integer.toHexString(Integer.parseInt(greenStr));
                else
                    greenStr =  Integer.toHexString(Integer.parseInt(greenStr));
                if (Integer.parseInt(blueStr) <= 0xF)
                    blueStr = "0" + Integer.toHexString(Integer.parseInt(blueStr));
                else
                    blueStr =  Integer.toHexString(Integer.parseInt(blueStr));

                colorstr = "#" +  redStr + greenStr + blueStr;
                setModeColor();
                dialog.dismiss();

            }
        });
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        customThread.exit(true);
    }

    private DiscreteSeekBar.OnProgressChangeListener progressChangeListener = new DiscreteSeekBar.OnProgressChangeListener() {
        @Override
        public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
            tvmodeSpeed.setText("Speed: " + Integer.toString(value));

            modespeed = value;
            if(color != 0 ) {
                colorstr = Integer.toHexString(color);
                colorbrightness = Integer.toHexString(value);
                colorbrightnessDec = String.valueOf(value);
                colorbrightnessDec = colorbrightnessDec + "%";
                colorstr = "#" + colorbrightness + colorstr;
                if(dialog != null) {
                    try {
                        selectedColor = dialog.findViewById(R.id.selected_color);
                        selectedColor.setBackgroundColor(Color.parseColor(colorstr));
                    }
                    catch (Exception e)
                    {
                        byte rgb[] = getLedBytes(color, value);

                        selectedColor.setBackgroundColor(color);
                    }

                }
            }
            if(customThread != null) {
                customThread.exit(true);
                customThread.exit(false);
            }
        }

        @Override
        public void onStartTrackingTouch(DiscreteSeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(DiscreteSeekBar seekBar) {
        }
    };

    private DiscreteSeekBar.OnProgressChangeListener progressChangeListener1 = new DiscreteSeekBar.OnProgressChangeListener() {
        @Override
        public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
            tvbrightness.setText("Brightness: " + Integer.toString(value));
            brightness = value;
            try {
                if (color == 0) {
                    if (!mycolors[flag_mode - 1].equals("empty")) {
                        String[] str2 = mycolors[flag_mode - 1].split("#");
                        if (str2[1].length() == 8) colorstr = str2[1].substring(2, 8);
                        else if (str2[1].length() == 7) colorstr = str2[1].substring(1, 7);
                        else colorstr = str2[1].substring(0, 6);
                        colorbrightness = Integer.toHexString((int) ((int) value * 2.55));
                        colorbrightnessDec = String.valueOf((int) ((int) value * 2.55));
                        if (value < 7) {
                            colorstr = "#" + "0" + colorbrightness + colorstr;
                        } else {
                            colorstr = "#" + colorbrightness + colorstr;
                        }
                        if (dialog != null) {
                            selectedColor = dialog.findViewById(R.id.selected_color);
                            selectedColor.setBackgroundColor(Color.parseColor(colorstr));
                        }
                        colorbrightnessDec = String.valueOf(value) + "%";
                    }
                } else {
                    colorstr = Integer.toHexString(color);
                    colorbrightness = Integer.toHexString((int) ((int) value * 2.55));
                    colorbrightnessDec = String.valueOf((int) ((int) value * 2.55));
                    if (value < 7) {
                        colorstr = "#" + "0" + colorbrightness + colorstr;
                    } else {
                        colorstr = "#" + colorbrightness + colorstr;
                    }
                    if (dialog != null) {
                        selectedColor = dialog.findViewById(R.id.selected_color);
                        try {
                            selectedColor.setBackgroundColor(Color.parseColor(colorstr));
                        } catch (Exception e) {
                        }
                    }
                    colorbrightnessDec = String.valueOf(value) + "%";
                }
            }
            catch (Exception e)
            {
                int pp = 0;
            }
        }

        @Override
        public void onStartTrackingTouch(DiscreteSeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(DiscreteSeekBar seekBar) {
        }
    };

    private ColorPickerView.ColorListener colorListener = newColor -> {

        if(default_color ==1 ) {
            color = newColor;
            byte[] rgb = getLedBytes(newColor, 100);

            int rr = Color.red(newColor);
            int bb = Color.blue(newColor);
            int gg = Color.green(newColor);

            if(colorPickerView.getOutSideSel()) {
                if (rr == 255) {
                    if (gg > bb) {
                        gg = gg - bb;
                        bb = 0;
                    } else {
                        bb = bb - gg;
                        gg = 0;
                    }
                } else if (gg == 255) {
                    if (rr > bb) {
                        rr = rr - bb;
                        bb = 0;
                    } else {
                        bb = bb - rr;
                        rr = 0;
                    }
                } else if (bb == 255) {
                    if (gg > rr) {
                        gg = gg - rr;
                        rr = 0;
                    } else {
                        rr = rr - gg;
                        gg = 0;
                    }
                }
            }

            txRedView.setText(Integer.toString(rr));
            txGreenView.setText(Integer.toString(gg));
            txBlueView.setText(Integer.toString(bb));
            selectedColor.setBackgroundColor(newColor);
            colorstr = Integer.toHexString(color);
            colorstr = "#" + colorstr;
        } else if(default_color == 0 ) {
            default_color =1;
        }
    };

    private byte[] getLedBytes(int newColor, int value) {
        byte[] rgb = new byte[7];
        color = (int)Long.parseLong(String.format("%06X", (0xFFFFFF & newColor)), 16);
        rgb[0] = (byte)0x56;
        rgb[1] = (byte)(value * ((color >> 16) & 0xFF) / 100);
        rgb[2]= (byte)(value * ((color >> 8) & 0xFF) / 100);
        rgb[3] = (byte)(value * ((color >> 0) & 0xFF) / 100);
        rgb[4] = 0x00;
        rgb[5] = (byte)0xF0;
        rgb[6] = (byte)0xAA;
        return rgb;
    }

    private void setModeColor(){
        mycolors[flag_mode-1] = colorstr;
        mybright[flag_mode-1] = brightness;
        mypointx[flag_mode-1] = selected_x;
        mypointy[flag_mode-1] = selected_y;
        if(selected_x ==0 && selected_y == 0) {
            mypointx[flag_mode-1] = center_x;
            mypointy[flag_mode-1] = center_y;
        }

        if(color == 0 && flag_mode > 4 && mycolors[flag_mode-1].equals("empty")){
            colorstr = "#ffffff";
            mycolors[flag_mode-1] = "#ffffff";
            mybright[flag_mode-1] = 100;
            mypointx[flag_mode-1] = center_x;
            mypointy[flag_mode-1] = center_y;
        }
        modeClass.save_modeColor();
    }

    private  class CustomThread extends Thread {
        byte[] rgb = new byte[7];
        boolean bExit = false;

        public void exit(boolean bExit){
            this.bExit = bExit;
        }

        @Override
        public void run() {
            while (!bExit) {
                ArrayList array_list = mydb.getColorMode1();
                if(array_list.size() == 0) bExit = true;
                switch (editModeNumber)
                {
                    case 1:
                        array_list = mydb.getColorMode1();
                        break;
                    case 2:
                        array_list = mydb.getColorMode2();
                        break;
                    case 3:
                        array_list = mydb.getColorMode3();
                        break;
                    case 4:
                        array_list = mydb.getColorMode4();
                        break;
                    case 5:
                        array_list = mydb.getColorMode5();
                        break;
                    case 6:
                        array_list = mydb.getColorMode6();
                        break;
                    case 7:
                        array_list = mydb.getColorMode7();
                        break;
                    case 8:
                        array_list = mydb.getColorMode8();
                        break;
                    case 9:
                        array_list = mydb.getColorMode9();
                        break;
                    case 10:
                        array_list = mydb.getColorMode0();
                        break;
                }

                String[] strArr = GetStringArray(array_list);

                for (int i = 0; i < array_list.size(); i++) {
                    String clr = strArr[i];
                    if (!clr.equals("empty")) {
                        //nCount++;
                        if (modemethod.equals("Fade") && !bExit) {
                            for (int kk = 0; kk < 100; kk++) {
                                rgb = getLedBytes(Color.parseColor(clr), kk);
                                com.skydoves.magiclight_ble_control.main.HomePageTabActivity.controlLed(rgb);
                                try {
                                    Thread.sleep(300 / modespeed);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            for (int kk = 100; kk > 0; kk--) {
                                rgb = getLedBytes(Color.parseColor(clr), kk);
                                com.skydoves.magiclight_ble_control.main.HomePageTabActivity.controlLed(rgb);
                                try {
                                    Thread.sleep(300 / modespeed);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        if (modemethod.equals("Jump") && !bExit) {
                            rgb = getLedBytes(Color.parseColor(clr), 100);
                            com.skydoves.magiclight_ble_control.main.HomePageTabActivity.controlLed(rgb);
                            try {
                                Thread.sleep(10000 / modespeed);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }

                        if (modemethod.equals("Strobe") && !bExit) {
                            rgb = getLedBytes(Color.parseColor(clr), 100);
                            com.skydoves.magiclight_ble_control.main.HomePageTabActivity.controlLed(rgb);
                            try {
                                Thread.sleep(20000 / modespeed);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            rgb = getLedBytes(Color.parseColor(clr), 0);
                            com.skydoves.magiclight_ble_control.main.HomePageTabActivity.controlLed(rgb);
                            try {
                                Thread.sleep(20000 / modespeed);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }

                    }
                }
            }
        }

    }


    private byte[] getLedWhiteBytes( int value) {
        byte[] rgb = new byte[7];
        rgb[0] = (byte)0x56;
        rgb[1] = 0x00;
        rgb[2]= 0x00;
        rgb[3] = 0x00;
        rgb[4] = (byte)(0xFF * value / 100);
        rgb[5] = (byte)0x0F;
        rgb[6] = (byte)0xAA;
        return rgb;
    }
}
