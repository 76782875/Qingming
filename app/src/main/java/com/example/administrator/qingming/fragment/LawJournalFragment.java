package com.example.administrator.qingming.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.api.BaseApi;
import com.example.administrator.qingming.api.MainApi;
import com.example.administrator.qingming.contacts.ContactDetailsActivity;
import com.example.administrator.qingming.contacts.lawjournal.CharacterParser;
import com.example.administrator.qingming.contacts.lawjournal.PinyinComparator;
import com.example.administrator.qingming.contacts.lawjournal.SideBar;
import com.example.administrator.qingming.adapter.SortAdapter;
import com.example.administrator.qingming.interfaces.GetResultCallBack;
import com.example.administrator.qingming.model.Constants;
import com.example.administrator.qingming.model.ModelContact;
import com.example.administrator.qingming.qinminutils.GsonUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/4/20.
 */

public class LawJournalFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private SideBar sideBar;
    private TextView dialog;
    private ListView sortListView;
    private SortAdapter adapter;
    private EditText editText;
    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
//    private List<SortModel> SourceDateList;
    private List<ModelContact.ResultBean> sourceDateList;
    SwipeRefreshLayout swipeRefresh;
    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.activity_law_journal,null);
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("qinmin", Context.MODE_PRIVATE);
        gsid = sharedPreferences.getString("cid","");
        init();
        ininHttp();
        return view;
    }


    private void init(){
        sourceDateList = new ArrayList<>();
        //实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
//
        sideBar = (SideBar) view.findViewById(R.id.sidrbar);
        dialog = (TextView) view.findViewById(R.id.dialog);
        swipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        sideBar.setTextView(dialog);
        swipeRefresh.setOnRefreshListener(this);//刷新接口

        //设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if(position != -1){
                    sortListView.setSelection(position);
                }
            }
        });
        sortListView = (ListView) view.findViewById(R.id.country_lvcountry);

        editText = (EditText) view.findViewById(R.id.clearedit);
        editText.setHintTextColor(getResources().getColor(R.color.cyan));
        //根据输入框输入值的改变来过滤搜索
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                filterData(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private String gsid;
    private String cid;
    private void ininHttp(){
        MainApi.getInstance(getActivity()).getTxlApi(gsid, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                swipeRefresh.setRefreshing(false);//刷新完成
                if(type == Constants.TYPE_SUCCESS){
                    final List<ModelContact.ResultBean> datelist = GsonUtil.fromJsonList(new Gson(), result, ModelContact.ResultBean.class);
                    //添加数据
                    sourceDateList.clear();
                    sourceDateList.addAll(filledData(datelist));
                    // 根据a-z进行排序源数据
                    Collections.sort(sourceDateList, pinyinComparator);
                    adapter = new SortAdapter(getActivity(), sourceDateList);
                    sortListView.setAdapter(adapter);

                    sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            cid = datelist.get(position).getId();
                            Log.e("---------->",""+cid);
                            Log.e("+++++++++++",""+sourceDateList.get(0).getId());
                            Log.e("==========>",""+datelist.get(0).getId());
                            Intent intent = new Intent(getActivity(),ContactDetailsActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("cid",""+cid);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                }else BaseApi.showErrMsg(getActivity(),result);
            }
        });
    }



    /**
     * 为ListView填充数据
     * @param datelist
     * @return
     */
    private List<ModelContact.ResultBean> filledData(List<ModelContact.ResultBean> datelist){
        List<ModelContact.ResultBean> mSortList = new ArrayList<>();
        for(int i=0; i<datelist.size(); i++){
            ModelContact.ResultBean sortModel = new ModelContact.ResultBean();
            sortModel.setName(datelist.get(i).getName());
            //汉字转换成拼音
            String pinyin = characterParser.getSelling(datelist.get(i).getName());
            String sortString = pinyin.substring(0, 1).toUpperCase();
            // 正则表达式，判断首字母是否是英文字母
            if(sortString.matches("[A-Z]")){
                sortModel.setSortLetters(sortString.toUpperCase());
            }else{
                sortModel.setSortLetters("#");
            }
            mSortList.add(sortModel);
        }
        return mSortList;
    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     * @param filterStr
     */
    private void filterData(String filterStr){
        List<ModelContact.ResultBean> filterDateList = new ArrayList<ModelContact.ResultBean>();

        if(TextUtils.isEmpty(filterStr)){
            filterDateList = sourceDateList;
        }else{
            filterDateList.clear();
            for(ModelContact.ResultBean sortModel : sourceDateList){
                String name = sortModel.getName();
                if(name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())){
                    filterDateList.add(sortModel);
                }
            }
        }

        // 根据a-z进行排序
        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateListView(filterDateList);
    }

    @Override
    public void onRefresh() {
        ininHttp();
    }
}
