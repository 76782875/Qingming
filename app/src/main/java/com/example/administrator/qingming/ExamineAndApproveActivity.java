package com.example.administrator.qingming;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.qingming.activity.CaseShenPiActivity;
import com.example.administrator.qingming.adapter.ShenPiAdapter;
import com.example.administrator.qingming.api.BaseApi;
import com.example.administrator.qingming.api.MainApi;
import com.example.administrator.qingming.interfaces.GetResultCallBack;
import com.example.administrator.qingming.model.Constants;
import com.example.administrator.qingming.model.MyCaseModel;
import com.example.administrator.qingming.qinminutils.GsonUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/26 0026.
 */

public class ExamineAndApproveActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener{
    private ImageView backbtn;
    private TextView examine2,examine3;
    private View examine_line1,examine_line2;
    private List<MyCaseModel.ResultBean> list;
    private List<MyCaseModel.ResultBean> list1;
    private List<MyCaseModel.ResultBean> list2;
    private ListView mlistview;
    ShenPiAdapter shenPiAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examine_and_approve);

        SharedPreferences sharedPreferences = getSharedPreferences("qinmin", Context.MODE_PRIVATE);
        company_id = sharedPreferences.getString("cid","");
        initView();

        Bundle bundle = getIntent().getExtras();
        case_state = bundle.getInt("case_state");

        Log.e("case_state======>",""+case_state);
        getHttp();
    }


    private void initView() {
        list = new ArrayList<>();
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        backbtn = (ImageView) findViewById(R.id.back_btn);
        examine2 = (TextView) findViewById(R.id.examine2);
        examine3 = (TextView) findViewById(R.id.examine3);
        examine_line1 = findViewById(R.id.examine_line1);
        examine_line2 = findViewById(R.id.line2);
        mlistview = (ListView) findViewById(R.id.listview);

        swipeRefreshLayout.setOnRefreshListener(this);
        backbtn.setOnClickListener(onClickListener);
        examine2.setOnClickListener(onClickListener);
        examine3.setOnClickListener(onClickListener);
        shenPiAdapter = new ShenPiAdapter(ExamineAndApproveActivity.this,list);
        mlistview.setAdapter(shenPiAdapter);
        mlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                String id1 = list.get(i).getId();
                String case_state= list.get(i).getCase_state();
                String ay= list.get(i).getAy();
                String ah_number= list.get(i).getAh_number();
                String wtr= list.get(i).getWtr();
                String dfdsr= list.get(i).getDfdsr();
                int dlf= list.get(i).getDlf();
                int jzf= list.get(i).getJzf();
                Log.e("id----->>",""+id1);
                String sffs = null;
                if(list.get(i).getSffs().equals("0")){
                    sffs = "免费";
                }else if(list.get(i).getSffs().equals("1")){
                    sffs = "计件收费";
                }else if(list.get(i).getSffs().equals("2")){
                    sffs = "按标的比例收费";
                }else if(list.get(i).getSffs().equals("3")){
                    sffs = "风险代理收费";
                }else if(list.get(i).getSffs().equals("4")){
                    sffs = "固定+风险代理收费";
                }

                String name = list.get(i).getName();
                String sarq =  list.get(i).getSarq();

                Intent intent = new Intent(ExamineAndApproveActivity.this, CaseShenPiActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id",id1);
                bundle.putString("ay",ay);
                bundle.putString("ah_number",ah_number);
                bundle.putString("wtr",wtr);
                bundle.putString("dfdsr",dfdsr);
                bundle.putString("sffs",sffs);
                bundle.putString("name",name);
                bundle.putString("sarq",sarq);
                bundle.putInt("dlf",dlf);
                bundle.putInt("jzf",jzf);
                bundle.putString("case_state",case_state);
                intent.putExtras(bundle);
                Log.e("","+"+id1+"+"+ay+"+"+ah_number+"+"+wtr+"+"+dfdsr+"+"+sffs+"+"+name+"+"+sarq+"+"+dlf+"+"+jzf);
                startActivity(intent);
            }
        });
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back_btn:
                    finish();
                    break;
                case R.id.examine2:
                    examine_line1.setVisibility(View.VISIBLE);
                    examine_line2.setVisibility(View.INVISIBLE);
                    examine2.setTextColor(getResources().getColor(R.color.black));
                    examine3.setTextColor(getResources().getColor(R.color.blue));
                    break;
                case R.id.examine3:
                    examine_line2.setVisibility(View.VISIBLE);
                    examine_line1.setVisibility(View.INVISIBLE);
                    examine3.setTextColor(getResources().getColor(R.color.black));
                    examine2.setTextColor(getResources().getColor(R.color.blue));
                    break;
            }
        }
    };

    private int index = 0;
    private void setListData(){
        list2.clear();
        if (index == 0)
            list2 .addAll(list);
        else if(index == 1)
            list2.addAll(list1);
        shenPiAdapter.notifyDataSetChanged();
    }
    /**
     * 获取审批的数据
     */
    String company_id;
    int case_state ;
    private void getHttp(){
        MainApi.getInstance(this).getsaspApi(company_id, case_state,new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                swipeRefreshLayout.setRefreshing(false);
                if(type == Constants.TYPE_SUCCESS){
                    List<MyCaseModel.ResultBean> resultBeen = GsonUtil.fromJsonList(new Gson(),result,MyCaseModel.ResultBean.class);
                    list.clear();
                    list.addAll(resultBeen);

                    shenPiAdapter.notifyDataSetChanged();
                }else BaseApi.showErrMsg(ExamineAndApproveActivity.this,result);
            }
        });
    }

    @Override
    public void onRefresh() {
        getHttp();
    }
}
