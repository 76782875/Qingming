package com.example.administrator.qingming.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.api.BaseApi;
import com.example.administrator.qingming.api.MainApi;
import com.example.administrator.qingming.dialog.LoadingDialog;
import com.example.administrator.qingming.interfaces.GetResultCallBack;
import com.example.administrator.qingming.model.Constants;
import com.example.administrator.qingming.model.ModelChangeLvSi;
import com.example.administrator.qingming.model.ModelCls;
import com.example.administrator.qingming.qinminutils.GsonUtil;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/6/6 0006.
 */

public class ChangeLvshiActivity extends Activity {
    private List<ModelChangeLvSi.ResultBean> list;
    private ArrayList<String> resultBeanList;
    private TextView counsel,numb,sarq,jssf,dlf,ls,change,xuigai;
    private LinearLayout input_case_name;
    private ImageView back_btn;
    private LoadingDialog loadingDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intobglvs);

        SharedPreferences sharedPreferences = getSharedPreferences("qinmin", Context.MODE_PRIVATE);
        cid = sharedPreferences.getString("cid", "");

        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy/MM/dd  HH:mm:ss");
        updatedate =  sDateFormat.format(new java.util.Date());

        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("cid");
        name = bundle.getString("name");
        Log.i("id===>",""+id+name);

        initView();
        getHttp();
        getclsHttp();
    }

    private void initView() {
        list = new ArrayList<>();
        resultBeanList = new ArrayList<>();
        loadingDialog = new LoadingDialog(this);
        back_btn = (ImageView) findViewById(R.id.back_btn);
        counsel = (TextView) findViewById(R.id.counsel);
        numb = (TextView) findViewById(R.id.numb);
        sarq = (TextView) findViewById(R.id.sarq);
        jssf = (TextView) findViewById(R.id.jssf);
        dlf = (TextView) findViewById(R.id.dlf);
        ls = (TextView) findViewById(R.id.ls);
        change = (TextView) findViewById(R.id.change);
        xuigai = (TextView) findViewById(R.id.xuigai);
        input_case_name = (LinearLayout) findViewById(R.id.input_case_name);

        input_case_name.setOnClickListener(onClickListener);
        xuigai.setOnClickListener(onClickListener);
        back_btn.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.xuigai:
                    postxgHttp();
                    break;
                case R.id.input_case_name:
                    showDialog();
                    break;
                case R.id.back_btn:
                    finish();
                    break;
            }
        }
    };

    String name;
    String id;
    private void getHttp(){
        MainApi.getInstance(this).getintobglvshiApi(id, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type == Constants.TYPE_SUCCESS){
                    List<ModelChangeLvSi.ResultBean> resultbean = GsonUtil.fromJsonList(new Gson(),
                            result,ModelChangeLvSi.ResultBean.class);
                    list.addAll(resultbean);
                    counsel.setText(list.get(0).getAy());
                    numb.setText(list.get(0).getAh_number());
                    sarq.setText(list.get(0).getSarq());
                    if(list.get(0).getSffs().equals("0")){
                        jssf.setText("免费");
                    }else if(list.get(0).getSffs().equals("1")){
                        jssf.setText("计件收费");
                    }else if(list.get(0).getSffs().equals("2")){
                        jssf.setText("按标的比例收费");
                    }else if(list.get(0).getSffs().equals("3")){
                        jssf.setText("风险代理费");
                    }else if(list.get(0).getSffs().equals("4")){
                        jssf.setText("固定+风险代理费");
                    }
                    dlf.setText(list.get(0).getDlf());
                    ls.setText(name);

                }else BaseApi.showErrMsg(ChangeLvshiActivity.this,result);
            }
        });
    }

    /**
     *查询律师
     */
    String cid;
    private void getclsHttp(){
        MainApi.getInstance(this).getclsApi(cid, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type == Constants.TYPE_SUCCESS){
                    List<ModelCls.ResultBean> resultBeen = GsonUtil.fromJsonList(new Gson(),result,
                            ModelCls.ResultBean.class);
                    lsname = new String[resultBeen.size()];
                    lsid = new String[resultBeen.size()];
                    for(int i=0;i<resultBeen.size();i++){
                        String name = resultBeen.get(i).getName();
                        String id = resultBeen.get(i).getId();
                        resultBeanList.add(name);
                        lsname[i] = name;
                        lsid[i] = id ;
                    }
                }else BaseApi.showErrMsg(ChangeLvshiActivity.this,result);
            }
        });
    }

    /**
     *修改律师
     */
    String createid,updatedate;
    private void postxgHttp(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("正在提交...");
        MainApi.getInstance(this).postxglsApi(id,createid,updatedate, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loadingDialog.dismiss();
                if(type == Constants.TYPE_SUCCESS){
                    Intent intent = new Intent(ChangeLvshiActivity.this,ChangeActivity.class);
                    startActivity(intent);
                }else BaseApi.showErrMsg(ChangeLvshiActivity.this,result);
            }
        });
    }

    String[] lsname ;
    String[] lsid ;
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this,AlertDialog.THEME_HOLO_LIGHT);
        builder.setTitle("--请选择--");
        builder.setItems(lsname, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                change.setText(lsname[which]);
                createid = lsid[which];
            }
        });
        builder.show();
    }

}
