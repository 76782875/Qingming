package com.example.administrator.qingming.model;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21 0021.
 */

public class ModelScdj {

    /**
     * status : 200
     * message : 获取成功
     * result : [{"id":"1","qmarea_id":"1","scdj":"1","wqhl":"0.50","dbfhl":"0.40","bfhl":"0.30","pch":"0.00","pca":"27.00","pcb":"0.90","pcc":"0.00","pcd":"0.00","pce":"0.00","pcf":"0.00","pcg":"0.00"}]
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
         * id : 1
         * qmarea_id : 1
         * scdj : 1
         * wqhl : 0.50
         * dbfhl : 0.40
         * bfhl : 0.30
         * pch : 0.00
         * pca : 27.00
         * pcb : 0.90
         * pcc : 0.00
         * pcd : 0.00
         * pce : 0.00
         * pcf : 0.00
         * pcg : 0.00
         */

        private int id;
        private double wqhl;
        private double dbfhl;
        private double bfhl;
        private double pch;
        private double pca;
        private double pcb;
        private double pcc;
        private double pcd;
        private double pce;
        private double pcf;
        private double pcg;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getWqhl() {
            return wqhl;
        }

        public void setWqhl(double wqhl) {
            this.wqhl = wqhl;
        }

        public double getDbfhl() {
            return dbfhl;
        }

        public void setDbfhl(double dbfhl) {
            this.dbfhl = dbfhl;
        }

        public double getBfhl() {
            return bfhl;
        }

        public void setBfhl(double bfhl) {
            this.bfhl = bfhl;
        }

        public double getPch() {
            return pch;
        }

        public void setPch(double pch) {
            this.pch = pch;
        }

        public double getPca() {
            return pca;
        }

        public void setPca(double pca) {
            this.pca = pca;
        }

        public double getPcb() {
            return pcb;
        }

        public void setPcb(double pcb) {
            this.pcb = pcb;
        }

        public double getPcc() {
            return pcc;
        }

        public void setPcc(double pcc) {
            this.pcc = pcc;
        }

        public double getPcd() {
            return pcd;
        }

        public void setPcd(double pcd) {
            this.pcd = pcd;
        }

        public double getPce() {
            return pce;
        }

        public void setPce(double pce) {
            this.pce = pce;
        }

        public double getPcf() {
            return pcf;
        }

        public void setPcf(double pcf) {
            this.pcf = pcf;
        }

        public double getPcg() {
            return pcg;
        }

        public void setPcg(double pcg) {
            this.pcg = pcg;
        }
    }
}
