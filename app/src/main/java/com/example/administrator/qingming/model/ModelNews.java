package com.example.administrator.qingming.model;

import java.util.List;

/**
 * Created by Administrator on 2017/5/31 0031.
 */

public class ModelNews {

    /**
     * list : [{"id":82,"theme":"财务收案审批通过","content":"您办理的《2017(刑)第001号》财务收案审批通过！案件已收案！","createDate":1495505482000,"companyId":"1","createId":"1","createName":"后台管理员","glid":96,"accepterId":"1"},{"id":72,"theme":"财务收案审批通过","content":"您办理的《2017(民)第002号》财务收案审批通过！案件已收案！","createDate":1495156623000,"companyId":"27248c1aa1b44822afbe6599ebb9b87f","createId":"49a2d2156f844d22bd41d26556aab2b0","createName":"王麻子","glid":94,"accepterId":"1"},{"id":71,"theme":"财务收案审批通过","content":"您办理的《2017(民)第001号》财务收案审批通过！案件已收案！","createDate":1495156619000,"companyId":"27248c1aa1b44822afbe6599ebb9b87f","createId":"49a2d2156f844d22bd41d26556aab2b0","createName":"王麻子","glid":93,"accepterId":"1"},{"id":69,"theme":"主任收案审批通过","content":"您办理的《2017(刑)第001号》主任收案审批通过！将移交财务审批！","createDate":1495156196000,"companyId":"27248c1aa1b44822afbe6599ebb9b87f","createId":"3cd631e5bdb8425793000ff6908b09b3","createName":"主任","glid":96,"accepterId":"1"},{"id":68,"theme":"主任收案审批通过","content":"您办理的《2017(民)第002号》主任收案审批通过！将移交财务审批！","createDate":1495156162000,"companyId":"27248c1aa1b44822afbe6599ebb9b87f","createId":"3cd631e5bdb8425793000ff6908b09b3","createName":"主任","glid":94,"accepterId":"1"},{"id":65,"theme":"主任收案审批通过","content":"您办理的《2017(民)第001号》主任收案审批通过！将移交财务审批！","createDate":1495075413000,"companyId":"1","createId":"1","createName":"后台管理员","glid":93,"accepterId":"1"}]
     * message : 获取成功！
     * status : 200
     */

    private String message;
    private int status;
    private List<ListBean> list;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 82
         * theme : 财务收案审批通过
         * content : 您办理的《2017(刑)第001号》财务收案审批通过！案件已收案！
         * createDate : 1495505482000
         * companyId : 1
         * createId : 1
         * createName : 后台管理员
         * glid : 96
         * accepterId : 1
         */

        private int id;
        private String theme;
        private String content;
        private long createDate;
        private String companyId;
        private String createId;
        private String createName;
        private int glid;
        private String accepterId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTheme() {
            return theme;
        }

        public void setTheme(String theme) {
            this.theme = theme;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public String getCompanyId() {
            return companyId;
        }

        public void setCompanyId(String companyId) {
            this.companyId = companyId;
        }

        public String getCreateId() {
            return createId;
        }

        public void setCreateId(String createId) {
            this.createId = createId;
        }

        public String getCreateName() {
            return createName;
        }

        public void setCreateName(String createName) {
            this.createName = createName;
        }

        public int getGlid() {
            return glid;
        }

        public void setGlid(int glid) {
            this.glid = glid;
        }

        public String getAccepterId() {
            return accepterId;
        }

        public void setAccepterId(String accepterId) {
            this.accepterId = accepterId;
        }
    }
}
