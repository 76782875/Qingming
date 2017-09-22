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
import com.example.administrator.qingming.news.casedetails.CreateWorkLogActivity;
import com.example.administrator.qingming.url.BaseUrl;
import com.example.administrator.qingming.work.DatePickDialogUtil;
import com.example.administrator.qingming.work.DateTimePickDialogUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/5 0005.
 */

public class ZhenChaActivity extends Activity {
    private String initStartDateTime ; // 初始化开始时间
    LoadingDialog loadingDialog;
    int mYear,mMonth,mDay;
    private String ah_number,wtr,dfdsr,ay,dsr;
    private TextView one,two,three,fore,name;
    private ImageView backbtn;
    private Button submit,xuigaibtn,delbtn;
    //侦查机关页面
    private TextView bananjiguan,xingju_time,daibu_time,zhencha_start_time,zhencha_finish_time,buchong_start_time,
            buchong_finish_time,two_buchong_start,two_buchong_finish,five;
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
        dsr = bundle.getString("dsr","");
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
            bananjiguan.setTextColor(getResources().getColor(R.color.black));
            edit_log.setText(bundle.getString("bzsm",""));
            edit_log.setTextColor(getResources().getColor(R.color.black));
            zhenchaanhao.setText(bundle.getString("zcah",""));
            zhenchaanhao.setTextColor(getResources().getColor(R.color.black));
            bumeng.setText(bundle.getString("bm",""));
            bumeng.setTextColor(getResources().getColor(R.color.black));
            chengbanren.setText(bundle.getString("cbr",""));
            chengbanren.setTextColor(getResources().getColor(R.color.black));
            phone_num.setText(bundle.getString("tel",""));
            phone_num.setTextColor(getResources().getColor(R.color.black));
            if(bundle.getString("xjsj","").equals("")){
                xingju_time.setText("请选择时间");
                xingju_time.setTextColor(getResources().getColor(R.color.transparent80));
            }else {
                xingju_time.setText(bundle.getString("xjsj",""));
                xingju_time.setTextColor(getResources().getColor(R.color.black));
            }
            if(bundle.getString("dbsj","").equals("")){
                daibu_time.setText("请选择时间");
                daibu_time.setTextColor(getResources().getColor(R.color.transparent80));
            }else {
                daibu_time.setText(bundle.getString("dbsj",""));
                daibu_time.setTextColor(getResources().getColor(R.color.black));
            }
            if(bundle.getString("zcqx_star","").equals("")){
                zhencha_start_time.setText("请选择时间");
                zhencha_finish_time.setText("请选择时间");
                zhencha_start_time.setTextColor(getResources().getColor(R.color.transparent80));
                zhencha_finish_time.setTextColor(getResources().getColor(R.color.transparent80));
            }else {
                zhencha_start_time.setText(bundle.getString("zcqx_star",""));
                zhencha_finish_time.setText(bundle.getString("zcqx_end",""));
                zhencha_start_time.setTextColor(getResources().getColor(R.color.black));
                zhencha_finish_time.setTextColor(getResources().getColor(R.color.black));
            }
            if(bundle.getString("bczcqx_star","").equals("")){
                buchong_start_time.setText("请选择时间");
                buchong_finish_time.setText("请选择时间");
                buchong_start_time.setTextColor(getResources().getColor(R.color.transparent80));
                buchong_finish_time.setTextColor(getResources().getColor(R.color.transparent80));
            }else {
                buchong_start_time.setText(bundle.getString("bczcqx_star",""));
                buchong_finish_time.setText(bundle.getString("bczcqx_end",""));
                buchong_start_time.setTextColor(getResources().getColor(R.color.black));
                buchong_finish_time.setTextColor(getResources().getColor(R.color.black));
            }

            if(bundle.getString("ecbczc_star","").equals("")){
                two_buchong_start.setText("请选择时间");
                two_buchong_finish.setText("请选择时间");
                two_buchong_start.setTextColor(getResources().getColor(R.color.transparent80));
                two_buchong_finish.setTextColor(getResources().getColor(R.color.transparent80));
            }else {
                two_buchong_start.setText(bundle.getString("ecbczc_star",""));
                two_buchong_finish.setText(bundle.getString("ecbczc_end",""));
                two_buchong_start.setTextColor(getResources().getColor(R.color.black));
                two_buchong_finish.setTextColor(getResources().getColor(R.color.black));
            }

            aa.setVisibility(View.VISIBLE);
            submit.setVisibility(View.GONE);
            xuigaibtn.setOnClickListener(onClickListener);
            delbtn.setOnClickListener(onClickListener);
        }

        one.setText(ay);
        two.setText(ah_number);
        three.setText(wtr);
        fore.setText(dfdsr);
        five.setText(dsr);

        //获取日期
        final Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH)+1;
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
                                bajg = bananjiguan.getText().toString();
                                zcah = zhenchaanhao.getText().toString();
                                bm = bumeng.getText().toString();
                                cbr = chengbanren.getText().toString();
                                bz = edit_log.getText().toString();
                                if(init()){
                                    if (!TextUtils.isEmpty(phone_num.getText())) {
                                        if (isMobileNO(phone_num.getText().toString())) {
                                            tel = phone_num.getText().toString();
                                            postzhencha();
                                        } else {
                                            Toast.makeText(ZhenChaActivity.this, "联系电话格式错误", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        tel = phone_num.getText().toString();
                                        postzhencha();
                                    }
                                }


                            }else {
                                Toast.makeText(ZhenChaActivity.this,"承办人不能为空",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(ZhenChaActivity.this,"部门不能为空",Toast.LENGTH_SHORT).show();
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
                    xingju_time.setText(initStartDateTime);
                    xingju_time.setTextColor(getResources().getColor(R.color.black));
                    DatePickDialogUtil dateTimePickDialogUtil = new DatePickDialogUtil(initStartDateTime,ZhenChaActivity.this);
                    dateTimePickDialogUtil.dateTimePicKDialog(xingju_time);
                    break;
                case R.id.daibu_time:
                    daibu_time.setText(initStartDateTime);
                    daibu_time.setTextColor(getResources().getColor(R.color.black));
                    DatePickDialogUtil dateTimePickDialogUtil1 = new DatePickDialogUtil(initStartDateTime,ZhenChaActivity.this);
                    dateTimePickDialogUtil1.dateTimePicKDialog(daibu_time);
                    break;
                case R.id.zhencha_start_time:
                    zhencha_start_time.setText(initStartDateTime);
                    zhencha_start_time.setTextColor(getResources().getColor(R.color.black));
                    DatePickDialogUtil dateTimePickDialogUtil2 = new DatePickDialogUtil(initStartDateTime,ZhenChaActivity.this);
                    dateTimePickDialogUtil2.dateTimePicKDialog(zhencha_start_time);
                    break;
                case R.id.zhencha_finish_time:
                    zhencha_finish_time.setText(initStartDateTime);
                    zhencha_finish_time.setTextColor(getResources().getColor(R.color.black));
                    DatePickDialogUtil dateTimePickDialogUtil3 = new DatePickDialogUtil(initStartDateTime,ZhenChaActivity.this);
                    dateTimePickDialogUtil3.dateTimePicKDialog(zhencha_finish_time);
                    break;
                case R.id.buchong_start_time:
                    buchong_start_time.setText(initStartDateTime);
                    buchong_start_time.setTextColor(getResources().getColor(R.color.black));
                    DatePickDialogUtil dateTimePickDialogUtil4 = new DatePickDialogUtil(initStartDateTime,ZhenChaActivity.this);
                    dateTimePickDialogUtil4.dateTimePicKDialog(buchong_start_time);
                    break;
                case R.id.buchong_finish_time:
                    buchong_finish_time.setText(initStartDateTime);
                    buchong_finish_time.setTextColor(getResources().getColor(R.color.black));
                    DatePickDialogUtil dateTimePickDialogUtil5 = new DatePickDialogUtil(initStartDateTime,ZhenChaActivity.this);
                    dateTimePickDialogUtil5.dateTimePicKDialog(buchong_finish_time);
                    break;
                case R.id.two_buchong_start:
                    two_buchong_start.setText(initStartDateTime);
                    two_buchong_start.setTextColor(getResources().getColor(R.color.black));
                    DatePickDialogUtil dateTimePickDialogUtil6 = new DatePickDialogUtil(initStartDateTime,ZhenChaActivity.this);
                    dateTimePickDialogUtil6.dateTimePicKDialog(two_buchong_start);
                    break;
                case R.id.two_buchong_finish:
                    two_buchong_finish.setText(initStartDateTime);
                    two_buchong_finish.setTextColor(getResources().getColor(R.color.black));
                    DatePickDialogUtil dateTimePickDialogUtil7 = new DatePickDialogUtil(initStartDateTime,ZhenChaActivity.this);
                    dateTimePickDialogUtil7.dateTimePicKDialog(two_buchong_finish);
                    break;
                case R.id.xuigai_btn:
                    if(!TextUtils.isEmpty(zhenchaanhao.getText())){
                        if(!TextUtils.isEmpty(bumeng.getText())){
                            if(!TextUtils.isEmpty(chengbanren.getText())){
                                bajg = bananjiguan.getText().toString();
                                zcah = zhenchaanhao.getText().toString();
                                bm = bumeng.getText().toString();
                                cbr = chengbanren.getText().toString();
                                bz = edit_log.getText().toString();

                                if(init()){
                                    if (!TextUtils.isEmpty(phone_num.getText())) {
                                        if (isMobileNO(phone_num.getText().toString())) {
                                            tel = phone_num.getText().toString();
                                            postxgzhencha();
                                        } else {
                                            Toast.makeText(ZhenChaActivity.this, "联系电话格式错误", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        tel = phone_num.getText().toString();
                                        postxgzhencha();
                                    }
                                }
                            }else {
                                Toast.makeText(ZhenChaActivity.this,"承办人不能为空",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(ZhenChaActivity.this,"部门不能为空",Toast.LENGTH_SHORT).show();
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


    private boolean init(){
        boolean b = true;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        int result = 0;
        if(!xingju_time.getText().toString().equals("请选择时间")){
            xjsj = xingju_time.getText().toString();
        }

        if(!daibu_time.getText().toString().equals("请选择时间")){
            dbsj = daibu_time.getText().toString();
        }

        if(!zhencha_start_time.getText().toString().equals("请选择时间")){
            try {
                Date date1 = format.parse(zhencha_start_time.getText().toString());
                Date date2 = format.parse(zhencha_finish_time.getText().toString());
                result = date1.compareTo(date2);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (result == 0) {
                Toast.makeText(ZhenChaActivity.this, "侦查时间时间格式错误", Toast.LENGTH_SHORT).show();
                b = false;
            } else if (result < 0) {
                zcqx_star= zhencha_start_time.getText().toString();
                zcqx_end= zhencha_finish_time.getText().toString();
            } else {
                Toast.makeText(ZhenChaActivity.this, "侦查时间时间格式错误", Toast.LENGTH_SHORT).show();
                b = false;
            }
        }

        if(!buchong_start_time.getText().toString().equals("请选择时间")){
            try {
                Date date1 = format.parse(buchong_start_time.getText().toString());
                Date date2 = format.parse(buchong_finish_time.getText().toString());
                result = date1.compareTo(date2);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (result == 0) {
                Toast.makeText(ZhenChaActivity.this, "补充侦查时间格式错误", Toast.LENGTH_SHORT).show();
                b = false;
            } else if (result < 0) {
                bczcqx_star= buchong_start_time.getText().toString();
                bczcqx_end= buchong_finish_time.getText().toString();
            } else {
                Toast.makeText(ZhenChaActivity.this, "补充侦查时间格式错误", Toast.LENGTH_SHORT).show();
                b = false;
            }

        }

        if(!two_buchong_start.getText().toString().equals("请选择时间")){
            try {
                Date date1 = format.parse(two_buchong_start.getText().toString());
                Date date2 = format.parse(two_buchong_finish.getText().toString());
                result = date1.compareTo(date2);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (result == 0) {
                Toast.makeText(ZhenChaActivity.this, "二次补充时间格式错误", Toast.LENGTH_SHORT).show();
                b = false;
            } else if (result < 0) {
                ecbczc_star= two_buchong_start.getText().toString();
                ecbczc_end= two_buchong_finish.getText().toString();
            } else {
                Toast.makeText(ZhenChaActivity.this, "二次补充时间格式错误", Toast.LENGTH_SHORT).show();
                b = false;
            }
        }
        return b;
    }

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
    private void postzhencha(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("上传中...");
        MainApi.getInstance(this).postaddzhenchaApi(id,cid,glid,bajg,bz,zcah,bm,cbr,tel,xjsj, dbsj,
                zcqx_star,zcqx_end,bczcqx_star,bczcqx_end,ecbczc_star,ecbczc_end,create_date,new GetResultCallBack() {
                    @Override
                    public void getResult(String result, int type) {
                        if(type == Constants.TYPE_SUCCESS){
                            loadingDialog.dismiss();
                            Toast.makeText(ZhenChaActivity.this,"上传成功",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ZhenChaActivity.this, CreateWorkActivity.class);
                            intent.putExtra("type",2);
                            intent.putExtra("id",glid);
                            intent.putExtra("ah_number",ah_number);
                            intent.putExtra("wtr",wtr);
                            intent.putExtra("dsr",dsr);
                            intent.putExtra("dfdsr",dfdsr);
                            intent.putExtra("ay",ay);
                            startActivity(intent);
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
                            Intent intent = new Intent(ZhenChaActivity.this, CreateWorkActivity.class);
                            intent.putExtra("type",2);
                            intent.putExtra("id",glid);
                            intent.putExtra("ah_number",ah_number);
                            intent.putExtra("wtr",wtr);
                            intent.putExtra("dsr",dsr);
                            intent.putExtra("dfdsr",dfdsr);
                            intent.putExtra("ay",ay);
                            startActivity(intent);
                        }else BaseApi.showErrMsg(ZhenChaActivity.this,result);
                    }
                });
    }

    /**
     * 修改
     */
    String update_date;
    private void postxgzhencha(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("修改中...");
        MainApi.getInstance(this).getxgzhenchaApi(aid,bajg,bz,zcah,bm,cbr,tel,xjsj, dbsj,
                zcqx_star,zcqx_end,bczcqx_star,bczcqx_end,ecbczc_star,ecbczc_end,update_date,new GetResultCallBack() {
                    @Override
                    public void getResult(String result, int type) {
                        if(type == Constants.TYPE_SUCCESS){
                            loadingDialog.dismiss();
                            Toast.makeText(ZhenChaActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ZhenChaActivity.this, CreateWorkActivity.class);
                            intent.putExtra("type",2);
                            intent.putExtra("id",glid);
                            intent.putExtra("ah_number",ah_number);
                            intent.putExtra("wtr",wtr);
                            intent.putExtra("dsr",dsr);
                            intent.putExtra("dfdsr",dfdsr);
                            intent.putExtra("ay",ay);
                            startActivity(intent);
                        }else BaseApi.showErrMsg(ZhenChaActivity.this,result);
                    }
                });
    }


    String title;
    AlertDialog.Builder builder;
    private void showDialog() {
        builder= new AlertDialog.Builder(ZhenChaActivity.this,AlertDialog.THEME_HOLO_LIGHT);
        builder.setTitle(title);
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
