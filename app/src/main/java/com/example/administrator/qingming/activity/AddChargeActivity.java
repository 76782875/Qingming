package com.example.administrator.qingming.activity;

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
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.api.BaseApi;
import com.example.administrator.qingming.api.MainApi;
import com.example.administrator.qingming.dialog.LoadingDialog;
import com.example.administrator.qingming.interfaces.GetResultCallBack;
import com.example.administrator.qingming.model.Constants;
import com.example.administrator.qingming.news.casedetails.CaseDetailsActivity;
import com.example.administrator.qingming.work.AddCaseActivity;
import com.example.administrator.qingming.work.CaseManageActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Administrator on 2017/6/30 0030.
 */

public class AddChargeActivity extends Activity {
    private TextView ah,date,fylx,zffs;
    private EditText jyje,beizhu;
    private Button add,tg,zf,back_sf;
    private LoadingDialog loading;
    private String ah_number;
    int mYear,mMonth,mDay;
    private static final int DATE_DIALOG = 0;
    private ImageView back_btn;
    int index;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_charge);

        SharedPreferences sharedPreferences = getSharedPreferences("qinmin", Context.MODE_PRIVATE);
        id = sharedPreferences.getString("id","");
        create_name = sharedPreferences.getString("name","");
        cid = sharedPreferences.getString("cid","");

        initView();
        Bundle bundle = getIntent().getExtras();
        index = bundle.getInt("index");
        if(index == 1){
            glid = bundle.getString("id","");
            ah_number = bundle.getString("ah_number","");
            add.setVisibility(View.VISIBLE);
            add.setOnClickListener(onclick);

            jyje.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    sfje = s.toString();
                }
            });
            beizhu.addTextChangedListener(new TextWatcher() {
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
            date.setOnClickListener(onclick);
            fylx.setOnClickListener(onclick);
            zffs.setOnClickListener(onclick);
        }else {
            sid = bundle.getString("id","");
            ah.setText(bundle.getString("an",""));
            date.setText(bundle.getString("skrq",""));
            jyje.setText(""+bundle.getDouble("sf"));
            fylx.setText(bundle.getString("fylx",""));
            zffs.setText(bundle.getString("sffs",""));
            beizhu.setText(bundle.getString("bz",""));
            int sftag = bundle.getInt("sftag");
            Log.e("====>",""+sftag);
            if(sftag == 0){
                tg.setVisibility(View.VISIBLE);
                zf.setVisibility(View.VISIBLE);
                tg.setOnClickListener(onclick);
                zf.setOnClickListener(onclick);
                jyje.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        sfje = s.toString();
                    }
                });
                beizhu.addTextChangedListener(new TextWatcher() {
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
                date.setOnClickListener(onclick);
                fylx.setOnClickListener(onclick);
                zffs.setOnClickListener(onclick);
            }else if(sftag == 1){
                back_sf.setVisibility(View.VISIBLE);
                back_sf.setOnClickListener(onclick);
                //设置不可编辑状态；
                jyje.setFocusable(false);
                jyje.setFocusableInTouchMode(false);
                beizhu.setFocusable(false);
                beizhu.setFocusableInTouchMode(false);
            }else if(sftag == 2){
                back_sf.setVisibility(View.VISIBLE);
                back_sf.setOnClickListener(onclick);
                //设置不可编辑状态；
                jyje.setFocusable(false);
                jyje.setFocusableInTouchMode(false);
                beizhu.setFocusable(false);
                beizhu.setFocusableInTouchMode(false);
            }

        }


//        //获取日期
//        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy/MM/dd    hh:mm:ss");
//        create_time = sDateFormat.format(new java.util.Date());

        //获取日期
        final Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);

    }

    private void initView() {
        back_btn = (ImageView) findViewById(R.id.back_btn);
        ah = (TextView) findViewById(R.id.ah);
        date = (TextView) findViewById(R.id.date);
        fylx = (TextView) findViewById(R.id.fylx);
        zffs = (TextView) findViewById(R.id.zffs);
        jyje = (EditText) findViewById(R.id.jyje);
        beizhu = (EditText) findViewById(R.id.beizhu);
        add = (Button) findViewById(R.id.add);
        tg = (Button) findViewById(R.id.tg);
        zf = (Button) findViewById(R.id.zf);
        back_sf = (Button) findViewById(R.id.back_sf);
        loading = new LoadingDialog(this);

        back_btn.setOnClickListener(onclick);
    }

    View.OnClickListener onclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back_btn:
                    finish();
                    break;
                case R.id.fylx:
                    showDialog();
                    break;
                case R.id.add:
                    create_time = date.getText().toString();
                    if(fylx.getText().toString().equals("预收款")){
                        mfylx = "1";
                    }else if(fylx.getText().toString().equals("追加费用")){
                        mfylx = "2";
                    }else if(fylx.getText().toString().equals("尾款")){
                        mfylx = "3";
                    }else {
                        mfylx = "4";
                    }

                    if(zffs.getText().toString().equals("现金")){
                        sflx = "1";
                    }else {
                        sflx ="2";
                    }
                    postHttp();
                    break;
                case R.id.tg:
                    if(fylx.getText().toString().equals("预收款")){
                        mfylx = "1";
                    }else if(fylx.getText().toString().equals("追加费用")){
                        mfylx = "2";
                    }else if(fylx.getText().toString().equals("尾款")){
                        mfylx = "3";
                    }else {
                        mfylx = "4";
                    }

                    if(zffs.getText().toString().equals("现金")){
                        sflx = "1";
                    }else {
                        sflx ="2";
                    }
                    zt = "2";
                    audit_id = id;
                    audit_name =create_name;
                    audit_time = date.getText().toString();
                    postxgHttp();
                    break;
                case R.id.zf:
                    if(fylx.getText().toString().equals("预收款")){
                        mfylx = "1";
                    }else if(fylx.getText().toString().equals("追加费用")){
                        mfylx = "2";
                    }else if(fylx.getText().toString().equals("尾款")){
                        mfylx = "3";
                    }else {
                        mfylx = "4";
                    }

                    if(zffs.getText().toString().equals("现金")){
                        sflx = "1";
                    }else {
                        sflx ="2";
                    }
                    zt = "3";
                    audit_id = id;
                    audit_name =create_name;
                    audit_time = date.getText().toString();
                    postxgHttp();
                    break;
                case R.id.back_sf:
                    Intent intent = new Intent(AddChargeActivity.this,ShoufeiActivity.class);
                    startActivity(intent);
                    break;
                case R.id.date:
                    showDialog(DATE_DIALOG);
                    break;
                case R.id.zffs:
                    showDialogs();
                    break;
            }
        }
    };

    String[] fylxs = {"预收款","追加费用","尾款","退款"};
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(AddChargeActivity.this,AlertDialog.THEME_HOLO_LIGHT);
        builder.setTitle("请选择案件分类");
        builder.setItems(fylxs, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                fylx.setText(fylxs[i]);
                Log.e("fylxs",""+fylx.getText().toString());
                fylx.setTextColor(getResources().getColor(R.color.black));
            }
        });
        builder.show();
    }

    String[] zffss = {"现金","刷卡"};
    private void showDialogs(){
        AlertDialog.Builder builder = new AlertDialog.Builder(AddChargeActivity.this,AlertDialog.THEME_HOLO_LIGHT);
        builder.setTitle("请选择案件分类");
        builder.setItems(zffss, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                zffs.setText(zffss[i]);
                Log.e("fylxs",""+zffs.getText().toString());
                zffs.setTextColor(getResources().getColor(R.color.black));
            }
        });
        builder.show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG:
                return new DatePickerDialog(this,onDateSetListener, mYear, mMonth, mDay);
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

    /**
     * 设置日期 利用StringBuffer追加
     */
    public void display() {
        date.setText(new StringBuffer().append(mYear).append("-").append(mMonth + 1).append("-").
                append(mDay).append(" "));
        date.setTextColor(getResources().getColor(R.color.black));
    }

    String id;
    String cid;
    String glid;
    String sfje;
    String sflx;
    String mfylx;
    String create_time;
    String create_name;
    String bz ;
    private void postHttp(){
        loading.show();
        loading.setLoadingContent("上传中...");
        MainApi.getInstance(this).getaddshoufeiApi(id, cid, glid, sfje, bz, sflx, mfylx,
                create_time, create_name, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type == Constants.TYPE_SUCCESS){
                    loading.dismiss();
                    Toast.makeText(AddChargeActivity.this,"上传成功",Toast.LENGTH_SHORT).show();
                    finish();
                }else BaseApi.showErrMsg(AddChargeActivity.this,result);
            }
        });
    }


    String  sid;
    String zt;
    String audit_id;
    String audit_name;
    String audit_time;
    private void postxgHttp(){
        loading.show();
        loading.setLoadingContent("上传中...");
        MainApi.getInstance(this).getxgshoufeixqApi(sid, sfje, sflx, bz, mfylx, zt, audit_id,
                audit_name,audit_time, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type == Constants.TYPE_SUCCESS){
                    loading.dismiss();
                    Toast.makeText(AddChargeActivity.this,"上传成功",Toast.LENGTH_SHORT).show();
                    finish();
                }else BaseApi.showErrMsg(AddChargeActivity.this,result);
            }
        });
    }
}
