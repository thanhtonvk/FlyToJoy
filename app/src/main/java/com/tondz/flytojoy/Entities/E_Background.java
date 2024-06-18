package com.tondz.flytojoy.Entities;

import android.graphics.Bitmap;

public class E_Background {
    private int level;
    private Bitmap background;
    public E_Background() {

    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Bitmap getBackground() {
        return background;
    }

    public void setBackground(Bitmap background) {
        this.background = background;
    }

    public E_Background(int level, Bitmap background) {
        this.level = level;
        this.background = background;
    }
}
