package com.example.administrator.qingming.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.api.MainApi;
import com.example.administrator.qingming.dialog.LoadingDialog;
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
    private LoadingDialog loadingDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intopress);

        initView();
        Bundle bundle = getIntent().getExtras();
        int index = bundle.getInt("index");
        if(index == 1){
            id = bundle.getString("cid");
            getHttp();
        }else {
            String cons_content = bundle.getString("cons_content");
            //加载数据
            webView.loadData(cons_content, "text/html; charset=UTF-8", null);
        }
    }

    private void initView() {
        loadingDialog = new LoadingDialog(this);
        webView = (WebView) findViewById(webview);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(this);
        list =new ArrayList<>();

        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);// 设置无边框
        //启用支持javascript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        /**
         * 用WebView显示图片，可使用这个参数 设置网页布局类型：
         * 1、LayoutAlgorithm.NARROW_COLUMNS ：适应内容大小
         * 2、LayoutAlgorithm.SINGLE_COLUMN:适应屏幕，内容将自动缩放
         */
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // 设置默认字体大小
       // webSettings.setDefaultFontSize(22);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        if (dm.densityDpi > 240 ) {
            webSettings.setDefaultFontSize(40); //可以取1-72之间的任意值，默认16
        }
        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(false); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        // 让JavaScript可以自动打开windows
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 设置缓存
        webSettings.setAppCacheEnabled(true);
        // 屏幕自适应网页,如果没有这个，在低分辨率的手机上显示可能会异常
        webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
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
        loadingDialog.show();
        loadingDialog.setLoadingContent("加载中...");
        MainApi.getInstance(this).getIntoNewsApi(id, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                swipeRefreshLayout.setRefreshing(false);
                loadingDialog.dismiss();
                if(type == Constants.TYPE_SUCCESS){
                    List<ModelIntoPress.ResultBean> resultBean = GsonUtil.fromJsonList(new Gson(),
                            result,ModelIntoPress.ResultBean.class);
                    list.addAll(resultBean);
                    product_intr = resultBean.get(0).getProduct_intr();
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
