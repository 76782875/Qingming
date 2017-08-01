package com.example.administrator.qingming.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.model.ModelPress;

import java.util.List;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class PressHomeAdapter extends BaseAdapter {
    List<ModelPress.ResultBean> list;
    Context context;
    public PressHomeAdapter(List<ModelPress.ResultBean> list, Context context){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_press_home,null);
            viewHolder.title1 = (TextView) convertView.findViewById(R.id.title1);
            viewHolder.title2 = (TextView) convertView.findViewById(R.id.title2);
            viewHolder.type = (TextView) convertView.findViewById(R.id.type);
            viewHolder.people = (TextView) convertView.findViewById(R.id.people);
            viewHolder.days = (TextView) convertView.findViewById(R.id.days);
            viewHolder.years = (TextView) convertView.findViewById(R.id.years);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
//        viewHolder.title1.setText(list.get(position).getProduct_name());
        viewHolder.title2.setText(list.get(position).getProduct_name());
//        viewHolder.type.setText(list.get(position).getInsert_time());
        viewHolder.people.setText(list.get(position).getInsert_name());

        String time = list.get(position).getInsert_time();
        //截取 月 和 日 字符串
        String year = time.substring(0, 4);
        String month = time.substring(5, 7);
        String day = time.substring(8, 10);
        viewHolder.days.setText(day);
        viewHolder.years.setText(year+"-"+month);
        return convertView;
    }

    final static class ViewHolder{
        TextView title1,title2,days,years;
        TextView type;
        TextView people;
    }
}
