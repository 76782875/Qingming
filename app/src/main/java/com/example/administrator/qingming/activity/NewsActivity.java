package com.example.administrator.qingming.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.model.ModelNews;
import com.example.administrator.qingming.adapter.NewsAdapater;
import com.example.administrator.qingming.news.casedetails.CaseDetailsActivity;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/5/23 0023.
 */

public class NewsActivity extends Activity {
    private ListView listmessage;
    private ImageView backbtn;
    private TextView examine2,examine3;
    private View examine_line1,examine_line2;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_news);

        SharedPreferences sharedPreferences = getSharedPreferences("qinmin", Context.MODE_PRIVATE);
        // 使用getString方法获得value，注意第2个参数是value的默认值
        accepterId = sharedPreferences.getString("id","");
        initView();

        new Thread(){
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
    }

    private void initView() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        backbtn = (ImageView) findViewById(R.id.back_btn);
        examine2 = (TextView) findViewById(R.id.examine2);
        examine3 = (TextView) findViewById(R.id.examine3);
        examine_line1 = findViewById(R.id.examine_line1);
        examine_line2 = findViewById(R.id.line2);
        listmessage = (ListView)findViewById(R.id.list_message);

        backbtn.setOnClickListener(onClickListener);
        examine2.setOnClickListener(onClickListener);
        examine3.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back_btn:
                    finish();
                    break;
                case R.id.examine2:
                    examine_line1.setVisibility(View.VISIBLE);
                    examine_line2.setVisibility(View.INVISIBLE);
                    examine2.setTextColor(getResources().getColor(R.color.blue));
                    examine3.setTextColor(getResources().getColor(R.color.black));
                    break;
                case R.id.examine3:
                    examine_line2.setVisibility(View.VISIBLE);
                    examine_line1.setVisibility(View.INVISIBLE);
                    examine2.setTextColor(getResources().getColor(R.color.black));
                    examine3.setTextColor(getResources().getColor(R.color.blue));
                    break;
            }
        }
    };

    private int id;
    private String theme;
    private String content;
    private long createDate;
    private String companyId;
    private String createId;
    private String createName;
    private int glid;
    private String accepterId;
    public void getString() throws IOException {
        //1 创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        String url ="http://192.168.188.122:8080/955tao/mobile/web/news?";
        //2 创建Request对象
        Request request = new Request.Builder().get().url(url+"accepterId="+accepterId).build();
        //3 创建回调对象
        Call call = okHttpClient.newCall(request);
        //4 执行
        final Response response = call.execute();
        final String str = response.body().string();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<ModelNews.ListBean> list = new ArrayList<ModelNews.ListBean>();
                ModelNews gson = new Gson().fromJson(str,ModelNews.class);
                int status = gson.getStatus();
                if(status == 200){
                    list = gson.getList();
                    listmessage.setAdapter(new NewsAdapater(NewsActivity.this,list));
                    listmessage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(NewsActivity.this, CaseDetailsActivity.class);
                            startActivity(intent);
                        }
                    });
                }else {
                    Toast.makeText(NewsActivity.this,"网络连接失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
