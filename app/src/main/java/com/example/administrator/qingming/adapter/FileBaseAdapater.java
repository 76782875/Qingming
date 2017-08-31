package com.example.administrator.qingming.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.api.MainApi;
import com.example.administrator.qingming.model.ModelFile;

import java.util.List;

/**
 * Created by Administrator on 2017/6/1 0001.
 */

public class FileBaseAdapater extends BaseAdapter implements View.OnClickListener{
    List<ModelFile.ResultBean> list1;
    List<ModelFile.ResultBean> list2;
    Context context;
    int tag =1;
    private Callback callback;
    public FileBaseAdapater(List<ModelFile.ResultBean> list1,List<ModelFile.ResultBean> list2, Context context,Callback callback){
        this.context =context;
        this.list1 = list1;
        this.list2 = list2;
        this.callback = callback;
    }
    @Override
    public int getCount() {
        return tag==1?list1.size():list2.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_comoany_file,null);
            viewHolder = new ViewHolder();
            viewHolder.item_time = (TextView) convertView.findViewById(R.id.item_time);
            viewHolder.item_name = (TextView) convertView.findViewById(R.id.item_name);
            viewHolder.item_numb = (TextView) convertView.findViewById(R.id.item_numb);
            viewHolder.gllx = (TextView) convertView.findViewById(R.id.gllx);
            viewHolder.gly = (TextView) convertView.findViewById(R.id.gly);
            viewHolder.one = (LinearLayout) convertView.findViewById(R.id.one);
            viewHolder.two = (LinearLayout) convertView.findViewById(R.id.two);
            viewHolder.download = (TextView) convertView.findViewById(R.id.download);
            viewHolder.det = (TextView) convertView.findViewById(R.id.del);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ModelFile.ResultBean bean ;

        viewHolder.download.setTag(position);
        viewHolder.det.setTag(position);
        viewHolder.download.setOnClickListener(this);
        viewHolder.det.setOnClickListener(this);

        viewHolder.one.setVisibility(View.GONE);
        viewHolder.two.setVisibility(View.GONE);
        viewHolder.det.setVisibility(View.GONE);

        if(tag == 1) {
            bean= list1.get(position);
            if(bean.iszw()){
                viewHolder.det.setVisibility(View.VISIBLE);
            }
        }else {
            bean = list2.get(position);
            viewHolder.one.setVisibility(View.VISIBLE);
            viewHolder.two.setVisibility(View.VISIBLE);
            viewHolder.det.setVisibility(View.VISIBLE);
        }
            viewHolder.item_name.setText(bean.getWjm());
            viewHolder.item_time.setText(bean.getCreatetime());
            viewHolder.gly.setText(bean.getCreatename());
            if (bean.getCase_ahnumber() != null) {
                viewHolder.item_numb.setText(bean.getCase_ahnumber());
            } else {
                viewHolder.item_numb.setText("无关联案件");
            }
            if (bean.getCase_type() == null) {
                viewHolder.gllx.setText("无关联类型");
            } else if (bean.getCase_type().equals("1")) {
                viewHolder.gllx.setText("工作日志");
            } else if (bean.getCase_type().equals("2")) {
                viewHolder.gllx.setText("法院开庭信息");
            } else if (bean.getCase_type().equals("3")) {
                viewHolder.gllx.setText("侦查机关");
            } else if (bean.getCase_type().equals("4")) {
                viewHolder.gllx.setText("检查机关");
            } else {
                viewHolder.gllx.setText("审批文件");
                viewHolder.det.setVisibility(View.GONE);
            }


        return convertView;
    }


    /**
     * 自定义接口，用于回调按钮点击事件到Activity
     */
     public interface Callback {
         public void click(View v);
     }

    @Override
     public void onClick(View v){
        callback.click(v);
     }

    public int getadapterTag() {
        return tag;
    }

    public void setadapterTag(int tag) {
        this.tag = tag;
    }


    final static class ViewHolder{
        LinearLayout one,two;
        TextView item_name;
        TextView item_numb;
        TextView gllx;
        TextView item_time;
        TextView gly;
        TextView download;
        TextView det;
    }
}
