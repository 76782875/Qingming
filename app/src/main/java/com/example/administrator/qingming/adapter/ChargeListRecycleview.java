package com.example.administrator.qingming.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.model.ChargeListModel;

import java.util.List;

/**
 * Created by Administrator on 2017/6/28 0028.
 */

public class ChargeListRecycleview extends RecyclerView.Adapter<ChargeListRecycleview.ViewHolder> {
    private List<ChargeListModel.ResultBean> list = null;
    private Context context;

    public ChargeListRecycleview(Context context,List<ChargeListModel.ResultBean> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public ChargeListRecycleview.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_charge_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ChargeListRecycleview.ViewHolder holder, int position) {
        ChargeListModel.ResultBean caseModel = list.get(position);
        holder.shr.setText(caseModel.getAudit_name());
        holder.zt.setText(caseModel.getZt());
        holder.money.setText(""+caseModel.getSfje());
        holder.szrq.setText(caseModel.getCreate_time());
        if(caseModel.getFylx().equals("1")){
            holder.fylx.setText("预收款");
        }else if(caseModel.getFylx().equals("1")){
            holder.fylx.setText("追加费用");
        }else if(caseModel.getFylx().equals("1")){
            holder.fylx.setText("尾款");
        }else {
            holder.fylx.setText("退款");
        }

        if(caseModel.getSflx().equals("1")){
            holder.zffs.setText("现金");
        }else {
            holder.zffs.setText("刷卡");
        }

        if(caseModel.getZt().equals("1")){
            holder.zt.setText("申请中");
        }else if(caseModel.getZt().equals("2")){
            holder.zt.setText("已确认");
        }else {
            holder.zt.setText("作废");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView fylx;
        TextView szrq;
        TextView money;
        TextView zffs;
        TextView zt;
        TextView shr;
        public ViewHolder(View itemView) {
            super(itemView);
            fylx = (TextView) itemView.findViewById(R.id.fylx);
            szrq = (TextView) itemView.findViewById(R.id.szrq);
            money = (TextView) itemView.findViewById(R.id.money);
            zffs = (TextView) itemView.findViewById(R.id.zffs);
            zt = (TextView) itemView.findViewById(R.id.zt);
            shr = (TextView) itemView.findViewById(R.id.shr);
        }
    }


}
