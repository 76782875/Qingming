package com.example.administrator.qingming.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.qingming.ExamineAndApproveActivity;
import com.example.administrator.qingming.R;
import com.example.administrator.qingming.activity.ChangeActivity;
import com.example.administrator.qingming.activity.FilesActivity;
import com.example.administrator.qingming.activity.IntoPressActivity;
import com.example.administrator.qingming.activity.MapActivity;
import com.example.administrator.qingming.activity.MyCaseActivity;
import com.example.administrator.qingming.activity.NewsActivity;
import com.example.administrator.qingming.activity.PressActivity;
import com.example.administrator.qingming.activity.ShoufeiActivity;
import com.example.administrator.qingming.adapter.PressAdater;
import com.example.administrator.qingming.api.BaseApi;
import com.example.administrator.qingming.api.MainApi;
import com.example.administrator.qingming.interfaces.GetResultCallBack;
import com.example.administrator.qingming.model.Constants;
import com.example.administrator.qingming.model.ModelPress;
import com.example.administrator.qingming.model.PersonalDataModel;
import com.example.administrator.qingming.qinminutils.GsonUtil;
import com.example.administrator.qingming.qinminutils.PullToRefreshView;
import com.example.administrator.qingming.qinminutils.ScrollListview;
import com.example.administrator.qingming.work.AddCaseActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by Administrator on 2017/6/22 0022.
 */

public class HomePageFragment extends Fragment implements PullToRefreshView.OnHeaderRefreshListener,PullToRefreshView.OnFooterRefreshListener{
    private ScrollListview listView;
    private View view;
    private List<ModelPress.ResultBean> list;
    private List<PersonalDataModel.ResultBean> list1;
    private PullToRefreshView pullToRefreshView;
    private TextView company,username,officename,mycase,addcase,file,qiandao,ls_case,zixun,biangen,sfsp;
    private TextView cwsa,caja,zrsa,zrja;
    private ImageView news;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_homepage,null);


        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("qinmin", Context.MODE_PRIVATE);
        id = sharedPreferences.getString("id","");
        gsid =sharedPreferences.getString("cid","");

        initView();
        getHttp();
        getString();

        return view;
    }

    private void initView() {
        list = new ArrayList<>();
        list1 = new ArrayList<>();
        news = (ImageView) view.findViewById(R.id.news);
        listView = (ScrollListview) view.findViewById(R.id.listview);
        pullToRefreshView = (PullToRefreshView) view.findViewById(R.id.pulltore);
        company = (TextView) view.findViewById(R.id.company);
        username = (TextView) view.findViewById(R.id.username);
        officename = (TextView) view.findViewById(R.id.officename);
        mycase = (TextView) view.findViewById(R.id.mycase);
        addcase = (TextView) view.findViewById(R.id.addcase);
        file = (TextView) view.findViewById(R.id.file);
        qiandao = (TextView) view.findViewById(R.id.qiandao);
        ls_case= (TextView) view.findViewById(R.id.ls_case);
        zixun= (TextView) view.findViewById(R.id.zixun);
        biangen= (TextView) view.findViewById(R.id.biangen);
        sfsp = (TextView) view.findViewById(R.id.sfsp);
        cwsa = (TextView) view.findViewById(R.id.cwsa);
        caja = (TextView) view.findViewById(R.id.cwja);
        zrsa = (TextView) view.findViewById(R.id.zrsa);
        zrja = (TextView) view.findViewById(R.id.zrja);

        pullToRefreshView.setOnHeaderRefreshListener(this);
        pullToRefreshView.setOnFooterRefreshListener(this);
        pullToRefreshView.setLastUpdated(new Date().toLocaleString());

        mycase.setOnClickListener(onClickListener);
        addcase.setOnClickListener(onClickListener);
        file.setOnClickListener(onClickListener);
        qiandao.setOnClickListener(onClickListener);
        ls_case.setOnClickListener(onClickListener);
        zixun.setOnClickListener(onClickListener);
        biangen.setOnClickListener(onClickListener);
        sfsp.setOnClickListener(onClickListener);
        cwsa.setOnClickListener(onClickListener);
        caja.setOnClickListener(onClickListener);
        zrsa.setOnClickListener(onClickListener);
        zrja.setOnClickListener(onClickListener);
        news.setOnClickListener(onClickListener);

        pressAdater = new PressAdater(list,getActivity());
        listView.setAdapter(pressAdater);
//                    setListViewHeightBasedOnChildren(listView);//计算listview的高度
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cid = list.get(position).getId();
                Log.i("cid","+++++"+cid);
                Intent intent = new Intent(getActivity(),IntoPressActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("cid",cid);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        Intent intent;
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.news://跳转到消息页面
                    intent = new Intent(getActivity(),NewsActivity.class);
                    startActivity(intent);
                    break;
                case R.id.mycase://跳转到我的案件页面
                    intent = new Intent(getActivity(),MyCaseActivity.class);
                    intent.putExtra("cc","1");
                    startActivity(intent);
                    break;
                case R.id.addcase://跳转到添加案件页面
                    intent = new Intent(getActivity(),AddCaseActivity.class);
                    startActivity(intent);
                    break;
                case R.id.file://跳转到文件页面
                    intent = new Intent(getActivity(),FilesActivity.class);
                    startActivity(intent);
                    break;
                case R.id.qiandao://跳转到签到页面
                    intent = new Intent(getActivity(),MapActivity.class);
                    startActivity(intent);
                    break;
                case R.id.ls_case://跳转到律所案件页面
                    intent = new Intent(getActivity(),MyCaseActivity.class);
                    intent.putExtra("cc","2");
                    startActivity(intent);
                    break;
                case R.id.zixun://跳转到新闻咨询页面
                    intent = new Intent(getActivity(),PressActivity.class);
                    startActivity(intent);
                    break;
                case R.id.biangen://跳转到变更律师页面
                    intent = new Intent(getActivity(),ChangeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.sfsp://跳转到收费审批页面
                    intent = new Intent(getActivity(),ShoufeiActivity.class);
                    startActivity(intent);
                    break;
                case R.id.cwsa://跳转到财务收案页面
                    intent = new Intent(getActivity(),ExamineAndApproveActivity.class);
                    intent.putExtra("case_state",3);
                    startActivity(intent);
                    break;
                case R.id.cwja://跳转到财务结案页面
                    intent = new Intent(getActivity(),ExamineAndApproveActivity.class);
                    intent.putExtra("case_state",6);
                    startActivity(intent);
                    break;
                case R.id.zrsa://跳转到主任收案页面
                    intent = new Intent(getActivity(),ExamineAndApproveActivity.class);
                    intent.putExtra("case_state",2);
                    startActivity(intent);
                    break;
                case R.id.zrja://跳转到主任结案页面
                    intent = new Intent(getActivity(),ExamineAndApproveActivity.class);
                    intent.putExtra("case_state",5);
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

                    username.setText(name1);
                    officename.setText(name);
                    company.setText(name);

                }else BaseApi.showErrMsg(getActivity(),result);
            }
        });
    }


    private String gsid;
    private int product_brand =14 ;
    private String cid;
    PressAdater pressAdater;
    public void getString(){
        MainApi.getInstance(getActivity()).getNewsApi(gsid,product_brand,new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                pullToRefreshView.onHeaderRefreshComplete();
                pullToRefreshView.onFooterRefreshComplete();
                if(type == Constants.TYPE_SUCCESS){
                    List<ModelPress.ResultBean> resultBeanList = GsonUtil.fromJsonList(new Gson(),
                            result,ModelPress.ResultBean.class);
                    list.clear();
                    list.addAll(resultBeanList);
                    pressAdater.notifyDataSetChanged();
                }else BaseApi.showErrMsg(getActivity(),result);
            }
        });
    }


    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
        getString();
        getHttp();
    }

    @Override
    public void onFooterRefresh(PullToRefreshView view) {
        getString();
    }


//    public void setListViewHeightBasedOnChildren(ListView listView) {
//        // 获取ListView对应的Adapter
//        ListAdapter listAdapter = listView.getAdapter();
//        if (listAdapter == null) {
//            return;
//        }
//
//        int totalHeight = 0;
//        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
//            // listAdapter.getCount()返回数据项的数目
//            View listItem = listAdapter.getView(i, null, listView);
//            // 计算子项View 的宽高
//            listItem.measure(0, 0);
//            // 统计所有子项的总高度
//            totalHeight += listItem.getMeasuredHeight();
//        }
//
//        ViewGroup.LayoutParams params = listView.getLayoutParams();
//        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
//        // listView.getDividerHeight()获取子项间分隔符占用的高度
//        // params.height最后得到整个ListView完整显示需要的高度
//        listView.setLayoutParams(params);
//    }

}