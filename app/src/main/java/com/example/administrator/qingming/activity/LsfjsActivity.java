package com.example.administrator.qingming.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.api.BaseApi;
import com.example.administrator.qingming.api.MainApi;
import com.example.administrator.qingming.dialog.LoadingDialog;
import com.example.administrator.qingming.interfaces.GetResultCallBack;
import com.example.administrator.qingming.model.Constants;
import com.example.administrator.qingming.model.ModelLsf;
import com.example.administrator.qingming.model.ModelPjgz;
import com.example.administrator.qingming.model.ModelScdj;
import com.example.administrator.qingming.qinminutils.GsonUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/21 0021.
 */

public class LsfjsActivity extends Activity {
    private ImageView back_btn;
    private Button add;
    List<ModelLsf.ResultBean> list2;
    private LoadingDialog loadingDialog;
    private TextView ajlx,area1,lsf_start,lsf_finish;
    private EditText ssbdje;
    private LinearLayout lvshifei;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lsf_jisuan);

        initView();
    }

    private void initView() {
        loadingDialog = new LoadingDialog(this);
        list2 = new ArrayList<>();
        back_btn = (ImageView) findViewById(R.id.back_btn);
        add = (Button) findViewById(R.id.add);
        ajlx= (TextView) findViewById(R.id.ajlx);
        area1= (TextView) findViewById(R.id.area1);
        lsf_start= (TextView) findViewById(R.id.lsf_start);
        lsf_finish= (TextView) findViewById(R.id.lsf_finish);
        ssbdje = (EditText) findViewById(R.id.ssbdje);
        lvshifei = (LinearLayout) findViewById(R.id.lvshifei);

        add.setOnClickListener(onclicklisten);
        back_btn.setOnClickListener(onclicklisten);
        area1.setOnClickListener(onclicklisten);
        ajlx.setOnClickListener(onclicklisten);
        ssbdje.addTextChangedListener(new newtextwatch(ssbdje));
    }

    View.OnClickListener onclicklisten = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.add:
                    if (!TextUtils.isEmpty(ssbdje.getText())) {
                        if (ajlx.getText().toString().equals("民事")) {
                            fyflag = 8;
                        } else if (ajlx.getText().toString().equals("刑事侦查阶段")) {
                            fyflag = 9;
                        } else if (ajlx.getText().toString().equals("刑事起诉阶段")) {
                            fyflag = 10;
                        } else if (ajlx.getText().toString().equals("刑事审判阶段")) {
                            fyflag = 11;
                        } else if (ajlx.getText().toString().equals("行政")) {
                            fyflag = 8;
                        }
                        max =""+ ssbdje.getText().toString();
                        getlsfHttp();
                    } else {
                        Toast.makeText(LsfjsActivity.this, "月工资不能为空", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.back_btn:
                    finish();
                    break;
                case R.id.area1:
                    showDialog();
                    final String[] str1 = {"重庆"};
                    builder.setItems(str1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            area1.setText(str1[i]);
                        }
                    });
                    builder.show();
                    break;
                case R.id.ajlx:
                    showDialog();
                    final String[] strss = {"民事","刑事侦查阶段","刑事起诉阶段","刑事审判阶段","行政"};
                    builder.setItems(strss, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            ajlx.setText(strss[i]);
                        }
                    });
                    builder.show();
                    break;
            }
        }
    };

    AlertDialog.Builder builder;
    private void showDialog(){
        builder = new AlertDialog.Builder(LsfjsActivity.this,AlertDialog.THEME_HOLO_LIGHT);
    }

    int fyflag;
    String max;
    private double fl;
    private double fwsfmin;
    private double fwsfmax;
    private double flmin;
    private double flmax;
    private void getlsfHttp(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("正在查询...");
        MainApi.getInstance(this).getlsfApi(1,fyflag,max,new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loadingDialog.dismiss();
                if(type == Constants.TYPE_SUCCESS){
                    List<ModelLsf.ResultBean> resultBean = GsonUtil.fromJsonList(new Gson(),result,ModelLsf.ResultBean.class);
                    list2.clear();
                    list2.addAll(resultBean);
                    double s = Double.parseDouble(ssbdje.getText().toString());//输入工资
                    fl = list2.get(0).getFl();
                    fwsfmin = list2.get(0).getFwsfmin();
                    fwsfmax = list2.get(0).getFwsfmax();
                    flmin = list2.get(0).getFlmin();
                    flmax = list2.get(0).getFlmax();
                    Log.e("",""+fl+fwsfmin+fwsfmax+flmin+flmax);
                    double ss = fwsfmin+ s * flmin;
                    double sss = fwsfmax+ s * flmax;
                    if (fl != 0.0) {
                        double num = s * fl;
                    }else{
                        lvshifei.setVisibility(View.VISIBLE);
                        lsf_start.setText("" + ss);
                        lsf_finish.setText("" + sss);
                    }

                }else BaseApi.showErrMsg(LsfjsActivity.this,result);
            }
        });
    }

    class newtextwatch implements TextWatcher {
        private EditText edit;

        public newtextwatch(EditText editText) {
            this.edit = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String msg4 = edit.getText().toString();
            Log.i("", "" + msg4);
        }
    }
}
