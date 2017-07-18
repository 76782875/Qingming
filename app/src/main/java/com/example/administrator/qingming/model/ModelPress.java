package com.example.administrator.qingming.model;

import java.util.List;

/**
 * Created by Administrator on 2017/6/2 0002.
 */

public class ModelPress {

    /**
     * status : 200
     * message : 获取成功
     * result : [{"id":"216","category_id":"11","product_name":"习近平同阿根廷总统马克里举行会谈 两国元首一致同意推动中阿全面战略伙伴关系得到更大发展","product_brand":"14","insert_time":"2017-05-18 11:02:00","insert_id":"1","insert_name":"后台管理员"},{"id":"217","category_id":"11","product_name":" 军报刊文：\u201c小鲜肉\u201d要多学学能战善战\u201c老戏骨\u201d","product_brand":"15","insert_time":"2017-05-18 11:02:40","insert_id":"1","insert_name":"后台管理员"},{"id":"218","category_id":"11","product_name":"杨幂头戴毛巾搞怪自嘲疯了 网友：她傻了","product_brand":"15","insert_time":"2017-05-18 11:03:35","insert_id":"1","insert_name":"后台管理员"},{"id":"219","category_id":"11","product_name":" 第70届戛纳电影节开幕 官方镜头最多的华人女星竟是\u2026","product_brand":"14","insert_time":"2017-05-18 11:05:10","insert_id":"1","insert_name":"后台管理员"},{"id":"220","category_id":"11","product_name":"北京现代年内推三款新SUV 竞争缤智/CR-V","product_brand":"16","insert_time":"2017-05-18 11:05:35","insert_id":"1","insert_name":"后台管理员"},{"id":"221","category_id":"11","product_name":"北京楼市：调控施压 \u201c锁量\u201d价稳","product_brand":"16","insert_time":"2017-05-18 11:05:59","insert_id":"1","insert_name":"后台管理员"},{"id":"224","category_id":null,"product_name":"周一资讯","product_brand":"14","insert_time":"2017-05-31 08:50:01","insert_id":"1","insert_name":"后台管理员"},{"id":"226","category_id":null,"product_name":"马克龙与普京首次会面：法国对叙利亚问题绝不让步","product_brand":"14","insert_time":"2017-05-31 09:21:17","insert_id":"1","insert_name":"后台管理员"},{"id":"227","category_id":null,"product_name":"中华人民共和国宪法","product_brand":"14","insert_time":"2017-06-03 00:49:27","insert_id":"1","insert_name":"后台管理员"}]
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
         * id : 216
         * category_id : 11
         * product_name : 习近平同阿根廷总统马克里举行会谈 两国元首一致同意推动中阿全面战略伙伴关系得到更大发展
         * product_brand : 14
         * insert_time : 2017-05-18 11:02:00
         * insert_id : 1
         * insert_name : 后台管理员
         */

        private String id;
        private String category_id;
        private String product_name;
        private int product_brand;
        private String insert_time;
        private String insert_id;
        private String insert_name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public int getProduct_brand() {
            return product_brand;
        }

        public void setProduct_brand(int product_brand) {
            this.product_brand = product_brand;
        }

        public String getInsert_time() {
            return insert_time;
        }

        public void setInsert_time(String insert_time) {
            this.insert_time = insert_time;
        }

        public String getInsert_id() {
            return insert_id;
        }

        public void setInsert_id(String insert_id) {
            this.insert_id = insert_id;
        }

        public String getInsert_name() {
            return insert_name;
        }

        public void setInsert_name(String insert_name) {
            this.insert_name = insert_name;
        }
    }
}
