package com.skydoves.magiclight_ble_control.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;

import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v4.app.Fragment;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.skydoves.magiclight_ble_control.R;
import com.skydoves.magiclight_ble_control.main.HomePageTabActivity;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import cn.carbswang.android.numberpickerview.library.NumberPickerView;

public class PatternFragment extends Fragment implements NumberPickerView.OnScrollListener, NumberPickerView.OnValueChangeListener,
        NumberPickerView.OnValueChangeListenerInScrolling{

    DiscreteSeekBar discreteSeekBar;
    int color = 0, mLastFirstVisibleItem = 0;
    ImageView   ivPatternNext, ivPatternPrev;
    static ListView listview;
    TextView txtPattern, txtSpeed;
    String[] values;
    private  boolean bHidden = true, bCrossFade = false, bInitial = true;
    PatternThread patternThread;
    byte[] rgb = {(byte)0x56, (byte)0x00, (byte)0x00,((byte)0x00),((byte)0x00),((byte)0xF0),((byte)0xAA), };
    byte[] black = {(byte)0x56, (byte)0x00, (byte)0x00,((byte)0x00),((byte)0x00),((byte)0xF0),((byte)0xAA), };
    int fadeSpeed = 100, mSpeed = 1, nSpeed = 100;
    NumberPicker patternPicker;
    private NumberPickerView mNumberPickerView;

    public PatternFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_pattern, container, false);
        txtSpeed= (TextView)view.findViewById(R.id.speed_txt);
        txtSpeed.setText("Speed: " + Integer.toString(nSpeed));
        discreteSeekBar= (DiscreteSeekBar) view.findViewById(R.id.seekBar_pattern);
        discreteSeekBar.setOnProgressChangeListener(progressChangeListener);
        discreteSeekBar.setMax(100);
        discreteSeekBar.setProgress(100);
        txtPattern = (TextView)view.findViewById(R.id.pattern_txt);
        ivPatternNext = view.findViewById(R.id.pattern_next);
        ivPatternNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listview.clearFocus();
                listview.post(new Runnable() {
                    @Override
                    public void run() {
                        bCrossFade = false;
                        listview.requestFocusFromTouch();
                        fadeSpeed = 100;
                        if(mLastFirstVisibleItem < values.length -1) {
                            mLastFirstVisibleItem++;
                        } else
                            mLastFirstVisibleItem = 0;

                       listview.setSelection(mLastFirstVisibleItem);

                       // listview.setSelection(mLastFirstVisibleItem);
                       // int index = (int)listview.getCheckedItemPosition();
                       // listview.smoothScrollToPosition(index+1);
                        listview.requestFocus();
                    }
                });

                    int value = mNumberPickerView.getValue();
                    mNumberPickerView.smoothScrollToValue(value, value + 1);
            }
        });

        ivPatternPrev = view.findViewById(R.id.pattern_prev);
        ivPatternPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listview.clearFocus();
                listview.post(new Runnable() {
                    @Override
                    public void run() {
                        bCrossFade = false;
                        listview.requestFocusFromTouch();
                        fadeSpeed = 100;
                        if(mLastFirstVisibleItem >  0) {
                            mLastFirstVisibleItem--;
                        } else mLastFirstVisibleItem = values.length - 1;

                        listview.setSelection(mLastFirstVisibleItem);

                       listview.requestFocus();
                    }
                });

                int current_item = patternPicker.getValue();
                if( current_item < values.length) {
                    int value = mNumberPickerView.getValue();
                    mNumberPickerView.smoothScrollToValue(value, value - 1);
                }
            }
        });

        listview = (ListView) view.findViewById(R.id.list_pattern);

        values = new String[] { "Red Fade",  "Green Fade", "Blue Fade","Yellow Fade", "Cyan Fade", "Purple Fade", "White Fade",
             "Red-Green Crossfade", "Red-Blue Crossfade", "Green-Blue Crossfade",
             "Seven-color Strobe",  "Red Strobe", "Green Strobe", "Blue Strobe",
            "Yellow Strobe", "Cyan Strobe", "Purple Strobe", "White Strobe" , "Seven-color Jump"};

        mNumberPickerView = view.findViewById(R.id.picker);
        mNumberPickerView.refreshByNewDisplayedValues(values);
        mNumberPickerView.setWrapSelectorWheel(false);
        mNumberPickerView.setOnScrollListener(this);
        mNumberPickerView.setOnValueChangedListener(this);
        mNumberPickerView.setOnValueChangeListenerInScrolling(this);


        patternPicker= view.findViewById(R.id.patternPicker);
        patternPicker.setMinValue(0);
        patternPicker.setMaxValue(values.length - 1);
        patternPicker.setDisplayedValues(values);
        patternPicker.setWrapSelectorWheel(false);

        NumberPicker.OnValueChangeListener myValChangedListener = new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                txtPattern.setText(values[newVal]);
            }
        };

        patternPicker.setOnValueChangedListener(myValChangedListener);
        txtPattern.setText(values[mLastFirstVisibleItem]);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,values);
        listview.setAdapter(adapter);

        // ListView on item selected listener.
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                txtPattern.setText(values[position]);
                mLastFirstVisibleItem = position;
                if(((HomePageTabActivity)getActivity()).getConnectionState()) {
                    controlLedPosition(position);
                }
            }
        });

        listview.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long i)
            {
                txtPattern.setText(values[position]);
                smoothScrollToPositionFromTop(listview, mLastFirstVisibleItem);
                if(((HomePageTabActivity)getActivity()).getConnectionState()) {
                     controlLedPosition(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        patternThread = new PatternThread();

        //patternThread.start();

        return view;
    }

    public static View getChildAtPosition(final AdapterView view, final int position) {
        final int index = position - view.getFirstVisiblePosition();
        if ((index >= 0) && (index < view.getChildCount())) {
            return view.getChildAt(index);
        } else {
            return null;
        }
    }

    public static void smoothScrollToPositionFromTop(final AbsListView view, final int position) {
        View child = getChildAtPosition(view, position);
        // There's no need to scroll if child is already at top or view is already scrolled to its end
        if ((child != null) && ((child.getTop() == 0) || ((child.getTop() > 0) && !view.canScrollVertically(1)))) {
         //   return;
        }

        // Perform scrolling to position
        new Handler().post(new Runnable() {
            @Override
            public void run() {
              //  view.smoothScrollToPositionFromTop(position, listview.getHeight()/2, 0);

                view.setSelection(position);
            }
        });
    }
    private void controlLedPosition(int position)  {
        rgb[1] = 0;        rgb[2]= 0;        rgb[3] = 0;
        switch (position){
            case 0:
                rgb[1] = (byte) 0xFF;  break;
            case 1:
                rgb[2] = (byte) 0xFF;  break;
            case 2:
                rgb[3] = (byte) 0xFF;   break;
            case 3:
                rgb[1] = (byte) 0xFF;rgb[2] = (byte) 0xFF;  break;
            case 4:
                rgb[3] = (byte) 0xFF;rgb[2] = (byte) 0xFF;  break;
            case 5:
                rgb[1] = (byte) 0x80;rgb[3] = (byte) 0x80;  break;
            case 6:
               rgb[4] = (byte) 0xFF;rgb[5] = (byte) 0x0F;   break;
        }
    }

    int fromByteArray(byte[] bytes) {
        return ((bytes[0] & 0xFF) << 24) |
                ((bytes[1] & 0xFF) << 16) |
                ((bytes[2] & 0xFF) << 8 ) |
                ((bytes[3] & 0xFF) << 0 );
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        bHidden = hidden;
        super.onHiddenChanged(hidden);

        if (hidden) {
           // if(!bInitial) patternThread.interrupt();
        } else {

            if(bInitial) {
                patternThread.start();
                bInitial = false;
            }
            else {
                Thread.State state = patternThread.getState();
                if(state == Thread.State.TERMINATED)
                {
                    patternThread = new PatternThread();
                    patternThread.start();
                }
            }
        }

    }

    private DiscreteSeekBar.OnProgressChangeListener progressChangeListener = new DiscreteSeekBar.OnProgressChangeListener() {
        @Override
        public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
            nSpeed = value;
            mSpeed = value / 10  + 1;
            txtSpeed.setText("Speed: " + Integer.toString(nSpeed));
//            discreteSeekBar.setScrubberColor(R.color.btnRed);
//            discreteSeekBar.setThumbColor(R.color.btnRed, R.color.btnRed);

        }

        @Override
        public void onStartTrackingTouch(DiscreteSeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(DiscreteSeekBar seekBar) {
        }
    };

    private byte[] getLedBytes(int newColor, int value) {
        if(mLastFirstVisibleItem > 6 && mLastFirstVisibleItem <10)
            value = 100 - value;
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

    private byte[] getLedWhiteBytes(int value) {
        rgb[0] = (byte)0x56;
        rgb[1] = 0;
        rgb[2]= 0;
        rgb[3] = 0;
        rgb[4] = (byte)((value * 0xFF) / 100 );
        rgb[5] = (byte)0x0F;
        rgb[6] = (byte)0xAA;
        return rgb;
    }

    @Override
    public void onValueChange(NumberPickerView picker, int oldVal, int newVal) {
        String[] content = picker.getDisplayedValues();
        txtPattern.setText(content[newVal - picker.getMinValue()]);
        mLastFirstVisibleItem = newVal -  - picker.getMinValue();
    }

    @Override
    public void onValueChangeInScrolling(NumberPickerView picker, int oldVal, int newVal) {

    }

    @Override
    public void onScrollStateChange(NumberPickerView view, int scrollState) {

    }

    private  class PatternThread extends Thread {
        boolean bMovePositive = false;
        int nCount = 0;
        @Override
        public void run() {
            while (bHidden == false )  {
                nCount++;
                if(mLastFirstVisibleItem < 10) {
                    if (bMovePositive == false) {
                        if (fadeSpeed > 0) fadeSpeed = fadeSpeed - 1;
                        else bMovePositive = true;
                    } else {
                        if (fadeSpeed < 100) fadeSpeed = fadeSpeed + 1;
                        else {
                            bMovePositive = false;
                            if (bCrossFade == false) bCrossFade = true;
                            else bCrossFade = false;
                        }
                    }
                    if (mLastFirstVisibleItem < 6)
                        controlLedPosition(mLastFirstVisibleItem);

                    else if (mLastFirstVisibleItem == 7) {

                        if (bCrossFade) controlLedPosition(0);
                        else controlLedPosition(1);

                    }
                    if (mLastFirstVisibleItem == 8) {

                        if (bCrossFade) controlLedPosition(0);
                        else controlLedPosition(2);

                    }
                    if (mLastFirstVisibleItem == 9) {

                        if (bCrossFade) controlLedPosition(1);
                        else controlLedPosition(2);
                    }

                    if(mLastFirstVisibleItem != 6)
                        rgb = getLedBytes(fromByteArray(rgb), fadeSpeed);
                    else
                        rgb = getLedWhiteBytes(fadeSpeed);

                    if (((HomePageTabActivity)getActivity()).getConnectionState()) {
                        ((HomePageTabActivity) getActivity()).controlLed(rgb);
                    }

                    try {
                        Thread.sleep(30000 / (nSpeed *  nSpeed +1));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }

                if(mLastFirstVisibleItem == 10)   {
                    controlLedPosition(nCount%6);
                }
                if(mLastFirstVisibleItem == 11)   {
                      controlLedPosition(0);
                }
                if(mLastFirstVisibleItem == 12)   {
                    controlLedPosition(1);
                }
                if(mLastFirstVisibleItem == 13)   {
                    controlLedPosition(2);
                }
                if(mLastFirstVisibleItem == 14)   {
                    controlLedPosition(3);
                }
                if(mLastFirstVisibleItem == 15)   {
                    controlLedPosition(4);
                }
                if(mLastFirstVisibleItem == 16)   {
                    controlLedPosition(5);
                }
                if(mLastFirstVisibleItem == 17)   {
                    controlLedPosition(6);
                }
                if(mLastFirstVisibleItem == 18)   {
                    controlLedPosition(nCount%6);
                }

                if(mLastFirstVisibleItem != 17)
                    rgb = getLedBytes(fromByteArray(rgb), 100);
                else
                    rgb = getLedWhiteBytes(100);

                if (((HomePageTabActivity)getActivity()).getConnectionState()) {
                    ((HomePageTabActivity) getActivity()).controlLed(rgb);
                }

                try {
                    //Thread.sleep(2000 / mSpeed);
                    Thread.sleep(1200000 / (nSpeed *  nSpeed +1));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(mLastFirstVisibleItem == 18) continue;

                rgb[1] = 0;  rgb[2]= 0;  rgb[3] = 0;

                rgb = getLedBytes(fromByteArray(rgb), 100);

                if (((HomePageTabActivity)getActivity()).getConnectionState()) {
                    ((HomePageTabActivity) getActivity()).controlLed(rgb);
                }

                try {
                    //Thread.sleep(2000 / mSpeed);
                    Thread.sleep(1200000 / (nSpeed *  nSpeed +1));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }
    }
}
