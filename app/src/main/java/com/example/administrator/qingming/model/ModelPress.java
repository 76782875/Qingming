package com.example.administrator.qingming.model;

import java.util.List;

/**
 * Created by Administrator on 2017/6/2 0002.
 */

public class ModelPress {

    /**
     * status : 200
     * message : 获取成功
     * result : [{"id":"15","product_brand":"14","category_id":null,"product_name":"测试新闻列表","insert_time":"2017-07-14 11:08:05","insert_id":"b306900362b54ac78d9a987db793785b","insert_name":"测试主任"}]
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
         * id : 15
         * product_brand : 14
         * category_id : null
         * product_name : 测试新闻列表
         * insert_time : 2017-07-14 11:08:05
         * insert_id : b306900362b54ac78d9a987db793785b
         * insert_name : 测试主任
         */

        private String id;
        private String product_brand;
        private Object category_id;
        private String product_name;
        private String insert_time;
        private String insert_id;
        private String insert_name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProduct_brand() {
            return product_brand;
        }

        public void setProduct_brand(String product_brand) {
            this.product_brand = product_brand;
        }

        public Object getCategory_id() {
            return category_id;
        }

        public void setCategory_id(Object category_id) {
            this.category_id = category_id;
        }

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public String getInsert_time() {
            return insert_time;
        }

        public void setInsert_time(String insert_time) {
            this.insert_time = insert_time;
        }

        public String getInsert_id() {
            return insert_id;
        }

        public void setInsert_id(String insert_id) {
            this.insert_id = insert_id;
        }

        public String getInsert_name() {
            return insert_name;
        }

        public void setInsert_name(String insert_name) {
            this.insert_name = insert_name;
        }
    }
}
