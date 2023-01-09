package com.example.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.test.MainActivity;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Post> listViewPortList = new ArrayList<>();

    public ListViewAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return listViewPortList.size();
    }

    @Override
    public Object getItem(int i) {
        return listViewPortList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.post_item, parent, false);
        }

        TextView title_text = (TextView) convertView.findViewById(R.id.post_title);
        TextView date_text = (TextView) convertView.findViewById(R.id.post_day);
        TextView post_text = (TextView) convertView.findViewById(R.id.post);

        Post item = listViewPortList.get(position);
        title_text.setText(item.title);
        date_text.setText(item.date);
        post_text.setText(item.post);

        return convertView;

    }
    public void setItems(ArrayList<Post> items){
        this.listViewPortList = items;
    }
}
