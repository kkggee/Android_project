package com.example.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapterSche extends BaseAdapter {
    private Context mContext;
    private ArrayList<Schedule> listViewSchedList = new ArrayList<>();

    public ListViewAdapterSche(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return listViewSchedList.size();
    }

    @Override
    public Object getItem(int i) {
        return listViewSchedList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.schedule_item, parent, false);
        }

        TextView time_text = (TextView) convertView.findViewById(R.id.startTime);
        TextView descript_text = (TextView) convertView.findViewById(R.id.descript);

        Schedule item = listViewSchedList.get(position);
        time_text.setText(item.startTime);
        descript_text.setText(item.descript);

        return convertView;

    }
    public void setItems(ArrayList<Schedule> items){
        this.listViewSchedList = items;
    }
}
