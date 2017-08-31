package com.example.administrator.qingming.model;

import java.util.List;

/**
 * Created by Administrator on 2017/8/29 0029.
 */

public class ModelFaPiaoAh {

    /**
     * status : 200
     * message : 获取成功
     * result : [{"fylx":"1","sfje":"654.00","zt":"2","ah_number":"2017(民)第003号","wtr":"韦","ay":"韦淋","dlf":"1","id":"115"},{"fylx":"1","sfje":"2.88","zt":"1","ah_number":"2017(民)第001号","wtr":"韦淋","ay":"吃鸡","dlf":"1","id":"113"},{"fylx":"1","sfje":"888.00","zt":"1","ah_number":"2017(刑)第001号","wtr":"ssss","ay":"sss","dlf":"555","id":"112"},{"fylx":"1","sfje":"5555555.00","zt":"3","ah_number":"2017(民)第002号","wtr":"全国","ay":"吃鸡","dlf":"1","id":"114"},{"fylx":"1","sfje":"11111.00","zt":"3","ah_number":"2017(刑)第001号","wtr":"ssss","ay":"sss","dlf":"555","id":"112"}]
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
         * fylx : 1
         * sfje : 654.00
         * zt : 2
         * ah_number : 2017(民)第003号
         * wtr : 韦
         * ay : 韦淋
         * dlf : 1
         * id : 115
         */

        private String fylx;
        private Double sfje;
        private String zt;
        private String ah_number;
        private String wtr;
        private String ay;
        private String dlf;
        private String id;

        public String getFylx() {
            return fylx;
        }

        public void setFylx(String fylx) {
            this.fylx = fylx;
        }

        public Double getSfje() {
            return sfje;
        }

        public void setSfje(Double sfje) {
            this.sfje = sfje;
        }

        public String getZt() {
            return zt;
        }

        public void setZt(String zt) {
            this.zt = zt;
        }

        public String getAh_number() {
            return ah_number;
        }

        public void setAh_number(String ah_number) {
            this.ah_number = ah_number;
        }

        public String getWtr() {
            return wtr;
        }

        public void setWtr(String wtr) {
            this.wtr = wtr;
        }

        public String getAy() {
            return ay;
        }

        public void setAy(String ay) {
            this.ay = ay;
        }

        public String getDlf() {
            return dlf;
        }

        public void setDlf(String dlf) {
            this.dlf = dlf;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
