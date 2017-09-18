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
 * Created by Administrator on 2017/9/8 0008.
 */

public class SealApplyForDetailsAdapter extends RecyclerView.Adapter<SealApplyForDetailsAdapter.ViewHolder> {
    List<ModelSealApplyFor.ResultBean> list;
    Context context;
    private OnItemClickListener mOnItemClickListener = null;

    public SealApplyForDetailsAdapter(List<ModelSealApplyFor.ResultBean> list,Context context){
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
    public SealApplyForDetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_seal_apply_for_details,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ModelSealApplyFor.ResultBean resultBean = list.get(position);
        holder.item_wtr.setText(resultBean.getSealwtr());
        holder.sealname.setText(resultBean.getWjm());
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

        //审批（1、审批中；2、审批通过；3、审批未通过）
        if(resultBean.getSeal_state().equals("1")){
            holder.item_state.setText("审批中");
            holder.search.setVisibility(View.GONE);
            holder.insert.setVisibility(View.GONE);
        }else if(resultBean.getSeal_state().equals("2")){
            holder.del.setVisibility(View.INVISIBLE);
            holder.insert.setVisibility(View.INVISIBLE);
            holder.item_state.setText("审批通过");
        }else if(resultBean.getSeal_state().equals("3")){
            holder.item_state.setText("审批未通过");
            holder.del.setVisibility(View.INVISIBLE);
            holder.insert.setVisibility(View.INVISIBLE);
        }

        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getLayoutPosition();
                mOnItemClickListener.onItemClick(v,pos);
            }
        });

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
        TextView item_wtr,sealname;
        TextView item_yysx,item_state;
        TextView del,insert,search;
        public ViewHolder(View itemView) {
            super(itemView);
            sealname = (TextView) itemView.findViewById(R.id.sealname);
            item_wtr = (TextView) itemView.findViewById(R.id.item_wtr);
            item_yysx = (TextView) itemView.findViewById(R.id.item_yysx);
            item_state = (TextView) itemView.findViewById(R.id.item_state);
            del = (TextView) itemView.findViewById(R.id.del);
            insert = (TextView) itemView.findViewById(R.id.insert);
            search = (TextView) itemView.findViewById(R.id.search);
        }
    }
}
