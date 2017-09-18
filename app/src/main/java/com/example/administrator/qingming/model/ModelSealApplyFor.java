package com.example.administrator.qingming.model;

import java.util.List;

/**
 * Created by Administrator on 2017/9/7 0007.
 */

public class ModelSealApplyFor {

    /**
     * status : 200
     * message : 获取成功
     * result : [{"id":"25","seal_name":"( 2017 )律师民函第001号","official_seal":"1","sealwtr":"tanhuan","ah_number":"2017(民)第001号","files_id":"766","create_date":"2017-08-11 00:00:00","seal_state":"000000002","lyr":"测试主任","ay":"韦淋","wtr":"韦","dsr":"65465","dfdsr":"你"},{"id":"20","seal_name":"( 2017 )律师民第045号","official_seal":"5","sealwtr":"liujiawen","ah_number":"2017(民)第045号","files_id":"760","create_date":"2017-08-10 00:00:00","seal_state":"000000001","lyr":"测试主任","ay":"测试","wtr":"蒲祖辉","dsr":"不知道","dfdsr":"不明白"}]
     */

    private int status;
    private String message;
    private String sealwtr;
    private String official_seal;

    public String getSealwtr() {
        return sealwtr;
    }

    public void setSealwtr(String sealwtr) {
        this.sealwtr = sealwtr;
    }

    public String getOfficial_seal() {
        return official_seal;
    }

    public void setOfficial_seal(String official_seal) {
        this.official_seal = official_seal;
    }

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
         * id : 25
         * seal_name : ( 2017 )律师民函第001号
         * official_seal : 1
         * sealwtr : tanhuan
         * ah_number : 2017(民)第001号
         * files_id : 766
         * create_date : 2017-08-11 00:00:00
         * seal_state : 000000002
         * lyr : 测试主任
         * ay : 韦淋
         * wtr : 韦
         * dsr : 65465
         * dfdsr : 你
         */

        private String id;
        private String seal_name;
        private String official_seal;
        private String sealwtr;
        private String ah_number;
        private String files_id;
        private String create_date;
        private String seal_state;
        private String lyr;
        private String ay;
        private String wtr;
        private String file_state;

        public String getFile_state() {
            return file_state;
        }

        public void setFile_state(String file_state) {
            this.file_state = file_state;
        }

        public String getXzdz() {
            return xzdz;
        }

        public void setXzdz(String xzdz) {
            this.xzdz = xzdz;
        }

        private String xzdz;

        public String getCase_id() {
            return case_id;
        }

        public void setCase_id(String case_id) {
            this.case_id = case_id;
        }

        private String dsr;
        private String case_id;

        public String getWjm() {
            return wjm;
        }

        public void setWjm(String wjm) {
            this.wjm = wjm;
        }

        private String dfdsr;
        private String wjm;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSeal_name() {
            return seal_name;
        }

        public void setSeal_name(String seal_name) {
            this.seal_name = seal_name;
        }

        public String getOfficial_seal() {
            return official_seal;
        }

        public void setOfficial_seal(String official_seal) {
            this.official_seal = official_seal;
        }

        public String getSealwtr() {
            return sealwtr;
        }

        public void setSealwtr(String sealwtr) {
            this.sealwtr = sealwtr;
        }

        public String getAh_number() {
            return ah_number;
        }

        public void setAh_number(String ah_number) {
            this.ah_number = ah_number;
        }

        public String getFiles_id() {
            return files_id;
        }

        public void setFiles_id(String files_id) {
            this.files_id = files_id;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }

        public String getSeal_state() {
            return seal_state;
        }

        public void setSeal_state(String seal_state) {
            this.seal_state = seal_state;
        }

        public String getLyr() {
            return lyr;
        }

        public void setLyr(String lyr) {
            this.lyr = lyr;
        }

        public String getAy() {
            return ay;
        }

        public void setAy(String ay) {
            this.ay = ay;
        }

        public String getWtr() {
            return wtr;
        }

        public void setWtr(String wtr) {
            this.wtr = wtr;
        }

        public String getDsr() {
            return dsr;
        }

        public void setDsr(String dsr) {
            this.dsr = dsr;
        }

        public String getDfdsr() {
            return dfdsr;
        }

        public void setDfdsr(String dfdsr) {
            this.dfdsr = dfdsr;
        }
    }
}
