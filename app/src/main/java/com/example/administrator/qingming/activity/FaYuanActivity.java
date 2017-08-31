package com.example.administrator.qingming.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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
import com.example.administrator.qingming.news.casedetails.CreateWorkActivity;
import com.example.administrator.qingming.work.DateTimePickDialogUtil;

import java.util.Calendar;

/**
 * Created by Administrator on 2017/7/5 0005.
 */

public class FaYuanActivity extends Activity {
    private String initStartDateTime ; // 初始化开始时间
    LoadingDialog loadingDialog;
    int mYear,mMonth,mDay,mHour,mMinute;
    private String ah_number,wtr,dfdsr,ay,dsr;
    private TextView one,two,three,fore,name,five;
    private ImageView backbtn;
    private Button submit,xuigaibtn,delbtn;
    private LinearLayout aa;
    private String create_date,update_date;
    private EditText shenpan,zhushe_phone,faguan_phone,edit_log,mspjg,fating,faguan,sujiyuan;
    private TextView lian,kaiting,xuanpan,shangsu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fayuan);

        SharedPreferences sharedPreferences = getSharedPreferences("qinmin", Context.MODE_PRIVATE);
        id = sharedPreferences.getString("id","");
        cid = sharedPreferences.getString("cid","");

        initView();
        Bundle bundle = getIntent().getExtras();
        glid = bundle.getString("id","");
        ah_number = bundle.getString("ah_number","");
        wtr = bundle.getString("wtr","");
        dfdsr = bundle.getString("dfdsr","");
        dsr = bundle.getString("dsr","");
        ay = bundle.getString("ay","");
        int zid = bundle.getInt("zid");

        if(zid == 1){
            submit.setVisibility(View.VISIBLE);
            submit.setOnClickListener(onclicklisten);
        }else if(zid == 2){
            aid = bundle.getString("aid","");
            Log.e("aid-------->",""+aid);
            shenpan.setText(bundle.getString("spcx",""));
            lian.setText(bundle.getString("ladate",""));
            kaiting.setText(bundle.getString("ktdate",""));
            if(bundle.getString("spdate","").equals("")){
                xuanpan.setText("请选择时间");
            }else {
                xuanpan.setText(bundle.getString("spdate",""));
            }
            if(bundle.getString("ssdate","").equals("")){
                shangsu.setText("请选择时间");
            }else {
                shangsu.setText(bundle.getString("ssdate",""));
            }
            mspjg.setText(bundle.getString("spjg",""));
            fating.setText(bundle.getString("ft",""));
            faguan.setText(bundle.getString("zsfg",""));
            faguan_phone.setText(bundle.getString("zsfgtel",""));
            sujiyuan.setText(bundle.getString("sjy",""));
            zhushe_phone.setText(bundle.getString("sjytel",""));
            edit_log.setText(bundle.getString("bzsm2",""));

            aa.setVisibility(View.VISIBLE);
            submit.setVisibility(View.GONE);
            xuigaibtn.setOnClickListener(onclicklisten);
            delbtn.setOnClickListener(onclicklisten);
        }
        one.setText(ay);
        two.setText(ah_number);
        three.setText(wtr);
        fore.setText(dfdsr);
        five.setText(dsr);

        //获取日期
        final Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
        mHour = ca.get(Calendar.HOUR_OF_DAY);
        mMinute = ca.get(Calendar.MINUTE);

        create_date = mYear+"-"+mMonth+"-"+mDay+" "+mHour+":"+mMinute;
        update_date = mYear+"-"+mMonth+"-"+mDay+" "+mHour+":"+mMinute;
    }

    private void initView() {
        five = (TextView) findViewById(R.id.five);
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
        shenpan = (EditText) findViewById(R.id.shenpan);
        zhushe_phone = (EditText) findViewById(R.id.zhushe_phone);
        faguan_phone = (EditText) findViewById(R.id.faguan_phone);
        edit_log = (EditText) findViewById(R.id.edit_log);
        lian = (TextView) findViewById(R.id.lian);
        kaiting= (TextView) findViewById(R.id.kaiting);
        xuanpan= (TextView) findViewById(R.id.xuanpan);
        shangsu= (TextView) findViewById(R.id.shangsu);
        mspjg= (EditText) findViewById(R.id.spjg);
        fating= (EditText) findViewById(R.id.fating);
        faguan= (EditText) findViewById(R.id.faguan);
        sujiyuan= (EditText) findViewById(R.id.sujiyuan);

        backbtn.setOnClickListener(onclicklisten);
        lian.setOnClickListener(onclicklisten);
        kaiting.setOnClickListener(onclicklisten);
        xuanpan.setOnClickListener(onclicklisten);
        shangsu.setOnClickListener(onclicklisten);
        shenpan.addTextChangedListener(new newtextWatcher(shenpan));
        zhushe_phone.addTextChangedListener(new newtextWatcher(zhushe_phone));
        faguan_phone.addTextChangedListener(new newtextWatcher(faguan_phone));
        edit_log.addTextChangedListener(new newtextWatcher(edit_log));
        mspjg.addTextChangedListener(new newtextWatcher(mspjg));
        fating.addTextChangedListener(new newtextWatcher(fating));
        faguan.addTextChangedListener(new newtextWatcher(faguan));
        sujiyuan.addTextChangedListener(new newtextWatcher(sujiyuan));
    }

    View.OnClickListener onclicklisten = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.submit_btn:
                    if(!TextUtils.isEmpty(shenpan.getText())){
                        if(!TextUtils.isEmpty(fating.getText())){
                            if(!lian.getText().toString().equals("请选择时间")){
                                if(!kaiting.getText().toString().equals("请选择时间")){
                                    ladate = lian.getText().toString();
                                    ktdate = kaiting.getText().toString();
                                    spdate = xuanpan.getText().toString();
                                    ssdate = shangsu.getText().toString();
                                    spcx = shenpan.getText().toString();
                                    spjg = mspjg.getText().toString();
                                    spjg = mspjg.getText().toString();
                                    ft = fating.getText().toString();
                                    zsfg = faguan.getText().toString();
                                    zsfgtel = faguan_phone.getText().toString();
                                    sjy = sujiyuan.getText().toString();
                                    sjytel = zhushe_phone.getText().toString();
                                    bz = edit_log.getText().toString();
                                    if(xuanpan.getText().toString().equals("请选择时间") && shangsu.getText().toString().equals("请选择时间")){
                                        if(!TextUtils.isEmpty(faguan_phone.getText()) && !TextUtils.isEmpty(zhushe_phone.getText())){
                                            if(isMobileNO(zsfgtel) && isMobileNO(sjytel)){
                                                postFaYuan2();
                                            }else {
                                                Toast.makeText(FaYuanActivity.this,"联系电话格式错误",Toast.LENGTH_SHORT).show();
                                            }
                                        }else if(!TextUtils.isEmpty(faguan_phone.getText()) && TextUtils.isEmpty(zhushe_phone.getText())){
                                            if(isMobileNO(zsfgtel) ){
                                                postFaYuan2();
                                            }else {
                                                Toast.makeText(FaYuanActivity.this,"法官联系电话格式错误",Toast.LENGTH_SHORT).show();
                                            }
                                        }else if(TextUtils.isEmpty(faguan_phone.getText()) && !TextUtils.isEmpty(zhushe_phone.getText())){
                                            if(isMobileNO(sjytel)){
                                                postFaYuan2();
                                            }else {
                                                Toast.makeText(FaYuanActivity.this,"书记员联系电话格式错误",Toast.LENGTH_SHORT).show();
                                            }
                                        }else {
                                            postFaYuan2();
                                        }
                                    }else if(xuanpan.getText().toString().equals("请选择时间") && !shangsu.getText().toString().equals("请选择时间")){
                                        if(!TextUtils.isEmpty(faguan_phone.getText()) && !TextUtils.isEmpty(zhushe_phone.getText())){
                                            if(isMobileNO(zsfgtel) && isMobileNO(sjytel)){
                                                postFaYuan4();
                                            }else {
                                                Toast.makeText(FaYuanActivity.this,"联系电话格式错误",Toast.LENGTH_SHORT).show();
                                            }
                                        }else if(!TextUtils.isEmpty(faguan_phone.getText()) && TextUtils.isEmpty(zhushe_phone.getText())){
                                            if(isMobileNO(zsfgtel) ){
                                                postFaYuan4();
                                            }else {
                                                Toast.makeText(FaYuanActivity.this,"法官联系电话格式错误",Toast.LENGTH_SHORT).show();
                                            }
                                        }else if(TextUtils.isEmpty(faguan_phone.getText()) && !TextUtils.isEmpty(zhushe_phone.getText())){
                                            if(isMobileNO(sjytel)){
                                                postFaYuan4();
                                            }else {
                                                Toast.makeText(FaYuanActivity.this,"书记员联系电话格式错误",Toast.LENGTH_SHORT).show();
                                            }
                                        }else {
                                            postFaYuan4();
                                        }
                                    }else if(!xuanpan.getText().toString().equals("请选择时间") && shangsu.getText().toString().equals("请选择时间")){
                                        if(!TextUtils.isEmpty(faguan_phone.getText()) && !TextUtils.isEmpty(zhushe_phone.getText())){
                                            if(isMobileNO(zsfgtel) && isMobileNO(sjytel)){
                                                postFaYuan1();
                                            }else {
                                                Toast.makeText(FaYuanActivity.this,"联系电话格式错误",Toast.LENGTH_SHORT).show();
                                            }
                                        }else if(!TextUtils.isEmpty(faguan_phone.getText()) && TextUtils.isEmpty(zhushe_phone.getText())){
                                            if(isMobileNO(zsfgtel) ){
                                                postFaYuan1();
                                            }else {
                                                Toast.makeText(FaYuanActivity.this,"法官联系电话格式错误",Toast.LENGTH_SHORT).show();
                                            }
                                        }else if(TextUtils.isEmpty(faguan_phone.getText()) && !TextUtils.isEmpty(zhushe_phone.getText())){
                                            if(isMobileNO(sjytel)){
                                                postFaYuan1();
                                            }else {
                                                Toast.makeText(FaYuanActivity.this,"书记员联系电话格式错误",Toast.LENGTH_SHORT).show();
                                            }
                                        }else {
                                            postFaYuan1();
                                        }
                                    }else {
                                        if(!TextUtils.isEmpty(faguan_phone.getText()) && !TextUtils.isEmpty(zhushe_phone.getText())){
                                            if(isMobileNO(zsfgtel) && isMobileNO(sjytel)){
                                                postFaYuan();
                                            }else {
                                                Toast.makeText(FaYuanActivity.this,"联系电话格式错误",Toast.LENGTH_SHORT).show();
                                            }
                                        }else if(!TextUtils.isEmpty(faguan_phone.getText()) && TextUtils.isEmpty(zhushe_phone.getText())){
                                            if(isMobileNO(zsfgtel) ){
                                                postFaYuan();
                                            }else {
                                                Toast.makeText(FaYuanActivity.this,"法官联系电话格式错误",Toast.LENGTH_SHORT).show();
                                            }
                                        }else if(TextUtils.isEmpty(faguan_phone.getText()) && !TextUtils.isEmpty(zhushe_phone.getText())){
                                            if(isMobileNO(sjytel)){
                                                postFaYuan();
                                            }else {
                                                Toast.makeText(FaYuanActivity.this,"书记员联系电话格式错误",Toast.LENGTH_SHORT).show();
                                            }
                                        }else {
                                            postFaYuan();
                                        }
                                    }

                                }else {
                                    Toast.makeText(FaYuanActivity.this,"开庭时间不能为空",Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(FaYuanActivity.this,"立案日期不能为空",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(FaYuanActivity.this,"法庭不能为空",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(FaYuanActivity.this,"审判程序不能为空",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.del_btn:
                    delfayuan();
                    break;
                case R.id.xuigai_btn:
                    ladate = lian.getText().toString();
                    ktdate = kaiting.getText().toString();
                    spdate = xuanpan.getText().toString();
                    ssdate = shangsu.getText().toString();
                    spcx = shenpan.getText().toString();
                    spjg = mspjg.getText().toString();
                    spjg = mspjg.getText().toString();
                    ft = fating.getText().toString();
                    zsfg = faguan.getText().toString();
                    zsfgtel = faguan_phone.getText().toString();
                    sjy = sujiyuan.getText().toString();
                    sjytel = zhushe_phone.getText().toString();
                    bz = edit_log.getText().toString();
                    if(!TextUtils.isEmpty(shenpan.getText())){
                        if(!TextUtils.isEmpty(fating.getText())){
                            if(!lian.getText().toString().equals("请选择时间")){
                                if(!kaiting.getText().toString().equals("请选择时间")){
                                    ladate = lian.getText().toString();
                                    ktdate = kaiting.getText().toString();
                                    spdate = xuanpan.getText().toString();
                                    ssdate = shangsu.getText().toString();
                                    spcx = shenpan.getText().toString();
                                    spjg = mspjg.getText().toString();
                                    spjg = mspjg.getText().toString();
                                    ft = fating.getText().toString();
                                    zsfg = faguan.getText().toString();
                                    zsfgtel = faguan_phone.getText().toString();
                                    sjy = sujiyuan.getText().toString();
                                    sjytel = zhushe_phone.getText().toString();
                                    bz = edit_log.getText().toString();
                                    if(xuanpan.getText().toString().equals("请选择时间") && shangsu.getText().toString().equals("请选择时间")){
                                        if(!TextUtils.isEmpty(faguan_phone.getText()) && !TextUtils.isEmpty(zhushe_phone.getText())){
                                            if(isMobileNO(zsfgtel) && isMobileNO(sjytel)){
                                                xgjiancha2();
                                            }else {
                                                Toast.makeText(FaYuanActivity.this,"联系电话格式错误",Toast.LENGTH_SHORT).show();
                                            }
                                        }else if(!TextUtils.isEmpty(faguan_phone.getText()) && TextUtils.isEmpty(zhushe_phone.getText())){
                                            if(isMobileNO(zsfgtel) ){
                                                xgjiancha2();
                                            }else {
                                                Toast.makeText(FaYuanActivity.this,"法官联系电话格式错误",Toast.LENGTH_SHORT).show();
                                            }
                                        }else if(TextUtils.isEmpty(faguan_phone.getText()) && !TextUtils.isEmpty(zhushe_phone.getText())){
                                            if(isMobileNO(sjytel)){
                                                xgjiancha2();
                                            }else {
                                                Toast.makeText(FaYuanActivity.this,"书记员联系电话格式错误",Toast.LENGTH_SHORT).show();
                                            }
                                        }else {
                                            xgjiancha2();
                                        }
                                    }else if(xuanpan.getText().toString().equals("请选择时间") && !shangsu.getText().toString().equals("请选择时间")){
                                        if(!TextUtils.isEmpty(faguan_phone.getText()) && !TextUtils.isEmpty(zhushe_phone.getText())){
                                            if(isMobileNO(zsfgtel) && isMobileNO(sjytel)){
                                                xgjiancha3();
                                            }else {
                                                Toast.makeText(FaYuanActivity.this,"联系电话格式错误",Toast.LENGTH_SHORT).show();
                                            }
                                        }else if(!TextUtils.isEmpty(faguan_phone.getText()) && TextUtils.isEmpty(zhushe_phone.getText())){
                                            if(isMobileNO(zsfgtel) ){
                                                xgjiancha3();
                                            }else {
                                                Toast.makeText(FaYuanActivity.this,"法官联系电话格式错误",Toast.LENGTH_SHORT).show();
                                            }
                                        }else if(TextUtils.isEmpty(faguan_phone.getText()) && !TextUtils.isEmpty(zhushe_phone.getText())){
                                            if(isMobileNO(sjytel)){
                                                xgjiancha3();
                                            }else {
                                                Toast.makeText(FaYuanActivity.this,"书记员联系电话格式错误",Toast.LENGTH_SHORT).show();
                                            }
                                        }else {
                                            xgjiancha3();
                                        }
                                    }else if(!xuanpan.getText().toString().equals("请选择时间") && shangsu.getText().toString().equals("请选择时间")){
                                        if(!TextUtils.isEmpty(faguan_phone.getText()) && !TextUtils.isEmpty(zhushe_phone.getText())){
                                            if(isMobileNO(zsfgtel) && isMobileNO(sjytel)){
                                                xgjiancha1();
                                            }else {
                                                Toast.makeText(FaYuanActivity.this,"联系电话格式错误",Toast.LENGTH_SHORT).show();
                                            }
                                        }else if(!TextUtils.isEmpty(faguan_phone.getText()) && TextUtils.isEmpty(zhushe_phone.getText())){
                                            if(isMobileNO(zsfgtel) ){
                                                xgjiancha1();
                                            }else {
                                                Toast.makeText(FaYuanActivity.this,"法官联系电话格式错误",Toast.LENGTH_SHORT).show();
                                            }
                                        }else if(TextUtils.isEmpty(faguan_phone.getText()) && !TextUtils.isEmpty(zhushe_phone.getText())){
                                            if(isMobileNO(sjytel)){
                                                xgjiancha1();
                                            }else {
                                                Toast.makeText(FaYuanActivity.this,"书记员联系电话格式错误",Toast.LENGTH_SHORT).show();
                                            }
                                        }else {
                                            xgjiancha1();
                                        }
                                    }else {
                                        if(!TextUtils.isEmpty(faguan_phone.getText()) && !TextUtils.isEmpty(zhushe_phone.getText())){
                                            if(isMobileNO(zsfgtel) && isMobileNO(sjytel)){
                                                xgjiancha();
                                            }else {
                                                Toast.makeText(FaYuanActivity.this,"联系电话格式错误",Toast.LENGTH_SHORT).show();
                                            }
                                        }else if(!TextUtils.isEmpty(faguan_phone.getText()) && TextUtils.isEmpty(zhushe_phone.getText())){
                                            if(isMobileNO(zsfgtel) ){
                                                xgjiancha();
                                            }else {
                                                Toast.makeText(FaYuanActivity.this,"法官联系电话格式错误",Toast.LENGTH_SHORT).show();
                                            }
                                        }else if(TextUtils.isEmpty(faguan_phone.getText()) && !TextUtils.isEmpty(zhushe_phone.getText())){
                                            if(isMobileNO(sjytel)){
                                                xgjiancha();
                                            }else {
                                                Toast.makeText(FaYuanActivity.this,"书记员联系电话格式错误",Toast.LENGTH_SHORT).show();
                                            }
                                        }else {
                                            xgjiancha();
                                        }
                                    }

                                }else {
                                    Toast.makeText(FaYuanActivity.this,"开庭时间不能为空",Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(FaYuanActivity.this,"立案日期不能为空",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(FaYuanActivity.this,"法庭不能为空",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(FaYuanActivity.this,"审判程序不能为空",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.back_btn:
                    finish();
                    break;
                case R.id.lian:
                    lian.setText(initStartDateTime);
                    lian.setTextColor(getResources().getColor(R.color.black));
                    DateTimePickDialogUtil dateTimePickDialogUtil = new DateTimePickDialogUtil(initStartDateTime,FaYuanActivity.this);
                    dateTimePickDialogUtil.dateTimePicKDialog(lian);
                    break;
                case R.id.kaiting:
                    kaiting.setText(initStartDateTime);
                    kaiting.setTextColor(getResources().getColor(R.color.black));
                    DateTimePickDialogUtil dateTimePickDialogUtil1 = new DateTimePickDialogUtil(initStartDateTime,FaYuanActivity.this);
                    dateTimePickDialogUtil1.dateTimePicKDialog(kaiting);
                    break;
                case R.id.xuanpan:
                    xuanpan.setText(initStartDateTime);
                    xuanpan.setTextColor(getResources().getColor(R.color.black));
                    DateTimePickDialogUtil dateTimePickDialogUtil2 = new DateTimePickDialogUtil(initStartDateTime,FaYuanActivity.this);
                    dateTimePickDialogUtil2.dateTimePicKDialog(xuanpan);
                    break;
                case R.id.shangsu:
                    shangsu.setText(initStartDateTime);
                    shangsu.setTextColor(getResources().getColor(R.color.black));
                    DateTimePickDialogUtil dateTimePickDialogUtil3 = new DateTimePickDialogUtil(initStartDateTime,FaYuanActivity.this);
                    dateTimePickDialogUtil3.dateTimePicKDialog(shangsu);
                    break;
            }
        }
    };


    String id;
    String cid;
    String glid;
    String spcx;
    String bz;
    String spjg;
    String ladate;
    String ktdate;
    String spdate;
    String ssdate;
    String ft;
    String zsfg;
    String zsfgtel;
    String sjy;
    String sjytel;
    private void postFaYuan(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("上传中...");
        MainApi.getInstance(this).postaddfayuanApi(id, cid, glid, spcx, bz, spjg,
                ladate, ktdate, spdate, ssdate, ft, zsfg, zsfgtel, sjy, sjytel,
                create_date, new GetResultCallBack() {
                    @Override
                    public void getResult(String result, int type) {
                        if(type == Constants.TYPE_SUCCESS){
                            loadingDialog.dismiss();
                            Toast.makeText(FaYuanActivity.this,"上传成功",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(FaYuanActivity.this, CreateWorkActivity.class);
                            intent.putExtra("type",4);
                            intent.putExtra("id",glid);
                            intent.putExtra("ah_number",ah_number);
                            intent.putExtra("wtr",wtr);
                            intent.putExtra("dsr",dsr);
                            intent.putExtra("dfdsr",dfdsr);
                            intent.putExtra("ay",ay);
                            startActivity(intent);
                            finish();
                        }else BaseApi.showErrMsg(FaYuanActivity.this,result);
                    }
                });
    }

    private void postFaYuan4(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("上传中...");
        MainApi.getInstance(this).postaddfayuanApi4(id, cid, glid, spcx, bz, spjg,
                ladate, ktdate,ssdate, ft, zsfg, zsfgtel, sjy, sjytel,
                create_date, new GetResultCallBack() {
                    @Override
                    public void getResult(String result, int type) {
                        if(type == Constants.TYPE_SUCCESS){
                            loadingDialog.dismiss();
                            Intent intent = new Intent(FaYuanActivity.this, CreateWorkActivity.class);
                            intent.putExtra("type",4);
                            intent.putExtra("id",glid);
                            intent.putExtra("ah_number",ah_number);
                            intent.putExtra("wtr",wtr);
                            intent.putExtra("dsr",dsr);
                            intent.putExtra("dfdsr",dfdsr);
                            intent.putExtra("ay",ay);
                            startActivity(intent);
                            finish();
                        }else BaseApi.showErrMsg(FaYuanActivity.this,result);
                    }
                });
    }

    private void postFaYuan1(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("上传中...");
        MainApi.getInstance(this).postaddfayuanApi1(id, cid, glid, spcx, bz, spjg,
                ladate, ktdate, spdate,ft, zsfg, zsfgtel, sjy, sjytel,
                create_date, new GetResultCallBack() {
                    @Override
                    public void getResult(String result, int type) {
                        if(type == Constants.TYPE_SUCCESS){
                            loadingDialog.dismiss();
                            Intent intent = new Intent(FaYuanActivity.this, CreateWorkActivity.class);
                            intent.putExtra("type",4);
                            intent.putExtra("id",glid);
                            intent.putExtra("ah_number",ah_number);
                            intent.putExtra("wtr",wtr);
                            intent.putExtra("dsr",dsr);
                            intent.putExtra("dfdsr",dfdsr);
                            intent.putExtra("ay",ay);
                            startActivity(intent);
                            finish();
                        }else BaseApi.showErrMsg(FaYuanActivity.this,result);
                    }
                });
    }

    private void postFaYuan2(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("上传中...");
        MainApi.getInstance(this).postaddfayuanApi2(id, cid, glid, spcx, bz, spjg,
                ladate, ktdate, ft, zsfg, zsfgtel, sjy, sjytel,
                create_date, new GetResultCallBack() {
                    @Override
                    public void getResult(String result, int type) {
                        if(type == Constants.TYPE_SUCCESS){
                            loadingDialog.dismiss();
                            Intent intent = new Intent(FaYuanActivity.this, CreateWorkActivity.class);
                            intent.putExtra("type",4);
                            intent.putExtra("id",glid);
                            intent.putExtra("ah_number",ah_number);
                            intent.putExtra("wtr",wtr);
                            intent.putExtra("dsr",dsr);
                            intent.putExtra("dfdsr",dfdsr);
                            intent.putExtra("ay",ay);
                            startActivity(intent);
                            finish();
                        }else BaseApi.showErrMsg(FaYuanActivity.this,result);
                    }
                });
    }

    private void xgjiancha(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("上传中...");
        MainApi.getInstance(this).getxgfayuanApi(aid,spcx,bz,spjg,ladate,ktdate,spdate,ssdate,
                ft,zsfg,zsfgtel,sjy,sjytel,update_date,new GetResultCallBack() {
                    @Override
                    public void getResult(String result, int type) {
                        if(type == Constants.TYPE_SUCCESS){
                            loadingDialog.dismiss();
                            Intent intent = new Intent(FaYuanActivity.this, CreateWorkActivity.class);
                            intent.putExtra("type",4);
                            intent.putExtra("id",glid);
                            intent.putExtra("ah_number",ah_number);
                            intent.putExtra("wtr",wtr);
                            intent.putExtra("dsr",dsr);
                            intent.putExtra("dfdsr",dfdsr);
                            intent.putExtra("ay",ay);
                            startActivity(intent);
                            finish();
                        }else BaseApi.showErrMsg(FaYuanActivity.this,result);
                    }
                });
    }

    private void xgjiancha3(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("上传中...");
        MainApi.getInstance(this).getxgfayuanApi3(aid,spcx,bz,spjg,ladate,ktdate,ssdate,
                ft,zsfg,zsfgtel,sjy,sjytel,update_date,new GetResultCallBack() {
                    @Override
                    public void getResult(String result, int type) {
                        if(type == Constants.TYPE_SUCCESS){
                            loadingDialog.dismiss();
                            Intent intent = new Intent(FaYuanActivity.this, CreateWorkActivity.class);
                            intent.putExtra("type",4);
                            intent.putExtra("id",glid);
                            intent.putExtra("ah_number",ah_number);
                            intent.putExtra("wtr",wtr);
                            intent.putExtra("dsr",dsr);
                            intent.putExtra("dfdsr",dfdsr);
                            intent.putExtra("ay",ay);
                            startActivity(intent);
                            finish();
                        }else BaseApi.showErrMsg(FaYuanActivity.this,result);
                    }
                });
    }

    private void xgjiancha1(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("上传中...");
        MainApi.getInstance(this).getxgfayuanApi1(aid,spcx,bz,spjg,ladate,ktdate,spdate,
                ft,zsfg,zsfgtel,sjy,sjytel,update_date,new GetResultCallBack() {
                    @Override
                    public void getResult(String result, int type) {
                        if(type == Constants.TYPE_SUCCESS){
                            loadingDialog.dismiss();
                            Intent intent = new Intent(FaYuanActivity.this, CreateWorkActivity.class);
                            intent.putExtra("type",4);
                            intent.putExtra("id",glid);
                            intent.putExtra("ah_number",ah_number);
                            intent.putExtra("wtr",wtr);
                            intent.putExtra("dsr",dsr);
                            intent.putExtra("dfdsr",dfdsr);
                            intent.putExtra("ay",ay);
                            startActivity(intent);
                            finish();
                        }else BaseApi.showErrMsg(FaYuanActivity.this,result);
                    }
                });
    }

    private void xgjiancha2(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("上传中...");
        MainApi.getInstance(this).getxgfayuanApi2(aid,spcx,bz,spjg,ladate,ktdate,
                ft,zsfg,zsfgtel,sjy,sjytel,update_date,new GetResultCallBack() {
                    @Override
                    public void getResult(String result, int type) {
                        if(type == Constants.TYPE_SUCCESS){
                            loadingDialog.dismiss();
                            Intent intent = new Intent(FaYuanActivity.this, CreateWorkActivity.class);
                            intent.putExtra("type",4);
                            intent.putExtra("id",glid);
                            intent.putExtra("ah_number",ah_number);
                            intent.putExtra("wtr",wtr);
                            intent.putExtra("dsr",dsr);
                            intent.putExtra("dfdsr",dfdsr);
                            intent.putExtra("ay",ay);
                            startActivity(intent);
                            finish();
                        }else BaseApi.showErrMsg(FaYuanActivity.this,result);
                    }
                });
    }

    /**
     * 删除
     */
    String aid;
    private void delfayuan(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("删除中...");
        MainApi.getInstance(this).getdelfayuanApi(aid,new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type == Constants.TYPE_SUCCESS){
                    loadingDialog.dismiss();
                    Intent intent = new Intent(FaYuanActivity.this, CreateWorkActivity.class);
                    intent.putExtra("type",4);
                    intent.putExtra("id",glid);
                    intent.putExtra("ah_number",ah_number);
                    intent.putExtra("wtr",wtr);
                    intent.putExtra("dsr",dsr);
                    intent.putExtra("dfdsr",dfdsr);
                    intent.putExtra("ay",ay);
                    startActivity(intent);
                    finish();
                }else BaseApi.showErrMsg(FaYuanActivity.this,result);
            }
        });
    }

    /**
     * 判断手机格式是否正确
     * @param mobiles
     * @return
     * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
     * 联通：130、131、132、152、155、156、185、186
     * 电信：133、153、180、189、（1349卫通）
     * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
     */
    public static boolean isMobileNO(String mobiles) {
        String telRegex = "[1][358]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
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
