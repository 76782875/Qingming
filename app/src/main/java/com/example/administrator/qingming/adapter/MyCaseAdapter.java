package com.example.administrator.qingming.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.model.MyCaseModel;

import java.util.List;

/**
 * Created by Administrator on 2017/5/22 0022.
 */

public class MyCaseAdapter extends RecyclerView.Adapter<MyCaseAdapter.ViewHolder> {
    //两个final int类型表示ViewType的两种类型
    private final int NORMAL_TYPE = 0;
    private final int FOOT_TYPE = 11;
    private String footViewText = "暂无更多数据";//FootView的内容
    Context context;
    List<MyCaseModel.ResultBean> list;
    private OnItemClickListener mOnItemClickListener = null;
    public MyCaseAdapter(Context context, List<MyCaseModel.ResultBean> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == list.size()) {
            return FOOT_TYPE;
        }
        return NORMAL_TYPE;
    }

    //设置监听接口
    public interface OnItemClickListener{
        void onItemClick(View view,int i);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public MyCaseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View normal_views = LayoutInflater.from(context).inflate(R.layout.item_examine,parent,false);
        View foot_view = LayoutInflater.from(context).inflate(R.layout.default_footer,parent,false);
        if (viewType == FOOT_TYPE)
            return new ViewHolder(foot_view, FOOT_TYPE);
        return new ViewHolder(normal_views, NORMAL_TYPE);
    }


    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        if(getItemViewType(position) == FOOT_TYPE){
            viewHolder.tvFootView.setText(footViewText);
        }else {
            MyCaseModel.ResultBean caseModel = list.get(position);
            viewHolder.name.setText(caseModel.getAy());
            viewHolder.numb.setText(caseModel.getAh_number());
            viewHolder.consignor.setText(caseModel.getWtr());
            viewHolder.adversary.setText(caseModel.getDfdsr());
            if(caseModel.getCase_state().equals("-1")){
                viewHolder.party.setText("收案返回");
            }else if(caseModel.getCase_state().equals("1")){
                viewHolder.party.setText("预收案");
            }else if(caseModel.getCase_state().equals("2")){
                viewHolder.party.setText("财务审批收案 ");
            }else if(caseModel.getCase_state().equals("3")){
                viewHolder.party.setText("主任审批收案");
            }else if(caseModel.getCase_state().equals("4")){
                viewHolder.party.setText("办案中");
            }else if(caseModel.getCase_state().equals("5")){
                viewHolder.party.setText("财务审批结案");
            }else if(caseModel.getCase_state().equals("6")){
                viewHolder.party.setText("主任审批结案 ");
            }else if(caseModel.getCase_state().equals("7")){
                viewHolder.party.setText("已结案");
            }else if(caseModel.getCase_state().equals("0")){
                viewHolder.party.setText("终止");
            }else if(caseModel.getCase_state().equals("-4")){
                viewHolder.party.setText("结案返回");
            }

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

    }

    @Override
    public int getItemCount() {
        return list.size()+1;
    }

    //创建一个方法来设置footView中的文字
    public void setFootViewText(String footViewText) {
        this.footViewText = footViewText;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView numb;
        TextView consignor;
        TextView party;
        TextView adversary;
        TextView tvFootView;
        public ViewHolder(View itemView,int viewtype) {
            super(itemView);
            if(viewtype == NORMAL_TYPE){
                name = (TextView) itemView.findViewById(R.id.case_item_name);
                numb = (TextView) itemView.findViewById(R.id.case_item_numb);
                consignor = (TextView) itemView.findViewById(R.id.case_item_consignor);
                party = (TextView) itemView.findViewById(R.id.case_item_party);
                adversary = (TextView) itemView.findViewById(R.id.case_item_adversary);
            }else {
                tvFootView = (TextView) itemView.findViewById(R.id.default_footer_title);
            }

        }
    }
}
