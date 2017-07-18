package com.example.administrator.qingming.work;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.qingming.R;

/**
 * Created by Administrator on 2017/5/22 0022.
 */

public class ExamineActivity extends Activity {
    private ImageView backbtn;
    private TextView pass,nopass;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examine);

        initView();
    }

    private void initView() {
        backbtn = (ImageView) findViewById(R.id.back_btn);
        pass = (TextView) findViewById(R.id.pass);
        nopass = (TextView) findViewById(R.id.nopass);

        backbtn.setOnClickListener(onclicklisten);
        pass.setOnClickListener(onclicklisten);
        nopass.setOnClickListener(onclicklisten);
    }

    View.OnClickListener onclicklisten = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back_btn:
                    finish();
                    break;
                case R.id.pass:
                    finish();
                    break;
                case R.id.nopass:
                    finish();
                    break;
            }
        }
    };


}
