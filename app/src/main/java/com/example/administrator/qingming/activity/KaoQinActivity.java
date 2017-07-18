package com.example.administrator.qingming.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.adapter.QianDaoAdapter;
import com.example.administrator.qingming.api.BaseApi;
import com.example.administrator.qingming.api.MainApi;
import com.example.administrator.qingming.interfaces.GetResultCallBack;
import com.example.administrator.qingming.model.Constants;
import com.example.administrator.qingming.model.ModelFile;
import com.example.administrator.qingming.model.ModelQianDao;
import com.example.administrator.qingming.qinminutils.GsonUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/7 0007.
 */

public class KaoQinActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener{
    private List<ModelQianDao.ResultBean> list;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listview;
    QianDaoAdapter qiandao;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaoqin);

        SharedPreferences sharedPreferences = getSharedPreferences("qinmin", Context.MODE_PRIVATE);
        id = sharedPreferences.getString("id","");

        initView();
        getHttp();
    }

    private void initView() {
        list = new ArrayList<>();
        listview = (ListView) findViewById(R.id.listview);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        qiandao = new QianDaoAdapter(KaoQinActivity.this,list);
        listview.setAdapter(qiandao);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    String id;
    private void getHttp(){
        MainApi.getInstance(this).getqiandaoApi(id, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                swipeRefreshLayout.setRefreshing(false);
                if(type == Constants.TYPE_SUCCESS){
                    List<ModelQianDao.ResultBean> resultBeen = GsonUtil.fromJsonList(new Gson(),result,ModelQianDao.ResultBean.class);
                    list.clear();
                    list.addAll(resultBeen);

                    qiandao.notifyDataSetChanged();
                }else BaseApi.showErrMsg(KaoQinActivity.this,result);
            }
        });
    }

    @Override
    public void onRefresh() {
        getHttp();
    }
}
