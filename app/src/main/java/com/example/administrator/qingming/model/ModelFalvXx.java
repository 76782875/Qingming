package com.example.administrator.qingming.model;

import java.util.List;

/**
 * Created by Administrator on 2017/7/26 0026.
 */

public class ModelFalvXx {

    /**
     * status : 200
     * message : 获取成功
     * result : [{"id":"47","spcx":"+656+","spjg":"68798789","ladate":"2017-07-26 00:00:00","ktdate":"2017-07-26 00:00:00","spdate":"2017-07-26 00:00:00","ssdate":"2017-07-26 00:00:00","ft":"654654","zsfg":"5465","zsfgtel":"684684","sjy":"65465","sjytel":"654684","bzsm2":"ceshia jhyug","zt":"1","wtr":"韦淋","ah_number":"2017(刑)第007号","ay":"又杀错人","dfdsr":"曾夕"},{"id":"45","spcx":"ss","spjg":"ss","ladate":"2017-07-25 00:00:00","ktdate":"2017-07-25 00:00:00","spdate":"2017-07-25 00:00:00","ssdate":"2017-07-25 00:00:00","ft":"ss","zsfg":"","zsfgtel":"","sjy":"","sjytel":"","bzsm2":"","zt":"1","wtr":"sy","ah_number":"2017(民)第057号","ay":"sy","dfdsr":"sy"},{"id":"46","spcx":"adsfa","spjg":"dfasc","ladate":"2017-07-25 00:00:00","ktdate":"2017-07-25 00:00:00","spdate":"2017-07-25 00:00:00","ssdate":"2017-07-25 00:00:00","ft":"zxv","zsfg":"","zsfgtel":"","sjy":"","sjytel":"","bzsm2":"","zt":"1","wtr":"dd","ah_number":"2017(非)第002号","ay":"dd","dfdsr":"dd"},{"id":"44","spcx":"撒打发斯蒂芬","spjg":"","ladate":"2017-07-11 00:00:00","ktdate":"2017-07-11 10:41:00","spdate":"2017-07-11 10:41:00","ssdate":"2017-07-11 00:00:00","ft":"发到付","zsfg":"","zsfgtel":"","sjy":"","sjytel":"","bzsm2":"","zt":"2","wtr":"asdf","ah_number":"2017(产)第001号","ay":"asdf","dfdsr":"asdf"},{"id":"43","spcx":"大幅度","spjg":"反调","ladate":"2017-07-10 00:00:00","ktdate":"2017-07-10 17:01:00","spdate":"2017-07-10 17:01:00","ssdate":"2017-07-10 00:00:00","ft":"","zsfg":"","zsfgtel":"","sjy":"","sjytel":"","bzsm2":" ","zt":"2","wtr":"™","ah_number":"2017(民)第001号","ay":"小屁孩·","dfdsr":"到的"},{"id":"41","spcx":"案件一审判决","spjg":"重庆涪陵区人民法院","ladate":"2017-07-10 00:00:00","ktdate":"2017-07-10 16:42:00","spdate":"2017-07-10 16:42:00","ssdate":"2017-07-10 00:00:00","ft":"重庆涪陵区人民法院","zsfg":"刘XX","zsfgtel":"18518518518","sjy":"淋XX","sjytel":"17317317317","bzsm2":"无 ","zt":"2","wtr":"测试","ah_number":"2017(民)第013号","ay":"测试","dfdsr":"测试"},{"id":"42","spcx":"123","spjg":"23","ladate":"2017-07-10 00:00:00","ktdate":"2017-07-10 16:00:00","spdate":"2017-07-10 16:58:00","ssdate":"2017-07-10 00:00:00","ft":"213","zsfg":"","zsfgtel":"","sjy":"","sjytel":"","bzsm2":"  ","zt":"2","wtr":"测试","ah_number":"2017(民)第013号","ay":"测试","dfdsr":"测试"}]
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
         * id : 47
         * spcx : +656+
         * spjg : 68798789
         * ladate : 2017-07-26 00:00:00
         * ktdate : 2017-07-26 00:00:00
         * spdate : 2017-07-26 00:00:00
         * ssdate : 2017-07-26 00:00:00
         * ft : 654654
         * zsfg : 5465
         * zsfgtel : 684684
         * sjy : 65465
         * sjytel : 654684
         * bzsm2 : ceshia jhyug
         * zt : 1
         * wtr : 韦淋
         * ah_number : 2017(刑)第007号
         * ay : 又杀错人
         * dfdsr : 曾夕
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
        private String zt;
        private String wtr;
        private String ah_number;
        private String ay;
        private String dfdsr;
        private String glid;

        public String getGlid() {
            return glid;
        }

        public void setGlid(String glid) {
            this.glid = glid;
        }

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

        public String getZt() {
            return zt;
        }

        public void setZt(String zt) {
            this.zt = zt;
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

        public String getAy() {
            return ay;
        }

        public void setAy(String ay) {
            this.ay = ay;
        }

        public String getDfdsr() {
            return dfdsr;
        }

        public void setDfdsr(String dfdsr) {
            this.dfdsr = dfdsr;
        }
    }
}
