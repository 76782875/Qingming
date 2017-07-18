package com.example.administrator.qingming.news.casedetails;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.administrator.qingming.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/25.
 */

public class CustomerDetailsActivity extends Activity {
    private ImageView backbtn,modifybtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);

        init();
    }

    private void init(){
        backbtn = (ImageView) findViewById(R.id.back_btn);
        modifybtn = (ImageView) findViewById(R.id.modify_btn);

        backbtn.setOnClickListener(onClickListener);
        modifybtn.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back_btn:
                    finish();
                    break;
                case R.id.modify_btn:
                    Intent intent = new Intent(CustomerDetailsActivity.this,AddConsignorActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

}
