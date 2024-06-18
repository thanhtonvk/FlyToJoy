package com.tondz.flytojoy.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import com.tondz.flytojoy.R;

public class ChooseMap extends AppCompatActivity {
    ImageButton btn_back, btn_map0, btn_map1;
    Button btn_startgame;
    public static int amountEnemy;
    public static int speedUpAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_map);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initView();
        eventClick();

    }

    private void eventClick() {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_startgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameStartActivity.mediaPlayer.pause();
                startActivity(new Intent(getApplicationContext(), GameActivity.class));
            }
        });
        btn_map0.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                GameView.map = 0;
                speedUpAmount = 1;
                amountEnemy = 4;
            }
        });
        btn_map1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                GameView.map = 1;
                speedUpAmount = 2;
                amountEnemy = 5;

            }
        });
    }

    private void initView() {
        btn_back = findViewById(R.id.btn_back_choosemap);
        btn_startgame = findViewById(R.id.btn_startgame_choosemap);
        btn_map0 = findViewById(R.id.img_btn_map0);
        btn_map1 = findViewById(R.id.img_btn_map1);

    }
}