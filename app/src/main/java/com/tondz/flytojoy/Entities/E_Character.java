package com.tondz.flytojoy.Entities;

import android.graphics.Bitmap;

import java.util.List;

public class E_Character {


    public E_Character() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private List<Bitmap> animation;
    private List<Bitmap> shoooting;
    private Bitmap dead;

    public Bitmap getDead() {
        return dead;
    }

    public void setDead(Bitmap dead) {
        this.dead = dead;
    }

    public E_Character(String name, List<Bitmap> animation, List<Bitmap> shoooting, Bitmap dead) {
        this.name = name;
        this.animation = animation;
        this.shoooting = shoooting;

        this.dead = dead;
    }

    public List<Bitmap> getAnimation() {
        return animation;
    }

    public void setAnimation(List<Bitmap> animation) {
        this.animation = animation;
    }

    public List<Bitmap> getShoooting() {
        return shoooting;
    }

    public void setShoooting(List<Bitmap> shoooting) {
        this.shoooting = shoooting;
    }

}
