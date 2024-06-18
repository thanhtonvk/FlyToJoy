package com.tondz.flytojoy.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tondz.flytojoy.Entities.E_Player;
import com.tondz.flytojoy.R;

import java.util.List;

public class CustomListView extends BaseAdapter {

    private Context context;
    private List<E_Player> e_playerList;
    LayoutInflater inflater;
    private int layout;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<E_Player> getE_playerList() {
        return e_playerList;
    }

    public void setE_playerList(List<E_Player> e_playerList) {
        this.e_playerList = e_playerList;
    }

    public LayoutInflater getInflater() {
        return inflater;
    }

    public void setInflater(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public CustomListView(Context context, List<E_Player> e_playerList, int layout) {
        this.context = context;
        this.e_playerList = e_playerList;
        this.layout = layout;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return e_playerList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(layout,null);
        TextView name = convertView.findViewById(R.id.tv_name);
        TextView point = convertView.findViewById(R.id.tv_score);
        ImageView background = convertView.findViewById(R.id.img_map);
        ImageView character = convertView.findViewById(R.id.img_character);
        E_Player e_player = e_playerList.get(position);
        name.setText(e_player.getName());
        point.setText(e_player.getPoint()+"");
        background.setImageResource(e_player.getMap());
        character.setImageResource(e_player.getCharacter());
        return convertView;
    }
}
