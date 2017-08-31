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

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.adapter.FaPiaoAdapter;
import com.example.administrator.qingming.api.BaseApi;
import com.example.administrator.qingming.api.MainApi;
import com.example.administrator.qingming.interfaces.GetResultCallBack;
import com.example.administrator.qingming.model.Constants;
import com.example.administrator.qingming.model.ModelFaPiao;
import com.example.administrator.qingming.qinminutils.GsonUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public class FaPiaoActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener{
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ImageView back_btn,add_btn;
    private List<ModelFaPiao.ResultBean> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fapiao);

        SharedPreferences sharedPreferences = getSharedPreferences("qinmin", Context.MODE_PRIVATE);
        create_id = sharedPreferences.getString("id","");

        initView();
        getHttp();
    }

    private void initView() {
        list = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        back_btn = (ImageView) findViewById(R.id.back_btn);
        add_btn = (ImageView) findViewById(R.id.add_btn);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        swipeRefreshLayout.setOnRefreshListener(this);
        fapiao = new FaPiaoAdapter(list,this);
        recyclerView.setAdapter(fapiao);
        fapiao.setOnItemClickListener(new FaPiaoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                String id = list.get(i).getId();
                String fpid = list.get(i).getAh_id();
                String ah_num = list.get(i).getAh_num();
                String sqr = list.get(i).getSqr();
                String fptt = list.get(i).getFptt();
                String kpxm = null;
                //1.律师代理费 2.法律顾问费 3.咨询费 4.法务托管费 5.非诉 6.其他(备注中填写) 7.支付宝 8.微信 9.新增
                if(list.get(i).getKpxm().equals("1")){
                    kpxm = "律师代理费";
                }else if (list.get(i).getKpxm().equals("2")) {
                    kpxm = "法律顾问费";
                }else if (list.get(i).getKpxm().equals("3")) {
                    kpxm = "咨询费";
                }else if (list.get(i).getKpxm().equals("4")) {
                    kpxm = "法务托管费";
                }else if (list.get(i).getKpxm().equals("5")) {
                    kpxm = "非诉";
                }else if (list.get(i).getKpxm().equals("6")) {
                    kpxm = "其他(备注中填写)";
                }else if (list.get(i).getKpxm().equals("7")) {
                    kpxm = "支付宝";
                }else if (list.get(i).getKpxm().equals("8")) {
                    kpxm = "微信";
                }else if (list.get(i).getKpxm().equals("9")) {
                    kpxm = "新增";
                }

                //      1.其他 2.个人
                String kjlx = null;
                if( list.get(i).getKjlx().equals("1")){
                    kjlx = "其他";
                }else {
                    kjlx = "个人";
                }

                //      1.增值税专用发票 2.增值税普通发票 3.通用机打发票
                String fplx = null;
                if(list.get(i).getFplx().equals("1")){
                    fplx = "增值税专用发票";
                }else if(list.get(i).getFplx().equals("2")){
                    fplx = "增值税普通发票";
                }else {
                    fplx = "通用机打发票";
                }
                String nsrsbh = list.get(i).getNsrsbh();
                String jbhkhyh = list.get(i).getJbhkhyh();
                String jbhkhzh = list.get(i).getJbhkhzh();
                String zccsdz = list.get(i).getZccsdz();
                String zcgddh = list.get(i).getZcgddh();
                String sjr = list.get(i).getSjr();
                String sjdz = list.get(i).getSjdz();
                String lxdh = list.get(i).getLxdh();
                String sqbz = list.get(i).getSqbz();
                String fph = list.get(i).getFph();
                String kprq = list.get(i).getKprq();
                String kpbz = list.get(i).getKpbz();
                String kpje = list.get(i).getKpje();

                Intent intent = new Intent(FaPiaoActivity.this,AddFaPiaoActivity.class);
                intent.putExtra("index",2);
                intent.putExtra("num",num);
                intent.putExtra("id",id);
                intent.putExtra("fpid",fpid);
                intent.putExtra("kpje",kpje);
                intent.putExtra("ah_num",ah_num);
                intent.putExtra("sqr",sqr);
                intent.putExtra("fptt",fptt);
                intent.putExtra("kpxm",kpxm);
                intent.putExtra("kjlx",kjlx);
                intent.putExtra("fplx",fplx);
                intent.putExtra("nsrsbh",nsrsbh);
                intent.putExtra("jbhkhyh",jbhkhyh);
                intent.putExtra("jbhkhzh",jbhkhzh);
                intent.putExtra("zccsdz",zccsdz);
                intent.putExtra("zcgddh",zcgddh);
                intent.putExtra("sjr",sjr);
                intent.putExtra("sjdz",sjdz);
                intent.putExtra("lxdh",lxdh);
                intent.putExtra("sqbz",sqbz);
                intent.putExtra("sqbz",sqbz);
                intent.putExtra("fph",fph);
                intent.putExtra("kprq",kprq);
                intent.putExtra("kpbz",kpbz);
                startActivity(intent);
            }
        });

        back_btn.setOnClickListener(onclicklisten);
        add_btn.setOnClickListener(onclicklisten);
    }

    View.OnClickListener onclicklisten = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back_btn:
                    finish();
                    break;
                case R.id.add_btn:
                    Intent intent = new Intent(FaPiaoActivity.this,AddFaPiaoActivity.class);
                    intent.putExtra("index",1);
                    intent.putExtra("num",num);
                    startActivity(intent);
                    break;
            }
        }
    };

    private String create_id;
    private FaPiaoAdapter fapiao;
    private Double num = 0.00;
    private void getHttp(){
        MainApi.getInstance(this).getcxfapiaoApi(create_id, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                swipeRefreshLayout.setRefreshing(false);
                if(type == Constants.TYPE_SUCCESS){
                    List<ModelFaPiao.ResultBean> resultbean = GsonUtil.fromJsonList(new Gson(),result,ModelFaPiao.ResultBean.class);
                    list.clear();
                    list.addAll(resultbean);

                    for(ModelFaPiao.ResultBean bean : resultbean){
                        num += Double.parseDouble(bean.getKpje());
                    }
                    fapiao.notifyDataSetChanged();
                }else BaseApi.showErrMsg(FaPiaoActivity.this,result);
            }
        });
    }

    @Override
    public void onRefresh() {
        getHttp();
    }
}
