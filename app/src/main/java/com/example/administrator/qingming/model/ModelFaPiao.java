package com.example.administrator.qingming.model;

import java.util.List;

/**
 * Created by Administrator on 2017/8/28 0028.
 */

public class ModelFaPiao {

    /**
     * status : 200
     * message : 获取成功
     * result : [{"id":"16","ah_id":"113","sqr":"ssss","fptt":"ssss","kpje":"3333","kpxm":"0","kjlx":"1","fplx":"0","nsrsbh":"","jbhkhyh":"","jbhkhzh":"","zccsdz":"","zcgddh":"","sjr":"","sjdz":"","lxdh":"","sqbz":"","fph":"3333","kprq":"2017-07-27 00:00:00","kpbz":"","zt":"0","create_id":"b306900362b54ac78d9a987db793785b","create_name":null,"create_time":null,"ah_num":"2017(民)第001号","wtr":"韦淋","dlf":"1","ay":"吃鸡"},{"id":"15","ah_id":"112","sqr":"沈元","fptt":"啦啦啦啦","kpje":"50000","kpxm":"2","kjlx":"1","fplx":"2","nsrsbh":"","jbhkhyh":"","jbhkhzh":"","zccsdz":"","zcgddh":"","sjr":"","sjdz":"","lxdh":"","sqbz":"","fph":"2222222222222","kprq":"2017-07-11 09:44:22","kpbz":"","zt":"0","create_id":"b306900362b54ac78d9a987db793785b","create_name":null,"create_time":null,"ah_num":"2017(刑)第001号","wtr":"ssss","dlf":"555","ay":"sss"}]
     */

    private int status;
    private String message;
    private List<ResultBean> result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 16
         * ah_id : 113
         * sqr : ssss
         * fptt : ssss
         * kpje : 3333
         * kpxm : 0
         * kjlx : 1
         * fplx : 0
         * nsrsbh :
         * jbhkhyh :
         * jbhkhzh :
         * zccsdz :
         * zcgddh :
         * sjr :
         * sjdz :
         * lxdh :
         * sqbz :
         * fph : 3333
         * kprq : 2017-07-27 00:00:00
         * kpbz :
         * zt : 0
         * create_id : b306900362b54ac78d9a987db793785b
         * create_name : null
         * create_time : null
         * ah_num : 2017(民)第001号
         * wtr : 韦淋
         * dlf : 1
         * ay : 吃鸡
         */

        private String id;
        private String ah_id;
        private String sqr;
        private String fptt;
        private String kpje;
        private String kpxm;
        private String kjlx;
        private String fplx;
        private String nsrsbh;
        private String jbhkhyh;
        private String jbhkhzh;
        private String zccsdz;
        private String zcgddh;
        private String sjr;
        private String sjdz;
        private String lxdh;
        private String sqbz;
        private String fph;
        private String kprq;
        private String kpbz;
        private String zt;
        private String create_id;
        private Object create_name;
        private Object create_time;
        private String ah_num;
        private String wtr;
        private String dlf;
        private String ay;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAh_id() {
            return ah_id;
        }

        public void setAh_id(String ah_id) {
            this.ah_id = ah_id;
        }

        public String getSqr() {
            return sqr;
        }

        public void setSqr(String sqr) {
            this.sqr = sqr;
        }

        public String getFptt() {
            return fptt;
        }

        public void setFptt(String fptt) {
            this.fptt = fptt;
        }

        public String getKpje() {
            return kpje;
        }

        public void setKpje(String kpje) {
            this.kpje = kpje;
        }

        public String getKpxm() {
            return kpxm;
        }

        public void setKpxm(String kpxm) {
            this.kpxm = kpxm;
        }

        public String getKjlx() {
            return kjlx;
        }

        public void setKjlx(String kjlx) {
            this.kjlx = kjlx;
        }

        public String getFplx() {
            return fplx;
        }

        public void setFplx(String fplx) {
            this.fplx = fplx;
        }

        public String getNsrsbh() {
            return nsrsbh;
        }

        public void setNsrsbh(String nsrsbh) {
            this.nsrsbh = nsrsbh;
        }

        public String getJbhkhyh() {
            return jbhkhyh;
        }

        public void setJbhkhyh(String jbhkhyh) {
            this.jbhkhyh = jbhkhyh;
        }

        public String getJbhkhzh() {
            return jbhkhzh;
        }

        public void setJbhkhzh(String jbhkhzh) {
            this.jbhkhzh = jbhkhzh;
        }

        public String getZccsdz() {
            return zccsdz;
        }

        public void setZccsdz(String zccsdz) {
            this.zccsdz = zccsdz;
        }

        public String getZcgddh() {
            return zcgddh;
        }

        public void setZcgddh(String zcgddh) {
            this.zcgddh = zcgddh;
        }

        public String getSjr() {
            return sjr;
        }

        public void setSjr(String sjr) {
            this.sjr = sjr;
        }

        public String getSjdz() {
            return sjdz;
        }

        public void setSjdz(String sjdz) {
            this.sjdz = sjdz;
        }

        public String getLxdh() {
            return lxdh;
        }

        public void setLxdh(String lxdh) {
            this.lxdh = lxdh;
        }

        public String getSqbz() {
            return sqbz;
        }

        public void setSqbz(String sqbz) {
            this.sqbz = sqbz;
        }

        public String getFph() {
            return fph;
        }

        public void setFph(String fph) {
            this.fph = fph;
        }

        public String getKprq() {
            return kprq;
        }

        public void setKprq(String kprq) {
            this.kprq = kprq;
        }

        public String getKpbz() {
            return kpbz;
        }

        public void setKpbz(String kpbz) {
            this.kpbz = kpbz;
        }

        public String getZt() {
            return zt;
        }

        public void setZt(String zt) {
            this.zt = zt;
        }

        public String getCreate_id() {
            return create_id;
        }

        public void setCreate_id(String create_id) {
            this.create_id = create_id;
        }

        public Object getCreate_name() {
            return create_name;
        }

        public void setCreate_name(Object create_name) {
            this.create_name = create_name;
        }

        public Object getCreate_time() {
            return create_time;
        }

        public void setCreate_time(Object create_time) {
            this.create_time = create_time;
        }

        public String getAh_num() {
            return ah_num;
        }

        public void setAh_num(String ah_num) {
            this.ah_num = ah_num;
        }

        public String getWtr() {
            return wtr;
        }

        public void setWtr(String wtr) {
            this.wtr = wtr;
        }

        public String getDlf() {
            return dlf;
        }

        public void setDlf(String dlf) {
            this.dlf = dlf;
        }

        public String getAy() {
            return ay;
        }

        public void setAy(String ay) {
            this.ay = ay;
        }
    }
}
