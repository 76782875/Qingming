package com.example.administrator.qingming.model;

import java.util.List;

/**
 * Created by Administrator on 2017/6/30 0030.
 */

public class ChargeListModel {

    /**
     * status : 200
     * message : 获取成功
     * result : [{"fylx":"0","sfje":"0.00","create_time":"2017-06-30 05:59:11","sflx":"0","zt":"1","audit_name":null}]
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
         * fylx : 0
         * sfje : 0.00
         * create_time : 2017-06-30 05:59:11
         * sflx : 0
         * zt : 1
         * audit_name : null
         */

        private String fylx;
        private double sfje;
        private String create_time;
        private String sflx;
        private String zt;
        private String audit_name;

        public String getFylx() {
            return fylx;
        }

        public void setFylx(String fylx) {
            this.fylx = fylx;
        }

        public double getSfje() {
            return sfje;
        }

        public void setSfje(double sfje) {
            this.sfje = sfje;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getSflx() {
            return sflx;
        }

        public void setSflx(String sflx) {
            this.sflx = sflx;
        }

        public String getZt() {
            return zt;
        }

        public void setZt(String zt) {
            this.zt = zt;
        }

        public String getAudit_name() {
            return audit_name;
        }

        public void setAudit_name(String audit_name) {
            this.audit_name = audit_name;
        }
    }
}
