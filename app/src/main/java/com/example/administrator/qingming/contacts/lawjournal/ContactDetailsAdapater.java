package com.example.administrator.qingming.contacts.lawjournal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.model.ModelContactDetail;

import java.util.List;

/**
 * Created by Administrator on 2017/5/2.
 */

public class ContactDetailsAdapater extends BaseAdapter {
    Context context;
    List<ModelContactDetail.ResultBean> list;
    public ContactDetailsAdapater(Context context,List<ModelContactDetail.ResultBean> list){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.contact_details_item,null);
            viewHolder.tvname = (TextView) convertView.findViewById(R.id.name);
            viewHolder.tvcompany = (TextView) convertView.findViewById(R.id.company);
//            viewHolder.cont = (TextView) convertView.findViewById(R.id.company_zhi);
//            viewHolder.image1 = (ImageView) convertView.findViewById(R.id.imageView_phone);
//            viewHolder.image2 = (ImageView) convertView.findViewById(R.id.imageView_xin);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvname.setText(list.get(position).getName());

        if(position == 1){
            viewHolder.image1.setVisibility(View.VISIBLE);
            viewHolder.image2.setVisibility(View.VISIBLE);
        }else if(position == 2 || position == 4 || position == 5){
            viewHolder.image1.setVisibility(View.VISIBLE);
        }else {
            viewHolder.image1.setVisibility(View.GONE);
            viewHolder.image2.setVisibility(View.GONE);
        }
        if(position > 0){
            viewHolder.tvname.setTextSize(16);
            viewHolder.tvname.setTextColor(context.getResources().getColor(R.color.cyan));
            viewHolder.tvcompany.setTextColor(context.getResources().getColor(R.color.cyan));
            viewHolder.cont.setText("");
        }
        return convertView;
    }

    final static class ViewHolder {
        TextView tvname;
        TextView tvcompany;
        TextView cont;
        ImageView image1;
        ImageView image2;
    }
}
