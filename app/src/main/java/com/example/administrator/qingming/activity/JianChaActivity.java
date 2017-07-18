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

public class JianChaActivity extends Activity {
    private static final int DATE_DIALOG = 0;
    private static final int DATE_DIALOY = 1;
    LoadingDialog loadingDialog;
    int mYear,mMonth,mDay;
    private String ah_number,wtr,dfdsr,ay;
    private TextView one,two,three,fore,name;
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
                                    if(!TextUtils.isEmpty(phone_num.getText())){
                                        if(!time_start.getText().toString().equals("起始时间")&&
                                                !time_finish.getText().toString().equals("截止时间")){
                                            badd = baan_area.getText().toString();
                                            bajg = bananjiguan.getText().toString();
                                            bm = bumeng.getText().toString();
                                            cbr = chengbanren.getText().toString();
                                            tel = phone_num.getText().toString();
                                            bz = edit_log.getText().toString();
                                            scqs_star = time_start.getText().toString();
                                            scqs_end= time_finish.getText().toString();
                                            postjiancha();
                                        }else {
                                            Toast.makeText(JianChaActivity.this,"请填写联系电话",Toast.LENGTH_SHORT).show();
                                        }
                                    }else {
                                        Toast.makeText(JianChaActivity.this,"请填写联系电话",Toast.LENGTH_SHORT).show();
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
                    showDialog(DATE_DIALOG);
                    break;
                case R.id.time_finish:
                    showDialog(DATE_DIALOY);
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
                    xgjiancha();
                    break;
                case R.id.back_btn:
                    finish();
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
        time_start.setText(new StringBuffer().append(mYear).append("-").append(mMonth + 1).append("-").
                append(mDay).append(" "));
        time_start.setTextColor(getResources().getColor(R.color.black));
    }
    public void finplay() {
        time_finish.setText(new StringBuffer().append(mYear).append("-").append(mMonth + 1).append("-").
                append(mDay).append(" "));
        time_finish.setTextColor(getResources().getColor(R.color.black));
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
