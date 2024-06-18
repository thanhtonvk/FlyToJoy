package com.tondz.flytojoy.Model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.tondz.flytojoy.R;

public class M_Column {
    public Bitmap col;
   public int x=0, y, width, height;

    public M_Column(Resources resources) {
        col = BitmapFactory.decodeResource(resources, R.drawable.col);
        width = col.getWidth();
        height = col.getHeight();
        col = Bitmap.createScaledBitmap(col,width,height,false);
        y = -height;
    }

    public Rect getCollisionShapeCol() {
        return new Rect(x, y,x+width,y+height );
    }
    public Bitmap getCol(){
        return col;
    }
}
