package com.example.administrator.qingming.work;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.example.administrator.qingming.R;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/5/8 0008.
 */

public class AddAffairActivity extends Activity {
    private String initStartDateTime ; // 初始化开始时间
    private String initEndDateTime ; // 初始化结束时间
    private ImageView addadress,backbtn;
    private TextView starttime,finishtime,affair,work,timetx,gk;
    private Button submitbtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_affair);

        initView();
        checkPermisson();
    }

    private void initView(){
        addadress = (ImageView) findViewById(R.id.add_adress);
        backbtn = (ImageView) findViewById(R.id.back_btn);
        starttime = (TextView) findViewById(R.id.start_time);
        finishtime = (TextView) findViewById(R.id.finish_time);
        affair = (TextView) findViewById(R.id.affair);
        work = (TextView) findViewById(R.id.work);
        timetx = (TextView) findViewById(R.id.time_tx);
        gk = (TextView) findViewById(R.id.gk);
        submitbtn = (Button) findViewById(R.id.submit_btn);

        backbtn.setOnClickListener(onClickListener);
        addadress.setOnClickListener(onClickListener);
        affair.setOnClickListener(onClickListener);
        work.setOnClickListener(onClickListener);
        starttime.setOnClickListener(onClickListener);
        finishtime.setOnClickListener(onClickListener);
        timetx.setOnClickListener(onClickListener);
        gk.setOnClickListener(onClickListener);
        submitbtn.setOnClickListener(onClickListener);
    }



    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){
                case R.id.work:
                    showWorkDialog();
                    break;
                case R.id.affair:
                    showDialog();
                    break;
                case R.id.start_time:
                    starttime.setText(initStartDateTime);
                    starttime.setTextColor(getResources().getColor(R.color.black));
                    DateTimePickDialogUtil dateTimePickDialogUtil = new DateTimePickDialogUtil(initStartDateTime,AddAffairActivity.this);
                    dateTimePickDialogUtil.dateTimePicKDialog(starttime);
                    break;
                case R.id.finish_time:
                    finishtime.setText(initStartDateTime);
                    finishtime.setTextColor(getResources().getColor(R.color.black));
                    DateTimePickDialogUtil mdateTimePickDialogUtil = new DateTimePickDialogUtil(initEndDateTime,AddAffairActivity.this);
                    mdateTimePickDialogUtil.dateTimePicKDialog(finishtime);
                    break;
                case R.id.back_btn:
                    finish();
                    break;
                case R.id.time_tx:
                    showTimeDialog();
                    break;
                case R.id.gk:
                    showGKDialog();
                    break;
                case R.id.add_adress:
                    intent=new Intent("com.baidu.location.f");
                    startService(intent);
                    Log.i("1", "1");
                    break;
                case R.id.submit_btn:
                    break;
            }
        }
    };



    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(AddAffairActivity.this);
        builder.setTitle("选择事件类型");
        final String[] xz = {"个人日历","公开日历","协同日历"};
        builder.setItems(xz, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                affair.setText(xz[which]);
                affair.setTextColor(getResources().getColor(R.color.black));
            }
        });
        builder.show();
    }

    final String[] xz = {"客户拜访","商务谈判","法律质询","客户拜访","商务谈判","法律质询","客户拜访","商务谈判","法律质询"};
    private void showWorkDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(AddAffairActivity.this);
        builder.setTitle("选择工作类别");
        builder.setItems(xz, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                work.setText(xz[which]);
                work.setTextColor(getResources().getColor(R.color.black));
            }
        });
        builder.show();
    }

    private void showTimeDialog(){
        final String[] ti = {"不提醒","1分钟","2分钟","3分钟","商务谈判","法律质询","客户拜访","商务谈判","法律质询"};
        AlertDialog.Builder builder = new AlertDialog.Builder(AddAffairActivity.this);
        builder.setTitle("提醒时间");
        builder.setItems(ti, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                timetx.setText(ti[which]);
                timetx.setTextColor(getResources().getColor(R.color.black));
            }
        });
        builder.show();
    }

    private void showGKDialog(){
        final String[] gkk = {"公开","不公开"};
        AlertDialog.Builder builder = new AlertDialog.Builder(AddAffairActivity.this);
        builder.setTitle("请选择");
        builder.setItems(gkk, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gk.setText(gkk[which]);
                gk.setTextColor(getResources().getColor(R.color.black));
            }
        });
        builder.show();
    }


    /**
     * 动态权限的请求
     */
    public void checkPermisson() {
        if (Build.VERSION.SDK_INT >=  Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this,//上下文
                    new String[]{
                            // 1、获取手机状态：
                    Manifest.permission.READ_PHONE_STATE,

                   // 2、获取位置信息：
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,

                    //3、读写SD卡：
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE},//权限数组
                    1001);
        }
    }
    /**
     * 动态权限的回调函数
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case 1001:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }

    }
}
