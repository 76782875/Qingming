package com.example.administrator.qingming.work;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.qingming.R;

import java.util.List;

/**
 * Created by Administrator on 2017/5/18 0018.
 */

public class WorkLogAdapter extends RecyclerView.Adapter<WorkLogAdapter.ViewHolder> implements View.OnClickListener{
    public Context context;
    public List<WorkLogModel> list;
    private OnItemClickListener mOnItemClickListener = null;

    public WorkLogAdapter(Context context,List<WorkLogModel> list){
        this.context = context;
        this.list = list;
    }

    //设置监听接口
    public interface OnItemClickListener{
        void onItemClick(View view,int i);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v =LayoutInflater.from(context).inflate(R.layout.item_create_work_log,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(v);
        v.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        WorkLogModel worklogmodel = list.get(i);
        viewHolder.time.setText(worklogmodel.getTime());
        viewHolder.type.setText(worklogmodel.getType());
        viewHolder.lb.setText(worklogmodel.getLb());
        viewHolder.worktime.setText(worklogmodel.getWorktime());
        viewHolder.numb.setText(worklogmodel.getNumb());
        viewHolder.content.setText(worklogmodel.getContent());
        viewHolder.gk.setText(worklogmodel.getGk());
        viewHolder.itemView.setTag(i);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView time;
        TextView type;
        TextView lb;
        TextView numb;
        TextView worktime;
        TextView content;
        TextView gk;

        public ViewHolder(View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.work_item_name);
            type = (TextView) itemView.findViewById(R.id.work_item_type);
            lb = (TextView) itemView.findViewById(R.id.work_item_lb);
            numb = (TextView) itemView.findViewById(R.id.work_item_numb);
            worktime = (TextView) itemView.findViewById(R.id.work_item_time);
            content = (TextView) itemView.findViewById(R.id.work_item_content);
            gk = (TextView) itemView.findViewById(R.id.work_item_gk);
        }

    }
}
