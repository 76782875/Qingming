package com.example.administrator.qingming.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.adapter.FaLvAdapter;
import com.example.administrator.qingming.api.BaseApi;
import com.example.administrator.qingming.api.MainApi;
import com.example.administrator.qingming.interfaces.GetResultCallBack;
import com.example.administrator.qingming.model.Constants;
import com.example.administrator.qingming.model.ModelFalv;
import com.example.administrator.qingming.qinminutils.GsonUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/17 0017.
 */

public class FaLvActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener{
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    List<ModelFalv.ResultBean> list;
    private ImageView backbtn;
    private EditText search_edit;
    private TextView search;
    String ss;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_falv);

        Bundle bundle = getIntent().getExtras();
        cons_type = bundle.getInt("conts_type");
        initView();
        getHttp();
    }

    private void initView() {
        list = new ArrayList<>();
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        backbtn = (ImageView) findViewById(R.id.back_btn);
        search = (TextView) findViewById(R.id.search);
        search_edit = (EditText) findViewById(R.id.search_edit);

        swipeRefreshLayout.setOnRefreshListener(this);

        LinearLayoutManager linearmange = new LinearLayoutManager(this);
        linearmange.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearmange);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        search_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ss = s.toString();
            }
        });
    }

    int cons_type;
    private void getHttp(){
        MainApi.getInstance(this).getfalvsApi(cons_type, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                swipeRefreshLayout.setRefreshing(false);
                if(type == Constants.TYPE_SUCCESS){
                    List<ModelFalv.ResultBean> resultbean = GsonUtil.fromJsonList(new Gson(),result,
                            ModelFalv.ResultBean.class);
                    list.clear();
                    list.addAll(resultbean);

                    FaLvAdapter modefalv = new FaLvAdapter(list,FaLvActivity.this);
                    recyclerView.setAdapter(modefalv);
                    modefalv.setOnItemClickListener(new FaLvAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int i) {
                            Toast.makeText(FaLvActivity.this,"你点击了"+i,Toast.LENGTH_SHORT).show();
                        }
                    });
                }else BaseApi.showErrMsg(FaLvActivity.this,result);
            }
        });
    }

    @Override
    public void onRefresh() {
        getHttp();
    }
}
