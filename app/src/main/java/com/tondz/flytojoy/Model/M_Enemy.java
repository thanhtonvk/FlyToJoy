package com.tondz.flytojoy.Model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;

import com.tondz.flytojoy.Entities.E_Enemy;
import com.tondz.flytojoy.R;

import java.util.ArrayList;
import java.util.List;

public class M_Enemy {
    public boolean wasShot = true;
    public int x = 0, y, width, height, enemyCounter = 1;
    private List<E_Enemy> listEnemies = new ArrayList<>();
    private List<Bitmap> animationEnemy;
    private Bitmap emeny1, enemy2, enemy3, enemy4;
    public int point;
    public int speed;

    public M_Enemy(Resources res,int positionE) {
        listEnemies.add(new E_Enemy());
        listEnemies.add(new E_Enemy());
        //setEnemies
        setEnemy(res);
        listEnemies.get(0).setSpeed(20);
        listEnemies.get(0).setPoint(5);
        listEnemies.get(1).setSpeed(30);
        listEnemies.get(1).setPoint(10);

        point = listEnemies.get(positionE).getPoint();
        speed = listEnemies.get(positionE).getSpeed();

        emeny1 = listEnemies.get(positionE).getAnimation().get(0);
        enemy2 = listEnemies.get(positionE).getAnimation().get(1);
        enemy3 = listEnemies.get(positionE).getAnimation().get(2);
        enemy4 = listEnemies.get(positionE).getAnimation().get(3);
        y -= height;
    }

    public void setEnemy(Resources res) {
        animationEnemy = new ArrayList<>();
        animationEnemy.add(BitmapFactory.decodeResource(res, R.drawable.bird1));
        animationEnemy.add(BitmapFactory.decodeResource(res, R.drawable.bird2));
        animationEnemy.add(BitmapFactory.decodeResource(res, R.drawable.bird3));
        animationEnemy.add(BitmapFactory.decodeResource(res, R.drawable.bird4));

        width = animationEnemy.get(0).getWidth();
        height = animationEnemy.get(0).getHeight();
        width = width / 1;
        height = height / 1;

        for (int i = 0; i < animationEnemy.size(); i++) {
            animationEnemy.set(i, Bitmap.createScaledBitmap(animationEnemy.get(i), width, height, false));
        }

        listEnemies.get(0).setAnimation(animationEnemy);


        animationEnemy = new ArrayList<>();
        animationEnemy.add(BitmapFactory.decodeResource(res, R.drawable.zombie1));
        animationEnemy.add(BitmapFactory.decodeResource(res, R.drawable.zombie2));
        animationEnemy.add(BitmapFactory.decodeResource(res, R.drawable.zombie3));
        animationEnemy.add(BitmapFactory.decodeResource(res, R.drawable.zombie4));

        width = animationEnemy.get(1).getWidth();
        height = animationEnemy.get(1).getHeight();
        width = width / 2;
        height = height / 2;
        for (int i = 0; i < animationEnemy.size(); i++) {
            animationEnemy.set(i, Bitmap.createScaledBitmap(animationEnemy.get(i), width, height, false));
        }

        listEnemies.get(1).setAnimation(animationEnemy);
    }

    public Bitmap getEnemy() {
        if (enemyCounter == 1) {
            enemyCounter++;
            return emeny1;
        }
        if (enemyCounter == 2) {
            enemyCounter++;
            return enemy2;
        }
        if ((enemyCounter == 3)) {
            enemyCounter++;
            return enemy3;
        }
        enemyCounter = 1;
        return enemy4;
    }

    public Rect getCollisionShapeEnemy() {
        return new Rect(x, y, x + width, y + height);
    }

}
