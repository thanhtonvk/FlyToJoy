package com.tondz.flytojoy.Entities;

import android.graphics.Bitmap;

import java.util.List;

public class E_Enemy {
    private List<Bitmap> animation;
    private int point;
    private int speed;

    public E_Enemy() {

    }

    public List<Bitmap> getAnimation() {
        return animation;
    }

    public void setAnimation(List<Bitmap> animation) {
        this.animation = animation;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public E_Enemy(List<Bitmap> animation, int point, int speed) {
        this.animation = animation;
        this.point = point;
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
