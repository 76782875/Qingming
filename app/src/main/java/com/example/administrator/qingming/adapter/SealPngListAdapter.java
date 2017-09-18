package com.example.administrator.qingming.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.model.ModelSealPngList;

import java.util.List;

/**
 * Created by Administrator on 2017/9/8 0008.
 */

public class SealPngListAdapter extends RecyclerView.Adapter<SealPngListAdapter.ViewHolder>{
    List<ModelSealPngList.ResultBean> list;
    Context context;
    private OnItemClickListener mOnItemClickListener = null;

    public SealPngListAdapter(List<ModelSealPngList.ResultBean> list,Context context){
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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_seal_apply_for_details_png_list,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.sealname.setText(list.get(position).getFilename());
//        0:未盖章；1：已盖章
        if(list.get(position).getPng_state().equals("0")){
            holder.item_state.setText("未盖章");
            holder.look.setVisibility(View.INVISIBLE);
            holder.insert.setVisibility(View.INVISIBLE);
        }else {
            holder.item_state.setText("已盖章");
            holder.seal.setVisibility(View.GONE);
        }
        holder.item_date.setText(list.get(position).getCreate_date());

        holder.look.setOnClickListener(new View.OnClickListener() {
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

        holder.seal.setOnClickListener(new View.OnClickListener() {
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
        TextView sealname,item_state,item_date;
        TextView look,insert,seal;
        public ViewHolder(View itemView) {
            super(itemView);
            sealname = (TextView) itemView.findViewById(R.id.sealname);
            item_state = (TextView) itemView.findViewById(R.id.item_state);
            item_date = (TextView) itemView.findViewById(R.id.item_date);
            look = (TextView) itemView.findViewById(R.id.look);
            insert = (TextView) itemView.findViewById(R.id.insert);
            seal = (TextView) itemView.findViewById(R.id.seal);
        }
    }
}
