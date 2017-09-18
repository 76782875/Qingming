package com.example.administrator.qingming.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.adapter.SealPngListAdapter;
import com.example.administrator.qingming.api.BaseApi;
import com.example.administrator.qingming.api.MainApi;
import com.example.administrator.qingming.dialog.LoadingDialog;
import com.example.administrator.qingming.interfaces.GetResultCallBack;
import com.example.administrator.qingming.model.Constants;
import com.example.administrator.qingming.model.ModelSealApplyFor;
import com.example.administrator.qingming.model.ModelSealPngList;
import com.example.administrator.qingming.qinminutils.GsonUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/8 0008.
 */

public class SealApplyForDetailsPngListActivity extends Activity {
    private RecyclerView recyclerView;
    private List<ModelSealPngList.ResultBean> list;
    private LoadingDialog loading;
    private int index;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seal_apply_for_details_png_list);

        initView();
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            index = bundle.getInt("index");
            if(index == 1){
                id = bundle.getString("id","");
            }else {
                id = bundle.getString("id","");
            }
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置垂直方向
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

        pnglist();
    }

    private void initView() {
        loading = new LoadingDialog(this);
        list = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycle);

        seal= new SealPngListAdapter(list,SealApplyForDetailsPngListActivity.this);
        recyclerView.setAdapter(seal);
        seal.setOnItemClickListener(new SealPngListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Intent intent;
                switch (view.getId()){
                    case R.id.look:
                        //  http://yunlvsi.cn/files/b306900362b54ac78d9a987db793785b/png/2017072815244514_1.png
                        intent  = new Intent(SealApplyForDetailsPngListActivity.this,SealPngActivity.class);
                        intent.putExtra("dz",list.get(i).getPngdz());
                        intent.putExtra("index",1);
                        intent.putExtra("file",list.get(i).getNew_filename());
                        intent.putExtra("id",list.get(i).getId());
                        startActivity(intent);
                        break;
                    case R.id.insert:
                        //  http://yunlvsi.cn/files/b306900362b54ac78d9a987db793785b/png/2017072815244514_1.png
                        intent  = new Intent(SealApplyForDetailsPngListActivity.this,SealPngActivity.class);
                        intent.putExtra("dz",list.get(i).getPngdz());
                        intent.putExtra("file",list.get(i).getNew_filename());
                        intent.putExtra("id",list.get(i).getId());
                        intent.putExtra("index",2);
                        startActivity(intent);
                        break;
                    case R.id.seal:
                        //  http://yunlvsi.cn/files/b306900362b54ac78d9a987db793785b/png/2017072815244514_1.png
                        intent  = new Intent(SealApplyForDetailsPngListActivity.this,SealPngActivity.class);
                        intent.putExtra("dz",list.get(i).getPngdz());
                        intent.putExtra("file",list.get(i).getNew_filename());
                        intent.putExtra("id",list.get(i).getId());
                        intent.putExtra("index",3);
                        startActivity(intent);
                        break;
                }
            }
        });

        findViewById(R.id.back_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    String id;
    SealPngListAdapter seal;
    private void pnglist(){
        loading.setLoadingContent("加载中...");
        loading.show();
        MainApi.getInstance(this).getpnglistApi(id, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loading.dismiss();
                if (type == Constants.TYPE_SUCCESS) {
                    List<ModelSealPngList.ResultBean> resultbean = GsonUtil.fromJsonList(new Gson(),result,
                            ModelSealPngList.ResultBean.class);
                    list.addAll(resultbean);

                    seal.notifyDataSetChanged();
                } else BaseApi.showErrMsg(SealApplyForDetailsPngListActivity.this, result);
            }
        });
    }
}
