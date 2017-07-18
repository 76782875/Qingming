package com.example.administrator.qingming.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.model.ModelQianDao;
import com.example.administrator.qingming.model.MyCaseModel;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10 0010.
 */

public class QianDaoAdapter extends BaseAdapter {
    Context context;
    List<ModelQianDao.ResultBean> list;

    public QianDaoAdapter(Context context, List<ModelQianDao.ResultBean> list){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_qiandao,null);
            viewHolder.name = (TextView) convertView.findViewById(R.id.title);
            viewHolder.numb = (TextView) convertView.findViewById(R.id.dakalx);
            viewHolder.consignor = (TextView) convertView.findViewById(R.id.dakatype);
            viewHolder.adversary = (TextView) convertView.findViewById(R.id.dakafl);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ModelQianDao.ResultBean bean = list.get(position);

        viewHolder.name.setText(bean.getDaka_time());
        if(bean.getDakalx().equals("1")){
            viewHolder.numb.setText("上班");
        }else {
            viewHolder.numb.setText("下班");
        }
        //1.公司范围内打卡 2.公司范围外打卡 3.外出
        if(bean.getDakatype().equals("1")){
            viewHolder.consignor.setText("公司范围内打卡");
        }else {
            viewHolder.consignor.setText("公司范围外打卡");
        }
        //打卡分类1.1.正常 2.迟到  4.忘打卡补录 5.早退 6.后勤更正
        if(bean.getDakafl().equals("1")){
            viewHolder.adversary.setText("正常");
        }else if(bean.getDakafl().equals("2")){
            viewHolder.adversary.setText("迟到");
        }else if(bean.getDakafl().equals("3")){
            viewHolder.adversary.setText("迟到");
        }else if(bean.getDakafl().equals("4")){
            viewHolder.adversary.setText("忘打卡补录");
        }else if(bean.getDakafl().equals("5")){
            viewHolder.adversary.setText("早退");
        }else if(bean.getDakafl().equals("6")){
            viewHolder.adversary.setText("后勤更正");
        }


        return convertView;
    }

    final static class ViewHolder{
        TextView name;
        TextView numb;
        TextView consignor;
        TextView adversary;
    }
}
