package com.example.administrator.qingming.news.casedetails;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.activity.ChargeListActivity;
import com.example.administrator.qingming.activity.ZhenChaActivity;
import com.example.administrator.qingming.api.BaseApi;
import com.example.administrator.qingming.api.MainApi;
import com.example.administrator.qingming.dialog.LoadingDialog;
import com.example.administrator.qingming.interfaces.GetResultCallBack;
import com.example.administrator.qingming.model.Constants;
import com.example.administrator.qingming.work.CaseRegisterActivity;

import java.util.Calendar;

/**
 * 案件详情页面
 *
 * Created by Administrator on 2017/4/24.
 */

public class CaseDetailsActivity extends Activity{
    private ImageView backbtn;//返回按钮
    private ImageView modifybtn;//编辑修改按钮
    private RelativeLayout counsel; //法律顾问
    private TextView qx,xg,xglv,kh;
    private RelativeLayout worklog,charge_list,zhencha,jiancha,fayuan;
    private TextView name,ah,time,jssf,dlf,ls;
    private String ah_number,id,wtr,dfdsr,ay;
    private int ysje;
    private LinearLayout sasq;
    private Button sabtn,ja_btn,zzbtn,agree_btn,noagree_btn;
    int mYear,mMonth,mDay;
    int ajlx,mdlf,jzf;
    String sffs,mname,sarq,court,detention,police,procuratorate,slbm,bzsm,ssbd,ssjd,ssdw;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_details);

        init();
        Bundle bundle = getIntent().getExtras();
        ay = bundle.getString("ay","");
        ah_number = bundle.getString("ah_number","");
        wtr = bundle.getString("wtr","");
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
        Log.e("id---------->",""+id);
        String case_state = bundle.getString("case_state","");
        Log.e("case_state---------->",""+case_state);

        if(case_state.equals("-1")){
            sabtn.setVisibility(View.VISIBLE);
            zzbtn.setVisibility(View.VISIBLE);
            sabtn.setOnClickListener(onClickListener);
            zzbtn.setOnClickListener(onClickListener);
        }else if(case_state.equals("1")){
            sabtn.setVisibility(View.VISIBLE);
            zzbtn.setVisibility(View.VISIBLE);
            sabtn.setOnClickListener(onClickListener);
            zzbtn.setOnClickListener(onClickListener);
        }else if(case_state.equals("2")){
            zzbtn.setVisibility(View.VISIBLE);
            zzbtn.setOnClickListener(onClickListener);
        }else if(case_state.equals("3")){
            zzbtn.setVisibility(View.VISIBLE);
            zzbtn.setOnClickListener(onClickListener);
        }else if(case_state.equals("4")){
            zzbtn.setVisibility(View.VISIBLE);
            zzbtn.setOnClickListener(onClickListener);
            ja_btn.setVisibility(View.VISIBLE);
            ja_btn.setOnClickListener(onClickListener);
        }else if(case_state.equals("5")){
            zzbtn.setVisibility(View.VISIBLE);
            zzbtn.setOnClickListener(onClickListener);
        }else if(case_state.equals("6")){
            zzbtn.setVisibility(View.VISIBLE);
            zzbtn.setOnClickListener(onClickListener);
        }else if(case_state.equals("7")){
            Log.e("case_state---------->","没有按钮");
        }else if(case_state.equals("0")){
            Log.e("case_state---------->","没有按钮");
        }else if(case_state.equals("-4")){
            zzbtn.setVisibility(View.VISIBLE);
            zzbtn.setOnClickListener(onClickListener);
            ja_btn.setVisibility(View.VISIBLE);
            ja_btn.setOnClickListener(onClickListener);
        }

        ajlx = bundle.getInt("ajlx");
        int mdlf = bundle.getInt("dlf");
        String sffs = bundle.getString("sffs","");
        String lsname = bundle.getString("name","");
        String sarq = bundle.getString("sarq","");
        int jzf = bundle.getInt("jzf");

        ysje = mdlf + jzf ;
        Log.e("---------->",""+ysje);

        name.setText(ay);
        ah.setText(ah_number);
        dlf.setText(""+mdlf);
        jssf.setText(sffs);
        ls.setText(lsname);
        time.setText(sarq);

        //获取日期
        final Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);

        update_date = mYear+"-"+mMonth+"-"+mDay;
    }
    private void init(){
        loadingDialog = new LoadingDialog(this);
        backbtn = (ImageView) findViewById(R.id.back_btn);
        modifybtn= (ImageView) findViewById(R.id.modify_btn);
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
        sasq = (LinearLayout) findViewById(R.id.sasq);
        sabtn = (Button) findViewById(R.id.sa_btn);
        zzbtn = (Button) findViewById(R.id.zz_btn);
        ja_btn = (Button) findViewById(R.id.ja_btn);

        backbtn.setOnClickListener(onClickListener);
        modifybtn.setOnClickListener(onClickListener);
        counsel.setOnClickListener(onClickListener);
        worklog.setOnClickListener(onClickListener);
        charge_list.setOnClickListener(onClickListener);
        zhencha.setOnClickListener(onClickListener);
        jiancha.setOnClickListener(onClickListener);
        fayuan.setOnClickListener(onClickListener);
    }
    /**
     *设置窗口相关属性
     */
    PopupWindow mypopupwindow;
    private void showPopupWindower(){
        View view =LayoutInflater.from(CaseDetailsActivity.this).inflate(R.layout.pop_window,null);
        qx = (TextView) view.findViewById(R.id.quxiao);
        xg = (TextView) view.findViewById(R.id.xuigai);
        xglv  = (TextView) view.findViewById(R.id.xuigailvshi );
        kh = (TextView) view.findViewById(R.id.kehu);
        mypopupwindow = new PopupWindow(view);
        mypopupwindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        mypopupwindow.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        mypopupwindow.setFocusable(true);// 设置弹出窗口可获得焦点
        mypopupwindow.setTouchable(true);// 设置PopupWindow可触摸
        mypopupwindow.setOutsideTouchable(false);// 设置外部不可点击
        mypopupwindow.setBackgroundDrawable(new BitmapDrawable());// 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        qx.setOnClickListener(onClickListener);
        xg.setOnClickListener(onClickListener);
        xglv.setOnClickListener(onClickListener);
        kh.setOnClickListener(onClickListener);
        mypopupwindow.showAtLocation(CaseDetailsActivity.this.findViewById(R.id.modify_btn),Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
        lightOff();
        mypopupwindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //判断是不是点击了外部
                if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
                    return true;
                }
                //不是点击外部
//                getPopupwindow();
                return false;
            }
        });
        //消息时屏幕变亮
        mypopupwindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
                layoutParams.alpha=1.0f;
                getWindow().setAttributes(layoutParams);
            }
        });
    }

    /**
     * * 显示时屏幕变暗
     */
    private void lightOff() {
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.alpha=0.5f;
        getWindow().setAttributes(layoutParams);
    }

    /*
     * 获取PopupWindow实例
     */
    public void getPopupwindow(){
        if(mypopupwindow != null && mypopupwindow.isShowing()){
            mypopupwindow.dismiss();
            Log.i("getPopupwindow","==========dismiss");
            mypopupwindow = null;
        }else {
            showPopupWindower();
            Log.i("getPopupwindow","==========create");
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        Intent intent;
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back_btn:
                    finish();
                    break;
//                case R.id.modify_btn:
//                    getPopupwindow();
//                    break;
                case R.id.counsel://跳转到案件简介页面
                    intent = new Intent(CaseDetailsActivity.this,CaseRegisterActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("index",102);
                    bundle.putInt("ajlx",ajlx);
                    bundle.putString("id",id);
                    bundle.putString("ay",ay);
                    bundle.putString("ah_number",ah_number);
                    bundle.putString("wtr",wtr);
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
//                case R.id.xuigai://跳转到案件简介页面
//                    intent = new Intent(CaseDetailsActivity.this,CaseIntroduction.class);
//                    startActivity(intent);
//                    mypopupwindow.dismiss();
//                    break;
                case R.id.kehu://跳转到客户详情页面
                    intent = new Intent(CaseDetailsActivity.this,AddConsignorActivity.class);
                    startActivity(intent);
                    mypopupwindow.dismiss();
                    break;
//                case R.id.xuigailvshi://跳转到工作日志页面
//                    Toast.makeText(CaseDetailsActivity.this,"你点击了修改律师！",Toast.LENGTH_SHORT).show();
//                    mypopupwindow.dismiss();
//                    break;
//                case R.id.quxiao:
//                    mypopupwindow.dismiss();
//                    break;
                case R.id.charge_list://跳转到收款信息页面
                    intent = new Intent(CaseDetailsActivity.this, ChargeListActivity.class);
                    intent.putExtra("id",id);
                    intent.putExtra("ah_number",ah_number);
                    intent.putExtra("ysje",ysje);
                    startActivity(intent);
                    break;
                case R.id.work_log: //跳转到工作日志页面
                    intent = new Intent(CaseDetailsActivity.this,CreateWorkActivity.class);
                    intent.putExtra("type",1);
                    intent.putExtra("id",id);
                    intent.putExtra("ah_number",ah_number);
                    intent.putExtra("wtr",wtr);
                    intent.putExtra("dfdsr",dfdsr);
                    intent.putExtra("ay",ay);
                    startActivity(intent);
                    break;
                case R.id.zhencha://跳转到侦查机关页面
                    intent = new Intent(CaseDetailsActivity.this, CreateWorkActivity.class);
                    intent.putExtra("type",2);
                    intent.putExtra("id",id);
                    intent.putExtra("ah_number",ah_number);
                    intent.putExtra("wtr",wtr);
                    intent.putExtra("dfdsr",dfdsr);
                    intent.putExtra("ay",ay);
                    startActivity(intent);
                    break;
                case R.id.jiancha://跳转到检查机关页面
                    intent = new Intent(CaseDetailsActivity.this, CreateWorkActivity.class);
                    intent.putExtra("type",3);
                    intent.putExtra("id",id);
                    intent.putExtra("ah_number",ah_number);
                    intent.putExtra("wtr",wtr);
                    intent.putExtra("dfdsr",dfdsr);
                    intent.putExtra("ay",ay);
                    startActivity(intent);
                    break;
                case R.id.fayuan://跳转到法院开庭页面
                    intent = new Intent(CaseDetailsActivity.this, CreateWorkActivity.class);
                    intent.putExtra("type",4);
                    intent.putExtra("id",id);
                    intent.putExtra("ah_number",ah_number);
                    intent.putExtra("wtr",wtr);
                    intent.putExtra("dfdsr",dfdsr);
                    intent.putExtra("ay",ay);
                    startActivity(intent);
                    break;
                case R.id.sa_btn://收案申请按钮
                    case_state = "2";
                    postHttp();
                    break;
                case R.id.ja_btn://结案案件按钮
                    case_state = "5";
                    postHttp();
                    break;
                case R.id.zz_btn://中止案件按钮
                    case_state = "0";
                    postHttp();
                    break;
            }
        }
    };

    LoadingDialog loadingDialog;
    String update_date;
    String case_state;
    private void postHttp(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("上传中...");
        MainApi.getInstance(this).getxgzjztApi(id,case_state,update_date, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type == Constants.TYPE_SUCCESS){
                    loadingDialog.dismiss();
                    Toast.makeText(CaseDetailsActivity.this,"上传成功",Toast.LENGTH_SHORT).show();
                    finish();
                }else BaseApi.showErrMsg(CaseDetailsActivity.this,result);
            }
        });
    }
}
