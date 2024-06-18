package com.tondz.flytojoy.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.tondz.flytojoy.Entities.E_Player;
import com.tondz.flytojoy.R;

public class SavePlayer extends AppCompatActivity {

    EditText editText;
    Button btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_player);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initView();
        Intent intent = getIntent();
        int character = intent.getIntExtra("character",0);
        int map = intent.getIntExtra("map",0);
        int score = intent.getIntExtra("point",0);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameStartActivity.playerDatabase.addPlayer(new E_Player(editText.getText().toString(),score,getCharacter(character),getMap(map)));
                startActivity(new Intent(getApplicationContext(),GameStartActivity.class));
            }
        });
    }
    private void initView(){
        editText = findViewById(R.id.edt_name);
        btn_save= findViewById(R.id.btn_save);
    }
    public int getCharacter(int position) {
        if (position == 0) {
            return R.drawable.ton_fly1;
        } else {
            return R.drawable.quang_fly1;
        }
    }

    public static int getMap(int map) {
        if (map == 0) {
            return R.drawable.background;
        }
        else return R.drawable.background2;
    }
}