package com.example.administrator.qingming;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.qingming.activity.MainActivity;
import com.example.administrator.qingming.api.BaseApi;
import com.example.administrator.qingming.api.MainApi;
import com.example.administrator.qingming.fragment.LawJournalFragment;
import com.example.administrator.qingming.fragment.ApplicationFragment;
import com.example.administrator.qingming.fragment.HomePageFragment;
import com.example.administrator.qingming.fragment.MeFragment;
import com.example.administrator.qingming.interfaces.GetResultCallBack;
import com.example.administrator.qingming.model.Constants;
import com.example.administrator.qingming.wuyong.WorkFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/20.
 */

public class HomePageBottomActivity extends FragmentActivity{
    public RadioGroup homepagebtn;
    private RadioButton me;
    private RadioButton homepage;
    private RadioButton news;
    private RadioButton work;
    private ViewPager viewPager;
    private ArrayList<Fragment> fragmentArrayList;
    private HomePageFragment homePageFragment;
    private LawJournalFragment judicialAddressFragment;
    private ApplicationFragment applicationFragment;
    private MeFragment meFragment;
    WorkFragment workFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hompage_bottom);

        init();

        //接收传递回来的ID
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            Integer id = bundle.getInt("judicial_address");
            viewPager.setCurrentItem(1);
        }
    }
    //找到控件ID
    private void init(){
        homepagebtn = (RadioGroup) findViewById(R.id.homepage_btn);
        me = (RadioButton) findViewById(R.id.me);
        homepage = (RadioButton) findViewById(R.id.homepage);
        news = (RadioButton) findViewById(R.id.news);
        work = (RadioButton) findViewById(R.id.work);
        viewPager = (ViewPager) findViewById(R.id.id_viewpage);

        fragmentArrayList = new ArrayList<>();
        homePageFragment = new HomePageFragment();
        applicationFragment = new ApplicationFragment();
        judicialAddressFragment=new LawJournalFragment();
        meFragment = new MeFragment();
        fragmentArrayList.add(homePageFragment);
        fragmentArrayList.add(applicationFragment);
        fragmentArrayList.add(judicialAddressFragment);
        fragmentArrayList.add(meFragment);

        QinMinPagerAdapter instantaneousPagerAdapter = new QinMinPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(instantaneousPagerAdapter);
        viewPager.setCurrentItem(0);//设置默认显示
        homepage.setChecked(true);
        viewPager.setOffscreenPageLimit(fragmentArrayList.size());
        homepage.setTextColor(getResources().getColor(R.color.blue));
        news.setOnClickListener(onClickListener);
        homepage.setOnClickListener(onClickListener);
        work.setOnClickListener(onClickListener);
        me.setOnClickListener(onClickListener);
        viewPager.setOnPageChangeListener(onPageChangeListener);
    }

    //    重写FragmentPagerAdapter方法
    public class QinMinPagerAdapter extends FragmentPagerAdapter {
        public QinMinPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }
    }

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            switch (i){
                case 0:
                    homepage.setChecked(true);
                    homepage.setTextColor(getResources().getColor(R.color.blue));
                    news.setTextColor(getResources().getColor(R.color.black));
                    me.setTextColor(getResources().getColor(R.color.black));
                    work.setTextColor(getResources().getColor(R.color.black));
                    break;
                case 1:
                    work.setChecked(true);
                    work.setTextColor(getResources().getColor(R.color.blue));
                    news.setTextColor(getResources().getColor(R.color.black));
                    me.setTextColor(getResources().getColor(R.color.black));
                    homepage.setTextColor(getResources().getColor(R.color.black));
                    break;
                case 2:
                    news.setChecked(true);
                    news.setTextColor(getResources().getColor(R.color.blue));
                    homepage.setTextColor(getResources().getColor(R.color.black));
                    me.setTextColor(getResources().getColor(R.color.black));
                    work.setTextColor(getResources().getColor(R.color.black));
                    break;
                case 3:
                    me.setChecked(true);
                    me.setTextColor(getResources().getColor(R.color.blue));
                    homepage.setTextColor(getResources().getColor(R.color.black));
                    news.setTextColor(getResources().getColor(R.color.black));
                    work.setTextColor(getResources().getColor(R.color.black));
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.homepage:
                    viewPager.setCurrentItem(0);
                    homepage.setTextColor(getResources().getColor(R.color.blue));
                    news.setTextColor(getResources().getColor(R.color.black));
                    me.setTextColor(getResources().getColor(R.color.black));
                    work.setTextColor(getResources().getColor(R.color.black));
                    break;
                case R.id.work:
                    viewPager.setCurrentItem(1);
                    work.setTextColor(getResources().getColor(R.color.blue));
                    homepage.setTextColor(getResources().getColor(R.color.black));
                    news.setTextColor(getResources().getColor(R.color.black));
                    me.setTextColor(getResources().getColor(R.color.black));
                    break;
                case R.id.news:
                    viewPager.setCurrentItem(2);
                    news.setTextColor(getResources().getColor(R.color.blue));
                    homepage.setTextColor(getResources().getColor(R.color.black));
                    me.setTextColor(getResources().getColor(R.color.black));
                    work.setTextColor(getResources().getColor(R.color.black));
                    break;

                case R.id.me:
                    viewPager.setCurrentItem(3);
                    me.setTextColor(getResources().getColor(R.color.blue));
                    homepage.setTextColor(getResources().getColor(R.color.black));
                    news.setTextColor(getResources().getColor(R.color.black));
                    work.setTextColor(getResources().getColor(R.color.black));
                    break;
            }
        }
    };




}
