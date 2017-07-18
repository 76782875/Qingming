package com.example.administrator.qingming.contacts.lawjournal;

import com.example.administrator.qingming.model.ModelContact;

import java.util.Comparator;

/**
 * Created by Administrator on 2017/4/24.
 */

public class PinyinComparator implements Comparator<ModelContact.ResultBean> {
    public int compare(ModelContact.ResultBean o1, ModelContact.ResultBean o2) {
        if (o1.getSortLetters().equals("@")
                || o2.getSortLetters().equals("#")) {
            return -1;
        } else if (o1.getSortLetters().equals("#")
                || o2.getSortLetters().equals("@")) {
            return 1;
        } else {
            return o1.getSortLetters().compareTo(o2.getSortLetters());
        }
    }

}
