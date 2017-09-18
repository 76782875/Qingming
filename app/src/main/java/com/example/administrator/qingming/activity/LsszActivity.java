package com.example.administrator.qingming.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.adapter.LsszAdapter;
import com.example.administrator.qingming.api.BaseApi;
import com.example.administrator.qingming.api.MainApi;
import com.example.administrator.qingming.dialog.LoadingDialog;
import com.example.administrator.qingming.interfaces.GetResultCallBack;
import com.example.administrator.qingming.model.Constants;
import com.example.administrator.qingming.model.ModelShouFei;
import com.example.administrator.qingming.qinminutils.GsonUtil;
import com.example.administrator.qingming.work.DatePickDialogUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2017/7/17 0017.
 */

public class LsszActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener{
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private List<ModelShouFei.ResultBean> list;
    private LsszAdapter lsszAdapter;
    private TextView sf,tf;
    private TextView start_time,finish_time,search;
    private String initStartDateTime ; // 初始化开始时间
    private int mYear,mMonth,mDay;
    private ImageView backbtn;
    private LoadingDialog loadingDialog;
    private boolean iszw;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lssz);

        SharedPreferences sharedPreferences = getSharedPreferences("qinmin", Context.MODE_PRIVATE);
        id =sharedPreferences.getString("id","");
        gsid =sharedPreferences.getString("cid","");
        iszw = sharedPreferences.getBoolean("zhiwei",false);

        initView();
        if(iszw){
            getHttp();
        }else {
            getHttps();
        }

        //获取日期
        final Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH)+1;
        mDay = ca.get(Calendar.DAY_OF_MONTH);
    }

    private void initView() {
        loadingDialog = new LoadingDialog(this);
        backbtn = (ImageView) findViewById(R.id.back_btn);
        list = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        sf = (TextView) findViewById(R.id.sf);
        tf = (TextView) findViewById(R.id.tf);
        start_time = (TextView) findViewById(R.id.start_time);
        finish_time = (TextView) findViewById(R.id.finish_time);
        search = (TextView) findViewById(R.id.search);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置垂直方向
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

        start_time.setOnClickListener(onClickListener);
        finish_time.setOnClickListener(onClickListener);
        backbtn.setOnClickListener(onClickListener);
        search.setOnClickListener(onClickListener);

        swipeRefreshLayout.setOnRefreshListener(this);//刷新接口
        lsszAdapter= new LsszAdapter(LsszActivity.this,list);
        recyclerView.setAdapter(lsszAdapter);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back_btn:
                    finish();
                    break;
                case R.id.start_time:
                    start_time.setText(initStartDateTime);
                    start_time.setTextColor(getResources().getColor(R.color.black));
                    DatePickDialogUtil dateTimePickDialogUtil = new DatePickDialogUtil(initStartDateTime,LsszActivity.this);
                    dateTimePickDialogUtil.dateTimePicKDialog(start_time);
                    break;
                case R.id.finish_time:
                    finish_time.setText(initStartDateTime);
                    finish_time.setTextColor(getResources().getColor(R.color.black));
                    DatePickDialogUtil dateTimePickDialogUtil7 = new DatePickDialogUtil(initStartDateTime,LsszActivity.this);
                    dateTimePickDialogUtil7.dateTimePicKDialog(finish_time);
                    break;
                case R.id.search:
                    if(!start_time.getText().equals("请选择开始时间")){
                        if(!finish_time.getText().equals("请选择结束时间")){
                            starttime = start_time.getText().toString();
                            endtime = finish_time.getText().toString();
                            if(iszw){
                                searchHttp();
                            }else {
                                searchHttps();
                            }

                        }else {
                            Toast.makeText(LsszActivity.this, "请选择时间", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(LsszActivity.this, "请选择时间", Toast.LENGTH_SHORT).show();
                    }

                    break;
            }
        }
    };


    private String gsid;
    private String id;
    private void getHttp(){
        MainApi.getInstance(this).getshoufeixqApi(gsid, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                swipeRefreshLayout.setRefreshing(false);
                if(type == Constants.TYPE_SUCCESS){
                    List<ModelShouFei.ResultBean> resultbean = GsonUtil.fromJsonList(new Gson(),result,
                            ModelShouFei.ResultBean.class);
                    list.clear();
                    list.addAll(resultbean);
                    double sum = 0;
                    double tfsum = 0;
                    for(int i =0 ; i<resultbean.size(); i++){
                        double sfje = resultbean.get(i).getSfje();
                        String fylx = resultbean.get(i).getFylx();
                        if(fylx.equals("4")){
                            tfsum += sfje;
                        }else {
                            sum += sfje;
                        }
                    }
                    sf.setText(""+sum);
                    tf.setText(""+tfsum);
                    lsszAdapter.notifyDataSetChanged();
                }else BaseApi.showErrMsg(LsszActivity.this,result);
            }
        });
    }

    private void getHttps(){
        MainApi.getInstance(this).getgrshoufeiApi(id, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                swipeRefreshLayout.setRefreshing(false);
                if(type == Constants.TYPE_SUCCESS){
                    List<ModelShouFei.ResultBean> resultbean = GsonUtil.fromJsonList(new Gson(),result,
                            ModelShouFei.ResultBean.class);
                    list.clear();
                    list.addAll(resultbean);
                    double sum = 0;
                    double tfsum = 0;
                    for(int i =0 ; i<resultbean.size(); i++){
                        double sfje = resultbean.get(i).getSfje();
                        String fylx = resultbean.get(i).getFylx();
                        if(fylx.equals("4")){
                            tfsum += sfje;
                        }else {
                            sum += sfje;
                        }
                    }
                    sf.setText(""+sum);
                    tf.setText(""+tfsum);
                    lsszAdapter.notifyDataSetChanged();
                }else BaseApi.showErrMsg(LsszActivity.this,result);
            }
        });
    }

    String starttime;
    String endtime;
    private void searchHttp(){
        loadingDialog.setLoadingContent("加载中");
        loadingDialog.show();
        MainApi.getInstance(this).getlsszsApi(gsid,starttime,endtime, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loadingDialog.dismiss();
                if(type == Constants.TYPE_SUCCESS){
                    List<ModelShouFei.ResultBean> resultbean = GsonUtil.fromJsonList(new Gson(),result,
                            ModelShouFei.ResultBean.class);
                    list.clear();
                    list.addAll(resultbean);
                    double sum = 0;
                    for(int i =0 ; i<resultbean.size(); i++){
                        double sfje = resultbean.get(i).getSfje();
                        sum += sfje;
                    }
                    sf.setText(""+sum);
                    lsszAdapter.notifyDataSetChanged();
                }else BaseApi.showErrMsg(LsszActivity.this,result);
            }
        });
    }

    private void searchHttps(){
        loadingDialog.setLoadingContent("加载中");
        loadingDialog.show();
        MainApi.getInstance(this).getgrszsApi(id,starttime,endtime, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loadingDialog.dismiss();
                if(type == Constants.TYPE_SUCCESS){
                    List<ModelShouFei.ResultBean> resultbean = GsonUtil.fromJsonList(new Gson(),result,
                            ModelShouFei.ResultBean.class);
                    list.clear();
                    list.addAll(resultbean);
                    double sum = 0;
                    for(int i =0 ; i<resultbean.size(); i++){
                        double sfje = resultbean.get(i).getSfje();
                        sum += sfje;
                    }
                    sf.setText(""+sum);
                    lsszAdapter.notifyDataSetChanged();
                }else BaseApi.showErrMsg(LsszActivity.this,result);
            }
        });
    }

    @Override
    public void onRefresh() {
        if(iszw){
            getHttp();
        }else {
            getHttps();
        }
    }
}
