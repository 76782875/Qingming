package com.example.administrator.qingming.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.adapter.MyCaseAdapter;
import com.example.administrator.qingming.api.BaseApi;
import com.example.administrator.qingming.api.MainApi;
import com.example.administrator.qingming.dialog.LoadingDialog;
import com.example.administrator.qingming.interfaces.GetResultCallBack;
import com.example.administrator.qingming.model.Constants;
import com.example.administrator.qingming.news.casedetails.CaseDetailsActivity;
import com.example.administrator.qingming.qinminutils.GsonUtil;
import com.example.administrator.qingming.work.ExamineActivity;
import com.example.administrator.qingming.model.MyCaseModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/19 0019.
 */

public class MyCaseActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener{
    private TextView ex1,ex2,ex3,ex4,shenpi;
    private View line1,line2,line3,line4;
    private List<MyCaseModel.ResultBean> list,list1,list2,list3,list4;
    private ImageView backbtn;
    private RecyclerView recyclerView;
    private MyCaseAdapter mAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int index = 0;
    String cc;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycase);

        SharedPreferences sharedPreferences = getSharedPreferences("qinmin", Context.MODE_PRIVATE);
        create_id = sharedPreferences.getString("id","");
        company_id = sharedPreferences.getString("cid","");

        initView();
        getbundle();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置垂直方向
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
}

    private void getbundle(){
        Bundle bundle = getIntent().getExtras();
        cc = bundle.getString("cc",""); //获取上个页面传递的值，
        if(cc.equals("1")){
//            值为1是我的案件页面
            getHttp();
        }else {
//            为2是律师案件页面
            getHttps();
        }

        index = bundle.getInt("index");
        if(index == 0){
            ex1.setTextColor(getResources().getColor(R.color.black));
            ex2.setTextColor(getResources().getColor(R.color.blue));
            ex3.setTextColor(getResources().getColor(R.color.blue));
            ex4.setTextColor(getResources().getColor(R.color.blue));
            line1.setVisibility(View.VISIBLE);
            line2.setVisibility(View.INVISIBLE);
            line3.setVisibility(View.INVISIBLE);
            line4.setVisibility(View.INVISIBLE);
        }else if(index == 1){
            ex1.setTextColor(getResources().getColor(R.color.blue));
            ex2.setTextColor(getResources().getColor(R.color.black));
            ex3.setTextColor(getResources().getColor(R.color.blue));
            ex4.setTextColor(getResources().getColor(R.color.blue));
            line2.setVisibility(View.VISIBLE);
            line1.setVisibility(View.INVISIBLE);
            line3.setVisibility(View.INVISIBLE);
            line4.setVisibility(View.INVISIBLE);
        }else if(index == 2){
            ex1.setTextColor(getResources().getColor(R.color.blue));
            ex2.setTextColor(getResources().getColor(R.color.blue));
            ex3.setTextColor(getResources().getColor(R.color.black));
            ex4.setTextColor(getResources().getColor(R.color.blue));
            line3.setVisibility(View.VISIBLE);
            line2.setVisibility(View.INVISIBLE);
            line1.setVisibility(View.INVISIBLE);
            line4.setVisibility(View.INVISIBLE);
        }else {
            ex1.setTextColor(getResources().getColor(R.color.blue));
            ex2.setTextColor(getResources().getColor(R.color.blue));
            ex3.setTextColor(getResources().getColor(R.color.blue));
            ex4.setTextColor(getResources().getColor(R.color.black));
            line4.setVisibility(View.VISIBLE);
            line2.setVisibility(View.INVISIBLE);
            line3.setVisibility(View.INVISIBLE);
            line1.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getbundle();
    }

    private void initView() {
        list = new ArrayList<>();
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();
        list4 = new ArrayList<>();
        loadingDialog = new LoadingDialog(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        ex1 = (TextView) findViewById(R.id.examine);
        ex2 = (TextView) findViewById(R.id.examine1);
        ex3 = (TextView) findViewById(R.id.examine2);
        ex4 = (TextView) findViewById(R.id.examine3);
        line1 = findViewById(R.id.examine_line1);
        line2 = findViewById(R.id.line2);
        line3 = findViewById(R.id.line3);
        line4 = findViewById(R.id.line4);
        backbtn = (ImageView) findViewById(R.id.back_btn);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);

        ex1.setOnClickListener(onclicklistener);
        ex2.setOnClickListener(onclicklistener);
        ex3.setOnClickListener(onclicklistener);
        ex4.setOnClickListener(onclicklistener);
        backbtn.setOnClickListener(onclicklistener);
        swipeRefreshLayout.setOnRefreshListener(this);

        mAdapter = new MyCaseAdapter(this,list4);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new MyCaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                String id = list4.get(i).getId();
                String case_state= list4.get(i).getCase_state();
                String ay= list4.get(i).getAy();
                String ah_number= list4.get(i).getAh_number();
                String wtr= list4.get(i).getWtr();
                String lxdh = list4.get(i).getTel();
                String dsr = list4.get(i).getDsr();
                String dfdsr= list4.get(i).getDfdsr();
                int dlf= list4.get(i).getDlf();
                int jzf= list4.get(i).getJzf();
                int ajlx = list4.get(i).getAjlx();
                String sffs = null;
                if(list4.get(i).getSffs().equals("0")){
                     sffs = "免费";
                }else if(list4.get(i).getSffs().equals("1")){
                     sffs = "计件收费";
                }else if(list4.get(i).getSffs().equals("2")){
                    sffs = "按标的比例收费";
                }else if(list4.get(i).getSffs().equals("3")){
                    sffs = "风险代理收费";
                }else if(list4.get(i).getSffs().equals("4")){
                    sffs = "固定+风险代理收费";
                }
                String name = list4.get(i).getName();
                String sarq =  list4.get(i).getSarq();
                //截取 月 和 日 字符串
                String year = sarq.substring(0, 10);
                String court =  list4.get(i).getCourt();
                String detention =  list4.get(i).getDetention();
                String police =  list4.get(i).getPolice();
                String procuratorate =  list4.get(i).getProcuratorate();
                String slbm =  list4.get(i).getSlbm();
                String bzsm =  list4.get(i).getBzsm();
                String ssdw =  list4.get(i).getSsdw();
                String ssbd =  list4.get(i).getSsbd();
                String ssjd =  list4.get(i).getSsjd();
                String badq =  list4.get(i).getBadq();

                Intent intent = new Intent(MyCaseActivity.this, CaseDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id",id);
                bundle.putString("ay",ay);
                bundle.putString("cc",cc);
                bundle.putString("ah_number",ah_number);
                bundle.putString("wtr",wtr);
                bundle.putString("lxdh",lxdh);
                bundle.putString("dsr",dsr);
                bundle.putString("dfdsr",dfdsr);
                bundle.putString("sffs",sffs);
                bundle.putString("name",name);
                bundle.putString("sarq",year);
                bundle.putString("court",court);
                bundle.putString("detention",detention);
                bundle.putString("police",police);
                bundle.putString("procuratorate",procuratorate);
                bundle.putString("slbm",slbm);
                bundle.putString("bzsm",bzsm);
                bundle.putString("ssdw",ssdw);
                bundle.putString("ssbd",ssbd);
                bundle.putString("ssjd",ssjd);
                bundle.putString("badq",badq);
                bundle.putInt("dlf",dlf);
                bundle.putInt("jzf",jzf);
                bundle.putInt("ajlx",ajlx);
                bundle.putString("case_state",case_state);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    View.OnClickListener onclicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.examine:
                    index = 0;
                    setListData();
                    ex1.setTextColor(getResources().getColor(R.color.black));
                    ex2.setTextColor(getResources().getColor(R.color.blue));
                    ex3.setTextColor(getResources().getColor(R.color.blue));
                    ex4.setTextColor(getResources().getColor(R.color.blue));
                    line1.setVisibility(View.VISIBLE);
                    line2.setVisibility(View.INVISIBLE);
                    line3.setVisibility(View.INVISIBLE);
                    line4.setVisibility(View.INVISIBLE);
                    break;
                case R.id.examine1:
                    index = 1;
                    setListData();
                    ex1.setTextColor(getResources().getColor(R.color.blue));
                    ex2.setTextColor(getResources().getColor(R.color.black));
                    ex3.setTextColor(getResources().getColor(R.color.blue));
                    ex4.setTextColor(getResources().getColor(R.color.blue));
                    line2.setVisibility(View.VISIBLE);
                    line1.setVisibility(View.INVISIBLE);
                    line3.setVisibility(View.INVISIBLE);
                    line4.setVisibility(View.INVISIBLE);
                    break;
                case R.id.examine2:
                    index = 2;
                    setListData();
                    ex1.setTextColor(getResources().getColor(R.color.blue));
                    ex2.setTextColor(getResources().getColor(R.color.blue));
                    ex3.setTextColor(getResources().getColor(R.color.black));
                    ex4.setTextColor(getResources().getColor(R.color.blue));
                    line3.setVisibility(View.VISIBLE);
                    line2.setVisibility(View.INVISIBLE);
                    line1.setVisibility(View.INVISIBLE);
                    line4.setVisibility(View.INVISIBLE);
                    break;
                case R.id.examine3:
                    index = 3;
                    setListData();
                    ex1.setTextColor(getResources().getColor(R.color.blue));
                    ex2.setTextColor(getResources().getColor(R.color.blue));
                    ex3.setTextColor(getResources().getColor(R.color.blue));
                    ex4.setTextColor(getResources().getColor(R.color.black));
                    line4.setVisibility(View.VISIBLE);
                    line2.setVisibility(View.INVISIBLE);
                    line3.setVisibility(View.INVISIBLE);
                    line1.setVisibility(View.INVISIBLE);
                    break;
                case R.id.back_btn:
                    finish();
                    break;
            }
        }
    };

    private void setListData(){
        list4.clear();
        if (index == 0)
            list4 .addAll( list );
        else if(index == 1)
            list4.addAll(list1);
        else if(index == 2)
            list4.addAll(list2);
        else if(index == 3)
            list4.addAll(list3);
        mAdapter.notifyDataSetChanged();
    }

    private String create_id;
    private void getHttp(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("加载中...");
        MainApi.getInstance(this).getmycaseApi(create_id, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                swipeRefreshLayout.setRefreshing(false);
                loadingDialog.dismiss();
                if(type == Constants.TYPE_SUCCESS){
                    List<MyCaseModel.ResultBean> resultBeen = GsonUtil.fromJsonList(new Gson(),result, MyCaseModel.ResultBean.class);
                    list.clear();
                    list1.clear();
                    list2.clear();
                    list3.clear();
                    for (int i=0;i<resultBeen.size();i++){
                        // 预收案：-1，1  审批中：2，3，5，6   办案中：4，-4  已结案：7
                        if(resultBeen.get(i).getCase_state().equals("-1") || resultBeen.get(i).getCase_state().equals("1")){
                            list.add(resultBeen.get(i));

                        }else if(resultBeen.get(i).getCase_state().equals("2") ||
                                resultBeen.get(i).getCase_state().equals("3") ||
                                resultBeen.get(i).getCase_state().equals("5") ||
                                resultBeen.get(i).getCase_state().equals("6")){
                            list1.add(resultBeen.get(i));

                        }else if(resultBeen.get(i).getCase_state().equals("4") ||
                                resultBeen.get(i).getCase_state().equals("-4")) {
                            list2.add(resultBeen.get(i));

                        }else if(resultBeen.get(i).getCase_state().equals("7")) {
                            list3.add(resultBeen.get(i));
                        }

                    }
                    setListData();
                }else BaseApi.showErrMsg(MyCaseActivity.this,result);
            }
        });
    }

    private String company_id;
    private void getHttps(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("加载中...");
        MainApi.getInstance(this).getlscaseApi(company_id, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                swipeRefreshLayout.setRefreshing(false);
                loadingDialog.dismiss();
                if(type == Constants.TYPE_SUCCESS){
                    List<MyCaseModel.ResultBean> resultBeen = GsonUtil.fromJsonList(new Gson(),result, MyCaseModel.ResultBean.class);
                    list.clear();
                    list1.clear();
                    list2.clear();
                    list3.clear();
                    for (int i=0;i<resultBeen.size();i++){
                        // 预收案：-1，1  审批中：2，3，5，6   办案中：4，-4  已结案：7
                        if(resultBeen.get(i).getCase_state().equals("-1") || resultBeen.get(i).getCase_state().equals("1")){
                            list.add(resultBeen.get(i));
                            Log.e("list",""+i);
                        }else if(resultBeen.get(i).getCase_state().equals("2") ||
                                resultBeen.get(i).getCase_state().equals("3") ||
                                resultBeen.get(i).getCase_state().equals("5") ||
                                resultBeen.get(i).getCase_state().equals("6")){
                            list1.add(resultBeen.get(i));
                            Log.e("list1",""+i);
                        }else if(resultBeen.get(i).getCase_state().equals("4") ||
                                resultBeen.get(i).getCase_state().equals("-4")) {
                            list2.add(resultBeen.get(i));
                            Log.e("list2",""+i);
                        }else if(resultBeen.get(i).getCase_state().equals("7")) {
                            list3.add(resultBeen.get(i));
                        }


                    }
                    setListData();
                }else BaseApi.showErrMsg(MyCaseActivity.this,result);
            }
        });
    }

    @Override
    public void onRefresh() {
        if(cc.equals("1")){
//            值为1是我的案件页面，
            getHttp();
        }else {
//            为2是律师案件页面
            getHttps();
        }
    }
}
