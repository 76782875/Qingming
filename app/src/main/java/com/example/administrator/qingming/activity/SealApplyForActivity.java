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
import android.widget.Toast;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.adapter.SealApplyForAdapter;
import com.example.administrator.qingming.api.BaseApi;
import com.example.administrator.qingming.api.MainApi;
import com.example.administrator.qingming.dialog.LoadingDialog;
import com.example.administrator.qingming.interfaces.GetResultCallBack;
import com.example.administrator.qingming.model.Constants;
import com.example.administrator.qingming.model.ModelSealApplyFor;
import com.example.administrator.qingming.qinminutils.GsonUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.qingming.R.id.swipe;

/**
 * Created by Administrator on 2017/9/7 0007.
 */

public class SealApplyForActivity extends Activity implements View.OnClickListener,SwipeRefreshLayout.OnRefreshListener{
    private ImageView back_btn,add_btn;
    private RecyclerView recyclerView;
    private List<ModelSealApplyFor.ResultBean> list;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seal_apply_for);

        SharedPreferences sharedPreferences = getSharedPreferences("qinmin", Context.MODE_PRIVATE);
        createid = sharedPreferences.getString("id","");

        initView();
        search();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置垂直方向
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
    }

    private void initView() {
        loadingDialog = new LoadingDialog(this);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(swipe);
        swipeRefreshLayout.setOnRefreshListener(this);
        list = new ArrayList<>();
        back_btn = (ImageView) findViewById(R.id.back_btn);
        add_btn = (ImageView) findViewById(R.id.add_btn);
        recyclerView = (RecyclerView) findViewById(R.id.recycle);

        back_btn.setOnClickListener(this);
        add_btn.setOnClickListener(this);
        sealApply = new SealApplyForAdapter(list,SealApplyForActivity.this);
        recyclerView.setAdapter(sealApply);
        sealApply.setOnItemClickListener(new SealApplyForAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                switch (view.getId()){
                    case R.id.del:
                        id = list.get(i).getId();
                        delseal(i);
                        break;
                    case R.id.insert:
                        Intent intent = new Intent(SealApplyForActivity.this,SealApplyForDetailsActivity.class);
                        intent.putExtra("ah_number",list.get(i).getAh_number());
                        intent.putExtra("ay",list.get(i).getAy());
                        intent.putExtra("dsr",list.get(i).getDsr());
                        intent.putExtra("dfdsr",list.get(i).getDfdsr());
                        intent.putExtra("wtr",list.get(i).getWtr());
                        intent.putExtra("id",list.get(i).getId());
                        intent.putExtra("caseid",list.get(i).getCase_id());
                        intent.putExtra("index",1);
                        startActivity(intent);
                        break;
                    case R.id.search:
                        Intent intent1 = new Intent(SealApplyForActivity.this,SealApplyForDetailsActivity.class);
                        intent1.putExtra("ah_number",list.get(i).getAh_number());
                        intent1.putExtra("ay",list.get(i).getAy());
                        intent1.putExtra("dsr",list.get(i).getDsr());
                        intent1.putExtra("dfdsr",list.get(i).getDfdsr());
                        intent1.putExtra("wtr",list.get(i).getWtr());
                        intent1.putExtra("id",list.get(i).getId());
                        intent1.putExtra("index",2);
                        startActivity(intent1);
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_btn:
                finish();
                break;
            case R.id.add_btn:
                Intent intent = new Intent(SealApplyForActivity.this,SealApplyForAddActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 查看详情
     */
    String createid;
    SealApplyForAdapter sealApply;
    private void search(){
        MainApi.getInstance(this).getsealApi(createid, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                swipeRefreshLayout.setRefreshing(false);
                if(type == Constants.TYPE_SUCCESS){
                    List<ModelSealApplyFor.ResultBean> resultbean = GsonUtil.fromJsonList(new Gson(),result,
                            ModelSealApplyFor.ResultBean.class);
                    list.clear();
                    list.addAll(resultbean);

                    sealApply.notifyDataSetChanged();
                }else BaseApi.showErrMsg(SealApplyForActivity.this,result);
            }
        });
    }

    /**
     * 删除
     */
    String id;
    private void delseal(final int i){
        loadingDialog.setLoadingContent("加载中...");
        loadingDialog.show();
        MainApi.getInstance(this).getdelsealApi(id, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loadingDialog.dismiss();
                if(type == Constants.TYPE_SUCCESS){
                    list.remove(i);
                    sealApply.notifyDataSetChanged();
                }else BaseApi.showErrMsg(SealApplyForActivity.this,result);
            }
        });
    }

    @Override
    public void onRefresh() {
        search();
    }
}
