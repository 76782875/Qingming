package com.example.administrator.qingming.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.api.BaseApi;
import com.example.administrator.qingming.api.MainApi;
import com.example.administrator.qingming.interfaces.GetResultCallBack;
import com.example.administrator.qingming.model.Constants;
import com.example.administrator.qingming.model.ModelChangeLvSi;
import com.example.administrator.qingming.qinminutils.GsonUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/6 0006.
 */

public class ChangeLvshiActivity extends Activity {
    private List<ModelChangeLvSi.ResultBean> list;
    private TextView counsel,numb,sarq,jssf,dlf,ls,change,xuigai;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intobglvs);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("cid");
        name = bundle.getString("name");
        Log.i("id===>",""+id+name);

        initView();
        getHttp();
    }

    private void initView() {
        list = new ArrayList<ModelChangeLvSi.ResultBean>();
        counsel = (TextView) findViewById(R.id.counsel);
        numb = (TextView) findViewById(R.id.numb);
        sarq = (TextView) findViewById(R.id.sarq);
        jssf = (TextView) findViewById(R.id.jssf);
        dlf = (TextView) findViewById(R.id.dlf);
        ls = (TextView) findViewById(R.id.ls);
        change = (TextView) findViewById(R.id.change);
        xuigai = (TextView) findViewById(R.id.xuigai);
    }

    String name;
    String id;
    private void getHttp(){
        MainApi.getInstance(this).getintobglvshiApi(id, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type == Constants.TYPE_SUCCESS){
                    List<ModelChangeLvSi.ResultBean> resultbean = GsonUtil.fromJsonList(new Gson(),
                            result,ModelChangeLvSi.ResultBean.class);
                    list.addAll(resultbean);
                    counsel.setText(list.get(0).getAy());
                    numb.setText(list.get(0).getAh_number());
                    sarq.setText(list.get(0).getSarq());
                    if(list.get(0).getSffs().equals("0")){
                        jssf.setText("免费");
                    }else if(list.get(0).getSffs().equals("1")){
                        jssf.setText("计件收费");
                    }else if(list.get(0).getSffs().equals("2")){
                        jssf.setText("按标的比例收费");
                    }else if(list.get(0).getSffs().equals("3")){
                        jssf.setText("风险代理费");
                    }else if(list.get(0).getSffs().equals("4")){
                        jssf.setText("固定+风险代理费");
                    }
                    dlf.setText(list.get(0).getDlf());
                    ls.setText(name);

                }else BaseApi.showErrMsg(ChangeLvshiActivity.this,result);
            }
        });
    }
}
