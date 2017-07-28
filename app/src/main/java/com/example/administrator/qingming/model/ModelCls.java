package com.example.administrator.qingming.model;

import java.util.List;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class ModelCls {

    /**
     * status : 200
     * message : 获取成功
     * result : [{"id":"85859ab4fce04d4382faf102d51db2e7","name":"测试律师"},{"id":"b306900362b54ac78d9a987db793785b","name":"测试主任"},{"id":"b9b1d38183cf4cefb97935437036a4ec","name":"测试副主任律师"},{"id":"f51026e7ae9447838c0b31c3bec57bb1","name":"测试财务"},{"id":"f9c62dd8399249fe914e7f7dbb7a0eee","name":"wcs"}]
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
         * id : 85859ab4fce04d4382faf102d51db2e7
         * name : 测试律师
         */

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
