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
import com.example.administrator.qingming.interfaces.GetResultCallBack;
import com.example.administrator.qingming.model.Constants;
import com.example.administrator.qingming.model.ModelShouFei;
import com.example.administrator.qingming.qinminutils.GsonUtil;
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
    List<ModelShouFei.ResultBean> list;
    LsszAdapter lsszAdapter;
    TextView sf,tf;
    TextView start_time,finish_time,search;
    private static final int DATE_DIALOG = 0;
    private static final int DATE_DIALOY = 1;
    int mYear,mMonth,mDay;
    ImageView backbtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lssz);

        SharedPreferences sharedPreferences = getSharedPreferences("qinmin", Context.MODE_PRIVATE);
        id =sharedPreferences.getString("cid","");

        initView();
        getHttp();

        //获取日期
        final Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
    }

    private void initView() {
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
        lsszAdapter.setOnItemClickListener(new LsszAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {

            }
        });
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back_btn:
                    finish();
                    break;
                case R.id.start_time:
                    showDialog(DATE_DIALOG);
                    break;
                case R.id.finish_time:
                    showDialog(DATE_DIALOY);
                    break;
                case R.id.search:
                    break;
            }
        }
    };

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG:
                return new DatePickerDialog(this,onDateSetListener, mYear, mMonth, mDay);
            case DATE_DIALOY:
                return new DatePickerDialog(this,monDateSetListener, mYear, mMonth, mDay);
        }
        return null;
    }

    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            display();
        }
    };
    DatePickerDialog.OnDateSetListener monDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            finplay();
        }
    };

    /**
     * 设置日期 利用StringBuffer追加
     */
    public void display() {
        start_time.setText(new StringBuffer().append(mYear).append("-").append(mMonth + 1).append("-").
                append(mDay).append(" "));
        start_time.setTextColor(getResources().getColor(R.color.black));
    }
    public void finplay() {
        finish_time.setText(new StringBuffer().append(mYear).append("-").append(mMonth + 1).append("-").
                append(mDay).append(" "));
        finish_time.setTextColor(getResources().getColor(R.color.black));
    }

    String id;
    private void getHttp(){
        MainApi.getInstance(this).getshoufeixqApi(id, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                swipeRefreshLayout.setRefreshing(false);
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
        getHttp();
    }
}
