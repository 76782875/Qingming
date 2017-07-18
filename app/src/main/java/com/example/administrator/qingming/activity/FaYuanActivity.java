package com.example.administrator.qingming.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
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

public class FaYuanActivity extends Activity {
    private static final int DATE_DIALOG = 0;
    private static final int DATE_DIALOY = 1;
    private static final int DATE_DIALOA = 2;
    private static final int DATE_DIALOB= 3;
    LoadingDialog loadingDialog;
    int mYear,mMonth,mDay;
    private String ah_number,wtr,dfdsr,ay;
    private TextView one,two,three,fore,name;
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
            xuanpan.setText(bundle.getString("spdate",""));
            shangsu.setText(bundle.getString("ssdate",""));
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
                            if(!lian.getText().toString().equals("请选择立案日期")){
                                if(!kaiting.getText().toString().equals("请选择开庭时间")){
                                    if(!xuanpan.getText().toString().equals("请选择宣判日期")){
                                        if(!shangsu.getText().toString().equals("请选择上诉日期")){
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
                                            postFaYuan();
                                        }else {
                                            Toast.makeText(FaYuanActivity.this,"上诉日期不能为空",Toast.LENGTH_SHORT).show();
                                        }
                                    }else {
                                        Toast.makeText(FaYuanActivity.this,"宣判日期不能为空",Toast.LENGTH_SHORT).show();
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
                    xgjiancha();
                    break;
                case R.id.back_btn:
                    finish();
                    break;
                case R.id.lian:
                    showDialog(DATE_DIALOG);
                    break;
                case R.id.kaiting:
                    showDialog(DATE_DIALOY);
                    break;
                case R.id.xuanpan:
                    showDialog(DATE_DIALOA);
                    break;
                case R.id.shangsu:
                    showDialog(DATE_DIALOB);
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
            case DATE_DIALOA:
                return new DatePickerDialog(this,monDateSetListener1, mYear, mMonth, mDay);
            case DATE_DIALOB:
                return new DatePickerDialog(this,monDateSetListener2, mYear, mMonth, mDay);
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
            xuanpan.setText(new StringBuffer().append(mYear).append("-").append(mMonth + 1).append("-").
                    append(mDay).append(" "));
            xuanpan.setTextColor(getResources().getColor(R.color.black));
        }
    };

    DatePickerDialog.OnDateSetListener monDateSetListener2 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            shangsu.setText(new StringBuffer().append(mYear).append("-").append(mMonth + 1).append("-").
                    append(mDay).append(" "));
            shangsu.setTextColor(getResources().getColor(R.color.black));
        }
    };


    /**
     * 设置日期 利用StringBuffer追加
     */
    public void display() {
        lian.setText(new StringBuffer().append(mYear).append("-").append(mMonth + 1).append("-").
                append(mDay).append(" "));
        lian.setTextColor(getResources().getColor(R.color.black));
    }
    public void finplay() {
        kaiting.setText(new StringBuffer().append(mYear).append("-").append(mMonth + 1).append("-").
                append(mDay).append(" "));
        kaiting.setTextColor(getResources().getColor(R.color.black));
    }

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
                            Toast.makeText(FaYuanActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(FaYuanActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                    finish();
                }else BaseApi.showErrMsg(FaYuanActivity.this,result);
            }
        });
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
