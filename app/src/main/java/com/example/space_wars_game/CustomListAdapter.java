package com.example.space_wars_game;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter {

    private ArrayList<ListItem> listData;
    private LayoutInflater layoutInflater;

    public CustomListAdapter(Context aContext, ArrayList<ListItem> listData){

        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);

    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.list_row, null);
            holder = new ViewHolder();
            holder.uName = (TextView) convertView.findViewById(R.id.name);
            holder.uScore = (TextView) convertView.findViewById(R.id.score);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder)convertView.getTag();
        }

        holder.uName.setText(listData.get(position).getName());
        holder.uScore.setText(listData.get(position).getScore());

        return convertView;
    }

    static  class ViewHolder{
        TextView uName;
        TextView uScore;
    }
}
