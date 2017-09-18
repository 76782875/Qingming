package com.example.administrator.qingming.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.adapter.SealApplyForDetailsAdapter;
import com.example.administrator.qingming.api.BaseApi;
import com.example.administrator.qingming.api.MainApi;
import com.example.administrator.qingming.dialog.LoadingDialog;
import com.example.administrator.qingming.interfaces.GetResultCallBack;
import com.example.administrator.qingming.model.Constants;
import com.example.administrator.qingming.model.ModelFile;
import com.example.administrator.qingming.model.ModelSealApplyFor;
import com.example.administrator.qingming.qinminutils.FileUtils;
import com.example.administrator.qingming.qinminutils.GsonUtil;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/9/7 0007.
 */

public class SealApplyForDetailsActivity extends Activity {
    private ImageView back_btn;
    private RecyclerView recycle;
    private String ah_number, ay, dsr, dfdsr, wtr;
    private TextView one, two, three, five, fore,filename;
    private List<ModelSealApplyFor.ResultBean> list;
    private Button choose,filepost;
    private LinearLayout file;
    private int index;
    private LoadingDialog loadingDialog;
    private SimpleDateFormat sdf,sDateFormat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seal_apply_for_details);

        SharedPreferences sharedPreferences = getSharedPreferences("qinmin", Context.MODE_PRIVATE);
        create_id = sharedPreferences.getString("id","");
        gsid = sharedPreferences.getString("cid","");
        mlyr = sharedPreferences.getString("name","");

        initView();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置垂直方向
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        // 设置布局管理器
        recycle.setLayoutManager(layoutManager);


        sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
        if(newwjm.equals("")){
            newwjm = sdf.format(new Date());
        }else {
            newwjm = sdf.format(new Date());
        }

        //获取日期
        sDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        createtime = sDateFormat.format(new java.util.Date());

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            ah_number = bundle.getString("ah_number", "");
            ay = bundle.getString("ay", "");
            dsr = bundle.getString("dsr", "");
            dfdsr = bundle.getString("dfdsr", "");
            wtr = bundle.getString("wtr", "");
            id = bundle.getString("id", "");
            mcase_ah = bundle.getString("caseid", "");
            index = bundle.getInt("index");

            one.setText(ay);
            two.setText(ah_number);
            three.setText(wtr);
            fore.setText(dfdsr);
            five.setText(dsr);
            case_ahnumber = ah_number;
            if(index == 1){
                file.setVisibility(View.VISIBLE);
                filepost.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(hzm.equals(".doc")||hzm.equals(".docx")){
                            postfile();
                        }else {
                            Toast.makeText(SealApplyForDetailsActivity.this,"只能上传doc,docx文件",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                choose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        choosefile();
                    }
                });
            }
        }

        detail();
    }

    private void initView() {
        fildid = new ArrayList<>();
        loadingDialog = new LoadingDialog(this);
        filename = (TextView) findViewById(R.id.filename);
        filepost = (Button) findViewById(R.id.filepost);
        choose = (Button) findViewById(R.id.choose);
        file = (LinearLayout) findViewById(R.id.file);
        list = new ArrayList<>();
        back_btn = (ImageView) findViewById(R.id.back_btn);
        recycle = (RecyclerView) findViewById(R.id.recycle);
        one = (TextView) findViewById(R.id.one);
        two = (TextView) findViewById(R.id.two);
        three = (TextView) findViewById(R.id.three);
        five = (TextView) findViewById(R.id.five);
        fore = (TextView) findViewById(R.id.fore);

        sealApplyForDetailsAdapter = new SealApplyForDetailsAdapter(list,SealApplyForDetailsActivity.this);
        recycle.setAdapter(sealApplyForDetailsAdapter);
        sealApplyForDetailsAdapter.setOnItemClickListener(new SealApplyForDetailsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                switch (view.getId()){
                    case R.id.search:
                        Intent intent = new Intent(SealApplyForDetailsActivity.this,SealApplyForDetailsPngListActivity.class);
                        intent.putExtra("id",list.get(i).getId());
                        intent.putExtra("index",1);
                        startActivity(intent);
                        break;
                    case R.id.del:
                        fileid = list.get(i).getId();
                        Log.e("======","fileid=="+fileid);
                        if(fildid.size()==1){
                            delseal(i);
                        }else {
                            //拿到得到的id字符串中的fileid
                            if(fildid.get(i).equals(fileid)){
                                //将它移除
                                fildid.remove(i);
                                Log.e("======",""+fildid);
                                Log.e("======",""+fildid.size());
                                //循环添加到上传的字符串中
                                fileidq = "";
                                for(int p =0;p<fildid.size();p++){
                                    fileidq += fildid.get(p)+",";
                                    Log.e("======","fileidq=="+fileidq);
                                }

                                files_id = fileidq.substring(0,fileidq.length()-1);
                                Log.e("======","fileidq=="+files_id);
                            }
                            insertsealfileid();
                        }
                        break;
                }
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    // 打开系统文件浏览功能
    Intent intent;
    private void choosefile(){
        intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            startActivityForResult(Intent.createChooser(intent, "请选择一个要上传的文件"), FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(SealApplyForDetailsActivity.this, "请安装文件管理器", Toast.LENGTH_SHORT).show();
        }
    }

    private String path,create_id;
    private static final int FILE_SELECT_CODE = 1001;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK && FILE_SELECT_CODE == requestCode){
            Uri uri = data.getData();
            Log.i("uri-------->", "" + uri);
            path = FileUtils.getPath(SealApplyForDetailsActivity.this,uri);
            wjm = path.substring(path.lastIndexOf("/") + 1, path.length());//获取文件名
            filename.setText(wjm);
            hzm = FileUtils.getLastUrl(path);//获取后缀名
            xzdz =create_id +"/"+newwjm+hzm;//下载地址
        }
    }

    /**
     * 上传文件
     * @param
     */
    String hzm;
    String del_flag = "0" ;
    String newwjm ="";
    String wjm;
    String xzdz;//下载地址
    String createtime;
    String mcase_ah;
    String case_type = "5";
    String case_ahnumber;
    String mlyr;
    String gsid;
    private void postfile(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("正在上传文件...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                MainApi.getInstance(SealApplyForDetailsActivity.this).submitsealApi(create_id,newwjm+hzm,
                        wjm,xzdz,mlyr,createtime,"-1",mcase_ah,case_type,case_ahnumber ,
                        del_flag,path, new GetResultCallBack() {
                            @Override
                            public void getResult(String result, int type) {
                                loadingDialog.dismiss();
                                if(type == Constants.TYPE_SUCCESS){//上传成功返回当前数据源ok
                                    newwjm = sdf.format(new Date());
                                    getsealfileid();
                                }else BaseApi.showErrMsg(SealApplyForDetailsActivity.this,result);
                            }
                        });
            }
        }).start();
    }

    /**
     * 查询上传的文件的id
     */
    String files_id;
    private void getsealfileid(){
        MainApi.getInstance(this).getsealfileidApi(wjm, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type == Constants.TYPE_SUCCESS){//上传成功返回当前数据源ok
                    List<ModelFile.ResultBean> r = GsonUtil.fromJsonList(new Gson(),result,ModelFile.ResultBean.class);
                    files_id = fileidq+r.get(0).getId();
                    insertsealfileid();
                }else BaseApi.showErrMsg(SealApplyForDetailsActivity.this,result);
            }
        });
    }

    /**
     * 修改seal表中的files_id
     */
    private void insertsealfileid(){
        MainApi.getInstance(this).insertsealfileidApi(id,files_id, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type == Constants.TYPE_SUCCESS){
                    fildid = new ArrayList<String>();
                    detail();
                }else BaseApi.showErrMsg(SealApplyForDetailsActivity.this,result);
            }
        });
    }

    /**
     * 删除文件
     */
    String fileid;
    private void delseal(final int i){
        loadingDialog.setLoadingContent("加载中...");
        loadingDialog.show();
        MainApi.getInstance(this).getdelsealApi(id, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loadingDialog.dismiss();
                if(type == Constants.TYPE_SUCCESS){
                    list.remove(i);
                    sealApplyForDetailsAdapter.notifyDataSetChanged();
                    Intent intent = new Intent(SealApplyForDetailsActivity.this,SealApplyForActivity.class);
                    startActivity(intent);
                    finish();
                }else BaseApi.showErrMsg(SealApplyForDetailsActivity.this,result);
            }
        });
    }

    /**
     * 查询文件
     */
    String id;
    String fileidq ="";
    List<String> fildid;
    SealApplyForDetailsAdapter sealApplyForDetailsAdapter;
    private void detail() {
        MainApi.getInstance(this).getinsertsealApi(id, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if (type == Constants.TYPE_SUCCESS) {
                    List<ModelSealApplyFor.ResultBean> resultbean = GsonUtil.fromJsonList(new Gson(),result,
                            ModelSealApplyFor.ResultBean.class);
                    list.clear();
                    list.addAll(resultbean);

                    for(ModelSealApplyFor.ResultBean bean : resultbean){
                        fildid.add( bean.getId());
                        fileidq += bean.getId()+",";
                    }
                    sealApplyForDetailsAdapter.notifyDataSetChanged();
                } else BaseApi.showErrMsg(SealApplyForDetailsActivity.this, result);
            }
        });
    }
}
