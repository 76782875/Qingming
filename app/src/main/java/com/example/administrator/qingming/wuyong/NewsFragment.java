package com.example.administrator.qingming.wuyong;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.qingming.news.casedetails.CaseDetailsActivity;
import com.example.administrator.qingming.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/20.
 */

public class NewsFragment extends Fragment {
    private ListView listmessage;
    private List<News> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news,null);
        listmessage = (ListView) view.findViewById(R.id.list_message);

        list = getData();
//        listmessage.setAdapter(new NewsAdapater(getActivity(),list));
        listmessage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), CaseDetailsActivity.class);
                startActivity(intent);

            }
        });
        return view;
    }

    private List<News> getData(){
        list = new ArrayList<News>();
        for(int i= 0;i<10;i++){
            News news = new News();
            news.setRemind("顾问到期提醒");
            news.setNews_time("5-22 00:00");
            news.setClient("张总");
            news.setCase_no("2017（顾）第0001号");
            news.setDue_time("2017-2-1");
            list.add(news);
        }
        return list;
    }
}
