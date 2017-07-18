package com.example.administrator.qingming.model;

import java.util.List;

/**
 * Created by Administrator on 2017/6/6 0006.
 */

public class ModelChangeLvSi {

    /**
     * status : 200
     * message : 获取成功
     * result : [{"ah_number":"2017(民)第002号","ay":"432423","sarq":"2017-06-13 00:00:00","sffs":"2","dlf":"432432"}]
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
         * ah_number : 2017(民)第002号
         * ay : 432423
         * sarq : 2017-06-13 00:00:00
         * sffs : 2
         * dlf : 432432
         */

        private String ah_number;
        private String ay;
        private String sarq;
        private String sffs;
        private String dlf;

        public String getAh_number() {
            return ah_number;
        }

        public void setAh_number(String ah_number) {
            this.ah_number = ah_number;
        }

        public String getAy() {
            return ay;
        }

        public void setAy(String ay) {
            this.ay = ay;
        }

        public String getSarq() {
            return sarq;
        }

        public void setSarq(String sarq) {
            this.sarq = sarq;
        }

        public String getSffs() {
            return sffs;
        }

        public void setSffs(String sffs) {
            this.sffs = sffs;
        }

        public String getDlf() {
            return dlf;
        }

        public void setDlf(String dlf) {
            this.dlf = dlf;
        }
    }
}
