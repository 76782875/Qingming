package com.example.administrator.qingming.work;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.SingleLineTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.qingming.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/15 0015.
 */

public class WorkLogActivity extends Activity {
    private ImageView searchbtn,workbackbtn,backbtn;
    private TextView worktimestart,worktimefinish,createtime,logtime;
    private DrawerLayout drawerLayout;
    private LinearLayout linearLayout;
    private FrameLayout frameLayout;
    private int mDrawerWidth;//抽屉全部拉出来时的宽度
    private EditText workeditnumb,workeditname;
    private String numb,name;
    private RecyclerView myrecyclerView;
    private List<WorkLogModel> list;
    private Button sub;
    int mYear,mMonth,mDay;
    private static final int DATE_DIALOG = 0;
    private static final int DATE_DIALOY = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_log);

        //获取日期
        final Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);

        initView();
        list = getDate();
        WorkLogAdapter workLogAdapter = new WorkLogAdapter(this,list);
        myrecyclerView.setAdapter(workLogAdapter);
//        date = getData();
        TitleItemDecoration titleItem = new TitleItemDecoration(this,list);
        myrecyclerView.addItemDecoration(titleItem);
        //线性布局,横向或者纵向滑动列表
        myrecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        workLogAdapter.setOnItemClickListener(new WorkLogAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Log.i("+++++++++++","你点击了第"+i+"项");
            }
        });
        measureView(linearLayout);
        mDrawerWidth = linearLayout.getMeasuredWidth();
    }

    private void initView() {
        searchbtn = (ImageView) findViewById(R.id.search_btn);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        linearLayout = (LinearLayout) findViewById(R.id.work_linear);
        frameLayout = (FrameLayout) findViewById(R.id.framelayout);
        backbtn = (ImageView) findViewById(R.id.back_btn);
        workbackbtn = (ImageView) findViewById(R.id.work_back_btn);
        workeditnumb = (EditText) findViewById(R.id.work_edit_numb);
        workeditname = (EditText) findViewById(R.id.work_edit_name);
        worktimestart = (TextView) findViewById(R.id.work_time_start);
        worktimefinish = (TextView) findViewById(R.id.work_time_finish);
        myrecyclerView = (RecyclerView) findViewById(R.id.recycle);
        logtime = (TextView) findViewById(R.id.log_time);
        createtime = (TextView) findViewById(R.id.create_time);
        sub = (Button) findViewById(R.id.submit_btn);

        searchbtn.setOnClickListener(onClickListener);
        backbtn.setOnClickListener(onClickListener);
        workbackbtn.setOnClickListener(onClickListener);
        logtime.setOnClickListener(onClickListener);
        createtime.setOnClickListener(onClickListener);
        worktimestart.setOnClickListener(onClickListener);
        worktimefinish.setOnClickListener(onClickListener);
        sub.setOnClickListener(onClickListener);
        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View view, float v) {
                //因为arg1的范围是0.0-1.0，是一个相对整个抽屉宽度的比例
                //所以要准换成
                float scrollWidth = v * mDrawerWidth;
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
        workeditnumb.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                numb = s.toString();
            }
        });
        workeditname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                name = s.toString();
            }
        });
        //时间选择器
        worktimestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG);
            }
        });
        worktimefinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOY);
            }
        });

    }

    public List<WorkLogModel> getDate() {
        list = new ArrayList<WorkLogModel>();
//        for(int i= 0;i<10;i++){
//            WorkLogModel workLogModel = new WorkLogModel();
//            workLogModel.setTime("2017-05-12 5:21:23");
//            workLogModel.setType("业务工作日志");
//            workLogModel.setLb("客户拜访");
//            workLogModel.setNumb("2017（顾）第0001号");
//            workLogModel.setWorktime("0.0");
//            workLogModel.setContent("发奇偶高级哦");
//            workLogModel.setGk("未公开");
//            workLogModel.setTag("2017-12-17");
//            list.add(workLogModel);
//        }
        WorkLogModel workLogModel = new WorkLogModel();
        workLogModel.setTime("2017-05-12 5:21:23");
        workLogModel.setType("业务工作日志");
        workLogModel.setLb("客户拜访");
        workLogModel.setNumb("2017（顾）第0001号");
        workLogModel.setWorktime("0.0");
        workLogModel.setContent("发奇偶高级哦");
        workLogModel.setGk("未公开");
        workLogModel.setTag("2017-12-16");
        list.add(workLogModel);

        WorkLogModel workLogModel1 = new WorkLogModel();
        workLogModel1.setTime("2017-05-12 5:21:23");
        workLogModel1.setType("业务工作日志");
        workLogModel1.setLb("客户拜访");
        workLogModel1.setNumb("2017（顾）第0001号");
        workLogModel1.setWorktime("0.0");
        workLogModel1.setContent("发奇偶高级哦");
        workLogModel1.setGk("未公开");
        workLogModel1.setTag("2017-12-17");
        list.add(workLogModel1);

        WorkLogModel workLogModel2 = new WorkLogModel();
        workLogModel2.setTime("2017-05-12 5:21:23");
        workLogModel2.setType("业务工作日志");
        workLogModel2.setLb("客户拜访");
        workLogModel2.setNumb("2017（顾）第0001号");
        workLogModel2.setWorktime("0.0");
        workLogModel2.setContent("发奇偶高级哦");
        workLogModel2.setGk("未公开");
        workLogModel2.setTag("2017-12-17");
        list.add(workLogModel2);

        WorkLogModel workLogModel3 = new WorkLogModel();
        workLogModel3.setTime("2017-05-12 5:21:23");
        workLogModel3.setType("业务工作日志");
        workLogModel3.setLb("客户拜访");
        workLogModel3.setNumb("2017（顾）第0001号");
        workLogModel3.setWorktime("0.0");
        workLogModel3.setContent("发奇偶高级哦");
        workLogModel3.setGk("未公开");
        workLogModel3.setTag("2017-12-18");
        list.add(workLogModel3);


        return list;
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){
                case R.id.search_btn:
                    drawerLayout.openDrawer(Gravity.RIGHT);
                    break;
                case R.id.back_btn:
                    finish();
                    break;
                case R.id.work_back_btn:
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                    break;
                case R.id.create_time:
                    createtime.setBackgroundResource(R.drawable.left_choose_blue);
                    createtime.setTextColor(getResources().getColor(R.color.white));
                    logtime.setBackgroundResource(R.drawable.right_choose);
                    logtime.setTextColor(getResources().getColor(R.color.blue));
                    break;
                case R.id.log_time:
                    logtime.setBackgroundResource(R.drawable.right_choose_blue);
                    logtime.setTextColor(getResources().getColor(R.color.white));
                    createtime.setBackgroundResource(R.drawable.left_choose);
                    createtime.setTextColor(getResources().getColor(R.color.blue));
                    break;
                case R.id.submit_btn:
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                    Log.i("submit",""+name+numb+worktimestart.getText().toString()+worktimefinish.getText().toString());
                    break;
            }
        }
    };

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG:
                return new DatePickerDialog(this,onDateSetListener, mYear, mMonth, mDay);
            case DATE_DIALOY:
                return new DatePickerDialog(this,onDateSetListenert, mYear, mMonth, mDay);
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

    DatePickerDialog.OnDateSetListener onDateSetListenert = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            displayt();
        }
    };

    /**
     * 设置日期 利用StringBuffer追加
     */
    public void display() {
        worktimestart.setText(new StringBuffer().append(mYear).append("-").append(mMonth + 1).append("-").
                append(mDay).append(" "));
        worktimestart.setTextColor(getResources().getColor(R.color.black));
    }

    public void displayt() {
        worktimefinish.setText(new StringBuffer().append(mYear).append("-").append(mMonth + 1).append("-").
                append(mDay).append(" "));
        worktimefinish.setTextColor(getResources().getColor(R.color.black));
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
