package com.example.administrator.qingming.qinminutils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Administrator on 2017/6/22 0022.
 */

public class ScrollListview extends ListView {
    public ScrollListview(Context context) {
        super(context);
    }

    public ScrollListview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollListview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 调用了makeMeasureSpec方法，这个方法是用来生成一个带有模式和大小信息的int值的，
     * 第一个参数Integer.MAX_VALUE >> 2，这个参数是传的一个大小值，为什么是这个值呢，
     * 我们现在已经知道了，我们要生成的控件，它的大小最大值是int的最低30位的最大值，
     * 我们先取Integer.MAX_VALUE来获取int值的最大值，然后左移2位就得到这个临界值最大值了
     *
     * public static final int UNSPECIFIED = 0 << MODE_SHIFT;  不确定模式是0左移30位，也就是int类型的最高两位是00
     * public static final int EXACTLY = 1 << MODE_SHIFT;    精 确模式是1左移30位，也就是int类型的最高两位是01
     * public static final int AT_MOST = 2 << MODE_SHIFT;    最大模式是是2左移30位，也就是int类型的最高两位是10
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
