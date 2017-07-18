package com.example.administrator.qingming.work;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.example.administrator.qingming.R;

/**
 * Created by Administrator on 2017/5/3.
 */

public class NetImageLocadHolder implements Holder<String> {
    private ImageView image_lv;

    @Override
    public View createView(Context context) {
        image_lv=new ImageView(context);
        image_lv.setScaleType(ImageView.ScaleType.FIT_XY);
        return image_lv;
    }

//    加载网络图片
    @Override
    public void UpdateUI(Context context, int position, String data) {
        //                             预加载
        Glide.with(context).load(data).placeholder(R.mipmap.ic_launcher).into(image_lv);
    }
}
