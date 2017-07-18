package com.example.administrator.qingming.model;

import java.util.List;

/**
 * Created by Administrator on 2017/7/6 0006.
 */

public class ModelJiancha {

    /**
     * status : 200
     * message : 获取成功
     * result : [{"id":"17","bajg":"f安抚","badd":"法法","bzsm":"本级跑","scqs_star":"2017-07-06 00:00:00","scqs_end":"2017-07-06 00:00:00","bm":" 法法","cbr":"法法","create_date":"2017-06-06 00:00:00","tel":"15823903420","ah_number":"2017(民)第 0071 号"}]
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
         * id : 17
         * bajg : f安抚
         * badd : 法法
         * bzsm : 本级跑
         * scqs_star : 2017-07-06 00:00:00
         * scqs_end : 2017-07-06 00:00:00
         * bm :  法法
         * cbr : 法法
         * create_date : 2017-06-06 00:00:00
         * tel : 15823903420
         * ah_number : 2017(民)第 0071 号
         */

        private String id;
        private String bajg;
        private String badd;
        private String bzsm;
        private String scqs_star;
        private String scqs_end;
        private String bm;
        private String cbr;
        private String create_date;
        private String tel;
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

        public String getBadd() {
            return badd;
        }

        public void setBadd(String badd) {
            this.badd = badd;
        }

        public String getBzsm() {
            return bzsm;
        }

        public void setBzsm(String bzsm) {
            this.bzsm = bzsm;
        }

        public String getScqs_star() {
            return scqs_star;
        }

        public void setScqs_star(String scqs_star) {
            this.scqs_star = scqs_star;
        }

        public String getScqs_end() {
            return scqs_end;
        }

        public void setScqs_end(String scqs_end) {
            this.scqs_end = scqs_end;
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

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getAh_number() {
            return ah_number;
        }

        public void setAh_number(String ah_number) {
            this.ah_number = ah_number;
        }
    }
}
