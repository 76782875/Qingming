package com.example.administrator.qingming.model;

import java.util.List;

/**
 * Created by Administrator on 2017/6/19 0019.
 */

public class PersonalDataModel {

    /**
     * status : 200
     * message : 获取成功
     * result : [{"id":"1","name1":"后台管理员","email":"thinkgem@163.com","mobile":"15202364444","remarks":"最高管理员","name":"铭轩科技"}]
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
         * name1 : 后台管理员
         * email : thinkgem@163.com
         * mobile : 15202364444
         * remarks : 最高管理员
         * name : 铭轩科技
         */

        private String id;
        private String name1;
        private String email;
        private String mobile;
        private String remarks;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName1() {
            return name1;
        }

        public void setName1(String name1) {
            this.name1 = name1;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
