package com.tondz.flytojoy.Model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.tondz.flytojoy.Entities.E_Background;
import com.tondz.flytojoy.R;

import java.util.ArrayList;
import java.util.List;

public class M_Background {
    public int x=0,y =0;
    private List<E_Background>listBackground;
    public Bitmap background;
    public M_Background(int screenX, int screenY, Resources res,int index){
       setListBackground(screenX,screenY,res);
       background = listBackground.get(index).getBackground();
    }
    private void setListBackground(int screenX, int screenY, Resources res){
        listBackground = new ArrayList<>();
        listBackground.add(new E_Background());
        listBackground.get(0).setLevel(1);
        listBackground.get(0).setBackground(BitmapFactory.decodeResource(res,R.drawable.background));
        listBackground.add(new E_Background());
        listBackground.get(1).setLevel(2);
        listBackground.get(1).setBackground(BitmapFactory.decodeResource(res,R.drawable.background2));
        for(int i =0;i<listBackground.size();i++){
            listBackground.get(i).setBackground(Bitmap.createScaledBitmap(listBackground.get(i).getBackground(),screenX,screenY,false));
        }
    }
}
