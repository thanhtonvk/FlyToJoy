package com.tondz.flytojoy.Controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;

import com.tondz.flytojoy.Model.M_Background;
import com.tondz.flytojoy.Model.M_Bullet;
import com.tondz.flytojoy.Model.M_Character;
import com.tondz.flytojoy.Model.M_Column;
import com.tondz.flytojoy.Model.M_Enemy;
import com.tondz.flytojoy.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameView extends SurfaceView implements Runnable {
    private Thread thread;
    private boolean isPlaying, isGameOver = false;
    private int screenX, screenY, score = 0;
    private Paint paint;
    private List<M_Enemy> enemies;
    private SharedPreferences prefs;
    private Random random = new Random();
    private SoundPool soundPool1, soundPool2, soundPoolBoom, soundPoolDead;
    private List<M_Bullet> bullets;
    private int sound1, sound2, boom, dead;
    public static M_Character m_character;
    private GameActivity activity;
    private M_Background background1, background2;
    public static int position = 0;
    public static int map = 1;
    List<M_Column> m_columns;

    public GameView(GameActivity activity, int screenX, int screenY) {
        super(activity);
        this.activity = activity;

        prefs = activity.getSharedPreferences("game", Context.MODE_PRIVATE);


        //set sound game
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .build();

            soundPool1 = new SoundPool.Builder()
                    .setAudioAttributes(audioAttributes)
                    .build();
            soundPool2 = new SoundPool.Builder().setAudioAttributes(audioAttributes).build();
            soundPoolBoom = new SoundPool.Builder().setAudioAttributes(audioAttributes).build();
            soundPoolDead = new SoundPool.Builder().setAudioAttributes(audioAttributes).build();

        } else {
            soundPool1 = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
            soundPool2 = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
            soundPoolBoom = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
            soundPoolDead = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        }
        sound1 = soundPool1.load(activity, R.raw.shoot, 1);
        sound2 = soundPool2.load(activity, R.raw.theme_playgame, 1);
        boom = soundPoolBoom.load(activity, R.raw.boom, 1);
        dead = soundPoolDead.load(activity, R.raw.deadgame, 0);
        this.screenX = screenX;
        this.screenY = screenY;


        //create background
        background1 = new M_Background(screenX, screenY, getResources(), map);
        background2 = new M_Background(screenX, screenY, getResources(), map);
        background2.x = screenX;
        //create character
        m_character = new M_Character(this, screenY, getResources(), position);

        //create bullets
        bullets = new ArrayList<>();
        //create draw score
        paint = new Paint();
        paint.setTextSize(128);
        paint.setColor(Color.WHITE);

        //create enemy
        enemies = new ArrayList<>();

        for (int i = 0; i < ChooseMap.amountEnemy; i++) {
            M_Enemy enemy = new M_Enemy(getResources(), random.nextInt(2));
            enemies.add(enemy);
        }
        m_columns = new ArrayList<>();

        m_columns.add(new M_Column(getResources()));


    }

    @Override
    public void run() {
        if (!prefs.getBoolean("isMute", false)) {
            soundPool2.play(sound2, 1, 1, 0, -1, 1);
        }
        while (isPlaying) {

            update();
            draw();
            sleep();

        }

    }

    int tmp = 100;

    //run game
    private void update() {
        //move background
        background1.x -= 10;
        background2.x -= 10;

        if (background1.x + background1.background.getWidth() < 0) {
            background1.x = screenX;
        }

        if (background2.x + background2.background.getWidth() < 0) {
            background2.x = screenX;
        }

//        when character going up
        if (m_character.isGoingUp)
            m_character.y_character -= 30;
            //when character going down
        else
            m_character.y_character += 30;

        if (m_character.y_character < -100)
            m_character.y_character = -100;

        if (m_character.y_character >= screenY - m_character.height_character - 150)
            m_character.y_character = screenY - m_character.height_character - 150;

        List<M_Bullet> trash = new ArrayList<>();


        //bullet
        for (M_Bullet bullet : bullets) {

            if (bullet.x > screenX)
                trash.add(bullet);

            bullet.x += 50;

            for (M_Enemy m_enemy : enemies) {
                if (Rect.intersects(m_enemy.getCollisionShapeEnemy(),
                        bullet.getCollisionShapeBullet())) {
                    if (!prefs.getBoolean("isMute", false)) {
                        soundPoolBoom.play(boom, 1, 1, 1, 0, 1);
                    }
                    score += m_enemy.point;
                    m_enemy.x = -500;
                    bullet.x = screenX + 500;
                    m_enemy.wasShot = true;
                }
            }
            //score up
        }
        for (M_Column column : m_columns) {
            column.x -= 10;
            if (column.x + column.width < 0) {
                column.x = screenX;
                column.y = 500;

            }
            if (Rect.intersects(column.getCollisionShapeCol(), m_character.getCollisionShapeCharacter())) {
                if (!prefs.getBoolean("isMute", false)) {
                    soundPoolDead.play(dead, 1, 1, 1, 0, 1);
                    soundPool2.pause(sound2);
                }
                gameOver();
                isGameOver = true;
                return;
            }
            if (column.x < 0) {
                column.x -= 500;
            }

        }
        if (score == tmp) {
            enemies.add(new M_Enemy(getResources(), random.nextInt(2)));
            tmp += 200;
        }

        for (M_Bullet bullet : trash)
            bullets.remove(bullet);

        for (M_Enemy m_enemy : enemies) {

            m_enemy.x -= m_enemy.speed;

            if (m_enemy.x + m_enemy.width < 0) {

                if (!m_enemy.wasShot) {
                    if (!prefs.getBoolean("isMute", false)) {
                        soundPoolDead.play(dead, 1, 1, 1, 0, 1);
                        soundPool2.pause(sound2);
                    }
                    gameOver();
                    isGameOver = true;
                    return;
                }

                int bound = (int) (30);
                m_enemy.speed = random.nextInt(bound);

                if (m_enemy.speed < 20)
                    m_enemy.speed = 20;

                m_enemy.x = screenX;
                m_enemy.y = random.nextInt(screenY - m_enemy.height - 150);

                m_enemy.wasShot = false;
            }

            if (Rect.intersects(m_enemy.getCollisionShapeEnemy(), m_character.getCollisionShapeCharacter())) {
                if (!prefs.getBoolean("isMute", false)) {
                    soundPoolDead.play(dead, 1, 1, 2, 0, 1);
                }
                isGameOver = true;
                soundPool2.pause(sound2);
                gameOver();
                return;
            }

        }

    }

    private void draw() {

        if (getHolder().getSurface().isValid()) {

            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);
            canvas.drawBitmap(background2.background, background2.x, background2.y, paint);

            for (M_Enemy m_enemy : enemies)
                canvas.drawBitmap(m_enemy.getEnemy(), m_enemy.x+random.nextInt(50), m_enemy.y+random.nextInt(50), paint);

            for (M_Column column : m_columns) {
                canvas.drawBitmap(column.getCol(), column.x+0, column.y+random.nextInt(400), paint);
                canvas.drawBitmap(column.getCol(), column.x+500, column.y+random.nextInt(400), paint);
                canvas.drawBitmap(column.getCol(), column.x+1000, column.y+random.nextInt(400), paint);
                canvas.drawBitmap(column.getCol(), column.x+1500, column.y+random.nextInt(400), paint);

            }
            canvas.drawText(score + "", screenX / 2f, 164, paint);

            if (isGameOver) {
                isPlaying = false;
                canvas.drawBitmap(m_character.getDead(position), m_character.x_character, m_character.y_character, paint);
                getHolder().unlockCanvasAndPost(canvas);
                return;
            }

            canvas.drawBitmap(m_character.getFlight(), m_character.x_character, m_character.y_character, paint);

            for (M_Bullet bullet : bullets)
                canvas.drawBitmap(bullet.bullet, bullet.x, bullet.y, paint);

            getHolder().unlockCanvasAndPost(canvas);

        }

    }


    private void gameOver() {
        try {
            Thread.sleep(2000);
            Intent intent = new Intent(activity, SavePlayer.class);
            intent.putExtra("character", position);
            intent.putExtra("map", map);
            intent.putExtra("point", score);
            activity.startActivity(intent);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //preventing default implementation previous to android.os.Build.VERSION_CODES.ECLAIR
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void sleep() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume() {

        isPlaying = true;
        thread = new Thread(this);
        thread.start();

    }

    public void pause() {

        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if (event.getX() < screenX / 2 && event.getY() < screenY / 2) {
                    m_character.isGoingUp = true;
                } else {
                    m_character.isGoingUp = false;
                }
                break;
        }

        return true;
    }


    public void newBullet() {

        if (!prefs.getBoolean("isMute", false))
            soundPool1.play(sound1, 1, 1, 0, 0, 1);

        M_Bullet m_bullet = new M_Bullet(getResources(), position);
        m_bullet.x = m_character.x_character + m_character.width_character;
        m_bullet.y = m_character.y_character + (m_character.height_character / 2);
        bullets.add(m_bullet);

    }
}
