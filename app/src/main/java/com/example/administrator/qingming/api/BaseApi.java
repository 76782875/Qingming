package com.example.administrator.qingming.api;

import android.content.Context;
import android.util.Log;

import com.example.administrator.qingming.interfaces.GetResultCallBack;
import com.example.administrator.qingming.model.Constants;
import com.example.administrator.qingming.qinminutils.OkHttpClientManager;
import com.example.administrator.qingming.qinminutils.ToastUtil;
import com.squareup.okhttp.Request;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/15.
 */
public class BaseApi {

    public Context mContext;

    public BaseApi(Context context) {
        mContext = context;
    }

    public void postLoad(String url, Map<String, String> map, final GetResultCallBack mCallBack) {
        Log.e("post请求url============", url + map.toString());
        OkHttpClientManager.postAsyn(url, map, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                ToastUtil.makeToast(mContext, "网络异常");
                mCallBack.getResult(request.toString(), Constants.TYPE_FAIL);
            }

            @Override
            public void onResponse(String response) {
                Log.e("response=======", response);
                try {
                    if (OkHttpClientManager.isParse(response)) {
                        mCallBack.getResult(response, Constants.TYPE_SUCCESS);
                    } else {
                        mCallBack.getResult(response, Constants.TYPE_FAIL);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void getLoad(String url, Map<String, String> map, final GetResultCallBack mCallBack) {
        if (map == null)
            map = new HashMap<>();
//        if (!TextUtils.isEmpty(MyData.uid)) {
//            long time = DateUtils.getCurrentTimeMillis() + MyData.timeDifference;
//            map.put("sign", MD5Utils.get32MD5(MyData.APIAndroidKey + time + MyData.uid));
//            map.put("timestamp", time + "");
//            if (!map.containsKey("uid"))
//                map.put("uid", MyData.uid);
//        }
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            url += entry.getKey() + "=" + entry.getValue() + "&";
        }
        if (url.endsWith("&")) {
            url = url.substring(0, url.length() - 1);
        }
        Log.e("get请求url============", url);
        OkHttpClientManager.getAsyn(url, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                ToastUtil.makeToast(mContext, "网络异常");
                mCallBack.getResult(request.toString(), Constants.TYPE_FAIL);
            }

            @Override
            public void onResponse(String response) {
                Log.e("response=======", response);
                try {
                    if (OkHttpClientManager.isParse(response)) {
                        mCallBack.getResult(response, Constants.TYPE_SUCCESS);
                    } else {
                        mCallBack.getResult(response, Constants.TYPE_FAIL);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void showErrMsg(Context context, String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            String msg = (String) jsonObject.get("msg");
            ToastUtil.makeToast(context, msg);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
