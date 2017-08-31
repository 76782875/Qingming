package com.example.administrator.qingming.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.qingming.R;

/**
 * Created by Administrator on 2017/6/26 0026.
 */

public class WorkActivity extends Activity {
    private TextView dk,gs,rs,ls,ss,wyj;
    private ImageView back_btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        initView();
    }

    private void initView() {
        back_btn = (ImageView) findViewById(R.id.back_btn);
        dk = (TextView) findViewById(R.id.dk);
        gs = (TextView) findViewById(R.id.gs);
        rs = (TextView) findViewById(R.id.rs);
        ls = (TextView) findViewById(R.id.ls);
        ss = (TextView) findViewById(R.id.ss);
        wyj = (TextView) findViewById(R.id.wyj);

        dk.setOnClickListener(onClickListener);
        gs.setOnClickListener(onClickListener);
        rs.setOnClickListener(onClickListener);
        ls.setOnClickListener(onClickListener);
        ss.setOnClickListener(onClickListener);
        wyj.setOnClickListener(onClickListener);
        back_btn.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        Intent intent;
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back_btn:
                    finish();
                    break;
                case R.id.dk:
                    intent = new Intent(WorkActivity.this, WorkJusuanActivity.class);
                    intent.putExtra("index",1);
                    startActivity(intent);
                    break;
                case R.id.gs:
                    intent = new Intent(WorkActivity.this, WorkJusuanActivity.class);
                    intent.putExtra("index",2);
                    startActivity(intent);
                    break;
                case R.id.rs:
//                    intent = new Intent(WorkActivity.this, WorkJusuanActivity.class);
//                    intent.putExtra("index",3);
//                    startActivity(intent);
                    Toast.makeText(WorkActivity.this,"此功能暂未开通",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.ls:
                    intent = new Intent(WorkActivity.this, LsfjsActivity.class);
                    startActivity(intent);
                    break;
                case R.id.ss:
                    intent = new Intent(WorkActivity.this, SsfJsActivity.class);
                    startActivity(intent);
                    break;
                case R.id.wyj:
                    intent = new Intent(WorkActivity.this, WorkJusuanActivity.class);
                    intent.putExtra("index",6);
                    startActivity(intent);
                    break;
            }
        }
    };
}
