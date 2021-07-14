package com.skydoves.magiclight_ble_control.views.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.skydoves.magiclight_ble_control.R;

public class HelpActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_help);
        getSupportActionBar().hide(); //<< this

        TextView tvhelp = findViewById(R.id.tv_help);
        tvhelp.setMovementMethod(new ScrollingMovementMethod());

        ImageView ivBack = findViewById(R.id.app_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HelpActivity.super.onBackPressed();
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}
