package com.example.administrator.qingming.model;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21 0021.
 */

public class ModelLsf {

    /**
     * status : 200
     * message : 获取成功
     * result : [{"id":"16","max":"0.00","fl":"0.00000000","fwsfmin":"500.00","fwsfmax":"1000.00","flmin":"0.00000000","flmax":"0.00000000"},{"id":"17","max":"9999.00","fl":"0.00000000","fwsfmin":"50.00","fwsfmax":"50.00","flmin":"0.00000000","flmax":"0.00000000"},{"id":"18","max":"100000.00","fl":"0.02500000","fwsfmin":"0.00","fwsfmax":"0.00","flmin":"0.00000000","flmax":"0.00000000"},{"id":"19","max":"200000.00","fl":"0.02000000","fwsfmin":"0.00","fwsfmax":"0.00","flmin":"0.00000000","flmax":"0.00000000"},{"id":"20","max":"500000.00","fl":"0.01500000","fwsfmin":"0.00","fwsfmax":"0.00","flmin":"0.00000000","flmax":"0.00000000"},{"id":"21","max":"1000000.00","fl":"0.01000000","fwsfmin":"0.00","fwsfmax":"0.00","flmin":"0.00000000","flmax":"0.00000000"},{"id":"22","max":"2000000.00","fl":"0.00900000","fwsfmin":"0.00","fwsfmax":"0.00","flmin":"0.00000000","flmax":"0.00000000"},{"id":"23","max":"5000000.00","fl":"0.00800000","fwsfmin":"0.00","fwsfmax":"0.00","flmin":"0.00000000","flmax":"0.00000000"},{"id":"24","max":"10000000.00","fl":"0.00700000","fwsfmin":"0.00","fwsfmax":"0.00","flmin":"0.00000000","flmax":"0.00000000"},{"id":"25","max":"20000000.00","fl":"0.00600000","fwsfmin":"0.00","fwsfmax":"0.00","flmin":"0.00000000","flmax":"0.00000000"},{"id":"26","max":"99999999999.00","fl":"0.00500000","fwsfmin":"0.00","fwsfmax":"0.00","flmin":"0.00000000","flmax":"0.00000000"}]
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
         * max : 0.00
         * fl : 0.00000000
         * fwsfmin : 500.00
         * fwsfmax : 1000.00
         * flmin : 0.00000000
         * flmax : 0.00000000
         */

        private int id;
        private double max;
        private double fl;
        private double fwsfmin;
        private double fwsfmax;
        private double flmin;
        private double flmax;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getMax() {
            return max;
        }

        public void setMax(double max) {
            this.max = max;
        }

        public double getFl() {
            return fl;
        }

        public void setFl(double fl) {
            this.fl = fl;
        }

        public double getFwsfmin() {
            return fwsfmin;
        }

        public void setFwsfmin(double fwsfmin) {
            this.fwsfmin = fwsfmin;
        }

        public double getFwsfmax() {
            return fwsfmax;
        }

        public void setFwsfmax(double fwsfmax) {
            this.fwsfmax = fwsfmax;
        }

        public double getFlmin() {
            return flmin;
        }

        public void setFlmin(double flmin) {
            this.flmin = flmin;
        }

        public double getFlmax() {
            return flmax;
        }

        public void setFlmax(double flmax) {
            this.flmax = flmax;
        }
    }
}
