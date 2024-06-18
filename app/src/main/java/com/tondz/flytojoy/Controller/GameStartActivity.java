package com.tondz.flytojoy.Controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.tondz.flytojoy.Entities.E_Player;
import com.tondz.flytojoy.Model.PlayerDatabase;
import com.tondz.flytojoy.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GameStartActivity extends Activity {
    ImageButton imgbtn_exit, imgbtn_menu, imgbtn_start;
    Button btn_highscore, btn_setting, btn_back;
    private boolean isMute;
    SharedPreferences sharedPreferences;
    public static MediaPlayer mediaPlayer;
    public static PlayerDatabase playerDatabase;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamestart);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        playerDatabase = new PlayerDatabase(this);
        dialogMenu();
        dialogHighScore();
        initView();
        eventClick();
        music();
        setSound();
    }

    private void eventClick() {
        imgbtn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialogMenu.show();
            }
        });
        imgbtn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                finishAffinity();
            }
        });
        imgbtn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChooseCharacter.class);
                startActivity(intent);
            }
        });

    }

    private void setSound() {
        sharedPreferences = getSharedPreferences("game", MODE_PRIVATE);
        isMute = sharedPreferences.getBoolean("isMute", false);
        if (isMute) {
            btn_setting.setText("Sound: off");
            mediaPlayer.pause();
        } else {
            btn_setting.setText("Sound: on");
            mediaPlayer.start();
        }
    }

    private void initView() {
        imgbtn_exit = findViewById(R.id.img_btnexit);
        imgbtn_menu = findViewById(R.id.imgbtn_menu);
        imgbtn_start = findViewById(R.id.imgbtn_playgame);


    }

    Dialog customDialogMenu;

    private void dialogMenu() {
        customDialogMenu = new Dialog(this);
        customDialogMenu.setContentView(R.layout.dialog_menu);
        customDialogMenu.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        btn_highscore = customDialogMenu.findViewById(R.id.btn_highscore);
        btn_setting = customDialogMenu.findViewById(R.id.btn_settings);
        btn_back = customDialogMenu.findViewById(R.id.btn_back);
        btn_highscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogScore.show();
            }
        });
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isMute = !isMute;
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isMute", isMute);
                editor.apply();
                setSound();
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialogMenu.dismiss();
            }
        });


    }

    public void music() {
        mediaPlayer = MediaPlayer.create(this, R.raw.themegame);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    Dialog dialogScore;
    EditText edt_inputname;
    Button btn_search,btn_sort;
    ListView listView;
    CustomListView customListView;

    private void dialogHighScore() {
        dialogScore = new Dialog(this);
        dialogScore.setContentView(R.layout.dialog_highscore);
        dialogScore.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        edt_inputname = dialogScore.findViewById(R.id.edt_textsearch);
        btn_search = dialogScore.findViewById(R.id.btn_search);
        listView = dialogScore.findViewById(R.id.listView);
        btn_sort= dialogScore.findViewById(R.id.btn_sort);
        customListView = new CustomListView(getApplicationContext(), playerDatabase.getAllPlayer(), R.layout.list_item);
        listView.setAdapter(customListView);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Do you want to delete");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        playerDatabase.deletePlayer(playerDatabase.getAllPlayer().get(position).getName());
                        customListView = new CustomListView(getApplicationContext(), playerDatabase.getAllPlayer(), R.layout.list_item);
                        listView.setAdapter(customListView);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customListView = new CustomListView(getApplicationContext(), findName(edt_inputname.getText().toString()), R.layout.list_item);
                listView.setAdapter(customListView);
            }
        });

        btn_sort.setOnClickListener(new View.OnClickListener() {
            int step = 0;
            @Override
            public void onClick(View v) {
                if (step ==0){
                    customListView = new CustomListView(getApplicationContext(), sortHighToLow(), R.layout.list_item);
                    listView.setAdapter(customListView);
                    step = 1;
                }
                else {
                    customListView = new CustomListView(getApplicationContext(), sortLowToHigh(), R.layout.list_item);
                    listView.setAdapter(customListView);
                    step = 0;
                }
            }
        });
    }
    private List<E_Player> findName(String keyword){
        List<E_Player>e_players = new ArrayList<>();
        for (E_Player e_player : playerDatabase.getAllPlayer()){
            if(e_player.getName().equalsIgnoreCase(keyword)){
                e_players.add(e_player);
            }
        }
        return e_players;
    }
    private List<E_Player>sortLowToHigh(){
        List<E_Player>e_players = playerDatabase.getAllPlayer();
        Collections.sort(e_players, new Comparator<E_Player>() {
            @Override
            public int compare(E_Player o1, E_Player o2) {
                return o1.getPoint()-o2.getPoint();
            }
        });
        return e_players;
    }
    private List<E_Player>sortHighToLow(){
        List<E_Player>e_players = playerDatabase.getAllPlayer();
        Collections.sort(e_players, new Comparator<E_Player>() {
            @Override
            public int compare(E_Player o1, E_Player o2) {
                return o2.getPoint()-o1.getPoint();
            }
        });
        return e_players;
    }
}
