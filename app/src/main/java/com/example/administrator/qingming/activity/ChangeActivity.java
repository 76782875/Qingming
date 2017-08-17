package com.example.administrator.qingming.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.adapter.ChangeAdapter;
import com.example.administrator.qingming.api.BaseApi;
import com.example.administrator.qingming.api.MainApi;
import com.example.administrator.qingming.dialog.LoadingDialog;
import com.example.administrator.qingming.interfaces.GetResultCallBack;
import com.example.administrator.qingming.model.Constants;
import com.example.administrator.qingming.model.ModelChange;
import com.example.administrator.qingming.qinminutils.GsonUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/5 0005.
 */

public class ChangeActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener{
    private SwipeRefreshLayout swipe;
    private ListView listview;
    private List<ModelChange.ResultBean> list;
    private List<ModelChange.ResultBean> list1;
    private List<ModelChange.ResultBean> list2;
    private List<ModelChange.ResultBean> list3;
    private EditText edit;
    private TextView search,choose;
    private ImageView back_btn;
    private LoadingDialog loadingDialog;
    private String gjc;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);

        SharedPreferences sharedPreferences = getSharedPreferences("qinmin", Context.MODE_PRIVATE);
        // 使用getString方法获得value，注意第2个参数是value的默认值
        company_id = sharedPreferences.getString("cid","");

        initView();
        getHttp();
    }

    private void initView() {
        loadingDialog = new LoadingDialog(this);
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        listview = (ListView) findViewById(R.id.listview);
        list = new ArrayList<>();
        edit = (EditText) findViewById(R.id.edit);
        search = (TextView) findViewById(R.id.search);
        choose = (TextView) findViewById(R.id.choose);
        back_btn = (ImageView) findViewById(R.id.back_btn);

        swipe.setOnRefreshListener(this);
        choose.setOnClickListener(onClick);
        search.setOnClickListener(onClick);
        back_btn.setOnClickListener(onClick);

        changeadapter = new ChangeAdapter(ChangeActivity.this,list);
        listview.setAdapter(changeadapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String cid = list.get(position).getId();
                String name  = list.get(position).getName();
                Log.i("cid------->",""+cid);
                Intent intent = new Intent(ChangeActivity.this,ChangeLvshiActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("cid",""+cid);
                bundle.putString("name",""+name);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                gjc = s.toString();
            }
        });
    }

    View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.choose:
                    final String[] ch ={"客户(委托人)搜索","对方当事人搜索","案号搜索"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(ChangeActivity.this);
                    builder.setItems(ch, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            choose.setText(ch[which]);
                            choose.setTextColor(getResources().getColor(R.color.black));
                        }
                    }).show();
                    break;
                case R.id.search:
                    if(choose.getText().toString().equals("客户(委托人)搜索")){
                        getSearchwtr();
                    }else if(choose.getText().toString().equals("对方当事人搜索")){
                        getSearchdfdsr();
                    }else if(choose.getText().toString().equals("案号搜索")){
                        getSearchah();
                    }
                    break;
                case R.id.back_btn:
                    finish();
                    break;
            }
        }
    };

    int index = 0;
//    private void setListData(){
//        list4.clear();
//        if (index == 0)
//            list4 .addAll(list);
//        else if(index == 1)
//            list4.addAll(list1);
//
//        mAdapter.notifyDataSetChanged();
//    }

    ChangeAdapter changeadapter;
    String company_id;
    private void getSearchwtr(){
        MainApi.getInstance(this).getbglvshicxApi(company_id,gjc, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                swipe.setRefreshing(false);
                if(type== Constants.TYPE_SUCCESS){
                    List<ModelChange.ResultBean> resultBean = GsonUtil.fromJsonList(new Gson(),
                            result,ModelChange.ResultBean.class);
                    list.clear();
                    list.addAll(resultBean);

                    changeadapter.notifyDataSetChanged();
                }else BaseApi.showErrMsg(ChangeActivity.this,result);
            }
        });
    }

    private void getSearchdfdsr(){
        MainApi.getInstance(this).getbglvshicxdApi(company_id,gjc, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                swipe.setRefreshing(false);
                if(type== Constants.TYPE_SUCCESS){
                    List<ModelChange.ResultBean> resultBean = GsonUtil.fromJsonList(new Gson(),
                            result,ModelChange.ResultBean.class);
                    list.clear();
                    list.addAll(resultBean);

                    changeadapter.notifyDataSetChanged();
                }else BaseApi.showErrMsg(ChangeActivity.this,result);
            }
        });
    }

    private void getSearchah(){
        MainApi.getInstance(this).getbglvshicxaApi(company_id,gjc, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                swipe.setRefreshing(false);
                if(type== Constants.TYPE_SUCCESS){
                    List<ModelChange.ResultBean> resultBean = GsonUtil.fromJsonList(new Gson(),
                            result,ModelChange.ResultBean.class);
                    list.clear();
                    list.addAll(resultBean);

                    changeadapter.notifyDataSetChanged();
                }else BaseApi.showErrMsg(ChangeActivity.this,result);
            }
        });
    }

    private void getHttp(){
        loadingDialog.setLoadingContent("加载中...");
        loadingDialog.show();
        MainApi.getInstance(this).getbglvshiApi(company_id, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                swipe.setRefreshing(false);
                loadingDialog.dismiss();
                if(type== Constants.TYPE_SUCCESS){
                    List<ModelChange.ResultBean> resultBean = GsonUtil.fromJsonList(new Gson(),
                            result,ModelChange.ResultBean.class);
                    list.clear();
                    list.addAll(resultBean);

                    changeadapter.notifyDataSetChanged();
                }else BaseApi.showErrMsg(ChangeActivity.this,result);
            }
        });
    }

    @Override
    public void onRefresh() {
        getHttp();
    }
}
