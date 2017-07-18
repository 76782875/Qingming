package com.example.administrator.qingming.work;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.news.casedetails.CaseDetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/12 0012.
 */

public class CaseManageActivity extends Activity {
    private ListView listView;
    private List<CaseModel> list;
    private ImageView backbtn,searchbtn,drawbackbtn;
    private DrawerLayout drawerLayout;
    int mDrawerWidth;//抽屉全部拉出来时的宽度
    float scrollWidth;//抽屉被拉出部分的宽度
    private LinearLayout linearLayout;
    private FrameLayout frameLayout;
    private TextView party,casetype,caseyear,casechoose,caseclear;
    private EditText caseantistop;
    private Button submitbtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_case_manage);

        initView();

        //测量抽屉的宽度和高度
        measureView(linearLayout);
        mDrawerWidth=linearLayout.getMeasuredWidth();

    }
    private void initView(){
        listView = (ListView) findViewById(R.id.case_list);
        backbtn = (ImageView) findViewById(R.id.back_btn);
        searchbtn = (ImageView) findViewById(R.id.search_btn);
        drawbackbtn = (ImageView) findViewById(R.id.draw_back_btn);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        linearLayout = (LinearLayout) findViewById(R.id.case_linear);
        frameLayout = (FrameLayout) findViewById(R.id.framelayout);
        party = (TextView) findViewById(R.id.party);
        casetype = (TextView) findViewById(R.id.case_type);
        caseyear = (TextView) findViewById(R.id.case_year);
        casechoose = (TextView) findViewById(R.id.case_choose);
        caseclear = (TextView) findViewById(R.id.case_clear);
        caseantistop = (EditText) findViewById(R.id.case_antistop);
        submitbtn = (Button) findViewById(R.id.submit_btn);

        backbtn.setOnClickListener(onClickListener);
        searchbtn.setOnClickListener(onClickListener);
        drawbackbtn.setOnClickListener(onClickListener);
        party.setOnClickListener(onClickListener);
        casetype.setOnClickListener(onClickListener);
        caseyear.setOnClickListener(onClickListener);
        casechoose.setOnClickListener(onClickListener);
        caseclear.setOnClickListener(onClickListener);
        submitbtn.setOnClickListener(onClickListener);
        caseantistop.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        list = getDate();
        CaseAdapter caseAdapter = new CaseAdapter(list,CaseManageActivity.this);
        listView.setAdapter(caseAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CaseManageActivity.this, CaseDetailsActivity.class);
                startActivity(intent);
            }
        });

        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
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
    }
    CaseModel caseModel;
    private List<CaseModel> getDate(){
        list = new ArrayList<>();
        for (int i = 0;i<10;i++){
            caseModel = new CaseModel();
            caseModel.setName("确认合同有效纠纷");
            caseModel.setNumb("2017（民）第0070 号");
            caseModel.setConsignor("王二麻子");
            caseModel.setParty("张三");
            caseModel.setAdversary("李四");
            caseModel.setAttorney("某主任");
            list.add(caseModel);
        }
        return list;
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back_btn:
                    finish();
                    break;
                case R.id.search_btn:
                    //打开抽屉
                    drawerLayout.openDrawer(Gravity.RIGHT);
                    break;
                case R.id.draw_back_btn:
                    //关闭抽屉
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                    break;
                case R.id.party:
                    final String[] str = {"委托人/当事人","对方当事人搜索","案号搜索","案由搜索","承办律师搜索","受理法院搜索"};
                    title = "检索方式";
                    showDialog();
                    builder.setItems(str, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            party.setText(str[which]);
                            party.setTextColor(getResources().getColor(R.color.black));
                        }
                    }).show();
                    break;
                case R.id.case_type:
                    final String[] type = {"民事案件","刑事案件","行政案件","非诉讼案件","法律顾问","法律援助",
                            "执行案件","中保案件","仲裁案件","破产案件","咨询代写文书"};
                    title = "案件类型";
                    showDialog();
                    builder.setItems(type, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            casetype.setText(type[which]);
                            casetype.setTextColor(getResources().getColor(R.color.black));
                        }
                    }).show();
                    break;
                case R.id.case_year:
                    final String[] year = {"2017年","2016年","案号搜索","案由搜索","承办律师搜索","受理法院搜索"};
                    title = "选择年份";
                    showDialog();
                    builder.setItems(year, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            caseyear.setText(year[which]);
                            caseyear.setTextColor(getResources().getColor(R.color.black));
                        }
                    }).show();
                    break;
                case R.id.case_choose:
                    final String[] choose = {"未审批","已审批"};
                    title = "收案状态";
                    showDialog();
                    builder.setItems(choose, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            casechoose.setText(choose[which]);
                            casechoose.setTextColor(getResources().getColor(R.color.black));
                        }
                    }).show();
                    break;
                case R.id.case_clear:
                    caseantistop.setText("");
                    casechoose.setText("请选择");
                    casechoose.setTextColor(getResources().getColor(R.color.cyan));
                    casetype.setText("请选择案件类型");
                    casetype.setTextColor(getResources().getColor(R.color.cyan));
                    caseyear.setText("请选择年份");
                    caseyear.setTextColor(getResources().getColor(R.color.cyan));
                    party.setText("委托人/当事人");
                    party.setTextColor(getResources().getColor(R.color.black));
                    break;
                case R.id.submit_btn:
                    break;
            }
        }
    };

    AlertDialog.Builder builder;
    String title;
    private void showDialog(){
        builder = new AlertDialog.Builder(CaseManageActivity.this,AlertDialog.THEME_HOLO_LIGHT);
        builder.setTitle(title);
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
