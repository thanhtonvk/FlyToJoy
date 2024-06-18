package com.tondz.flytojoy.Controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tondz.flytojoy.R;

public class GameActivity extends Activity {

    private GameView gameView;
    FrameLayout game;// Sort of "holder" for everything we are placing
    RelativeLayout GameButtons;//Holder for the buttons
    ImageButton butOne;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        gameView = new GameView(this, point.x, point.y);
        game = new FrameLayout(this);
        GameButtons = new RelativeLayout(this);
        butOne = new ImageButton(this);
        butOne.setImageResource(R.drawable.hunt_80px);
        butOne.setId(R.id.id_button);
        butOne.setBackgroundResource(R.drawable.border_button);
        RelativeLayout.LayoutParams b1 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.FILL_PARENT,
                RelativeLayout.LayoutParams.FILL_PARENT);
        GameButtons.setLayoutParams(params);
        GameButtons.addView(butOne);
        b1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        b1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        butOne.setLayoutParams(b1);
        game.addView(gameView);
        game.addView(GameButtons);
        butOne.setX(-300);
        butOne.setY(-150);
        setContentView(game);
        butOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               GameView.m_character.toShoot++;
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //preventing default implementation previous to android.os.Build.VERSION_CODES.ECLAIR
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }
}
