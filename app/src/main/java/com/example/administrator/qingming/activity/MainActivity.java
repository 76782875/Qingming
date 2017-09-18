package com.example.administrator.qingming.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.qingming.HomePageBottomActivity;
import com.example.administrator.qingming.R;
import com.example.administrator.qingming.RegsterActivity;
import com.example.administrator.qingming.api.BaseApi;
import com.example.administrator.qingming.api.MainApi;
import com.example.administrator.qingming.dialog.LoadingDialog;
import com.example.administrator.qingming.fragment.HomePageFragment;
import com.example.administrator.qingming.interfaces.GetResultCallBack;
import com.example.administrator.qingming.model.Constants;
import com.example.administrator.qingming.model.ModelDate;
import com.example.administrator.qingming.model.ModelZhiWei;
import com.example.administrator.qingming.qinminutils.GsonUtil;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;


public class MainActivity extends Activity {
    private String phone;
    private String password;
    private EditText inputname; //输入手机号
    private EditText inputpassword; //输入密码
    private Button loginbtn;
    private Button regsterbtn;
    private CheckBox checkBox;
    SharedPreferences sp = null;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sp = this.getSharedPreferences("userinfo", Context.MODE_PRIVATE);

        initview();
    }
    //找到控件，设置点击事件
    private void initview() {
        loadingDialog = new LoadingDialog(this);
        inputname = (EditText) findViewById(R.id.input_name);
        inputpassword = (EditText) findViewById(R.id.input_password);
        loginbtn = (Button) findViewById(R.id.login_btn);
//        regsterbtn = (Button) findViewById(R.id.regster_btn);
        checkBox = (CheckBox) findViewById(R.id.login_checkbox);
        loginbtn.setOnClickListener(onClickListener);
//        regsterbtn.setOnClickListener(onClickListener);

        inputname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                phone = s.toString();
            }
        });

        inputpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                password = s.toString();
            }
        });

        if (sp.getBoolean("checkboxBoolean", false)){
            inputname.setText(sp.getString("inputname", null));
            inputpassword.setText(sp.getString("inputpassword", null));
            checkBox.setChecked(true);
        }
    }

    SharedPreferences.Editor editor;
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){
                case R.id.login_btn:
//                    intent = new Intent(MainActivity.this, HomePageBottomActivity.class);
//                    startActivity(intent);
                    //通过
                    boolean CheckBoxLogin = checkBox.isChecked();
                    //按钮被选中，下次进入时会显示账号和密码
                    if(CheckBoxLogin){
                        editor = sp.edit();
                        editor.putString("inputname", phone);
                        editor.putString("inputpassword", password);
                        editor.putBoolean("checkboxBoolean", true);
                        editor.commit();
                    }else {
                        //按钮没被选中，清空账号和密码，下次进入时会显示账号和密码
                        editor = sp.edit();
                        editor.putString("inputname", null);
                        editor.putString("inputpassword", null);
                        editor.putBoolean("checkboxBoolean", false);
                        editor.commit();
                    }
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                Looper.prepare();
                                getString();
                                Looper.loop();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                    break;
//                case R.id.regster_btn:
//                    intent = new Intent(MainActivity.this,RegsterActivity.class);
//                    startActivity(intent);
//                    break;
            }
        }
    };


    private String id;
    private String loginName;
    private String cid;
    private String name;
    public void getString() throws IOException {
        //1 创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        String url ="http://192.168.188.122:8080/yunlvsi/mobile/web/login?";
        //2 创建Request对象
        Request request = new Request.Builder().get().url(url+"username="+phone+"&password="+password).build();
        //3 创建回调对象
        Call call = okHttpClient.newCall(request);
        //4 执行
        final Response response = call.execute();
        final String str = response.body().string();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ModelDate gson = new Gson().fromJson(str,ModelDate.class);
                int status = gson.getStatus();
                cid = gson.getCid();
                if(status == 200){
                    ModelDate.UserBean userBean = gson.getUser();
                    id = userBean.getId();
                    loginName = userBean.getLoginName();
                    name =userBean.getName();
                    cxoffice();
                }else {
                    Toast.makeText(MainActivity.this,"用户名或者密码错误",Toast.LENGTH_SHORT).show();
                }
                saveSharePreferences();
            }
        });
    }

    String zhiwei;
    boolean iszw;
    private void cxoffice(){
        loadingDialog.setLoadingContent("请稍后...");
        loadingDialog.show();
        MainApi.getInstance(this).getcxofficeApi(id, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loadingDialog.dismiss();
                if(type == Constants.TYPE_SUCCESS){
                    List<ModelZhiWei.ResultBean> resultBeen = GsonUtil.fromJsonList(new Gson(),result,
                            ModelZhiWei.ResultBean.class);
                    zhiwei = resultBeen.get(0).getName();
                    Intent intent = new Intent(MainActivity.this, HomePageBottomActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    Log.e("main=====",""+zhiwei);
                    if(zhiwei.equals("主任")){
                        iszw = true;
                    }
                    saveSharePreferences();

                }else BaseApi.showErrMsg(MainActivity.this,result);
            }
        });

    }

    //存储到本地
    private void saveSharePreferences(){
        SharedPreferences sharedPreferences1 = getSharedPreferences("qinmin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences1.edit();
        editor.putString("id",id);
        editor.putString("loginName",loginName);
        editor.putString("cid",cid);
        editor.putString("name",name);
        editor.putBoolean("zhiwei",iszw);
        editor.commit();
    }

}
