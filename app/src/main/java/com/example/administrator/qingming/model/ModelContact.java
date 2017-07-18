package com.example.administrator.qingming.model;

import java.util.List;

/**
 * Created by Administrator on 2017/6/3 0003.
 */

public class ModelContact {

    /**
     * status : 200
     * message : 获取成功
     * result : [{"id":"30","name":"谭小刀","ssjg":"铭轩科技发放的沙发沙发斯蒂芬"},{"id":"31","name":"刘欢","ssjg":"铭轩科技"}]
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
         * id : 30
         * name : 谭小刀
         * ssjg : 铭轩科技发放的沙发沙发斯蒂芬
         */

        private String id;
        private String name;
        private String ssjg;
        private String sortLetters;  //显示数据拼音的首字母

        public String getSortLetters() {
            return sortLetters;
        }
        public void setSortLetters(String sortLetters) {
            this.sortLetters = sortLetters;
        }

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

        public String getSsjg() {
            return ssjg;
        }

        public void setSsjg(String ssjg) {
            this.ssjg = ssjg;
        }
    }
}
