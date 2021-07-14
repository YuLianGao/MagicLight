package com.skydoves.magiclight_ble_control.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import com.skydoves.magiclight_ble_control.R;
import com.skydoves.magiclight_ble_control.main.HomePageTabActivity;
import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;
import java.nio.ByteBuffer;
import java.util.Random;
import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.io.android.AudioDispatcherFactory;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;

public class MusicFragment extends Fragment {

    private Thread listeningThread;
    private Handler uiThread;

    private AudioDispatcher dispatcher;
    private AudioProcessor processor;

    DiscreteSeekBar discreteSeekBar;

    ImageView ivCircle;

    PitchDetectionHandler pdh;
    Random random;

    double amplitudeSync = 0;

    int randColor = 0; double dSensitivity = 1;

    private  boolean bHidden = true;

    public MusicFragment() {

    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        bHidden = hidden;
        if (hidden == false) {
            startDispatch();
        }
        else if (listeningThread != null && dispatcher != null) {

        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_sync, container, false);
        discreteSeekBar= (DiscreteSeekBar) view.findViewById(R.id.seekBar);
        discreteSeekBar.setOnProgressChangeListener(progressChangeListener);
        discreteSeekBar.setMax(100);
        discreteSeekBar.setProgress(100);
        ivCircle = view.findViewById(R.id.ivCircle);
       startTimer(300000);
        return  view;
    }

    private void startTimer(long time){
        CountDownTimer counter = new CountDownTimer(time, 3000){
            public void onTick(long millisUntilDone){
                random = new Random();
                randColor = ByteBuffer.wrap(getColorwithPosition(random.nextInt(6) )).getInt();
            }

            public void onFinish() {
                startTimer(300000);
            }
        }.start();
    }
    private DiscreteSeekBar.OnProgressChangeListener progressChangeListener = new DiscreteSeekBar.OnProgressChangeListener() {
        @Override
        public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
            dSensitivity = (float) value * 0.01 ;
            discreteSeekBar.setScrubberColor(R.color.btnRed);
            discreteSeekBar.setThumbColor(R.color.btnRed, R.color.btnRed);

        }

        @Override
        public void onStartTrackingTouch(DiscreteSeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(DiscreteSeekBar seekBar) {
        }
    };



    private void startDispatch() {

           uiThread = new Handler();
           pdh = (PitchDetectionResult result, AudioEvent audioEven) -> uiThread.post(() -> {
            final float pitchInHz = result.getPitch();
            int pitch =  pitchInHz > 0 ? (int) pitchInHz : 1;

            double amps = 0;
            if(((HomePageTabActivity)getActivity()).getConnectionState() && !bHidden) {

                amps = audioEven.getRMS() * 300 * dSensitivity;
                double sen = dSensitivity;
                amplitudeSync = amps ;

                if((amplitudeSync ) >=  1) {

                    byte[] rgb = getLedBright(randColor, (int)amplitudeSync);

                    switch ((int)amplitudeSync)
                    {
                        case 1:
                            ivCircle.setImageResource (R.drawable.circle_1);
                            break;
                        case 2:
                        case 3:
                            ivCircle.setImageResource (R.drawable.circle_2);
                            break;
                        case 4:
                        case 5:
                        case 6:
                            ivCircle.setImageResource (R.drawable.circle_3);
                            break;
                        case 7:
                        case 8:
                        case 9:
                            ivCircle.setImageResource (R.drawable.circle_4);
                            break;
                    }

                    ((HomePageTabActivity)getActivity()).controlLed(rgb);

                }
                else
                {
                    byte[] rgb = {0x56, 0x00, 0x00, 0x00, 0x00, 0x0F, (byte)0xAA};
                    ((HomePageTabActivity)getActivity()).controlLed(rgb);

                    ivCircle.setImageResource (R.drawable.circle_0);

                }
            }
        });


        dispatcher = AudioDispatcherFactory.fromDefaultMicrophone(22050, 1024, 0);
        processor = new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.FFT_YIN, 22050, 1024, pdh);
        dispatcher.addAudioProcessor(processor);

        listeningThread = new Thread(dispatcher);
        listeningThread.start();

    }

    private byte[] getLedBytes(int newColor) {
        byte[] rgb = new byte[7];
        int color = (int)Long.parseLong(String.format("%06X", (0xFFFFFF & newColor)), 16);
        rgb[0] = (byte)0x56;
        rgb[1] = (byte)((color >> 16) & 0xFF);
        rgb[2]= (byte)((color >> 8) & 0xFF);
        rgb[3] = (byte)((color >> 0) & 0xFF);
        rgb[4] = 0;
        rgb[5] = (byte)0xF0;
        rgb[6] = (byte)0xAA;
        return rgb;
    }

    private byte[] getLedBright(int newColor, int value) {
        byte[] rgb = new byte[7];
        int color = (int)Long.parseLong(String.format("%06X", (0xFFFFFF & newColor)), 16);
        rgb[0] = (byte)0x56;
        rgb[1] = (byte)(value * ((color >> 16) & 0xFF) / 100);
        rgb[2]= (byte)(value * ((color >> 8) & 0xFF) / 100);
        rgb[3] = (byte)(value * ((color >> 0) & 0xFF) / 100);
        rgb[4] = 0x00;
        rgb[5] = (byte)0xF0;
        rgb[6] = (byte)0xAA;
        return rgb;
    }
    private byte[] getColorwithPosition(int position)  {
        byte[] rgb = new byte[7];
        rgb[1] = 0;        rgb[2]= 0;        rgb[3] = 0;
        switch (position){
            case 0: {
                rgb[1] = (byte) 0xFF;
                break;
            }
            case 1: {
                rgb[2] = (byte) 0xFF;
                break;
            }
            case 2: {
                rgb[3] = (byte) 0xFF;
                break;
            }
            case 3: {
                rgb[1] = (byte) 0xFF;rgb[2] = (byte) 0xFF;
                break;
            }
            case 4: {
                rgb[3] = (byte) 0xFF;rgb[2] = (byte) 0xFF;
                break;
            }
            case 5: {
                rgb[1] = (byte) 0x80;rgb[3] = (byte) 0x80;
                break;
            }
            case 6: {
                rgb[4] = (byte) 0xFF;rgb[5] = (byte) 0x0F;
                break;
            }
        }
        return  rgb;
    }


}
