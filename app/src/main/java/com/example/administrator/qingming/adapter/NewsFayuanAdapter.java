package com.example.administrator.qingming.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.model.ModelFaYuan;
import com.example.administrator.qingming.model.ModelFalvXx;

import java.util.List;

/**
 * Created by Administrator on 2017/7/25 0025.
 */

public class NewsFayuanAdapter extends BaseAdapter {

    private Context context;
    private List<ModelFalvXx.ResultBean> list;

    public NewsFayuanAdapter(Context context,List<ModelFalvXx.ResultBean> list){
        this.context=context;
        this.list= list ;
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
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_news_fayuan,null);
            viewHolder = new ViewHolder();
            viewHolder.case_no = (TextView) convertView.findViewById(R.id.case_no);
            viewHolder.case_time = (TextView) convertView.findViewById(R.id.case_time);
            viewHolder.spxs = (TextView) convertView.findViewById(R.id.spxs);
            viewHolder.spjg = (TextView) convertView.findViewById(R.id.spjg);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.case_no.setText(list.get(position).getAh_number());
        viewHolder.spxs.setText(list.get(position).getSpcx());
        viewHolder.spjg.setText(list.get(position).getSpjg());
        viewHolder.case_time.setText(list.get(position).getKtdate());
        return convertView;
    }

    final static class ViewHolder{
        TextView case_time;
        TextView spxs;
        TextView spjg;
        TextView case_no;
    }
}
