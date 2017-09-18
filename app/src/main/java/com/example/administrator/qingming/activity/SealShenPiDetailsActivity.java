package com.example.administrator.qingming.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.adapter.SealShenPiDetailsAdapter;
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

/**
 * Created by Administrator on 2017/9/13 0013.
 */

public class SealShenPiDetailsActivity extends Activity {
    private ImageView back_btn;
    private RecyclerView recycle;
    private String ah_number, ay, dsr, dfdsr, wtr;
    private TextView one, two, three, five, fore,filename;
    private List<ModelSealApplyFor.ResultBean> list;
    private SealShenPiDetailsAdapter sealdetail;
    private  String fileid;//点击的文件id
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seal_shenpi_details);

        SharedPreferences sharedPreferences = getSharedPreferences("qinmin", Context.MODE_PRIVATE);
        userid = sharedPreferences.getString("id","");

        initView();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            ah_number = bundle.getString("ah_number", "");
            ay = bundle.getString("ay", "");
            dsr = bundle.getString("dsr", "");
            dfdsr = bundle.getString("dfdsr", "");
            wtr = bundle.getString("wtr", "");
            id = bundle.getString("id", "");

            one.setText(ay);
            two.setText(ah_number);
            three.setText(wtr);
            fore.setText(dfdsr);
            five.setText(dsr);
        }

        detail();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置垂直方向
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        // 设置布局管理器
        recycle.setLayoutManager(layoutManager);
    }

    private void initView() {
        loadingDialog = new LoadingDialog(this);
        filename = (TextView) findViewById(R.id.filename);
        list = new ArrayList<>();
        back_btn = (ImageView) findViewById(R.id.back_btn);
        recycle = (RecyclerView) findViewById(R.id.recycle);
        one = (TextView) findViewById(R.id.one);
        two = (TextView) findViewById(R.id.two);
        three = (TextView) findViewById(R.id.three);
        five = (TextView) findViewById(R.id.five);
        fore = (TextView) findViewById(R.id.fore);

        sealdetail = new SealShenPiDetailsAdapter(list,SealShenPiDetailsActivity.this);
        recycle.setAdapter(sealdetail);
        sealdetail.setOnItemClickListener(new SealShenPiDetailsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                switch (view.getId()){
                    case R.id.seal:
                        fileid = list.get(i).getId();
                        Log.e("=====",""+fileid);
                        xzdz = list.get(i).getXzdz();
                        zhpng();
                        break;
                    case R.id.pass:
                        seal_state = "2";
                        insertsealfileid();
                        break;
                    case R.id.unpass:
                        seal_state = "3";
                        insertsealfileid();
                        break;
                }
            }
        });
    }


    String id;
    private void detail() {
        MainApi.getInstance(this).getinsertsealApi(id, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if (type == Constants.TYPE_SUCCESS) {
                    List<ModelSealApplyFor.ResultBean> resultbean = GsonUtil.fromJsonList(new Gson(),result,
                            ModelSealApplyFor.ResultBean.class);
                    list.clear();
                    list.addAll(resultbean);

                    sealdetail.notifyDataSetChanged();
                } else BaseApi.showErrMsg(SealShenPiDetailsActivity.this, result);
            }
        });
    }

    /**
     * 修改seal表中的files_id
     */
    String seal_state;
    private void insertsealfileid(){
        MainApi.getInstance(this).insertsealstateApi(id,seal_state, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type == Constants.TYPE_SUCCESS){
                    detail();
                }else BaseApi.showErrMsg(SealShenPiDetailsActivity.this,result);
            }
        });
    }

    String xzdz;
    String userid;
    private void zhpng(){
        loadingDialog.setLoadingContent("正在转换成png格式...");
        loadingDialog.show();
        MainApi.getInstance(this).zhpngApi(xzdz,fileid,id,userid ,new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loadingDialog.dismiss();
                if(type == Constants.TYPE_SUCCESS){
//                    fileid = list.get(0).getId();
                    Intent intent = new Intent(SealShenPiDetailsActivity.this,SealApplyForDetailsPngListActivity.class);
                    intent.putExtra("index",2);
                    intent.putExtra("id",fileid);
                    Log.e("==========",""+fileid);
                    startActivity(intent);
                }else BaseApi.showErrMsg(SealShenPiDetailsActivity.this,result);
            }
        });
    }
}
