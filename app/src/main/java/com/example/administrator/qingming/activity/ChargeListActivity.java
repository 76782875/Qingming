package com.example.administrator.qingming.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.adapter.ChargeListRecycleview;
import com.example.administrator.qingming.api.BaseApi;
import com.example.administrator.qingming.api.MainApi;
import com.example.administrator.qingming.interfaces.GetResultCallBack;
import com.example.administrator.qingming.model.ChargeListModel;
import com.example.administrator.qingming.model.Constants;
import com.example.administrator.qingming.qinminutils.GsonUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/28 0028.
 */

public class ChargeListActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener{
    private TextView yinshou,jiaoyi,weishou;
    private ImageView addbtn,backbtn;
    private String ah_number,id;
    private List<ChargeListModel.ResultBean> list;
    private SwipeRefreshLayout swipe;
    private RecyclerView recyc;
    private ChargeListRecycleview charge;
    private int ysje,sfje,wsje;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge_list);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("id","");
        ah_number = bundle.getString("ah_number","");
        ysje = bundle.getInt("ysje");

        initView();
        getHttp();

        yinshou.setText(""+ysje);
    }

    private void initView() {
        list = new ArrayList<>();
        addbtn = (ImageView) findViewById(R.id.add_btn);
        backbtn = (ImageView) findViewById(R.id.back_btn);
        yinshou = (TextView) findViewById(R.id.yinshou);
        jiaoyi = (TextView) findViewById(R.id.jiaoyi);
        weishou = (TextView) findViewById(R.id.weishou);
        recyc = (RecyclerView) findViewById(R.id.recycle);
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置垂直方向
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        // 设置布局管理器
        recyc.setLayoutManager(layoutManager);
        charge = new ChargeListRecycleview(ChargeListActivity.this,list);
        recyc.setAdapter(charge);

        swipe.setOnRefreshListener(this);
        addbtn.setOnClickListener(onclick);
        backbtn.setOnClickListener(onclick);
    }

    View.OnClickListener onclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back_btn:
                    finish();
                    break;
                case R.id.add_btn:
                    Intent intent = new Intent(ChargeListActivity.this,AddChargeActivity.class);
                    intent.putExtra("index",1);
                    intent.putExtra("id",id);
                    intent.putExtra("ah_number",ah_number);
                    startActivity(intent);
                    break;
            }
        }
    };

    private void getHttp(){
        MainApi.getInstance(this).getshoufeiApi(id, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                swipe.setRefreshing(false);
                if(type == Constants.TYPE_SUCCESS){
                    List<ChargeListModel.ResultBean> resultBean = GsonUtil.fromJsonList(new Gson(),
                            result, ChargeListModel.ResultBean.class);
                    list.clear();
                    list.addAll(resultBean);

                    double sum = 0;
                    for(int i = 0;i< resultBean.size();i++){
                        if(resultBean.get(i).getZt().equals("2")){
                            double d = resultBean.get(i).getSfje();
                            Log.e("=====>",""+d);
                            sum += d;
                        }
                    }

                    Log.e("=====>>",""+sum);
                    jiaoyi.setText(""+sum);
                    double aa = ysje - sum;
                    weishou.setText(""+aa);

                    charge.notifyDataSetChanged();
                }else BaseApi.showErrMsg(ChargeListActivity.this,result);
            }
        });
    }

    @Override
    public void onRefresh() {
        getHttp();
    }
}
