package com.example.administrator.qingming.news.casedetails;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
import java.util.Calendar;

/**
 * Created by Administrator on 2017/5/16 0016.
 */

public class CreateWorkLogActivity extends Activity {
    private static final int DATE_DIALOG = 0;
    private static final int DATE_DIALOY = 1;
    private EditText edit_log;
    int mYear,mMonth,mDay;
    private String initStartDateTime ; // 初始化开始时间
    private String initEndDateTime ; // 初始化结束时间
    private ImageView backbtn;
    private TextView logtype,logsort,logstart,logfinish,createshoose;
    private TextView one,two,three,fore,name;
    private Button submit;
    LoadingDialog loadingDialog;
    private String ah_number,wtr,dfdsr,ay;

    private int type;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_work_log);

        SharedPreferences sharedPreferences = getSharedPreferences("qinmin", Context.MODE_PRIVATE);
        id = sharedPreferences.getString("id","");
        cid = sharedPreferences.getString("cid","");

        initView();
        getBundle();
        //获取日期
        final Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);

        create_date = mYear+"-"+mMonth+"-"+mDay;
    }

    //得到上个页面传递的数据,根据type的值拿到对应页面
    private void getBundle(){
        Bundle bundle = getIntent().getExtras();
        glid = bundle.getString("id","");
        ah_number = bundle.getString("ah_number","");
        wtr = bundle.getString("wtr","");
        dfdsr = bundle.getString("dfdsr","");
        ay = bundle.getString("ay","");
        type = bundle.getInt("type");
        Log.e("=============>",""+type);

        one.setText(ay);
        two.setText(ah_number);
        three.setText(wtr);
        fore.setText(dfdsr);
    }

    private void initView() {
        name = (TextView) findViewById(R.id.name);
        backbtn = (ImageView) findViewById(R.id.back_btn);
//        logtype = (TextView) findViewById(R.id.log_type);
        logsort = (TextView) findViewById(R.id.log_sort);
        logstart = (TextView) findViewById(R.id.log_start);
        logfinish = (TextView) findViewById(R.id.log_finish);
        loadingDialog = new LoadingDialog(this);
        submit = (Button) findViewById(R.id.submit_btn);
        edit_log = (EditText) findViewById(R.id.edit_log);
        one = (TextView) findViewById(R.id.one);
        two = (TextView) findViewById(R.id.two);
        three = (TextView) findViewById(R.id.three);
        fore = (TextView) findViewById(R.id.fore);

//        createshoose = (TextView) findViewById(R.id.create_choose);

        backbtn.setOnClickListener(onClickListener);
//        logtype.setOnClickListener(onClickListener);
        logsort.setOnClickListener(onClickListener);
        logstart.setOnClickListener(onClickListener);
        logfinish.setOnClickListener(onClickListener);
        submit.setOnClickListener(onClickListener);
        edit_log.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                bz = s.toString();
            }
        });
//        createshoose.setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){
                case R.id.back_btn:
                    finish();
                    break;
//                case R.id.log_type:
//                    title = "选择日志类型";
//                    showDialog();
//                    final String[] type = {"业务工作日志","所务工作日志","客户开发"};
//                    builder.setItems(type, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            logtype.setText(type[which]);
//                        }
//                    }).show();
//                    break;
                case R.id.log_sort:
                    title = "选择日志类别";
                    showDialog();
                    final String[] sort = {"客户拜访","商务谈判","法律咨询","案例/资料检索","法律文书起草/修改","调查取证",
                            "案件研讨","刑案律师会见","诉讼/仲裁出庭","信访/社区/法援律师值"};
                    builder.setItems(sort, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            logsort.setText(sort[which]);
                        }
                    }).show();
                    break;
                case R.id.log_start:
                    showDialog(DATE_DIALOG);
                    break;
                case R.id.log_finish:
                    showDialog(DATE_DIALOY);
                    break;
                case R.id.submit_btn:
                    start_date = logstart.getText().toString();
                    stop_date = logfinish.getText().toString();
                    if(logsort.getText().toString().equals("客户拜访")){
                        log_type = "1";
                    }else if(logsort.getText().toString().equals("商务谈判")){
                        log_type = "2";
                    }else if(logsort.getText().toString().equals("法律咨询")){
                        log_type = "3";
                    }else if(logsort.getText().toString().equals("案例/资料检索")){
                        log_type = "4";
                    }else if(logsort.getText().toString().equals("法律文书起草/修改")){
                        log_type = "5";
                    }else if(logsort.getText().toString().equals("调查取证")){
                        log_type = "6";
                    }else if(logsort.getText().toString().equals("刑案律师会见")){
                        log_type = "7";
                    }else if(logsort.getText().toString().equals("诉讼/仲裁出庭")){
                        log_type = "8";
                    }else if(logsort.getText().toString().equals("信访/社区/法援律师值")){
                        log_type = "9";
                    }
                    postHttp();
                    break;
//                case R.id.create_choose:
//                    intent = new Intent(CreateWorkLogActivity.this,CaseManageActivity.class);
//                    startActivity(intent);
//                    break;

            }
        }
    };


    /**
     * 添加日志
     */
    String id;
    String cid;
    String glid;
    String log_type;
    String bz="";
    String start_date;
    String stop_date;
    String create_date;
    private void postHttp(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("上传中...");
        MainApi.getInstance(this).postaddrizhiApi(id, cid, glid, log_type, bz, start_date, stop_date,
                create_date , new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type == Constants.TYPE_SUCCESS){
                    loadingDialog.dismiss();
                    Toast.makeText(CreateWorkLogActivity.this,"上传成功",Toast.LENGTH_SHORT).show();
                    finish();
                }else BaseApi.showErrMsg(CreateWorkLogActivity.this,result);
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

    /**
     * 设置日期 利用StringBuffer追加
     */
    public void display() {
        logstart.setText(new StringBuffer().append(mYear).append("-").append(mMonth + 1).append("-").
                append(mDay).append(" "));
        logstart.setTextColor(getResources().getColor(R.color.black));
    }
    public void finplay() {
        logfinish.setText(new StringBuffer().append(mYear).append("-").append(mMonth + 1).append("-").
                append(mDay).append(" "));
        logfinish.setTextColor(getResources().getColor(R.color.black));
    }

    String title;
    AlertDialog.Builder builder;
    private void showDialog() {
        builder= new AlertDialog.Builder(CreateWorkLogActivity.this);
        builder.setTitle(title);
    }



}
