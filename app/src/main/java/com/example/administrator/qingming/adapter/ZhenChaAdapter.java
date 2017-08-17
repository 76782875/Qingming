package com.example.administrator.qingming.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.model.ModelZhenCha;

import java.util.List;

/**
 * Created by Administrator on 2017/7/5 0005.
 */

public class ZhenChaAdapter extends BaseAdapter{
    Context context;
    List<ModelZhenCha.ResultBean> list;

    public ZhenChaAdapter(Context context,List<ModelZhenCha.ResultBean> list){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_zhencha,null);
            viewHolder.number = (TextView) convertView.findViewById(R.id.case_item_name);
            viewHolder.banan = (TextView) convertView.findViewById(R.id.case_item_banan);
            viewHolder.zcha = (TextView) convertView.findViewById(R.id.case_item_numb);
            viewHolder.bm = (TextView) convertView.findViewById(R.id.case_item_bumeng);
            viewHolder.cbr = (TextView) convertView.findViewById(R.id.case_item_chengbanren);
            viewHolder.xjsj = (TextView) convertView.findViewById(R.id.case_item_xingjuren);
            viewHolder.dbsj = (TextView) convertView.findViewById(R.id.case_item_daibu);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ModelZhenCha.ResultBean resultBeen = list.get(position);
        viewHolder.number.setText(resultBeen.getAh_number());
        viewHolder.banan.setText(resultBeen.getBajg());
        viewHolder.zcha.setText(resultBeen.getZcah());
        viewHolder.bm.setText(resultBeen.getBm());
        viewHolder.cbr.setText(resultBeen.getCbr());
        if( resultBeen.getXjsj()!=null){
            String time = resultBeen.getXjsj();
            //截取 月 和 日 字符串
            String year = time.substring(0, 10);
            viewHolder.xjsj.setText(year);
        }else {
            viewHolder.xjsj.setText(resultBeen.getXjsj());
        }
       if(resultBeen.getDbsj()!=null){
           String time1 = resultBeen.getDbsj();
           String year1 = time1.substring(0, 10);
           viewHolder.dbsj.setText(year1);
       }else {
           viewHolder.dbsj.setText(resultBeen.getDbsj());
       }

        return convertView;
    }

    final static class ViewHolder{
        TextView number;
        TextView banan;
        TextView zcha;
        TextView bm;
        TextView cbr;
        TextView xjsj;
        TextView dbsj;
    }
}
