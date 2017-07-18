package com.example.administrator.qingming.model;

import java.util.List;

/**
 * Created by Administrator on 2017/6/2 0002.
 */

public class ModelContactDetail {

    /**
     * status : 200
     * message : 获取成功
     * result : [{"name":"谭小刀","ssjg":"铭轩科技发放的沙发沙发斯蒂芬","tel":"15213615312","cz":"86  519-85129131","email":"1123153265@qq.com","jtzz":"重庆市沙坪坝区三峡广场","bz":"。。。。。。。。。。。"}]
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
         * name : 谭小刀
         * ssjg : 铭轩科技发放的沙发沙发斯蒂芬
         * tel : 15213615312
         * cz : 86  519-85129131
         * email : 1123153265@qq.com
         * jtzz : 重庆市沙坪坝区三峡广场
         * bz : 。。。。。。。。。。。
         */

        private String name;
        private String ssjg;
        private String tel;
        private String cz;
        private String email;
        private String jtzz;
        private String bz;

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

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getCz() {
            return cz;
        }

        public void setCz(String cz) {
            this.cz = cz;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getJtzz() {
            return jtzz;
        }

        public void setJtzz(String jtzz) {
            this.jtzz = jtzz;
        }

        public String getBz() {
            return bz;
        }

        public void setBz(String bz) {
            this.bz = bz;
        }
    }
}
