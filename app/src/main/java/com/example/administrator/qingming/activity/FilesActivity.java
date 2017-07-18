package com.example.administrator.qingming.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Path;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.adapter.FileBaseAdapater;
import com.example.administrator.qingming.api.BaseApi;
import com.example.administrator.qingming.api.MainApi;
import com.example.administrator.qingming.dialog.LoadingDialog;
import com.example.administrator.qingming.interfaces.GetResultCallBack;
import com.example.administrator.qingming.model.Constants;
import com.example.administrator.qingming.model.ModelChange;
import com.example.administrator.qingming.model.ModelFile;
import com.example.administrator.qingming.qinminutils.FileUtils;
import com.example.administrator.qingming.qinminutils.GsonUtil;
import com.example.administrator.qingming.url.BaseUrl;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * Created by Administrator on 2017/5/23 0023.
 */

public class FilesActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener,FileBaseAdapater.Callback{
    private static final int FILE_SELECT_CODE = 1001;
    private ImageView backbtn;
    private TextView examine2,examine3;
    private View examine_line1,examine_line2;
    private ListView listView;
    private List<ModelFile.ResultBean> list1;
    private List<ModelFile.ResultBean> list2;
    private SwipeRefreshLayout swipe;
    int tag ;
    int mDrawerWidth;//抽屉全部拉出来时的宽度
    float scrollWidth;//抽屉被拉出部分的宽度
    private DrawerLayout drawerlayout;
    private LinearLayout menucalendar;
    private FrameLayout frameLayout;
    private ImageView addbtn;
    private LoadingDialog loadingDialog;
    String path;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        SharedPreferences sharedPreferences = getSharedPreferences("qinmin", Context.MODE_PRIVATE);
        createid = sharedPreferences.getString("id","");
        createname = sharedPreferences.getString("name","");
        gsid = sharedPreferences.getString("cid","");

        //获取日期
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy/MM/dd    hh:mm:ss");
        createtime = sDateFormat.format(new java.util.Date());
        Calendar calendar = Calendar.getInstance();
        int y =calendar.get(Calendar.YEAR);
        int M = calendar.get(Calendar.MONTH)+1;
        int d = calendar.get(Calendar.DATE);
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        int m = calendar.get(Calendar.MINUTE);
        int s = calendar.get(Calendar.SECOND);
        int mi = calendar.get(Calendar.MILLISECOND);

        newwjm = ""+y+M+d+h+m+s+mi;

        initView();
        getHttp();

        //测量抽屉的宽度和高度
        measureView(menucalendar);
        mDrawerWidth=menucalendar.getMeasuredWidth();
    }

    private void initView() {
        loadingDialog = new LoadingDialog(this);
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        backbtn = (ImageView) findViewById(R.id.back_btn);
        examine2 = (TextView) findViewById(R.id.examine2);
        examine3 = (TextView) findViewById(R.id.examine3);
        examine_line1 = findViewById(R.id.examine_line1);
        examine_line2 = findViewById(R.id.line2);
        listView = (ListView) findViewById(R.id.listview);
        drawerlayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        menucalendar = (LinearLayout) findViewById(R.id.menu_calendar);
        frameLayout = (FrameLayout) findViewById(R.id.framelayout);
        addbtn = (ImageView) findViewById(R.id.add_btn);

        backbtn.setOnClickListener(onClickListener);
        examine2.setOnClickListener(onClickListener);
        examine3.setOnClickListener(onClickListener);
        addbtn.setOnClickListener(onClickListener);
        fileBaseAdapater = new FileBaseAdapater(list1,list2,FilesActivity.this,this);
        listView.setAdapter(fileBaseAdapater);

        swipe.setOnRefreshListener(this);
        drawerlayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View view, float v) {
                //因为arg1的范围是0.0-1.0，是一个相对整个抽屉宽度的比例
                //所以要准换成
                scrollWidth=v*mDrawerWidth;
                //setScroll中的参数，正数表示向左移动，负数向右
                frameLayout.setScrollX((int)(1*scrollWidth));
            }

            @Override
            public void onDrawerOpened(View view) {

            }

            @Override
            public void onDrawerClosed(View view) {

            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back_btn:
                    finish();
                    break;
                case R.id.examine2:
                    fileBaseAdapater.setadapterTag(1);
                    fileBaseAdapater.notifyDataSetChanged();
                    examine_line1.setVisibility(View.VISIBLE);
                    examine_line2.setVisibility(View.INVISIBLE);
                    examine2.setTextColor(getResources().getColor(R.color.black));
                    examine3.setTextColor(getResources().getColor(R.color.blue));
                    break;
                case R.id.examine3:
                    gsid = "-1";
                    fileBaseAdapater.setadapterTag(2);
                    fileBaseAdapater.notifyDataSetChanged();
                    examine_line2.setVisibility(View.VISIBLE);
                    examine_line1.setVisibility(View.INVISIBLE);
                    examine3.setTextColor(getResources().getColor(R.color.black));
                    examine2.setTextColor(getResources().getColor(R.color.blue));
                    break;
                case R.id.add_btn:
//                    drawerlayout.openDrawer(Gravity.RIGHT);
                    choosefile();
                    break;
            }
        }
    };

    /**
     * 查询文件
     */
    String createid;
    List<ModelFile.ResultBean> resultBeen;
    FileBaseAdapater fileBaseAdapater;
    private void getHttp(){
        MainApi.getInstance(this).getcasefileApi(createid, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                swipe.setRefreshing(false);
                if(type == Constants.TYPE_SUCCESS){
                    resultBeen = GsonUtil.fromJsonList(new Gson(),result,ModelFile.ResultBean.class);
                    list1.clear();
                    list2.clear();
                    for (ModelFile.ResultBean bean:resultBeen){
                        if(bean.getGsid().equals("-1"))//个人文件
                        list2.add(bean);
                        else list1.add(bean);//会所文件
                    }
                    fileBaseAdapater.notifyDataSetChanged();
                }else BaseApi.showErrMsg(FilesActivity.this,result);

            }
        });
    }

    @Override
    public void onRefresh() {
        getHttp();
    }

    /**
     * 删除文件
     */
    String id;
    private void getHttps(final int adapterTag , final int position){
        loadingDialog.show();
        loadingDialog.setLoadingContent("删除中...");
        MainApi.getInstance(this).getdelfileApi(id, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loadingDialog.dismiss();
                if(type == Constants.TYPE_SUCCESS){
                   if(adapterTag == 1)
                       list1.remove(position);
                    else list2.remove(position);
                    fileBaseAdapater.notifyDataSetChanged();
                }else BaseApi.showErrMsg(FilesActivity.this,result);
            }
        });
    }

    // 打开系统文件浏览功能
    Intent intent;
    private void choosefile(){
        intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            startActivityForResult(Intent.createChooser(intent, "请选择一个要上传的文件"), FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(FilesActivity.this, "请安装文件管理器", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK&&FILE_SELECT_CODE == requestCode){
            Uri uri = data.getData();
            Log.i("uri-------->", "" + uri);
//            path = Uri.decode(data.getDataString());
//            path = uri.getPath();
            path = FileUtils.getPath(FilesActivity.this,uri);
            wjm = path.substring(path.lastIndexOf("/") + 1, path.length());//获取文件名
            hzm = FileUtils.getLastUrl(path);//获取后缀名
            xzdz =createid +"/"+newwjm+hzm;//下载地址
            Log.e("path---------->", "" + path);
            postfile();
//            FilesActivity.this.startService(intent);
        }
    }


    /**
     * 上传文件
     * @param
     */
    String hzm;
    String del_flag = "0" ;
    String newwjm;
    String wjm;
    String xzdz;//下载地址
    String createtime;
    String createname;
    private void postfile(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("正在上传文件...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                MainApi.getInstance(FilesActivity.this).getuploadingfileApi(createid,newwjm+hzm,wjm,xzdz,createname,createtime,gsid,
                        del_flag,path, new GetResultCallBack() {
                            @Override
                            public void getResult(String result, int type) {
                                loadingDialog.dismiss();
                                if(type == Constants.TYPE_SUCCESS){//上传成功返回当前数据源ok

                                }else BaseApi.showErrMsg(FilesActivity.this,result);
                            }
                        });
            }
        }).start();
    }

    /**
     * 下载文件
     */
    String downloadUrl;
    private void downfile(){
        downloadUrl =  "http://192.168.188.122/Public/Uploads/"+xzdz;
        loadingDialog.show();
        loadingDialog.setLoadingContent("下载文件。。。");
        MainApi.getInstance(this).getdownloadfileApi(downloadUrl,new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loadingDialog.dismiss();
                if(type == Constants.TYPE_SUCCESS){
                    Log.i("===========","下载成功");
                }else BaseApi.showErrMsg(FilesActivity.this,result);
            }
        });
    }

    String gsid ;
    @Override
    public void click(View v) {
        int position = (Integer) v.getTag();
        switch (v.getId()){
            case R.id.del:
                if(fileBaseAdapater.getadapterTag() == 1){
                    Log.i("=====》","你点击了会所文件删除");
                    id = list1.get(position).getId();
                    xzdz = list1.get(position).getXzdz();
                }else {
                    Log.i("=====》","你点击了个人文件删除");
                    id = list2.get((Integer) v.getTag()).getId();
                }
                Log.i("id==========>",""+id);
                getHttps(fileBaseAdapater.getadapterTag(),position);
                break;
            case R.id.download:
                if(fileBaseAdapater.getadapterTag() == 1){
                    Log.i("=====》","你点击了会所文件下载");
                    xzdz = list1.get(position).getXzdz();
                }else {
                    Log.i("=====》","你点击了个人文件下载");
                    xzdz = list2.get(position).getXzdz();
                }
                downfile();
                break;
        }
    }



    /**
     * 此方法可以多次被不同的View对象调用。
     * 在调用该方法后，
     * 使用View对象的getMessuredHeight()获高度（单位px）
     * @param child 需要测量高度和宽度的View对象，
     */
    private void measureView(View child) {
        ViewGroup.LayoutParams params = child.getLayoutParams();
        if (params == null) {
            params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0,
                params.width);
        int lpHeight = params.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(lpHeight,
                    View.MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(0,
                    View.MeasureSpec.UNSPECIFIED);
        }
        child.measure(childWidthSpec, childHeightSpec);
    }


}
