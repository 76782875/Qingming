package com.example.administrator.qingming.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.adapter.PressAdater;
import com.example.administrator.qingming.api.BaseApi;
import com.example.administrator.qingming.api.MainApi;
import com.example.administrator.qingming.interfaces.GetResultCallBack;
import com.example.administrator.qingming.model.Constants;
import com.example.administrator.qingming.model.ModelPress;
import com.example.administrator.qingming.qinminutils.GsonUtil;
import com.google.gson.Gson;

import java.util.*;


/**
 * Created by Administrator on 2017/5/26 0026.
 */

public class PressActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener{
    private ImageView backbtn;
    private TextView examine1,examine2,examine3;
    private View examine_line1,examine_line2,examine_line3;
    private List<ModelPress.ResultBean> list;
    private ListView listView;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        SharedPreferences sharedPreferences = getSharedPreferences("qinmin", Context.MODE_PRIVATE);
        gsid =sharedPreferences.getString("cid","");
        initView();
        getString();
    }

    private void initView() {
        list = new ArrayList<>();
        backbtn = (ImageView) findViewById(R.id.back_btn);
        examine1 = (TextView) findViewById(R.id.examine1);
        examine2 = (TextView) findViewById(R.id.examine2);
        examine3 = (TextView) findViewById(R.id.examine3);
        examine_line1 = findViewById(R.id.examine_line1);
        examine_line3 = findViewById(R.id.line3);
        examine_line2 = findViewById(R.id.line2);
        listView = (ListView) findViewById(R.id.listview);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
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
                    product_brand = 14;
                    getString();
                    examine_line1.setVisibility(View.VISIBLE);
                    examine_line2.setVisibility(View.INVISIBLE);
                    examine_line3.setVisibility(View.INVISIBLE);
                    examine1.setTextColor(getResources().getColor(R.color.black));
                    examine2.setTextColor(getResources().getColor(R.color.blue));
                    examine3.setTextColor(getResources().getColor(R.color.blue));
                    break;
                case R.id.examine2:
                    product_brand = 15;
                    getString();
                    examine_line1.setVisibility(View.INVISIBLE);
                    examine_line2.setVisibility(View.VISIBLE);
                    examine_line3.setVisibility(View.INVISIBLE);
                    examine1.setTextColor(getResources().getColor(R.color.blue));
                    examine2.setTextColor(getResources().getColor(R.color.black));
                    examine3.setTextColor(getResources().getColor(R.color.blue));
                    break;
                case R.id.examine3:
                    product_brand = 16;
                    getString();
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

    private String gsid ;
    private int product_brand =14 ;
    private String cid;
    PressAdater pressAdater;
    public void getString(){
        MainApi.getInstance(this).getNewsApi(gsid,product_brand,new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                swipeRefreshLayout.setRefreshing(false);//刷新完成
                if(type == Constants.TYPE_SUCCESS){
                    List<ModelPress.ResultBean> resultBeanList = GsonUtil.fromJsonList(new Gson(),
                            result,ModelPress.ResultBean.class);
                    list.clear();
                    list.addAll(resultBeanList);

                    pressAdater = new PressAdater(list,PressActivity.this);
                    listView.setAdapter(pressAdater);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            cid = list.get(position).getId();
                            Log.i("cid","+++++"+cid);
                            Intent intent = new Intent(PressActivity.this,IntoPressActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("cid",cid);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                }else BaseApi.showErrMsg(PressActivity.this,result);
            }
        });
    }

    @Override
    public void onRefresh() {
        getString();
    }
}
