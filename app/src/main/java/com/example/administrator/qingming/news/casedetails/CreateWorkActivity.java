package com.example.administrator.qingming.news.casedetails;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.activity.FaYuanActivity;
import com.example.administrator.qingming.activity.JianChaActivity;
import com.example.administrator.qingming.activity.ZhenChaActivity;
import com.example.administrator.qingming.adapter.CreateWorkAdapter;
import com.example.administrator.qingming.adapter.FaYuanAdapter;
import com.example.administrator.qingming.adapter.JianChaAdapter;
import com.example.administrator.qingming.adapter.ZhenChaAdapter;
import com.example.administrator.qingming.api.BaseApi;
import com.example.administrator.qingming.api.MainApi;
import com.example.administrator.qingming.dialog.LoadingDialog;
import com.example.administrator.qingming.interfaces.GetResultCallBack;
import com.example.administrator.qingming.model.Constants;
import com.example.administrator.qingming.model.ModelFaYuan;
import com.example.administrator.qingming.model.ModelJiancha;
import com.example.administrator.qingming.model.ModelZhenCha;
import com.example.administrator.qingming.qinminutils.GsonUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/17 0017.
 */

public class CreateWorkActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener{
    private ImageView backbtn,addbtn;
    private ListView listView;
    private List<CreateModel.ResultBean> list;
    private List<ModelZhenCha.ResultBean> zhenchalist;
    private List<ModelJiancha.ResultBean> jianchalist;
    private List<ModelFaYuan.ResultBean> fayuanlist;
    private String ah_number,glid,wtr,dfdsr,ay,dsr;
    private CreateWorkAdapter createWorkAdapter;
    private ZhenChaAdapter zhenChaAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LoadingDialog loadingDialog;
    private TextView name;
    private int type;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_work);


        initView();
        getbundle();

    }

    //从上个页面传递的数据
    private void getbundle(){
        Bundle bundle = getIntent().getExtras();
        glid = bundle.getString("id","");
        ah_number = bundle.getString("ah_number","");
        wtr = bundle.getString("wtr","");
        dsr = bundle.getString("dsr","");
        dfdsr = bundle.getString("dfdsr","");
        ay = bundle.getString("ay","");
        type = bundle.getInt("type");
        if(type == 1){
            name.setText("日志列表");
            getHttp();
        }else if(type == 2){
            name.setText("侦查机关列表");
            getZhenChaHttp();
        }else if(type == 3){
            name.setText("检查机关列表");
            getJianChaHttp();
        }else {
            name.setText("法院开庭列表");
            getFaYuan();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        getbundle();
        super.onNewIntent(intent);
    }

    private void initView() {
        loadingDialog = new LoadingDialog(this);
        list = new ArrayList<>();
        zhenchalist = new ArrayList<>();
        jianchalist = new ArrayList<>();
        fayuanlist = new ArrayList<>();
        backbtn = (ImageView) findViewById(R.id.back_btn);
        addbtn = (ImageView) findViewById(R.id.add_btn);
        listView = (ListView) findViewById(R.id.listview);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        name = (TextView) findViewById(R.id.name);

        backbtn.setOnClickListener(onclicklistener);
        addbtn.setOnClickListener(onclicklistener);
        swipeRefreshLayout.setOnRefreshListener(this);
    }


    View.OnClickListener onclicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back_btn:
                    finish();
                    break;
                case R.id.add_btn:
                    Intent intent;
                    if(type == 1){
                        intent = new Intent(CreateWorkActivity.this, CreateWorkLogActivity.class);
                    }else if(type == 2){
                        intent = new Intent(CreateWorkActivity.this, ZhenChaActivity.class);
                    }else if(type == 3){
                        intent = new Intent(CreateWorkActivity.this, JianChaActivity.class);
                    }else {
                        intent = new Intent(CreateWorkActivity.this, FaYuanActivity.class);
                    }
                    intent.putExtra("zid",1);
                    intent.putExtra("id",glid);
                    intent.putExtra("ah_number",ah_number);
                    intent.putExtra("wtr",wtr);
                    intent.putExtra("dsr",dsr);
                    intent.putExtra("dfdsr",dfdsr);
                    intent.putExtra("ay",ay);
                    startActivity(intent);
                    break;
            }
        }
    };

    private void getHttp(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("加载中...");
        MainApi.getInstance(this).getrizhiApi(glid, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loadingDialog.dismiss();
                swipeRefreshLayout.setRefreshing(false);
                if(type == Constants.TYPE_SUCCESS){
                    List<CreateModel.ResultBean> resultBeen = GsonUtil.fromJsonList(new Gson(),result,
                            CreateModel.ResultBean.class);
                    list.clear();
                    list.addAll(resultBeen);
                    createWorkAdapter = new CreateWorkAdapter(CreateWorkActivity.this,list);
                    listView.setAdapter(createWorkAdapter);

                    createWorkAdapter.notifyDataSetChanged();
                }else BaseApi.showErrMsg(CreateWorkActivity.this,result);
            }
        });
    }

    private void getZhenChaHttp(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("加载中...");
        MainApi.getInstance(this).getzhenchaApi(glid, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loadingDialog.dismiss();
                swipeRefreshLayout.setRefreshing(false);
                if(type == Constants.TYPE_SUCCESS){
                    List<ModelZhenCha.ResultBean> resultBeen = GsonUtil.fromJsonList(new Gson(),result,
                            ModelZhenCha.ResultBean.class);
                    zhenchalist.clear();
                    zhenchalist.addAll(resultBeen);

                    zhenChaAdapter = new ZhenChaAdapter(CreateWorkActivity.this,zhenchalist);
                    listView.setAdapter(zhenChaAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(CreateWorkActivity.this,ZhenChaActivity.class);
                            String cid = zhenchalist.get(position).getId();
                            String bajg = zhenchalist.get(position).getBajg();
                            String bzsm = zhenchalist.get(position).getBzsm();
                            String zcah = zhenchalist.get(position).getZcah();
                            String bm = zhenchalist.get(position).getBm();
                            String cbr = zhenchalist.get(position).getCbr();
                            String tel = zhenchalist.get(position).getTel();
                            String xjsj = zhenchalist.get(position).getXjsj();
                            String dbsj = zhenchalist.get(position).getDbsj();
                            String zcqx_star = zhenchalist.get(position).getZcqx_star();
                            String zcqx_end = zhenchalist.get(position).getZcqx_end();
                            String bczcqx_star = zhenchalist.get(position).getBczcqx_star();
                            String bczcqx_end = zhenchalist.get(position).getBczcqx_end();
                            String ecbczc_star = zhenchalist.get(position).getEcbczc_star();
                            String ecbczc_end = zhenchalist.get(position).getEcbczc_end();
                            intent.putExtra("zid",2);
                            intent.putExtra("aid",cid);
                            intent.putExtra("id",glid);
                            intent.putExtra("ah_number",ah_number);
                            intent.putExtra("wtr",wtr);
                            intent.putExtra("dsr",dsr);
                            intent.putExtra("dfdsr",dfdsr);
                            intent.putExtra("ay",ay);
                            intent.putExtra("bajg",bajg);
                            intent.putExtra("bzsm",bzsm);
                            intent.putExtra("zcah",zcah);
                            intent.putExtra("bm",bm);
                            intent.putExtra("cbr",cbr);
                            intent.putExtra("tel",tel);
                            intent.putExtra("xjsj",xjsj);
                            intent.putExtra("dbsj",dbsj);
                            intent.putExtra("zcqx_star",zcqx_star);
                            intent.putExtra("zcqx_end",zcqx_end);
                            intent.putExtra("bczcqx_star",bczcqx_star);
                            intent.putExtra("bczcqx_end",bczcqx_end);
                            intent.putExtra("ecbczc_star",ecbczc_star);
                            intent.putExtra("ecbczc_end",ecbczc_end);
                            startActivity(intent);
                        }
                    });

                    zhenChaAdapter.notifyDataSetChanged();
                }else BaseApi.showErrMsg(CreateWorkActivity.this,result);
            }
        });
    }

    JianChaAdapter jianChaAdapter;
    private void getJianChaHttp(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("加载中...");
        MainApi.getInstance(this).getjianchaApi(glid, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loadingDialog.dismiss();
                swipeRefreshLayout.setRefreshing(false);
                if(type == Constants.TYPE_SUCCESS){
                    List<ModelJiancha.ResultBean> resultBeen = GsonUtil.fromJsonList(new Gson(),result,
                            ModelJiancha.ResultBean.class);
                    jianchalist.clear();
                    jianchalist.addAll(resultBeen);

                    jianChaAdapter = new JianChaAdapter(CreateWorkActivity.this,jianchalist);
                    listView.setAdapter(jianChaAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(CreateWorkActivity.this,JianChaActivity.class);
                            String cid = jianchalist.get(position).getId();
                            String bajg = jianchalist.get(position).getBajg();
                            String bzsm = jianchalist.get(position).getBzsm();
                            String badd = jianchalist.get(position).getBadd();
                            String bm = jianchalist.get(position).getBm();
                            String cbr = jianchalist.get(position).getCbr();
                            String tel = jianchalist.get(position).getTel();
                            String scqs_star = jianchalist.get(position).getScqs_star();
                            String scqs_end = jianchalist.get(position).getScqs_end();
                            intent.putExtra("zid",2);
                            intent.putExtra("aid",cid);
                            intent.putExtra("id",glid);
                            intent.putExtra("ah_number",ah_number);
                            intent.putExtra("wtr",wtr);
                            intent.putExtra("dsr",dsr);
                            intent.putExtra("dfdsr",dfdsr);
                            intent.putExtra("ay",ay);
                            intent.putExtra("bajg",bajg);
                            intent.putExtra("bzsm",bzsm);
                            intent.putExtra("badd",badd);
                            intent.putExtra("bm",bm);
                            intent.putExtra("cbr",cbr);
                            intent.putExtra("tel",tel);
                            intent.putExtra("scqs_star",scqs_star);
                            intent.putExtra("scqs_end",scqs_end);
                            startActivity(intent);
                        }
                    });
                    jianChaAdapter.notifyDataSetChanged();
                }else BaseApi.showErrMsg(CreateWorkActivity.this,result);
            }
        });
    }

    FaYuanAdapter faYuanAdapter;
    private void getFaYuan(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("加载中...");
        MainApi.getInstance(this).getfayuanApi(glid, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loadingDialog.dismiss();
                swipeRefreshLayout.setRefreshing(false);
                if(type == Constants.TYPE_SUCCESS){
                    List<ModelFaYuan.ResultBean> resultBeen = GsonUtil.fromJsonList(new Gson(),result,
                            ModelFaYuan.ResultBean.class);
                    fayuanlist.clear();
                    fayuanlist.addAll(resultBeen);

                    faYuanAdapter = new FaYuanAdapter(CreateWorkActivity.this,fayuanlist);
                    listView.setAdapter(faYuanAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(CreateWorkActivity.this,FaYuanActivity.class);
                            String cid = fayuanlist.get(position).getId();
                            String spcx = fayuanlist.get(position).getSpcx();
                            String spjg = fayuanlist.get(position).getSpjg();
                            String ladate= fayuanlist.get(position).getLadate();
                            String ktdate= fayuanlist.get(position).getKtdate();
                            String spdate= fayuanlist.get(position).getSpdate();
                            String ssdate= fayuanlist.get(position).getSsdate();
                            String ft= fayuanlist.get(position).getFt();
                            String zsfg= fayuanlist.get(position).getZsfg();
                            String zsfgtel= fayuanlist.get(position).getZsfgtel();
                            String sjy= fayuanlist.get(position).getSjy();
                            String sjytel= fayuanlist.get(position).getSjytel();
                            String bzsm2= fayuanlist.get(position).getBzsm2();
                            String update_zt= fayuanlist.get(position).getUpdate_zt();
//                            String wtr= fayuanlist.get(position).getWtr();
//                            String ah_number= fayuanlist.get(position).getAh_number();
                            intent.putExtra("zid",2);
                            intent.putExtra("aid",cid);
                            intent.putExtra("id",glid);
                            intent.putExtra("ah_number",ah_number);
                            intent.putExtra("wtr",wtr);
                            intent.putExtra("dsr",dsr);
                            intent.putExtra("dfdsr",dfdsr);
                            intent.putExtra("ay",ay);
                            intent.putExtra("spcx",spcx);
                            intent.putExtra("spjg",spjg);
                            intent.putExtra("ladate",ladate);
                            intent.putExtra("ktdate",ktdate);
                            intent.putExtra("spdate",spdate);
                            intent.putExtra("ssdate",ssdate);
                            intent.putExtra("ft",ft);
                            intent.putExtra("zsfg",zsfg);
                            intent.putExtra("zsfgtel",zsfgtel);
                            intent.putExtra("sjy",sjy);
                            intent.putExtra("sjytel",sjytel);
                            intent.putExtra("bzsm2",bzsm2);
                            intent.putExtra("update_zt",update_zt);
                            startActivity(intent);
                        }
                    });
                    faYuanAdapter.notifyDataSetChanged();
                }else BaseApi.showErrMsg(CreateWorkActivity.this,result);
            }
        });
    }

    @Override
    public void onRefresh() {
        if(type ==1 ){
            getHttp();
        }else if(type == 2){
            getZhenChaHttp();
        }else if(type == 3){
            getJianChaHttp();
        }else {
            getFaYuan();
        }

    }
}
