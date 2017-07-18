package com.example.administrator.qingming.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.api.MainApi;
import com.example.administrator.qingming.interfaces.GetResultCallBack;
import com.example.administrator.qingming.model.Constants;
import com.example.administrator.qingming.model.ModelIntoPress;
import com.example.administrator.qingming.qinminutils.GsonUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.qingming.R.id.webview;

/**
 * Created by Administrator on 2017/6/5 0005.
 */

public class IntoPressActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener{
    private WebView webView;
    List<ModelIntoPress.ResultBean> list;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intopress);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("cid");
        Log.i("id===>",""+id);

        initView();
        getHttp();
    }

    private void initView() {
        webView = (WebView) findViewById(webview);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(this);
        list =new ArrayList<ModelIntoPress.ResultBean>();

        //启用支持javascript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // 设置可以被显示的屏幕控制
        webSettings.setDisplayZoomControls(true);
        // 设置默认字体大小
        webSettings.setDefaultFontSize(14);
        // 支持缩放(适配到当前屏幕)
        webSettings.setSupportZoom(true);
        // 将图片调整到合适的大小
        webSettings.setUseWideViewPort(true);
        // 让JavaScript可以自动打开windows
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 设置缓存
        webSettings.setAppCacheEnabled(true);
        // 屏幕自适应网页,如果没有这个，在低分辨率的手机上显示可能会异常
        webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        //设置自适应手机屏幕，两者合用
        webSettings.setUseWideViewPort(true);//将图片调整到适合webView的大小
        webSettings.setLoadWithOverviewMode(true);//缩放至屏幕大小
        //处理各种通知请求和事件，如果不使用该句代码，将使用内置浏览器访问网页
        webView.setWebViewClient(new WebViewClient());

    }
    //按返回键时，不退出程序而是返回上一浏览页面：
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    String id;
    String product_intr;
    private void getHttp(){
        MainApi.getInstance(this).getIntoNewsApi(id, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                swipeRefreshLayout.setRefreshing(false);
                if(type == Constants.TYPE_SUCCESS){
                    List<ModelIntoPress.ResultBean> resultBean = GsonUtil.fromJsonList(new Gson(),
                            result,ModelIntoPress.ResultBean.class);
                    list.addAll(resultBean);
                    product_intr = resultBean.get(0).getProduct_intr();
                    Log.i("product_intr","====>"+product_intr);
                    //加载数据
                    webView.loadData(product_intr, "text/html; charset=UTF-8", null);
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        getHttp();
    }
}
