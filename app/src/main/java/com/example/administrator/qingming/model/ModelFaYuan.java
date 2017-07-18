package com.example.administrator.qingming.model;

import java.util.List;

/**
 * Created by Administrator on 2017/7/6 0006.
 */

public class ModelFaYuan {

    /**
     * status : 200
     * message : 获取成功
     * result : [{"id":"30","spcx":"56456","spjg":"","ladate":"2017-07-06 00:00:00","ktdate":"2017-07-06 00:00:00","spdate":"2017-07-06 00:00:00","ssdate":"2017-07-06 00:00:00","ft":"null","zsfg":"null","zsfgtel":"null","sjy":"null","sjytel":"null","bzsm2":null,"update_zt":"1","wtr":"张杰","ah_number":"2017(民)第 0071 号"},{"id":"31","spcx":"是","spjg":"65445","ladate":"2017-07-06 00:00:00","ktdate":"2017-07-06 00:00:00","spdate":"2017-07-06 00:00:00","ssdate":"2017-07-06 00:00:00","ft":"null","zsfg":"null","zsfgtel":"null","sjy":"null","sjytel":"null","bzsm2":null,"update_zt":"1","wtr":"张杰","ah_number":"2017(民)第 0071 号"},{"id":"32","spcx":"2017-7-6 ","spjg":"发","ladate":"2017-07-06 00:00:00","ktdate":"2017-07-06 00:00:00","spdate":"2017-07-06 00:00:00","ssdate":"2017-07-06 00:00:00","ft":"发","zsfg":"发","zsfgtel":"5456465","sjy":"发","sjytel":"5656","bzsm2":"45656465","update_zt":"1","wtr":"张杰","ah_number":"2017(民)第 0071 号"},{"id":"33","spcx":"台式机和我","spjg":"公司","ladate":"2017-07-06 00:00:00","ktdate":"2017-07-06 00:00:00","spdate":"2017-07-06 00:00:00","ssdate":"2017-07-06 00:00:00","ft":"噶","zsfg":"65456","zsfgtel":"干撒","sjy":"干撒","sjytel":"65654","bzsm2":"噶","update_zt":"1","wtr":"张杰","ah_number":"2017(民)第 0071 号"}]
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
         * id : 30
         * spcx : 56456
         * spjg :
         * ladate : 2017-07-06 00:00:00
         * ktdate : 2017-07-06 00:00:00
         * spdate : 2017-07-06 00:00:00
         * ssdate : 2017-07-06 00:00:00
         * ft : null
         * zsfg : null
         * zsfgtel : null
         * sjy : null
         * sjytel : null
         * bzsm2 : null
         * update_zt : 1
         * wtr : 张杰
         * ah_number : 2017(民)第 0071 号
         */

        private String id;
        private String spcx;
        private String spjg;
        private String ladate;
        private String ktdate;
        private String spdate;
        private String ssdate;
        private String ft;
        private String zsfg;
        private String zsfgtel;
        private String sjy;
        private String sjytel;
        private String bzsm2;
        private String update_zt;
        private String wtr;
        private String ah_number;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSpcx() {
            return spcx;
        }

        public void setSpcx(String spcx) {
            this.spcx = spcx;
        }

        public String getSpjg() {
            return spjg;
        }

        public void setSpjg(String spjg) {
            this.spjg = spjg;
        }

        public String getLadate() {
            return ladate;
        }

        public void setLadate(String ladate) {
            this.ladate = ladate;
        }

        public String getKtdate() {
            return ktdate;
        }

        public void setKtdate(String ktdate) {
            this.ktdate = ktdate;
        }

        public String getSpdate() {
            return spdate;
        }

        public void setSpdate(String spdate) {
            this.spdate = spdate;
        }

        public String getSsdate() {
            return ssdate;
        }

        public void setSsdate(String ssdate) {
            this.ssdate = ssdate;
        }

        public String getFt() {
            return ft;
        }

        public void setFt(String ft) {
            this.ft = ft;
        }

        public String getZsfg() {
            return zsfg;
        }

        public void setZsfg(String zsfg) {
            this.zsfg = zsfg;
        }

        public String getZsfgtel() {
            return zsfgtel;
        }

        public void setZsfgtel(String zsfgtel) {
            this.zsfgtel = zsfgtel;
        }

        public String getSjy() {
            return sjy;
        }

        public void setSjy(String sjy) {
            this.sjy = sjy;
        }

        public String getSjytel() {
            return sjytel;
        }

        public void setSjytel(String sjytel) {
            this.sjytel = sjytel;
        }

        public String getBzsm2() {
            return bzsm2;
        }

        public void setBzsm2(String bzsm2) {
            this.bzsm2 = bzsm2;
        }

        public String getUpdate_zt() {
            return update_zt;
        }

        public void setUpdate_zt(String update_zt) {
            this.update_zt = update_zt;
        }

        public String getWtr() {
            return wtr;
        }

        public void setWtr(String wtr) {
            this.wtr = wtr;
        }

        public String getAh_number() {
            return ah_number;
        }

        public void setAh_number(String ah_number) {
            this.ah_number = ah_number;
        }
    }
}
