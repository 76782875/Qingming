package com.example.administrator.qingming.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.model.ModelJiancha;
import com.example.administrator.qingming.model.ModelZhenCha;

import java.util.List;

/**
 * Created by Administrator on 2017/7/6 0006.
 */

public class JianChaAdapter extends BaseAdapter {
    Context context;
    List<ModelJiancha.ResultBean> list;

    public JianChaAdapter(Context context,List<ModelJiancha.ResultBean> list){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_jiancha,null);
            viewHolder.number = (TextView) convertView.findViewById(R.id.case_item_name);
            viewHolder.banan = (TextView) convertView.findViewById(R.id.case_item_banan);
            viewHolder.tel = (TextView) convertView.findViewById(R.id.case_item_xingjuren);
            viewHolder.bm = (TextView) convertView.findViewById(R.id.case_item_bumeng);
            viewHolder.cbr = (TextView) convertView.findViewById(R.id.case_item_chengbanren);
            viewHolder.srart = (TextView) convertView.findViewById(R.id.start_time);
            viewHolder.finish = (TextView) convertView.findViewById(R.id.finish_time);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ModelJiancha.ResultBean resultBeen = list.get(position);
        viewHolder.number.setText(resultBeen.getAh_number());
        viewHolder.banan.setText(resultBeen.getBajg());
        viewHolder.tel.setText(resultBeen.getTel());
        viewHolder.bm.setText(resultBeen.getBm());
        viewHolder.cbr.setText(resultBeen.getCbr());
        String srarttime = resultBeen.getScqs_star();
        String finishtime = resultBeen.getScqs_end();
        //截取 月 和 日 字符串
        if(srarttime != null ){
            String year = srarttime.substring(0, 10);
            String years = finishtime.substring(0, 10);
            viewHolder.srart.setText(year);
            viewHolder.finish.setText(years);
        }
        return convertView;
    }

    final static class ViewHolder{
        TextView number;
        TextView banan;
        TextView tel;
        TextView bm;
        TextView cbr;
        TextView srart;
        TextView finish;
    }
}
