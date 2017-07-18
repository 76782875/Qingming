package com.example.administrator.qingming.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.administrator.qingming.contacts.CustomerAddressActivity;
import com.example.administrator.qingming.R;

import static com.example.administrator.qingming.R.id.law_journal;

/**
 * 联系法官页面
 * Created by Administrator on 2017/4/20.
 */

public class JudicialAddressFragment extends Fragment {
    private RelativeLayout lawjournal;//律所通讯录
    private RelativeLayout customeraddress;//客户通讯录
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_judicial_address_book,null);
        lawjournal = (RelativeLayout) view.findViewById(law_journal);
        customeraddress = (RelativeLayout) view.findViewById(R.id.customer_address);

        lawjournal.setOnClickListener(onClickListener);
        customeraddress.setOnClickListener(onClickListener);
        return view;
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){
                case R.id.law_journal:
                    intent = new Intent(getContext(), LawJournalFragment.class);
                    startActivity(intent);
                    break;
                case R.id.customer_address:
                    intent = new Intent(getContext(), CustomerAddressActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}
