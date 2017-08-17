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
import com.example.administrator.qingming.work.DatePickDialogUtil;
import com.example.administrator.qingming.work.DateTimePickDialogUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/5 0005.
 */

public class JianChaActivity extends Activity {
    private String initStartDateTime ; // 初始化开始时间
    LoadingDialog loadingDialog;
    int mYear,mMonth,mDay;
    private String ah_number,wtr,dfdsr,ay,dsr;
    private TextView one,two,three,fore,name,five;
    private ImageView backbtn;
    private Button submit,xuigaibtn,delbtn;
    private LinearLayout aa;
    private String create_date,update_date;

    private EditText baan_area,bananjiguan,bumeng,chengbanren,phone_num,edit_log;
    private TextView time_start,time_finish;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiancha);

        SharedPreferences sharedPreferences = getSharedPreferences("qinmin", Context.MODE_PRIVATE);
        id = sharedPreferences.getString("id","");
        cid = sharedPreferences.getString("cid","");

        initView();
        Bundle bundle = getIntent().getExtras();
        glid = bundle.getString("id","");
        ah_number = bundle.getString("ah_number","");
        wtr = bundle.getString("wtr","");
        dsr = bundle.getString("dsr","");
        dfdsr = bundle.getString("dfdsr","");
        ay = bundle.getString("ay","");
        int zid = bundle.getInt("zid");

        if(zid == 1){
            submit.setVisibility(View.VISIBLE);
            submit.setOnClickListener(onclicklisten);
        }else if(zid == 2){
            aid = bundle.getString("aid","");
            Log.e("aid-------->",""+aid);
            bananjiguan.setText(bundle.getString("bajg",""));
            edit_log.setText(bundle.getString("bzsm",""));
            baan_area.setText(bundle.getString("badd",""));
            bumeng.setText(bundle.getString("bm",""));
            chengbanren.setText(bundle.getString("cbr",""));
            phone_num.setText(bundle.getString("tel",""));
            time_start.setText(bundle.getString("scqs_star",""));
            time_finish.setText(bundle.getString("scqs_end",""));
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

        create_date = mYear+"-"+mMonth+"-"+mDay;
        update_date = mYear+"-"+mMonth+"-"+mDay;
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
        baan_area= (EditText) findViewById(R.id.baan_area);
        bananjiguan= (EditText) findViewById(R.id.bananjiguan);
        bumeng= (EditText) findViewById(R.id.bumeng);
        chengbanren= (EditText) findViewById(R.id.chengbanren);
        phone_num= (EditText) findViewById(R.id.phone_num);
        edit_log= (EditText) findViewById(R.id.edit_log);
        time_start = (TextView) findViewById(R.id.time_start);
        time_finish = (TextView) findViewById(R.id.time_finish);

        backbtn.setOnClickListener(onclicklisten);
        time_start.setOnClickListener(onclicklisten);
        time_finish.setOnClickListener(onclicklisten);


        baan_area.addTextChangedListener(new newtextWatcher(baan_area));
        bananjiguan.addTextChangedListener(new newtextWatcher(bananjiguan));
        bumeng.addTextChangedListener(new newtextWatcher(bumeng));
        chengbanren.addTextChangedListener(new newtextWatcher(chengbanren));
        phone_num.addTextChangedListener(new newtextWatcher(phone_num));
        edit_log.addTextChangedListener(new newtextWatcher(edit_log));
    }

    View.OnClickListener onclicklisten = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.submit_btn:
                    if(!TextUtils.isEmpty(baan_area.getText())){
                        if(!TextUtils.isEmpty(bananjiguan.getText())){
                            if(!TextUtils.isEmpty(bumeng.getText())){
                                if(!TextUtils.isEmpty(chengbanren.getText())){
                                    if(!time_start.getText().toString().equals("请选择时间")&&
                                            !time_finish.getText().toString().equals("请选择时间") ){
                                        badd = baan_area.getText().toString();
                                        bajg = bananjiguan.getText().toString();
                                        bm = bumeng.getText().toString();
                                        cbr = chengbanren.getText().toString();
                                        tel = phone_num.getText().toString();
                                        bz = edit_log.getText().toString();
                                        scqs_star = time_start.getText().toString();
                                        scqs_end = time_finish.getText().toString();
                                        //判断时间
                                        int result = 0;
                                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                        try {
                                            Date date1 = format.parse(time_start.getText().toString());
                                            Date date2 = format.parse(time_finish.getText().toString());
                                            result = date1.compareTo(date2);
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                        if (result ==0) {
                                            Toast.makeText(JianChaActivity.this, "时间格式错误", Toast.LENGTH_SHORT).show();
                                        } else if ( result < 0 ) {
                                            //判断手机号输入
                                            if(!TextUtils.isEmpty(phone_num.getText())){
                                                if(isMobileNO(phone_num.getText().toString())){
                                                    postjiancha();
                                                }else {
                                                    Toast.makeText(JianChaActivity.this,"联系电话格式错误",Toast.LENGTH_SHORT).show();
                                                }
                                            }else {
                                                postjiancha();
                                            }
                                        }else {
                                            Toast.makeText(JianChaActivity.this, "时间格式错误", Toast.LENGTH_SHORT).show();
                                        }
                                    }else {
                                        Toast.makeText(JianChaActivity.this,"请选择时间",Toast.LENGTH_SHORT).show();
                                    }
                                }else {
                                    Toast.makeText(JianChaActivity.this,"请填写承办人",Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(JianChaActivity.this,"请填写部门",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(JianChaActivity.this,"请填写办案机关",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(JianChaActivity.this,"请填写办案地点",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.time_start:
                    time_start.setText(initStartDateTime);
                    time_start.setTextColor(getResources().getColor(R.color.black));
                    DatePickDialogUtil dateTimePickDialogUtil = new DatePickDialogUtil(initStartDateTime,JianChaActivity.this);
                    dateTimePickDialogUtil.dateTimePicKDialog(time_start);
                    break;
                case R.id.time_finish:
                    time_finish.setText(initStartDateTime);
                    time_finish.setTextColor(getResources().getColor(R.color.black));
                    DatePickDialogUtil dateTimePickDialogUtil1 = new DatePickDialogUtil(initStartDateTime,JianChaActivity.this);
                    dateTimePickDialogUtil1.dateTimePicKDialog(time_finish);
                    break;
                case R.id.del_btn:
                    deljiancha();
                    break;
                case R.id.xuigai_btn:
                    badd = baan_area.getText().toString();
                    bajg = bananjiguan.getText().toString();
                    bm = bumeng.getText().toString();
                    cbr = chengbanren.getText().toString();
                    tel = phone_num.getText().toString();
                    bz = edit_log.getText().toString();
                    scqs_star = time_start.getText().toString();
                    scqs_end= time_finish.getText().toString();
                    //判断时间
                    int result = 0;
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date date1 = format.parse(time_start.getText().toString());
                        Date date2 = format.parse(time_finish.getText().toString());
                        result = date1.compareTo(date2);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (result ==0) {
                        Toast.makeText(JianChaActivity.this, "时间格式错误", Toast.LENGTH_SHORT).show();
                    } else if ( result < 0 ) {
                        //判断手机号输入
                        if(!TextUtils.isEmpty(phone_num.getText())){
                            if(isMobileNO(phone_num.getText().toString())){
                                xgjiancha();
                            }else {
                                Toast.makeText(JianChaActivity.this,"联系电话格式错误",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            xgjiancha();
                        }
                    }else {
                        Toast.makeText(JianChaActivity.this, "时间格式错误", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.back_btn:
                    finish();
                    break;
            }
        }
    };


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

    String id;
    String cid;
    String glid;
    String bajg;
    String bz;
    String badd;
    String bm;
    String cbr;
    String tel;
    String scqs_star;
    String scqs_end;
    private void postjiancha(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("上传中...");
        MainApi.getInstance(this).postaddjianchaApi(id,cid,glid,bajg,bz,badd,bm,cbr,tel,
                scqs_star,scqs_end,create_date,new GetResultCallBack() {
                    @Override
                    public void getResult(String result, int type) {
                        if(type == Constants.TYPE_SUCCESS){
                            loadingDialog.dismiss();
                            Toast.makeText(JianChaActivity.this,"上传成功",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(JianChaActivity.this, CreateWorkActivity.class);
                            intent.putExtra("type",3);
                            intent.putExtra("id",glid);
                            intent.putExtra("ah_number",ah_number);
                            intent.putExtra("wtr",wtr);
                            intent.putExtra("dsr",dsr);
                            intent.putExtra("dfdsr",dfdsr);
                            intent.putExtra("ay",ay);
                            startActivity(intent);
                            finish();
                        }else BaseApi.showErrMsg(JianChaActivity.this,result);
                    }
                });
    }

    private void xgjiancha(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("上传中...");
        MainApi.getInstance(this).getxgjianchaApi(aid,bajg,bz,badd,bm,cbr,tel,
                scqs_star,scqs_end,update_date,new GetResultCallBack() {
                    @Override
                    public void getResult(String result, int type) {
                        if(type == Constants.TYPE_SUCCESS){
                            loadingDialog.dismiss();
                            Toast.makeText(JianChaActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(JianChaActivity.this, CreateWorkActivity.class);
                            intent.putExtra("type",3);
                            intent.putExtra("id",glid);
                            intent.putExtra("ah_number",ah_number);
                            intent.putExtra("wtr",wtr);
                            intent.putExtra("dsr",dsr);
                            intent.putExtra("dfdsr",dfdsr);
                            intent.putExtra("ay",ay);
                            startActivity(intent);
                            finish();
                        }else BaseApi.showErrMsg(JianChaActivity.this,result);
                    }
                });
    }
    /**
     * 删除
     */
    String aid;
    private void deljiancha(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("删除中...");
        MainApi.getInstance(this).getdeljianchaApi(aid,new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type == Constants.TYPE_SUCCESS){
                    loadingDialog.dismiss();
                    Toast.makeText(JianChaActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(JianChaActivity.this, CreateWorkActivity.class);
                    intent.putExtra("type",3);
                    intent.putExtra("id",glid);
                    intent.putExtra("ah_number",ah_number);
                    intent.putExtra("wtr",wtr);
                    intent.putExtra("dsr",dsr);
                    intent.putExtra("dfdsr",dfdsr);
                    intent.putExtra("ay",ay);
                    startActivity(intent);
                    finish();
                }else BaseApi.showErrMsg(JianChaActivity.this,result);
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
