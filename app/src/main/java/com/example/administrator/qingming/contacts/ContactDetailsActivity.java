package com.example.administrator.qingming.contacts;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.api.BaseApi;
import com.example.administrator.qingming.api.MainApi;
import com.example.administrator.qingming.contacts.lawjournal.ContactModel;
import com.example.administrator.qingming.interfaces.GetResultCallBack;
import com.example.administrator.qingming.model.Constants;
import com.example.administrator.qingming.model.ModelContactDetail;
import com.example.administrator.qingming.qinminutils.GsonUtil;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/5/2.
 */

public class ContactDetailsActivity extends Activity {
    private ListView mylistView;
    private List<ModelContactDetail.ResultBean> list;
    private ImageView backbtn;
    private TextView name,tel,company,chuanzhen,email,home,bz;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        Bundle bundle = getIntent().getExtras();
        id =bundle.getString("cid");
        Log.i("=====>",""+ id);
        initView();
        initHttp();

    }

    private String id ;
    private void initHttp(){
        MainApi.getInstance(this).getIntoTxlApi(id, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if (type == Constants.TYPE_SUCCESS) {//请求成功
                    ArrayList<ModelContactDetail.ResultBean> dataList = GsonUtil.fromJsonList(new Gson(), result, ModelContactDetail.ResultBean.class);
                    list.addAll(dataList);
                    name.setText(list.get(0).getName());
                    tel.setText(list.get(0).getTel());
                    company.setText(list.get(0).getSsjg());
                    chuanzhen.setText(list.get(0).getCz());
                    email.setText(list.get(0).getEmail());
                    home.setText(list.get(0).getJtzz());
                    bz.setText(list.get(0).getBz());
                } else BaseApi.showErrMsg(ContactDetailsActivity.this, result);//失败提示msg
            }
        });
    }


    private void initView(){
        list = new ArrayList<>();
        backbtn = (ImageView) findViewById(R.id.back_btn);
        name = (TextView) findViewById(R.id.name);
        tel = (TextView) findViewById(R.id.tel);
        company = (TextView) findViewById(R.id.company);
        chuanzhen = (TextView) findViewById(R.id.chuanzhen);
        email = (TextView) findViewById(R.id.email);
        home = (TextView) findViewById(R.id.home);
        bz = (TextView) findViewById(R.id.bz);

        backbtn.setOnClickListener(onClickListener);

    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back_btn:
                    finish();
                    break;
            }
        }
    };


}
