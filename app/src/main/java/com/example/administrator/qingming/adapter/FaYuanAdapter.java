package com.example.administrator.qingming.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.model.ModelFaYuan;

import java.util.List;

/**
 * Created by Administrator on 2017/7/6 0006.
 */

public class FaYuanAdapter extends BaseAdapter {
    Context context;
    List<ModelFaYuan.ResultBean> list;

    public FaYuanAdapter(Context context,List<ModelFaYuan.ResultBean> list){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_fayuan,null);
            viewHolder.number = (TextView) convertView.findViewById(R.id.case_item_name);
            viewHolder.spcx = (TextView) convertView.findViewById(R.id.case_item_banan);
            viewHolder.wtr = (TextView) convertView.findViewById(R.id.case_item_numb);
            viewHolder.slfy = (TextView) convertView.findViewById(R.id.case_item_bumeng);
            viewHolder.lasj = (TextView) convertView.findViewById(R.id.case_item_chengbanren);
            viewHolder.ktsj = (TextView) convertView.findViewById(R.id.case_item_xingjuren);
            viewHolder.shangsu = (TextView) convertView.findViewById(R.id.shansu_time);
            viewHolder.xuanpan = (TextView) convertView.findViewById(R.id.case_item_daibu);
            viewHolder.bz = (TextView) convertView.findViewById(R.id.case_beizhu);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ModelFaYuan.ResultBean resultBeen = list.get(position);
        viewHolder.number.setText(resultBeen.getAh_number());
        viewHolder.spcx.setText(resultBeen.getSpcx());
        viewHolder.wtr.setText(resultBeen.getWtr());
        viewHolder.slfy.setText(resultBeen.getFt());
        viewHolder.lasj.setText(resultBeen.getLadate());
        viewHolder.ktsj.setText(resultBeen.getKtdate());
        viewHolder.shangsu.setText(resultBeen.getSsdate());
        viewHolder.xuanpan.setText(resultBeen.getSpdate());
        viewHolder.bz.setText(resultBeen.getBzsm2());
        return convertView;
    }

    final static class ViewHolder{
        TextView number;
        TextView spcx;
        TextView wtr;
        TextView slfy;
        TextView lasj;
        TextView ktsj;
        TextView shangsu;
        TextView xuanpan;
        TextView bz;
    }
}
