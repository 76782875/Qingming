package com.example.administrator.qingming.contacts;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    private ImageView backbtn,call_phone,message;
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
//        checkPermisson();
    }

    private String id ;
    private String telnumb;
    private void initHttp(){
        MainApi.getInstance(this).getIntoTxlApi(id, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if (type == Constants.TYPE_SUCCESS) {//请求成功
                    ArrayList<ModelContactDetail.ResultBean> dataList = GsonUtil.fromJsonList(new Gson(), result, ModelContactDetail.ResultBean.class);
                    list.addAll(dataList);
                    name.setText(list.get(0).getName());
                    if(!list.get(0).getTel().equals("")){
                        telnumb = list.get(0).getTel();
                        tel.setText(telnumb);
                    }
                    if(!list.get(0).getSsjg().equals("")){
                        company.setText(list.get(0).getSsjg());
                    }
                    if(!list.get(0).getCz().equals("")){
                        chuanzhen.setText(list.get(0).getCz());
                    }
                    if(!list.get(0).getEmail().equals("")){
                        email.setText(list.get(0).getEmail());
                    }
                    if(!list.get(0).getJtzz().equals("")){
                        home.setText(list.get(0).getJtzz());
                    }
                    if(!list.get(0).getBz().equals("")){
                        bz.setText(list.get(0).getBz());
                    }

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
        message = (ImageView) findViewById(R.id.message);
        call_phone = (ImageView) findViewById(R.id.call_phone);

        backbtn.setOnClickListener(onClickListener);
        call_phone.setOnClickListener(onClickListener);
        message.setOnClickListener(onClickListener);
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){
                case R.id.back_btn:
                    finish();
                    break;
                case R.id.message:
                    Uri uri = Uri.parse("smsto:"+telnumb);
                    Intent intentMessage = new Intent(Intent.ACTION_SENDTO,uri);
//                    intentMessage.putExtra("sms_body", ss);
                    startActivity(intentMessage);
                    break;
                case R.id.call_phone:
                    Uri uri1 = Uri.parse("tel:"+telnumb);
                    intent = new Intent(Intent.ACTION_CALL, uri1);
                    startActivity(intent);
                    break;
            }
        }
    };


    /**
     * 动态权限的请求
     */
    public void checkPermisson() {
        if (Build.VERSION.SDK_INT >= 23) {
            ActivityCompat.requestPermissions(this,//上下文
                    new String[]{Manifest.permission.CALL_PHONE,
                            Manifest.permission.SEND_SMS},//权限数组
                    1001);
        }
    }

    /**
     * 动态权限的回调函数
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


}
