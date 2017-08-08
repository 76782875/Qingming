package com.example.administrator.qingming.fragment;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.activity.MainActivity;
import com.example.administrator.qingming.activity.MeActivity;
import com.example.administrator.qingming.api.BaseApi;
import com.example.administrator.qingming.api.MainApi;
import com.example.administrator.qingming.dialog.LoadingDialog;
import com.example.administrator.qingming.interfaces.GetResultCallBack;
import com.example.administrator.qingming.model.Constants;
import com.example.administrator.qingming.model.PersonalDataModel;
import com.example.administrator.qingming.qinminutils.GsonUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.ACTIVITY_SERVICE;

/**
 * Created by Administrator on 2017/4/20.
 */

public class MeFragment extends Fragment {
    private List<PersonalDataModel.ResultBean> list1;
    private EditText consignor,weight,phone_num,gender;
    private TextView officename,submit_btn,tuichu_btn;
    String myofficename,myconsignor,myemail,myphone_num,bz;
    View view;
    private LoadingDialog loadingDialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_me,null);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("qinmin", Context.MODE_PRIVATE);
        id = sharedPreferences.getString("id","");

        initView();
        getHttp();
        return view;
    }

    private void initView() {
        list1 = new ArrayList<>();
        loadingDialog = new LoadingDialog(getActivity());
        officename= (TextView) view.findViewById(R.id.officename);
        consignor= (EditText) view.findViewById(R.id.consignor);
        weight= (EditText) view.findViewById(R.id.weight);
        phone_num= (EditText) view.findViewById(R.id.phone_num);
        gender= (EditText) view.findViewById(R.id.gender);
        submit_btn= (TextView) view.findViewById(R.id.submit_btn);
        tuichu_btn = (TextView) view.findViewById(R.id.tuichu_btn);

        submit_btn.setOnClickListener(onclick);
        tuichu_btn.setOnClickListener(onclick);
        consignor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                myconsignor = s.toString();
            }
        });//姓名
        weight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                myemail = s.toString();
            }
        });//邮箱
        phone_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                myphone_num = s.toString();
            }
        });//电话
        gender.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                bz =s.toString();
            }
        });//备注
    }

    View.OnClickListener onclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.submit_btn:
                    postHttp();
                    break;
                case R.id.tuichu_btn:
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
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
    private void getHttp(){
        MainApi.getInstance(getActivity()).getpersonal_dateApi(id, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type == Constants.TYPE_SUCCESS){
                    List<PersonalDataModel.ResultBean> resultBean = GsonUtil.fromJsonList(new Gson(),
                            result, PersonalDataModel.ResultBean.class);
                    list1.addAll(resultBean);
                    id = list1.get(0).getId();
                    name1 = list1.get(0).getName1();
                    name = list1.get(0).getName();
                    email = list1.get(0).getEmail();
                    mobile = list1.get(0).getMobile();
                    remarks = list1.get(0).getRemarks();

                    consignor.setText(name1);
                    officename.setText(name);
                    weight.setText(email);
                    phone_num.setText(mobile);
                    gender.setText(remarks);

                    myofficename=officename.getText().toString();
                    myemail=weight.getText().toString();
                    myphone_num=phone_num.getText().toString();
                    bz=gender.getText().toString();
                    myconsignor=consignor.getText().toString();
                }else BaseApi.showErrMsg(getActivity(),result);
            }
        });
    }


    private void postHttp(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("正在上传数据...");
        MainApi.getInstance(getActivity()).getinsert_dataApi(id, myofficename, myemail, myphone_num, bz,
                myconsignor, new GetResultCallBack() {
                    @Override
                    public void getResult(String result, int type) {
                        if(type == Constants.TYPE_SUCCESS){
                            loadingDialog.dismiss();
                            Toast.makeText(getActivity(),"上传成功",Toast.LENGTH_SHORT).show();
                        }else BaseApi.showErrMsg(getActivity(),result);
                    }
                });
    }

}
