package com.example.administrator.qingming.wuyong;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.administrator.qingming.HomePageBottomActivity;
import com.example.administrator.qingming.R;
import com.example.administrator.qingming.work.AddCaseActivity;
import com.example.administrator.qingming.wuyong.CalendarActivity;
import com.example.administrator.qingming.work.CaseManageActivity;
import com.example.administrator.qingming.work.ConflictofInterestActivity;
import com.example.administrator.qingming.activity.MyCaseActivity;
import com.example.administrator.qingming.work.NetImageLocadHolder;
import com.example.administrator.qingming.work.WorkLogActivity;

import java.util.Arrays;
import java.util.List;


/**
 * Created by Administrator on 2017/5/2.
 */

public class WorkFragment extends Fragment {
    private TextView judicialaddress,calendarben,conflictofinterest,addcase,minecase,worklog,courttrial,
            publicsecurity,judicialcase,examineandapprove,incomeandexpenses,collection,customermanagement,signin;
    private ConvenientBanner convenientBanner;
    private String[] images = {"http://img2.imgtn.bdimg.com/it/u=3093785514,1341050958&fm=21&gp=0.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"};
    //轮播下面的小点
    private int[] indicator={R.mipmap.ic_page_indicator,R.mipmap.ic_page_indicator_focused};
    //网络图片加载地址的集合
    private List<String> bean;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_work, null);
        convenientBanner = (ConvenientBanner) view.findViewById(R.id.convenientBanner);

        bean = Arrays.asList(images);
        //设置指示器是否可见
        convenientBanner.setPointViewVisible(true);
        //设置小点
        convenientBanner.setPageIndicator(indicator);
        //允许手动轮播
        convenientBanner.setManualPageable(true);
        //设置自动轮播的时间
        convenientBanner.startTurning(3000);
        //设置点击事件    泛型为具体实现类ImageLoaderHolder
        convenientBanner.setPages(new CBViewHolderCreator<NetImageLocadHolder>() {
            @Override
            public NetImageLocadHolder createHolder() {
                return new NetImageLocadHolder();
            }
        },bean);
        //设置指示器的方向
        convenientBanner.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);

        convenientBanner.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getActivity(), "点击了"+convenientBanner.getCurrentItem(),Toast.LENGTH_SHORT).show();
            }
        });

        initView();
        return view;
    }

    private void initView(){
        judicialaddress = (TextView) view.findViewById(R.id.judicial_address);
        calendarben = (TextView) view.findViewById(R.id.calendar_btn);
        conflictofinterest = (TextView) view.findViewById(R.id.conflict_of_interest);
        addcase = (TextView) view.findViewById(R.id.addcase);
        minecase = (TextView) view.findViewById(R.id.minecase);
        worklog = (TextView) view.findViewById(R.id.work_log);
        courttrial = (TextView) view.findViewById(R.id.court_trial);
        publicsecurity = (TextView) view.findViewById(R.id.public_security);
        judicialcase = (TextView) view.findViewById(R.id.judicial_case);
        examineandapprove = (TextView) view.findViewById(R.id.examine_and_approve);
        incomeandexpenses = (TextView) view.findViewById(R.id.income_and_expenses);
        collection = (TextView) view.findViewById(R.id.collection);
        customermanagement = (TextView) view.findViewById(R.id.customer_management);
        signin = (TextView) view.findViewById(R.id.sign_in);

        judicialaddress.setOnClickListener(onClickListener);
        calendarben.setOnClickListener(onClickListener);
        conflictofinterest.setOnClickListener(onClickListener);
        addcase.setOnClickListener(onClickListener);
        minecase.setOnClickListener(onClickListener);
        worklog.setOnClickListener(onClickListener);
        courttrial.setOnClickListener(onClickListener);
        publicsecurity.setOnClickListener(onClickListener);
        judicialcase.setOnClickListener(onClickListener);
        examineandapprove.setOnClickListener(onClickListener);
        incomeandexpenses.setOnClickListener(onClickListener);
        collection.setOnClickListener(onClickListener);
        customermanagement.setOnClickListener(onClickListener);
        signin.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        Intent intent;
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.judicial_address:
                    intent = new Intent(getActivity(), HomePageBottomActivity.class);
                    intent.putExtra("judicial_addre8ss",1);
                    startActivityForResult(intent,1005);
                    break;
                case R.id.calendar_btn:
                    intent = new Intent(getActivity(), CalendarActivity.class);
                    startActivity(intent);
                    break;
                case R.id.conflict_of_interest:
                    intent = new Intent(getActivity(), ConflictofInterestActivity.class);
                    startActivity(intent);
                    break;
                case R.id.addcase:
                    intent = new Intent(getActivity(), AddCaseActivity.class);
                    startActivity(intent);
                    break;
                case R.id.minecase:
                    intent = new Intent(getActivity(), CaseManageActivity.class);
                    startActivity(intent);
                    break;
                case R.id.work_log:
                    intent = new Intent(getActivity(), WorkLogActivity.class);
                    startActivity(intent);
                    break;
                case R.id.examine_and_approve:
                    intent = new Intent(getActivity(), MyCaseActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

}
