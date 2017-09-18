package com.example.administrator.qingming.model;

import java.util.List;

/**
 * Created by Administrator on 2017/9/8 0008.
 */

public class ModelSealPngList {

    /**
     * status : 200
     * message : 获取成功
     * result : [{"filename":"教育装备与实验教学管理信息化方案20170717_第1页.png","pngdz":"b306900362b54ac78d9a987db793785b\\png\\2017080315251429_1.png","png_state":"1","create_date":"2017-08-03 15:26:24"},{"filename":"教育装备与实验教学管理信息化方案20170717_第2页.png","pngdz":"b306900362b54ac78d9a987db793785b\\png\\2017080315251429_2.png","png_state":"1","create_date":"2017-08-03 15:26:24"},{"filename":"教育装备与实验教学管理信息化方案20170717_第3页.png","pngdz":"b306900362b54ac78d9a987db793785b\\png\\2017080315251429_3.png","png_state":"1","create_date":"2017-08-03 15:26:24"},{"filename":"教育装备与实验教学管理信息化方案20170717_第4页.png","pngdz":"b306900362b54ac78d9a987db793785b\\png\\2017080315251429_4.png","png_state":"1","create_date":"2017-08-03 15:26:24"},{"filename":"教育装备与实验教学管理信息化方案20170717_第5页.png","pngdz":"b306900362b54ac78d9a987db793785b\\png\\2017080315251429_5.png","png_state":"1","create_date":"2017-08-03 15:26:24"},{"filename":"教育装备与实验教学管理信息化方案20170717_第6页.png","pngdz":"b306900362b54ac78d9a987db793785b\\png\\2017080315251429_6.png","png_state":"1","create_date":"2017-08-03 15:26:24"},{"filename":"教育装备与实验教学管理信息化方案20170717_第7页.png","pngdz":"b306900362b54ac78d9a987db793785b\\png\\2017080315251429_7.png","png_state":"1","create_date":"2017-08-03 15:26:24"},{"filename":"教育装备与实验教学管理信息化方案20170717_第8页.png","pngdz":"b306900362b54ac78d9a987db793785b\\png\\2017080315251429_8.png","png_state":"1","create_date":"2017-08-03 15:26:24"},{"filename":"教育装备与实验教学管理信息化方案20170717_第9页.png","pngdz":"b306900362b54ac78d9a987db793785b\\png\\2017080315251429_9.png","png_state":"1","create_date":"2017-08-03 15:26:24"},{"filename":"教育装备与实验教学管理信息化方案20170717_第10页.png","pngdz":"b306900362b54ac78d9a987db793785b\\png\\2017080315251429_10.png","png_state":"1","create_date":"2017-08-03 15:26:24"},{"filename":"教育装备与实验教学管理信息化方案20170717_第11页.png","pngdz":"b306900362b54ac78d9a987db793785b\\png\\2017080315251429_11.png","png_state":"1","create_date":"2017-08-03 15:26:24"},{"filename":"教育装备与实验教学管理信息化方案20170717_第12页.png","pngdz":"b306900362b54ac78d9a987db793785b\\png\\2017080315251429_12.png","png_state":"1","create_date":"2017-08-03 15:26:24"},{"filename":"教育装备与实验教学管理信息化方案20170717_第13页.png","pngdz":"b306900362b54ac78d9a987db793785b\\png\\2017080315251429_13.png","png_state":"1","create_date":"2017-08-03 15:26:24"},{"filename":"教育装备与实验教学管理信息化方案20170717_第14页.png","pngdz":"b306900362b54ac78d9a987db793785b\\png\\2017080315251429_14.png","png_state":"1","create_date":"2017-08-03 15:26:24"}]
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
         * filename : 教育装备与实验教学管理信息化方案20170717_第1页.png
         * pngdz : b306900362b54ac78d9a987db793785b\png\2017080315251429_1.png
         * png_state : 1
         * create_date : 2017-08-03 15:26:24
         */

        private String filename;
        private String new_filename;
        private String pngdz;
        private String png_state;
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNew_filename() {
            return new_filename;
        }

        public void setNew_filename(String new_filename) {
            this.new_filename = new_filename;
        }

        private String create_date;

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public String getPngdz() {
            return pngdz;
        }

        public void setPngdz(String pngdz) {
            this.pngdz = pngdz;
        }

        public String getPng_state() {
            return png_state;
        }

        public void setPng_state(String png_state) {
            this.png_state = png_state;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }
    }
}
