package com.tondz.flytojoy.Model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.tondz.flytojoy.R;

import java.util.ArrayList;
import java.util.List;

public class M_Bullet {
    private Bitmap[]bullets;
    public int heigh_bullet,width_bullet;
    public int x,y;
    public Bitmap bullet;
    public M_Bullet(Resources resources,int position){
        setBullet(resources);
        bullet = bullets[position];
    }
    public void setBullet(Resources res) {
        bullets = new Bitmap[3];
        bullets[0] = BitmapFactory.decodeResource(res,R.drawable.ton_bullet);
        bullets[1] = BitmapFactory.decodeResource(res,R.drawable.quang_bullet);
        bullets[2] = BitmapFactory.decodeResource(res,R.drawable.diep_bullet);
        heigh_bullet = bullets[0].getHeight();
        width_bullet = bullets[0].getWidth();
        width_bullet /= 3;
        heigh_bullet /= 3;
        for(int i = 0;i<bullets.length;i++){
            bullets[i] = Bitmap.createScaledBitmap(bullets[i],width_bullet,heigh_bullet,false);
        }

    }
    public Rect getCollisionShapeBullet() {
        return new Rect(x, y, x + width_bullet, y + heigh_bullet);
    }
}
