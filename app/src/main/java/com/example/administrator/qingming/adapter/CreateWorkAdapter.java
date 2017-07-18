package com.example.administrator.qingming.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.news.casedetails.CreateModel;

import java.util.List;

/**
 * Created by Administrator on 2017/5/17 0017.
 */

public class CreateWorkAdapter extends BaseAdapter {
    Context context;
    List<CreateModel.ResultBean> list;

    public CreateWorkAdapter(Context context,List<CreateModel.ResultBean> list){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_create_work_log_two,null);
            viewHolder.content = (TextView) convertView.findViewById(R.id.work_item_content);
            viewHolder.lb = (TextView) convertView.findViewById(R.id.work_item_lb);
            viewHolder.time = (TextView) convertView.findViewById(R.id.work_item_name);
            viewHolder.numb = (TextView) convertView.findViewById(R.id.work_item_numb);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.time.setText(list.get(position).getCreate_date());

        viewHolder.numb.setText(list.get(position).getAh_number());
        viewHolder.content.setText(list.get(position).getBzsm());

        if(list.get(position).getLog_type().equals("1")){
            viewHolder.lb.setText("客户拜访");
        }else if(list.get(position).getLog_type().equals("2")){
            viewHolder.lb.setText("商务谈判");
        }else if(list.get(position).getLog_type().equals("3")){
            viewHolder.lb.setText("法律咨询");
        }else if(list.get(position).getLog_type().equals("4")){
            viewHolder.lb.setText("案例/资料检索");
        }else if(list.get(position).getLog_type().equals("5")){
            viewHolder.lb.setText("法律文书起草/修改");
        }else if(list.get(position).getLog_type().equals("6")){
            viewHolder.lb.setText("调查取证");
        }else if(list.get(position).getLog_type().equals("7")){
            viewHolder.lb.setText("刑案律师会见");
        }else if(list.get(position).getLog_type().equals("8")){
            viewHolder.lb.setText("诉讼/仲裁出庭");
        }else if(list.get(position).getLog_type().equals("9")){
            viewHolder.lb.setText("信访/社区/法援律师值");
        }

        return convertView;
    }

    final static class ViewHolder{
        TextView time;
        TextView lb;
        TextView numb;
        TextView content;

    }
}
