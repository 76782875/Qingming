package com.example.administrator.qingming.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.model.ModelChange;

import java.util.List;

/**
 * Created by Administrator on 2017/6/5 0005.
 */

public class ChangeAdapter extends BaseAdapter {
    Context context;
    List<ModelChange.ResultBean> list;
    public ChangeAdapter(Context context,List<ModelChange.ResultBean> list){
        this.context = context;
        this.list = list;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_change,null);
            viewHolder.reference_numbe = (TextView) convertView.findViewById(R.id.numb);
            viewHolder.wtr = (TextView) convertView.findViewById(R.id.wtr);
            viewHolder.dfdsr = (TextView) convertView.findViewById(R.id.dfdsr);
            viewHolder.change_attorney = (TextView) convertView.findViewById(R.id.change_attorney);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.reference_numbe.setText(list.get(position).getAh_number());
        viewHolder.wtr.setText(list.get(position).getWtr());
        viewHolder.dfdsr.setText(list.get(position).getDfdsr());
        viewHolder.change_attorney.setText(list.get(position).getName());
        return convertView;
    }

    final static class ViewHolder{
        TextView reference_numbe;
        TextView wtr;
        TextView dfdsr;
        TextView change_attorney;
    }
}
