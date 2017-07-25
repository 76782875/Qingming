package com.example.administrator.qingming.model;

import java.util.List;

/**
 * Created by Administrator on 2017/7/20 0020.
 */

public class ModelPjgz {

    /**
     * status : 200
     * message : 获取成功
     * result : [{"id":"0","area_name":"全国","avg_wag":"2801"},{"id":"1","area_name":"重庆","avg_wag":"5426"}]
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
         * id : 0
         * area_name : 全国
         * avg_wag : 2801
         */

        private String id;
        private String area_name;
        private int avg_wag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public int getAvg_wag() {
            return avg_wag;
        }

        public void setAvg_wag(int avg_wag) {
            this.avg_wag = avg_wag;
        }
    }
}
