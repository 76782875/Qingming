package com.example.administrator.qingming.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.administrator.qingming.R;
import com.example.administrator.qingming.model.ModelPress;

import java.util.List;

/**
 * Created by Administrator on 2017/6/3 0003.
 */

public class PressAdater extends BaseAdapter {
    List<ModelPress.ResultBean> list;
    Context context;
    public PressAdater(List<ModelPress.ResultBean> list, Context context){
        this.list = list;
        this.context =context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_press,null);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.work_item_people = (TextView) convertView.findViewById(R.id.work_item_people);
            viewHolder.item_time = (TextView) convertView.findViewById(R.id.item_time);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.title.setText(list.get(position).getProduct_name());
        viewHolder.work_item_people.setText(list.get(position).getInsert_name());
        viewHolder.item_time.setText(list.get(position).getInsert_time());
        return convertView;
    }

    final static class ViewHolder{
        TextView title;
        TextView work_item_people;
        TextView item_time;
    }
}
