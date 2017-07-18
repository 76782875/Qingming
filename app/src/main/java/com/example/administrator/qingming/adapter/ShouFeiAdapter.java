package com.example.administrator.qingming.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.model.ModelShouFei;

import java.util.List;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public class ShouFeiAdapter extends RecyclerView.Adapter<ShouFeiAdapter.ViewHolder> {
    private OnItemClickListener mOnItemClickListener = null;
    private List<ModelShouFei.ResultBean> list = null;
    private Context context;
    private String footViewText = "暂无更多数据";//FootView的内容
    //两个final int类型表示ViewType的两种类型
    private final int NORMAL_TYPE = 0;
    private final int FOOT_TYPE = 11;

    public ShouFeiAdapter(Context context,List<ModelShouFei.ResultBean> list){
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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View normal_views = LayoutInflater.from(context).inflate(R.layout.item_shoufeil,parent,false);
        View foot_view = LayoutInflater.from(context).inflate(R.layout.default_footer,parent,false);

        if (viewType == FOOT_TYPE)
            return new ViewHolder(foot_view, FOOT_TYPE);
        return new ViewHolder(normal_views, NORMAL_TYPE);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (getItemViewType(position) == FOOT_TYPE) {
            holder.tvFootView.setText(footViewText);
        } else {
            ModelShouFei.ResultBean resultBean = list.get(position);
//        1 预收费  2 追加费用 3尾款  4退款
            if (resultBean.getFylx().equals("1")) {
                holder.case_item_type.setText("预收费");
            } else if (resultBean.getFylx().equals("2")) {
                holder.case_item_type.setText("追加费用");
            } else if (resultBean.getFylx().equals("3")) {
                holder.case_item_type.setText("尾款");
            } else if (resultBean.getFylx().equals("4")) {
                holder.case_item_type.setText("退款");
            }
            holder.case_item_jyje.setText(""+resultBean.getSfje());
            holder.case_item_person.setText(resultBean.getAudit_name());
            holder.finish_time.setText(resultBean.getAudit_time());

            if (mOnItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getLayoutPosition();
                        mOnItemClickListener.onItemClick(holder.itemView, pos);
                    }
                });
            }

            holder.itemView.setTag(position);
        }
    }

    //创建一个方法来设置footView中的文字
    public void setFootViewText(String footViewText) {
        this.footViewText = footViewText;
    }

    @Override
    public int getItemCount() {
        return list.size()+1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView case_item_type;
        TextView case_item_jyje;
        TextView case_item_person;
        TextView finish_time;
        TextView tvFootView;
        public ViewHolder(View itemView,int viewtype) {
            super(itemView);
            if(viewtype == NORMAL_TYPE){
                case_item_type = (TextView) itemView.findViewById(R.id.case_item_type);
                case_item_jyje = (TextView) itemView.findViewById(R.id.case_item_jyje);
                case_item_person = (TextView) itemView.findViewById(R.id.case_item_person);
                finish_time = (TextView) itemView.findViewById(R.id.finish_time);
            }else {
                tvFootView = (TextView) itemView.findViewById(R.id.default_footer_title);
            }

        }
    }
}
