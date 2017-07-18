package com.example.administrator.qingming.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.adapter.ShouFeiAdapter;
import com.example.administrator.qingming.api.BaseApi;
import com.example.administrator.qingming.api.MainApi;
import com.example.administrator.qingming.interfaces.GetResultCallBack;
import com.example.administrator.qingming.model.Constants;
import com.example.administrator.qingming.model.ModelPress;
import com.example.administrator.qingming.model.ModelShouFei;
import com.example.administrator.qingming.qinminutils.GsonUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public class ShoufeiActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener{
    private ImageView backbtn;
    private TextView examine1,examine2,examine3;
    private View examine_line1,examine_line2,examine_line3;
    private List<ModelShouFei.ResultBean> list;
    private List<ModelShouFei.ResultBean> list1;
    private List<ModelShouFei.ResultBean> list2;
    private List<ModelShouFei.ResultBean> list3;
    private RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    ShouFeiAdapter shoufei;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoufeiliebiao);

        SharedPreferences sharedPreferences = getSharedPreferences("qinmin", Context.MODE_PRIVATE);
        gsid =sharedPreferences.getString("cid","");
        initView();
        getHttp();
    }

    private void initView() {
        list = new ArrayList<>();
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();
        backbtn = (ImageView) findViewById(R.id.back_btn);
        examine1 = (TextView) findViewById(R.id.examine1);
        examine2 = (TextView) findViewById(R.id.examine2);
        examine3 = (TextView) findViewById(R.id.examine3);
        examine_line1 = findViewById(R.id.examine_line1);
        examine_line3 = findViewById(R.id.line3);
        examine_line2 = findViewById(R.id.line2);
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置垂直方向
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

        shoufei= new ShouFeiAdapter(ShoufeiActivity.this,list);
        recyclerView.setAdapter(shoufei);
        shoufei.setOnItemClickListener(new ShouFeiAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                String id = list.get(i).getId();
                String an = list.get(i).getAh_number();
                String skrq = list.get(i).getAudit_time();
                double sf = list.get(i).getSfje();
                String fylx = null;
                if(list.get(i).getFylx().equals("1")){
                    fylx = "预收费";
                }else if(list.get(i).getFylx().equals("2")){
                    fylx = "追加费用";
                }else if(list.get(i).getFylx().equals("3")){
                    fylx = "尾款";
                }else if(list.get(i).getFylx().equals("4")){
                    fylx = "退款";
                }
                String sffs = null;
                if(list.get(i).getSflx().equals("1")){
                    sffs ="现金";
                }else if (list.get(i).getSflx().equals("2")){
                    sffs ="刷卡";
                }
                String bz = list.get(i).getBz();
                Intent intent = new Intent(ShoufeiActivity.this,AddChargeActivity.class);
                intent.putExtra("index",2);
                intent.putExtra("id",id);
                intent.putExtra("an",an);
                intent.putExtra("skrq",skrq);
                intent.putExtra("sf",sf);
                intent.putExtra("fylx",fylx);
                intent.putExtra("sffs",sffs);
                intent.putExtra("bz",bz);
                startActivity(intent);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(this);//刷新接口

        backbtn.setOnClickListener(onClickListener);
        examine1.setOnClickListener(onClickListener);
        examine2.setOnClickListener(onClickListener);
        examine3.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back_btn:
                    finish();
                    break;
                case R.id.examine1:
                    index =0;
                    setListData();
                    examine_line1.setVisibility(View.VISIBLE);
                    examine_line2.setVisibility(View.INVISIBLE);
                    examine_line3.setVisibility(View.INVISIBLE);
                    examine1.setTextColor(getResources().getColor(R.color.black));
                    examine2.setTextColor(getResources().getColor(R.color.blue));
                    examine3.setTextColor(getResources().getColor(R.color.blue));
                    break;
                case R.id.examine2:
                    index =1;
                    setListData();
                    examine_line1.setVisibility(View.INVISIBLE);
                    examine_line2.setVisibility(View.VISIBLE);
                    examine_line3.setVisibility(View.INVISIBLE);
                    examine1.setTextColor(getResources().getColor(R.color.blue));
                    examine2.setTextColor(getResources().getColor(R.color.black));
                    examine3.setTextColor(getResources().getColor(R.color.blue));
                    break;
                case R.id.examine3:
                    index =2;
                    setListData();
                    examine_line3.setVisibility(View.VISIBLE);
                    examine_line1.setVisibility(View.INVISIBLE);
                    examine_line2.setVisibility(View.INVISIBLE);
                    examine3.setTextColor(getResources().getColor(R.color.black));
                    examine2.setTextColor(getResources().getColor(R.color.blue));
                    examine1.setTextColor(getResources().getColor(R.color.blue));
                    break;
            }
        }
    };

    int index = 0;
    private void setListData(){
        list.clear();
        if (index == 0)
            list .addAll( list1 );
        else if(index == 1)
            list.addAll(list2);
        else if(index == 2)
            list.addAll(list3);
        shoufei.notifyDataSetChanged();
    }

    String gsid;
    private void getHttp(){
        MainApi.getInstance(this).getshoufeixqApi(gsid, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                swipeRefreshLayout.setRefreshing(false);
                if(type == Constants.TYPE_SUCCESS){
                    List<ModelShouFei.ResultBean> resultBean = GsonUtil.fromJsonList(new Gson(),
                            result, ModelShouFei.ResultBean.class);
                    list1.clear();
                    list2.clear();
                    list3.clear();
                    for(int i=0;i<resultBean.size();i++){
                        if(resultBean.get(i).getZt().equals("1")){
                            list1.add(resultBean.get(i));
                        }else if(resultBean.get(i).getZt().equals("2")){
                            list2.add(resultBean.get(i));
                        }else if(resultBean.get(i).getZt().equals("3")){
                            list3.add(resultBean.get(i));
                        }
                    }
                    setListData();
                }else BaseApi.showErrMsg(ShoufeiActivity.this,result);
            }
        });
    }
    @Override
    public void onRefresh() {
        getHttp();
    }
}
