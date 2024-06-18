package com.tondz.flytojoy.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.tondz.flytojoy.Entities.E_Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerDatabase extends SQLiteOpenHelper {

    public PlayerDatabase(@Nullable Context context) {
        super(context, "database", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE PLAYER(" +
                "NAME TEXT NOT NULL," +
                "POINT INT," +
                "CHARACTER INT," +
                "MAP INT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS PLAYER");
        onCreate(db);
    }

    public void addPlayer(E_Player e_player) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NAME", e_player.getName());
        values.put("POINT", e_player.getPoint());
        values.put("CHARACTER", e_player.getCharacter());
        values.put("MAP", e_player.getMap());
        database.insert("PLAYER", null, values);
        database.close();
    }

    public E_Player getPlayer(String name) {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query("PLAYER", null, "NAME = ?", new String[]{name}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        E_Player  e_player= new E_Player(cursor.getString(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3));
        database.close();
        return e_player;
    }

    public List<E_Player> getAllPlayer() {
        List<E_Player> e_players = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM PLAYER", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            E_Player e_player = new E_Player(cursor.getString(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3));
            e_players.add(e_player);
            cursor.moveToNext();
        }
        database.close();
        return e_players;
    }

    public void deletePlayer(String name) {
        SQLiteDatabase database = getWritableDatabase();
        database.delete("PLAYER","NAME = ?",new String[]{name});
        database.close();
    }
}
