package com.example.administrator.qingming.qinminutils;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/7/15.
 */
public class ToastUtil {

    private static Toast toast;
    private static View view;

    private static void getToast(Context context) {
        if (toast == null) {
            toast = new Toast(context);
        }
        if (view == null) {
            view = Toast.makeText(context, "", Toast.LENGTH_SHORT).getView();
        }
        toast.setView(view);
    }

    /**
     * Toast显示
     *
     * @param context
     * @param text
     */
    public static void makeToast(Context context, String text) {
        try {
            getToast(context);
            toast.setText(text);
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cancelToast() {
        if (toast != null)
            toast.cancel();
    }

}
