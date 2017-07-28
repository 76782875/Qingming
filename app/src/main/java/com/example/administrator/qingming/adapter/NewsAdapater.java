package com.example.administrator.qingming.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.model.ModelNews;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */

public class NewsAdapater extends BaseAdapter {
    private Context context;
    private List<ModelNews.ListBean> list;
    public NewsAdapater(Context context,List<ModelNews.ListBean> list){
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
            convertView= LayoutInflater.from(context).inflate(R.layout.news_item,null);
            viewHolder = new ViewHolder();
            viewHolder.case_no = (TextView) convertView.findViewById(R.id.case_no);
            viewHolder.client = (TextView) convertView.findViewById(R.id.client);
            viewHolder.news_time = (TextView) convertView.findViewById(R.id.news_time);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //将时间戳转换成时间
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy年MM月dd日  HH:mm");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(list.get(position).getCreateDate());
        String times = sdr.format(new Date(lcc));

        viewHolder.news_time.setText(times);
        viewHolder.client.setText(list.get(position).getTheme());
        viewHolder.case_no.setText(list.get(position).getContent());
        return convertView;
    }
    final static class ViewHolder{
        TextView news_time;
        TextView client;
        TextView case_no;
    }
}
