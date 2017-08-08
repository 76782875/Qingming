package com.example.administrator.qingming.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.qingming.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class GuideActivity extends Activity implements ViewPager.OnPageChangeListener{
    private ViewPager viewpager;
    private List<ImageView> list;
    private int currentItem = 0;
    public GestureDetector mGestureDetector;
    private int flaggingWidth;
    private Button start;
    private int[] image = {R.mipmap.aaa,R.mipmap.a98,R.mipmap.a99};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
        setContentView(R.layout.activity_yindao);

        // 获取分辨率
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        flaggingWidth = dm.widthPixels / 3;

        initView();
    }

    private void initView() {
        viewpager = (ViewPager) findViewById(R.id.viewpage);
        start = (Button) findViewById(R.id.start);

        list = new ArrayList<>();
        // 初始化图片资源
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        for(int i=0;i<image.length;i++){
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(image[i]);
            list.add(imageView);
        }

        ViewPagerAdapter viewpagerAdapter = new ViewPagerAdapter(GuideActivity.this,list);
        viewpager.setAdapter(viewpagerAdapter);
        viewpager.setCurrentItem(0);//设置默认显示
        viewpager.setOnPageChangeListener(this);
    }


    public class ViewPagerAdapter extends PagerAdapter {

        private Context context;
        private List<ImageView> mDatas;

        public ViewPagerAdapter(Context context, List<ImageView> Datas) {
            this.context = context;
            this.mDatas = Datas;
        }

        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        //初始化position位置的界面
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mDatas.get(position));
            return mDatas.get(position);
        }

        //对不在界面内的导航页进行删除
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mDatas.get(position));
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentItem = position;
        if(position == 2){
            start.setVisibility(View.VISIBLE);
            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(GuideActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }else {
            start.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
