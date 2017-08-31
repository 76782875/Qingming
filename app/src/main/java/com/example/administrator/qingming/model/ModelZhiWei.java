package com.example.administrator.qingming.model;

import java.util.List;

/**
 * Created by Administrator on 2017/8/24 0024.
 */

public class ModelZhiWei {

    /**
     * status : 200
     * message : 获取成功
     * result : [{"id":"40819564ae4f4ceca27739a0c3b9c1ca","name":"主任","office_id":"1","limits":"1"}]
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
         * id : 40819564ae4f4ceca27739a0c3b9c1ca
         * name : 主任
         * office_id : 1
         * limits : 1
         */

        private String id;
        private String name;
        private String office_id;
        private String limits;

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

        public String getOffice_id() {
            return office_id;
        }

        public void setOffice_id(String office_id) {
            this.office_id = office_id;
        }

        public String getLimits() {
            return limits;
        }

        public void setLimits(String limits) {
            this.limits = limits;
        }
    }
}
