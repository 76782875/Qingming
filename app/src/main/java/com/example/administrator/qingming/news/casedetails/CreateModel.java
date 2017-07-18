package com.example.administrator.qingming.news.casedetails;

import java.util.List;

/**
 * Created by Administrator on 2017/5/17 0017.
 */

public class CreateModel {

    /**
     * status : 200
     * message : 获取成功
     * result : [{"log_type":"1","create_date":"2017-06-04 00:00:00","bzsm":"","ah_number":"2017(民)第 0071 号"},{"log_type":"1","create_date":"2017-06-04 00:00:00","bzsm":"我要跑了，你来抓我啊·························","ah_number":"2017(民)第 0071 号"}]
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
         * log_type : 1
         * create_date : 2017-06-04 00:00:00
         * bzsm :
         * ah_number : 2017(民)第 0071 号
         */

        private String log_type;
        private String create_date;
        private String bzsm;
        private String ah_number;

        public String getLog_type() {
            return log_type;
        }

        public void setLog_type(String log_type) {
            this.log_type = log_type;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }

        public String getBzsm() {
            return bzsm;
        }

        public void setBzsm(String bzsm) {
            this.bzsm = bzsm;
        }

        public String getAh_number() {
            return ah_number;
        }

        public void setAh_number(String ah_number) {
            this.ah_number = ah_number;
        }
    }
}
