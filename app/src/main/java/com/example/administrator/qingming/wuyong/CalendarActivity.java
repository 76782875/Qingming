package com.example.administrator.qingming.wuyong;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.work.AddAffairActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 */

public class CalendarActivity extends FragmentActivity {
    private static final int DATE_DIALOG = 0;
    private static final int DATE_DIALOY = 1;
    private ImageView iv_left;
    private ImageView iv_right;
    private ImageView searchbtn;
    private ImageView addbtn;
    private TextView tv_date;
    private TextView tv_week;
    private TextView tv_today;
    private MonthDateView monthDateView;
    private DrawerLayout drawerlayout;
    private LinearLayout menucalendar;
    private FrameLayout frameLayout;
    int mDrawerWidth;//抽屉全部拉出来时的宽度
    float scrollWidth;//抽屉被拉出部分的宽度
    private ImageView backdrawerbtn;
    int mYear,mMonth,mDay;
    private EditText editcalendar;
    private TextView starttime,finishtime,affair,work;
    private Button submitbtn;//提交按钮
    private String edt;
    private ListView listcalendar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        //添加事务天数
        List<Integer> list = new ArrayList<Integer>();
        list.add(10);
        list.add(12);
        list.add(15);
        list.add(16);

        initView();

        //获取日期
        final Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);

        //测量抽屉的宽度和高度
        measureView(menucalendar);
        mDrawerWidth=menucalendar.getMeasuredWidth();

        monthDateView.setTextView(tv_date,tv_week);
        monthDateView.setDaysHasThingList(list);

        setOnlistener();
    }

    private void initView(){
        iv_left = (ImageView) findViewById(R.id.iv_left);
        iv_right = (ImageView) findViewById(R.id.iv_right);
        monthDateView = (MonthDateView) findViewById(R.id.monthDateView);
        tv_date = (TextView) findViewById(R.id.date_text);
        tv_week  =(TextView) findViewById(R.id.week_text);
        tv_today = (TextView) findViewById(R.id.tv_today);
        addbtn = (ImageView) findViewById(R.id.add_btn);
        searchbtn = (ImageView) findViewById(R.id.search_btn);
        backdrawerbtn = (ImageView) findViewById(R.id.back_drawer_btn);
        drawerlayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        menucalendar = (LinearLayout) findViewById(R.id.menu_calendar);
        frameLayout = (FrameLayout) findViewById(R.id.framelayout);
        editcalendar = (EditText) findViewById(R.id.edit_calendar);
        starttime = (TextView) findViewById(R.id.start_time);
        finishtime = (TextView) findViewById(R.id.finish_time);
        submitbtn = (Button) findViewById(R.id.submit_btn);
        affair = (TextView) findViewById(R.id.affair);
        work = (TextView) findViewById(R.id.work);
        listcalendar = (ListView) findViewById(R.id.list_calendar);
    }

    private void setOnlistener(){
        iv_left.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                monthDateView.onLeftClick();
            }
        });
        iv_right.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                monthDateView.onRightClick();
            }
        });
        tv_today.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                monthDateView.setTodayToView();
            }
        });
        monthDateView.setDateClick(new MonthDateView.DateClick() {

            @Override
            public void onClickOnDate() {
                Toast.makeText(getApplication(), "点击了：" + monthDateView.getmSelDay(), Toast.LENGTH_SHORT).show();
            }
        });
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开抽屉
                drawerlayout.openDrawer(Gravity.RIGHT);
            }
        });
        backdrawerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭抽屉
                drawerlayout.closeDrawer(Gravity.RIGHT);
            }
        });
        drawerlayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View view, float v) {
                //因为arg1的范围是0.0-1.0，是一个相对整个抽屉宽度的比例
                //所以要准换成
                scrollWidth=v*mDrawerWidth;
                //setScroll中的参数，正数表示向左移动，负数向右
                frameLayout.setScrollX((int)(1*scrollWidth));
            }

            @Override
            public void onDrawerOpened(View view) {

            }

            @Override
            public void onDrawerClosed(View view) {

            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });
        editcalendar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                edt = s.toString();
            }
        });
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this,AddAffairActivity.class);
                startActivity(intent);
            }
        });
        //选择事件类型
        affair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWorkDialog();
            }
        });
        //时间选择器
        starttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG);
            }
        });
        finishtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOY);
            }
        });
        //提交按钮
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("submit=========","====>"+starttime.getText().toString());
                Log.i("submit=========","====>"+finishtime.getText().toString());
                Log.i("edt=========","====>"+edt);
                drawerlayout.closeDrawer(Gravity.RIGHT);
            }
        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG:
                return new DatePickerDialog(this,onDateSetListener, mYear, mMonth, mDay);
            case DATE_DIALOY:
                return new DatePickerDialog(this,monDateSetListener, mYear, mMonth, mDay);
        }
        return null;
    }

    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            display();
        }
    };
    DatePickerDialog.OnDateSetListener monDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            finplay();
        }
    };

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(CalendarActivity.this);
        builder.setTitle("选择事件类型");
        final String[] xz = {"个人日历","公开日历","协同日历"};
        builder.setItems(xz, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                affair.setText(xz[which]);
                affair.setTextColor(getResources().getColor(R.color.black));
            }
        });
        builder.show();
    }

    final String[] xz = {"客户拜访","商务谈判","法律质询","客户拜访","商务谈判","法律质询","客户拜访","商务谈判","法律质询"};
    private void showWorkDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(CalendarActivity.this);
        builder.setTitle("选择工作类别");
        builder.setItems(xz, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                work.setText(xz[which]);
                work.setTextColor(getResources().getColor(R.color.black));
            }
        });
        builder.show();
    }

    /**
     * 设置日期 利用StringBuffer追加
     */
    public void display() {
        starttime.setText(new StringBuffer().append(mYear).append("-").append(mMonth + 1).append("-").
                append(mDay).append(" "));
        starttime.setTextColor(getResources().getColor(R.color.black));
    }
    public void finplay() {
        finishtime.setText(new StringBuffer().append(mYear).append("-").append(mMonth + 1).append("-").
                append(mDay).append(" "));
        finishtime.setTextColor(getResources().getColor(R.color.black));
    }

    /**
     * 此方法可以多次被不同的View对象调用。
     * 在调用该方法后，
     * 使用View对象的getMessuredHeight()获高度（单位px）
     * @param child 需要测量高度和宽度的View对象，
     */
    private void measureView(View child) {
        ViewGroup.LayoutParams params = child.getLayoutParams();
        if (params == null) {
            params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0,
                params.width);
        int lpHeight = params.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(lpHeight,
                    View.MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(0,
                    View.MeasureSpec.UNSPECIFIED);
        }
        child.measure(childWidthSpec, childHeightSpec);
    }
}
