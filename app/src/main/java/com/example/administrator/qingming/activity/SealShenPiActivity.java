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
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.adapter.SealApplyForAdapter;
import com.example.administrator.qingming.adapter.SealShenPiAdapter;
import com.example.administrator.qingming.api.BaseApi;
import com.example.administrator.qingming.api.MainApi;
import com.example.administrator.qingming.interfaces.GetResultCallBack;
import com.example.administrator.qingming.model.Constants;
import com.example.administrator.qingming.model.ModelSealApplyFor;
import com.example.administrator.qingming.qinminutils.GsonUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/13 0013.
 */

public class SealShenPiActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener{
    private List<ModelSealApplyFor.ResultBean> list;
    private ImageView back_btn;
    private RecyclerView recycle;
    private SwipeRefreshLayout swipe;
    private SealShenPiAdapter sealadapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seal_shenpi);

        SharedPreferences sharedPreferences = getSharedPreferences("qinmin", Context.MODE_PRIVATE);
        gsid = sharedPreferences.getString("cid","");

        initView();
        search();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置垂直方向
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        // 设置布局管理器
        recycle.setLayoutManager(layoutManager);
    }

    private void initView() {
        list = new ArrayList<>();
        back_btn = (ImageView) findViewById(R.id.back_btn);
        recycle = (RecyclerView) findViewById(R.id.recycle);
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);

        swipe.setOnRefreshListener(this);
        sealadapter = new SealShenPiAdapter(list , SealShenPiActivity.this);
        recycle.setAdapter(sealadapter);

        sealadapter.setOnItemClickListener(new SealShenPiAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                switch (view.getId()){
                    case R.id.insert:
                        Intent intent = new Intent(SealShenPiActivity.this,SealShenPiDetailsActivity.class);
                        intent.putExtra("ah_number",list.get(i).getAh_number());
                        intent.putExtra("ay",list.get(i).getAy());
                        intent.putExtra("dsr",list.get(i).getDsr());
                        intent.putExtra("dfdsr",list.get(i).getDfdsr());
                        intent.putExtra("wtr",list.get(i).getWtr());
                        intent.putExtra("id",list.get(i).getId());
                        intent.putExtra("caseid",list.get(i).getCase_id());
                        startActivity(intent);
                        break;
                    case R.id.search:
                        Intent intent1 = new Intent(SealShenPiActivity.this,SealShenPiDetailsActivity.class);
                        intent1.putExtra("ah_number",list.get(i).getAh_number());
                        intent1.putExtra("ay",list.get(i).getAy());
                        intent1.putExtra("dsr",list.get(i).getDsr());
                        intent1.putExtra("dfdsr",list.get(i).getDfdsr());
                        intent1.putExtra("wtr",list.get(i).getWtr());
                        intent1.putExtra("id",list.get(i).getId());
                        startActivity(intent1);
                        break;
                }
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    String gsid;
    private void search(){
        MainApi.getInstance(this).getsealshenpiApi(gsid, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                swipe.setRefreshing(false);
                if(type == Constants.TYPE_SUCCESS){
                    List<ModelSealApplyFor.ResultBean> resultbean = GsonUtil.fromJsonList(new Gson(),result,
                            ModelSealApplyFor.ResultBean.class);
                    list.clear();
                    list.addAll(resultbean);

                    sealadapter.notifyDataSetChanged();
                }else BaseApi.showErrMsg(SealShenPiActivity.this,result);
            }
        });
    }

    @Override
    public void onRefresh() {
        search();
    }
}
