package com.example.administrator.qingming.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.model.ModelFalv;

import java.util.List;

/**
 * Created by Administrator on 2017/7/17 0017.
 */

public class FaLvAdapter extends RecyclerView.Adapter<FaLvAdapter.ViewHolder> {
    List<ModelFalv.ResultBean> list;
    Context context;
    private OnItemClickListener mOnItemClickListener = null;

    public FaLvAdapter(List<ModelFalv.ResultBean> list, Context context){
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_falv,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.falv_item_name.setText(list.get(position).getCons_name());
        holder.falv_item_date.setText(list.get(position).getPerform_date());
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView,pos);
                }
            });
        }
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView falv_item_name;
        TextView falv_item_date;
        public ViewHolder(View itemView) {
            super(itemView);
            falv_item_name = (TextView) itemView.findViewById(R.id.falv_item_name);
            falv_item_date = (TextView) itemView.findViewById(R.id.falv_item_date);
        }
    }
}
