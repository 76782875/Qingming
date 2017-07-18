package com.example.administrator.qingming.model;

import java.util.List;

/**
 * Created by Administrator on 2017/5/22 0022.
 */

public class MyCaseModel {

    /**
     * status : 200
     * message : 获取成功
     * result : [{"id":"107","case_state":"1","ay":"sdfh","ah_number":"2017(民)第 0071 号","wtr":"sgs","dfdsr":"sgs","sffs":null,"dlf":"5156","jzf":null,"name":"嫖老师","sarq":"2017-06-28 00:00:00"},{"id":"109","case_state":"1","ay":"jd","ah_number":"2017(民)第 0071 号","wtr":"jdj","dfdsr":"djd","sffs":null,"dlf":"54","jzf":null,"name":"嫖老师","sarq":"2017-06-30 00:00:00"},{"id":"110","case_state":"4","ay":"626","ah_number":"2017(民)第 0071 号","wtr":"5215","dfdsr":"654","sffs":null,"dlf":"654","jzf":null,"name":"嫖老师","sarq":"2017-06-30 00:00:00"}]
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
         * id : 107
         * case_state : 1
         * ay : sdfh
         * ah_number : 2017(民)第 0071 号
         * wtr : sgs
         * dfdsr : sgs
         * sffs : null
         * dlf : 5156
         * jzf : null
         * name : 嫖老师
         * sarq : 2017-06-28 00:00:00
         */

        private String id;
        private String case_state;
        private String ay;
        private String ah_number;
        private String wtr;
        private String dfdsr;
        private String sffs;
        private int dlf;
        private int jzf;
        private String name;
        private String sarq;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCase_state() {
            return case_state;
        }

        public void setCase_state(String case_state) {
            this.case_state = case_state;
        }

        public String getAy() {
            return ay;
        }

        public void setAy(String ay) {
            this.ay = ay;
        }

        public String getAh_number() {
            return ah_number;
        }

        public void setAh_number(String ah_number) {
            this.ah_number = ah_number;
        }

        public String getWtr() {
            return wtr;
        }

        public void setWtr(String wtr) {
            this.wtr = wtr;
        }

        public String getDfdsr() {
            return dfdsr;
        }

        public void setDfdsr(String dfdsr) {
            this.dfdsr = dfdsr;
        }

        public String getSffs() {
            return sffs;
        }

        public void setSffs(String sffs) {
            this.sffs = sffs;
        }

        public int getDlf() {
            return dlf;
        }

        public void setDlf(int dlf) {
            this.dlf = dlf;
        }

        public int getJzf() {
            return jzf;
        }

        public void setJzf(int jzf) {
            this.jzf = jzf;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSarq() {
            return sarq;
        }

        public void setSarq(String sarq) {
            this.sarq = sarq;
        }
    }
}
