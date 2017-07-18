package com.example.administrator.qingming.model;

import java.util.List;

/**
 * Created by Administrator on 2017/6/5 0005.
 */

public class ModelChange {

    /**
     * status : 200
     * message : 获取成功
     * result : [{"id":"4","ah_number":"2017(民)第002号","wtr":"3423423","dfdsr":"3423423","attorney_id":null,"create_id":"1","name":"后台管理员"},{"id":"6","ah_number":"2017(民)第004号","wtr":"43534","dfdsr":"45345","attorney_id":null,"create_id":"1","name":"后台管理员"},{"id":"95","ah_number":"2017(刑)第001号","wtr":"零大胖","dfdsr":"零收益","attorney_id":"1","create_id":"1","name":"后台管理员"},{"id":"96","ah_number":"2017(刑)第001号","wtr":"刘佳文","dfdsr":"谭欢","attorney_id":null,"create_id":"1","name":"后台管理员"},{"id":"98","ah_number":"2017(刑)第001号","wtr":"刘佳文","dfdsr":"凤姐","attorney_id":null,"create_id":"1","name":"后台管理员"},{"id":"99","ah_number":"2017(民)第003号","wtr":"老奶奶","dfdsr":"小学生","attorney_id":null,"create_id":"1","name":"后台管理员"},{"id":"104","ah_number":"2017(民)第001号","wtr":"刘佳汶","dfdsr":"小学生","attorney_id":null,"create_id":"1","name":"后台管理员"},{"id":"111","ah_number":"2017(民)第002号","wtr":"ggggggggggg","dfdsr":"3423423","attorney_id":null,"create_id":"1","name":"后台管理员"},{"id":"112","ah_number":"2017(民)第003号","wtr":"qwwwwww","dfdsr":"5345","attorney_id":null,"create_id":"1","name":"后台管理员"},{"id":"119","ah_number":"2017(民)第010号","wtr":"发生大","dfdsr":"法萨芬","attorney_id":null,"create_id":"1","name":"后台管理员"},{"id":"1273","ah_number":"2017(民)第031号","wtr":"刘佳文","dfdsr":"老奶奶","attorney_id":null,"create_id":"1","name":"后台管理员"},{"id":"1274","ah_number":"2017(民)第004号","wtr":"ggggg","dfdsr":"gggghg","attorney_id":null,"create_id":"1","name":"后台管理员"},{"id":"1280","ah_number":"2017(非)第001号","wtr":"x学校","dfdsr":"学生","attorney_id":null,"create_id":"1","name":"后台管理员"},{"id":"1281","ah_number":"2017(民)第005号","wtr":"x学校","dfdsr":"学生","attorney_id":null,"create_id":"1","name":"后台管理员"}]
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
         * id : 4
         * ah_number : 2017(民)第002号
         * wtr : 3423423
         * dfdsr : 3423423
         * attorney_id : null
         * create_id : 1
         * name : 后台管理员
         */

        private String id;
        private String ah_number;
        private String wtr;
        private String dfdsr;
        private Object attorney_id;
        private String create_id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getDfdsr() {
            return dfdsr;
        }

        public void setDfdsr(String dfdsr) {
            this.dfdsr = dfdsr;
        }

        public Object getAttorney_id() {
            return attorney_id;
        }

        public void setAttorney_id(Object attorney_id) {
            this.attorney_id = attorney_id;
        }

        public String getCreate_id() {
            return create_id;
        }

        public void setCreate_id(String create_id) {
            this.create_id = create_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
