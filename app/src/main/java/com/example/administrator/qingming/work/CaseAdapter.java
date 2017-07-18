package com.example.administrator.qingming.work;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.qingming.R;

import java.util.List;

/**
 * Created by Administrator on 2017/5/15 0015.
 */

public class CaseAdapter extends BaseAdapter {
    List<CaseModel> list;
    Context context;
    Boolean cratechoose;
    public CaseAdapter(List<CaseModel> list,Context context){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.case_list_item,null);
            viewHolder.name = (TextView) convertView.findViewById(R.id.case_item_name);
            viewHolder.numb = (TextView) convertView.findViewById(R.id.case_item_numb);
            viewHolder.consignor = (TextView) convertView.findViewById(R.id.case_item_consignor);
            viewHolder.party = (TextView) convertView.findViewById(R.id.case_item_party);
            viewHolder.adversary = (TextView) convertView.findViewById(R.id.case_item_adversary);
            viewHolder.attorney = (TextView) convertView.findViewById(R.id.case_item_attorney);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.case_item_image);
            viewHolder.choose = (Button) convertView.findViewById(R.id.choose);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(list.get(position).getName());
        viewHolder.numb.setText(list.get(position).getNumb());
        viewHolder.consignor.setText(list.get(position).getConsignor());
        viewHolder.party.setText(list.get(position).getParty());
        viewHolder.adversary.setText(list.get(position).getAdversary());
        viewHolder.attorney.setText(list.get(position).getAttorney());

        return convertView;
    }


    final static class ViewHolder{
        TextView name;
        TextView numb;
        TextView consignor;
        TextView party;
        TextView adversary;
        TextView attorney;
        ImageView image;
        Button choose;
    }
}

