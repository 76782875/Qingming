package com.example.administrator.qingming.model;

import java.util.List;

/**
 * Created by Administrator on 2017/9/14 0014.
 */

public class ModelSealImage {

    /**
     * status : 200
     * message : 获取成功
     * result : [{"id":"17","dz":"0e4aee48ea7144938a85bc1d73268b6f/gz/20170728112707.png","gz_lx":"1"}]
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
         * dz : 0e4aee48ea7144938a85bc1d73268b6f/gz/20170728112707.png
         * gz_lx : 1
         */

        private String id;
        private String dz;
        private String gz_lx;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDz() {
            return dz;
        }

        public void setDz(String dz) {
            this.dz = dz;
        }

        public String getGz_lx() {
            return gz_lx;
        }

        public void setGz_lx(String gz_lx) {
            this.gz_lx = gz_lx;
        }
    }
}
