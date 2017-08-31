package com.example.administrator.qingming.model;

import java.util.List;

/**
 * Created by Administrator on 2017/8/21 0021.
 */

public class ModelCity {

    /**
     * status : 200
     * message : 获取成功
     * result : [{"id":"1","parent_id":"0","parent_ids":"0,","code":"1","name":"中国","type":"1"},{"id":"11","parent_id":"1","parent_ids":"0,1,","code":"11","name":"北京市","type":"2"},{"id":"110100","parent_id":"11","parent_ids":"0,1,11,","code":"110100","name":"北京市","type":"3"},{"id":"110101","parent_id":"110100","parent_ids":"0,1,11,110100,","code":"110101","name":"东城区","type":"4"},{"id":"110102","parent_id":"110100","parent_ids":"0,1,11,110100,","code":"110102","name":"西城区","type":"4"},{"id":"110103","parent_id":"110100","parent_ids":"0,1,11,110100,","code":"110103","name":"崇文区","type":"4"},{"id":"110104","parent_id":"110100","parent_ids":"0,1,11,110100,","code":"110104","name":"宣武区","type":"4"},{"id":"110105","parent_id":"110100","parent_ids":"0,1,11,110100,","code":"110105","name":"朝阳区","type":"4"},{"id":"110106","parent_id":"110100","parent_ids":"0,1,11,110100,","code":"110106","name":"丰台区","type":"4"},{"id":"110107","parent_id":"110100","parent_ids":"0,1,11,110100,","code":"110107","name":"石景山区","type":"4"},{"id":"110108","parent_id":"110100","parent_ids":"0,1,11,110100,","code":"110108","name":"海淀区","type":"4"},{"id":"110109","parent_id":"110100","parent_ids":"0,1,11,110100,","code":"110109","name":"门头沟区","type":"4"},{"id":"110111","parent_id":"110100","parent_ids":"0,1,11,110100,","code":"110111","name":"房山区","type":"4"},{"id":"110112","parent_id":"110100","parent_ids":"0,1,11,110100,","code":"110112","name":"通州区","type":"4"},{"id":"110113","parent_id":"110100","parent_ids":"0,1,11,110100,","code":"110113","name":"顺义区","type":"4"},{"id":"110114","parent_id":"110100","parent_ids":"0,1,11,110100,","code":"110114","name":"昌平区","type":"4"},{"id":"110115","parent_id":"110100","parent_ids":"0,1,11,110100,","code":"110115","name":"大兴区","type":"4"},{"id":"110116","parent_id":"110100","parent_ids":"0,1,11,110100,","code":"110116","name":"怀柔区","type":"4"},{"id":"110117","parent_id":"110100","parent_ids":"0,1,11,110100,","code":"110117","name":"平谷区","type":"4"},{"id":"110200","parent_id":"11","parent_ids":"0,1,11,","code":"110200","name":"县","type":"3"}]
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
         * parent_id : 0
         * parent_ids : 0,
         * code : 1
         * name : 中国
         * type : 1
         */

        private String id;
        private String parent_id;
        private String parent_ids;
        private String code;
        private String name;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public String getParent_ids() {
            return parent_ids;
        }

        public void setParent_ids(String parent_ids) {
            this.parent_ids = parent_ids;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
