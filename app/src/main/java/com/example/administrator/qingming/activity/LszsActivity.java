package com.example.administrator.qingming.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.adapter.LszsAdapter;
import com.example.administrator.qingming.model.ModelLszs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


/**
 * Created by Administrator on 2017/7/13 0013.
 */

public class LszsActivity extends Activity {
    private RecyclerView recyclerView;
    private List<ModelLszs> list;
    private TextView xfo,xxxgf,mfsf,xzf;
    private TextView jjf,shf,xf,ss;
    private TextView xz,gz,fy,jcy;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lszs);

        initView();
        initonclick();
    }

    private void initView() {
        list =new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        xfo = (TextView) findViewById(R.id.xfo);
        xxxgf = (TextView) findViewById(R.id.xxxgf);
        mfsf = (TextView) findViewById(R.id.mfsf);
        xzf = (TextView) findViewById(R.id.xzf);
        jjf = (TextView) findViewById(R.id.jjf);
        shf = (TextView) findViewById(R.id.shf);
        xf = (TextView) findViewById(R.id.xf);
        ss = (TextView) findViewById(R.id.ss);
        xz = (TextView) findViewById(R.id.xz);
        gz = (TextView) findViewById(R.id.gz);
        fy = (TextView) findViewById(R.id.fy);
        jcy = (TextView) findViewById(R.id.jcy);

        GridLayoutManager grid =new GridLayoutManager(LszsActivity.this,4);
        recyclerView.setLayoutManager(grid);

        getData();
        LszsAdapter lszsAdapter = new LszsAdapter(LszsActivity.this,list);
        recyclerView.setAdapter(lszsAdapter);
        lszsAdapter.setOnItemClickListener(new LszsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                switch (i){
                    case 0:
                        Toast.makeText(LszsActivity.this,"你点击了"+i,Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(LszsActivity.this,"你点击了"+i,Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(LszsActivity.this,"你点击了"+i,Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(LszsActivity.this,"你点击了"+i,Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(LszsActivity.this,"你点击了"+i,Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Toast.makeText(LszsActivity.this,"你点击了"+i,Toast.LENGTH_SHORT).show();
                        break;
                    case 6:
                        Toast.makeText(LszsActivity.this,"你点击了"+i,Toast.LENGTH_SHORT).show();
                        break;
                    case 7:
                        Toast.makeText(LszsActivity.this,"你点击了"+i,Toast.LENGTH_SHORT).show();
                        break;
                    case 8:
                        Toast.makeText(LszsActivity.this,"你点击了"+i,Toast.LENGTH_SHORT).show();
                        break;
                    case 9:
                        Toast.makeText(LszsActivity.this,"你点击了"+i,Toast.LENGTH_SHORT).show();
                        break;
                    case 10:
                        Toast.makeText(LszsActivity.this,"你点击了"+i,Toast.LENGTH_SHORT).show();
                        break;
                    case 11:
                        Toast.makeText(LszsActivity.this,"你点击了"+i,Toast.LENGTH_SHORT).show();
                        break;
                    case 12:
                        Toast.makeText(LszsActivity.this,"你点击了"+i,Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    private void initonclick(){
        xfo.setOnClickListener(onClickListener);
        xxxgf.setOnClickListener(onClickListener);
        mfsf.setOnClickListener(onClickListener);
        xzf.setOnClickListener(onClickListener);
        jjf.setOnClickListener(onClickListener);
        shf.setOnClickListener(onClickListener);
        xf.setOnClickListener(onClickListener);
        ss.setOnClickListener(onClickListener);
        xz.setOnClickListener(onClickListener);
        gz.setOnClickListener(onClickListener);
        fy.setOnClickListener(onClickListener);
        jcy.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        Intent intent;
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.xfo:
                    intent = new Intent(LszsActivity.this,FaLvActivity.class);
                    intent.putExtra("conts_type",1);
                    startActivity(intent);
                    break;
                case R.id.xxxgf:
                    intent = new Intent(LszsActivity.this,FaLvActivity.class);
                    intent.putExtra("conts_type",2);
                    startActivity(intent);
                    break;
                case R.id.mfsf:
                    intent = new Intent(LszsActivity.this,FaLvActivity.class);
                    intent.putExtra("conts_type",3);
                    startActivity(intent);
                    break;
                case R.id.xzf:
                    intent = new Intent(LszsActivity.this,FaLvActivity.class);
                    intent.putExtra("conts_type",4);
                    startActivity(intent);
                    break;
                case R.id.jjf:
                    intent = new Intent(LszsActivity.this,FaLvActivity.class);
                    intent.putExtra("conts_type",5);
                    startActivity(intent);
                    break;
                case R.id.shf:
                    intent = new Intent(LszsActivity.this,FaLvActivity.class);
                    intent.putExtra("conts_type",6);
                    startActivity(intent);
                    break;
                case R.id.xf:
                    intent = new Intent(LszsActivity.this,FaLvActivity.class);
                    intent.putExtra("conts_type",7);
                    startActivity(intent);
                    break;
                case R.id.ss:
                    intent = new Intent(LszsActivity.this,FaLvActivity.class);
                    intent.putExtra("conts_type",8);
                    startActivity(intent);
                    break;
                case R.id.xz:
                    intent = new Intent(LszsActivity.this,FaLvActivity.class);
                    intent.putExtra("conts_type",9);
                    startActivity(intent);
                    break;
                case R.id.gz:
                    intent = new Intent(LszsActivity.this,FaLvActivity.class);
                    intent.putExtra("conts_type",10);
                    startActivity(intent);
                    break;
                case R.id.fy:
                    intent = new Intent(LszsActivity.this,FaLvActivity.class);
                    intent.putExtra("conts_type",11);
                    startActivity(intent);
                    break;
                case R.id.jcy:
                    intent = new Intent(LszsActivity.this,FaLvActivity.class);
                    intent.putExtra("conts_type",12);
                    startActivity(intent);
                    break;
            }
        }
    };

    int[] image = {R.mipmap.cs_beijing,R.mipmap.cs_tianjin,
            R.mipmap.cs_hebei, R.mipmap.cs_shanxi,R.mipmap.cs_neimeng,R.mipmap.cs_liaoning,
            R.mipmap.cs_jilin,R.mipmap.cs_heilongj,R.mipmap.cs_shanghai,R.mipmap.cs_jiangsu,R.mipmap.cs_zhejiang,R.mipmap.cs_anhui,
            R.mipmap.cs_fujian,R.mipmap.cs_jiangxi,R.mipmap.cs_shandong,R.mipmap.cs_henan,R.mipmap.cs_hubei,
            R.mipmap.cs_hunan,R.mipmap.cs_guangdong,R.mipmap.cs_guangxi,R.mipmap.cs_hainan,
            R.mipmap.cs_chongqing,R.mipmap.cs_sx,R.mipmap.cs_fuizhou,R.mipmap.cs_yunnan,R.mipmap.cs_xizang,
            R.mipmap.cs_shanxi,R.mipmap.cs_gansu,R.mipmap.cs_qinghai,R.mipmap.cs_ningxia};
    String[] n = {"北京","天津","河北",
            "山西","内蒙古","辽宁","吉林","黑龙江","上海","江苏","浙江","安徽","福建","江西","山东","河南",
            "湖北","湖南","广东","广西","海南","重庆","四川","贵州","云南","西藏","陕西","甘肃","青海","宁夏"};
    public void getData(){
        for(int i=0;i<n.length;i++){
            ModelLszs modelLszs = new ModelLszs();
            modelLszs.setImage(image[i]);
            modelLszs.setName(n[i]);
            list.add(modelLszs);
        }
    }
}
