package com.example.administrator.qingming.work;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.qingming.R;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class ConflictofInterestActivity extends Activity {
    private ImageView backbtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conflict_of_interest);

        initView();
    }

    private void initView(){
        backbtn = (ImageView) findViewById(R.id.back_btn);
        backbtn.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        Intent intent;
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
