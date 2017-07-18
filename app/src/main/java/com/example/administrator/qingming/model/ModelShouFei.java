package com.example.administrator.qingming.model;

import java.util.List;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public class ModelShouFei {

    /**
     * status : 200
     * message : 获取成功
     * result : [{"fylx":"2","sfje":"65546.00","sflx":"1","zt":"1","audit_name":null,"audit_time":null,"bz":"","ah_number":"2017(民)第 0071 号"}]
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
         * id : 71
         * fylx : 2
         * sfje : 65546.00
         * sflx : 1
         * zt : 1
         * audit_name : null
         * audit_time : null
         * bz :
         * ah_number : 2017(民)第 0071 号
         */

        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        private String fylx;
        private double sfje;
        private String sflx;
        private String zt;
        private String audit_name;
        private String audit_time;
        private String bz;
        private String ah_number;

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

        public String getAudit_time() {
            return audit_time;
        }

        public void setAudit_time(String audit_time) {
            this.audit_time = audit_time;
        }

        public String getBz() {
            return bz;
        }

        public void setBz(String bz) {
            this.bz = bz;
        }

        public String getAh_number() {
            return ah_number;
        }

        public void setAh_number(String ah_number) {
            this.ah_number = ah_number;
        }
    }

}
