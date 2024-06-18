package com.tondz.flytojoy.Model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.tondz.flytojoy.Entities.E_Character;
import com.tondz.flytojoy.Controller.GameView;
import com.tondz.flytojoy.R;

import java.util.ArrayList;
import java.util.List;

public class M_Character {
    public List<E_Character> e_characterList = new ArrayList<>();
    public int toShoot = 0, wingCounter = 0;
    public boolean isGoingUp = false;
    public int x_character, y_character, width_character, height_character, shootCounter = 1;
    private GameView gameView;
    private List<Bitmap> listAnimationCharacter;
    private List<Bitmap> listAnimationShoot;
    private Bitmap char1, char2, shoot1, shoot2, shoot3, shoot4, dead;

    public M_Character(GameView gameView, int screenY, Resources res, int position) {
        this.gameView = gameView;
        e_characterList.add(new E_Character());
        e_characterList.get(0).setName("Tôn Sociu");
        e_characterList.add(new E_Character());
        e_characterList.get(0).setName("Quang Dz");
        e_characterList.add(new E_Character());
        e_characterList.get(0).setName("Điệp Đại Ca ");
        //set character
        setCharacter(res);
        //set shoot
        setShoot(res);
        //setDead
        setDead(res);
        //set position character
        char1 = e_characterList.get(position).getAnimation().get(0);
        char2 = e_characterList.get(position).getAnimation().get(1);
        shoot1 = e_characterList.get(position).getShoooting().get(0);
        shoot2 = e_characterList.get(position).getShoooting().get(1);
        shoot3 = e_characterList.get(position).getShoooting().get(2);
        shoot4 = e_characterList.get(position).getShoooting().get(3);
        dead = e_characterList.get(position).getDead();
        y_character = screenY / 2;
        x_character = 64;

    }

    public void setDead(Resources res) {
        e_characterList.get(0).setDead(BitmapFactory.decodeResource(res, R.drawable.ton_dead));
        e_characterList.get(0).setDead(Bitmap.createScaledBitmap(e_characterList.get(0).getDead(), width_character, height_character, false));
        e_characterList.get(1).setDead(BitmapFactory.decodeResource(res,R.drawable.quang_dead));
        e_characterList.get(1).setDead(Bitmap.createScaledBitmap(e_characterList.get(1).getDead(),width_character,height_character,false));
        e_characterList.get(2).setDead(BitmapFactory.decodeResource(res,R.drawable.diep_dead));
        e_characterList.get(2).setDead(Bitmap.createScaledBitmap(e_characterList.get(2).getDead(),width_character,height_character,false));
    }

    public void setShoot(Resources res) {
        listAnimationShoot = new ArrayList<>();
        listAnimationShoot.add(BitmapFactory.decodeResource(res, R.drawable.ton_shooting1));
        listAnimationShoot.add(BitmapFactory.decodeResource(res, R.drawable.ton_shooting2));
        listAnimationShoot.add(BitmapFactory.decodeResource(res, R.drawable.ton_shooting3));
        listAnimationShoot.add(BitmapFactory.decodeResource(res, R.drawable.ton_shooting4));
        e_characterList.get(0).setShoooting(listAnimationShoot);
        for (int i = 0; i < listAnimationShoot.size(); i++) {
            listAnimationShoot.set(i, Bitmap.createScaledBitmap(listAnimationShoot.get(i), width_character, height_character, false));
        }
        listAnimationShoot = new ArrayList<>();
        listAnimationShoot.add(BitmapFactory.decodeResource(res, R.drawable.quang_shooting1));
        listAnimationShoot.add(BitmapFactory.decodeResource(res, R.drawable.quang_shooting2));
        listAnimationShoot.add(BitmapFactory.decodeResource(res, R.drawable.quang_shooting3));
        listAnimationShoot.add(BitmapFactory.decodeResource(res, R.drawable.quang_shooting4));
        e_characterList.get(1).setShoooting(listAnimationShoot);
        for (int i = 0; i < listAnimationShoot.size(); i++) {
            listAnimationShoot.set(i, Bitmap.createScaledBitmap(listAnimationShoot.get(i), width_character, height_character, false));
        }
        listAnimationShoot = new ArrayList<>();
        listAnimationShoot.add(BitmapFactory.decodeResource(res, R.drawable.diep_shoot1));
        listAnimationShoot.add(BitmapFactory.decodeResource(res, R.drawable.diep_shoot2));
        listAnimationShoot.add(BitmapFactory.decodeResource(res, R.drawable.diep_shoot3));
        listAnimationShoot.add(BitmapFactory.decodeResource(res, R.drawable.diep_shoot4));
        e_characterList.get(2).setShoooting(listAnimationShoot);
        for (int i = 0; i < listAnimationShoot.size(); i++) {
            listAnimationShoot.set(i, Bitmap.createScaledBitmap(listAnimationShoot.get(i), width_character, height_character, false));
        }

    }


    public void setCharacter(Resources res) {
        listAnimationCharacter = new ArrayList<>();
        listAnimationCharacter.add(BitmapFactory.decodeResource(res, R.drawable.ton_fly1));
        listAnimationCharacter.add(BitmapFactory.decodeResource(res, R.drawable.ton_fly2));
        width_character = listAnimationCharacter.get(0).getWidth();
        height_character = listAnimationCharacter.get(0).getHeight();
        width_character /= 3;
        height_character /= 3;
        for (int i = 0; i < listAnimationCharacter.size(); i++) {
            listAnimationCharacter.set(i, Bitmap.createScaledBitmap(listAnimationCharacter.get(i), width_character, height_character, false));
        }
        e_characterList.get(0).setAnimation(listAnimationCharacter);
        listAnimationCharacter = new ArrayList<>();
        listAnimationCharacter.add(BitmapFactory.decodeResource(res, R.drawable.quang_fly1));
        listAnimationCharacter.add(BitmapFactory.decodeResource(res, R.drawable.quang_fly2));
        for (int i = 0; i < listAnimationCharacter.size(); i++) {
            listAnimationCharacter.set(i, Bitmap.createScaledBitmap(listAnimationCharacter.get(i), width_character, height_character, false));
        }
        e_characterList.get(1).setAnimation(listAnimationCharacter);
        listAnimationCharacter = new ArrayList<>();
        listAnimationCharacter.add(BitmapFactory.decodeResource(res, R.drawable.diep_fly1));
        listAnimationCharacter.add(BitmapFactory.decodeResource(res, R.drawable.diep_fly2));
        for (int i = 0; i < listAnimationCharacter.size(); i++) {
            listAnimationCharacter.set(i, Bitmap.createScaledBitmap(listAnimationCharacter.get(i), width_character, height_character, false));
        }
        e_characterList.get(2).setAnimation(listAnimationCharacter);
    }

    public Bitmap getFlight() {
        if (toShoot != 0) {
            if (shootCounter == 1) {
                shootCounter++;
                return shoot1;
            }
            if (shootCounter == 2) {
                shootCounter++;
                return shoot2;
            }
            if (shootCounter == 3) {
                shootCounter++;
                return shoot3;
            }
            shootCounter = 1;
            toShoot--;
            gameView.newBullet();
            return shoot4;
        }
        if (wingCounter == 0) {
            wingCounter = 1;
            return char1;
        } else {
            wingCounter = 0;
            return char2;
        }
    }


    public Rect getCollisionShapeCharacter() {
        return new Rect(x_character, y_character, x_character + width_character, y_character + height_character);
    }

    public Bitmap getDead(int position) {
        return e_characterList.get(position).getDead();
    }
}

