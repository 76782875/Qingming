package com.example.administrator.qingming.model;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10 0010.
 */

public class ModelQianDao {

    /**
     * status : 200
     * message : 获取成功
     * result : [{"id":"285","daka_time":"2017-07-10 09:25:48","dakalx":"1","dakatype":"0","dakafl":"0"},{"id":"286","daka_time":"2017-07-10 10:32:33","dakalx":"1","dakatype":"1","dakafl":"2"},{"id":"287","daka_time":"2017-07-10 10:32:33","dakalx":"1","dakatype":"1","dakafl":"1"},{"id":"288","daka_time":"2017-07-10 10:49:42","dakalx":"2","dakatype":"1","dakafl":"5"}]
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
         * id : 285
         * daka_time : 2017-07-10 09:25:48
         * dakalx : 1
         * dakatype : 0
         * dakafl : 0
         */

        private String id;
        private String daka_time;
        private String dakalx;
        private String dakatype;
        private String dakafl;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDaka_time() {
            return daka_time;
        }

        public void setDaka_time(String daka_time) {
            this.daka_time = daka_time;
        }

        public String getDakalx() {
            return dakalx;
        }

        public void setDakalx(String dakalx) {
            this.dakalx = dakalx;
        }

        public String getDakatype() {
            return dakatype;
        }

        public void setDakatype(String dakatype) {
            this.dakatype = dakatype;
        }

        public String getDakafl() {
            return dakafl;
        }

        public void setDakafl(String dakafl) {
            this.dakafl = dakafl;
        }
    }
}
