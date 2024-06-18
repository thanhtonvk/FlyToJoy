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

public class ChooseCharacter extends AppCompatActivity {

    ImageButton btn_back,btn_ton,btn_quang,btn_diep;
    Button btn_startgame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_character);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initView();
        eventClick();

    }
    private void eventClick(){
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_startgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),ChooseMap.class));
            }
        });
        btn_ton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                GameView.position = 0;

            }
        });
        btn_quang.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                GameView.position = 1;
           
            }
        });
        btn_diep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameView.position = 2;
            }
        });
    }
    private void initView(){
        btn_back = findViewById(R.id.btn_back_choosecharacter);
        btn_startgame = findViewById(R.id.btn_startgame_character);
        btn_quang = findViewById(R.id.img_btn_quang);
        btn_ton = findViewById(R.id.img_btn_ton);
        btn_diep = findViewById(R.id.img_btn_diep);
    }
}