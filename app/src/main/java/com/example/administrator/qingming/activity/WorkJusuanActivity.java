package com.example.administrator.qingming.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.administrator.qingming.dialog.LoadingDialog;
import com.example.administrator.qingming.interfaces.GetResultCallBack;
import com.example.administrator.qingming.model.Constants;
import com.example.administrator.qingming.model.ModelLsf;
import com.example.administrator.qingming.model.ModelPjgz;
import com.example.administrator.qingming.model.ModelScdj;
import com.example.administrator.qingming.qinminutils.GsonUtil;
import com.example.administrator.qingming.work.AddCaseActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/20 0020.
 */

public class WorkJusuanActivity extends Activity {
    private ImageView back_btn;
    private Button add;
    private TextView name;
    private LinearLayout dk_liner, gs_liner, ls_liner, ss_liner, wyj_liner, dklx,gspc,gspc1,shenghuo,
            one,two,scjt,lvshifei,wyj;
    int index;
    private EditText dkje, hkqx, lv;
    private TextView qx, llv, lx,area1,ajlx,lsf_start,lsf_finish;
    private TextView area,sc,count,gwbzj,szbzj,po,qtqs,count1,gwbzj1,szbzj1,po1,qtqs1,qtqs2,qtqs3,qtqs4;
    private EditText ygz,ssbdje;
    List<ModelPjgz.ResultBean> list;
    List<ModelScdj.ResultBean> list1;
    List<ModelLsf.ResultBean> list2;
    int g_avg;
    int cq_avg;
    private LoadingDialog loadingDialog;
    private TextView wyjjs,lvv,yqqx,choose_lv;
    private EditText lilv,lishi,benjin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_jisuan);

        initView();
        getHttp();
        Bundle bundle = getIntent().getExtras();
        index = bundle.getInt("index");
        if (index == 1) {
            name.setText("贷款利息计算");
            dk_liner.setVisibility(View.VISIBLE);
            dkje.addTextChangedListener(new newtextwatch(dkje));
            hkqx.addTextChangedListener(new newtextwatch(hkqx));
            lv.addTextChangedListener(new newtextwatch(lv));
        } else if (index == 2) {
            name.setText("工伤赔偿计算");
            gs_liner.setVisibility(View.VISIBLE);
            area.setOnClickListener(onclicklisten);
            sc.setOnClickListener(onclicklisten);
            ygz.addTextChangedListener(new newtextwatch(ygz));
        } else if (index == 3) {
            name.setText("人身损害计算");
        } else if (index == 6) {
            name.setText("违约金计算");
            wyj_liner.setVisibility(View.VISIBLE);
            lilv.addTextChangedListener(new newtextwatch(lilv));
            lishi.addTextChangedListener(new newtextwatch(lishi));
            benjin.addTextChangedListener(new newtextwatch(benjin));
            choose_lv.setOnClickListener(onclicklisten);
        }
    }

    private void initView() {
        loadingDialog = new LoadingDialog(this);
        list = new ArrayList<>();
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        back_btn = (ImageView) findViewById(R.id.back_btn);
        add = (Button) findViewById(R.id.add);
        name = (TextView) findViewById(R.id.name);
        dk_liner = (LinearLayout) findViewById(R.id.dk_liner);
        gs_liner = (LinearLayout) findViewById(R.id.gs_liner);
        ls_liner = (LinearLayout) findViewById(R.id.ls_liner);
        ss_liner = (LinearLayout) findViewById(R.id.ss_liner);
        wyj_liner = (LinearLayout) findViewById(R.id.wyj_liner);
        scjt = (LinearLayout) findViewById(R.id.scjt);
        dklx = (LinearLayout) findViewById(R.id.dklx);
        gspc = (LinearLayout) findViewById(R.id.gspc);
        gspc1 = (LinearLayout) findViewById(R.id.gspc1);
        shenghuo= (LinearLayout) findViewById(R.id.shenghuo);
        one= (LinearLayout) findViewById(R.id.one);
        two= (LinearLayout) findViewById(R.id.two);
        lvshifei = (LinearLayout) findViewById(R.id.lvshifei);
        wyj = (LinearLayout) findViewById(R.id.wyj);
        dkje = (EditText) findViewById(R.id.dkje);
        hkqx = (EditText) findViewById(R.id.hkqx);
        lv = (EditText) findViewById(R.id.lv);
        qx = (TextView) findViewById(R.id.qx);
        llv = (TextView) findViewById(R.id.llv);
        lx = (TextView) findViewById(R.id.lx);
        area= (TextView) findViewById(R.id.area);
        sc= (TextView) findViewById(R.id.sc);
        ygz= (EditText) findViewById(R.id.ygz);
        count= (TextView) findViewById(R.id.count);
        gwbzj= (TextView) findViewById(R.id.gwbzj);
        szbzj= (TextView) findViewById(R.id.szbzj);
        po= (TextView) findViewById(R.id.po);
        qtqs= (TextView) findViewById(R.id.qtqs);
        count1= (TextView) findViewById(R.id.count1);
        gwbzj1= (TextView) findViewById(R.id.gwbzj1);
        szbzj1= (TextView) findViewById(R.id.szbzj1);
        po1= (TextView) findViewById(R.id.po1);
        qtqs1= (TextView) findViewById(R.id.qtqs1);
        qtqs2= (TextView) findViewById(R.id.qtqs2);
        qtqs3= (TextView) findViewById(R.id.qtqs3);
        qtqs4= (TextView) findViewById(R.id.qtqs4);
        area1= (TextView) findViewById(R.id.area1);
        ajlx= (TextView) findViewById(R.id.ajlx);
        ssbdje = (EditText) findViewById(R.id.ssbdje);
        lsf_start= (TextView) findViewById(R.id.lsf_start);
        lsf_finish= (TextView) findViewById(R.id.lsf_finish);
        wyjjs= (TextView) findViewById(R.id.wyjjs);
        lvv= (TextView) findViewById(R.id.lvv);
        yqqx= (TextView) findViewById(R.id.yqqx);
        choose_lv = (TextView) findViewById(R.id.choose_lv);
        lilv= (EditText) findViewById(R.id.lilv);
        lishi= (EditText) findViewById(R.id.lishi);
        benjin= (EditText) findViewById(R.id.benjin);

        add.setOnClickListener(onclicklisten);
        back_btn.setOnClickListener(onclicklisten);
    }

    View.OnClickListener onclicklisten = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.add:
                    if(index == 2){
                        if(sc.getText().toString().equals("一级")){
                            scdj = 1;
                        }else if(sc.getText().toString().equals("二级") ){
                            scdj = 2;
                        }else if(sc.getText().toString().equals("三级") ){
                            scdj = 3;
                        }else if(sc.getText().toString().equals("四级") ){
                            scdj = 4;
                        }else  if(sc.getText().toString().equals("五级")){
                            scdj = 5;
                        }else if(sc.getText().toString().equals("六级") ){
                            scdj = 6;
                        }else if(sc.getText().toString().equals("七级") ){
                            scdj = 7;
                        }else if(sc.getText().toString().equals("八级") ){
                            scdj = 8;
                        }else if(sc.getText().toString().equals("九级") ){
                            scdj = 9;
                        } else if (sc.getText().toString().equals("十级")) {
                            scdj = 10;
                        }else if(sc.getText().toString().equals("工亡")){
                            scdj = 11;
                        }
                        if(!TextUtils.isEmpty(ygz.getText())){
                            getScHttp();
                        }else {
                            Toast.makeText(WorkJusuanActivity.this,"月工资不能为空",Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        jisuan();
                    }
                    break;
                case R.id.back_btn:
                    finish();
                    break;
                case R.id.area:
                    showDialog();
                    final String[] str = {"重庆"};
                    builder.setItems(str, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            area.setText(str[i]);
                        }
                    });
                    builder.show();
                    break;
                case R.id.sc:
                    showDialog();
                    final String[] strs = {"工亡","一级","二级","三级","四级","五级","六级","七级","八级","九级","十级"};
                    builder.setItems(strs, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            sc.setText(strs[i]);
                        }
                    });
                    builder.show();
                    break;
                case R.id.area1:
                    showDialog();
                    final String[] str1 = {"重庆"};
                    builder.setItems(str1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            area1.setText(str1[i]);
                        }
                    });
                    builder.show();
                    break;
                case R.id.ajlx:
                    showDialog();
                    final String[] strss = {"民事","刑事侦查阶段","刑事起诉阶段","刑事审判阶段","行政"};
                    builder.setItems(strss, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            ajlx.setText(strss[i]);
                        }
                    });
                    builder.show();
                    break;
                case R.id.choose_lv:
                    showDialog();
                    final String[] chooselv = {"天利率","月利率","年利率"};
                    builder.setItems(chooselv, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            choose_lv.setText(chooselv[i]);
                        }
                    });
                    builder.show();
                    break;
            }
        }
    };

    AlertDialog.Builder builder;
    private void showDialog(){
        builder = new AlertDialog.Builder(WorkJusuanActivity.this,AlertDialog.THEME_HOLO_LIGHT);
    }

    private void jisuan() {
        if (index == 1) {
            if(!TextUtils.isEmpty(dkje.getText())){
                if(!TextUtils.isEmpty(hkqx.getText())){
                    if(!TextUtils.isEmpty(lv.getText())){
                        dklx.setVisibility(View.VISIBLE);
                        double a = Double.parseDouble(dkje.getText().toString());
                        double b = Double.parseDouble(hkqx.getText().toString());
                        double c = Double.parseDouble(lv.getText().toString()) * 0.01;
                        Log.e("====>", "" + a + "  " + b + "  " + c);
                        Log.e("====>", "" + a * b * c);
                        qx.setText(hkqx.getText().toString());
                        llv.setText(lv.getText().toString() + "%");
                        lx.setText(""+a * b * c);
                    }else {
                        Toast.makeText(WorkJusuanActivity.this, "请输入利率", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(WorkJusuanActivity.this, "请输入还款金额", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(WorkJusuanActivity.this, "请输入本金", Toast.LENGTH_SHORT).show();
            }

        } else if (index == 5) {

        } else if (index == 6) {
            if(!TextUtils.isEmpty(benjin.getText())){
                if(!TextUtils.isEmpty(lishi.getText())){
                    if(!TextUtils.isEmpty(lilv.getText())){
                        wyj.setVisibility(View.VISIBLE);
                        double bj = Integer.valueOf(benjin.getText().toString());
                        int a = Integer.valueOf(lishi.getText().toString());
                        double c = Integer.valueOf(lilv.getText().toString())*0.01;
                        double d = 0;
                        if(choose_lv.getText().toString().equals("天利率")){
                            d = bj*a*c;
                            yqqx.setText(""+a);
                            lvv.setText(""+c);
                            wyjjs.setText("" + d);
                        }else if(choose_lv.getText().toString().equals("月利率")){
                            d = bj*a*c;
                            yqqx.setText(""+a);
                            lvv.setText(""+c);
                            wyjjs.setText("" + d);
                        }else if(choose_lv.getText().toString().equals("年利率")){
                            d = bj*a*c;
                            yqqx.setText(""+a);
                            lvv.setText(""+c);
                            wyjjs.setText("" + d);
                        }
                    }else {
                        Toast.makeText(WorkJusuanActivity.this, "请输入利率", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(WorkJusuanActivity.this, "请输入历时几天", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(WorkJusuanActivity.this, "请输入本金", Toast.LENGTH_SHORT).show();
            }

        }
    }



    private void getHttp(){
        MainApi.getInstance(this).getpjgzApi(new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type == Constants.TYPE_SUCCESS){
                    List<ModelPjgz.ResultBean> resultBean = GsonUtil.fromJsonList(new Gson(),result,ModelPjgz.ResultBean.class);
                    list.addAll(resultBean);
                    g_avg = list.get(0).getAvg_wag();//全国平均工资
                    cq_avg = list.get(1).getAvg_wag();//重庆平均工资
                }else BaseApi.showErrMsg(WorkJusuanActivity.this,result);
            }
        });
    }

    private int qmarea_id;
    private int scdj;
    private double wqhl;
    private double dbfhl;
    private double bfhl;
    private double pch;
    private double pca;
    private double pcb;
    private double pcc;
    private double pcd;
    private double pce;
    private double pcf;
    private double pcg;
    private void getScHttp(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("正在查询...");
        MainApi.getInstance(this).getgspcApi(1,scdj,new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loadingDialog.dismiss();
                if(type == Constants.TYPE_SUCCESS){
                    List<ModelScdj.ResultBean> resultbean = GsonUtil.fromJsonList(new Gson(),result,ModelScdj.ResultBean.class);
                    list1.clear();
                    list1.addAll(resultbean);
                      wqhl = list1.get(0).getWqhl();//完全护理
                      dbfhl= list1.get(0).getDbfhl();//大部分护理
                      bfhl= list1.get(0).getBfhl();//部分护理
                      pch= list1.get(0).getPch();//一次性工亡补助金
                      pca= list1.get(0).getPca();//一次性伤残补助金
                      pcb= list1.get(0).getPcb();//伤残津贴月数
                      pcc= list1.get(0).getPcc();//一次性工伤医疗补助金
                      pcd= list1.get(0).getPcd();//一次性伤残就业补助金
                      pce= list1.get(0).getPce();//丧葬费
                      pcf= list1.get(0).getPcf();//供养亲属抚恤金（配偶）
                      pcg= list1.get(0).getPcg();//供养亲属抚恤金（其它金属）

                    Log.e("===>>>",""+wqhl+dbfhl+bfhl+pch+pca+pcb+pcc+pcd+pce+pcf+pcg);
                    if(scdj == 11){
                        gspc.setVisibility(View.VISIBLE);
                        gspc1.setVisibility(View.GONE);
                        double a = g_avg * pch ;//一次性工亡补助金
                        int s = Integer.valueOf(ygz.getText().toString());//输入工资
                        if(s > cq_avg * 3){
                            s = cq_avg * 3;
                        }else {
                            s= Integer.valueOf(ygz.getText().toString());
                        }
                        double b = g_avg * pce;//一丧葬补助金
                        double c = s * pcf;//配偶金
                        double d = s * pcg ;//其他亲属补助金
                        double sum = a+b;
                        count.setText(""+sum);
                        gwbzj.setText(""+a);
                        szbzj.setText(""+b);
                        po.setText(""+c);
                        qtqs.setText(""+d);
                    }else {
                        gspc.setVisibility(View.GONE);
                        gspc1.setVisibility(View.VISIBLE);
                        int s = Integer.valueOf(ygz.getText().toString());//输入工资
                        double a = s * bfhl;
                        double b = s * dbfhl;
                        double c = s * wqhl;
                        if(wqhl != 0.00){
                            shenghuo.setVisibility(View.VISIBLE);
                            po1.setText(""+a);
                            qtqs1.setText(""+b);
                            qtqs2.setText(""+c);
                        }else {
                            shenghuo.setVisibility(View.GONE);
                        }
                        //伤残津贴月数
                        double d = s * pcb;
                        if(pcb != 0.00){
                            scjt.setVisibility(View.VISIBLE);
                            szbzj1.setText(""+d);
                        }else {
                            scjt.setVisibility(View.GONE);
                        }

                        //一次性伤残补助金
                        double e = s * pca;
                        double f = s * pcc;
                        double g = s*pcd;
                        if(pca != 0.00){
                            gwbzj1.setText(""+e);
                            //一次性工伤医疗补助金
                        }

                        if(pcc != 0.00){
                            one.setVisibility(View.VISIBLE);
                            qtqs3.setText(""+f);
                        }else {
                            one.setVisibility(View.GONE);
                        }

                        //一次性伤残就业补助金
                        if(pcd != 0.00){
                            two.setVisibility(View.VISIBLE);
                            qtqs4.setText(""+g);
                        }else {
                            two.setVisibility(View.GONE);
                        }

                        double sum = a+b+c+d+e+f+g;
                        count1.setText(""+sum);
                    }

                }else BaseApi.showErrMsg(WorkJusuanActivity.this,result);
            }
        });
    }


    class newtextwatch implements TextWatcher {
        private EditText edit;

        public newtextwatch(EditText editText) {
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
            Log.i("", "" + msg4);
        }
    }
}
