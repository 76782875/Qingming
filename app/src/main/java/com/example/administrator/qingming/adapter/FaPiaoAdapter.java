package com.example.administrator.qingming.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.model.ModelFaPiao;

import java.util.List;

/**
 * Created by Administrator on 2017/8/28 0028.
 */

public class FaPiaoAdapter extends RecyclerView.Adapter<FaPiaoAdapter.ViewHolder> {
    private List<ModelFaPiao.ResultBean> list;
    private Context mcontext;
    private OnItemClickListener mOnItemClickListener = null;

    public FaPiaoAdapter(List<ModelFaPiao.ResultBean> list,Context context){
        this.list = list;
        this.mcontext = context;
    }


    //设置监听接口
    public interface OnItemClickListener{
        void onItemClick(View view,int i);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.item_fapiao,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        ModelFaPiao.ResultBean resultBean = list.get(i);
        viewHolder.item_ah.setText(resultBean.getAh_num());
        viewHolder.item_person.setText(resultBean.getSqr());
        viewHolder.item_top.setText(resultBean.getFptt());
        viewHolder.item_money.setText(resultBean.getKpje());
        viewHolder.item_time.setText(resultBean.getKprq().substring(0,10));

        if (mOnItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = viewHolder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(viewHolder.itemView,pos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView item_ah;
        TextView item_person;
        TextView item_top;
        TextView item_money;
        TextView item_time;
        public ViewHolder(View itemView) {
            super(itemView);
            item_ah = (TextView) itemView.findViewById(R.id.item_ah);
            item_person = (TextView) itemView.findViewById(R.id.item_person);
            item_top = (TextView) itemView.findViewById(R.id.item_top);
            item_money = (TextView) itemView.findViewById(R.id.item_money);
            item_time = (TextView) itemView.findViewById(R.id.item_time);
        }
    }
}
