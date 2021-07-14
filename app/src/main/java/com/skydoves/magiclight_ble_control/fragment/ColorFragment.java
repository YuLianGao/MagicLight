package com.skydoves.magiclight_ble_control.fragment;


import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import com.skydoves.magiclight_ble_control.model.DBHelper;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import android.support.v4.app.Fragment;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.skydoves.colorpickerview.ColorPickerView;
import com.skydoves.magiclight_ble_control.MyColorPickerView;
import com.skydoves.magiclight_ble_control.R;
import com.skydoves.magiclight_ble_control.main.HomePageTabActivity;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;



/**
 * A simple {@link Fragment} subclass.
 */
public class ColorFragment extends Fragment {

    MyColorPickerView colorPickerView;
    DiscreteSeekBar discreteSeekBar;
    int color = 0;

    private TextView    txRedView;
    private TextView    txGreenView;
    private TextView   txBlueView;

    private TextView    tvRedView;
    private TextView    tvGreenView;
    private TextView   tvBlueView;

    private LinearLayout ll_rgb;

    public static DBHelper mydb ;
    //View selectedColor;
    TextView txtSpeed;  int nSpeed = 100;
    private  int ledbright =  100;
    public ColorFragment() {
        // Required empty public constructor
    }

    DiscreteSeekBar redSeekBar, greenSeekBar, blueSeekBar;
    int valRed=0, valGreen=0, valBlue=0;

    Button btn_diy1, btn_diy2, btn_diy3, btn_diy4, btn_diy5;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_leaderboard, container, false);
        colorPickerView= (MyColorPickerView) view.findViewById(R.id.colorPickerView);
        colorPickerView.setColorListener(colorListener);

        txBlueView = view.findViewById(R.id.blue_txt);
        txGreenView = view.findViewById(R.id.green_txt);
        txRedView = view.findViewById(R.id.red_txt);

        ll_rgb = view.findViewById(R.id.ll_rgb);
        ll_rgb.bringToFront();
        ll_rgb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                     // set the custom layout
                final View customLayout = getLayoutInflater().inflate(R.layout.dialog_color, null);
                builder.setView(customLayout);
                // create and show the alert dialog
                Button btn_cancel = customLayout.findViewById(R.id.btn_cancel);
                Button btn_confirm = customLayout.findViewById(R.id.btn_confirm);
                AlertDialog dialog = builder.create();
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE );

                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                btn_confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int red1, green1, blue1;
                        int maxValue = valRed;
                        if(valGreen > valBlue && valGreen > valRed)
                        {
                            maxValue = valGreen;
                        }
                        else  if(valBlue > valGreen && valBlue > valRed)
                        {
                            maxValue = valBlue;
                        }

                        red1 = valRed *  255 / maxValue;
                        green1 = valGreen * 255 / maxValue ;
                        blue1 = valBlue * 255 / maxValue ;
                        dialog.dismiss();

                        int bright = maxValue * 100 / 255;
                        discreteSeekBar.setProgress(bright);

                        Point clrPoint =  colorPickerView.getSelectedPoint(red1, green1, blue1);
                        colorPickerView.setSelectedPoint(clrPoint);

                        txRedView.setText(Integer.toString(valRed));
                        txGreenView.setText(Integer.toString(valGreen));
                        txBlueView.setText(Integer.toString(valBlue));
                    }
                });

                tvBlueView = customLayout.findViewById(R.id.blue_tv);
                tvGreenView = customLayout.findViewById(R.id.green_tv);
                tvRedView = customLayout.findViewById(R.id.red_tv);

                redSeekBar = customLayout.findViewById(R.id.red_seekBar);
                redSeekBar= (DiscreteSeekBar) customLayout.findViewById(R.id.red_seekBar);
                redSeekBar.setOnProgressChangeListener(redChangeListener);
                redSeekBar.setMax(255);
                valRed = Integer.parseInt(txRedView.getText().toString());

                greenSeekBar = customLayout.findViewById(R.id.green_seekBar);
                greenSeekBar= (DiscreteSeekBar) customLayout.findViewById(R.id.green_seekBar);
                greenSeekBar.setOnProgressChangeListener(greenChangeListener);
                greenSeekBar.setMax(255);
                valGreen = Integer.parseInt(txGreenView.getText().toString());

                blueSeekBar = customLayout.findViewById(R.id.blue_seekBar);
                blueSeekBar= (DiscreteSeekBar) customLayout.findViewById(R.id.blue_seekBar);
                blueSeekBar.setOnProgressChangeListener(blueChangeListener);
                blueSeekBar.setMax(255);
                valBlue = Integer.parseInt(txBlueView.getText().toString());


                if(valBlue == valGreen && valRed == valGreen)
                {
                    valRed = 255 * ledbright / 100;
                    valGreen = 255 * ledbright / 100;
                    valBlue = 255 * ledbright / 100;
                }

                redSeekBar.setProgress(valRed);
                greenSeekBar.setProgress(valGreen);
                blueSeekBar.setProgress(valBlue);

                tvRedView.setText(Integer.toString(valRed));
                tvGreenView.setText(Integer.toString(valGreen));
                tvBlueView.setText(Integer.toString(valBlue));
                dialog.show();



            }
        });

        txtSpeed= (TextView)view.findViewById(R.id.speed_txt);
        txtSpeed.setText("Brightness: " + Integer.toString(nSpeed));

        discreteSeekBar= (DiscreteSeekBar) view.findViewById(R.id.seekBar);
        discreteSeekBar.setOnProgressChangeListener(progressChangeListener);
        discreteSeekBar.setMax(100);
        discreteSeekBar.setProgress(100);

        mydb = new DBHelper(getActivity());

        btn_diy1 = (Button) view.findViewById(R.id.btn_DIY1);
        if(mydb.getDYIColor("1") != "0")  {
            GradientDrawable shape =  new GradientDrawable();
            shape.setColor(Integer.valueOf(mydb.getDYIColor("1")));
            shape.setCornerRadius( 10f );
            shape.setStroke(2, Color.argb(0xFF,0xAA,0xAA,0xAA));
            btn_diy1.setText(mydb.getDYIBright("1") + "%");
            btn_diy1.setBackground(shape);
        }

        btn_diy1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                GradientDrawable shape =  new GradientDrawable();
                shape.setColor(colorPickerView.getColor());
                shape.setCornerRadius( 10f );
                shape.setStroke(2, Color.argb(0xFF,0xAA,0xAA,0xAA));
                btn_diy1.setText(ledbright + "%");
                //btn_diy1.setBackgroundResource(R.drawable.diy_border);
                btn_diy1.setBackground(shape);
                if(mydb.getDYIColor("1") != "0")
                    mydb.updateDIYInfo("1",String.valueOf(ledbright), String.valueOf(colorPickerView.getColor()), String.valueOf(colorPickerView.getSelectedPoint().x), String.valueOf(colorPickerView.getSelectedPoint().y));
                else
                    mydb.insertDIYInfo("1",String.valueOf(ledbright), String.valueOf(colorPickerView.getColor()), String.valueOf(colorPickerView.getSelectedPoint().x), String.valueOf(colorPickerView.getSelectedPoint().y));
                return true;
            }
        });
        btn_diy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strDYIColor1 = mydb.getDYIColor("1");
                String strDYIBright1 = mydb.getDYIBright("1");
                String strXpos = mydb.getDYIXPos("1");
                String strYpos = mydb.getDYIYPos("1");
                if(strDYIColor1 == "0")  return;

                discreteSeekBar.setProgress(Integer.valueOf(strDYIBright1));

                color = (int)Long.parseLong(String.format("%06X", (0xFFFFFF & Integer.valueOf(strDYIColor1))), 16);
                valRed = (byte)(Integer.valueOf(strDYIBright1) * ((color >> 16) & 0xFF) / 100) & 0xFF;
                valGreen= (byte)(Integer.valueOf(strDYIBright1) * ((color >> 8) & 0xFF) / 100) & 0xFF;
                valBlue = (byte)(Integer.valueOf(strDYIBright1) * ((color >> 0) & 0xFF) / 100)& 0xFF;

                Point clrPoint =  new Point();
                clrPoint.x = Integer.valueOf(strXpos);
                clrPoint.y = Integer.valueOf(strYpos);
                colorPickerView.setSelectedPoint(clrPoint);
                if((valRed) < 3) valRed = 0;
                if((valGreen) < 3) valGreen = 0;
                if((valBlue) < 3) valBlue = 0;
                txRedView.setText(Integer.toString(valRed));
                txGreenView.setText(Integer.toString(valGreen));
                txBlueView.setText(Integer.toString(valBlue));

                return ;
            }
        });
        btn_diy2 = (Button) view.findViewById(R.id.btn_DIY2);
        if(mydb.getDYIColor("2") != "0")  {
            GradientDrawable shape =  new GradientDrawable();
            shape.setColor(Integer.valueOf(mydb.getDYIColor("2")));
            shape.setCornerRadius( 10f );
            shape.setStroke(2, Color.argb(0xFF,0xAA,0xAA,0xAA));
            btn_diy2.setText(mydb.getDYIBright("2") + "%");
            btn_diy2.setBackground(shape);
        }
        btn_diy2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                GradientDrawable shape =  new GradientDrawable();
                shape.setColor(colorPickerView.getColor());
                shape.setCornerRadius( 10f );
                shape.setStroke(2, Color.argb(0xFF,0xAA,0xAA,0xAA));
                btn_diy2.setText(ledbright + "%");
                //btn_diy1.setBackgroundResource(R.drawable.diy_border);
                btn_diy2.setBackground(shape);
                if(mydb.getDYIColor("2") != "0")
                    mydb.updateDIYInfo("2",String.valueOf(ledbright), String.valueOf(colorPickerView.getColor()), String.valueOf(colorPickerView.getSelectedPoint().x), String.valueOf(colorPickerView.getSelectedPoint().y));
                else
                    mydb.insertDIYInfo("2",String.valueOf(ledbright), String.valueOf(colorPickerView.getColor()), String.valueOf(colorPickerView.getSelectedPoint().x), String.valueOf(colorPickerView.getSelectedPoint().y));
                return true;
            }
        });
        btn_diy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strDYIColor1 = mydb.getDYIColor("2");
                String strDYIBright1 = mydb.getDYIBright("2");
                String strXpos = mydb.getDYIXPos("2");
                String strYpos = mydb.getDYIYPos("2");
                if(strDYIColor1 == "0")  return;

                discreteSeekBar.setProgress(Integer.valueOf(strDYIBright1));

                color = (int)Long.parseLong(String.format("%06X", (0xFFFFFF & Integer.valueOf(strDYIColor1))), 16);
                valRed = (byte)(Integer.valueOf(strDYIBright1) * ((color >> 16) & 0xFF) / 100) & 0xFF;
                valGreen= (byte)(Integer.valueOf(strDYIBright1) * ((color >> 8) & 0xFF) / 100) & 0xFF;
                valBlue = (byte)(Integer.valueOf(strDYIBright1) * ((color >> 0) & 0xFF) / 100)& 0xFF;

                Point clrPoint =  new Point();
                clrPoint.x = Integer.valueOf(strXpos);
                clrPoint.y = Integer.valueOf(strYpos);
                colorPickerView.setSelectedPoint(clrPoint);
                if((valRed) < 3) valRed = 0;
                if((valGreen) < 3) valGreen = 0;
                if((valBlue) < 3) valBlue = 0;
                txRedView.setText(Integer.toString(valRed));
                txGreenView.setText(Integer.toString(valGreen));
                txBlueView.setText(Integer.toString(valBlue));

                return ;
            }
        });
        btn_diy3 = (Button) view.findViewById(R.id.btn_DIY3);
        if(mydb.getDYIColor("3") != "0")  {
            GradientDrawable shape =  new GradientDrawable();
            shape.setColor(Integer.valueOf(mydb.getDYIColor("3")));
            shape.setCornerRadius( 10f );
            shape.setStroke(2, Color.argb(0xFF,0xAA,0xAA,0xAA));
            btn_diy3.setText(mydb.getDYIBright("3") + "%");
            btn_diy3.setBackground(shape);
        }
        btn_diy3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                GradientDrawable shape =  new GradientDrawable();
                shape.setColor(colorPickerView.getColor());
                shape.setCornerRadius( 10f );
                shape.setStroke(2, Color.argb(0xFF,0xAA,0xAA,0xAA));
                btn_diy3.setText(ledbright + "%");
                //btn_diy1.setBackgroundResource(R.drawable.diy_border);
                btn_diy3.setBackground(shape);
                if(mydb.getDYIColor("3") != "0")
                    mydb.updateDIYInfo("3",String.valueOf(ledbright), String.valueOf(colorPickerView.getColor()), String.valueOf(colorPickerView.getSelectedPoint().x), String.valueOf(colorPickerView.getSelectedPoint().y));
                else
                    mydb.insertDIYInfo("3",String.valueOf(ledbright), String.valueOf(colorPickerView.getColor()), String.valueOf(colorPickerView.getSelectedPoint().x), String.valueOf(colorPickerView.getSelectedPoint().y));
                return true;
            }
        });
        btn_diy3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strDYIColor1 = mydb.getDYIColor("3");
                String strDYIBright1 = mydb.getDYIBright("3");
                String strXpos = mydb.getDYIXPos("3");
                String strYpos = mydb.getDYIYPos("3");
                if(strDYIColor1 == "0")  return;

                discreteSeekBar.setProgress(Integer.valueOf(strDYIBright1));

                color = (int)Long.parseLong(String.format("%06X", (0xFFFFFF & Integer.valueOf(strDYIColor1))), 16);
                valRed = (byte)(Integer.valueOf(strDYIBright1) * ((color >> 16) & 0xFF) / 100) & 0xFF;
                valGreen= (byte)(Integer.valueOf(strDYIBright1) * ((color >> 8) & 0xFF) / 100) & 0xFF;
                valBlue = (byte)(Integer.valueOf(strDYIBright1) * ((color >> 0) & 0xFF) / 100)& 0xFF;

                Point clrPoint =  new Point();
                clrPoint.x = Integer.valueOf(strXpos);
                clrPoint.y = Integer.valueOf(strYpos);
                colorPickerView.setSelectedPoint(clrPoint);
                if((valRed) < 3) valRed = 0;
                if((valGreen) < 3) valGreen = 0;
                if((valBlue) < 3) valBlue = 0;
                txRedView.setText(Integer.toString(valRed));
                txGreenView.setText(Integer.toString(valGreen));
                txBlueView.setText(Integer.toString(valBlue));

                return ;
            }
        });
        btn_diy4 = (Button) view.findViewById(R.id.btn_DIY4);
        if(mydb.getDYIColor("4") != "0")  {
            GradientDrawable shape =  new GradientDrawable();
            shape.setColor(Integer.valueOf(mydb.getDYIColor("4")));
            shape.setCornerRadius( 10f );
            shape.setStroke(2, Color.argb(0xFF,0xAA,0xAA,0xAA));
            btn_diy4.setText(mydb.getDYIBright("4") + "%");
            btn_diy4.setBackground(shape);
        }
        btn_diy4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                GradientDrawable shape =  new GradientDrawable();
                shape.setColor(colorPickerView.getColor());
                shape.setCornerRadius( 10f );
                shape.setStroke(2, Color.argb(0xFF,0xAA,0xAA,0xAA));
                btn_diy4.setText(ledbright + "%");
                //btn_diy1.setBackgroundResource(R.drawable.diy_border);
                btn_diy4.setBackground(shape);
                if(mydb.getDYIColor("4") != "0")
                    mydb.updateDIYInfo("4",String.valueOf(ledbright), String.valueOf(colorPickerView.getColor()), String.valueOf(colorPickerView.getSelectedPoint().x), String.valueOf(colorPickerView.getSelectedPoint().y));
                else
                    mydb.insertDIYInfo("4",String.valueOf(ledbright), String.valueOf(colorPickerView.getColor()), String.valueOf(colorPickerView.getSelectedPoint().x), String.valueOf(colorPickerView.getSelectedPoint().y));
                return true;
            }
        });
        btn_diy4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strDYIColor1 = mydb.getDYIColor("4");
                String strDYIBright1 = mydb.getDYIBright("4");
                String strXpos = mydb.getDYIXPos("4");
                String strYpos = mydb.getDYIYPos("4");
                if(strDYIColor1 == "0")  return;

                discreteSeekBar.setProgress(Integer.valueOf(strDYIBright1));

                color = (int)Long.parseLong(String.format("%06X", (0xFFFFFF & Integer.valueOf(strDYIColor1))), 16);
                valRed = (byte)(Integer.valueOf(strDYIBright1) * ((color >> 16) & 0xFF) / 100) & 0xFF;
                valGreen= (byte)(Integer.valueOf(strDYIBright1) * ((color >> 8) & 0xFF) / 100) & 0xFF;
                valBlue = (byte)(Integer.valueOf(strDYIBright1) * ((color >> 0) & 0xFF) / 100)& 0xFF;


                Point clrPoint =  new Point();
                clrPoint.x = Integer.valueOf(strXpos);
                clrPoint.y = Integer.valueOf(strYpos);
                colorPickerView.setSelectedPoint(clrPoint);
                if((valRed) < 3) valRed = 0;
                if((valGreen) < 3) valGreen = 0;
                if((valBlue) < 3) valBlue = 0;
                txRedView.setText(Integer.toString(valRed));
                txGreenView.setText(Integer.toString(valGreen));
                txBlueView.setText(Integer.toString(valBlue));

                return ;
            }
        });
        btn_diy5 = (Button) view.findViewById(R.id.btn_DIY5);
        if(mydb.getDYIColor("5") != "0")  {
            GradientDrawable shape =  new GradientDrawable();
            shape.setColor(Integer.valueOf(mydb.getDYIColor("5")));
            shape.setCornerRadius( 10f );
            shape.setStroke(2, Color.argb(0xFF,0xAA,0xAA,0xAA));
            btn_diy5.setText(mydb.getDYIBright("5") + "%");
            btn_diy5.setBackground(shape);
        }
        btn_diy5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                GradientDrawable shape =  new GradientDrawable();
                shape.setColor(colorPickerView.getColor());
                shape.setCornerRadius( 10f );
                shape.setStroke(2, Color.argb(0xFF,0xAA,0xAA,0xAA));
                btn_diy5.setText(ledbright + "%");
                //btn_diy1.setBackgroundResource(R.drawable.diy_border);
                btn_diy5.setBackground(shape);
                if(mydb.getDYIColor("5") != "0")
                    mydb.updateDIYInfo("5",String.valueOf(ledbright), String.valueOf(colorPickerView.getColor()), String.valueOf(colorPickerView.getSelectedPoint().x), String.valueOf(colorPickerView.getSelectedPoint().y));
                else
                    mydb.insertDIYInfo("5",String.valueOf(ledbright), String.valueOf(colorPickerView.getColor()), String.valueOf(colorPickerView.getSelectedPoint().x), String.valueOf(colorPickerView.getSelectedPoint().y));
                return true;
            }
        });
        btn_diy5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strDYIColor1 = mydb.getDYIColor("5");
                String strDYIBright1 = mydb.getDYIBright("5");
                String strXpos = mydb.getDYIXPos("5");
                String strYpos = mydb.getDYIYPos("5");
                if(strDYIColor1 == "0")  return;

                discreteSeekBar.setProgress(Integer.valueOf(strDYIBright1));


                color = (int)Long.parseLong(String.format("%06X", (0xFFFFFF & Integer.valueOf(strDYIColor1))), 16);
                valRed = (byte)(Integer.valueOf(strDYIBright1) * ((color >> 16) & 0xFF) / 100) & 0xFF;
                valGreen= (byte)(Integer.valueOf(strDYIBright1) * ((color >> 8) & 0xFF) / 100) & 0xFF;
                valBlue = (byte)(Integer.valueOf(strDYIBright1) * ((color >> 0) & 0xFF) / 100)& 0xFF;

                Point clrPoint =  new Point();
                clrPoint.x = Integer.valueOf(strXpos);
                clrPoint.y = Integer.valueOf(strYpos);
                colorPickerView.setSelectedPoint(clrPoint);
                if((valRed) < 3) valRed = 0;
                if((valGreen) < 3) valGreen = 0;
                if((valBlue) < 3) valBlue = 0;
                txRedView.setText(Integer.toString(valRed));
                txGreenView.setText(Integer.toString(valGreen));
                txBlueView.setText(Integer.toString(valBlue));

                return ;
            }
        });
        return view;
    }


    private DiscreteSeekBar.OnProgressChangeListener progressChangeListener = new DiscreteSeekBar.OnProgressChangeListener() {
        @Override
        public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
            txtSpeed.setText("Brightness: " + Integer.toString(value));
            ledbright = value;
            if(((HomePageTabActivity)getActivity()).getConnectionState())
            {
                byte[] rgb ;
                if (color == 0)
                    rgb = getLedWhiteBytes( value);
                else
                    rgb = getLedBytes(color, value);

                txRedView.setText(Integer.toString(rgb[1] & 0xFF));
                txGreenView.setText(Integer.toString(rgb[2] & 0xFF));
                txBlueView.setText(Integer.toString(rgb[3] & 0xFF));

                ((HomePageTabActivity)getActivity()).controlLed(rgb);
            }
        }



        @Override
        public void onStartTrackingTouch(DiscreteSeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(DiscreteSeekBar seekBar) {
        }
    };

    private DiscreteSeekBar.OnProgressChangeListener redChangeListener = new DiscreteSeekBar.OnProgressChangeListener() {
        @Override
        public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
            tvRedView.setText(Integer.toString(value));
            valRed = value;
        }

        @Override
        public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

        }
    };

    private DiscreteSeekBar.OnProgressChangeListener greenChangeListener = new DiscreteSeekBar.OnProgressChangeListener() {
        @Override
        public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
           valGreen = value;
            tvGreenView.setText(Integer.toString(value));
        }

        @Override
        public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

        }
    };

    private DiscreteSeekBar.OnProgressChangeListener blueChangeListener = new DiscreteSeekBar.OnProgressChangeListener() {
        @Override
        public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
            tvBlueView.setText(Integer.toString(value));
            valBlue = value;
        }

        @Override
        public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

        }
    };

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden == false) {
            byte[] rgb ;
            if (color == 0)
                rgb = getLedWhiteBytes( ledbright);
            else
                rgb = getLedBytes(color, ledbright);

            if(((HomePageTabActivity)getActivity()).getConnectionState()) {
                ((HomePageTabActivity)getActivity()).controlLed(rgb);
            }
        }
        else  {

        }
    }

    private ColorPickerView.ColorListener colorListener = newColor -> {

        color = newColor;
        byte[] rgb;

        if(newColor == -1) newColor = 0;

        if (newColor == 0)  rgb = getLedWhiteBytes( ledbright);
        else rgb = getLedBytes(newColor, ledbright);

        txRedView.setText(Integer.toString(rgb[1] & 0xFF));
        txGreenView.setText(Integer.toString(rgb[2] & 0xFF));
        txBlueView.setText(Integer.toString(rgb[3] & 0xFF));

        if(((HomePageTabActivity)getActivity()).getConnectionState()) {
            ((HomePageTabActivity)getActivity()).controlLed(rgb);
        }
    };

    private byte[] getLedBytes(int newColor, int value) {
        byte[] rgb = new byte[7];
        color = (int)Long.parseLong(String.format("%06X", (0xFFFFFF & newColor)), 16);
        rgb[0] = (byte)0x56;
        rgb[1] = (byte)(value * ((color >> 16) & 0xFF) / 100);
        if((rgb[1]& 0xFF) < 4) rgb[1] = 0;
        rgb[2]= (byte)(value * ((color >> 8) & 0xFF) / 100);
        if((rgb[2]& 0xFF) < 4) rgb[2] = 0;
        rgb[3] = (byte)(value * ((color >> 0) & 0xFF) / 100);
        if((rgb[3]& 0xFF) < 4) rgb[3] = 0;
        rgb[4] = 0x00;
        rgb[5] = (byte)0xF0;
        rgb[6] = (byte)0xAA;
        return rgb;
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

    private byte[] getLedBrightBytes(int value) {
        byte[] rgb = new byte[7];
        byte byteValue = (byte)(value & 0xFF);
        rgb[0] = (byte)0x56;
        rgb[1] = 0x00;
        rgb[2]=  0x00;
        rgb[3] = 0x00;
        rgb[4] = byteValue;
        rgb[5] = (byte)0x0F;
        rgb[6] = (byte)0xAA;
        return rgb;
    }

}
