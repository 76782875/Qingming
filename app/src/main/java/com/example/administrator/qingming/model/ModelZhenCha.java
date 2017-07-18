package com.example.administrator.qingming.model;

import java.util.List;

/**
 * Created by Administrator on 2017/7/5 0005.
 */

public class ModelZhenCha {

    /**
     * status : 200
     * message : 获取成功
     * result : [{"id":"26","bajg":"公安局","bzsm":"7755","zcah":"367","bm":"5894","cbr":"535","tel":"5245","xjsj":"2017-07-05 16:09:00","dbsj":"2017-07-05 16:09:00","zcqx_star":"2017-07-05 16:04:00","zcqx_end":"2017-07-05 16:04:00","bczcqx_star":"2017-07-05 16:04:00","bczcqx_end":"2017-07-05 16:04:00","ecbczc_star":"2017-07-05 16:04:00","ecbczc_end":"2017-07-05 16:04:00","ah_number":"2017(民)第 0071 号"},{"id":"27","bajg":"公安局","bzsm":"","zcah":"654","bm":"321","cbr":"61","tel":"54654","xjsj":null,"dbsj":null,"zcqx_star":null,"zcqx_end":null,"bczcqx_star":null,"bczcqx_end":null,"ecbczc_star":null,"ecbczc_end":null,"ah_number":"2017(民)第 0071 号"},{"id":"28","bajg":"公安局","bzsm":"","zcah":"62564","bm":"656","cbr":"351","tel":"65165465","xjsj":null,"dbsj":null,"zcqx_star":null,"zcqx_end":null,"bczcqx_star":null,"bczcqx_end":null,"ecbczc_star":null,"ecbczc_end":null,"ah_number":"2017(民)第 0071 号"},{"id":"29","bajg":"反贪局","bzsm":"","zcah":"654","bm":"6546","cbr":"654","tel":"65465","xjsj":"2017-07-05 00:00:00","dbsj":null,"zcqx_star":null,"zcqx_end":null,"bczcqx_star":null,"bczcqx_end":null,"ecbczc_star":null,"ecbczc_end":null,"ah_number":"2017(民)第 0071 号"},{"id":"30","bajg":"公安局","bzsm":"","zcah":"654","bm":"656","cbr":"54","tel":"65465","xjsj":"2017-07-05 00:00:00","dbsj":"2017-07-05 00:00:00","zcqx_star":"2017-07-05 00:00:00","zcqx_end":"2017-07-05 00:00:00","bczcqx_star":null,"bczcqx_end":null,"ecbczc_star":null,"ecbczc_end":null,"ah_number":"2017(民)第 0071 号"}]
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
         * id : 26
         * bajg : 公安局
         * bzsm : 7755
         * zcah : 367
         * bm : 5894
         * cbr : 535
         * tel : 5245
         * xjsj : 2017-07-05 16:09:00
         * dbsj : 2017-07-05 16:09:00
         * zcqx_star : 2017-07-05 16:04:00
         * zcqx_end : 2017-07-05 16:04:00
         * bczcqx_star : 2017-07-05 16:04:00
         * bczcqx_end : 2017-07-05 16:04:00
         * ecbczc_star : 2017-07-05 16:04:00
         * ecbczc_end : 2017-07-05 16:04:00
         * ah_number : 2017(民)第 0071 号
         */

        private String id;
        private String bajg;
        private String bzsm;
        private String zcah;
        private String bm;
        private String cbr;
        private String tel;
        private String xjsj;
        private String dbsj;
        private String zcqx_star;
        private String zcqx_end;
        private String bczcqx_star;
        private String bczcqx_end;
        private String ecbczc_star;
        private String ecbczc_end;
        private String ah_number;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBajg() {
            return bajg;
        }

        public void setBajg(String bajg) {
            this.bajg = bajg;
        }

        public String getBzsm() {
            return bzsm;
        }

        public void setBzsm(String bzsm) {
            this.bzsm = bzsm;
        }

        public String getZcah() {
            return zcah;
        }

        public void setZcah(String zcah) {
            this.zcah = zcah;
        }

        public String getBm() {
            return bm;
        }

        public void setBm(String bm) {
            this.bm = bm;
        }

        public String getCbr() {
            return cbr;
        }

        public void setCbr(String cbr) {
            this.cbr = cbr;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getXjsj() {
            return xjsj;
        }

        public void setXjsj(String xjsj) {
            this.xjsj = xjsj;
        }

        public String getDbsj() {
            return dbsj;
        }

        public void setDbsj(String dbsj) {
            this.dbsj = dbsj;
        }

        public String getZcqx_star() {
            return zcqx_star;
        }

        public void setZcqx_star(String zcqx_star) {
            this.zcqx_star = zcqx_star;
        }

        public String getZcqx_end() {
            return zcqx_end;
        }

        public void setZcqx_end(String zcqx_end) {
            this.zcqx_end = zcqx_end;
        }

        public String getBczcqx_star() {
            return bczcqx_star;
        }

        public void setBczcqx_star(String bczcqx_star) {
            this.bczcqx_star = bczcqx_star;
        }

        public String getBczcqx_end() {
            return bczcqx_end;
        }

        public void setBczcqx_end(String bczcqx_end) {
            this.bczcqx_end = bczcqx_end;
        }

        public String getEcbczc_star() {
            return ecbczc_star;
        }

        public void setEcbczc_star(String ecbczc_star) {
            this.ecbczc_star = ecbczc_star;
        }

        public String getEcbczc_end() {
            return ecbczc_end;
        }

        public void setEcbczc_end(String ecbczc_end) {
            this.ecbczc_end = ecbczc_end;
        }

        public String getAh_number() {
            return ah_number;
        }

        public void setAh_number(String ah_number) {
            this.ah_number = ah_number;
        }
    }
}
