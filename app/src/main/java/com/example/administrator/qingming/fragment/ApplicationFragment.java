package com.example.administrator.qingming.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.qingming.ExamineAndApproveActivity;
import com.example.administrator.qingming.R;
import com.example.administrator.qingming.activity.ChangeActivity;
import com.example.administrator.qingming.activity.FaPiaoActivity;
import com.example.administrator.qingming.activity.KaoQinActivity;
import com.example.administrator.qingming.activity.LsszActivity;
import com.example.administrator.qingming.activity.LszsActivity;
import com.example.administrator.qingming.activity.MapActivity;
import com.example.administrator.qingming.activity.MyCaseActivity;
import com.example.administrator.qingming.activity.ShoufeiActivity;
import com.example.administrator.qingming.activity.WorkActivity;
import com.example.administrator.qingming.work.AddCaseActivity;


/**
 * 应用页面
 * Created by Administrator on 2017/4/20.
 */

public class ApplicationFragment extends Fragment {
    //办公按钮
    private TextView ls_case,zixun,spz,baz,yja,biangen,lsaj,gzsq;
    //审批按钮
    private TextView sasp,jasp,fpgl;
    //财务统计按钮
    private TextView cwsa,cwja,sfsp,lssz;
    //行政按钮
    private TextView kqdk,dkjl,zrsa,lszs;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_application, null);

        initView();
        return view;
    }

    private void initView(){
        ls_case = (TextView) view.findViewById(R.id.ls_case);
        zixun = (TextView) view.findViewById(R.id.zixun);
        spz = (TextView) view.findViewById(R.id.spz);
        baz = (TextView) view.findViewById(R.id.baz);
        yja = (TextView) view.findViewById(R.id.yja);
        biangen = (TextView) view.findViewById(R.id.biangen);
        lsaj = (TextView) view.findViewById(R.id.lsaj);
        sasp = (TextView) view.findViewById(R.id.sasp);
        jasp = (TextView) view.findViewById(R.id.jasp);
        fpgl = (TextView) view.findViewById(R.id.fpgl);
        cwsa = (TextView) view.findViewById(R.id.cwsa);
        cwja = (TextView) view.findViewById(R.id.cwja);
        sfsp = (TextView) view.findViewById(R.id.sfsp);
        lssz = (TextView) view.findViewById(R.id.lssz);
        kqdk = (TextView) view.findViewById(R.id.kqdk);
        dkjl = (TextView) view.findViewById(R.id.dkjl);
        zrsa = (TextView) view.findViewById(R.id.zrsa);
        lszs = (TextView) view.findViewById(R.id.lszs);
        gzsq = (TextView) view.findViewById(R.id.gzsq);

        gzsq.setOnClickListener(onClickListener);
        ls_case.setOnClickListener(onClickListener);
        zixun.setOnClickListener(onClickListener);
        spz.setOnClickListener(onClickListener);
        baz.setOnClickListener(onClickListener);
        yja.setOnClickListener(onClickListener);
        biangen.setOnClickListener(onClickListener);
        lsaj.setOnClickListener(onClickListener);
        sasp.setOnClickListener(onClickListener);
        jasp.setOnClickListener(onClickListener);
        fpgl.setOnClickListener(onClickListener);
        cwsa.setOnClickListener(onClickListener);
        cwja.setOnClickListener(onClickListener);
        sfsp.setOnClickListener(onClickListener);
        lssz.setOnClickListener(onClickListener);
        kqdk.setOnClickListener(onClickListener);
        dkjl.setOnClickListener(onClickListener);
        zrsa.setOnClickListener(onClickListener);
        lszs.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        Intent intent;
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.ls_case:
                    intent = new Intent(getActivity(), AddCaseActivity.class);
                    startActivity(intent);
                    break;
                case R.id.zixun:
                    intent = new Intent(getActivity(), MyCaseActivity.class);
                    intent.putExtra("cc","1");
                    intent.putExtra("index",0);
                    startActivity(intent);
                    break;
                case R.id.spz:
                    intent = new Intent(getActivity(), MyCaseActivity.class);
                    intent.putExtra("cc","1");
                    intent.putExtra("index",1);
                    startActivity(intent);
                    break;
                case R.id.baz:
                    intent = new Intent(getActivity(), MyCaseActivity.class);
                    intent.putExtra("cc","1");
                    intent.putExtra("index",2);
                    Log.e("传递过去的index值：",""+2);
                    startActivity(intent);
                    break;
                case R.id.yja:
                    intent = new Intent(getActivity(), MyCaseActivity.class);
                    intent.putExtra("cc","1");
                    intent.putExtra("index",3);
                    startActivity(intent);
                    break;
                case R.id.biangen:
                    intent = new Intent(getActivity(), ChangeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.lsaj:
                    intent = new Intent(getActivity(), MyCaseActivity.class);
                    intent.putExtra("cc","1");
                    startActivity(intent);
                    break;
                case R.id.sasp:
                    intent = new Intent(getActivity(), ExamineAndApproveActivity.class);
                    intent.putExtra("case_state",2);
                    startActivity(intent);
                    break;
                case R.id.jasp:
                    intent = new Intent(getActivity(), ExamineAndApproveActivity.class);
                    intent.putExtra("case_state",5);
                    startActivity(intent);
                    break;
                case R.id.fpgl:
                    intent = new Intent(getActivity(), FaPiaoActivity.class);
                    startActivity(intent);
//                    Toast.makeText(getActivity(),"此功能暂不开通",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.cwsa:
                    intent = new Intent(getActivity(), ExamineAndApproveActivity.class);
                    intent.putExtra("case_state",3);
                    startActivity(intent);
                    break;
                case R.id.cwja:
                    intent = new Intent(getActivity(), ExamineAndApproveActivity.class);
                    intent.putExtra("case_state",6);
                    startActivity(intent);
                    break;
                case R.id.sfsp:
                    intent = new Intent(getActivity(), ShoufeiActivity.class);
                    startActivity(intent);
                    break;
                case R.id.lssz:
                    intent = new Intent(getActivity(), LsszActivity.class);
                    startActivity(intent);
                    break;
                case R.id.kqdk:
                    intent = new Intent(getActivity(), MapActivity.class);
                    startActivity(intent);
                    break;
                case R.id.dkjl:
                    intent = new Intent(getActivity(), KaoQinActivity.class);
                    startActivity(intent);
                    break;
                case R.id.zrsa:
                    intent = new Intent(getActivity(), WorkActivity.class);
                    startActivity(intent);
                    break;
                case R.id.lszs:
                    intent = new Intent(getActivity(), LszsActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}
