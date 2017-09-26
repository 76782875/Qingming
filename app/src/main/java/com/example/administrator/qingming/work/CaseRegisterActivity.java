package com.example.administrator.qingming.work;

import android.app.Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.example.administrator.qingming.activity.MyCaseActivity;
import com.example.administrator.qingming.activity.ZhenChaActivity;
import com.example.administrator.qingming.api.BaseApi;
import com.example.administrator.qingming.api.MainApi;
import com.example.administrator.qingming.dialog.LoadingDialog;
import com.example.administrator.qingming.interfaces.GetResultCallBack;
import com.example.administrator.qingming.model.Constants;
import com.example.administrator.qingming.model.ModelCaseNo;
import com.example.administrator.qingming.model.ModelCity;
import com.example.administrator.qingming.qinminutils.CacheServerResponse;
import com.example.administrator.qingming.qinminutils.GsonUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lljjcoder.citypickerview.widget.CityPicker;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


/**
 * Created by Administrator on 2017/5/10 0010.
 */

public class CaseRegisterActivity extends Activity {
    private String initStartDateTime ; // 初始化开始时间
    private TextView date_of_cognizance,lawsuit,locus_standi,shen,city,county,textView,shi,shoufei;
    private EditText brief,agency_fee,grant,consignor,dfdsr,slbm,ssbd,public_security,procuratorate,court,
            detention_house,remark,lxdh,dsr;
    private EditText stime,sfwrc,snumb,swtr,sbzsm;
    private ImageView backbtn;
    private Button submitbtn;
    private LinearLayout choose_city,xinshi,zong,wenshu;
    private CityPicker cityPicker ;
    private LoadingDialog loadingDialog;
    private List<ModelCaseNo> list;
    private int index;
    private String cc;
    private List<ModelCity.ResultBean> citylist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_register);

        SharedPreferences sharedPreferences = getSharedPreferences("qinmin", Context.MODE_PRIVATE);
        id = sharedPreferences.getString("id","");
        cid = sharedPreferences.getString("cid","");

        initView();

        cxprovince();
        cxcity();
        cxcounty();
        Bundle bundle = this.getIntent().getExtras();
        index = bundle.getInt("index");
        ajlx = bundle.getInt("ajlx");
        cc = bundle.getString("cc","");
        if(ajlx == 1){
            textView.setText("民事案件");
        }else if(ajlx == 2){
            textView.setText("刑事案件");
            xinshi.setVisibility(View.VISIBLE);
        }else if(ajlx == 3){
            textView.setText("行政案件");
        }else if(ajlx == 4){
            textView.setText("非诉讼法律事务");
        }else if(ajlx == 5){
            textView.setText("法律顾问");
        }else if(ajlx == 7){
            textView.setText("执行案件");
        }else if(ajlx == 8){
            textView.setText("中保案件");
        }else if(ajlx == 9){
            textView.setText("仲裁案件");
        }else if(ajlx == 10){
            textView.setText("破产案件");
        }

        //获取日期
        final Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH)+1;
        mDay = ca.get(Calendar.DAY_OF_MONTH);

        if(index == 101){
            ajfl = bundle.getInt("ajfl");
            date_of_cognizance.setText(mYear+"-"+mMonth+"-"+mDay);
        }else {
            ajfl = bundle.getInt("ajfl");
            ay = bundle.getString("ay", "");
            ah_number = bundle.getString("ah_number", "");
            wtr = bundle.getString("wtr", "");
            mlxdh = bundle.getString("lxdh", "");
            mdsr = bundle.getString("dsr", "");
            mdfdsr = bundle.getString("dfdsr", "");
            mid = bundle.getString("id", "");
            dlf = "" + bundle.getInt("dlf");
            zjf = "" + bundle.getInt("jzf");
            sffs = bundle.getString("sffs", "");
            sarq = bundle.getString("sarq", "");
            mcourt = bundle.getString("court", "");
            detention = bundle.getString("detention", "");
            police = bundle.getString("police", "");
            mprocuratorate = bundle.getString("procuratorate", "");
            mslbm = bundle.getString("slbm", "");
            bzsm = bundle.getString("bzsm", "");
            ssdw = bundle.getString("ssdw");
            mssbd = bundle.getString("ssbd", "");
            ssjd = bundle.getString("ssjd");
            badq = bundle.getString("badq");
            if (!TextUtils.isEmpty(badq)) {
                StringBuffer r = read();
                StringBuffer r1 = read1();
                StringBuffer r2 = read2();
                StringBuffer r3 = read3();
                StringBuffer r4 = read4();
                StringBuffer r5 = read5();

                String[] countyidarray = r.toString().split(",");
                String[] countynamearray = r1.toString().split(",");
                String[] cityidarray = r3.toString().split(",");
                String[] citynamearray = r2.toString().split(",");
                String[] shenidarray = r5.toString().split(",");
                String[] shennamearray = r4.toString().split(",");
                String[] ssc = badq.split(",");
                String p = "";
                String s = "";
                String c = "";
                for (int i = 0; i < shenidarray.length; i++) {
                    if (ssc[0].equals(shenidarray[i])) {
                        p = shennamearray[i];

                    }
                }

                for (int i = 0; i < cityidarray.length; i++) {
                    if (ssc[1].equals(cityidarray[i]) ) {
                        s = citynamearray[i];

                    }
                }

                for (int i = 0; i < countyidarray.length; i++) {
                    if (ssc[2].equals(countyidarray[i])) {
                        c = countynamearray[i];

                    }
                }

                shen.setText(p);
                city.setText(s);
                county.setText(c);
                shen.setTextColor(getResources().getColor(R.color.black));
                city.setTextColor(getResources().getColor(R.color.black));
                county.setTextColor(getResources().getColor(R.color.black));
            }

            if (sffs.equals("0")) {
                shoufei.setText("免费");
            } else if (sffs.equals("1")) {
                shoufei.setText("计件收费");
            } else if (sffs.equals("2")) {
                shoufei.setText("按标的比例收费");
            } else if (sffs.equals("3")) {
                shoufei.setText("风险代理收费");
            } else if (sffs.equals("4")) {
                shoufei.setText("固定+风险代理收费");
            }

            if (ajlx == 2) {
                if (ssjd.equals("0")) {
                    lawsuit.setText("初审");
                } else if (ssjd.equals("1")) {
                    lawsuit.setText("二审");
                } else if (ssjd.equals("2")) {
                    lawsuit.setText("再审");
                }

                if (ssdw.equals("1")) {
                    locus_standi.setText("原告人");
                } else if (ssdw.equals("2")) {
                    locus_standi.setText("犯罪嫌疑人");
                } else if (ssdw.equals("3")) {
                    locus_standi.setText("被告人/附带民事诉讼被告人");
                } else if (ssdw.equals("4")) {
                    locus_standi.setText("上诉人");
                } else if (ssdw.equals("5")) {
                    locus_standi.setText("被上诉人");
                } else if (ssdw.equals("6")) {
                    locus_standi.setText("自诉人");
                } else if (ssdw.equals("7")) {
                    locus_standi.setText("申诉人");
                } else if (ssdw.equals("8")) {
                    locus_standi.setText("被害人");
                } else if (ssdw.equals("9")) {
                    locus_standi.setText("其他");
                }


                public_security.setText(police);
                procuratorate.setText(mprocuratorate);
                court.setText(mcourt);
                detention_house.setText(detention);

            } else if (ajlx != 2 || ajlx != 11) {
                if (ssjd.equals("0")) {
                    lawsuit.setText("调查取证");
                } else if (ssjd.equals("1")) {
                    lawsuit.setText("一审");
                } else if (ssjd.equals("2")) {
                    lawsuit.setText("二审");
                } else if (ssjd.equals("3")) {
                    lawsuit.setText("执行");
                } else if (ssjd.equals("4")) {
                    lawsuit.setText("再审");
                }

                if (ssdw.equals("0")) {
                    locus_standi.setText("原告");
                } else if (ssdw.equals("1")) {
                    locus_standi.setText("被告");
                } else if (ssdw.equals("2")) {
                    locus_standi.setText("上诉人");
                } else if (ssdw.equals("3")) {
                    locus_standi.setText("被上诉人");
                } else if (ssdw.equals("4")) {
                    locus_standi.setText("申请执行人");
                } else if (ssdw.equals("5")) {
                    locus_standi.setText("被执行人");
                } else if (ssdw.equals("6")) {
                    locus_standi.setText("申请人");
                } else if (ssdw.equals("7")) {
                    locus_standi.setText("被申请人");
                } else if (ssdw.equals("8")) {
                    locus_standi.setText("第三人");
                } else if (ssdw.equals("9")) {
                    locus_standi.setText("其他");
                }
            }

            date_of_cognizance.setText(sarq);
            brief.setText(ay);
            consignor.setText(wtr);
            lxdh.setText(mlxdh);
            dsr.setText(mdsr);
            dfdsr.setText(mdfdsr);
            slbm.setText(mslbm);
            ssbd.setText(mssbd);
            shoufei.setText(sffs);
            agency_fee.setText(dlf);
            grant.setText(zjf);
            remark.setText(bzsm);
            date_of_cognizance.setTextColor(getResources().getColor(R.color.black));
            shoufei.setTextColor(getResources().getColor(R.color.black));
            lawsuit.setTextColor(getResources().getColor(R.color.black));
            locus_standi.setTextColor(getResources().getColor(R.color.black));

        }

    }

    private void initView(){
        list = new ArrayList<>();
        citylist = new ArrayList<>();
        loadingDialog = new LoadingDialog(this);
        shoufei = (TextView) findViewById(R.id.sffs);
        textView = (TextView) findViewById(R.id.textView);
        date_of_cognizance = (TextView) findViewById(R.id.date_of_cognizance);//收案日期
        brief = (EditText) findViewById(R.id.brief);//案由
        consignor = (EditText) findViewById(R.id.consignor);//委托人
        lxdh = (EditText) findViewById(R.id.lxdh);//联系电话
        dsr = (EditText) findViewById(R.id.dsr);//当事人
        dfdsr = (EditText) findViewById(R.id.dfdsr);//对方当事人
        slbm = (EditText) findViewById(R.id.slbm);//受理部门
        ssbd = (EditText) findViewById(R.id.ssbd);//诉讼标的
        agency_fee = (EditText) findViewById(R.id.agency_fee);//代理费
        grant = (EditText) findViewById(R.id.grant);//杂费
        public_security = (EditText) findViewById(R.id.public_security);//公安
        procuratorate = (EditText) findViewById(R.id.procuratorate);//检察院
        court = (EditText) findViewById(R.id.court);//法院
        detention_house = (EditText) findViewById(R.id.detention_house);//看守所
        remark = (EditText) findViewById(R.id.remark);//备注
        backbtn = (ImageView) findViewById(R.id.back_btn);
        submitbtn = (Button) findViewById(R.id.submit_btn);
        lawsuit = (TextView) findViewById(R.id.lawsuit);//诉讼阶段
        locus_standi = (TextView) findViewById(R.id.locus_standi);//诉讼地位
        shen = (TextView) findViewById(R.id.sheng);//省
        city = (TextView) findViewById(R.id.city);//市
        county = (TextView) findViewById(R.id.county);//县
        choose_city = (LinearLayout) findViewById(R.id.choose_city);
        xinshi = (LinearLayout) findViewById(R.id.xinshi);
        zong = (LinearLayout) findViewById(R.id.zong);
        wenshu = (LinearLayout) findViewById(R.id.wenshu);
        //咨询代写文书
        shi = (TextView) findViewById(R.id.shi);
        stime = (EditText) findViewById(R.id.stime);
        sfwrc = (EditText) findViewById(R.id.sfwrc);
        snumb = (EditText) findViewById(R.id.snumb);
        swtr = (EditText) findViewById(R.id.swtr);
        sbzsm = (EditText) findViewById(R.id.sbzsm);

        stime.addTextChangedListener(new newtextwatch(stime));
        sfwrc.addTextChangedListener(new newtextwatch(sfwrc));
        snumb.addTextChangedListener(new newtextwatch(snumb));
        swtr.addTextChangedListener(new newtextwatch(swtr));
        sbzsm.addTextChangedListener(new newtextwatch(sbzsm));

        date_of_cognizance.setOnClickListener(onClickListener);
        dfdsr.addTextChangedListener(new newtextwatch(dfdsr));
        slbm.addTextChangedListener(new newtextwatch(slbm));
        ssbd.addTextChangedListener(new newtextwatch(ssbd));
        brief.addTextChangedListener(new newtextwatch(brief));
        agency_fee.addTextChangedListener(new newtextwatch(agency_fee));
        grant.addTextChangedListener(new newtextwatch(grant));
        public_security.addTextChangedListener(new newtextwatch(public_security));
        procuratorate.addTextChangedListener(new newtextwatch(procuratorate));
        court.addTextChangedListener(new newtextwatch(court));
        detention_house.addTextChangedListener(new newtextwatch(detention_house));
        remark.addTextChangedListener(new newtextwatch(remark));
        consignor.addTextChangedListener(new newtextwatch(consignor));
        lxdh.addTextChangedListener(new newtextwatch(lxdh));
        dsr.addTextChangedListener(new newtextwatch(dsr));
        backbtn.setOnClickListener(onClickListener);
        submitbtn.setOnClickListener(onClickListener);
        lawsuit.setOnClickListener(onClickListener);
        locus_standi.setOnClickListener(onClickListener);
        choose_city.setOnClickListener(onClickListener);
        shoufei.setOnClickListener(onClickListener);
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back_btn:
                    finish();
                    break;
                case R.id.sffs:
                    str = "请填写收费方式";
                    final String[] sf = {"免费", "计件收费", "按标的比例收费", "风险代理收费", "固定+风险代理收费"};
                    showsDialog();
                    builder.setItems(sf, new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            shoufei.setText(sf[which]);
                            shoufei.setTextColor(getResources().getColor(R.color.black));
                        }
                    }).show();
                    break;
                case R.id.lawsuit:
                    str = "请填写诉讼阶段";
                    if(ajlx ==2 ){
                        final String[] jd = {"侦查阶段", "审查起诉", "一审审理", "二审审理", "死刑复核", "再审"};
                        showsDialog();
                        builder.setItems(jd, new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                lawsuit.setText(jd[which]);
                                lawsuit.setTextColor(getResources().getColor(R.color.black));
                                Log.e("","我是1");
                            }
                        }).show();
                    }else {
                        final String[] jdd = {"调查取证", "一审", "二审", "执行", "再审"};
                        showsDialog();
                        builder.setItems(jdd, new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                lawsuit.setText(jdd[which]);
                                lawsuit.setTextColor(getResources().getColor(R.color.black));
                            }
                        }).show();
                    }

                    break;
                case R.id.locus_standi:
                    str = "请选择诉讼地位";
                    if(ajlx==2){
                        final String[] dw = {"原告人", "犯罪嫌疑人", "被告人", "上诉人", "被上诉人",
                                "自诉人", "申诉人", "被害人", "其他"};
                        showsDialog();
                        builder.setItems(dw, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                locus_standi.setText(dw[which]);
                                locus_standi.setTextColor(getResources().getColor(R.color.black));
                            }
                        }).show();
                    }else {
                        final String[] dw = {"原告", "被告", "上诉人", "被上诉人",
                                "申请执行人", "被申请执行人", "申请人", "被申请人", "第三人", "其他"};
                        showsDialog();
                        builder.setItems(dw, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                locus_standi.setText(dw[which]);
                                locus_standi.setTextColor(getResources().getColor(R.color.black));
                            }
                        }).show();
                    }

                    break;
                case R.id.choose_city:
                    cityPickerChoose();
                    break;
                case R.id.date_of_cognizance:
                    date_of_cognizance.setText(initStartDateTime);
                    date_of_cognizance.setTextColor(getResources().getColor(R.color.black));
                    DatePickDialogUtil dateTimePickDialogUtil = new DatePickDialogUtil(initStartDateTime,CaseRegisterActivity.this);
                    dateTimePickDialogUtil.dateTimePicKDialog(date_of_cognizance);
                    break;
                case R.id.submit_btn:
                    if(index == 101){
                        if(ajlx == 11){
//                            if(!TextUtils.isEmpty(stime.getText())){
//                                if(!TextUtils.isEmpty(sfwrc.getText())){
//                                    if(!TextUtils.isEmpty(snumb.getText())){
//                                        if(!TextUtils.isEmpty(swtr.getText())){
//                                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
////                                            fwdate = format.format(new java.util.Date());
////                                            tgfwrc = sfwrc.getText().toString();
////                                            fwfy = snumb.getText().toString();
////                                            wtr = swtr.getText().toString();
////                                            bzsm = sbzsm.getText().toString();
////                                            fwtype = ajfl;//0.口头法律咨询(人次) 1书面法律咨询(人次) 2代写法律文书(件)
////                                            create_date = format.format(new java.util.Date());//创建时间
////                                            postCase();
//                                        }else {
//                                            Toast.makeText(CaseRegisterActivity.this,"委托人不能为空",Toast.LENGTH_SHORT).show();
//                                        }
//                                    }else {
//                                        Toast.makeText(CaseRegisterActivity.this,"服务费用不能为空",Toast.LENGTH_SHORT).show();
//                                    }
//                                }else {
//                                    Toast.makeText(CaseRegisterActivity.this,"服务人次不能为空",Toast.LENGTH_SHORT).show();
//                                }
//                            }else {
//                                Toast.makeText(CaseRegisterActivity.this,"服务时间不能为空",Toast.LENGTH_SHORT).show();
//                            }
                        }else{
                            if(!date_of_cognizance.getText().toString().equals("请填写收案日期")){
                                if(!TextUtils.isEmpty(brief.getText())){
                                    if(!TextUtils.isEmpty(consignor.getText())){
                                        if(!TextUtils.isEmpty(dsr.getText())){
                                            if(!TextUtils.isEmpty(dfdsr.getText())){
                                                if(!TextUtils.isEmpty(slbm.getText())){
                                                    if(!TextUtils.isEmpty(ssbd.getText())){
                                                        if(!shoufei.getText().toString().equals("请选择收费方式")){
                                                            if(!TextUtils.isEmpty(agency_fee.getText())){
                                                                if(!TextUtils.isEmpty(grant.getText())){
                                                                    fangfa();
                                                                    if(!TextUtils.isEmpty(lxdh.getText())){
                                                                        if(isMobileNO(lxdh.getText().toString())){
                                                                            mlxdh = lxdh.getText().toString();//联系电话
                                                                            getHttp();
                                                                        }else {
                                                                            Toast.makeText(CaseRegisterActivity.this,"电话号码格式错误",Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    }else {
                                                                        mlxdh = lxdh.getText().toString();//联系电话
                                                                        getHttp();
                                                                    }

                                                                }else {
                                                                    Toast.makeText(CaseRegisterActivity.this,"杂费不能为空",Toast.LENGTH_SHORT).show();
                                                                }
                                                            }else {
                                                                Toast.makeText(CaseRegisterActivity.this,"代理费不能为空",Toast.LENGTH_SHORT).show();
                                                            }
                                                        }else {
                                                            Toast.makeText(CaseRegisterActivity.this,"收费方式不能为空",Toast.LENGTH_SHORT).show();
                                                        }
                                                    }else {
                                                        Toast.makeText(CaseRegisterActivity.this,"诉讼标的不能为空",Toast.LENGTH_SHORT).show();
                                                    }
                                                }else {
                                                    Toast.makeText(CaseRegisterActivity.this,"受理部门不能为空",Toast.LENGTH_SHORT).show();
                                                }
                                            }else {
                                                Toast.makeText(CaseRegisterActivity.this,"对方当事人不能为空",Toast.LENGTH_SHORT).show();
                                            }
                                        }else {
                                            Toast.makeText(CaseRegisterActivity.this,"当事人不能为空",Toast.LENGTH_SHORT).show();
                                        }
                                    }else {
                                        Toast.makeText(CaseRegisterActivity.this,"委托人不能为空",Toast.LENGTH_SHORT).show();
                                    }
                                }else {
                                    Toast.makeText(CaseRegisterActivity.this,"案由不能为空",Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(CaseRegisterActivity.this,"收案日期不能为空",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }else if(index == 102){
                        if(ajlx == 11){
//                            if(!TextUtils.isEmpty(stime.getText())){
//                                if(!TextUtils.isEmpty(sfwrc.getText())){
//                                    if(!TextUtils.isEmpty(snumb.getText())){
//                                        if(!TextUtils.isEmpty(swtr.getText())){
//                                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
////                                            fwdate = format.format(new java.util.Date());
////                                            tgfwrc = sfwrc.getText().toString();
////                                            fwfy = snumb.getText().toString();
////                                            wtr = swtr.getText().toString();
////                                            bzsm = sbzsm.getText().toString();
////                                            fwtype = ajfl;//0.口头法律咨询(人次) 1书面法律咨询(人次) 2代写法律文书(件)
////                                            create_date = format.format(new java.util.Date());//创建时间
////                                            postCase();
//                                        }else {
//                                            Toast.makeText(CaseRegisterActivity.this,"委托人不能为空",Toast.LENGTH_SHORT).show();
//                                        }
//                                    }else {
//                                        Toast.makeText(CaseRegisterActivity.this,"服务费用不能为空",Toast.LENGTH_SHORT).show();
//                                    }
//                                }else {
//                                    Toast.makeText(CaseRegisterActivity.this,"服务人次不能为空",Toast.LENGTH_SHORT).show();
//                                }
//                            }else {
//                                Toast.makeText(CaseRegisterActivity.this,"服务时间不能为空",Toast.LENGTH_SHORT).show();
//                            }
                        }else{
                            if(!date_of_cognizance.getText().toString().equals("请填写收案日期")){
                                if(!TextUtils.isEmpty(brief.getText())){
                                    if(!TextUtils.isEmpty(consignor.getText())){
                                        if(!TextUtils.isEmpty(dsr.getText())){
                                            if(!TextUtils.isEmpty(dfdsr.getText())){
                                                if(!TextUtils.isEmpty(slbm.getText())){
                                                    if(!TextUtils.isEmpty(ssbd.getText())){
                                                        if(!shoufei.getText().toString().equals("请选择收费方式")){
                                                            if(!TextUtils.isEmpty(agency_fee.getText())){
                                                                if(!TextUtils.isEmpty(grant.getText())){
                                                                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:dd:ss");
                                                                    create_date = format.format(new java.util.Date());//创建时间
                                                                    sarq = date_of_cognizance.getText().toString();//收案日期
                                                                    ay = brief.getText().toString();//案由
                                                                    wtr = consignor.getText().toString();//委托人
                                                                    mdsr = dsr.getText().toString();//对方当事人
                                                                    mdfdsr = dfdsr.getText().toString();//对方当事人
                                                                    mslbm = slbm.getText().toString();//受理部门
                                                                    mssbd = ssbd.getText().toString();//诉讼标的
                                                                    dlf = agency_fee.getText().toString();//代理费
                                                                    zjf = grant.getText().toString();//杂费
//                                                                    badq= shen.getText().toString()+city.getText().toString();//办案地区
                                                                    police= public_security.getText().toString();//公安
                                                                    mprocuratorate= procuratorate.getText().toString();//检查院
                                                                    mcourt= court.getText().toString();//法院
                                                                    detention= detention_house.getText().toString();//看守所
                                                                    remarks= remark.getText().toString();//备注说明
                                                                    fangfa();
                                                                    if(!TextUtils.isEmpty(lxdh.getText())){
                                                                        if(isMobileNO(lxdh.getText().toString())){
                                                                            mlxdh = lxdh.getText().toString();//联系电话
                                                                            postxgmycase();
                                                                        }else {
                                                                            Toast.makeText(CaseRegisterActivity.this,"电话号码格式错误",Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    }else {
                                                                        postxgmycase();
                                                                    }
                                                                }else {
                                                                    Toast.makeText(CaseRegisterActivity.this,"杂费不能为空",Toast.LENGTH_SHORT).show();
                                                                }
                                                            }else {
                                                                Toast.makeText(CaseRegisterActivity.this,"代理费不能为空",Toast.LENGTH_SHORT).show();
                                                            }
                                                        }else {
                                                            Toast.makeText(CaseRegisterActivity.this,"收费方式不能为空",Toast.LENGTH_SHORT).show();
                                                        }
                                                    }else {
                                                        Toast.makeText(CaseRegisterActivity.this,"诉讼标的不能为空",Toast.LENGTH_SHORT).show();
                                                    }
                                                }else {
                                                    Toast.makeText(CaseRegisterActivity.this,"受理部门不能为空",Toast.LENGTH_SHORT).show();
                                                }
                                            }else {
                                                Toast.makeText(CaseRegisterActivity.this,"对方当事人不能为空",Toast.LENGTH_SHORT).show();
                                            }
                                        }else {
                                            Toast.makeText(CaseRegisterActivity.this,"当事人不能为空",Toast.LENGTH_SHORT).show();
                                        }
                                    }else {
                                        Toast.makeText(CaseRegisterActivity.this,"委托人不能为空",Toast.LENGTH_SHORT).show();
                                    }
                                }else {
                                    Toast.makeText(CaseRegisterActivity.this,"案由不能为空",Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(CaseRegisterActivity.this,"收案日期不能为空",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    break;
            }
        }
    };

    private void fangfa(){
        /**
         * 收费方式    0免费 1计件收费 2按标的比例收费 3风险代理收费 4固定+风险代理收费
         * 诉讼阶段 0 调查取证1 一审2 二审 3执行 4再审   刑事案件不同：侦查阶段，审查起诉", "一审审理", "二审审理", "死刑复核", "再审"
         * 诉讼地位 0原告 1被告 2上诉人3 被上诉人4 申请执行人 5被执行人 6申请人7 被申请人 8第三人 9其他
         */
        if(!shoufei.getText().toString().equals("请选择收费方式")){
            if(shoufei.getText().toString().equals("免费")){
                sffs = "0";
            }else if(shoufei.getText().toString().equals("计件收费")){
                sffs = "1";
            }else if(shoufei.getText().toString().equals("按标的比例收费")){
                sffs = "2";
            }else if(shoufei.getText().toString().equals("风险代理收费")){
                sffs = "3";
            }else if(shoufei.getText().toString().equals("固定+风险代理收费")){
                sffs = "4";
            }
        }

        if(ajlx == 2 ){
            Log.e("","我是3");
            if(!lawsuit.getText().toString().equals("请选择诉讼阶段")){
                if(lawsuit.getText().toString().equals("侦查阶段")){
                    ssjd = "0";
                }else if(lawsuit.getText().toString().equals("审查起诉")){
                    ssjd = "1";
                }else if(lawsuit.getText().toString().equals("一审审理")){
                    ssjd = "2";
                }else if(lawsuit.getText().toString().equals("二审审理")){
                    ssjd = "3";
                }else if(lawsuit.getText().toString().equals("死刑复核")){
                    ssjd = "4";
                }else if(lawsuit.getText().toString().equals("再审")){
                    ssjd = "5";
                }
            }

            if(!locus_standi.getText().toString().equals("请选择诉讼地位")){
                if(locus_standi.getText().toString().equals("原告人")){
                    ssdw = "0";
                }else if(locus_standi.getText().toString().equals("犯罪嫌疑人")){
                    ssdw = "1";
                }else if(locus_standi.getText().toString().equals("被告人/附带民事诉讼被告人")){
                    ssdw = "2";
                }else if(locus_standi.getText().toString().equals("上诉人")){
                    ssdw = "3";
                }else if(locus_standi.getText().toString().equals("被上诉人")){
                    ssdw = "4";
                }else if(locus_standi.getText().toString().equals("自诉人")){
                    ssdw = "5";
                }else if(locus_standi.getText().toString().equals("申诉人")){
                    ssdw = "6";
                }else if(locus_standi.getText().toString().equals("被害人")){
                    ssdw = "7";
                }else if(locus_standi.getText().toString().equals("其他")){
                    ssdw = "8";
                }
            }
        }else {
            Log.e("","我是4");
            if(!lawsuit.getText().toString().equals("请选择诉讼阶段")){
                if(lawsuit.getText().toString().equals("调查取证")){
                    ssjd = "0";
                }else if(lawsuit.getText().toString().equals("一审")){
                    ssjd = "1";
                }else if(lawsuit.getText().toString().equals("二审")){
                    ssjd = "2";
                }else if(lawsuit.getText().toString().equals("执行")){
                    ssjd = "3";
                }else if(lawsuit.getText().toString().equals("再审")){
                    ssjd = "4";
                }
            }

            if(!locus_standi.getText().toString().equals("请选择诉讼地位")){
                if(locus_standi.getText().toString().equals("原告")){
                    ssdw = "0";
                }else if(locus_standi.getText().toString().equals("被告")){
                    ssdw = "1";
                }else if(locus_standi.getText().toString().equals("上诉人")){
                    ssdw = "2";
                }else if(locus_standi.getText().toString().equals("被上诉人")){
                    ssdw = "3";
                }else if(locus_standi.getText().toString().equals("申请执行人")){
                    ssdw = "4";
                }else if(locus_standi.getText().toString().equals("被执行人")){
                    ssdw = "5";
                }else if(locus_standi.getText().toString().equals("申请人")){
                    ssdw = "6";
                }else if(locus_standi.getText().toString().equals("被申请人")){
                    ssdw = "7";
                }else if(locus_standi.getText().toString().equals("第三人")){
                    ssdw = "8";
                }else if(locus_standi.getText().toString().equals("其他")){
                    ssdw = "9";
                }
            }


        }

    }

    int ajlx;
    int ajfl;
    String id,mid;
    String cid;
    String ah_number;
    String sarq;
    String ay;
    String remarks;
    String wtr;
    String mlxdh;
    String mdsr;
    String mdfdsr;
    String mslbm;
    String mssbd;
    String dlf;
    String zjf;
    String ssjd;
    String ssdw;
    String badq;
    String police;
    String mprocuratorate;
    String mcourt;
    String detention;
    String sffs;
    public void postCaseRegister(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("上传中...");
        MainApi.getInstance(this).getpostcaseApi(id, cid, ajlx,ajfl,ah_number,sarq,ay,remarks, wtr,mlxdh,mdsr,
                mdfdsr, mslbm, mssbd,sffs , dlf,zjf,ssjd,ssdw,badq,police,mprocuratorate,mcourt,detention,create_date, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type== Constants.TYPE_SUCCESS){
                    loadingDialog.dismiss();
                    Toast.makeText(CaseRegisterActivity.this,"上传成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CaseRegisterActivity.this,MyCaseActivity.class);
                    intent.putExtra("cc",cc);
                    startActivity(intent);
                }else BaseApi.showErrMsg(CaseRegisterActivity.this,result);
            }
        });
    }

    public void postxgmycase(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("上传中...");
        MainApi.getInstance(this).getxgmycaseApi(mid, cid, ajlx,ajfl,ah_number,sarq,ay,remarks, wtr,mlxdh,mdsr,
                mdfdsr, mslbm, mssbd,sffs , dlf,zjf,ssjd,ssdw,badq,police,mprocuratorate,mcourt,detention,create_date, new GetResultCallBack() {
                    @Override
                    public void getResult(String result, int type) {
                        if(type== Constants.TYPE_SUCCESS){
                            loadingDialog.dismiss();
                            Toast.makeText(CaseRegisterActivity.this,"上传成功",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CaseRegisterActivity.this,MyCaseActivity.class);
                            intent.putExtra("cc",cc);
                            startActivity(intent);
                        }else BaseApi.showErrMsg(CaseRegisterActivity.this,result);
                    }
                });
    }

//    String fwnr;//服务内容
//    String fwdate;//服务时间
//    int fwtype;//0.口头法律咨询(人次) 1书面法律咨询(人次) 2代写法律文书(件)
//    String tgfwrc;//提供服务人次
//    String fwfy;//服务费用
    String bzsm ;//备注说明
    String create_date;//创建时间
//    public void postCase(){
//        loadingDialog.show();
//        loadingDialog.setLoadingContent("上传中...");
//        MainApi.getInstance(this).getpostcasezxApi(id, cid, fwnr,fwdate,ah_number,fwtype,tgfwrc,fwfy,
//                wtr, bzsm, create_date, new GetResultCallBack() {
//            @Override
//            public void getResult(String result, int type) {
//                if(type== Constants.TYPE_SUCCESS){
//                    loadingDialog.dismiss();
//                    Toast.makeText(CaseRegisterActivity.this,"上传成功",Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(CaseRegisterActivity.this, MyCaseActivity.class);
//                    startActivity(intent);
//                }else BaseApi.showErrMsg(CaseRegisterActivity.this,result);
//            }
//        });
//    }

    /**
     * 查询案号（有则+1，无则创建）
     */
    private void getHttp(){
        MainApi.getInstance(this).getanhaoApi(cid, ajlx,ssjd,new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type== Constants.TYPE_SUCCESS){
                    Toast.makeText(CaseRegisterActivity.this,"获取成功",Toast.LENGTH_SHORT).show();
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        ah_number =jsonObject.getString("caseNo");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.e("ah_number====>",""+ah_number);
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
                    create_date = format.format(new java.util.Date());//创建时间
                    sarq = date_of_cognizance.getText().toString();//收案日期
                    ay = brief.getText().toString();//案由
                    wtr = consignor.getText().toString();//委托人
                    mdsr = dsr.getText().toString();//对方当事人
                    mdfdsr = dfdsr.getText().toString();//对方当事人
                    mslbm = slbm.getText().toString();//受理部门
                    mssbd = ssbd.getText().toString();//诉讼标的
                    dlf = agency_fee.getText().toString();//代理费
                    zjf = grant.getText().toString();//杂费
//                    badq= shen.getText().toString()+city.getText().toString();//办案地区
                    police= public_security.getText().toString();//公安
                    mprocuratorate= procuratorate.getText().toString();//检查院
                    mcourt= court.getText().toString();//法院
                    detention= detention_house.getText().toString();//看守所
                    remarks= remark.getText().toString();//备注说明
                    postCaseRegister();
                }else BaseApi.showErrMsg(CaseRegisterActivity.this,result);
            }
        });
    }

    String str;
    AlertDialog.Builder builder;
    private void showsDialog(){
        builder = new AlertDialog.Builder(CaseRegisterActivity.this,AlertDialog.THEME_HOLO_LIGHT);
        builder.setTitle(str);
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

    int mYear,mMonth,mDay;

    /**
     * 选择城市
     */
    public void cityPickerChoose(){
        cityPicker = new CityPicker.Builder(CaseRegisterActivity.this).textSize(14)
                .title("城市选择")
                .titleBackgroundColor("#234Dfa")
                .confirTextColor("#000000")
                .cancelTextColor("#000000")
//                .onlyShowProvinceAndCity(true)//两级联动：省+市
                .province("山东省")
                .city("烟台市")
                .district("长岛县")
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(false)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .build();
        cityPicker.show();
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                String province = citySelected[0];
                String cityi = citySelected[1];
                String countyi = citySelected[2];
                shen.setText(province);
                shen.setTextColor(getResources().getColor(R.color.black));
                city.setText(cityi);
                city.setTextColor(getResources().getColor(R.color.black));
                county.setText(countyi);
                county.setTextColor(getResources().getColor(R.color.black));

                String p="";
                String s="";
                String c="";
                for(int i=0;i<cityalist.size();i++){
                    if(shen.getText().toString().equals(cityalist.get(i))){
                        p = parentidlist.get(i);
                        Log.e("==>",""+cityalist.get(i)+"+++"+parentidlist.get(i));
                    }
                }


                for(int i=0;i<cityalists.size();i++){
                    if(city.getText().toString().equals(cityalists.get(i)) && parentidlists.get(i).equals(p)){
                        s = idlists.get(i);
                        Log.e("city==>",""+cityalists.get(i)+"+++"+idlists.get(i));
                    }
                }

                for(int i=0;i<countylist.size();i++){
                    if(county.getText().toString().equals(countylist.get(i)) && parentidlistss.get(i).equals(s)){
                        c = idlistss.get(i);
                        Log.e("count==>",""+countylist.get(i)+"+++"+parentidlistss.get(i));
                    }
                }

                badq = p+","+s+","+c;
                Log.e("地区==>",""+badq);
            }
        });
    }

    private List<String> cityalist = new ArrayList<>();
    private List<String> parentidlist = new ArrayList<>();
    private void cxprovince(){
        MainApi.getInstance(this).getcxcityApi(2,new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type== Constants.TYPE_SUCCESS){
                    List<ModelCity.ResultBean> resultBeen = GsonUtil.fromJsonList(new Gson(),result,ModelCity.ResultBean.class);
                    citylist.addAll(resultBeen);
                    for(int i=0;i<citylist.size();i++){
                        String name = citylist.get(i).getName();
                        String id = citylist.get(i).getId();
                        cityalist.add(name);
                        parentidlist.add(id);
                    }

                    save5(parentidlist);
                    save4(cityalist);
                }else BaseApi.showErrMsg(CaseRegisterActivity.this,result);
            }
        });
    }


    private List<String> cityalists = new ArrayList<>();
    private List<String> idlists = new ArrayList<>();
    private List<String> parentidlists = new ArrayList<>();
    private void cxcity(){
        MainApi.getInstance(this).getcxcityApi(3,new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type== Constants.TYPE_SUCCESS){
                    List<ModelCity.ResultBean> resultBeen = GsonUtil.fromJsonList(new Gson(),result,ModelCity.ResultBean.class);
                    citylist.clear();
                    citylist.addAll(resultBeen);
                    for(int i=0;i<citylist.size();i++){
                        String name = citylist.get(i).getName();
                        String id = citylist.get(i).getId();
                        String parent_id = citylist.get(i).getParent_id();
                        cityalists.add(name);
                        idlists.add(id);
                        parentidlists.add(parent_id);
                    }
                    save3(idlists);
                    save2(cityalists);
                }else BaseApi.showErrMsg(CaseRegisterActivity.this,result);
            }
        });
    }

    private List<String> countylist = new ArrayList<>();
    private List<String> idlistss = new ArrayList<>();
    private List<String> parentidlistss = new ArrayList<>();
    private void cxcounty(){
        MainApi.getInstance(this).getcxcityApi(4,new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type== Constants.TYPE_SUCCESS){
                    List<ModelCity.ResultBean> resultBeen = GsonUtil.fromJsonList(new Gson(),result,ModelCity.ResultBean.class);
                    citylist.clear();
                    citylist.addAll(resultBeen);
                    for(int i=0;i<citylist.size();i++){
                        String name = citylist.get(i).getName();
                        String id = citylist.get(i).getId();
                        String parent_id = citylist.get(i).getParent_id();
                        countylist.add(name);
                        idlistss.add(id);
                        parentidlistss.add(parent_id);
                    }

                    save(idlistss);
                    save1(countylist);
                }else BaseApi.showErrMsg(CaseRegisterActivity.this,result);
            }
        });
    }


    private final static String FILE1_NAME = "area.txt"; // 设置文件的名称
    private final static String FILE2_NAME = "area2.txt";
    private final static String FILE3_NAME = "area3.txt";
    private final static String FILE4_NAME = "numb1.txt";
    private final static String FILE5_NAME = "numb2.txt";
    private final static String FILE6_NAME = "numb3.txt";
    private StringBuffer read() {
        FileInputStream in = null;
        Scanner s = null;
        StringBuffer sb = new StringBuffer();
        try {
            in = super.openFileInput(FILE1_NAME);
            s = new Scanner(in);
            while (s.hasNext()) {
                sb.append(s.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb;
    }

    private StringBuffer read1() {
        FileInputStream in = null;
        Scanner s = null;
        StringBuffer sb = new StringBuffer();
        try {
            in = super.openFileInput(FILE2_NAME);
            s = new Scanner(in);
            while (s.hasNext()) {
                sb.append(s.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb;
    }

    private StringBuffer read2() {
        FileInputStream in = null;
        Scanner s = null;
        StringBuffer sb = new StringBuffer();
        try {
            in = super.openFileInput(FILE3_NAME);
            s = new Scanner(in);
            while (s.hasNext()) {
                sb.append(s.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb;
    }

    private StringBuffer read3() {
        FileInputStream in = null;
        Scanner s = null;
        StringBuffer sb = new StringBuffer();
        try {
            in = super.openFileInput(FILE4_NAME);
            s = new Scanner(in);
            while (s.hasNext()) {
                sb.append(s.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb;
    }

    private StringBuffer read4() {
        FileInputStream in = null;
        Scanner s = null;
        StringBuffer sb = new StringBuffer();
        try {
            in = super.openFileInput(FILE5_NAME);
            s = new Scanner(in);
            while (s.hasNext()) {
                sb.append(s.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb;
    }

    private StringBuffer read5() {
        FileInputStream in = null;
        Scanner s = null;
        StringBuffer sb = new StringBuffer();
        try {
            in = super.openFileInput(FILE6_NAME);
            s = new Scanner(in);
            while (s.hasNext()) {
                sb.append(s.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb;
    }


    private void save(List<String> data) {
        FileOutputStream out = null;
        PrintStream ps = null;
        try {
            out = super.openFileOutput(FILE1_NAME, Context.MODE_PRIVATE);
            ps = new PrintStream(out);
            ps.println(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                    ps.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void save1(List<String> data) {
        FileOutputStream out = null;
        PrintStream ps = null;
        try {
            out = super.openFileOutput(FILE2_NAME, Context.MODE_PRIVATE);
            ps = new PrintStream(out);
            ps.println(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                    ps.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void save2(List<String> data) {
        FileOutputStream out = null;
        PrintStream ps = null;
        try {
            out = super.openFileOutput(FILE3_NAME, Context.MODE_PRIVATE);
            ps = new PrintStream(out);
            ps.println(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                    ps.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void save3(List<String> data) {
        FileOutputStream out = null;
        PrintStream ps = null;
        try {
            out = super.openFileOutput(FILE4_NAME, Context.MODE_PRIVATE);
            ps = new PrintStream(out);
            ps.println(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                    ps.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void save4(List<String> data) {
        FileOutputStream out = null;
        PrintStream ps = null;
        try {
            out = super.openFileOutput(FILE5_NAME, Context.MODE_PRIVATE);
            ps = new PrintStream(out);
            ps.println(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                    ps.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void save5(List<String> data) {
        FileOutputStream out = null;
        PrintStream ps = null;
        try {
            out = super.openFileOutput(FILE6_NAME, Context.MODE_PRIVATE);
            ps = new PrintStream(out);
            ps.println(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                    ps.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //判断文件是否存在
    public boolean fileIsExists(String strFile) {
        try {
            File f=new File(strFile);
            if(!f.exists()) {
                return false;
            }
        }
        catch (Exception e)
        {
            return false;
        }

        return true;
    }


    class newtextwatch implements TextWatcher{
        private EditText edit;
        public newtextwatch(EditText editText){
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
