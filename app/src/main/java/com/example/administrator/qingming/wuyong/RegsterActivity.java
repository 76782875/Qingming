package com.example.administrator.qingming.wuyong;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.administrator.qingming.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Administrator on 2017/4/26.
 */

public class RegsterActivity extends Activity {
    private ImageView backbtn;
    private EditText inputPhoneEtedit;//手机号码输入框
    private EditText inputyzedit;//验证码输入框
    private EditText inputpasswordedit;//新密码输入框
    private EditText inputpasswordnewedit;//确认新密码输入框
    private Button okbtn;//确认按钮
    private Button getbtn;//获取验证码按钮
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regster);

        init();
    }

    private void init(){
        backbtn = (ImageView) findViewById(R.id.back_btn);
        inputpasswordedit = (EditText) findViewById(R.id.input_password_edit);
        inputpasswordnewedit = (EditText) findViewById(R.id.input_password_new_edit);
        inputyzedit = (EditText) findViewById(R.id.inputPhone_edit);
        inputPhoneEtedit = (EditText) findViewById(R.id.inputPhoneEt_edit);

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
