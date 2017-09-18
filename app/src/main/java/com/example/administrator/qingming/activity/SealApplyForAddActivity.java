package com.example.administrator.qingming.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.api.BaseApi;
import com.example.administrator.qingming.api.MainApi;
import com.example.administrator.qingming.dialog.LoadingDialog;
import com.example.administrator.qingming.interfaces.GetResultCallBack;
import com.example.administrator.qingming.model.Constants;
import com.example.administrator.qingming.model.ModelFile;
import com.example.administrator.qingming.model.MyCaseModel;
import com.example.administrator.qingming.qinminutils.FileUtils;
import com.example.administrator.qingming.qinminutils.GsonUtil;
import com.example.administrator.qingming.work.DatePickDialogUtil;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/9/11 0011.
 */

public class SealApplyForAddActivity extends Activity {
    private ImageView back_btn;
    private String initStartDateTime ; // 初始化开始时间
    private TextView type,case_ah,filename,time,lyr;
    private EditText wtr,edit_log;
    private Button choose,filepost,post;
    private List<MyCaseModel.ResultBean> list;
    private SimpleDateFormat sdf,sDateFormat;
    private LoadingDialog loadingDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seal_apply_for_add);

        SharedPreferences sharedPreferences = getSharedPreferences("qinmin", Context.MODE_PRIVATE);
        create_id = sharedPreferences.getString("id","");
        gsid = sharedPreferences.getString("cid","");
        mlyr = sharedPreferences.getString("name","");

        sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
        if(newwjm.equals("")){
            newwjm = sdf.format(new Date());
        }else {
            newwjm = sdf.format(new Date());
        }

        //获取日期
        sDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        createtime = sDateFormat.format(new java.util.Date());

        initView();
        sealah();

        lyr.setText(mlyr);
        wtr.addTextChangedListener(new newtextWatcher(wtr));
        edit_log.addTextChangedListener(new newtextWatcher(edit_log));
    }

    private void initView() {
        back_btn = (ImageView) findViewById(R.id.back_btn);
        loadingDialog = new LoadingDialog(this);
        list = new ArrayList<>();
        type = (TextView) findViewById(R.id.type);
        case_ah = (TextView) findViewById(R.id.case_ah);
        filename = (TextView) findViewById(R.id.filename);
        time = (TextView) findViewById(R.id.time);
        lyr = (TextView) findViewById(R.id.lyr);
        wtr = (EditText) findViewById(R.id.wtr);
        edit_log = (EditText) findViewById(R.id.edit_log);
        choose = (Button) findViewById(R.id.choose);
        filepost = (Button) findViewById(R.id.filepost);
        post = (Button) findViewById(R.id.post);

        type.setOnClickListener(onclicklisten);
        case_ah.setOnClickListener(onclicklisten);
        time.setOnClickListener(onclicklisten);
        choose.setOnClickListener(onclicklisten);
        filepost.setOnClickListener(onclicklisten);
        post.setOnClickListener(onclicklisten);
        back_btn.setOnClickListener(onclicklisten);
    }

    View.OnClickListener onclicklisten = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back_btn:
                    finish();
                    break;
                case R.id.type:
                    dialog();
                    break;
                case R.id.case_ah:
                    ahdialog();
                    break;
                case R.id.time:
                    time.setText(initStartDateTime);
                    time.setTextColor(getResources().getColor(R.color.black));
                    DatePickDialogUtil dateTimePickDialogUtil5 = new DatePickDialogUtil(initStartDateTime,SealApplyForAddActivity.this);
                    dateTimePickDialogUtil5.dateTimePicKDialog(time);
                    break;
                case R.id.choose:
                    choosefile();
                    break;
                case R.id.filepost:
                    if(hzm.equals(".doc")||hzm.equals(".docx")){
                        postfile();
                    }else {
                        Toast.makeText(SealApplyForAddActivity.this,"只能上传doc,docx文件",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.post:
                    if(!TextUtils.isEmpty(wtr.getText())){
                        if(!case_ah.getText().equals("请选择案号")){
                            if(!time.getText().equals("请选择时间")){
                                if(!TextUtils.isEmpty(files_id)){
                                    officeseal();
                                    bzsm = edit_log.getText().toString();
                                    seal_state = "1";
                                    mwtr = wtr.getText().toString();
                                    postseal();
                                }else {
                                    Toast.makeText(SealApplyForAddActivity.this,"请上传文件",Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(SealApplyForAddActivity.this,"请选择时间",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(SealApplyForAddActivity.this,"请选择案号",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(SealApplyForAddActivity.this,"委托人不能为空",Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    private void officeseal(){
        String date = case_ahnumber.substring(0,4);
        String lx = case_ahnumber.substring(5,6);
        String ahnum = case_ahnumber.substring(7,case_ahnumber.length());
        //用印事项(1：公函；2：介绍信；3：律师函；4：委托代理合同；5：其他；6：授权委托书；7：收案审批表；8：律师会见介绍信)
        if(type.getText().toString().equals("公函")){
            official_seal = "1";
        }else if(type.getText().toString().equals("介绍信")){
            official_seal = "2";
        }else if(type.getText().toString().equals("律师函")){
            official_seal = "3";
        }else if(type.getText().toString().equals("委托代理合同")){
            official_seal = "4";
        }else if(type.getText().toString().equals("其他")){
            official_seal = "5";
        }else if(type.getText().toString().equals("授权委托书")){
            official_seal = "6";
        }else if(type.getText().toString().equals("收案审批表")){
            official_seal = "7";
        }else if(type.getText().toString().equals("律师会见介绍信")){
            official_seal = "8";
        }


        //根据选择的文件类型来拼接得到最后的函号/编号
        if(official_seal.equals("1")){
            //公函
            seal_name="( "+date+" )律师"+lx+"函"+ahnum;
        }
        if(official_seal.equals("2")){
            //介绍信
            seal_name="( "+date+" )律师"+lx+"介"+ahnum;
        }
        if(official_seal.equals("3")){
            //律师函
            seal_name="( "+date+" )律师"+lx+"律"+ahnum;
        }
        if(official_seal.equals("4")){
            //委托代理合同
            seal_name="( "+date+" )律师"+lx+"合同"+ahnum;
        }
        if(official_seal.equals("5")){
            //其他
            seal_name="( "+date+" )律师"+lx+ahnum;
        }
        if(official_seal.equals("6")){
            //授权委托书
            seal_name="( "+date+" )律师"+lx+ahnum;
        }

        if(official_seal.equals("7")){
            //收案审批表
            seal_name="( "+date+" )律师"+lx+"审"+ahnum;
        }
        if(official_seal.equals("8")){
            //律师会见介绍信
            seal_name="( "+date+" )律师"+lx+"介"+ahnum;
        }
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
            Toast.makeText(SealApplyForAddActivity.this, "请安装文件管理器", Toast.LENGTH_SHORT).show();
        }
    }

    private String path;
    private static final int FILE_SELECT_CODE = 1001;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK && FILE_SELECT_CODE == requestCode){
            Uri uri = data.getData();
            Log.i("uri-------->", "" + uri);
//            path = Uri.decode(data.getDataString());
//            path = uri.getPath();
            path = FileUtils.getPath(SealApplyForAddActivity.this,uri);
            wjm = path.substring(path.lastIndexOf("/") + 1, path.length());//获取文件名
            filename.setText(wjm);
            hzm = FileUtils.getLastUrl(path);//获取后缀名
            xzdz =create_id +"/"+newwjm+hzm;//下载地址
        }
    }

    String[] items = {"公函","介绍信","律师函","委托代理合同","其他","授权委托书","收案审批表","律师会见介绍信"};
    private void dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this,AlertDialog.THEME_HOLO_LIGHT);
        builder.setTitle("选择文件类型");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                type.setText(items[which]);
            }
        }).show();
    }

    private void ahdialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this,AlertDialog.THEME_HOLO_LIGHT);
        builder.setTitle("请选择案号");
        builder.setItems(sealah, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                case_ah.setText(sealah[which]);
                case_ahnumber = case_ah.getText().toString();
                mcase_ah = sealahid.get(which);
            }
        }).show();
    }


    String[] sealah;
    List<String> sealahid;
    private void sealah(){
        MainApi.getInstance(this).getsealahApi(create_id, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type == Constants.TYPE_SUCCESS){
                    List<MyCaseModel.ResultBean> resultBeen = GsonUtil.fromJsonList(new Gson(),result, MyCaseModel.ResultBean.class);
                    list.addAll(resultBeen);
                    sealah = new String[list.size()];
                    sealahid = new ArrayList<>();
                    for(int i=0;i<resultBeen.size();i++){
                        sealah[i] = list.get(i).getAh_number();
                        sealahid.add(list.get(i).getId());
                    }
                }else BaseApi.showErrMsg(SealApplyForAddActivity.this,result);
            }
        });
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
    private void postfile(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("正在上传文件...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                MainApi.getInstance(SealApplyForAddActivity.this).submitsealApi(create_id,newwjm+hzm,
                        wjm,xzdz,mlyr,createtime,"-1",mcase_ah,case_type,case_ahnumber ,
                        del_flag,path, new GetResultCallBack() {
                            @Override
                            public void getResult(String result, int type) {
                                loadingDialog.dismiss();
                                if(type == Constants.TYPE_SUCCESS){//上传成功返回当前数据源ok
                                    newwjm = sdf.format(new Date());
                                    getsealfileid();
                                }else BaseApi.showErrMsg(SealApplyForAddActivity.this,result);
                            }
                        });
            }
        }).start();
    }


    /**
     * 提交
     */
    String seal_name;
    String official_seal;
    String mwtr;
    String create_id;
    String gsid;
    String files_id;
    String bzsm;
    String seal_state;
    String mlyr;
    private void postseal(){
        MainApi.getInstance(this).postsealApi(seal_name, official_seal, mwtr, create_id, gsid,
                mcase_ah, case_ahnumber, files_id, createtime, bzsm, seal_state,
                 mlyr, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type == Constants.TYPE_SUCCESS){//上传成功返回当前数据源ok
                    Intent intent = new Intent(SealApplyForAddActivity.this,SealApplyForActivity.class);
                    startActivity(intent);
                    finish();
                }else BaseApi.showErrMsg(SealApplyForAddActivity.this,result);
            }
        });
    }

    /**
     * 查询上传文件id
     */
    private void getsealfileid(){
        MainApi.getInstance(this).getsealfileidApi(wjm, new GetResultCallBack() {
                    @Override
                    public void getResult(String result, int type) {
                        if(type == Constants.TYPE_SUCCESS){//上传成功返回当前数据源ok
                            List<ModelFile.ResultBean> r = GsonUtil.fromJsonList(new Gson(),result,ModelFile.ResultBean.class);
                            files_id = r.get(0).getId();
                        }else BaseApi.showErrMsg(SealApplyForAddActivity.this,result);
                    }
                });
    }

    class  newtextWatcher implements TextWatcher {
        private EditText edit;
        public newtextWatcher(EditText editText){
            this.edit = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String msg4 = edit.getText().toString();
            Log.i("",""+msg4);
        }
    }
}
