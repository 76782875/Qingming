package com.example.administrator.qingming.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import com.example.administrator.qingming.R;

/**
 * Created by Administrator on 2017/8/3 0003.
 */

public class SplashActivity extends Activity {
    // 判断是否是第一次开启应用
    boolean isFirstOpen  = false;
    //延时时间，用于由欢迎界面进入另外的页面的延时效果
    private static final int TIME = 2 * 1000;
    private static final int TO_MAIN = 100001;
    private static final int TO_GUIDE = 100002;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 如果不是第一次启动app，则正常显示启动屏
        setContentView(R.layout.guide_one);

        init();
    }



    //由于不能在主线程中直接延时，所以用一个Handler来处理发送过来的消息
    Handler myHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case TO_MAIN:
                    Intent i1 = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i1);
                    finish();
                    break;
                case TO_GUIDE:
                    Intent i2 = new Intent(SplashActivity.this, GuideActivity.class);
                    startActivity(i2);
                    finish();
                    break;
            }
        }

        ;
    };

    private void init() {
        //将用户是否是第一次使用的值用SharedPreferences存储到本地
        SharedPreferences perPreferences = getSharedPreferences("JohnTsai", MODE_PRIVATE);
        isFirstOpen = perPreferences.getBoolean("isFirstUse", true);
        if (!isFirstOpen) {
            myHandler.sendEmptyMessageDelayed(TO_MAIN, TIME);
        } else {
            myHandler.sendEmptyMessageDelayed(TO_GUIDE, TIME);
            SharedPreferences.Editor editor = perPreferences.edit();
            editor.putBoolean("isFirstUse", false);
            editor.commit();
        }
    }

}
