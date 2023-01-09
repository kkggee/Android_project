package com.example.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapterReply extends BaseAdapter {
    private Context mContext;
    private ArrayList<Reply> listViewAdapterReply = new ArrayList<>();

    public ListViewAdapterReply(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return listViewAdapterReply.size();
    }

    @Override
    public Object getItem(int i) {
        return listViewAdapterReply.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.reply_item, parent, false);
        }

        TextView userID_text = (TextView) convertView.findViewById(R.id.cmt_userid_tv);
        TextView date_text = (TextView) convertView.findViewById(R.id.cmt_date_tv);
        TextView script_text = (TextView) convertView.findViewById(R.id.cmt_content_tv);

        Reply item = listViewAdapterReply.get(position);
        userID_text.setText(item.userID);
        date_text.setText(item.timestamp);
        script_text.setText(item.script);

        return convertView;

    }
    public void setItems(ArrayList<Reply> items){
        this.listViewAdapterReply = items;
    }
}
