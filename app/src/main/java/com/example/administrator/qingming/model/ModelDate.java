package com.example.administrator.qingming.model;

/**
 * Created by Administrator on 2017/5/31 0031.
 */

public class ModelDate {

    /**
     * message : 登录成功！
     * user : {"searchFromPage":false,"remarks":"最高管理员","createDate":"2013-05-27 00:00:00","updateDate":"2017-05-31 01:50:14","delFlag":"0","id":"1","loginName":"yunlvsi","no":"0001","name":"后台管理员","email":"thinkgem@163.com","phone":"8675","mobile":"15202364444","userType":"1","loginIp":"192.168.188.248","loginDate":"2017-05-31 07:15:09","roleNames":"亲民系统管理员","admin":true}
     * status : 200
     * cid : 1
     */

    private String message;
    private UserBean user;
    private int status;
    private String cid;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public static class UserBean {
        /**
         * searchFromPage : false
         * remarks : 最高管理员
         * createDate : 2013-05-27 00:00:00
         * updateDate : 2017-05-31 01:50:14
         * delFlag : 0
         * id : 1
         * loginName : yunlvsi
         * no : 0001
         * name : 后台管理员
         * email : thinkgem@163.com
         * phone : 8675
         * mobile : 15202364444
         * userType : 1
         * loginIp : 192.168.188.248
         * loginDate : 2017-05-31 07:15:09
         * roleNames : 亲民系统管理员
         * admin : true
         */

        private boolean searchFromPage;
        private String remarks;
        private String createDate;
        private String updateDate;
        private String delFlag;
        private String id;
        private String loginName;
        private String no;
        private String name;
        private String email;
        private String phone;
        private String mobile;
        private String userType;
        private String loginIp;
        private String loginDate;
        private String roleNames;
        private boolean admin;

        public boolean isSearchFromPage() {
            return searchFromPage;
        }

        public void setSearchFromPage(boolean searchFromPage) {
            this.searchFromPage = searchFromPage;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public String getLoginIp() {
            return loginIp;
        }

        public void setLoginIp(String loginIp) {
            this.loginIp = loginIp;
        }

        public String getLoginDate() {
            return loginDate;
        }

        public void setLoginDate(String loginDate) {
            this.loginDate = loginDate;
        }

        public String getRoleNames() {
            return roleNames;
        }

        public void setRoleNames(String roleNames) {
            this.roleNames = roleNames;
        }

        public boolean isAdmin() {
            return admin;
        }

        public void setAdmin(boolean admin) {
            this.admin = admin;
        }
    }
}
