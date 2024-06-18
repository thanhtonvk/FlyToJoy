package com.tondz.flytojoy.Entities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class E_Player {
    private String name;
    private int point;
    private int character;
    private int map;

    public int getCharacter() {
        return character;
    }

    public void setCharacter(int character) {
        this.character = character;
    }

    public int getMap() {
        return map;
    }

    public void setMap(int map) {
        this.map = map;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public E_Player(String name, int point, int character, int map) {
        this.name = name;
        this.point = point;
        this.character = character;
        this.map = map;
    }

}

