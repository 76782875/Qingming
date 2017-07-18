package com.example.administrator.qingming.contacts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.contacts.lawjournal.CharacterParser;
import com.example.administrator.qingming.contacts.lawjournal.PinyinComparator;
import com.example.administrator.qingming.contacts.lawjournal.SideBar;
import com.example.administrator.qingming.adapter.SortAdapter;
import com.example.administrator.qingming.model.ModelContact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/5/2.
 */

public class CustomerAddressActivity extends Activity {
    private TextView name;
    private SideBar sideBar;
    private TextView dialog;
    private ListView sortListView;
    private SortAdapter adapter;
    private EditText editText;
    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    private List<ModelContact.ResultBean> SourceDateList;

    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law_journal);

        init();
    }

    private void init(){
        name = (TextView) findViewById(R.id.name);
        name.setText("客户通讯录");
        //实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();

        sideBar = (SideBar) findViewById(R.id.sidrbar);
        dialog = (TextView) findViewById(R.id.dialog);
        sideBar.setTextView(dialog);

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
        sortListView = (ListView) findViewById(R.id.country_lvcountry);
        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //这里要利用adapter.getItem(position)来获取当前position所对应的对象
//                Toast.makeText(getApplication(), ((SortModel)adapter.getItem(position)).getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CustomerAddressActivity.this,ContactDetailsActivity.class);
                startActivity(intent);
            }
        });
//        SourceDateList = filledData(getResources().getStringArray(R.array.date));

        editText = (EditText) findViewById(R.id.clearedit);
        editText.setHint("请输入客户名/联系人/负责人");
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

        // 根据a-z进行排序源数据
        Collections.sort(SourceDateList, pinyinComparator);
        adapter = new SortAdapter(this, SourceDateList);
        sortListView.setAdapter(adapter);
    }

    /**
     * 为ListView填充数据
     * @param date
     * @return
     */
//    private List<SortModel> filledData(String[] date){
//        List<SortModel> mSortList = new ArrayList<SortModel>();
//        for(int i=0; i<date.length; i++){
//            SortModel sortModel = new SortModel();
//            sortModel.setName(date[i]);
//            //汉字转换成拼音
//            String pinyin = characterParser.getSelling(date[i]);
//            String sortString = pinyin.substring(0, 1).toUpperCase();
//            // 正则表达式，判断首字母是否是英文字母
//            if(sortString.matches("[A-Z]")){
//                sortModel.setSortLetters(sortString.toUpperCase());
//            }else{
//                sortModel.setSortLetters("#");
//            }
//            mSortList.add(sortModel);
//        }
//        return mSortList;
//    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     * @param filterStr
     */
    private void filterData(String filterStr){
        List<ModelContact.ResultBean> filterDateList = new ArrayList<ModelContact.ResultBean>();

        if(TextUtils.isEmpty(filterStr)){
            filterDateList = SourceDateList;
        }else{
            filterDateList.clear();
            for(ModelContact.ResultBean sortModel : SourceDateList){
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
}
