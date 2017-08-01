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
import android.view.Window;
import android.widget.ImageView;

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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
        setContentView(R.layout.activity_yindao);

        slipToMain();
        // 获取分辨率
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        flaggingWidth = dm.widthPixels / 3;
        initView();
    }

    private void initView() {
        viewpager = (ViewPager) findViewById(R.id.viewpage);
        ImageView view1 = new ImageView(this, null);
        view1.setBackgroundResource(R.mipmap.aaa);
        ImageView view2 = new ImageView(this, null);
        view1.setBackgroundResource(R.mipmap.a98);
        ImageView view3 = new ImageView(this, null);
        view1.setBackgroundResource(R.mipmap.a99);
        list = new ArrayList<>();
        // 初始化图片资源
        list.add(view1);
        list.add(view2);
        list.add(view3);

        ViewPagerAdapter viewpagerAdapter = new ViewPagerAdapter(GuideActivity.this,list);
        viewpager.setAdapter(viewpagerAdapter);
        viewpager.setCurrentItem(0);//设置默认显示
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
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    private void slipToMain(){
        mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                        if (currentItem == 2) {
                            if ((e1.getRawX() - e2.getRawX()) >= flaggingWidth) {
                                Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                                return true;
                            }
                        }
                        return false;
                    }

                });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentItem = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
