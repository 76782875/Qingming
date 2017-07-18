package com.example.administrator.qingming.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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

import java.util.Calendar;

/**
 * Created by Administrator on 2017/7/5 0005.
 */

public class ZhenChaActivity extends Activity {
    private static final int DATE_DIALOG = 0;
    private static final int DATE_DIALOY = 1;
    private static final int DATE_DIALOA = 2;
    private static final int DATE_DIALOB= 3;
    private static final int DATE_DIALOC = 4;
    private static final int DATE_DIALOD = 5;
    private static final int DATE_DIALOE= 6;
    private static final int DATE_DIALOF = 7;
    LoadingDialog loadingDialog;
    int mYear,mMonth,mDay;
    private String ah_number,wtr,dfdsr,ay;
    private TextView one,two,three,fore,name;
    private ImageView backbtn;
    private Button submit,xuigaibtn,delbtn;
    //侦查机关页面
    private TextView bananjiguan,xingju_time,daibu_time,zhencha_start_time,zhencha_finish_time,buchong_start_time,
            buchong_finish_time,two_buchong_start,two_buchong_finish;
    private EditText zhenchaanhao,bumeng,chengbanren,phone_num,edit_log;
    private LinearLayout aa;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhencha);

        SharedPreferences sharedPreferences = getSharedPreferences("qinmin", Context.MODE_PRIVATE);
        id = sharedPreferences.getString("id","");
        cid = sharedPreferences.getString("cid","");

        initView();
        Bundle bundle = getIntent().getExtras();
        glid = bundle.getString("id","");
        ah_number = bundle.getString("ah_number","");
        wtr = bundle.getString("wtr","");
        dfdsr = bundle.getString("dfdsr","");
        ay = bundle.getString("ay","");
        int zid = bundle.getInt("zid");


        if(zid == 1){
            submit.setVisibility(View.VISIBLE);
            submit.setOnClickListener(onClickListener);
        }else if(zid == 2){
            aid = bundle.getString("aid","");
            Log.e("aid-------->",""+aid);
            bananjiguan.setText(bundle.getString("bajg",""));
            edit_log.setText(bundle.getString("bzsm",""));
            zhenchaanhao.setText(bundle.getString("zcah",""));
            bumeng.setText(bundle.getString("bm",""));
            chengbanren.setText(bundle.getString("cbr",""));
            phone_num.setText(bundle.getString("tel",""));
            xingju_time.setText(bundle.getString("xjsj",""));
            daibu_time.setText(bundle.getString("dbsj",""));
            zhencha_start_time.setText(bundle.getString("zcqx_star",""));
            zhencha_finish_time.setText(bundle.getString("zcqx_end",""));
            buchong_start_time.setText(bundle.getString("bczcqx_star",""));
            buchong_finish_time.setText(bundle.getString("bczcqx_end",""));
            two_buchong_start.setText(bundle.getString("ecbczc_star",""));
            two_buchong_finish.setText(bundle.getString("ecbczc_end",""));
            aa.setVisibility(View.VISIBLE);
            submit.setVisibility(View.GONE);
            xuigaibtn.setOnClickListener(onClickListener);
            delbtn.setOnClickListener(onClickListener);
        }

        one.setText(ay);
        two.setText(ah_number);
        three.setText(wtr);
        fore.setText(dfdsr);

        //获取日期
        final Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);

        create_date = mYear+"-"+mMonth+"-"+mDay;
        update_date = mYear+"-"+mMonth+"-"+mDay;
    }

    private void initView() {
        loadingDialog = new LoadingDialog(this);
        aa = (LinearLayout) findViewById(R.id.aaa);
        one = (TextView) findViewById(R.id.one);
        two = (TextView) findViewById(R.id.two);
        three = (TextView) findViewById(R.id.three);
        fore = (TextView) findViewById(R.id.fore);
        submit = (Button) findViewById(R.id.submit_btn);
        xuigaibtn = (Button) findViewById(R.id.xuigai_btn);
        delbtn = (Button) findViewById(R.id.del_btn);
        backbtn = (ImageView) findViewById(R.id.back_btn);
        bananjiguan = (TextView) findViewById(R.id.bananjiguan);
        xingju_time = (TextView) findViewById(R.id.xingju_time);
        zhencha_start_time = (TextView) findViewById(R.id.zhencha_start_time);
        zhencha_finish_time = (TextView) findViewById(R.id.zhencha_finish_time);
        buchong_start_time = (TextView) findViewById(R.id.buchong_start_time);
        buchong_finish_time = (TextView) findViewById(R.id.buchong_finish_time);
        two_buchong_finish = (TextView) findViewById(R.id.two_buchong_finish);
        two_buchong_start = (TextView) findViewById(R.id.two_buchong_start);
        zhenchaanhao = (EditText) findViewById(R.id.zhenchaanhao);
        bumeng = (EditText) findViewById(R.id.bumeng);
        chengbanren = (EditText) findViewById(R.id.chengbanren);
        phone_num = (EditText) findViewById(R.id.phone_num);
        daibu_time = (TextView) findViewById(R.id.daibu_time);
        edit_log = (EditText) findViewById(R.id.edit_log);

        bananjiguan.setOnClickListener(onClickListener);
        xingju_time.setOnClickListener(onClickListener);
        daibu_time.setOnClickListener(onClickListener);
        zhencha_start_time.setOnClickListener(onClickListener);
        zhencha_finish_time.setOnClickListener(onClickListener);
        buchong_start_time.setOnClickListener(onClickListener);
        buchong_finish_time.setOnClickListener(onClickListener);
        two_buchong_start.setOnClickListener(onClickListener);
        two_buchong_finish.setOnClickListener(onClickListener);

        backbtn.setOnClickListener(onClickListener);

        zhenchaanhao.addTextChangedListener(new newtextWatcher(zhenchaanhao));
        bumeng.addTextChangedListener(new newtextWatcher(bumeng));
        chengbanren.addTextChangedListener(new newtextWatcher(chengbanren));
        phone_num.addTextChangedListener(new newtextWatcher(phone_num));
        edit_log.addTextChangedListener(new newtextWatcher(edit_log));
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back_btn:
                    finish();
                    break;
                case R.id.submit_btn:
                    if(!TextUtils.isEmpty(zhenchaanhao.getText())){
                        if(!TextUtils.isEmpty(bumeng.getText())){
                            if(!TextUtils.isEmpty(chengbanren.getText())){
                                if(!TextUtils.isEmpty(phone_num.getText())){
                                    bajg = bananjiguan.getText().toString();
                                    zcah = zhenchaanhao.getText().toString();
                                    bm = bumeng.getText().toString();
                                    cbr = chengbanren.getText().toString();
                                    tel = phone_num.getText().toString();
                                    bz = edit_log.getText().toString();
                                    xjsj = xingju_time.getText().toString();
                                    dbsj = daibu_time.getText().toString();
                                    zcqx_star= zhencha_start_time.getText().toString();
                                    zcqx_end= zhencha_finish_time.getText().toString();
                                    bczcqx_star= buchong_start_time.getText().toString();
                                    bczcqx_end= buchong_finish_time.getText().toString();
                                    ecbczc_star= two_buchong_start.getText().toString();
                                    ecbczc_end= two_buchong_finish.getText().toString();
                                    Log.e("===========>",""+xingju_time.getText().toString().equals("请选择刑拘时间"));
                                    if(xingju_time.getText().toString().equals("请选择刑拘时间") &&
                                            daibu_time.getText().toString().equals("请选择逮捕时间") &&
                                            zhencha_start_time.getText().toString().equals("起始时间") &&
                                            zhencha_finish_time.getText().toString().equals("截止时间") &&
                                            buchong_start_time.getText().toString().equals("起始时间") &&
                                            buchong_finish_time.getText().toString().equals("截止时间") &&
                                            two_buchong_start.getText().toString().equals("起始时间") &&
                                            two_buchong_finish.getText().toString().equals("截止时间")
                                            ){
                                        postzhencha1();
                                    }else if(!xingju_time.getText().toString().equals("请选择刑拘时间") &&
                                            daibu_time.getText().toString().equals("请选择逮捕时间") &&
                                            zhencha_start_time.getText().toString().equals("起始时间") &&
                                            zhencha_finish_time.getText().toString().equals("截止时间") &&
                                            buchong_start_time.getText().toString().equals("起始时间") &&
                                            buchong_finish_time.getText().toString().equals("截止时间") &&
                                            two_buchong_start.getText().toString().equals("起始时间") &&
                                            two_buchong_finish.getText().toString().equals("截止时间")
                                            ){
                                        postzhencha2();
                                    }else if(!xingju_time.getText().toString().equals("请选择刑拘时间") &&
                                            !daibu_time.getText().toString().equals("请选择逮捕时间") &&
                                            zhencha_start_time.getText().toString().equals("起始时间") &&
                                            zhencha_finish_time.getText().toString().equals("截止时间") &&
                                            buchong_start_time.getText().toString().equals("起始时间") &&
                                            buchong_finish_time.getText().toString().equals("截止时间") &&
                                            two_buchong_start.getText().toString().equals("起始时间") &&
                                            two_buchong_finish.getText().toString().equals("截止时间")
                                            ){
                                        postzhencha3();
                                    }else if(!xingju_time.getText().toString().equals("请选择刑拘时间") &&
                                            !daibu_time.getText().toString().equals("请选择逮捕时间") &&
                                            !zhencha_start_time.getText().toString().equals("起始时间") &&
                                            !zhencha_finish_time.getText().toString().equals("截止时间") &&
                                            buchong_start_time.getText().toString().equals("起始时间") &&
                                            buchong_finish_time.getText().toString().equals("截止时间") &&
                                            two_buchong_start.getText().toString().equals("起始时间") &&
                                            two_buchong_finish.getText().toString().equals("截止时间")
                                            ){
                                        postzhencha4();
                                    }else if(!xingju_time.getText().toString().equals("请选择刑拘时间") &&
                                            !daibu_time.getText().toString().equals("请选择逮捕时间") &&
                                            !zhencha_start_time.getText().toString().equals("起始时间") &&
                                            !zhencha_finish_time.getText().toString().equals("截止时间") &&
                                            !buchong_start_time.getText().toString().equals("起始时间") &&
                                            !buchong_finish_time.getText().toString().equals("截止时间") &&
                                            two_buchong_start.getText().toString().equals("起始时间") &&
                                            two_buchong_finish.getText().toString().equals("截止时间")
                                            ){
                                        postzhencha5();
                                    }else if(!xingju_time.getText().toString().equals("请选择刑拘时间") &&
                                            !daibu_time.getText().toString().equals("请选择逮捕时间") &&
                                            !zhencha_start_time.getText().toString().equals("起始时间") &&
                                            !zhencha_finish_time.getText().toString().equals("截止时间") &&
                                            !buchong_start_time.getText().toString().equals("起始时间") &&
                                            !buchong_finish_time.getText().toString().equals("截止时间") &&
                                            !two_buchong_start.getText().toString().equals("起始时间") &&
                                            !two_buchong_finish.getText().toString().equals("截止时间")
                                            ){
                                        postzhencha6();
                                    }

                                }else {
                                    Toast.makeText(ZhenChaActivity.this,"联系电话不能为空",Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(ZhenChaActivity.this,"承办人不能为空",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(ZhenChaActivity.this,"侦查案号不能为空",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(ZhenChaActivity.this,"侦查案号不能为空",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.bananjiguan:
                    title = "选择日志类别";
                    showDialog();
                    final String[] ba = {"公安局","反贪局","看守所"};
                    builder.setItems(ba, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            bananjiguan.setText(ba[which]);
                        }
                    }).show();
                    break;
                case R.id.xingju_time:
                    showDialog(DATE_DIALOG);
                    break;
                case R.id.daibu_time:
                    showDialog(DATE_DIALOY);
                    break;
                case R.id.zhencha_start_time:
                    showDialog(DATE_DIALOA);
                    break;
                case R.id.zhencha_finish_time:
                    showDialog(DATE_DIALOB);
                    break;
                case R.id.buchong_start_time:
                    showDialog(DATE_DIALOC);
                    break;
                case R.id.buchong_finish_time:
                    showDialog(DATE_DIALOD);
                    break;
                case R.id.two_buchong_start:
                    showDialog(DATE_DIALOE);
                    break;
                case R.id.two_buchong_finish:
                    showDialog(DATE_DIALOF);
                    break;
                case R.id.xuigai_btn:
                    if(!TextUtils.isEmpty(zhenchaanhao.getText())){
                        if(!TextUtils.isEmpty(bumeng.getText())){
                            if(!TextUtils.isEmpty(chengbanren.getText())){
                                if(!TextUtils.isEmpty(phone_num.getText())){
                                    bajg = bananjiguan.getText().toString();
                                    zcah = zhenchaanhao.getText().toString();
                                    bm = bumeng.getText().toString();
                                    cbr = chengbanren.getText().toString();
                                    tel = phone_num.getText().toString();
                                    bz = edit_log.getText().toString();
                                    xjsj = xingju_time.getText().toString();
                                    dbsj = daibu_time.getText().toString();
                                    zcqx_star= zhencha_start_time.getText().toString();
                                    zcqx_end= zhencha_finish_time.getText().toString();
                                    bczcqx_star= buchong_start_time.getText().toString();
                                    bczcqx_end= buchong_finish_time.getText().toString();
                                    ecbczc_star= two_buchong_start.getText().toString();
                                    ecbczc_end= two_buchong_finish.getText().toString();
                                    Log.e("===========>",""+xingju_time.getText().toString().equals("请选择刑拘时间"));
                                    if(xingju_time.getText().toString().equals("请选择刑拘时间") &&
                                            daibu_time.getText().toString().equals("请选择逮捕时间") &&
                                            zhencha_start_time.getText().toString().equals("起始时间") &&
                                            zhencha_finish_time.getText().toString().equals("截止时间") &&
                                            buchong_start_time.getText().toString().equals("起始时间") &&
                                            buchong_finish_time.getText().toString().equals("截止时间") &&
                                            two_buchong_start.getText().toString().equals("起始时间") &&
                                            two_buchong_finish.getText().toString().equals("截止时间")
                                            ){
                                        postzhencha8();
                                    }else if(!xingju_time.getText().toString().equals("请选择刑拘时间") &&
                                            daibu_time.getText().toString().equals("请选择逮捕时间") &&
                                            zhencha_start_time.getText().toString().equals("起始时间") &&
                                            zhencha_finish_time.getText().toString().equals("截止时间") &&
                                            buchong_start_time.getText().toString().equals("起始时间") &&
                                            buchong_finish_time.getText().toString().equals("截止时间") &&
                                            two_buchong_start.getText().toString().equals("起始时间") &&
                                            two_buchong_finish.getText().toString().equals("截止时间")
                                            ){
                                        postzhencha9();
                                    }else if(!xingju_time.getText().toString().equals("请选择刑拘时间") &&
                                            !daibu_time.getText().toString().equals("请选择逮捕时间") &&
                                            zhencha_start_time.getText().toString().equals("起始时间") &&
                                            zhencha_finish_time.getText().toString().equals("截止时间") &&
                                            buchong_start_time.getText().toString().equals("起始时间") &&
                                            buchong_finish_time.getText().toString().equals("截止时间") &&
                                            two_buchong_start.getText().toString().equals("起始时间") &&
                                            two_buchong_finish.getText().toString().equals("截止时间")
                                            ){
                                        postzhencha10();
                                    }else if(!xingju_time.getText().toString().equals("请选择刑拘时间") &&
                                            !daibu_time.getText().toString().equals("请选择逮捕时间") &&
                                            !zhencha_start_time.getText().toString().equals("起始时间") &&
                                            !zhencha_finish_time.getText().toString().equals("截止时间") &&
                                            buchong_start_time.getText().toString().equals("起始时间") &&
                                            buchong_finish_time.getText().toString().equals("截止时间") &&
                                            two_buchong_start.getText().toString().equals("起始时间") &&
                                            two_buchong_finish.getText().toString().equals("截止时间")
                                            ){
                                        postzhencha11();
                                    }else if(!xingju_time.getText().toString().equals("请选择刑拘时间") &&
                                            !daibu_time.getText().toString().equals("请选择逮捕时间") &&
                                            !zhencha_start_time.getText().toString().equals("起始时间") &&
                                            !zhencha_finish_time.getText().toString().equals("截止时间") &&
                                            !buchong_start_time.getText().toString().equals("起始时间") &&
                                            !buchong_finish_time.getText().toString().equals("截止时间") &&
                                            two_buchong_start.getText().toString().equals("起始时间") &&
                                            two_buchong_finish.getText().toString().equals("截止时间")
                                            ){
                                        postzhencha12();
                                    }else if(!xingju_time.getText().toString().equals("请选择刑拘时间") &&
                                            !daibu_time.getText().toString().equals("请选择逮捕时间") &&
                                            !zhencha_start_time.getText().toString().equals("起始时间") &&
                                            !zhencha_finish_time.getText().toString().equals("截止时间") &&
                                            !buchong_start_time.getText().toString().equals("起始时间") &&
                                            !buchong_finish_time.getText().toString().equals("截止时间") &&
                                            !two_buchong_start.getText().toString().equals("起始时间") &&
                                            !two_buchong_finish.getText().toString().equals("截止时间")
                                            ){
                                        postzhencha13();
                                    }
                                }else {
                                    Toast.makeText(ZhenChaActivity.this,"联系电话不能为空",Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(ZhenChaActivity.this,"承办人不能为空",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(ZhenChaActivity.this,"侦查案号不能为空",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(ZhenChaActivity.this,"侦查案号不能为空",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.del_btn:
                    postzhencha7();
                    break;
            }
        }
    };

    /**
     *添加侦查机关
     */
    String create_date;
    String id;
    String cid;
    String glid;
    String bajg;
    String zcah;
    String bm;
    String cbr;
    String tel;
    String xjsj ;
    String dbsj ;
    String zcqx_star;
    String zcqx_end;
    String bczcqx_star;
    String bczcqx_end;
    String ecbczc_star;
    String ecbczc_end;
    String bz;
    private void postzhencha1(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("上传中...");
        MainApi.getInstance(this).postaddzhenchaApi1(id,cid,glid,bajg,bz,zcah,bm,cbr,tel, create_date,
                new GetResultCallBack() {
                    @Override
                    public void getResult(String result, int type) {
                        if(type == Constants.TYPE_SUCCESS){
                            loadingDialog.dismiss();
                            Toast.makeText(ZhenChaActivity.this,"上传成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }else BaseApi.showErrMsg(ZhenChaActivity.this,result);
                    }
                });
    }
    private void postzhencha2(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("上传中...");
        MainApi.getInstance(this).postaddzhenchaApi2(id,cid,glid,bajg,bz,zcah,bm,cbr,tel,xjsj,
                create_date,new GetResultCallBack() {
                    @Override
                    public void getResult(String result, int type) {
                        if(type == Constants.TYPE_SUCCESS){
                            loadingDialog.dismiss();
                            Toast.makeText(ZhenChaActivity.this,"上传成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }else BaseApi.showErrMsg(ZhenChaActivity.this,result);
                    }
                });
    }
    private void postzhencha3(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("上传中...");
        MainApi.getInstance(this).postaddzhenchaApi3(id,cid,glid,bajg,bz,zcah,bm,cbr,tel,xjsj, dbsj,
                create_date,new GetResultCallBack() {
                    @Override
                    public void getResult(String result, int type) {
                        if(type == Constants.TYPE_SUCCESS){
                            loadingDialog.dismiss();
                            Toast.makeText(ZhenChaActivity.this,"上传成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }else BaseApi.showErrMsg(ZhenChaActivity.this,result);
                    }
                });
    }
    private void postzhencha4(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("上传中...");
        MainApi.getInstance(this).postaddzhenchaApi4(id,cid,glid,bajg,bz,zcah,bm,cbr,tel,xjsj, dbsj,
                zcqx_star,zcqx_end,create_date,new GetResultCallBack() {
                    @Override
                    public void getResult(String result, int type) {
                        if(type == Constants.TYPE_SUCCESS){
                            loadingDialog.dismiss();
                            Toast.makeText(ZhenChaActivity.this,"上传成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }else BaseApi.showErrMsg(ZhenChaActivity.this,result);
                    }
                });
    }
    private void postzhencha5(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("上传中...");
        MainApi.getInstance(this).postaddzhenchaApi5(id,cid,glid,bajg,bz,zcah,bm,cbr,tel,xjsj, dbsj,
                zcqx_star,zcqx_end,bczcqx_star,bczcqx_end,create_date,new GetResultCallBack() {
                    @Override
                    public void getResult(String result, int type) {
                        if(type == Constants.TYPE_SUCCESS){
                            loadingDialog.dismiss();
                            Toast.makeText(ZhenChaActivity.this,"上传成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }else BaseApi.showErrMsg(ZhenChaActivity.this,result);
                    }
                });
    }
    private void postzhencha6(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("上传中...");
        MainApi.getInstance(this).postaddzhenchaApi6(id,cid,glid,bajg,bz,zcah,bm,cbr,tel,xjsj, dbsj,
                zcqx_star,zcqx_end,bczcqx_star,bczcqx_end,ecbczc_star,ecbczc_end,create_date,new GetResultCallBack() {
                    @Override
                    public void getResult(String result, int type) {
                        if(type == Constants.TYPE_SUCCESS){
                            loadingDialog.dismiss();
                            Toast.makeText(ZhenChaActivity.this,"上传成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }else BaseApi.showErrMsg(ZhenChaActivity.this,result);
                    }
                });
    }

    /**
     * 删除
     */
    String aid;
    private void postzhencha7(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("删除中...");
        MainApi.getInstance(this).getdelzhenchaApi(aid,new GetResultCallBack() {
                    @Override
                    public void getResult(String result, int type) {
                        if(type == Constants.TYPE_SUCCESS){
                            loadingDialog.dismiss();
                            Toast.makeText(ZhenChaActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }else BaseApi.showErrMsg(ZhenChaActivity.this,result);
                    }
                });
    }

    /**
     * 修改
     */
    String update_date;
    private void postzhencha8(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("修改中...");
        MainApi.getInstance(this).getxgzhenchaApi5(aid,bajg,bz,zcah,bm,cbr,tel,update_date,new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type == Constants.TYPE_SUCCESS){
                    loadingDialog.dismiss();
                    Toast.makeText(ZhenChaActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                    finish();
                }else BaseApi.showErrMsg(ZhenChaActivity.this,result);
            }
        });
    }

    private void postzhencha9(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("修改中...");
        MainApi.getInstance(this).getxgzhenchaApi4(aid,bajg,bz,zcah,bm,cbr,tel,xjsj, update_date,new GetResultCallBack() {
                    @Override
                    public void getResult(String result, int type) {
                        if(type == Constants.TYPE_SUCCESS){
                            loadingDialog.dismiss();
                            Toast.makeText(ZhenChaActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }else BaseApi.showErrMsg(ZhenChaActivity.this,result);
                    }
                });
    }

    private void postzhencha10(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("修改中...");
        MainApi.getInstance(this).getxgzhenchaApi3(aid,bajg,bz,zcah,bm,cbr,tel,xjsj, dbsj, update_date,new GetResultCallBack() {
                    @Override
                    public void getResult(String result, int type) {
                        if(type == Constants.TYPE_SUCCESS){
                            loadingDialog.dismiss();
                            Toast.makeText(ZhenChaActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }else BaseApi.showErrMsg(ZhenChaActivity.this,result);
                    }
                });
    }

    private void postzhencha11(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("修改中...");
        MainApi.getInstance(this).getxgzhenchaApi2(aid,bajg,bz,zcah,bm,cbr,tel,xjsj, dbsj,
                zcqx_star,zcqx_end,update_date,new GetResultCallBack() {
                    @Override
                    public void getResult(String result, int type) {
                        if(type == Constants.TYPE_SUCCESS){
                            loadingDialog.dismiss();
                            Toast.makeText(ZhenChaActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }else BaseApi.showErrMsg(ZhenChaActivity.this,result);
                    }
                });
    }

    private void postzhencha12(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("修改中...");
        MainApi.getInstance(this).getxgzhenchaApi1(aid,bajg,bz,zcah,bm,cbr,tel,xjsj, dbsj,
                zcqx_star,zcqx_end,bczcqx_star,bczcqx_end,update_date,new GetResultCallBack() {
                    @Override
                    public void getResult(String result, int type) {
                        if(type == Constants.TYPE_SUCCESS){
                            loadingDialog.dismiss();
                            Toast.makeText(ZhenChaActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }else BaseApi.showErrMsg(ZhenChaActivity.this,result);
                    }
                });
    }

    private void postzhencha13(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("修改中...");
        MainApi.getInstance(this).getxgzhenchaApi(aid,bajg,bz,zcah,bm,cbr,tel,xjsj, dbsj,
                zcqx_star,zcqx_end,bczcqx_star,bczcqx_end,ecbczc_star,ecbczc_end,update_date,new GetResultCallBack() {
                    @Override
                    public void getResult(String result, int type) {
                        if(type == Constants.TYPE_SUCCESS){
                            loadingDialog.dismiss();
                            Toast.makeText(ZhenChaActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }else BaseApi.showErrMsg(ZhenChaActivity.this,result);
                    }
                });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG:
                return new DatePickerDialog(this,onDateSetListener, mYear, mMonth, mDay);
            case DATE_DIALOY:
                return new DatePickerDialog(this,monDateSetListener, mYear, mMonth, mDay);
            case DATE_DIALOA:
                return new DatePickerDialog(this,monDateSetListener1, mYear, mMonth, mDay);
            case DATE_DIALOB:
                return new DatePickerDialog(this,monDateSetListener2, mYear, mMonth, mDay);
            case DATE_DIALOC:
                return new DatePickerDialog(this,monDateSetListener3, mYear, mMonth, mDay);
            case DATE_DIALOD:
                return new DatePickerDialog(this,monDateSetListener4, mYear, mMonth, mDay);
            case DATE_DIALOE:
                return new DatePickerDialog(this,monDateSetListener5, mYear, mMonth, mDay);
            case DATE_DIALOF:
                return new DatePickerDialog(this,monDateSetListener6, mYear, mMonth, mDay);

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

    DatePickerDialog.OnDateSetListener monDateSetListener1 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            zhencha_start_time.setText(new StringBuffer().append(mYear).append("-").append(mMonth + 1).append("-").
                    append(mDay).append(" "));
            zhencha_start_time.setTextColor(getResources().getColor(R.color.black));
        }
    };

    DatePickerDialog.OnDateSetListener monDateSetListener2 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            zhencha_finish_time.setText(new StringBuffer().append(mYear).append("-").append(mMonth + 1).append("-").
                    append(mDay).append(" "));
            zhencha_finish_time.setTextColor(getResources().getColor(R.color.black));
        }
    };

    DatePickerDialog.OnDateSetListener monDateSetListener3 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            buchong_start_time.setText(new StringBuffer().append(mYear).append("-").append(mMonth + 1).append("-").
                    append(mDay).append(" "));
            buchong_start_time.setTextColor(getResources().getColor(R.color.black));
        }
    };

    DatePickerDialog.OnDateSetListener monDateSetListener4 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            buchong_finish_time.setText(new StringBuffer().append(mYear).append("-").append(mMonth + 1).append("-").
                    append(mDay).append(" "));
            buchong_finish_time.setTextColor(getResources().getColor(R.color.black));
        }
    };

    DatePickerDialog.OnDateSetListener monDateSetListener5 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            two_buchong_start.setText(new StringBuffer().append(mYear).append("-").append(mMonth + 1).append("-").
                    append(mDay).append(" "));
            two_buchong_start.setTextColor(getResources().getColor(R.color.black));
        }
    };

    DatePickerDialog.OnDateSetListener monDateSetListener6 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            two_buchong_finish.setText(new StringBuffer().append(mYear).append("-").append(mMonth + 1).append("-").
                    append(mDay).append(" "));
            two_buchong_finish.setTextColor(getResources().getColor(R.color.black));
        }
    };

    /**
     * 设置日期 利用StringBuffer追加
     */
    public void display() {
        xingju_time.setText(new StringBuffer().append(mYear).append("-").append(mMonth + 1).append("-").
                append(mDay).append(" "));
        xingju_time.setTextColor(getResources().getColor(R.color.black));
    }
    public void finplay() {
        daibu_time.setText(new StringBuffer().append(mYear).append("-").append(mMonth + 1).append("-").
                append(mDay).append(" "));
        daibu_time.setTextColor(getResources().getColor(R.color.black));
    }

    String title;
    AlertDialog.Builder builder;
    private void showDialog() {
        builder= new AlertDialog.Builder(ZhenChaActivity.this);
        builder.setTitle(title);
    }

    class  newtextWatcher implements TextWatcher {
        private EditText edit;
        public newtextWatcher(EditText editText){
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
            Log.i("",""+msg4);
        }
    }

}
