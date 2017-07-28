package com.example.administrator.qingming.api;

import android.content.Context;
import android.util.Log;


import com.example.administrator.qingming.interfaces.GetResultCallBack;
import com.example.administrator.qingming.model.Constants;
import com.example.administrator.qingming.qinminutils.FileUtils;
import com.example.administrator.qingming.qinminutils.OkHttpClientManager;
import com.example.administrator.qingming.url.BaseUrl;
import com.squareup.okhttp.Request;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/2.
 */
public class MainApi extends BaseApi {

    private static MainApi api;

    public static MainApi getInstance(Context context) {
        synchronized (MainApi.class) {
            if (api == null) {
                api = new MainApi(context);
            }
        }
        return api;
    }

    public MainApi(Context context) {
        super(context);
    }

    /**
     * 通讯录
     */
    public void getTxlApi(String gsid,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("gsid", gsid + "");
        getLoad(BaseUrl.lvtxlurl, map, callBack);
    }

    /**
     * 进入通讯录
     */
    public void getIntoTxlApi(String id,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id + "");
        getLoad(BaseUrl.intolvtxlurl, map, callBack);
    }

    /**
     * 新闻
     */
    public void getNewsApi(String gsid,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("gsid", gsid + "");
        getLoad(BaseUrl.news, map, callBack);
    }

    /**
     * 进入新闻
     */
    public void getIntoNewsApi(String id,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id + "");
        getLoad(BaseUrl.intonews, map, callBack);
    }

    /**
     * 进入变更律师
     */
    public void getbglvshiApi(String company_id,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("company_id", company_id + "");
        getLoad(BaseUrl.bglvshi, map, callBack);
    }

    /**
     * 进入变更律师
     */
    public void getintobglvshiApi(String id,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id + "");
        getLoad(BaseUrl.intobglvshi, map, callBack);
    }

    /**
     * 文件
     */
    public void getcasefileApi(String createid,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("createid", createid + "");
        getLoad(BaseUrl.casefile, map, callBack);
    }

    /**
     * 删除文件
     */
    public void getdelfileApi(String id,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id + "");
        getLoad(BaseUrl.deletefile, map, callBack);
    }


    /**
     * 上传文件
     */
    public void getuploadingfileApi(String createid,String newwjm,String wjm,String xzdz,String createname,
                                    String createtime, String gsid,String del_flag,String path, final GetResultCallBack callBack) {
        OkHttpClientManager.getUploadDelegate().postAsyn(BaseUrl.submit , "file", new File(path),
                new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("gsid", gsid),
                        new OkHttpClientManager.Param("newwjm", newwjm),
                        new OkHttpClientManager.Param("wjm", wjm),
                        new OkHttpClientManager.Param("xzdz", xzdz),
                        new OkHttpClientManager.Param("createid", createid),
                        new OkHttpClientManager.Param("createname", createname),
                        new OkHttpClientManager.Param("createtime", createtime),
                        new OkHttpClientManager.Param("del_flag", del_flag)},
                new OkHttpClientManager.ResultCallback<String>() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e("onError","============"+request);
                        callBack.getResult(request.toString(),Constants.TYPE_FAIL);
                    }
                    @Override
                    public void onResponse(String response) {
                        Log.e("onResponse","============"+response);
                        try {
                            if(OkHttpClientManager.isParse(response)){
                                callBack.getResult(response,Constants.TYPE_SUCCESS);
                            }else {
                                callBack.getResult(response,Constants.TYPE_FAIL);
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                },mContext);
    }

    /**
     * 下载文件
     */
    public void getdownloadfileApi(String downloadUrl, final GetResultCallBack callBack) {
        OkHttpClientManager.getDownloadDelegate().downloadAsyn(downloadUrl, FileUtils.getFileDir(),
                null,
                new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                callBack.getResult(request.toString(),Constants.TYPE_FAIL);
            }
            @Override
            public void onResponse(String response) {
                Log.e("--------------->",""+response);
                try {
                    if(OkHttpClientManager.isParse(response)){
                        callBack.getResult(response,Constants.TYPE_SUCCESS);
                    }else {
                        callBack.getResult(response,Constants.TYPE_FAIL);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, mContext);
    }


    /**
     * 审批
     */
    public void getsaspApi(String company_id,int case_state,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("company_id", company_id + "");
        map.put("case_state", case_state + "");
        getLoad(BaseUrl.sasp, map, callBack);
    }

    /**
     * 个人资料
     */
    public void getpersonal_dateApi(String id,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id + "");
        getLoad(BaseUrl.personal_date, map, callBack);
    }

    /**
     * 修改个人资料
     */
    public void getinsert_dataApi(String id,String name1,String email,String mobile,String remarks,
                                  String name,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id + "");
        map.put("name1",name1 + "");
        map.put("email",email + "");
        map.put("mobile",mobile + "");
        map.put("remarks",remarks + "");
        map.put("name",name + "");
        postLoad(BaseUrl.insert_data, map, callBack);
    }

    /**
     * 添加案件
     */
    public void getpostcaseApi(String id,String cid,int ajlx,int ajfl,String ah_number,String sarq,
                               String ay,String remarks, String wtr, String dfdsr,String slbm,
                               String ssbd,String sffs,String dlf,String zjf, int ssjd, int ssdw,
                               String badq, String police, String mprocuratorate, String mcourt,
                               String detention,String create_date,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("create_id", id + "");
        map.put("company_id",cid + "");
        map.put("ajlx", ajlx + "");
        map.put("ajfl",ajfl + "");
        map.put("ah_number",ah_number + "");
        map.put("sarq",sarq + "");
        map.put("ay",ay + "");
        map.put("bzsm",remarks + "");
        map.put("wtr",wtr + "");
        map.put("dfdsr",dfdsr + "");
        map.put("slbm",slbm + "");
        map.put("ssbd",ssbd + "");
        map.put("sffs",sffs + "");
        map.put("dlf",dlf + "");
        map.put("jzf",zjf + "");
        map.put("ssjd",ssjd + "");
        map.put("ssdw",ssdw + "");
        map.put("badq",badq + "");
        map.put("police",police + "");
        map.put("procuratorate",mprocuratorate + "");
        map.put("court",mcourt + "");
        map.put("detention",detention + "");
        map.put("create_date",create_date + "");
        postLoad(BaseUrl.postcase, map, callBack);
    }

    /**
     * 修改案件
     */
    public void getxgmycaseApi(String id,String cid,int ajlx,int ajfl,String ah_number,String sarq,
                               String ay,String remarks, String wtr, String dfdsr,String slbm,
                               String ssbd,String sffs,String dlf,String zjf, int ssjd, int ssdw,
                               String badq, String police, String mprocuratorate, String mcourt,
                               String detention,String create_date,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("create_id", id + "");
        map.put("company_id",cid + "");
        map.put("ajlx", ajlx + "");
        map.put("ajfl",ajfl + "");
        map.put("ah_number",ah_number + "");
        map.put("sarq",sarq + "");
        map.put("ay",ay + "");
        map.put("bzsm",remarks + "");
        map.put("wtr",wtr + "");
        map.put("dfdsr",dfdsr + "");
        map.put("slbm",slbm + "");
        map.put("ssbd",ssbd + "");
        map.put("sffs",sffs + "");
        map.put("dlf",dlf + "");
        map.put("jzf",zjf + "");
        map.put("ssjd",ssjd + "");
        map.put("ssdw",ssdw + "");
        map.put("badq",badq + "");
        map.put("police",police + "");
        map.put("procuratorate",mprocuratorate + "");
        map.put("court",mcourt + "");
        map.put("detention",detention + "");
        map.put("create_date",create_date + "");
        postLoad(BaseUrl.xgmycase, map, callBack);
    }

    /**
     * 添加案件(咨询代写文书)
     */
    public void getpostcasezxApi(String id,String cid,String fwnr,String fwdate,String ah_number,
                                 int fwtype,String tgfwrc,String fwfy, String wtr,
                               String bzsm,String create_date,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("create_id", id + "");
        map.put("company_id",cid + "");
        map.put("fwnr", fwnr + "");
        map.put("fwdate",fwdate + "");
        map.put("ah_number",ah_number + "");
        map.put("fwtype",fwtype + "");
        map.put("tgfwrc",tgfwrc + "");
        map.put("fwfy",fwfy + "");
        map.put("wtr",wtr + "");
        map.put("bzsm",bzsm + "");
        map.put("create_date",create_date + "");
        postLoad(BaseUrl.postcasezx, map, callBack);
    }

    /**
     * 我的案件
     */
    public void getmycaseApi(String create_id, GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("create_id", create_id + "");
        postLoad(BaseUrl.mycase, map, callBack);
    }
    /**
     * 律所案件
     */
    public void getlscaseApi(String company_id, GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("company_id", company_id + "");
        postLoad(BaseUrl.lscase, map, callBack);
    }

    /**
     * 收费
     */
    public void getshoufeiApi(String id, GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("glid", id + "");
        postLoad(BaseUrl.shoufei, map, callBack);
    }

    /**
     * 添加收费
     */
    public void getaddshoufeiApi(String id,String cid,String glid,String sfje,String bz,String sflx,
                                 String fylx,String create_time,String create_name,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("create_id", id + "");
        map.put("company_id",cid + "");
        map.put("glid", glid + "");
        map.put("sfje",sfje + "");
        map.put("bz",bz + "");
        map.put("sflx",sflx + "");
        map.put("fylx",fylx + "");
        map.put("create_time",create_time + "");
        map.put("create_name",create_name + "");
        postLoad(BaseUrl.addshoufei, map, callBack);
    }

    /**
     * 添加日志
     */
    public void postaddrizhiApi(String id,String cid,String glid,String log_type,String bz,String start_date,
                                 String stop_date,String create_date,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("create_id", id + "");
        map.put("company_id",cid + "");
        map.put("glid", glid + "");
        map.put("log_type",log_type + "");
        map.put("bzsm",bz + "");
        map.put("start_date",start_date + "");
        map.put("stop_date",stop_date + "");
        map.put("create_date",create_date + "");
        postLoad(BaseUrl.addrizhi, map, callBack);
    }


    /**
     * 日志
     */
    public void getrizhiApi(String id,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("glid", id + "");
        postLoad(BaseUrl.rizhi, map, callBack);
    }


    /**
     * 添加侦查机关
     */
    public void postaddzhenchaApi1(String id,String cid,String glid,String bajg,String bz,String zcah,
                                String bm,String cbr,String tel,String create_date,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("create_id", id + "");
        map.put("company_id",cid + "");
        map.put("glid", glid + "");
        map.put("bajg",bajg + "");
        map.put("bzsm",bz + "");
        map.put("zcah",zcah + "");
        map.put("bm",bm + "");
        map.put("cbr",cbr + "");
        map.put("tel",tel + "");
        map.put("create_date",create_date + "");
        postLoad(BaseUrl.addzhencha, map, callBack);
    }
    /**
     * 添加侦查机关（刑拘时间）
     */
    public void postaddzhenchaApi2(String id,String cid,String glid,String bajg,String bz,String zcah,
                                  String bm,String cbr,String tel,String xjsj,String create_date,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("create_id", id + "");
        map.put("company_id",cid + "");
        map.put("glid", glid + "");
        map.put("bajg",bajg + "");
        map.put("bzsm",bz + "");
        map.put("zcah",zcah + "");
        map.put("bm",bm + "");
        map.put("cbr",cbr + "");
        map.put("tel",tel + "");
        map.put("xjsj",xjsj + "");
        map.put("create_date",create_date + "");
        postLoad(BaseUrl.addzhencha, map, callBack);
    }
    /**
     * 添加侦查机关（刑拘时间、逮捕时间）
     */
    public void postaddzhenchaApi3(String id,String cid,String glid,String bajg,String bz,String zcah,
                                  String bm,String cbr,String tel,String xjsj,String dbsj,
                                  String create_date,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("create_id", id + "");
        map.put("company_id",cid + "");
        map.put("glid", glid + "");
        map.put("bajg",bajg + "");
        map.put("bzsm",bz + "");
        map.put("zcah",zcah + "");
        map.put("bm",bm + "");
        map.put("cbr",cbr + "");
        map.put("tel",tel + "");
        map.put("xjsj",xjsj + "");
        map.put("dbsj",dbsj + "");
        map.put("create_date",create_date + "");
        postLoad(BaseUrl.addzhencha, map, callBack);
    }
    /**
     * 添加侦查机关（刑拘时间、逮捕时间、侦查时间）
     */
    public void postaddzhenchaApi4(String id,String cid,String glid,String bajg,String bz,String zcah,
                                   String bm,String cbr,String tel,String xjsj,String dbsj,String zcqx_star,
                                   String zcqx_end, String create_date,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("create_id", id + "");
        map.put("company_id",cid + "");
        map.put("glid", glid + "");
        map.put("bajg",bajg + "");
        map.put("bzsm",bz + "");
        map.put("zcah",zcah + "");
        map.put("bm",bm + "");
        map.put("cbr",cbr + "");
        map.put("tel",tel + "");
        map.put("xjsj",xjsj + "");
        map.put("dbsj",dbsj + "");
        map.put("zcqx_star",zcqx_star + "");
        map.put("zcqx_end",zcqx_end + "");
        map.put("create_date",create_date + "");
        postLoad(BaseUrl.addzhencha, map, callBack);
    }
    /**
     * 添加侦查机关（刑拘时间、逮捕时间、侦查时间、补充侦查）
     */
    public void postaddzhenchaApi5(String id,String cid,String glid,String bajg,String bz,String zcah,
                                   String bm,String cbr,String tel,String xjsj,String dbsj,String zcqx_star,
                                   String zcqx_end, String bczcqx_star, String bczcqx_end, String create_date,
                                   GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("create_id", id + "");
        map.put("company_id",cid + "");
        map.put("glid", glid + "");
        map.put("bajg",bajg + "");
        map.put("bzsm",bz + "");
        map.put("zcah",zcah + "");
        map.put("bm",bm + "");
        map.put("cbr",cbr + "");
        map.put("tel",tel + "");
        map.put("xjsj",xjsj + "");
        map.put("dbsj",dbsj + "");
        map.put("zcqx_star",zcqx_star + "");
        map.put("zcqx_end",zcqx_end + "");
        map.put("bczcqx_star",bczcqx_star + "");
        map.put("bczcqx_end",bczcqx_end + "");
        map.put("create_date",create_date + "");
        postLoad(BaseUrl.addzhencha, map, callBack);
    }
    /**
     * 添加侦查机关（刑拘时间、逮捕时间、侦查时间、补充侦查、二次侦查）
     */
    public void postaddzhenchaApi6(String id,String cid,String glid,String bajg,String bz,String zcah,
                                   String bm,String cbr,String tel,String xjsj,String dbsj,String zcqx_star,
                                   String zcqx_end, String bczcqx_star, String bczcqx_end, String ecbczc_star,
                                   String ecbczc_end, String create_date,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("create_id", id + "");
        map.put("company_id",cid + "");
        map.put("glid", glid + "");
        map.put("bajg",bajg + "");
        map.put("bzsm",bz + "");
        map.put("zcah",zcah + "");
        map.put("bm",bm + "");
        map.put("cbr",cbr + "");
        map.put("tel",tel + "");
        map.put("xjsj",xjsj + "");
        map.put("dbsj",dbsj + "");
        map.put("zcqx_star",zcqx_star + "");
        map.put("zcqx_end",zcqx_end + "");
        map.put("bczcqx_star",bczcqx_star + "");
        map.put("bczcqx_end",bczcqx_end + "");
        map.put("ecbczc_star",ecbczc_star + "");
        map.put("ecbczc_star",ecbczc_star + "");
        map.put("ecbczc_end",ecbczc_end + "");
        map.put("create_date",create_date + "");
        postLoad(BaseUrl.addzhencha, map, callBack);
    }

    /**
     * 侦查列表
     */
    public void getzhenchaApi(String id,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("glid", id + "");
        postLoad(BaseUrl.zhencha, map, callBack);
    }

    /**
     * 法院
     */
    public void getfayuanApi(String id,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("glid", id + "");
        postLoad(BaseUrl.fayuan, map, callBack);
    }

    /**
     * 法院(消息通知)
     */
    public void getnewsfayuanApi(String create_id,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("create_id", create_id + "");
        postLoad(BaseUrl.newsfayuan, map, callBack);
    }

    /**
     * 检查列表
     */
    public void getjianchaApi(String id,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("glid", id + "");
        postLoad(BaseUrl.jiancha, map, callBack);
    }


    /**
     * 修改侦查列表
     */
    public void getxgzhenchaApi(String id,String bajg,String bz,String zcah,
                                String bm,String cbr,String tel,String xjsj,String dbsj,String zcqx_star,
                                String zcqx_end, String bczcqx_star, String bczcqx_end, String ecbczc_star,
                                String ecbczc_end, String update_date,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id + "");
        map.put("bajg",bajg + "");
        map.put("bzsm",bz + "");
        map.put("zcah",zcah + "");
        map.put("bm",bm + "");
        map.put("cbr",cbr + "");
        map.put("tel",tel + "");
        map.put("xjsj",xjsj + "");
        map.put("dbsj",dbsj + "");
        map.put("zcqx_star",zcqx_star + "");
        map.put("zcqx_end",zcqx_end + "");
        map.put("bczcqx_star",bczcqx_star + "");
        map.put("bczcqx_end",bczcqx_end + "");
        map.put("ecbczc_star",ecbczc_star + "");
        map.put("ecbczc_star",ecbczc_star + "");
        map.put("ecbczc_end",ecbczc_end + "");
        map.put("update_date",update_date + "");
        postLoad(BaseUrl.xgzhencha, map, callBack);
    }

    public void getxgzhenchaApi1(String id,String bajg,String bz,String zcah,
                                String bm,String cbr,String tel,String xjsj,String dbsj,String zcqx_star,
                                String zcqx_end, String bczcqx_star, String bczcqx_end, String update_date,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id + "");
        map.put("bajg",bajg + "");
        map.put("bzsm",bz + "");
        map.put("zcah",zcah + "");
        map.put("bm",bm + "");
        map.put("cbr",cbr + "");
        map.put("tel",tel + "");
        map.put("xjsj",xjsj + "");
        map.put("dbsj",dbsj + "");
        map.put("zcqx_star",zcqx_star + "");
        map.put("zcqx_end",zcqx_end + "");
        map.put("bczcqx_star",bczcqx_star + "");
        map.put("bczcqx_end",bczcqx_end + "");
        map.put("update_date",update_date + "");
        postLoad(BaseUrl.xgzhencha, map, callBack);
    }

    public void getxgzhenchaApi2(String id,String bajg,String bz,String zcah,
                                String bm,String cbr,String tel,String xjsj,String dbsj,String zcqx_star,
                                String zcqx_end, String update_date,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id + "");
        map.put("bajg",bajg + "");
        map.put("bzsm",bz + "");
        map.put("zcah",zcah + "");
        map.put("bm",bm + "");
        map.put("cbr",cbr + "");
        map.put("tel",tel + "");
        map.put("xjsj",xjsj + "");
        map.put("dbsj",dbsj + "");
        map.put("zcqx_star",zcqx_star + "");
        map.put("zcqx_end",zcqx_end + "");
        map.put("update_date",update_date + "");
        postLoad(BaseUrl.xgzhencha, map, callBack);
    }

    public void getxgzhenchaApi3(String id,String bajg,String bz,String zcah,
                                String bm,String cbr,String tel,String xjsj,String dbsj, String update_date,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id + "");
        map.put("bajg",bajg + "");
        map.put("bzsm",bz + "");
        map.put("zcah",zcah + "");
        map.put("bm",bm + "");
        map.put("cbr",cbr + "");
        map.put("tel",tel + "");
        map.put("xjsj",xjsj + "");
        map.put("dbsj",dbsj + "");
        map.put("update_date",update_date + "");
        postLoad(BaseUrl.xgzhencha, map, callBack);
    }

    public void getxgzhenchaApi4(String id,String bajg,String bz,String zcah,
                                String bm,String cbr,String tel,String xjsj,String update_date,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id + "");
        map.put("bajg",bajg + "");
        map.put("bzsm",bz + "");
        map.put("zcah",zcah + "");
        map.put("bm",bm + "");
        map.put("cbr",cbr + "");
        map.put("tel",tel + "");
        map.put("xjsj",xjsj + "");
        map.put("update_date",update_date + "");
        postLoad(BaseUrl.xgzhencha, map, callBack);
    }

    public void getxgzhenchaApi5(String id,String bajg,String bz,String zcah,
                                 String bm,String cbr,String tel,String update_date,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id + "");
        map.put("bajg",bajg + "");
        map.put("bzsm",bz + "");
        map.put("zcah",zcah + "");
        map.put("bm",bm + "");
        map.put("cbr",cbr + "");
        map.put("tel",tel + "");
        map.put("update_date",update_date + "");
        postLoad(BaseUrl.xgzhencha, map, callBack);
    }

    /**
     * 删除侦查列表
     */
    public void getdelzhenchaApi(String id,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id + "");
        postLoad(BaseUrl.delzhencha, map, callBack);
    }

    /**
     * 删除检查列表
     */
    public void getdeljianchaApi(String id,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id + "");
        postLoad(BaseUrl.deljiancha, map, callBack);
    }

    /**
     * 删除法院列表
     */
    public void getdelfayuanApi(String id,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id + "");
        postLoad(BaseUrl.delfayuan, map, callBack);
    }

    /**
     * 修改检查列表
     */
    public void getxgjianchaApi(String id,String bajg,String bz,String badd,
                                String bm,String cbr,String tel,String scqs_star,String scqs_end,
                                String update_date,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id + "");
        map.put("bajg",bajg + "");
        map.put("bzsm",bz + "");
        map.put("badd",badd + "");
        map.put("bm",bm + "");
        map.put("cbr",cbr + "");
        map.put("tel",tel + "");
        map.put("scqs_star",scqs_star + "");
        map.put("scqs_end",scqs_end + "");
        map.put("update_date",update_date + "");
        postLoad(BaseUrl.xgjiancha, map, callBack);
    }

    /**
     * 修改法院
     */
    public void getxgfayuanApi(String id,String spcx,String bz,String spjg, String ladate,String ktdate,
                               String spdate,String ssdate,String ft, String zsfg,String zsfgtel,
                               String sjy,String sjytel, String update_date, GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id + "");
        map.put("spcx",spcx + "");
        map.put("bzsm2",bz + "");
        map.put("spjg",spjg + "");
        map.put("ladate",ladate + "");
        map.put("ktdate",ktdate + "");
        map.put("spdate",spdate + "");
        map.put("ssdate",ssdate + "");
        map.put("ft",ft + "");
        map.put("zsfg",zsfg + "");
        map.put("zsfgtel",zsfgtel + "");
        map.put("sjy",sjy + "");
        map.put("sjytel",sjytel + "");
        map.put("update_date",update_date + "");
        postLoad(BaseUrl.xgfayuan, map, callBack);
    }

    /**
     * 添加检查机关
     */
    public void postaddjianchaApi(String id,String cid,String glid,String bajg,String bz,String badd,
                                   String bm,String cbr,String tel,String scqs_star,String scqs_end, String create_date,
                                   GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("create_id", id + "");
        map.put("company_id",cid + "");
        map.put("glid", glid + "");
        map.put("bajg",bajg + "");
        map.put("bzsm",bz + "");
        map.put("badd",badd + "");
        map.put("bm",bm + "");
        map.put("cbr",cbr + "");
        map.put("tel",tel + "");
        map.put("scqs_star",scqs_star + "");
        map.put("scqs_end",scqs_end + "");
        map.put("create_date",create_date + "");
        postLoad(BaseUrl.addjiancha, map, callBack);
    }


    /**
     * 添加法院开庭
     */
    public void postaddfayuanApi(String id,String cid,String glid,String spcx,String bz,String spjg,
                                  String ladate,String ktdate,String spdate,String ssdate,String ft,
                                 String zsfg,String zsfgtel,String sjy,String sjytel,
                                 String create_date, GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("create_id", id + "");
        map.put("company_id",cid + "");
        map.put("glid", glid + "");
        map.put("spcx",spcx + "");
        map.put("bzsm2",bz + "");
        map.put("spjg",spjg + "");
        map.put("ladate",ladate + "");
        map.put("ktdate",ktdate + "");
        map.put("spdate",spdate + "");
        map.put("ssdate",ssdate + "");
        map.put("ft",ft + "");
        map.put("zsfg",zsfg + "");
        map.put("zsfgtel",zsfgtel + "");
        map.put("sjy",sjy + "");
        map.put("sjytel",sjytel + "");
        map.put("create_date",create_date + "");
        postLoad(BaseUrl.addfayuan, map, callBack);
    }

    /**
     * 修改案件状态
     */
    public void getxgzjztApi(String id, int case_state,String update_date,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id + "");
        map.put("case_state",case_state + "");
        map.put("update_date",update_date + "");
        postLoad(BaseUrl.xgzjzt, map, callBack);
    }

    /**
     * 添加签到
     */
    public void postqiandaoApi(String id,String uid,String cid,String daka_time,String dakalx,String dakatype,
                                 String dakafl,String ktdate, String elongitude,String nlatitude,
                                   String create_date, GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("create_id", id + "");
        map.put("userid", uid + "");
        map.put("gsid",cid + "");
        map.put("daka_time",daka_time + "");
        map.put("dakalx",dakalx + "");
        map.put("dakatype",dakatype + "");
        map.put("dakafl",dakafl + "");
        map.put("ktdate",ktdate + "");
        map.put("elongitude",elongitude + "");
        map.put("nlatitude",nlatitude + "");
        map.put("create_date",create_date + "");
        postLoad(BaseUrl.postqiandao, map, callBack);
    }

    /**
     * 签到
     */
    public void getqiandaoApi(String id, GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("userid", id + "");
        postLoad(BaseUrl.qiandao, map, callBack);
    }

    /**
     * 收费
     */
    public void getshoufeixqApi(String id, GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("company_id", id + "");
        postLoad(BaseUrl.shoufeixq, map, callBack);
    }

    /**
     * 收费
     */
    public void getxgshoufeixqApi(String id,String sfje,String sflx,String bz,String fylx,String zt,String audit_id,
                                  String audit_name,String audit_time,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id + "");
        map.put("sfje", sfje + "");
        map.put("sflx", sflx + "");
        map.put("bz", bz + "");
        map.put("fylx", fylx + "");
        map.put("zt", zt + "");
        map.put("audit_id", audit_id + "");
        map.put("audit_name", audit_name + "");
        map.put("audit_time", audit_time + "");
        postLoad(BaseUrl.xgshoufeixq, map, callBack);
    }

    /**
     * 法律
     */
    public void getfalvsApi(int cons_type,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("cons_type", cons_type + "");
        postLoad(BaseUrl.falvs, map, callBack);
    }

    /**
     * 案号
     */
    public void getanhaoApi(String company_id,int ajlx,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("companyId", company_id + "");
        map.put("ajlx", ajlx + "");
        postLoad(BaseUrl.anhao, map, callBack);
    }

    /**
     * 平均工资
     */
    public void getpjgzApi(GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        postLoad(BaseUrl.pjgz, map, callBack);
    }

    /**
     * 伤残等级
     */
    public void getgspcApi(int qmarea_id,int scdj,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("qmarea_id", qmarea_id + "");
        map.put("scdj", scdj + "");
        postLoad(BaseUrl.gspc, map, callBack);
    }


    /**
     * 伤残等级
     */
    public void getlsfApi(int df,int fyflag,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("df", df + "");
        map.put("fyflag", fyflag + "");
        postLoad(BaseUrl.lsf, map, callBack);
    }

    /**
     * 创建消息通知
     */
    public void getaddxxtzApi(String theme,String content,String company_id,String glid,
                              String accepter_id,String create_id, String create_name,String create_date,
                              GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("theme", theme + "");
        map.put("content", content + "");
        map.put("company_id", company_id + "");
        map.put("glid", glid + "");
        map.put("accepter_id", accepter_id + "");
        map.put("create_id", create_id + "");
        map.put("create_name", create_name + "");
        map.put("create_date", create_date + "");
        postLoad(BaseUrl.addxxtz, map, callBack);
    }

    /**
     * 消息
     */
    public void getxiaoxiApi(String accepterId,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("accepterId", accepterId + "");
        getLoad(BaseUrl.xiaoxi, map, callBack);
    }

    /**
     * 消息下个页面
     */
    public void getmycasesApi(int id,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id + "");
        getLoad(BaseUrl.mycases, map, callBack);
    }

    /**
     * 查询律师
     */
    public void getclsApi(String id,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("company_id", id + "");
        getLoad(BaseUrl.cls, map, callBack);
    }

    /**
     * 修改律师
     */
    public void postxglsApi(String id,String create_id,String update_date,GetResultCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id + "");
        map.put("create_id", create_id + "");
        map.put("update_date", update_date + "");
        postLoad(BaseUrl.xgls, map, callBack);
    }
}
