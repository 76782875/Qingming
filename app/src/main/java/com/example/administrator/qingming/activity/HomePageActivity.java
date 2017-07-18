package com.example.administrator.qingming.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.qingming.fragment.ApplicationFragment;
import com.example.administrator.qingming.ExamineAndApproveActivity;
import com.example.administrator.qingming.R;
import com.example.administrator.qingming.api.BaseApi;
import com.example.administrator.qingming.api.MainApi;
import com.example.administrator.qingming.fragment.LawJournalFragment;
import com.example.administrator.qingming.interfaces.GetResultCallBack;
import com.example.administrator.qingming.model.Constants;
import com.example.administrator.qingming.model.PersonalDataModel;
import com.example.administrator.qingming.qinminutils.GsonUtil;
import com.example.administrator.qingming.work.AddCaseActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/19 0019.
 */

public class HomePageActivity extends Activity {
    private LinearLayout personalinformation;
    private TextView minecase,addcase,examine_and_approve,examine,file,message,cantacts,
            application,change,news,qiandao,username,officename;
    private static String path="/sdcard/myHead/";//sd路径
    private ImageView headimage;
    private static final int ME_REQUEST_CODE = 0x222;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        SharedPreferences sharedPreferences = getSharedPreferences("qinmin", Context.MODE_PRIVATE);
        id = sharedPreferences.getString("id","");

        initView();
        getHttp();

        Bitmap bt = BitmapFactory.decodeFile(path + "head.jpg");//从Sd中找头像，转换成Bitmap
        if(bt!=null){
            @SuppressWarnings("deprecation")
            Drawable drawable = new BitmapDrawable(bt);//转换成drawable
            headimage.setImageDrawable(drawable);
        }else{
            /**
             *	如果SD里面没有则需要从服务器取头像，取回来的头像再保存在SD中
             *
             */
        }

    }

    private void initView() {
        list = new ArrayList<>();
        personalinformation = (LinearLayout) findViewById(R.id.personal_information);
        minecase = (TextView) findViewById(R.id.minecase);
        addcase = (TextView) findViewById(R.id.addcase);
        examine_and_approve = (TextView) findViewById(R.id.examine_and_approve);
        examine = (TextView) findViewById(R.id.examine);
        file = (TextView) findViewById(R.id.file);
        message = (TextView) findViewById(R.id.message);
        cantacts = (TextView) findViewById(R.id.cantacts);
        application = (TextView) findViewById(R.id.application);
        change = (TextView) findViewById(R.id.change);
        news = (TextView) findViewById(R.id.news);
        headimage = (ImageView) findViewById(R.id.head_image);
        qiandao = (TextView) findViewById(R.id.qiandao);
        username = (TextView) findViewById(R.id.username);
        officename = (TextView) findViewById(R.id.officename);

        qiandao.setOnClickListener(onclicklistener);
        personalinformation.setOnClickListener(onclicklistener);
        minecase.setOnClickListener(onclicklistener);
        addcase.setOnClickListener(onclicklistener);
        examine_and_approve.setOnClickListener(onclicklistener);
        examine.setOnClickListener(onclicklistener);
        file.setOnClickListener(onclicklistener);
        message.setOnClickListener(onclicklistener);
        cantacts.setOnClickListener(onclicklistener);
        application.setOnClickListener(onclicklistener);
        change.setOnClickListener(onclicklistener);
        news.setOnClickListener(onclicklistener);
    }
    View.OnClickListener onclicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){
                case R.id.personal_information://跳转到个人中心页面
                    intent = new Intent(HomePageActivity.this,MeActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("id",id);
                    bundle.putString("name1",name1);
                    bundle.putString("name",name);
                    bundle.putString("email",email);
                    bundle.putString("remarks",remarks);
                    bundle.putString("mobile",mobile);
                    intent.putExtras(bundle);
                    startActivityForResult(intent,ME_REQUEST_CODE);
                    break;
                case R.id.minecase://跳转到我的案件页面
                    intent = new Intent(HomePageActivity.this,MyCaseActivity.class);
                    startActivity(intent);
                    break;
                case R.id.addcase://跳转到新增案件页面
                    intent = new Intent(HomePageActivity.this,AddCaseActivity.class);
                    startActivity(intent);
                    break;
                case R.id.examine_and_approve://跳转到审批页面
                    intent = new Intent(HomePageActivity.this,ExamineAndApproveActivity.class);
                    startActivity(intent);
                    break;
                case R.id.examine://跳转到财务审批页面
                    intent = new Intent(HomePageActivity.this,ExamineAndApproveActivity.class);
                    startActivity(intent);
                    break;
                case R.id.file://跳转到文件页面
                    intent = new Intent(HomePageActivity.this,FilesActivity.class);
                    startActivity(intent);
                    break;
                case R.id.message://跳转到消息页面
                    intent = new Intent(HomePageActivity.this,NewsActivity.class);
                    startActivity(intent);
                    break;
                case R.id.cantacts://跳转到联系人页面
                    intent = new Intent(HomePageActivity.this,LawJournalFragment.class);
                    startActivity(intent);
                    break;
                case R.id.application://跳转到工具页面
                    intent = new Intent(HomePageActivity.this,ApplicationFragment.class);
                    startActivity(intent);
                    break;
                case R.id.change://跳转到变更律师页面
                    intent = new Intent(HomePageActivity.this,ChangeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.news://跳转到新闻资讯页面
                    intent = new Intent(HomePageActivity.this,PressActivity.class);
                    startActivity(intent);
                    break;
                case R.id.qiandao://跳转到新闻资讯页面
                    intent = new Intent(HomePageActivity.this,MapActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

    private String id;
    private String name1;
    private String email;
    private String mobile;
    private String remarks;
    private String name;
    List<PersonalDataModel.ResultBean> list;
    private void getHttp(){
        MainApi.getInstance(this).getpersonal_dateApi(id, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type == Constants.TYPE_SUCCESS){
                    List<PersonalDataModel.ResultBean> resultBean = GsonUtil.fromJsonList(new Gson(),
                            result, PersonalDataModel.ResultBean.class);
                    list.addAll(resultBean);
                    id = list.get(0).getId();
                    name1 = list.get(0).getName1();
                    name = list.get(0).getName();
                    email = list.get(0).getEmail();
                    mobile = list.get(0).getMobile();
                    remarks = list.get(0).getRemarks();

                    username.setText(name1);
                    officename.setText(name);

                }else BaseApi.showErrMsg(HomePageActivity.this,result);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case ME_REQUEST_CODE://提交文件成功刷新本页数据
                    break;
            }
        }
    }
}
