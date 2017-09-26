package com.example.administrator.qingming.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.administrator.qingming.R;
import com.example.administrator.qingming.model.ModelSealApplyFor;

import java.util.List;


/**
 * Created by Administrator on 2017/9/13 0013.
 */

public class SealShenPiAdapter extends RecyclerView.Adapter<SealShenPiAdapter.ViewHolder> {
    List<ModelSealApplyFor.ResultBean> list;
    Context context;
    private OnItemClickListener mOnItemClickListener = null;

    public SealShenPiAdapter(List<ModelSealApplyFor.ResultBean> list,Context context){
        this.list = list;
        this.context = context;

    }

    //设置监听接口
    public interface OnItemClickListener{
        void onItemClick(View view,int i);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public SealShenPiAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shenpi,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SealShenPiAdapter.ViewHolder holder, int position) {
        final ModelSealApplyFor.ResultBean resultBean = list.get(position);
        holder.sealname.setText(resultBean.getSeal_name());
        holder.item_ah.setText(resultBean.getAh_number());
        holder.item_person.setText(resultBean.getLyr());
        holder.item_wtr.setText(resultBean.getSealwtr());

        //用印事项(1：公函；2：介绍信；3：律师函；4：委托代理合同；5：其他；6：授权委托书；7：收案审批表；8：律师会见介绍信)
        if(resultBean.getOfficial_seal().equals("1")){
            holder.item_yysx.setText("公函");
        }else if(resultBean.getOfficial_seal().equals("2")){
            holder.item_yysx.setText("介绍信");
        }else if(resultBean.getOfficial_seal().equals("3")){
            holder.item_yysx.setText("律师函");
        }else if(resultBean.getOfficial_seal().equals("4")){
            holder.item_yysx.setText("委托代理合同");
        }else if(resultBean.getOfficial_seal().equals("5")){
            holder.item_yysx.setText("其他");
        }else if(resultBean.getOfficial_seal().equals("6")){
            holder.item_yysx.setText("授权委托书");
        }else if(resultBean.getOfficial_seal().equals("7")){
            holder.item_yysx.setText("收案审批表");
        }else if(resultBean.getOfficial_seal().equals("8")){
            holder.item_yysx.setText("律师会见介绍信");
        }

        holder.item_time.setText(resultBean.getCreate_date().substring(0,10));
        //审批（1、审批中；2、审批通过；3、审批未通过）
        if(resultBean.getSeal_state().equals("1")){
            holder.item_state.setText("审批中");
            holder.search.setVisibility(View.GONE);
            holder.insert.setVisibility(View.VISIBLE);
        }else if(resultBean.getSeal_state().equals("2")){
            holder.item_state.setText("审批通过");
            holder.search.setVisibility(View.VISIBLE);
            holder.insert.setVisibility(View.INVISIBLE);
        }else if(resultBean.getSeal_state().equals("3")){
            holder.item_state.setText("审批未通过");
            holder.insert.setVisibility(View.INVISIBLE);
            holder.search.setVisibility(View.VISIBLE);
        }

        holder.insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getLayoutPosition();
                mOnItemClickListener.onItemClick(v,pos);
            }
        });

        holder.search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getLayoutPosition();
                mOnItemClickListener.onItemClick(v,pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView sealname,item_ah,item_person,item_wtr;
        TextView item_yysx,item_time,item_state;
        TextView insert,search;
        public ViewHolder(View itemView) {
            super(itemView);
            sealname = (TextView) itemView.findViewById(R.id.sealname);
            item_ah = (TextView) itemView.findViewById(R.id.item_ah);
            item_person = (TextView) itemView.findViewById(R.id.item_person);
            item_wtr = (TextView) itemView.findViewById(R.id.item_wtr);
            item_yysx = (TextView) itemView.findViewById(R.id.item_yysx);
            item_time = (TextView) itemView.findViewById(R.id.item_time);
            item_state = (TextView) itemView.findViewById(R.id.item_state);
            insert = (TextView) itemView.findViewById(R.id.insert);
            search = (TextView) itemView.findViewById(R.id.search);
        }
    }
}
