package com.skydoves.magiclight_ble_control.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.skydoves.magiclight_ble_control.ColorModeActivity;
import com.skydoves.magiclight_ble_control.model.ModeClass;
import com.skydoves.magiclight_ble_control.R;
import com.skydoves.magiclight_ble_control.model.DBHelper;


public class CustomFragment extends Fragment implements View.OnClickListener{

    ModeClass modeClass;

    public static Button btnAddMode;
    public static RelativeLayout btn_group1, btn_group2, btn_group3, btn_group4, btn_group5, btn_group6, btn_group7, btn_group8, btn_group9, btn_group0;
    public static Button btnDelete1, btnEdit1, btnSend1;
    public static Button btnDelete2, btnEdit2, btnSend2;
    public static Button btnDelete3, btnEdit3, btnSend3;
    public static Button btnDelete4, btnEdit4, btnSend4;
    public static Button btnDelete5, btnEdit5, btnSend5;
    public static Button btnDelete6, btnEdit6, btnSend6;
    public static Button btnDelete7, btnEdit7, btnSend7;
    public static Button btnDelete8, btnEdit8, btnSend8;
    public static Button btnDelete9, btnEdit9, btnSend9;
    public static Button btnDelete0, btnEdit0, btnSend0;
    public static LinearLayout mode_pattern1,mode_pattern2,mode_pattern3,mode_pattern4,mode_pattern5,mode_pattern6,mode_pattern7,mode_pattern8,mode_pattern9,mode_pattern0;
    public static TextView mode1_1, mode1_2, mode1_3, mode1_4, mode1_5, mode1_6, mode1_7, mode1_8, mode1_9, mode1_10, mode1_11, mode1_12, mode1_13, mode1_14, mode1_15, mode1_16;
    public static TextView mode2_1, mode2_2, mode2_3, mode2_4, mode2_5, mode2_6, mode2_7, mode2_8, mode2_9, mode2_10, mode2_11, mode2_12, mode2_13, mode2_14, mode2_15, mode2_16;
    public static TextView mode3_1, mode3_2, mode3_3, mode3_4, mode3_5, mode3_6, mode3_7, mode3_8, mode3_9, mode3_10, mode3_11, mode3_12, mode3_13, mode3_14, mode3_15, mode3_16;
    public static TextView mode4_1, mode4_2, mode4_3, mode4_4, mode4_5, mode4_6, mode4_7, mode4_8, mode4_9, mode4_10, mode4_11, mode4_12, mode4_13, mode4_14, mode4_15, mode4_16;
    public static TextView mode5_1, mode5_2, mode5_3, mode5_4, mode5_5, mode5_6, mode5_7, mode5_8, mode5_9, mode5_10, mode5_11, mode5_12, mode5_13, mode5_14, mode5_15, mode5_16;
    public static TextView mode6_1, mode6_2, mode6_3, mode6_4, mode6_5, mode6_6, mode6_7, mode6_8, mode6_9, mode6_10, mode6_11, mode6_12, mode6_13, mode6_14, mode6_15, mode6_16;
    public static TextView mode7_1, mode7_2, mode7_3, mode7_4, mode7_5, mode7_6, mode7_7, mode7_8, mode7_9, mode7_10, mode7_11, mode7_12, mode7_13, mode7_14, mode7_15, mode7_16;
    public static TextView mode8_1, mode8_2, mode8_3, mode8_4, mode8_5, mode8_6, mode8_7, mode8_8, mode8_9, mode8_10, mode8_11, mode8_12, mode8_13, mode8_14, mode8_15, mode8_16;
    public static TextView mode9_1, mode9_2, mode9_3, mode9_4, mode9_5, mode9_6, mode9_7, mode9_8, mode9_9, mode9_10, mode9_11, mode9_12, mode9_13, mode9_14, mode9_15, mode9_16;
    public static TextView mode0_1, mode0_2, mode0_3, mode0_4, mode0_5, mode0_6, mode0_7, mode0_8, mode0_9, mode0_10, mode0_11, mode0_12, mode0_13, mode0_14, mode0_15, mode0_16;
    public static TextView txDescription;
    public static TextView template_title1, template_method1, template_speed1;
    public static TextView template_title2, template_method2, template_speed2;
    public static TextView template_title3, template_method3, template_speed3;
    public static TextView template_title4, template_method4, template_speed4;
    public static TextView template_title5, template_method5, template_speed5;
    public static TextView template_title6, template_method6, template_speed6;
    public static TextView template_title7, template_method7, template_speed7;
    public static TextView template_title8, template_method8, template_speed8;
    public static TextView template_title9, template_method9, template_speed9;
    public static TextView template_title0, template_method0, template_speed0;
    public static FrameLayout flDescription;
    public static DBHelper mydb ;


    public CustomFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view1 =  inflater.inflate(R.layout.fragment_custom, container, false);
        mydb = new DBHelper(getActivity());
        modeClass = new ModeClass();
        initUI(view1);
        return view1;
    }

    @Override
    public void onClick(View v) {
        int a = mydb.getCount();
        Intent intent = new Intent(getActivity(), ColorModeActivity.class);
        intent.putExtra("editable","edit");
        modeClass.addClickForRemoveBtn(v);
        onResume();
        if(v==btnAddMode){
            if(a>9){
                Toast.makeText(getActivity(),"Custom mode count can't be over 10", Toast.LENGTH_LONG).show();
            } else {
                intent.putExtra("editable", "create");
                intent.putExtra("edit_mode_number", 0);
                startActivity(intent);
            }
        } else if(v == mode_pattern1 || v == btnEdit1) {
            intent.putExtra("edit_mode_number",1);
            startActivity(intent);
        } else if(v == mode_pattern2 || v == btnEdit2) {
            intent.putExtra("edit_mode_number",2);
            startActivity(intent);
        } else if(v == mode_pattern3 || v == btnEdit3) {
            intent.putExtra("edit_mode_number",3);
            startActivity(intent);
        } else if(v == mode_pattern4 || v == btnEdit4) {
            intent.putExtra("edit_mode_number",4);
            startActivity(intent);
        } else if(v == mode_pattern5 || v == btnEdit5) {
            intent.putExtra("edit_mode_number",5);
            startActivity(intent);
        } else if(v == mode_pattern6 || v == btnEdit6) {
            intent.putExtra("edit_mode_number",6);
            startActivity(intent);
        } else if(v == mode_pattern7 || v == btnEdit7) {
            intent.putExtra("edit_mode_number",7);
            startActivity(intent);
        } else if(v == mode_pattern8 || v == btnEdit8) {
            intent.putExtra("edit_mode_number",8);
            startActivity(intent);
        } else if(v == mode_pattern9 || v == btnEdit9) {
            intent.putExtra("edit_mode_number",9);
            startActivity(intent);
        } else if(v == mode_pattern0 || v == btnEdit0) {
            intent.putExtra("edit_mode_number",10);
            startActivity(intent);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        int mode_count = mydb.getCount();
        SetUnvisibleModes(mode_count);
        modeClass = new ModeClass();
        modeClass.init_CustomOnResume(getActivity());
    }

    private void initUI(View view) {
        modeClass.initUI_customFragment(view, this);
    }

    private void SetUnvisibleModes(int count_mode) {
        modeClass.setUnvisible(count_mode);
    }

}
