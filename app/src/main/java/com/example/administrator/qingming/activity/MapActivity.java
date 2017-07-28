package com.example.administrator.qingming.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.model.LatLng;
import com.example.administrator.qingming.R;
import com.example.administrator.qingming.api.BaseApi;
import com.example.administrator.qingming.api.MainApi;
import com.example.administrator.qingming.dialog.LoadingDialog;
import com.example.administrator.qingming.interfaces.GetResultCallBack;
import com.example.administrator.qingming.model.Constants;
import com.example.administrator.qingming.model.ModelQianDao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2017/5/8 0008.
 */

public class MapActivity extends Activity{
    LoadingDialog loadingDialog;
    private LinearLayout sb,xb;
    public MapView mapView = null;
    public BaiduMap baiduMap = null;
    public BaiduMap baiduMapcril = null;
    // 定位相关声明
    public LocationClient locationClient = null;
    //自定义图标
    BitmapDescriptor mCurrentMarker = null;
    boolean isFirstLoc = true;// 是否首次定位
    int mYear;
    int mMonth;
    int mDay,HH,NN,SS;
    int dkday,sdkdate,xddate;
    int msdkday,mxdkday,msdkdate,mxdkdate;
    int minuteOfDay;
    final int start = 18 * 60 + 30;// 起始时间 18:30的分钟数
    String mWeek;
    private TextView time,qiandaojilu,sbdk,xbdk;
    private TextView longitude,latitude;//经，纬度
    String mlongitude,mlatitude;
    private TextView dakas;
    double linejuli;
    private ImageView back_btn;
    boolean issb = false;
    boolean isxb = false;
    private List<ModelQianDao.ResultBean> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.baidu_map);

        checkPermisson();

        SharedPreferences sharedPreferences = getSharedPreferences("qinmin", Context.MODE_PRIVATE);
        id = sharedPreferences.getString("id", "");
        uid = sharedPreferences.getString("id", "");
        cid = sharedPreferences.getString("cid", "");

        SharedPreferences sharedPreferences1 = getSharedPreferences("sdaka", Context.MODE_PRIVATE);
        msdkday = sharedPreferences1.getInt("date", 0);//我上班打卡的那天
        msdkdate = sharedPreferences1.getInt("sb", 0);

        SharedPreferences sharedPreferences2 = getSharedPreferences("xdaka", Context.MODE_PRIVATE);
        mxdkday = sharedPreferences2.getInt("date", 0);//我下班打卡的那天
        mxdkdate = sharedPreferences2.getInt("xb", 0);

        initView();
//        getHttp();

        //获取日期
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy/MM/dd  HH:mm:ss");
        String date = sDateFormat.format(new java.util.Date());

        create_date = sDateFormat.format(new java.util.Date());
        daka_time = sDateFormat.format(new java.util.Date());

        final Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
        mWeek = String.valueOf(ca.get(Calendar.DAY_OF_WEEK));
        HH = ca.get(Calendar.HOUR_OF_DAY);
        NN = ca.get(Calendar.MINUTE);
        SS = ca.get(Calendar.SECOND);

        if (mWeek.equals("1")) {
            mWeek = "天";
        } else if (mWeek.equals("2")) {
            mWeek = "一";
        } else if (mWeek.equals("3")) {
            mWeek = "二";
        } else if (mWeek.equals("4")) {
            mWeek = "三";
        } else if (mWeek.equals("5")) {
            mWeek = "四";
        } else if (mWeek.equals("6")) {
            mWeek = "五";
        } else if (mWeek.equals("7")) {
            mWeek = "六";
        }

        Log.e("=======>","mDay==="+mDay);
        Log.e("=======>","mdkday==="+msdkday+"========"+mxdkday);
        Log.e("=======>","msdkdate==="+msdkdate);
        Log.e("=======>","mxdkdate==="+mxdkdate);
        //判断上班签到
        if(msdkday == mDay){
            if(msdkdate  == 1){
                issb = true;
                sbdk.setText("签到成功");
            }
        }else {
            Toast.makeText(MapActivity.this,"这是第二天",Toast.LENGTH_SHORT).show();
        }

        //判断下班签到
        if(mxdkday == mDay){
            if(mxdkdate == 1)
                isxb = true ;
                xbdk.setText("签到成功");
        }else {
            Toast.makeText(MapActivity.this,"这是第二天",Toast.LENGTH_SHORT).show();
        }

        time.setText(date+"星期"+mWeek);
    }


    private void initView() {
        list = new ArrayList<>();
        back_btn = (ImageView) findViewById(R.id.back_btn);
        loadingDialog = new LoadingDialog(this);
        sb = (LinearLayout) findViewById(R.id.sb);
        xb = (LinearLayout) findViewById(R.id.xb);
        sbdk = (TextView) findViewById(R.id.sbdk);
        xbdk = (TextView) findViewById(R.id.xbdk);
        qiandaojilu = (TextView) findViewById(R.id.qiandaojilu);
        time = (TextView) findViewById(R.id.time);
        longitude = (TextView) findViewById(R.id.longitude);
        mapView = (MapView) this.findViewById(R.id.baidu_map); // 获取地图控件引用
        baiduMap = mapView.getMap();
        baiduMapcril = mapView.getMap();
        //开启定位图层
        baiduMap.setMyLocationEnabled(true);

        locationClient = new LocationClient(getApplicationContext()); // 实例化LocationClient类
        locationClient.registerLocationListener(myListener); // 注册监听函数
        setLocationOption();	//设置定位参数
        locationClient.start(); // 开始定位

        qiandaojilu.setOnClickListener(onclick);
        sb.setOnClickListener(onclick);
        xb.setOnClickListener(onclick);
        back_btn.setOnClickListener(onclick);
    }


    View.OnClickListener onclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back_btn:
                    finish();
                    break;
                case R.id.qiandaojilu:
                    Intent intent = new Intent(MapActivity.this,KaoQinActivity.class);
                    startActivity(intent);
                    break;
                case R.id.sb:
                    if(!issb){
                        Log.e("===>",""+HH);
                        index =0;
                        dakalx = "1";
                        if(HH <= 9){
                            dakafl = ""+1;
                        }else {
                            dakafl = ""+2;
                        }
                        postHttp();
                        issb = true ;

                    }else {
                        Toast.makeText(MapActivity.this,"你今天已经上班打卡了",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.xb:
                    if(!isxb){
                        index = 1;
                        dakalx = "2";
                        minuteOfDay = HH * 60 + NN;// 从0:00分开是到目前为止的分钟数
                        Log.e("===>",""+HH);
                        Log.e("===>",""+start);
                        Log.e("===>",""+minuteOfDay);
                        if(minuteOfDay >= start){
                            Log.e("===>","正常");
                            dakafl = ""+1;
                        }else {
                            Log.e("===>","早退");
                            dakafl = ""+5;
                        }
                        postHttp();
                        isxb = true;

                    }else {
                        Toast.makeText(MapActivity.this,"你今天已经下班打卡了",Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    int index = 0;
    String id;
    String uid;
    String cid;
    String daka_time;
    String dakalx;//上下班的类型
    String dakatype;
    String dakafl;
    String ktdate;
    String elongitude;
    String nlatitude;
    String create_date;
    private void postHttp(){
        loadingDialog.show();
        loadingDialog.setLoadingContent("上传中...");
        MainApi.getInstance(this).postqiandaoApi(id,uid, cid, daka_time, dakalx, dakatype, dakafl,
                ktdate, elongitude, nlatitude, create_date, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type == Constants.TYPE_SUCCESS){
                    loadingDialog.dismiss();
                    if(index == 0){
                        sbdk.setText("签到成功");
                        sdkdate = 1;
                    }
                    if(index == 1){
                        xbdk.setText("签到成功");
                        xddate = 1;
                    }
                    dkday = mDay;
                    saveSharePreferences();
                }else BaseApi.showErrMsg(MapActivity.this,result);
            }
        });
    }

//    private void getHttp(){
//        MainApi.getInstance(this).getqiandaoApi(id, new GetResultCallBack() {
//            @Override
//            public void getResult(String result, int type) {
//                if(type == Constants.TYPE_SUCCESS){
//                    List<ModelQianDao.ResultBean> resultBeen = GsonUtil.fromJsonList(new Gson(),result,ModelQianDao.ResultBean.class);
//                    list.clear();
//                    list.addAll(resultBeen);
//
//                    for (int i=0;i<resultBeen.size();i++){
//                        resultBeen.get(i).getDaka_time();
//                        Log.e("=========",resultBeen.get(i).getDaka_time());
//                    }
//                }else BaseApi.showErrMsg(MapActivity.this,result);
//            }
//        });
//    }

    //存储到本地
    public void saveSharePreferences(){
        if(index == 0) {
            SharedPreferences sharedPreferences1 = getSharedPreferences("sdaka", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences1.edit();
            editor.putInt("date", dkday);//打卡时间
            editor.putInt("sb", sdkdate);//是否上班签到
            editor.commit();
        }else {
            SharedPreferences sharedPreferences2 = getSharedPreferences("xdaka", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor1 = sharedPreferences2.edit();
            editor1.putInt("date", dkday);//打卡时间
            editor1.putInt("xb", xddate);//是否下班签到
            editor1.commit();
        }
    }

    public BDLocationListener myListener = new BDLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mapView == null)
                return;

            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            baiduMap.setMyLocationData(locData);	//设置定位数据

            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),location.getLongitude());
                LatLng llcir = new LatLng(29.58929,106.47305);//圆心坐标
                //地图标注
                BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.mipmap.adress);
                OverlayOptions options = new MarkerOptions().position(ll).icon(bitmapDescriptor);
                baiduMap.addOverlay(options);
                //标绘圆
                CircleOptions circleOptions = new CircleOptions();
                circleOptions.center(llcir);//设置圆心坐标
                circleOptions.fillColor(0Xaafaa355);//圆的填充颜色
                circleOptions.fillColor(0Xaafaa355);//圆的填充颜色
                circleOptions.radius(500);//设置半径
                circleOptions.stroke(new Stroke(2, 0xAA00FF00));//设置边框
                baiduMapcril.addOverlay(circleOptions);

                MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(ll, 16);	//设置地图中心点以及缩放级别
//				MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
                baiduMap.animateMapStatus(u);
                showInfo("位置：" + location.getAddrStr());
                Log.i("经纬度====>","纬度"+location.getLatitude()+"经度"+location.getLongitude());
                elongitude = "" +location.getLongitude();
                nlatitude = "" +location.getLatitude();
            }

            //获取定位结果
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());    //获取定位时间
            sb.append("\nerror code : ");
            sb.append(location.getLocType());    //获取类型类型
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());    //获取纬度信息
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());    //获取经度信息
            sb.append("\nradius : ");
            sb.append(location.getRadius());    //获取定位精准度
            Log.i("BaiduLocationApiDem", sb.toString());

            handler.sendEmptyMessage(0);
            mlatitude = String.valueOf(location.getLatitude());
            mlongitude = String.valueOf(location.getLongitude());

            //测试经纬度间的距离
            LatLng start = new LatLng(location.getLongitude(),location.getLatitude());
            LatLng end = new LatLng(106.47305,29.58929);//公司地址
            linejuli = getDistance(start, end);
            if(linejuli>=100){
                dakatype =""+1;
            }else {
                dakatype =""+2;
            }

            Log.i("BaiduLocationApiDem", ""+location.getLatitude()+""+location.getLongitude());
//            Message msg = handler.obtainMessage();
//            msg.what = 0; //消息标识
//            Bundle bundle=new Bundle();
//            bundle.putString("Latitude",""+location.getLatitude());
//            bundle.putString("Longitude",""+location.getLongitude());
//            msg.setData(bundle);
//            msg.obj=bundle;
//            handler.sendMessage(msg); //发送消息

        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
    };

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            longitude.setText(""+linejuli);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mapView.onPause();
    }
    /**
     * 动态权限的请求
     */
    public void checkPermisson() {
        if (Build.VERSION.SDK_INT >= 23) {
            ActivityCompat.requestPermissions(this,//上下文
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},//权限数组
                    1001);
        }
    }

    //显示消息
    private void showInfo(String str){
        Toast.makeText(MapActivity.this, str, Toast.LENGTH_SHORT).show();
    }

    /**
     * 动态权限的回调函数
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 设置定位参数
     */
    private void setLocationOption() {
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开GPS
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 设置定位模式
        option.setCoorType("bd09ll"); // 返回的定位结果是百度经纬度,默认值gcj02
        option.setScanSpan(5000); // 设置发起定位请求的间隔时间为5000ms
        option.setIsNeedAddress(true); // 返回的定位结果包含地址信息
        option.setNeedDeviceDirect(true); // 返回的定位结果包含手机机头的方向

        locationClient.setLocOption(option);
    }

    /**
     * 计算两点之间距离
     * @param start
     * @param end
     * @return 米
     */
    public double getDistance(LatLng start,LatLng end){
        double lat1 = (Math.PI/180)*start.latitude;
        double lat2 = (Math.PI/180)*end.latitude;

        double lon1 = (Math.PI/180)*start.longitude;
        double lon2 = (Math.PI/180)*end.longitude;

//      double Lat1r = (Math.PI/180)*(gp1.getLatitudeE6()/1E6);
//      double Lat2r = (Math.PI/180)*(gp2.getLatitudeE6()/1E6);
//      double Lon1r = (Math.PI/180)*(gp1.getLongitudeE6()/1E6);
//      double Lon2r = (Math.PI/180)*(gp2.getLongitudeE6()/1E6);
        //地球半径
        double R = 6371;

        //两点间距离 km，如果想要米的话，结果*1000就可以了
        double d =  Math.acos(Math.sin(lat1)*Math.sin(lat2)+Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon2-lon1))*R;
        return d*1000;
    }

}
