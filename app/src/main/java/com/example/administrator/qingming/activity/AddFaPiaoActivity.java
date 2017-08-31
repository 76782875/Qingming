package com.example.administrator.qingming.activity;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.api.BaseApi;
import com.example.administrator.qingming.api.MainApi;
import com.example.administrator.qingming.interfaces.GetResultCallBack;
import com.example.administrator.qingming.model.Constants;
import com.example.administrator.qingming.model.ModelFaPiaoAh;
import com.example.administrator.qingming.qinminutils.GsonUtil;
import com.example.administrator.qingming.work.DatePickDialogUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/28 0028.
 */

public class AddFaPiaoActivity extends Activity {
    private ImageView back_btn;
    private String initStartDateTime ; // 初始化开始时间
    private TextView ah,edit_wtr, edit_ay, edit_dlf,edit_ykfp, edit_ydkx,kpxm, kplx, kprq,fplx;
    private EditText edit_sqr,  edit_fptt, edit_kpje;
    private EditText edit_rsr, edit_khyh, edit_khzh, edit_address, edit_phone, edit_sjr, edit_sj_address, phone;
    private EditText edit_sqbz, edit_fp_numb, edit_bz;
    private Button submit_btn,xuigai_btn,del_btn;
    private LinearLayout aaa;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fapiao);

        SharedPreferences sharedPreferences = getSharedPreferences("qinmin", Context.MODE_PRIVATE);
        create_id = sharedPreferences.getString("id","");
        company_id = sharedPreferences.getString("cid","");

        initView();
        Bundle bundle =getIntent().getExtras();
        Double num =bundle.getDouble("num",0);
        int index = bundle.getInt("index");
        edit_ykfp.setText(""+num);
        if(index == 1){
            getHttp();
            submit_btn.setVisibility(View.VISIBLE);
            submit_btn.setOnClickListener(onClickListener);
        }else {
            id = bundle.getString("id","");
            ah_id = bundle.getString("fpid","");
            ah_num = bundle.getString("ah_num","");
            sqr =bundle.getString("sqr","");
            fptt =bundle.getString("fptt","");
            mkpxm =bundle.getString("kpxm","");
            kjlx =bundle.getString("kjlx","");
            mfplx =bundle.getString("fplx","");
            nsrsbh =bundle.getString("nsrsbh","");
            jbhkhyh =bundle.getString("jbhkhyh","");
            jbhkhzh =bundle.getString("jbhkhzh","");
            zccsdz =bundle.getString("zccsdz","");
            zcgddh =bundle.getString("zcgddh","");
            sjr =bundle.getString("sjr","");
            sjdz =bundle.getString("sjdz","");
            lxdh =bundle.getString("lxdh","");
            sqbz =bundle.getString("sqbz","");
            fph =bundle.getString("fph","");
            mkprq = bundle.getString("kprq","");
            kpbz = bundle.getString("kpbz","");
            kpje = bundle.getString("kpje","");
            getHttps();
            aaa.setVisibility(View.VISIBLE);
            xuigai_btn.setOnClickListener(onClickListener);
            del_btn.setOnClickListener(onClickListener);

            ah.setText(ah_num);
            edit_sqr.setText(sqr);
            edit_fptt.setText(fptt);
            edit_kpje.setText(kpje);
            edit_rsr.setText(nsrsbh);
            edit_khyh.setText(jbhkhyh);
            edit_khzh.setText(jbhkhzh);
            edit_address.setText(zccsdz);
            edit_phone.setText(zcgddh);
            edit_sjr.setText(sjr);
            edit_sj_address.setText(sjdz);
            phone.setText(lxdh);
            edit_sqbz.setText(sqbz);
            edit_fp_numb.setText(fph);
            kprq.setText(mkprq);
            kprq.setTextColor(getResources().getColor(R.color.black));
            edit_bz.setText(kpbz);
        }

    }

    private void initView() {
        submit_btn = (Button) findViewById(R.id.submit_btn);
        xuigai_btn = (Button) findViewById(R.id.xuigai_btn);
        del_btn = (Button) findViewById(R.id.del_btn);
        aaa = (LinearLayout) findViewById(R.id.aaa);
        back_btn = (ImageView) findViewById(R.id.back_btn);
        kpxm = (TextView) findViewById(R.id.kpxm);//开票项目
        kplx = (TextView) findViewById(R.id.kplx);//开票类型
        fplx = (TextView) findViewById(R.id.fplx);//发票类型
        ah = (TextView) findViewById(R.id.ah);//案号
        kprq = (TextView) findViewById(R.id.kprq);//开票日期
        edit_sqr = (EditText) findViewById(R.id.edit_sqr);//申请人
        edit_wtr = (TextView) findViewById(R.id.edit_wtr);//委托人
        edit_ay = (TextView) findViewById(R.id.edit_ay);//案由
        edit_dlf = (TextView) findViewById(R.id.edit_dlf);//代理费
        edit_fptt = (EditText) findViewById(R.id.edit_fptt);//开票抬头
        edit_ydkx = (TextView) findViewById(R.id.edit_ydkx);//已到款项
        edit_ykfp = (TextView) findViewById(R.id.edit_ykfp);//已开发票
        edit_kpje = (EditText) findViewById(R.id.edit_kpje);//开票金额
        edit_rsr = (EditText) findViewById(R.id.edit_rsr);//纳税人识别号
        edit_khyh = (EditText) findViewById(R.id.edit_khyh);//基本户开户银行
        edit_khzh = (EditText) findViewById(R.id.edit_khzh);//基本户开户账号
        edit_address = (EditText) findViewById(R.id.edit_address);//注册场所地址
        edit_phone = (EditText) findViewById(R.id.edit_phone);//注册固定电话
        edit_sjr = (EditText) findViewById(R.id.edit_sjr);//收件人
        edit_sj_address = (EditText) findViewById(R.id.edit_sj_address);//收件人地址
        phone = (EditText) findViewById(R.id.phone);//联系电话
        edit_sqbz = (EditText) findViewById(R.id.edit_sqbz);//申请备注
        edit_fp_numb = (EditText) findViewById(R.id.edit_fp_numb);//发票号
        edit_bz = (EditText) findViewById(R.id.edit_bz);//开票备注

        edit_sqr.addTextChangedListener(new newtextwatch(edit_sqr));
        edit_fptt.addTextChangedListener(new newtextwatch(edit_fptt));
        edit_kpje.addTextChangedListener(new newtextwatch(edit_kpje));
        edit_rsr.addTextChangedListener(new newtextwatch(edit_rsr));
        edit_khyh.addTextChangedListener(new newtextwatch(edit_khyh));
        edit_khzh.addTextChangedListener(new newtextwatch(edit_khzh));
        edit_address.addTextChangedListener(new newtextwatch(edit_address));
        edit_phone.addTextChangedListener(new newtextwatch(edit_phone));
        edit_sjr.addTextChangedListener(new newtextwatch(edit_sjr));
        edit_sj_address.addTextChangedListener(new newtextwatch(edit_sj_address));
        phone.addTextChangedListener(new newtextwatch(phone));
        edit_sqbz.addTextChangedListener(new newtextwatch(edit_sqbz));
        edit_fp_numb.addTextChangedListener(new newtextwatch(edit_fp_numb));
        edit_bz.addTextChangedListener(new newtextwatch(edit_bz));

        back_btn.setOnClickListener(onClickListener);
        ah.setOnClickListener(onClickListener);
        kpxm.setOnClickListener(onClickListener);
        kplx.setOnClickListener(onClickListener);
        fplx.setOnClickListener(onClickListener);
        kprq.setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back_btn:
                    finish();
                    break;
                case R.id.ah:
                    showDialog();
                    builder.setItems(name, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ah.setText(name[which]);
                            ah_id = amid1.get(which);
                            getHttps();
                        }
                    });
                    builder.show();
                    break;
                case R.id.kpxm:
                    showDialog();
                    final String[] kpxms = {"律师代理费","法律顾问费","质询费","法务托管费","非诉",
                            "其他(备注中填写)","支付宝","微信","新增"};
                    builder.setItems(kpxms, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            kpxm.setText(kpxms[which]);
                        }
                    });
                    builder.show();
                    break;
                case R.id.kplx:
                    showDialog();
                    final String[] kplxs = {"企业","个人"};
                    builder.setItems(kplxs, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            kplx.setText(kplxs[which]);
                        }
                    });
                    builder.show();
                    break;
                case R.id.fplx:
                    showDialog();
                    final String[] fplxs = {"增值税专用发票","增值税普通发票","普通机打发票"};
                    builder.setItems(fplxs, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            fplx.setText(fplxs[which]);
                        }
                    });
                    builder.show();
                    break;
                case R.id.kprq:
                    kprq.setText(initStartDateTime);
                    kprq.setTextColor(getResources().getColor(R.color.black));
                    DatePickDialogUtil dateTimePickDialogUtil7 = new DatePickDialogUtil(initStartDateTime,AddFaPiaoActivity.this);
                    dateTimePickDialogUtil7.dateTimePicKDialog(kprq);
                    break;
                case R.id.submit_btn:
                    if(!ah.getText().toString().equals("请选择")){
                        if(!TextUtils.isEmpty(edit_sqr.getText())){
                            if(!TextUtils.isEmpty(edit_fptt.getText())){
                                if(!TextUtils.isEmpty(edit_kpje.getText())){
                                    if(!TextUtils.isEmpty(edit_fp_numb.getText())){
                                        if(!kprq.getText().toString().equals("请选择")){
                                           sqr = edit_sqr.getText().toString();
                                            fptt = edit_sqr.getText().toString();
                                            kpje = edit_kpje.getText().toString();
                                            fangfa();
                                            nsrsbh = edit_rsr.getText().toString();
                                            jbhkhyh = edit_khyh.getText().toString();
                                            jbhkhzh = edit_khzh.getText().toString();
                                            zccsdz = edit_address.getText().toString();
                                            zcgddh = edit_phone.getText().toString();
                                            sjr = edit_sjr.getText().toString();
                                            sjdz = edit_sj_address.getText().toString();
                                            lxdh = phone.getText().toString();
                                            sqbz = edit_sqbz.getText().toString();
                                            fph = edit_fp_numb.getText().toString();
                                            mkprq = kprq.getText().toString();
                                            kpbz = edit_bz.getText().toString();
                                            ah_num = ah.getText().toString();
                                            zt = "0";
                                            if(!TextUtils.isEmpty(lxdh)){
                                                if(isMobileNO(lxdh)){
                                                    postaddfp();
                                                }else {
                                                    Toast.makeText(AddFaPiaoActivity.this,"联系电话格式错误",Toast.LENGTH_SHORT).show();
                                                }
                                            }else {
                                                postaddfp();
                                            }
                                        }else {
                                            Toast.makeText(AddFaPiaoActivity.this,"请选择开票日期",Toast.LENGTH_SHORT).show();
                                        }
                                    }else {
                                        Toast.makeText(AddFaPiaoActivity.this,"请填写发票号",Toast.LENGTH_SHORT).show();
                                    }
                                }else {
                                    Toast.makeText(AddFaPiaoActivity.this,"请填写开票金额",Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(AddFaPiaoActivity.this,"请填写发票抬头",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(AddFaPiaoActivity.this,"请填写申请人",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(AddFaPiaoActivity.this,"请选择案号",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.xuigai_btn:
                    if (!TextUtils.isEmpty(edit_sqr.getText())) {
                        if (!TextUtils.isEmpty(edit_fptt.getText())) {
                            if (!TextUtils.isEmpty(edit_kpje.getText())) {
                                if (!TextUtils.isEmpty(edit_fp_numb.getText())) {
                                    if (!kprq.getText().toString().equals("请选择")) {
                                        sqr = edit_sqr.getText().toString();
                                        fptt = edit_sqr.getText().toString();
                                        kpje = edit_kpje.getText().toString();
                                        fangfa();
                                        nsrsbh = edit_rsr.getText().toString();
                                        jbhkhyh = edit_khyh.getText().toString();
                                        jbhkhzh = edit_khzh.getText().toString();
                                        zccsdz = edit_address.getText().toString();
                                        zcgddh = edit_phone.getText().toString();
                                        sjr = edit_sjr.getText().toString();
                                        sjdz = edit_sj_address.getText().toString();
                                        lxdh = phone.getText().toString();
                                        sqbz = edit_sqbz.getText().toString();
                                        fph = edit_fp_numb.getText().toString();
                                        mkprq = kprq.getText().toString();
                                        kpbz = edit_bz.getText().toString();
                                        ah_num = ah.getText().toString();
                                        zt = "0";
                                        if (!TextUtils.isEmpty(lxdh)) {
                                            if (isMobileNO(lxdh)) {
                                                insert_fpfp();
                                            } else {
                                                Toast.makeText(AddFaPiaoActivity.this, "联系电话格式错误", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            insert_fpfp();
                                        }
                                    } else {
                                        Toast.makeText(AddFaPiaoActivity.this, "请选择开票日期", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(AddFaPiaoActivity.this, "请填写发票号", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(AddFaPiaoActivity.this, "请填写开票金额", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(AddFaPiaoActivity.this, "请填写发票抬头", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(AddFaPiaoActivity.this, "请填写申请人", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.del_btn:
                    delfp();
                    break;
            }
        }
    };

    String[] name;
    List<String> name1;
    List<String> amid1;
    AlertDialog.Builder builder;
    private void showDialog(){
        builder = new AlertDialog.Builder(this,AlertDialog.THEME_HOLO_LIGHT);
        builder.setTitle("--请选择--");
    }

    private void fangfa() {
//      1.律师代理费 2.法律顾问费 3.咨询费 4.法务托管费 5.非诉 6.其他(备注中填写) 7.支付宝 8.微信 9.新增
        if (kpxm.getText().toString().equals("律师代理费")) {
            mkpxm = "1";
        } else if (kpxm.getText().toString().equals("法律顾问费")) {
            mkpxm = "2";
        }else if (kpxm.getText().toString().equals("咨询费")) {
            mkpxm = "3";
        }else if (kpxm.getText().toString().equals("法务托管费")) {
            mkpxm = "4";
        }else if (kpxm.getText().toString().equals("非诉")) {
            mkpxm = "5";
        }else if (kpxm.getText().toString().equals("其他(备注中填写)")) {
            mkpxm = "6";
        }else if (kpxm.getText().toString().equals("支付宝")) {
            mkpxm = "7";
        }else if (kpxm.getText().toString().equals("微信")) {
            mkpxm = "8";
        }else if (kpxm.getText().toString().equals("新增")) {
            mkpxm = "9";
        }

//      1.其他 2.个人
        if(kplx.getText().toString().equals("其他")){
            kjlx = "1";
        }else {
            kjlx = "2";
        }

//      1.增值税专用发票 2.增值税普通发票 3.通用机打发票
        if(fplx.getText().toString().equals("增值税专用发票")){
            mfplx = "1";
        }else if(fplx.getText().toString().equals("增值税普通发票")){
            mfplx = "2";
        }else {
            mfplx = "3";
        }

    }

    private String create_id;
    private String company_id;
    private String id;
    private Double num;
    private void getHttp(){
        MainApi.getInstance(this).getfapiaoxqApi(company_id,new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type== Constants.TYPE_SUCCESS){
                    List<ModelFaPiaoAh.ResultBean> resultbean = GsonUtil.fromJsonList(new Gson(),
                            result,ModelFaPiaoAh.ResultBean.class);
                    name1 = new ArrayList<>();
                    amid1 = new ArrayList<>();
                    for(int i=0;i<resultbean.size();i++){
                        String names = resultbean.get(i).getAh_number();
                        String amids = resultbean.get(i).getId();
                        name1.add(names);
                        amid1.add(amids);
                    }
                    name = name1.toArray(new String[name1.size()]);
                }else BaseApi.showErrMsg(AddFaPiaoActivity.this,result);
            }
        });
    }

    String wtrs;
    String dlfs;
    String ays;
    private void getHttps(){
        MainApi.getInstance(this).getfapiaoxqsApi(create_id,ah_id,new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type== Constants.TYPE_SUCCESS){
                    List<ModelFaPiaoAh.ResultBean> resultbean = GsonUtil.fromJsonList(new Gson(),
                            result,ModelFaPiaoAh.ResultBean.class);
                    for(int i=0;i<resultbean.size();i++){
                        num = resultbean.get(i).getSfje();
                        wtrs = resultbean.get(i).getWtr();
                        dlfs = resultbean.get(i).getDlf();
                        ays = resultbean.get(i).getAy();
                    }
                    edit_wtr.setText(wtrs);
                    edit_ay.setText(ays);
                    edit_dlf.setText(dlfs);
                    if(num != null){
                        edit_ydkx.setText(""+num);
                    }else {
                        edit_ydkx.setText("0");
                    }

                }else BaseApi.showErrMsg(AddFaPiaoActivity.this,result);
            }
        });
    }

    String ah_id;
    String sqr;
    String fptt;
    String kpje;
    String mkpxm;
    String kjlx;
    String mfplx;
    String nsrsbh;
    String jbhkhyh;
    String jbhkhzh;
    String zccsdz;
    String zcgddh;
    String sjr;
    String sjdz;
    String lxdh;
    String sqbz;
    String fph;
    String mkprq;
    String kpbz;
    String zt;
    String ah_num;
    String create_name;
    String create_time;
    /**
     * 添加
     */
    private void postaddfp(){
        MainApi.getInstance(this).getaddfpApi(create_id, ah_id, sqr, fptt, kpje, mkpxm, kjlx, mfplx, nsrsbh,
                jbhkhyh, jbhkhzh, zccsdz, zcgddh, sjr, sjdz, lxdh, sqbz, fph, mkprq, kpbz, zt, ah_num, create_name,
                create_time, new GetResultCallBack() {
                    @Override
                    public void getResult(String result, int type) {
                        if(type == Constants.TYPE_SUCCESS){
                            Intent intent = new Intent(AddFaPiaoActivity.this,FaPiaoActivity.class);
                            startActivity(intent);
                            finish();
                        }else BaseApi.showErrMsg(AddFaPiaoActivity.this,result);
                    }
                });
    }

    /**
     * 修改
     */
    private void insert_fpfp(){
        MainApi.getInstance(this).getinsert_fpApi( id,sqr, fptt, kpje, mkpxm, kjlx, mfplx, nsrsbh,
                jbhkhyh, jbhkhzh, zccsdz, zcgddh, sjr, sjdz, lxdh, sqbz, fph, mkprq, kpbz, zt, new GetResultCallBack() {
                    @Override
                    public void getResult(String result, int type) {
                        if(type == Constants.TYPE_SUCCESS){
                            Intent intent = new Intent(AddFaPiaoActivity.this,FaPiaoActivity.class);
                            startActivity(intent);
                            finish();
                        }else BaseApi.showErrMsg(AddFaPiaoActivity.this,result);
                    }
                });
    }
    /**
     * 删除
     */
    private void delfp(){
        MainApi.getInstance(this).getdelfpApi(id, new GetResultCallBack() {
                    @Override
                    public void getResult(String result, int type) {
                        if(type == Constants.TYPE_SUCCESS){
                            Intent intent = new Intent(AddFaPiaoActivity.this,FaPiaoActivity.class);
                            startActivity(intent);
                            finish();
                        }else BaseApi.showErrMsg(AddFaPiaoActivity.this,result);
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

    class newtextwatch implements TextWatcher {
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
