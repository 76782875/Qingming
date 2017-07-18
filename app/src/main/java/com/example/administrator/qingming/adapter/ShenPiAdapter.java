package com.example.administrator.qingming.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.model.MyCaseModel;

import java.util.List;

/**
 * Created by Administrator on 2017/6/16 0016.
 */

public class ShenPiAdapter extends BaseAdapter {
    Context context;
    List<MyCaseModel.ResultBean> list;
    public ShenPiAdapter(Context context, List<MyCaseModel.ResultBean> list){
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
            viewHolder =  new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_case,null);
            viewHolder.name = (TextView) convertView.findViewById(R.id.case_item_name);
            viewHolder.numb = (TextView) convertView.findViewById(R.id.case_item_numb);
            viewHolder.consignor = (TextView) convertView.findViewById(R.id.case_item_consignor);
            viewHolder.adversary = (TextView) convertView.findViewById(R.id.case_item_adversary);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MyCaseModel.ResultBean bean = list.get(position);


        viewHolder.name.setText(bean.getAy());
        viewHolder.numb.setText(bean.getAh_number());
        viewHolder.consignor.setText(bean.getWtr());
        viewHolder.adversary.setText(bean.getDfdsr());


        return convertView;
    }

    final static class ViewHolder{
        TextView name;
        TextView numb;
        TextView consignor;
        TextView adversary;
    }
}
