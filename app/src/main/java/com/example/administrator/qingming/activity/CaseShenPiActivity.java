package com.example.administrator.qingming.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.api.BaseApi;
import com.example.administrator.qingming.api.MainApi;
import com.example.administrator.qingming.dialog.LoadingDialog;
import com.example.administrator.qingming.interfaces.GetResultCallBack;
import com.example.administrator.qingming.model.Constants;
import com.example.administrator.qingming.news.casedetails.CaseDetailsActivity;
import com.example.administrator.qingming.news.casedetails.CreateWorkActivity;
import com.example.administrator.qingming.work.CaseRegisterActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Administrator on 2017/7/7 0007.
 */

public class CaseShenPiActivity extends Activity {
    private Button agree_btn,noagree_btn,caiwu_btn;
    private String ah_number,id,wtr,lxdh,dsr,dfdsr,ay,cc;
    private TextView name,ah,time,jssf,dlf,ls;
    private int ysje;
    int mYear,mMonth,mDay;
    private RelativeLayout worklog,charge_list,zhencha,jiancha,fayuan;
    private LoadingDialog loadingDialog;
    private RelativeLayout counsel;
    String sffs,mname,sarq,court,detention,police,procuratorate,slbm,bzsm,ssbd,ssjd,ssdw;
    private int ajlx,mdlf,jzf;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_shenpi);

        SharedPreferences sharedPreferences = getSharedPreferences("qinmin", Context.MODE_PRIVATE);
        create_id = sharedPreferences.getString("id","");
        company_id = sharedPreferences.getString("cid","");

        initView();
        Bundle bundle = getIntent().getExtras();
        cc = bundle.getString("cc","");
        ay = bundle.getString("ay","");
        ah_number = bundle.getString("ah_number","");
        wtr = bundle.getString("wtr","");
        lxdh = bundle.getString("lxdh","");
        dsr = bundle.getString("dsr","");
        dfdsr = bundle.getString("dfdsr","");
        id = bundle.getString("id","");
        mdlf = bundle.getInt("dlf");
        jzf = bundle.getInt("jzf");
        sffs = bundle.getString("sffs",sffs);
        mname =bundle.getString("name",mname);
        sarq =bundle.getString("sarq",sarq);
        court =bundle.getString("court",court);
        detention =bundle.getString("detention",detention);
        police =bundle.getString("police",police);
        procuratorate =bundle.getString("procuratorate",procuratorate);
        slbm =bundle.getString("slbm",slbm);
        bzsm =bundle.getString("bzsm",bzsm);
        ssdw =bundle.getString("ssdw",ssdw);
        ssbd =bundle.getString("ssbd",ssbd);
        ssjd =bundle.getString("ssjd",ssjd);

        case_state = bundle.getInt("case_state");
        String createid = bundle.getString("createid","");
        if(case_state==2){
            //主任审批
            caiwu_btn.setVisibility(View.VISIBLE);
            caiwu_btn.setOnClickListener(onclicklisten);
            agree_btn.setOnClickListener(onclicklisten);
            noagree_btn.setOnClickListener(onclicklisten);
        }else if(case_state == 5){
            //主任审批
            caiwu_btn.setVisibility(View.VISIBLE);
            caiwu_btn.setOnClickListener(onclicklisten);
            agree_btn.setOnClickListener(onclicklisten);
            noagree_btn.setOnClickListener(onclicklisten);
        }else if(case_state==3){
            //财务审批
            agree_btn.setOnClickListener(onclicklisten);
            noagree_btn.setOnClickListener(onclicklisten);
        }else if(case_state==6){
            //财务审批
            agree_btn.setOnClickListener(onclicklisten);
            noagree_btn.setOnClickListener(onclicklisten);
        }

        int mdlf = bundle.getInt("dlf");
        String sffs = bundle.getString("sffs","");
        String lsname = bundle.getString("name","");
        String sarq = bundle.getString("sarq","");
        int jzf = bundle.getInt("jzf");

        ysje = mdlf + jzf ;
        Log.e("---------->",""+ysje);

        accepter_id = createid;
        create_name = lsname;

        name.setText(ay);
        ah.setText(ah_number);
        dlf.setText(""+mdlf);
        jssf.setText(sffs);
        ls.setText(lsname);
        time.setText(sarq);

        //获取日期
        final Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH)+1;
        mDay = ca.get(Calendar.DAY_OF_MONTH);

        //获取日期
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy/MM/dd  HH:mm:ss");
        update_date = sDateFormat.format(new java.util.Date());
        create_date = sDateFormat.format(new java.util.Date());
    }

    private void initView() {
        loadingDialog = new LoadingDialog(this);
        agree_btn = (Button) findViewById(R.id.agree_btn);
        noagree_btn = (Button) findViewById(R.id.noagree_btn);
        counsel = (RelativeLayout) findViewById(R.id.counsel);
        worklog = (RelativeLayout) findViewById(R.id.work_log);
        zhencha = (RelativeLayout) findViewById(R.id.zhencha);
        jiancha = (RelativeLayout) findViewById(R.id.jiancha);
        fayuan = (RelativeLayout) findViewById(R.id.fayuan);
        charge_list = (RelativeLayout) findViewById(R.id.charge_list);
        name = (TextView) findViewById(R.id.name);
        ah = (TextView) findViewById(R.id.ah);
        time = (TextView) findViewById(R.id.time);
        jssf = (TextView) findViewById(R.id.jssf);
        dlf = (TextView) findViewById(R.id.dlf);
        ls = (TextView) findViewById(R.id.ls);
        caiwu_btn = (Button) findViewById(R.id.caiwu_btn);

        worklog.setOnClickListener(onclicklisten);
        charge_list.setOnClickListener(onclicklisten);
        zhencha.setOnClickListener(onclicklisten);
        jiancha.setOnClickListener(onclicklisten);
        fayuan.setOnClickListener(onclicklisten);
        counsel.setOnClickListener(onclicklisten);
    }

    View.OnClickListener onclicklisten = new View.OnClickListener() {
        Intent intent;
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.counsel://跳转到案件简介页面
                    intent = new Intent(CaseShenPiActivity.this,CaseRegisterActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("index",102);
                    bundle.putInt("ajlx",ajlx);
                    bundle.putString("id",id);
                    bundle.putString("cc",cc);
                    bundle.putString("ay",ay);
                    bundle.putString("ah_number",ah_number);
                    bundle.putString("wtr",wtr);
                    bundle.putString("lxdh",lxdh);
                    bundle.putString("dsr",dsr);
                    bundle.putString("dfdsr",dfdsr);
                    bundle.putString("sffs",sffs);
                    bundle.putString("name",mname);
                    bundle.putString("sarq",sarq);
                    bundle.putString("court",court);
                    bundle.putString("detention",detention);
                    bundle.putString("police",police);
                    bundle.putString("procuratorate",procuratorate);
                    bundle.putString("slbm",slbm);
                    bundle.putString("bzsm",bzsm);
                    bundle.putString("ssdw",ssdw);
                    bundle.putString("ssbd",ssbd);
                    bundle.putString("ssjd",ssjd);
                    bundle.putInt("dlf",mdlf);
                    bundle.putInt("jzf",jzf);
                    bundle.putInt("ajlx",ajlx);
                    intent.putExtras(bundle);
                    Log.e("ajlx====》",""+ajlx);
                    startActivity(intent);
                    break;
                case R.id.charge_list://跳转到收款信息页面
                    intent = new Intent(CaseShenPiActivity.this, ChargeListActivity.class);
                    intent.putExtra("id",id);
                    intent.putExtra("ah_number",ah_number);
                    intent.putExtra("ysje",ysje);
                    startActivity(intent);
                    break;
                case R.id.work_log: //跳转到工作日志页面
                    intent = new Intent(CaseShenPiActivity.this,CreateWorkActivity.class);
                    intent.putExtra("type",1);
                    intent.putExtra("id",id);
                    intent.putExtra("ah_number",ah_number);
                    intent.putExtra("wtr",wtr);
                    intent.putExtra("dfdsr",dfdsr);
                    intent.putExtra("ay",ay);
                    startActivity(intent);
                    break;
                case R.id.zhencha://跳转到侦查机关页面
                    intent = new Intent(CaseShenPiActivity.this, CreateWorkActivity.class);
                    intent.putExtra("type",2);
                    intent.putExtra("id",id);
                    intent.putExtra("ah_number",ah_number);
                    intent.putExtra("wtr",wtr);
                    intent.putExtra("dfdsr",dfdsr);
                    intent.putExtra("ay",ay);
                    startActivity(intent);
                    break;
                case R.id.jiancha://跳转到检查机关页面
                    intent = new Intent(CaseShenPiActivity.this, CreateWorkActivity.class);
                    intent.putExtra("type",3);
                    intent.putExtra("id",id);
                    intent.putExtra("ah_number",ah_number);
                    intent.putExtra("wtr",wtr);
                    intent.putExtra("dfdsr",dfdsr);
                    intent.putExtra("ay",ay);
                    startActivity(intent);
                    break;
                case R.id.fayuan://跳转到法院开庭页面
                    intent = new Intent(CaseShenPiActivity.this, CreateWorkActivity.class);
                    intent.putExtra("type",4);
                    intent.putExtra("id",id);
                    intent.putExtra("ah_number",ah_number);
                    intent.putExtra("wtr",wtr);
                    intent.putExtra("dfdsr",dfdsr);
                    intent.putExtra("ay",ay);
                    startActivity(intent);
                    break;
                case R.id.agree_btn:
                    case_state = 4;
                    postHttp();
                    break;
                case R.id.noagree_btn:
                    case_state = -1;
                    postHttp();
                    break;
                case R.id.caiwu_btn:
                    case_state = 3;
                    postHttp();
                    break;
            }
        }
    };


    String update_date;
    int case_state;
    private void postHttp(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("上传中...");
        MainApi.getInstance(this).getxgzjztApi(id,case_state,update_date, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type == Constants.TYPE_SUCCESS){
                    loadingDialog.dismiss();
                    xxtz();
                    xxHttp();
                }else BaseApi.showErrMsg(CaseShenPiActivity.this,result);
            }
        });
    }

    String theme;
    String mcontent;
    String company_id;
    String glid;
    String accepter_id;
    String create_id;
    String create_name;
    String create_date;
    private void xxHttp(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("上传中...");
        MainApi.getInstance(this).getaddxxtzApi(theme,mcontent,company_id,glid,accepter_id, create_id,
                create_name,create_date,new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type == Constants.TYPE_SUCCESS){
                    loadingDialog.dismiss();
                    finish();
                }else BaseApi.showErrMsg(CaseShenPiActivity.this,result);
            }
        });
    }

    private void xxtz(){
        if (case_state==3) {
            theme ="主任收案审批通过";
            mcontent="您办理的《"+ah_number+"》主任收案审批通过！将移交财务审批！";
            glid = id ;
        }
        if (case_state==4) {
            theme ="收案审批通过";
            mcontent="您办理的《"+ah_number+"》收案审批通过！案件已收案！";
            glid = id ;
        }
        if (case_state==-1) {
            theme ="收案审批未通过";
            mcontent="您办理的《"+ah_number+"》收案审批未通过！请注意查看！";
            glid = id ;
        }
        if (case_state==-4) {
            theme ="结案审批未通过";
            mcontent="您办理的《"+ah_number+"》结案审批未通过！请注意查看！";
            glid = id ;
        }
        if (case_state==7) {
            theme ="结案审批通过";
            mcontent="您办理的《"+ah_number+"》结案审批通过！案件已结案！";
            glid = id ;
        }
        if (case_state==6) {
            theme ="主任结案审批通过";
            mcontent="您办理的 《"+ah_number+"》主任结案审批通过！将移交财务审批！";
            glid = id ;
        }
    }
}
