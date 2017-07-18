package com.example.administrator.qingming.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.model.ModelLszs;


import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/13 0013.
 */

public class LszsAdapter extends RecyclerView.Adapter<LszsAdapter.ViewHolder> {
    Context context;
    List<ModelLszs> list;
    private final int NORMAL_TYPE = 0;
    private final int HEAD_TYPE_ONE = 11;
    private String headViewTextoneone = "宪法法律";
    private final int HEAD_TYPE_TWO = 12;
    private String headViewTextonetwo = "行政法规与司法解释";
    private final int HEAD_TYPE_THREE = 13;
    private String headViewTextonethree = "地方性法规规章";
    private OnItemClickListener mOnItemClickListener = null;

    public LszsAdapter( Context context,List<ModelLszs> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0  ){
            return HEAD_TYPE_ONE;
        }else if(position == 10 ){
            return HEAD_TYPE_TWO;
        }else if(position == 15){
            return HEAD_TYPE_THREE;
        }else {
            return NORMAL_TYPE;
        }
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_zs,parent,false);
        return new ViewHolder(view, NORMAL_TYPE);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
//        if(getItemViewType(position)==HEAD_TYPE_ONE){
//            holder.head.setText(headViewTextoneone);
//        }else if(getItemViewType(position)==HEAD_TYPE_TWO){
//            holder.head.setText(headViewTextonetwo);
//        }else if(getItemViewType(position)==HEAD_TYPE_THREE){
//            holder.head.setText(headViewTextonethree);
//        }else {
            holder.imageView.setImageResource(list.get(position).getImage());
            holder.textView.setText(list.get(position).getName());
//        }
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

    //创建一个方法来设置footView中的文字
    public void setHeadViewText(String headViewText) {
        this.headViewTextoneone = headViewText;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
//        TextView head;
        public ViewHolder(View itemView,int viewtype) {
            super(itemView);
//            if(viewtype == HEAD_TYPE_ONE){
//                head = (TextView) itemView.findViewById(R.id.head);
//            }else if(viewtype == HEAD_TYPE_TWO){
//                head = (TextView) itemView.findViewById(R.id.head);
//            }else if(viewtype == HEAD_TYPE_THREE){
//                head = (TextView) itemView.findViewById(R.id.head);
//            }else {
                imageView = (ImageView) itemView.findViewById(R.id.image);
                textView = (TextView) itemView.findViewById(R.id.name);
//            }
        }
    }
}
