package com.example.administrator.qingming.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.adapter.FaYuanAdapter;
import com.example.administrator.qingming.adapter.NewsFayuanAdapter;
import com.example.administrator.qingming.api.BaseApi;
import com.example.administrator.qingming.api.MainApi;
import com.example.administrator.qingming.dialog.LoadingDialog;
import com.example.administrator.qingming.interfaces.GetResultCallBack;
import com.example.administrator.qingming.model.Constants;
import com.example.administrator.qingming.model.ModelFaYuan;
import com.example.administrator.qingming.model.ModelFalvXx;
import com.example.administrator.qingming.model.ModelNews;
import com.example.administrator.qingming.adapter.NewsAdapater;
import com.example.administrator.qingming.model.MyCaseModel;
import com.example.administrator.qingming.news.casedetails.CaseDetailsActivity;
import com.example.administrator.qingming.news.casedetails.CreateWorkActivity;
import com.example.administrator.qingming.qinminutils.GsonUtil;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/5/23 0023.
 */

public class NewsActivity extends Activity {
    private ListView listmessage,listmessage2;
    private ImageView backbtn;
    private TextView examine2,examine3;
    private View examine_line1,examine_line2;
    private List<ModelNews.ListBean> list;
    private List<ModelFalvXx.ResultBean> fayuanlist;
    private List<MyCaseModel.ResultBean> caselist;
    private LoadingDialog loadingDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_news);

        SharedPreferences sharedPreferences = getSharedPreferences("qinmin", Context.MODE_PRIVATE);
        // 使用getString方法获得value，注意第2个参数是value的默认值
        accepterId = sharedPreferences.getString("id","");
        initView();

        getFaYuan();
        getHttp();
    }

    private void initView() {
        loadingDialog = new LoadingDialog(this);
        list = new ArrayList<>();
        fayuanlist = new ArrayList<>();
        caselist = new ArrayList<>();
        backbtn = (ImageView) findViewById(R.id.back_btn);
        examine2 = (TextView) findViewById(R.id.examine2);
        examine3 = (TextView) findViewById(R.id.examine3);
        examine_line1 = findViewById(R.id.examine_line1);
        examine_line2 = findViewById(R.id.line2);
        listmessage = (ListView)findViewById(R.id.list_message);
        listmessage2 = (ListView)findViewById(R.id.list_message2);

        listmessage.setVisibility(View.VISIBLE);
        newsAdapater = new NewsAdapater(NewsActivity.this,list);
        listmessage.setAdapter(newsAdapater);
        listmessage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                glid = list.get(position).getGlid();
                getcase();
            }
        });


        newsFayuanAdapter = new NewsFayuanAdapter(NewsActivity.this,fayuanlist);
        listmessage2.setAdapter(newsFayuanAdapter);
        listmessage2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(NewsActivity.this,FaYuanActivity.class);
                String cid = fayuanlist.get(position).getId();
                String glid = fayuanlist.get(position).getGlid();
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
                String dfdsr= fayuanlist.get(position).getDfdsr();
                String ay= fayuanlist.get(position).getAy();
                String wtr= fayuanlist.get(position).getWtr();
                String ah_number= fayuanlist.get(position).getAh_number();
                intent.putExtra("zid",2);
                intent.putExtra("aid",cid);
                intent.putExtra("id",glid);
                intent.putExtra("ah_number",ah_number);
                intent.putExtra("wtr",wtr);
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
                startActivity(intent);
            }
        });

        backbtn.setOnClickListener(onClickListener);
        examine2.setOnClickListener(onClickListener);
        examine3.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back_btn:
                    finish();
                    break;
                case R.id.examine2:
                    listmessage.setVisibility(View.VISIBLE);
                    listmessage2.setVisibility(View.GONE);
                    examine_line1.setVisibility(View.VISIBLE);
                    examine_line2.setVisibility(View.INVISIBLE);
                    examine2.setTextColor(getResources().getColor(R.color.blue));
                    examine3.setTextColor(getResources().getColor(R.color.black));
                    getHttp();
                    newsAdapater.notifyDataSetChanged();
                    break;
                case R.id.examine3:
                    listmessage.setVisibility(View.GONE);
                    listmessage2.setVisibility(View.VISIBLE);
                    examine_line2.setVisibility(View.VISIBLE);
                    examine_line1.setVisibility(View.INVISIBLE);
                    examine2.setTextColor(getResources().getColor(R.color.black));
                    examine3.setTextColor(getResources().getColor(R.color.blue));
                    getFaYuan();
                    newsFayuanAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    NewsAdapater newsAdapater;
    private String accepterId;
    private void getHttp(){
        MainApi.getInstance(this).getxiaoxiApi(accepterId, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if (type == Constants.TYPE_SUCCESS) {
//                    ModelNews  resultBeen = GsonUtil.fromJSONData(new Gson(), result,
//                            ModelNews.class);
                    List<ModelNews.ListBean> listBeen = GsonUtil.fromJsonList(new Gson(), result,
                            ModelNews.ListBean.class);
                    list.clear();
                    list.addAll(listBeen);

                    newsAdapater.notifyDataSetChanged();
                } else BaseApi.showErrMsg(NewsActivity.this, result);
            }
        });
    }

    NewsFayuanAdapter newsFayuanAdapter;
    private void getFaYuan(){
        MainApi.getInstance(this).getnewsfayuanApi(accepterId, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type == Constants.TYPE_SUCCESS){
                    List<ModelFalvXx.ResultBean> resultBeen = GsonUtil.fromJsonList(new Gson(),result,
                            ModelFalvXx.ResultBean.class);
                    fayuanlist.clear();
                    fayuanlist.addAll(resultBeen);

                    newsFayuanAdapter.notifyDataSetChanged();
                }else BaseApi.showErrMsg(NewsActivity.this,result);
            }
        });
    }

    int glid;
    String case_state;
    String ay;
    String ah_number;
    String wtr;
    String dfdsr;
    int dlf;
    int jzf;
    int ajlx;
    String sffs ;
    String name ;
    String sarq ;
    String court ;
    String detention ;
    String police ;
    String procuratorate ;
    String slbm ;
    String bzsm ;
    String ssdw ;
    String ssbd;
    String ssjd;
    private void getcase(){
        loadingDialog.setLoadingContent("请稍后...");
        loadingDialog.show();
        MainApi.getInstance(this).getmycasesApi(glid, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loadingDialog.dismiss();
                if(type == Constants.TYPE_SUCCESS){
                    List<MyCaseModel.ResultBean> resultBeen = GsonUtil.fromJsonList(new Gson(),result,
                            MyCaseModel.ResultBean.class);
                    caselist.clear();
                    caselist.addAll(resultBeen);

                    case_state = caselist.get(0).getCase_state();
                    ay = caselist.get(0).getAy();
                    ah_number = caselist.get(0).getAh_number();
                    wtr = caselist.get(0).getWtr();
                    dfdsr = caselist.get(0).getDfdsr();
                    dlf = caselist.get(0).getDlf();
                    jzf = caselist.get(0).getJzf();
                    ajlx = caselist.get(0).getAjlx();
//                     sffs = null;
                    if (caselist.get(0).getSffs().equals("0")) {
                        sffs = "免费";
                    } else if (caselist.get(0).getSffs().equals("1")) {
                        sffs = "计件收费";
                    } else if (caselist.get(0).getSffs().equals("2")) {
                        sffs = "按标的比例收费";
                    } else if (caselist.get(0).getSffs().equals("3")) {
                        sffs = "风险代理收费";
                    } else if (caselist.get(0).getSffs().equals("4")) {
                        sffs = "固定+风险代理收费";
                    }
                    name = caselist.get(0).getName();
                    sarq = caselist.get(0).getSarq();
                    court = caselist.get(0).getCourt();
                    detention = caselist.get(0).getDetention();
                    police = caselist.get(0).getPolice();
                    procuratorate = caselist.get(0).getProcuratorate();
                    slbm = caselist.get(0).getSlbm();
                    bzsm = caselist.get(0).getBzsm();
                    ssdw = caselist.get(0).getSsdw();
                    ssbd = caselist.get(0).getSsbd();
                    ssjd = caselist.get(0).getSsjd();
                    
                    Intent intent = new Intent(NewsActivity.this, CaseDetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("id",glid+"");
                    bundle.putString("ay",ay);
                    bundle.putString("ah_number",ah_number);
                    bundle.putString("wtr",wtr);
                    bundle.putString("dfdsr",dfdsr);
                    bundle.putString("sffs",sffs);
                    bundle.putString("name",name);
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
                    bundle.putInt("dlf",dlf);
                    bundle.putInt("jzf",jzf);
                    bundle.putInt("ajlx",ajlx);
                    bundle.putString("case_state",case_state);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }else BaseApi.showErrMsg(NewsActivity.this,result);
            }
        });
    }

}
